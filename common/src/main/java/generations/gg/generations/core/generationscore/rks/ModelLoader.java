/*
 * Copyright (C) 2023 ThePokeCraftMod
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package generations.gg.generations.core.generationscore.rks;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.systems.RenderSystem;
import com.thepokecraftmod.rks.FileLocator;
import com.thepokecraftmod.rks.Pair;
import com.thepokecraftmod.rks.assimp.AssimpModelLoader;
import com.thepokecraftmod.rks.draw.MeshDrawCommand;
import com.thepokecraftmod.rks.model.Mesh;
import com.thepokecraftmod.rks.model.Model;
import com.thepokecraftmod.rks.model.animation.Skeleton;
import com.thepokecraftmod.rks.scene.FullMesh;
import com.thepokecraftmod.rks.scene.MeshObject;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector4f;
import org.lwjgl.assimp.Assimp;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryUtil;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.*;
import java.util.function.Supplier;

public class ModelLoader {
    private static final Map<ResourceLocation, ModelContext> MODEL_CACHE = Maps.newConcurrentMap();
    private static final List<ResourceLocation> LOADING_MODELS = new ArrayList<>();

    public static Optional<ModelContext> getPokemon(ResourceLocation species) {
        if (MODEL_CACHE.containsKey(species)) {
            LOADING_MODELS.remove(species);
            return Optional.of(MODEL_CACHE.get(species));
        } else {
            if (!LOADING_MODELS.contains(species)) loadPokemon(species);
            return Optional.empty();
        }
    }

    public static void loadPokemon(ResourceLocation modelLocation) {
        // TODO: check ResourceManager to see if a replacement exists
        LOADING_MODELS.add(modelLocation);
        var modelRepo = PokeCraftRKSImpl.getInstance().getRepo(PokeCraftRKSImpl.MODEL_REPOSITORY);
        PokeCraftRKSImpl.getInstance().modelLoadingThreads.submit(() -> new ReadyToUploadObject(modelLocation, modelRepo));
    }

    public static class ReadyToUploadObject implements FileLocator {
        private static final ResourceLocation FALLBACK = GenerationsCore.id("fallback");
        private final ResourceLocation root;
        private final RepoFs repo;

        public ReadyToUploadObject(ResourceLocation modelLoc, RepoFs repo) {
            this.root = repo.entries.containsKey(new ResourceLocation(modelLoc.toString() + "/model.gltf")) ? modelLoc : FALLBACK;
            this.repo = repo;
            var model = AssimpModelLoader.load("model.gltf", this, Assimp.aiProcess_GenNormals | Assimp.aiProcess_LimitBoneWeights);
            var object = loadAnimatedMeshes(model);
            var shaderFunction = PokeCraftRKSImpl.getInstance().shadingMethods.get(model.config().shadingMethod);
            var material = new MaterialUploader(model, this, shaderFunction);

            Minecraft.getInstance().tell(() -> {
                RenderSystem.assertOnRenderThread();
                var resolvedObject = object.get();
                for (var meshObject : resolvedObject.objects) meshObject.setup(shaderFunction.apply("unimplemented"));

                material.upload();
                MODEL_CACHE.put(modelLoc, new ModelContext(
                        resolvedObject,
                        model,
                        shaderFunction,
                        this,
                        material
                ));
            });
        }

        public static Supplier<FullMesh> loadAnimatedMeshes(Model model) {
            var indexBuffers = new ArrayList<ByteBuffer>();
            var vertexBuffers = new ArrayList<FloatBuffer>();
            var uvBuffers = new ArrayList<FloatBuffer>();
            var normalBuffers = new ArrayList<FloatBuffer>();
            var jointBuffers = new ArrayList<FloatBuffer>();
            var weightBuffers = new ArrayList<FloatBuffer>();


            for (var mesh : model.meshes()) {
                if (mesh.bones().size() == 0) throw new RuntimeException("Mesh has no bones");
                var useShort = mesh.indices().size() < Short.MAX_VALUE;

                var indexBuffer = MemoryUtil.memAlloc(mesh.indices().size() * (useShort ? 2 : 4));
                mesh.indices().forEach(integer -> {
                    if (useShort) indexBuffer.putShort((short) (int) integer);
                    else indexBuffer.putInt(integer);
                });
                indexBuffer.flip();
                indexBuffers.add(indexBuffer);

                var vertexBuffer = MemoryUtil.memAllocFloat(mesh.positions().size() * 3);
                mesh.positions().forEach(vec3 -> {
                    vertexBuffer.put(vec3.x());
                    vertexBuffer.put(vec3.y());
                    vertexBuffer.put(vec3.z());
                });
                vertexBuffer.flip();
                vertexBuffers.add(vertexBuffer);

                var uvBuffer = MemoryUtil.memAllocFloat(mesh.uvs().size() * 2);
                mesh.uvs().forEach(vec2 -> {
                    uvBuffer.put(vec2.x());
                    uvBuffer.put(vec2.y());
                });
                uvBuffer.flip();
                uvBuffers.add(uvBuffer);

                var normalBuffer = MemoryUtil.memAllocFloat(mesh.normals().size() * 3);
                mesh.normals().forEach(vec3 -> {
                    normalBuffer.put(vec3.x());
                    normalBuffer.put(vec3.y());
                    normalBuffer.put(vec3.z());
                });
                normalBuffer.flip();
                normalBuffers.add(normalBuffer);

                var jointWeightData = generateJointWeightData(mesh, model.skeleton());
                var jointBuffer = MemoryUtil.memAllocFloat(jointWeightData.a().size() * 4);
                jointWeightData.a().forEach(vec4 -> {
                    jointBuffer.put(vec4.x());
                    jointBuffer.put(vec4.y());
                    jointBuffer.put(vec4.z());
                    jointBuffer.put(vec4.w());
                });
                jointBuffer.flip();
                jointBuffers.add(jointBuffer);

                var weightBuffer = MemoryUtil.memAllocFloat(jointWeightData.b().size() * 4);
                jointWeightData.b().forEach(vec4 -> {
                    weightBuffer.put(vec4.x());
                    weightBuffer.put(vec4.y());
                    weightBuffer.put(vec4.z());
                    weightBuffer.put(vec4.w());
                });
                weightBuffer.flip();
                weightBuffers.add(weightBuffer);
            }

            return () -> {
                var mro = new FullMesh();
                for (int i = 0; i < vertexBuffers.size(); i++) {
                    var mesh = model.meshes()[i];
                    var indexBuffer = indexBuffers.get(i);
                    var vertexBuffer = vertexBuffers.get(i);
                    var uvBuffer = uvBuffers.get(i);
                    var normalBuffer = normalBuffers.get(i);
                    var jointBuffer = jointBuffers.get(i);
                    var weightBuffer = weightBuffers.get(i);
                    var useShort = mesh.indices().size() < Short.MAX_VALUE;
                    var meshObject = new MeshObject(model.materialReferences()[mesh.material()]);
                    var vao = GL30.glGenVertexArrays();
                    GL30.glBindVertexArray(vao);

                    bindArrayBuffer(vertexBuffer);
                    vertexAttribPointer(0, 3);
                    bindArrayBuffer(uvBuffer);
                    vertexAttribPointer(1, 2);
                    bindArrayBuffer(normalBuffer);
                    vertexAttribPointer(2, 3);
                    bindArrayBuffer(jointBuffer);
                    vertexAttribPointer(3, 4);
                    bindArrayBuffer(weightBuffer);
                    vertexAttribPointer(4, 4);

                    var ebo = GL15.glGenBuffers();
                    GL15.glBindBuffer(GL15C.GL_ELEMENT_ARRAY_BUFFER, ebo);
                    GL15.glBufferData(GL15C.GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL15.GL_STATIC_DRAW);

                    meshObject.model.meshDrawCommands.add(new MeshDrawCommand(
                            vao,
                            GL11C.GL_TRIANGLES,
                            useShort ? GL11C.GL_UNSIGNED_SHORT : GL11C.GL_UNSIGNED_INT,
                            ebo,
                            mesh.indices().size()
                    ));

                    PokeCraftRKSImpl.getInstance().modelLoadingThreads.submit(() -> {
                        MemoryUtil.memFree(indexBuffer);
                        MemoryUtil.memFree(vertexBuffer);
                        MemoryUtil.memFree(uvBuffer);
                        MemoryUtil.memFree(normalBuffer);
                        MemoryUtil.memFree(jointBuffer);
                        MemoryUtil.memFree(weightBuffer);
                    });
                    mro.add(meshObject);
                }

                return mro;
            };
        }

        private static Pair<List<Vector4f>, List<Vector4f>> generateJointWeightData(Mesh mesh, Skeleton skeleton) {
            var dataSize = 4 * 2;
            var data = new float[mesh.positions().size() * dataSize];
            var bone_index_map0 = new HashMap<Integer, Integer>();
            var bone_index_map1 = new HashMap<Integer, Integer>();

            for (var boneId = 0; boneId < mesh.bones().size(); boneId++) {
                var bone = Objects.requireNonNull(mesh.bones().get(boneId));
                var joinedBoneId = skeleton.getId(bone); // gets the global bone id instead of using the meshes bone id

                for (int weightId = 0; weightId < bone.weights.length; weightId++) {
                    var weight = bone.weights[weightId];
                    int vertId = weight.vertexId;
                    int pVertex = vertId * dataSize; // pointer to where a vertex starts in the array.

                    if (!bone_index_map0.containsKey(vertId)) {
                        data[(pVertex)] = joinedBoneId;
                        data[(pVertex) + 2] = weight.weight;
                        bone_index_map0.put(vertId, 0);
                    } else if (bone_index_map0.get(vertId) == 0) {
                        data[(pVertex) + 1] = joinedBoneId;
                        data[(pVertex) + 3] = weight.weight;
                        bone_index_map0.put(vertId, 1);
                    } else if (!bone_index_map1.containsKey(vertId)) {
                        data[(pVertex) + 4] = joinedBoneId;
                        data[(pVertex) + 6] = weight.weight;
                        bone_index_map1.put(vertId, 0);
                    } else if (bone_index_map1.get(vertId) == 0) {
                        data[(pVertex) + 5] = joinedBoneId;
                        data[(pVertex) + 7] = weight.weight;
                        bone_index_map1.put(vertId, 1);
                    } else throw new RuntimeException("Max 4 bones per vertex");
                }
            }

            var joints = new ArrayList<Vector4f>();
            var weights = new ArrayList<Vector4f>();

            for (int i = 0; i < data.length; i += dataSize) {
                joints.add(new Vector4f(data[i], data[i + 1], data[i + 4], data[i + 5]));
                weights.add(new Vector4f(data[i + 2], data[i + 3], data[i + 6], data[i + 7]));
            }

            return new Pair<>(joints, weights);
        }

        private static void vertexAttribPointer(int binding, int numComponents) {
            GL20.glEnableVertexAttribArray(binding);
            GL20.glVertexAttribPointer(
                    binding,
                    numComponents,
                    GL11.GL_FLOAT,
                    false,
                    0,
                    0
            );
        }

        private static void bindArrayBuffer(FloatBuffer data) {
            var glBufferView = GL15.glGenBuffers();
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, glBufferView);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
        }

        @Override
        public byte[] getFile(String str) {
            return repo.getResource(new ResourceLocation(String.join("/", root.toString(), str)));
        }

        @Override
        public BufferedImage readImage(String name) {
            throw new RuntimeException("unimplemented");
        }
    }
}