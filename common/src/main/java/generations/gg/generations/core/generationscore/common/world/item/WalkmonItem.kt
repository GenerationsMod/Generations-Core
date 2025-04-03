package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.CobblemonNetwork.sendPacketToPlayer
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.architectury.registry.menu.MenuRegistry
import generations.gg.generations.core.generationscore.common.network.GenerationsNetwork.sendPacketToPlayer
import generations.gg.generations.core.generationscore.common.network.packets.S2CPlaySoundPacket
import generations.gg.generations.core.generationscore.common.util.DataKeys
import generations.gg.generations.core.generationscore.common.util.TEXT_CODEC
import generations.gg.generations.core.generationscore.common.util.extensions.get
import generations.gg.generations.core.generationscore.common.util.extensions.set
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer.SimpleGenericContainer
import generations.gg.generations.core.generationscore.common.world.container.WalkmonContainer
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsItemComponents
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.*
import net.minecraft.world.level.Level

class WalkmonItem(properties: Properties, private val row: Int, type: String) : Item(properties) {
    private val defaultTranslation: String = "container.$type"

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND) {
            var holder = load(player.getItemInHand(usedHand))

            if(player.isShiftKeyDown) holder.openScreen(player as ServerPlayer, row)
            else {
                holder.toggle()
                holder.save(player.getItemInHand(usedHand))
            }
        }
        return super.use(level, player, usedHand)
    }

    override fun inventoryTick(stack: ItemStack, level: Level, entity: Entity, slotId: Int, isSelected: Boolean) {
        if (!level.isClientSide && entity is ServerPlayer) {
            load(stack).tick(this, entity)
        }
    }

    private fun startPlayingSong(record: JukeboxPlayable, player: ServerPlayer) {
        sendPacketToPlayer(player, S2CPlaySoundPacket(record.song.unwrap(player.serverLevel().registryAccess()).get().value()));
    }

    private fun stopPlayingSong(sound: SoundEvent, player: ServerPlayer) {
        sendPacketToPlayer(player, S2CPlaySoundPacket(sound))
    }

    fun load(stack: ItemStack): DiscHolder {
        return stack.get(GenerationsItemComponents.DISC_HOLDER) ?: DiscHolder(row, Component.translatable(defaultTranslation))
    }

    class DiscHolder(
        private var currentSlot: Int = 0,
        private var timeUntilNextSong: Int = 0,
        private var playing: Boolean = false,
        private var discs: SimpleGenericContainer,
        private var title: MutableComponent
    ) : MenuProvider {
        companion object {
            val CODEC: Codec<DiscHolder> = RecordCodecBuilder.create {
                it.group(
                    Codec.INT.fieldOf(DataKeys.CURRENT_SLOT).forGetter { it.currentSlot },
                    Codec.INT.fieldOf(DataKeys.TIME_UNTIL_NEXT_SONG).forGetter { it.timeUntilNextSong},
                    Codec.BOOL.fieldOf(DataKeys.PLAYING).forGetter { it.playing },
                    SimpleGenericContainer.CODEC.fieldOf(DataKeys.DISCS).forGetter { it.discs },
                    TEXT_CODEC.optionalFieldOf("title", Component.empty()).forGetter { it.title }
                ).apply(it, ::DiscHolder)
            }
        }

        constructor(rows: Int, title: MutableComponent) : this(discs = SimpleGenericContainer(9, rows), title = title)


        override fun getDisplayName(): Component = title

        override fun createMenu(i: Int, arg: Inventory, arg2: Player): AbstractContainerMenu =
            WalkmonContainer(i, arg, discs, arg2.inventory.selected)

        fun openScreen(player: ServerPlayer, row: Int) {
            MenuRegistry.openExtendedMenu(player, this) {
                it.writeVarInt(9).writeVarInt(row).writeVarInt(player.inventory.selected)
            }
        }

        val disc: JukeboxPlayable?
            get() = discs.getItem(currentSlot).get(DataComponents.JUKEBOX_PLAYABLE)

        private fun createTag(): CompoundTag {
            val holderTag = CompoundTag()

            holderTag.putInt(DataKeys.CURRENT_SLOT, currentSlot)
            holderTag.putInt(DataKeys.TIME_UNTIL_NEXT_SONG, timeUntilNextSong)
            holderTag.putBoolean(DataKeys.PLAYING, playing)
            holderTag.put(DataKeys.DISCS, discs.createTag())
            return holderTag
        }

        fun tick(walkmon: WalkmonItem, player: ServerPlayer) {
            if (playing) {
                if (timeUntilNextSong > 0) {
                    timeUntilNextSong -= 1
                } else {
                    next()?.run {
                        val song = this.song.unwrap(player.serverLevel().registryAccess()).orElseThrow().value()

                        timeUntilNextSong = song.lengthInTicks()
                        val sound = song.soundEvent.value();

                        disc?.run { walkmon.stopPlayingSong(sound, player) }
                        walkmon.startPlayingSong(this, player)
                    } ?: {
                        playing = false
                        currentSlot = 0
                        timeUntilNextSong = 0
                    }
                }
            } else {
                timeUntilNextSong = 0
            }
        }

        private fun next(): JukeboxPlayable? {
            val size = discs.containerSize

            var index = currentSlot

            while (index < size) {
                index++

                val stack = discs.getItem(index)
                if(stack.isEmpty) continue

                val playable = stack.get(DataComponents.JUKEBOX_PLAYABLE)
                if(playable != null) {
                    currentSlot = index
                    return playable
                }
            }

            return null
        }

        fun toggle() {
            playing = !playing
        }


        fun save(stack: ItemStack, container: SimpleGenericContainer? = null) {
            val discs = this.discs;

            if(container != null) this.discs = container
            stack.set(GenerationsItemComponents.DISC_HOLDER, this)
            this.discs = discs
        }
    }
}
