package generations.gg.generations.core.generationscore.fabric.mixin;

import generations.gg.generations.core.generationscore.world.level.IPortalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PortalShape.class)
public class PortalShapeMixin {
    @Shadow
    @Final
    @Mutable
    private static BlockBehaviour.StatePredicate FRAME;

    static {
        FRAME = (blockState, blockGetter, blockPos) -> ((IPortalBlock) (Object) blockState.getBlock()).isPortalFrame(blockState, blockGetter, blockPos);
    }
}
