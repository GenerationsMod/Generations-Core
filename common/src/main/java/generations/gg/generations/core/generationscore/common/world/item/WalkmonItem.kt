package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.CobblemonNetwork.sendPacketToPlayer
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import earth.terrarium.common_storage_lib.context.impl.PlayerContext
import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import earth.terrarium.common_storage_lib.item.util.ItemStorageData
import generations.gg.generations.core.generationscore.common.GenerationsStorage
import generations.gg.generations.core.generationscore.common.network.packets.S2CPlaySoundPacket
import generations.gg.generations.core.generationscore.common.util.DataKeys
import generations.gg.generations.core.generationscore.common.util.TEXT_CODEC
import generations.gg.generations.core.generationscore.common.util.TEXT_STREAM_CODEC
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import net.minecraft.core.component.DataComponents
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.JukeboxPlayable
import net.minecraft.world.level.Level

class WalkmonItem(properties: Properties, private val row: Int, type: String) : Item(properties.component(GenerationsDataComponents.WALKMON_DATA.value(), WalkmonData(0, 0, false, Component.literal("Walkmon")))) {
    private val defaultTranslation: String = "container.$type"

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND) {
            val stack = player.getItemInHand(usedHand);

            val walkmon = stack.walkmonData

            if(walkmon != null) {

                if (player.isShiftKeyDown) GenericContainer.openScreen(SimpleItemStorage(PlayerContext.ofHand(player, usedHand), GenerationsStorage.ITEM_CONTENTS.componentType(), 9 * row), 9, row, walkmon.title.takeUnless { it == Component.EMPTY } ?: Component.translatable(defaultTranslation), player, player.inventory.selected)
                else {
                    walkmon.toggle()
                }
            }
        }
        return super.use(level, player, usedHand)
    }

    override fun inventoryTick(stack: ItemStack, level: Level, entity: Entity, slotId: Int, isSelected: Boolean) {
        if (!level.isClientSide && entity is ServerPlayer) {
            stack.get(GenerationsDataComponents.WALKMON_DATA.value())?.tick(stack, entity)
        }
    }

    class WalkmonData(
        private var currentSlot: Int = 0,
        private var timeUntilNextSong: Int = 0,
        private var playing: Boolean = false,
        var title: MutableComponent
    ) {
        companion object {
            val CODEC: Codec<WalkmonData> = RecordCodecBuilder.create {
                it.group(
                    Codec.INT.fieldOf(DataKeys.CURRENT_SLOT).forGetter { it.currentSlot },
                    Codec.INT.fieldOf(DataKeys.TIME_UNTIL_NEXT_SONG).forGetter { it.timeUntilNextSong},
                    Codec.BOOL.fieldOf(DataKeys.PLAYING).forGetter { it.playing },
                    TEXT_CODEC.optionalFieldOf("title", Component.empty()).forGetter { it.title }
                ).apply(it, ::WalkmonData)
            }
            val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, WalkmonData> = StreamCodec.composite(
                ByteBufCodecs.VAR_INT, WalkmonData::currentSlot,
                ByteBufCodecs.VAR_INT, WalkmonData::timeUntilNextSong,
                ByteBufCodecs.BOOL, WalkmonData::playing,
                TEXT_STREAM_CODEC, WalkmonData::title,
                ::WalkmonData
            )
        }


        val ItemStack.disc: JukeboxPlayable?
            get() = GenerationsStorage.ITEM_CONTENTS.get(this).stacks.get(currentSlot).resource.get(DataComponents.JUKEBOX_PLAYABLE)

        fun tick(stack: ItemStack, player: ServerPlayer) {
            if (playing) {
                if (timeUntilNextSong > 0) {
                    timeUntilNextSong -= 1
                } else {
                    GenerationsStorage.ITEM_CONTENTS.get(stack).next()?.run {
                        val song = this.song.unwrap(player.serverLevel().registryAccess()).orElseThrow().value()

                        timeUntilNextSong = song.lengthInTicks()
                        val sound = song.soundEvent.value();

                        stack.disc?.run { stopPlayingSong(sound, player) }
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

        private fun startPlayingSong(record: JukeboxPlayable, player: ServerPlayer) {
            sendPacketToPlayer(player, S2CPlaySoundPacket(record.song.unwrap(player.serverLevel().registryAccess()).get().value()));
        }

        private fun stopPlayingSong(sound: SoundEvent, player: ServerPlayer) {
            sendPacketToPlayer(player, S2CPlaySoundPacket(sound))
        }

        private fun ItemStorageData.next(): JukeboxPlayable? {
            val size = stacks.size
            var index = currentSlot + 1

            while (index < size) {
                val stack = stacks[index]
                if (stack.isEmpty) {
                    index++
                    continue
                }

                val playable = stack.resource.get(DataComponents.JUKEBOX_PLAYABLE)
                if (playable != null) {
                    currentSlot = index
                    return playable
                }

                index++
            }

            return null
        }

        fun toggle() {
            playing = !playing
        }
    }
}

private val ItemStack.walkmonData: WalkmonItem.WalkmonData?
    get() = this.get(GenerationsDataComponents.WALKMON_DATA.value())
