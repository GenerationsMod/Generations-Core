package generations.gg.generations.core.generationscore.common.world.level.block.entities

import com.google.common.annotations.VisibleForTesting
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.ShrineBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.Clearable
import net.minecraft.world.Container
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.JukeboxSong
import net.minecraft.world.item.JukeboxSongPlayer
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.JukeboxBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.Vec3
import net.minecraft.world.ticks.ContainerSingleItem
import java.util.*

class MeloettaMusicBoxBlockEntity(pos: BlockPos, state: BlockState) :
    ShrineBlockEntity(GenerationsBlockEntities.MELOETTA_MUSIC_BOX.get(), pos, state), Clearable, ContainerSingleItem {
    private var item: ItemStack
    val songPlayer: JukeboxSongPlayer

    init {
        this.item = ItemStack.EMPTY
        this.songPlayer = JukeboxSongPlayer({ this.onSongChanged() }, this.blockPos)
    }

    fun onSongChanged() {
        if (theItem.`is`(GenerationsItems.RELIC_SONG.get())) {
            PokemonUtil.spawn(LegendKeys.MELOETTA.createPokemon(70), level, blockPos, angle)
            this.setSongItemWithoutPlaying(ItemStack(GenerationsItems.INERT_RELIC_SONG.get()))
        }

        level!!.updateNeighborsAt(this.blockPos, blockState.block)
        this.setChanged()
    }

    private fun notifyItemChangedInJukebox(hasRecord: Boolean) {
        if (this.level != null && level!!.getBlockState(this.blockPos) === this.blockState) {
            level!!.setBlock(
                this.blockPos,
                blockState.setValue(JukeboxBlock.HAS_RECORD, hasRecord) as BlockState, 2
            )
            level!!.gameEvent(
                GameEvent.BLOCK_CHANGE,
                this.blockPos, GameEvent.Context.of(this.blockState)
            )
        }
    }

    fun popOutTheItem() {
        if (this.level != null && !level!!.isClientSide) {
            val blockPos: BlockPos = this.blockPos
            val itemStack: ItemStack = this.theItem
            if (!itemStack.isEmpty) {
                this.removeTheItem()
                val vec3: Vec3 = Vec3.atLowerCornerWithOffset(blockPos, 0.5, 1.01, 0.5).offsetRandom(
                    level!!.random, 0.7f
                )
                val itemStack2: ItemStack = itemStack.copy()
                val itemEntity: ItemEntity = ItemEntity(
                    this.level!!, vec3.x(), vec3.y(), vec3.z(), itemStack2
                )
                itemEntity.setDefaultPickUpDelay()
                level!!.addFreshEntity(itemEntity)
            }
        }
    }

    val comparatorOutput: Int
        get() = JukeboxSong.fromStack(level!!.registryAccess(), this.item).map(Holder<JukeboxSong>::value).map(JukeboxSong::comparatorOutput).orElse(0) as Int

    public override fun loadAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.loadAdditional(nbt, provider)
        if (nbt.contains("RecordItem", 10)) {
            this.item = ItemStack.parse(provider, nbt.getCompound("RecordItem")).orElse(ItemStack.EMPTY) as ItemStack
        } else {
            this.item = ItemStack.EMPTY
        }

        if (nbt.contains("ticks_since_song_started", 4)) {
            JukeboxSong.fromStack(provider, this.item).ifPresent({ holder -> songPlayer.setSongWithoutPlaying(holder, nbt.getLong("ticks_since_song_started"))
            })
        }
    }

    override fun saveAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.saveAdditional(nbt, provider)
        if (!this.theItem.isEmpty) {
            nbt.put("RecordItem", this.theItem.save(provider))
        }

        if (songPlayer.song != null) {
            nbt.putLong("ticks_since_song_started", songPlayer.ticksSinceSongStarted)
        }
    }

    override fun getTheItem(): ItemStack {
        return this.item
    }

    override fun splitTheItem(amount: Int): ItemStack {
        val itemStack: ItemStack = this.item
        this.theItem = ItemStack.EMPTY
        return itemStack
    }

    override fun setTheItem(item: ItemStack) {
        this.item = item
        val bl: Boolean = !this.item.isEmpty
        val optional: Optional<Holder<JukeboxSong>> = JukeboxSong.fromStack(
            level!!.registryAccess(), this.item
        )
        this.notifyItemChangedInJukebox(bl)
        if (bl && optional.isPresent) {
            songPlayer.play(this.level, optional.get())
        } else {
            songPlayer.stop(this.level, this.blockState)
        }
    }

    override fun getMaxStackSize(): Int {
        return 1
    }

    override fun stillValid(player: Player): Boolean {
        return true
    }

    val containerBlockEntity: BlockEntity
        get() {
            return this
        }

    override fun canPlaceItem(slot: Int, stack: ItemStack): Boolean {
        return stack.has(DataComponents.JUKEBOX_PLAYABLE) && getItem(slot).isEmpty
    }

    override fun canTakeItem(target: Container, slot: Int, stack: ItemStack): Boolean {
        return target.hasAnyMatching({ obj: ItemStack -> obj.isEmpty })
    }

    @VisibleForTesting
    fun setSongItemWithoutPlaying(stack: ItemStack) {
        this.item = stack
        JukeboxSong.fromStack(level!!.registryAccess(), stack).ifPresent({ holder: Holder<JukeboxSong?>? ->
            songPlayer.setSongWithoutPlaying(holder, 0L)
        })
        level!!.updateNeighborsAt(this.blockPos, blockState.block)
        this.setChanged()
    }

    @VisibleForTesting
    fun tryForcePlaySong() {
        JukeboxSong.fromStack(level!!.registryAccess(), this.theItem).ifPresent { holder -> songPlayer.play(this.level!!, holder) }
    }

    companion object {
        private const val SONG_END_PADDING: Int = 20
        @JvmStatic
        fun tick(level: Level, pos: BlockPos, state: BlockState, jukebox: MeloettaMusicBoxBlockEntity) {
            jukebox.songPlayer.tick(level, state)
        }
    }
}
