package generations.gg.generations.core.generationscore.world.level.block.entities;

import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ClockBlockEntity extends DyedVariantBlockEntity<ClockBlockEntity> implements ModelContextProviders.FrameProvider {
    public ClockBlockEntity(BlockPos arg2, BlockState arg3) {
        super(GenerationsBlockEntities.CLOCK.get(), arg2, arg3);
    }

    @Override
    public float getFrame() {
        assert Minecraft.getInstance().level != null;
        var time = (float) Minecraft.getInstance().level.getDayTime() % 24000 + 6000;
        time /= 240f; //TODO: Verify this is 100% accurate.
        return time;
    }

    @Override
    public String getAnimation() {
        return "time";
    }

    @Override
    public boolean isAnimated() {
        return true;
    }
}
