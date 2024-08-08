package generations.gg.generations.core.generationscore.fabric.mixin;

import generations.gg.generations.core.generationscore.common.world.level.block.IPortalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PortalShape.class)
public abstract class PortalShapeMixin {
    @Shadow
    @Final
    @Mutable
    private static BlockBehaviour.StatePredicate FRAME;

    static {
        FRAME = (blockState, blockGetter, blockPos) -> ((IPortalBlock) blockState.getBlock()).isPortalFrame(blockState, blockGetter, blockPos);
    }
}
