package generations.gg.generations.core.generationscore.common.world.item

import dev.architectury.registry.menu.MenuRegistry
import generations.gg.generations.core.generationscore.common.network.GenerationsNetwork.sendPacketToPlayer
import generations.gg.generations.core.generationscore.common.network.packets.S2CPlaySoundPacket
import generations.gg.generations.core.generationscore.common.util.DataKeys
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer.SimpleGenericContainer
import generations.gg.generations.core.generationscore.common.world.container.WalkmonContainer
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.RecordItem
import net.minecraft.world.level.Level
import java.util.*

class WalkmonItem(properties: Properties, private val row: Int, type: String) : Item(properties) {
    private val defaultTranslation: String

    init {
        defaultTranslation = "container.$type"
    }

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND) {
            var holder = load(player.getItemInHand(usedHand))

            if(player.isShiftKeyDown) holder.openScreen(player as ServerPlayer)
            else {
                holder.toggle()
                holder.save(player.getItemInHand(usedHand))
            }
        }
        return super.use(level, player, usedHand)
    }

    override fun inventoryTick(stack: ItemStack, level: Level, entity: Entity, slotId: Int, isSelected: Boolean) {
        if (!level.isClientSide && entity is ServerPlayer) {
            var discHolder = load(stack);
            discHolder.tick(entity);
            discHolder.save(stack)
        }
    }

    private fun startPlayingSong(record: RecordItem, player: ServerPlayer) {
        sendPacketToPlayer(player, S2CPlaySoundPacket(record))
    }

    private fun stopPlayingSong(sound: SoundEvent, player: ServerPlayer) {
        sendPacketToPlayer(player, S2CPlaySoundPacket(sound))
    }

    fun load(stack: ItemStack, discs: SimpleGenericContainer? = null): DiscHolder {
        val holderTag = stack.getTagElement(DataKeys.DISC_HOLDER)

        return if (holderTag == null) DiscHolder()
        else {
            val currentSlot = holderTag.getInt(DataKeys.CURRENT_SLOT)
            val timeUntilNextSong = holderTag.getInt(DataKeys.TIME_UNTIL_NEXT_SONG)
            val playing = holderTag.getBoolean(DataKeys.PLAYING)
            var actualDiscs = discs ?: SimpleGenericContainer(9, row).also { it.fromTag(holderTag.getList(DataKeys.DISCS, Tag.TAG_COMPOUND.toInt())) }
            DiscHolder(currentSlot, timeUntilNextSong, playing, actualDiscs)
        }
    }

    //TODO: ItemStackComponent in 1.21
    inner class DiscHolder(
        private var currentSlot: Int = 0,
        private var timeUntilNextSong: Int = 0,
        private var playing: Boolean = false,
        private val discs: SimpleGenericContainer = SimpleGenericContainer(9, row),
    ) : MenuProvider {


        override fun getDisplayName(): Component = Component.translatable(defaultTranslation)

        override fun createMenu(i: Int, arg: Inventory, arg2: Player): AbstractContainerMenu =
            WalkmonContainer(i, arg, discs, arg2.inventory.selected)

        fun openScreen(player: ServerPlayer) {
            MenuRegistry.openExtendedMenu(player, this) {
                it.writeVarInt(9).writeVarInt(row).writeVarInt(player.inventory.selected)
            }


        }

        val disc: RecordItem?
            get() = discs.getItem(currentSlot).item as? RecordItem

        private fun createTag(): CompoundTag {
            val holderTag = CompoundTag();

            holderTag.putInt(DataKeys.CURRENT_SLOT, currentSlot)
            holderTag.putInt(DataKeys.TIME_UNTIL_NEXT_SONG, timeUntilNextSong)
            holderTag.putBoolean(DataKeys.PLAYING, playing)
            holderTag.put(DataKeys.DISCS, discs.createTag())
            return holderTag
        }

        fun save(stack: ItemStack) {
            stack.getOrCreateTag().put(DataKeys.DISC_HOLDER, createTag())
        }

        fun tick(player: ServerPlayer) {
            if (playing) {
                if (timeUntilNextSong > 0) {
                    timeUntilNextSong -= 1;
                } else {
                    next()?.run {
                        timeUntilNextSong = lengthInTicks
                        val sound = this.sound

                        disc?.run { stopPlayingSong(sound, player) }
                        startPlayingSong(this, player)
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

        private fun next(): RecordItem? {
            val size = discs.containerSize

            var index = currentSlot

            while (index < size) {
                index++;

                val stack = discs.getItem(index)
                if(stack.isEmpty) continue
                val item = stack.item;
                if(item is RecordItem) {
                    currentSlot = index;
                    return item
                }
            }

            return null
        }

        fun toggle() {
            playing = !playing
        }
    }
}
