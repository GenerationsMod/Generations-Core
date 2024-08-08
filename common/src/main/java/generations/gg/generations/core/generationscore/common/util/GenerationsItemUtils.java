package generations.gg.generations.core.generationscore.common.util;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.item.GenericRotatableBlockItem;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import generations.gg.generations.core.generationscore.common.world.item.GenericRotatableBlockItem;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import generations.gg.generations.core.generationscore.common.world.item.GenericRotatableBlockItem;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class GenerationsItemUtils {
    public static boolean canEquip(ItemStack carried, EquipmentSlot equipmentslottype, Entity entity) {
        return GenerationsCore.getImplementation().canEquip(carried, equipmentslottype, entity);
    }

    public static <T extends Block> BlockItem generateBlockItem(T t, Item.Properties properties) {
        if(t instanceof GenericRotatableModelBlock<?> block) {
            return new GenericRotatableBlockItem(block, properties);
        } else {
            return new BlockItem(t, properties);
        }
    }
}
