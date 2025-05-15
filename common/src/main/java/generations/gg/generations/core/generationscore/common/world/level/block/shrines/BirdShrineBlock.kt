package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import com.cobblemon.mod.common.pokemon.Pokemon
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.item.MelodyFluteItem
import generations.gg.generations.core.generationscore.common.world.item.WingItem
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericShrineBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.InteractShrineBlock.Companion.isActive
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.InteractShrineBlock.Companion.toggleActive
import generations.gg.generations.core.generationscore.common.world.level.schedule.ScheduledTask
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import java.util.*

@Suppress("deprecation")
abstract class BirdShrineBlock @SafeVarargs constructor(
    properties: Properties,
    model: ResourceLocation,
    width: Int = 0,
    height: Int = 0,
    length: Int = 0,
    vararg imbuedItems: RegistrySupplier<WingItem>
) : ShrineBlock<GenericShrineBlockEntity>(
        properties,
        GenerationsBlockEntities.GENERIC_SHRINE,
        model,
        width,
        height,
        length
    ) {
    private val allowedImbuedItems: Set<ResourceLocation> =
        imbuedItems.asSequence()
            .filter(Objects::nonNull)
            .map(RegistrySupplier<WingItem>::getKey).map(ResourceKey<WingItem>::location)
            .toSet()

    public override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        handIn: InteractionHand,
        hit: BlockHitResult
    ): ItemInteractionResult {
        if (!level.isClientSide()) {
//            var stack = player.getItemInHand(handIn); TODO: Investigate if this is need still. Suspect stakc parameter is only needed but insure.

            if (MelodyFluteItem.isFlute(stack)) {
                val imbuedStack = MelodyFluteItem.getImbuedItem(stack)
                if (imbuedStack.isEmpty || allowedImbuedItems.stream().noneMatch { a: ResourceLocation? ->
                        imbuedStack.`is` { item: Holder<Item?> ->
                            item.`is`(
                                a
                            )
                        }
                    }) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION
                val pokemonProperties = getProperties(imbuedStack)

                if (!isActive(state) && stack.damageValue >= stack.maxDamage && pokemonProperties != null) {
                    toggleActive(level, pos)
                    level.playSound(null, pos, GenerationsSounds.LUGIA_SHRINE_SONG.get(), SoundSource.BLOCKS)

                    if (!player.isCreative) stack.shrink(1)

                    ScheduledTask.schedule(
                        {
                            toggleActive(level, pos)
                            PokemonUtil.spawn(pokemonProperties, level, pos, state.getValue(FACING).toYRot())
                        }, 100
                    )

                    return ItemInteractionResult.SUCCESS
                }
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION
    }

    fun getProperties(stack: ItemStack): Pokemon? = stack.item.instanceOrNull<WingItem>()?.key?.createPokemon(70)


    override val isActivatable: Boolean
        get() = true
}
