package generations.gg.generations.core.generationscore.client.render.rarecandy.animation;

import gg.generations.rarecandy.renderer.animation.Animation;
import gg.generations.rarecandy.renderer.animation.AnimationInstance;

public class FixedFrameAnimationInstance extends AnimationInstance {
    public FixedFrameAnimationInstance(Animation animation, float frame) {
        super(animation);
        this.currentTime = frame;
    }

    @Override
    public void update(double secondsPassed) {
//        super.update(secondsPassed);
    }

    @Override
    public void updateStart(double secondsPassed) {
//        super.updateStart(secondsPassed);
    }

    @Override
    public boolean shouldDestroy() {
        return super.shouldDestroy();
    }

    public void setCurrentTime(float frame) {
        currentTime = frame;
    }
}