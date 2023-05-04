package generations.gg.generations.core.generationscore.world.level.block.shrines;

import com.pokemod.pokemod.registries.types.PixelmonData;
import com.pokemod.pokemod.world.entity.pixelmon.PixelmonEntity;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import com.pokemod.pokemod.world.level.block.entities.generic.GenericShrineBlockEntity;
import com.pokemod.pokemod.world.level.block.entities.ShrineBlockEntity;
import com.pokemod.pokemod.world.item.MelodyFluteItem;
import com.pokemod.pokemod.world.level.schedule.ScheduledTask;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class BirdShrineBlock extends ShrineBlock<GenericShrineBlockEntity> {
    private final ResourceLocation pokeEntryId;
    private final RegistryObject<Item> imbuedItem;
    @Nullable
    private final RegistryObject<Item> galarianImbuedItem;

    public BirdShrineBlock(Properties materialIn, ResourceLocation model, ResourceLocation pokeEntryId, RegistryObject<Item> imbuedItem) {
        this(materialIn, model, pokeEntryId, imbuedItem, null);
    }

    public BirdShrineBlock(Properties materialIn, ResourceLocation model, ResourceLocation pokeEntryId, RegistryObject<Item> imbuedItem, @Nullable RegistryObject<Item> galarianImbuedItem) {
        super(materialIn, PokeModBlockEntities.GENERIC_SHRINE, model);
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
                        level.addFreshEntity(new PixelmonEntity(level, PixelmonData.of(pokeEntryId, form), entity.getBlockPos()));
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
