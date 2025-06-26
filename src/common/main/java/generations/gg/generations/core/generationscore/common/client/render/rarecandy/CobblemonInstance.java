package generations.gg.generations.core.generationscore.common.client.render.rarecandy;

import generations.gg.generations.core.generationscore.common.client.TeraProvider;
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import gg.generations.rarecandy.renderer.animation.Animation;
import gg.generations.rarecandy.renderer.animation.AnimationInstance;
import gg.generations.rarecandy.renderer.animation.Transform;
import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class CobblemonInstance extends AnimatedObjectInstance implements BlockLightValueProvider, TeraProvider {

    public Matrix4f[] matrixTransforms;
    public Transform offsets;
    private final Vector3f tint = new Vector3f();
    private boolean teraActive = false;

    public Transform getOffset(String material) {
        return this.offsets != null ? this.currentAnimation.getOffset(material) : null;
    }
    private int light;

    public CobblemonInstance(Matrix4f transformationMatrix, Matrix4f viewMatrix, String materialId) {
        super(transformationMatrix, viewMatrix, materialId);}

    @Override
    public void changeAnimation(AnimationInstance newAnimation) {

    }

    public void setAnimation(Animation animation) {
        if(currentAnimation == null) currentAnimation = new StaticAnimationInstance(animation);
        ((StaticAnimationInstance) currentAnimation).setAnimation(animation);

    }

    @Override
    public Matrix4f[] getTransforms() {
        return matrixTransforms;
    }

    @Override
    public int getLight() {
        return light;
    }

    @Override
    public void setLight(int light) {
        this.light = light;
    }

    public Vector3f getTint() {
        return tint;
    }

    @Override
    public boolean getTeraActive() {
        return teraActive;
    }

    @Override
    public void setTeraActive(boolean teraActive) {
        this.teraActive = teraActive;
    }

    private static class StaticAnimationInstance extends AnimationInstance {
        public StaticAnimationInstance(Animation animation) {
            super(animation);
        }

        public void setAnimation(Animation animation) {
            this.animation = animation;
        }
    }
}