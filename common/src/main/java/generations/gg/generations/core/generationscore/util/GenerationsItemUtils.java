package generations.gg.generations.core.generationscore.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import generations.gg.generations.core.generationscore.world.item.GenericRotatableBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class GenerationsItemUtils {
    @ExpectPlatform
    public static boolean canEquip(ItemStack carried, EquipmentSlot equipmentslottype, Entity entity) {
        throw new RuntimeException();
    }

    public static <T extends Block> BlockItem generateBlockItem(T t, Item.Properties properties) {
        if(t instanceof GenericRotatableModelBlock<?> block) {
            return new GenericRotatableBlockItem(block, properties);
        } else {
            return new BlockItem(t, properties);
        }
    }
}
