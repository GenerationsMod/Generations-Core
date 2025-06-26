package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.CobblemonNetwork
import generations.gg.generations.core.generationscore.common.network.packets.S2CPlaySoundPacket
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import generations.gg.generations.core.generationscore.common.world.sound.WalkmonSoundManager
import net.minecraft.core.Holder
import net.minecraft.core.NonNullList
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.item.Item.TooltipContext
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.JukeboxPlayable
import net.minecraft.world.item.JukeboxSong
import net.minecraft.world.item.component.ItemContainerContents
import kotlin.jvm.optionals.getOrNull

//class WalkmonData(
//    var currentSlot: Int = 0,
//    var rows: Int = 1,
//    private var timeUntilNextSong: Int = 0,
//    private var playing: Boolean = false,
//    private var currentSong: Optional<JukeboxPlayable> = Optional.empty(),
//    var title: MutableComponent = Component.literal("Walkmon")
//)

//{
//    companion object {
//        val CODEC: Codec<WalkmonData> = RecordCodecBuilder.create {
//            it.group(
//                Codec.INT.fieldOf("currentSlot").forGetter { it.currentSlot },
//                Codec.INT.fieldOf("rows").forGetter { it.rows },
//                Codec.INT.fieldOf("timeUntilNextSong").forGetter { it.timeUntilNextSong },
//                Codec.BOOL.fieldOf("playing").forGetter { it.playing },
//                JukeboxPlayable.CODEC.optionalFieldOf("currentSong").forGetter(WalkmonData::currentSong),
//                TEXT_CODEC.optionalFieldOf("title", Component.empty()).forGetter { it.title }
//            ).apply(it, ::WalkmonData)
//        }
//
//        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, WalkmonData> = StreamCodec.composite(
//            ByteBufCodecs.VAR_INT, WalkmonData::currentSlot,
//            ByteBufCodecs.VAR_INT, WalkmonData::rows,
//            ByteBufCodecs.VAR_INT, WalkmonData::timeUntilNextSong,
//            ByteBufCodecs.BOOL, WalkmonData::playing,
//            JukeboxPlayable.STREAM_CODEC.optional(), WalkmonData::currentSong,
//            TEXT_STREAM_CODEC, WalkmonData::title,
//            ::WalkmonData
//        )
//    }

private var ItemStack.currentSong: JukeboxSong?
    get() = get(GenerationsDataComponents.CURRENT_SONG.value())
    set(value) { set(GenerationsDataComponents.CURRENT_SONG.value(), value) }

private var ItemStack.currentSlot: Int
    get() = getOrDefault(GenerationsDataComponents.CURRENT_SLOT.value(), 0)
    set(value) { set(GenerationsDataComponents.CURRENT_SLOT.value(), value) }

var ItemStack.container: ItemContainerContents
    get() = getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY)
    set(value) { set(DataComponents.CONTAINER, value) }

var ItemStack.rows: Int
    get() {
        return getOrDefault(GenerationsDataComponents.ROWS.value(), 1)
    }
    set(value) { set(GenerationsDataComponents.ROWS.value(), value) }

private var ItemStack.timeUntilNextSong: Int
    get() = getOrDefault(GenerationsDataComponents.TIME_UNTIL_NEXT_SONG.value(), 0)
    set(value) {
        set(GenerationsDataComponents.TIME_UNTIL_NEXT_SONG.value(), value)
    }

private var ItemStack.playing: Boolean
    get() = getOrDefault(GenerationsDataComponents.PLAYING.value(), false)
    set(value) {
        set(GenerationsDataComponents.PLAYING.value(), value)
    }

val ItemStack.title: Component
    get() = displayName

    fun ItemStack.next(): JukeboxPlayable? {
        val list = NonNullList.withSize(9 * rows, ItemStack.EMPTY)
        container.copyInto(list)

        println("[DiscContainer.next] === ENTER ===")
        println("[DiscContainer.next] currentSlot = $currentSlot, containerSize = $list.size")

        val size = list.size
        if (list.isEmpty()) {
            println("[DiscContainer.next] Container is empty. Returning null.")
            return null
        }

        var index = (currentSlot + 1) % size
        var attempts = 0

        while (attempts < size) {
            val stack = list[index]
            println("[DiscContainer.next] Attempt $attempts — index=$index, stack=$stack")

            if (!stack.isEmpty) {
                val playable = stack.get(DataComponents.JUKEBOX_PLAYABLE)
                if (playable != null) {
                    println("[DiscContainer.next] Found playable at index $index: $playable")
                    currentSlot = index
                    return playable
                } else {
                    println("[DiscContainer.next] Stack at $index is not playable.")
                }
            } else {
                println("[DiscContainer.next] Stack at $index is empty.")
            }

            index = (index + 1) % size
            attempts++
        }

        println("[DiscContainer.next] No playable found after scanning all slots.")
        return null
    }

    fun ItemStack.stop(player: ServerPlayer) {
        playing = false
        timeUntilNextSong = 0
        currentSong = null
        stopPlayingSong(player)
    }

    fun ItemStack.toggle(player: ServerPlayer) {
        if (playing) {
            stop(player)
            stopPlaybackClient(player)
        } else {
            playing = true
        }
    }

    fun startPlayingSong(record: Holder<SoundEvent>, lengthInTicks: Int, player: ServerPlayer, trackedId: Int) {
        S2CPlaySoundPacket(record, lengthInTicks, true, trackedId).also {
            CobblemonNetwork.sendPacketToPlayer(player, it)
        }
    }

    fun stopPlayingSong(player: ServerPlayer) {
        S2CPlaySoundPacket().sendToPlayer(player)
    }

    fun stopPlaybackClient(player: ServerPlayer) {
        if (player.level().isClientSide) {
            WalkmonSoundManager.stopSound(player.uuid)
        }
    }

    fun JukeboxSong?.getTrackId(): Int {
        return this?.hashCode() ?: return 0
    }

    fun ItemStack.populate(list: MutableList<Component>, context: TooltipContext) {
        println("[populate] === ENTER ===")
        println("[populate] Playback status: playing=$playing")

        if (playing) {
            val song = currentSong
            println("[populate] Attempting to resolve current song: ${song}")
            println("[populate] Resolved song: $song")

            if (song == null) {
                println("[populate] Song resolution failed. No tooltip added.")
                return
            }

            val remainingSeconds = timeUntilNextSong / 20f
            println("[populate] Song: ${song.description.string}")
            println("[populate] Time left: $remainingSeconds / ${song.lengthInSeconds}")

            list.add(Component.literal("Currently playing: ${song.description.string}"))

            list.add(Component.literal("${(song.lengthInSeconds - remainingSeconds).timeCode()} / ${song.lengthInSeconds.timeCode()}"))
        } else {
            println("[populate] No song playing. Showing idle message.")
            list.add(Component.literal("Nothing is currently playing."))
        }

        println("[populate] === EXIT ===")
    }
//}

fun Float.timeCode(): String {
    val whole = this.toInt()
    val minutes = whole/60
    val seconds = whole % 60
    return "${minutes}:${if(seconds < 10) "0" else ""}$seconds"
}

fun ItemStack.tick(player: ServerPlayer) {
    if (!playing) {
        stopPlaybackClient(player)
        return
    }

    if (timeUntilNextSong > 0) {
        timeUntilNextSong--
        return
    }

    val next = next()
    if (next == null) {
        stop(player)
        stopPlaybackClient(player)
        return
    }

    val songHolder = next.song().unwrap(player.serverLevel().registryAccess()).getOrNull()
    val song = songHolder?.value()

    if (song == null) {
        stop(player)
        stopPlaybackClient(player)
        return
    }

    timeUntilNextSong = song.lengthInTicks()
    currentSong = song
    startPlayingSong(song.soundEvent, song.lengthInTicks(), player, currentSong.getTrackId())
}
