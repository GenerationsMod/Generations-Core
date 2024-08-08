package generations.gg.generations.core.generationscore.common.world.level.block;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.DefaultPcBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.PcBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

public class TablePc extends PcBlock<DefaultPcBlockEntity, TablePc> {
    public TablePc(BlockBehaviour.Properties properties, ResourceLocation tablePc, int i, int i1, int i2) {
        super(GenerationsBlockEntities.PC, DefaultPcBlockEntity.class, properties, GenerationsBlockEntityModels.TABLE_PC, 0, 0, 0);
    }

    @NotNull
    @Override
    public BlockEntityTicker<DefaultPcBlockEntity> getTicker() {
        return DefaultPcBlockEntity.TICKER;
    }
}
