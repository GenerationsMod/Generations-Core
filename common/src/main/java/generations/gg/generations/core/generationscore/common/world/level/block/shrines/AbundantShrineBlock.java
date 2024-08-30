package generations.gg.generations.core.generationscore.common.world.level.block.shrines;

import generations.gg.generations.core.generationscore.common.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.AbundantShrineBlockEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Consumer;

public class AbundantShrineBlock extends ShrineBlock<AbundantShrineBlockEntity> {

    private static final Consumer<ServerPlayer> playerConsumer = p -> GenerationsUtils.giveItem(p, GenerationsItems.REVEAL_GLASS.get().getDefaultInstance());

    public AbundantShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.ABUNDANT_SHRINE, GenerationsBlockEntityModels.ABUNDANT_SHRINE);
    }
}
