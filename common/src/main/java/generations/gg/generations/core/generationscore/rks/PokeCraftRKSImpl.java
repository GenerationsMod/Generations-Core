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

import com.thepokecraftmod.rks.model.texture.TextureType;
import com.thepokecraftmod.rks.pipeline.Shader;
import com.thepokecraftmod.rks.pipeline.UniformBlockReference;
import com.thepokecraftmod.rks.storage.AnimatedObjectInstance;
import com.thepokecraftmod.rks.storage.RksRenderer;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.compress.archivers.tar.TarFile;
import org.jetbrains.annotations.NotNull;
import org.tukaani.xz.XZInputStream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;

public class PokeCraftRKSImpl {
    private static final PokeCraftRKSImpl INSTANCE = new PokeCraftRKSImpl();
    public static final ResourceLocation MODEL_REPOSITORY = GenerationsCore.id("repositories/pokemon/models.fsrepo");
    public static final ResourceLocation ANIMATION_REPOSITORY = GenerationsCore.id("repositories/pokemon/animations.fsrepo");
    private final RksRenderer rks = new RksRenderer();
    private final SharedUniformBlock sharedUniforms = new SharedUniformBlock();
    public final ThreadPoolExecutor modelLoadingThreads = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    public final Map<String, Shader> shaders = new HashMap<>();
    public final Map<String, Function<String, Shader>> shadingMethods = new HashMap<>();
    private final long globalAnimationTimer = System.currentTimeMillis();
    private final Map<ResourceLocation, RepoFs> openRepositories = new HashMap<>();

    public void render() {
        sharedUniforms.update();
        rks.update((System.currentTimeMillis() - globalAnimationTimer) / 1000d);
        rks.render();
        rks.reset();
    }

    public void addToFrame(AnimatedObjectInstance instance) {
        rks.add(instance.object, instance);
    }

    public void onInitialize() {
        var resourceManager = Minecraft.getInstance().getResourceManager();

        shaders.put("default_pokemon", new Shader.Builder().shader(getShader(GenerationsCore.id("shaders/pokemon.vsh")), getShader(GenerationsCore.id("shaders/pokemon.fsh")))
                .uniform(new UniformBlockReference("SharedInfo", 0))
                .uniform(new UniformBlockReference("InstanceInfo", 1))
                .texture(TextureType.ALBEDO)
                .texture(TextureType.NORMALS)
                .texture(TextureType.METALNESS)
                .texture(TextureType.ROUGHNESS)
                .texture(TextureType.AMBIENT_OCCLUSION)
                .texture(TextureType.EMISSIVE)
                .build()
        );

        shadingMethods.put("pokemon", s -> shaders.get("default_pokemon"));

        try {
            if (resourceManager.getResource(MODEL_REPOSITORY).isEmpty())
                throw new RuntimeException("Missing Model Repo");
            else
                openRepositories.put(MODEL_REPOSITORY, new RepoFs(GenerationsCore.MOD_ID, new TarFile(new XZInputStream(resourceManager.open(MODEL_REPOSITORY)).readAllBytes()))); // FIXME: this means the entire archive is in memory. There has to be something we can do...
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onResourceManagerReload() {
        modelLoadingThreads.purge();
        shaders.clear();
        shadingMethods.clear();
        rks.reset();
        openRepositories.values().forEach(is -> {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        openRepositories.clear();
        onInitialize();
    }

    @NotNull
    public RepoFs getRepo(ResourceLocation location) {
        if (openRepositories.containsKey(location)) return openRepositories.get(location);
        else throw new RuntimeException("Fs Repo does not exist \"" + location + "\"");
    }

    private static String getShader(ResourceLocation id) {
        var resourceManager = Minecraft.getInstance().getResourceManager();

        try (var is = resourceManager.open(id)) {
            var s = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            if (!/*PokeCraft.isDevelopmentEnvironment()*/false) s = s.replace("#pragma optionNV(strict on)", "");
            return s;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PokeCraftRKSImpl getInstance() {
        return INSTANCE;
    }
}