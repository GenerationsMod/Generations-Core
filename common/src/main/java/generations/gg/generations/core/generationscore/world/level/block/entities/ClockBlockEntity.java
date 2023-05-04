package generations.gg.generations.core.generationscore.world.level.block.entities;

import com.pokemod.pokemod.client.model.ModelContextProviders;
import com.pokemod.rarecandy.storage.AnimatedObjectInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ClockBlockEntity extends DyedVariantBlockEntity implements ModelContextProviders.FrameProvider {
    public AnimatedObjectInstance rarecandyInstance;

    public ClockBlockEntity(BlockPos arg2, BlockState arg3) {
        super(PokeModBlockEntities.CLOCK.get(), arg2, arg3);
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
