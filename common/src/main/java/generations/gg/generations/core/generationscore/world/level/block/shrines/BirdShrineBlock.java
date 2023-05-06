package generations.gg.generations.core.generationscore.world.level.block.shrines;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.item.MelodyFluteItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.ShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.schedule.ScheduledTask;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class BirdShrineBlock extends ShrineBlock<GenericShrineBlockEntity> {
    private final ResourceLocation pokeEntryId;
    private final RegistrySupplier<Item> imbuedItem;
    @Nullable
    private final RegistrySupplier<Item> galarianImbuedItem;

    public BirdShrineBlock(BlockBehaviour.Properties materialIn, ResourceLocation model, ResourceLocation pokeEntryId, RegistrySupplier<Item> imbuedItem) {
        this(materialIn, model, pokeEntryId, imbuedItem, null);
    }

    public BirdShrineBlock(Properties materialIn, ResourceLocation model, ResourceLocation pokeEntryId, RegistrySupplier<Item> imbuedItem, @Nullable RegistrySupplier<Item> galarianImbuedItem) {
        super(materialIn, GenerationsBlockEntities.GENERIC_SHRINE, model);
        this.pokeEntryId = pokeEntryId;
        this.imbuedItem = imbuedItem;
        this.galarianImbuedItem = galarianImbuedItem;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (!level.isClientSide()) {
            var stack = player.getItemInHand(handIn);

            if (MelodyFluteItem.isFlute(stack)) {
                var imbuedStack = MelodyFluteItem.getImbuedItem(stack);
                if (imbuedStack.isEmpty()) return InteractionResult.PASS;
                var form = getForm(imbuedStack);
                var entity = level.getBlockEntity(hit.getBlockPos());

                if (entity instanceof ShrineBlockEntity shrine && !shrine.isActive() && stack.getDamageValue() >= stack.getMaxDamage() && form != null) {
                    shrine.toggleActive();
                    stack.shrink(1);

                    ScheduledTask.schedule(() -> {
//                        level.addFreshEntity(new PixelmonEntity(level, PixelmonData.of(pokeEntryId, form), entity.getBlockPos())); TODO: Spawn Pokemon
                        shrine.toggleActive();
                    }, 150);
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.PASS;
    }

    public String getForm(ItemStack stack) {
        if (MelodyFluteItem.isItem(imbuedItem, stack)) {
            return "none";
        } else if (MelodyFluteItem.isItem(galarianImbuedItem, stack)) {
            return "galarian";
        } else {
            return null;
        }
    }
}
