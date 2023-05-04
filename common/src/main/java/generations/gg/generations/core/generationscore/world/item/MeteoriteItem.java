package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MeteoriteItem extends EnchantableItem {
    public MeteoriteItem(Properties arg) {
        super(arg);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide()) {
//            ItemStack stack = player.getItemInHand(usedHand);
//
//            if (EnchantableItem.isEnchanted(stack)) {
//                level.addFreshEntity(new PixelmonEntity(level, PixelmonData.of(BuiltinPixelmonSpecies.DEOXYS.location(), "attack"), player.getOnPos()));
//                stack.shrink(1);
//
//                return InteractionResultHolder.success(stack);
//            }
        }

        return super.use(level, player, usedHand);
    }
}
