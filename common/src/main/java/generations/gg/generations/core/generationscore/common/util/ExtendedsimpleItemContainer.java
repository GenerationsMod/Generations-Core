package generations.gg.generations.core.generationscore.common.util;

import earth.terrarium.botarium.common.item.SimpleItemContainer;
import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage;
import generations.gg.generations.core.generationscore.common.GenerationsStorage;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class ExtendedsimpleItemContainer extends SimpleItemStorage {
    public ExtendedsimpleItemContainer(BlockEntity entity, int size) {
        super(entity, GenerationsStorage.INSTANCE.getITEM_CONTENTS(), size);
    }
}
