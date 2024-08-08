package generations.gg.generations.core.generationscore.common.world.item;

import com.cobblemon.mod.common.pokemon.Pokemon;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.world.container.CalyrexSteedContainer;
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.world.container.CalyrexSteedContainer;
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.container.CalyrexSteedContainer;
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;
import java.util.stream.IntStream;

public class CalyrexSteedItem extends Item {
    private final SpeciesKey speices;
    private final Supplier<Item> unenchanted;
    private final String defaultTranslation;

    public CalyrexSteedItem(String name, Properties arg, SpeciesKey speices, Supplier<Item> unenchanted) {
        super(arg);
        this.speices = speices;
        this.unenchanted = unenchanted;
        this.defaultTranslation = "container." + name + "_carrot";
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return getCarrots(stack).isFull();
    }

    @Override
    public boolean isEdible() {
        return true;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND) {
            var carrots = getCarrots(player.getItemInHand(usedHand));
            if(!carrots.isFull()) {
                getCarrots(player.getItemInHand(usedHand)).openScreen(player, player.getInventory().selected);
                return InteractionResultHolder.success(player.getItemInHand(usedHand));
            }
            return super.use(level, player, usedHand);
        }

        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if(!level.isClientSide()) {
            PokemonUtil.spawn(speices.createPokemon(70), level, livingEntity.position(), livingEntity.getYRot());
        }

        return unenchanted.get().getDefaultInstance();
    }

    public CarrotHolder getCarrots(ItemStack stack) {
        var container = new CarrotHolder();
        container.fromTag(stack.getOrCreateTag().getList("inventory", Tag.TAG_COMPOUND));
        return container;
    }

    public void save(GenericContainer.SimpleGenericContainer container, ItemStack stack) {
        stack.getOrCreateTag().put("inventory", container.createTag());
    }

    public class CarrotHolder extends GenericContainer.SimpleGenericContainer {
        public CarrotHolder() {
            super(9, 2);
        }

        @Override
        public @NotNull Component getDisplayName() {
            return Component.translatable(CalyrexSteedItem.this.defaultTranslation);
        }

        @Override
        public @Nullable AbstractContainerMenu createMenu(int i, @NotNull Inventory arg, @NotNull Player arg2) {
            return new CalyrexSteedContainer(i, arg, this, arg2.getInventory().selected);
        }

        public boolean isFull() {
            return IntStream.range(0, getContainerSize()).mapToObj(this::getItem).allMatch(stack -> stack.is(Items.CARROT) && stack.getCount() >= stack.getMaxStackSize());
        }
    }
}
