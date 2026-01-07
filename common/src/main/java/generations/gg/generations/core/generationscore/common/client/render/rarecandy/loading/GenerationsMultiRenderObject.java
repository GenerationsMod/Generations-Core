package generations.gg.generations.core.generationscore.common.client.render.rarecandy.loading;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferUploader;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.Pipelines;
import gg.generations.rarecandy.pokeutils.BlendType;
import gg.generations.rarecandy.pokeutils.CullType;
import gg.generations.rarecandy.renderer.animation.AnimationInstance;
import gg.generations.rarecandy.renderer.components.MultiRenderObject;
import gg.generations.rarecandy.renderer.loading.ModelLoader;
import gg.generations.rarecandy.renderer.rendering.ObjectInstance;
import gg.generations.rarecandy.renderer.rendering.RenderStage;
import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance;
import net.minecraft.client.renderer.RenderType;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL43;

import java.util.List;

public class GenerationsMultiRenderObject extends MultiRenderObject {
    public GenerationsMultiRenderObject(ModelLoader.Names names) {
        super(names);
    }

    @Override
    public boolean shouldRender(int mesh, ObjectInstance instance) {
        if (instance instanceof AnimatedObjectInstance animationInstance) {
            AnimationInstance animation = animationInstance.currentAnimation;
            if (animation != null) {
                var anim = animation.getAnimation();

                if(anim != null) {

                    int animId = animationInstance.currentAnimation.getAnimation().id;
                    if (this.hideDuringAnimation[mesh][animId]) {
                        return true;
                    }
                }
            }
        }

        return !this.getVariant(mesh, instance.variant()).hide();
    }

    @Override
    public void render(RenderStage renderStage, List<ObjectInstance> list) {
        updateSSBOs();
        Pipelines.vao.bind();

        RenderSystem.glBindBuffer(GL43.GL_ARRAY_BUFFER, destBuffer);

        for (int i = 0; i < instances.size(); i++) {
            Pipelines.transformVertices(this, i);

            var instance = instances.get(i);
            for (int mesh = 0; mesh < meshes.length; mesh++) {
                if (shouldRender(mesh, instance)) {
                    var model = this.meshes[mesh];

                    if (model == null) {
                        continue;
                    }


                    System.out.println("modelBuffer: " + modelBuffer);
                    System.out.println("destBuffer: " + destBuffer);
                    System.out.println("instanceBuffer.bufferId: " + instanceBuffer.getBufferId());
                    System.out.println("uvTransformBuffer.bufferId: " + uvTransformBuffer.getBufferId());

                    Pipelines.processMaterial(instance, this, mesh);

                    Pipelines.commonOn();

                    Pipelines.solidOn();
                    model.render();
                    Pipelines.solidOff();

                    Pipelines.emissiveOn();
                    model.render();
                    Pipelines.emissiveOff();
                }
            }
        }
    }

    //TODO: calculate
    @Override
    public int targetVertexStride() {
        return 64;
    }
}
