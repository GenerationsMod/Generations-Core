package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.decorations.PastelBeanBagBlock;
import generations.gg.generations.core.generationscore.world.level.block.entities.DyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericDyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.function.Function;

public class SwivelChairBlock extends DyeableBlock<GenericDyedVariantBlockEntity, SwivelChairBlock> {
    public SwivelChairBlock(Function<DyeColor, DyedBlockItem<GenericDyedVariantBlockEntity, SwivelChairBlock>> function, BlockBehaviour.Properties props) {
        super(function, GenerationsBlockEntities.GENERIC_DYED_VARIANT, props, GenerationsBlockEntityModels.SWIVEL_CHAIR);
    }
}
