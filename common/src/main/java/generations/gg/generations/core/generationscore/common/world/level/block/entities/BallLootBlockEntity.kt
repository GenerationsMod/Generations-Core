package generations.gg.generations.core.generationscore.common.world.level.block.entities

import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.DynamicOps
import com.mojang.serialization.JsonOps
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.NbtOps
import net.minecraft.nbt.Tag
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import java.time.Instant
import java.util.*
import java.util.function.BiConsumer
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collectors

private fun <A, B> Codec<A>.write(value: A, ops: DynamicOps<B>): B? {
    return this.encodeStart(ops, value).result().orElse(null)
}

private fun <A> Codec<A>.write(value: A): Tag? {
    return write(value, NbtOps.INSTANCE)
}

private fun <A, B> Codec<A>.read(value: B, ops: DynamicOps<B>): A? {
    return this.parse(ops, value).result().orElse(null)
}

class BallLootBlockEntity(pPos: BlockPos, pBlockState: BlockState) :
    ModelProvidingBlockEntity(GenerationsBlockEntities.BALL_LOOT.get(), pPos, pBlockState) {
    //    @Override
    //    public String getVariant() {
    //        return getBlockState().getBlock() instanceof BallLootBlock loot ? loot.getType() : "poke";
    //    }
    var owner: UUID? = null
    private val claims: MutableList<LootClaim> = ArrayList()
    var lootMode: LootMode = LootMode.ONCE_PER_PLAYER
    private var doesCustomDrops = false
    private var _visible = true

    var visible: Boolean
        get() = _visible
        set(value) {
            _visible = value
            sync()
        }

    var customDrops: NonNullList<ItemStack>? = null
        private set

    override fun readNbt(nbt: CompoundTag, provider: HolderLookup.Provider) {
        owner = if (nbt.contains("owner")) nbt.getUUID("owner") else null

        claims.clear()
        if (nbt.contains("claims")) {
            val listTag = nbt.getList("claims", CompoundTag.TAG_COMPOUND.toInt())

            for (i in listTag.indices) {
                val tag = listTag.getCompound(i)
                val uuid = tag.getUUID("uuid")
                val instant = tag.getLongArray("instant")

                claims.add(LootClaim(uuid, Instant.ofEpochSecond(instant[0], instant[1])))
            }
        }

        lootMode = LootMode.valueOf(nbt.getString("lootMode"))
        doesCustomDrops = nbt.getBoolean("doesCustomDrops")
        visible = nbt.getBoolean("visible")

        if (nbt.contains("customDrops")) customDrops =
            nbt.getList("customDrops", CompoundTag.TAG_COMPOUND.toInt()).stream()
                .map { obj: Tag? -> CompoundTag::class.java.cast(obj) }.map { a: CompoundTag? ->
                    ItemStack.CODEC.parse(
                        NbtOps.INSTANCE, a
                    ).mapOrElse(
                        Function.identity()
                    ) { error: DataResult.Error<ItemStack?>? -> ItemStack.EMPTY }
                }.collect(
                    Collectors.toCollection(
                        Supplier { NonNullList.create() })
                )
    }

    override fun writeNbt(nbt: CompoundTag, provider: HolderLookup.Provider) {
        owner.ifNotNull { nbt.putUUID("owner", it) }
        nbt.put(
            "claims", claims.stream().map<CompoundTag> { a: LootClaim ->
                val tag = CompoundTag()
                tag.putUUID("uuid", a.uuid)
                val instant = a.time
                tag.putLongArray("instant", longArrayOf(instant.epochSecond, instant.nano.toLong()))
                tag
            }.collect(
                Supplier<ListTag> { ListTag() },
                BiConsumer<ListTag, CompoundTag> { obj: ListTag, e: CompoundTag -> obj.add(e) },
                BiConsumer<ListTag, ListTag> { obj: ListTag, c: ListTag -> obj.addAll(c) })
        )

        nbt.putString("lootMode", lootMode.name)
        nbt.putBoolean("doesCustomDrops", doesCustomDrops)
        nbt.putBoolean("visible", visible)

        customDrops?.map(ItemStack.CODEC::write)?.requireNoNulls()?.foldRight(ListTag()) { tag, acum -> acum.also { it.add(tag) } }?.run { nbt.put("customDrops", this) }
    }

    fun canClaim(playerUUID: UUID): Boolean {
        if (lootMode == LootMode.UNLIMITED) return true

        val claim = this.getLootClaim(playerUUID)

        if (claim.isEmpty) {
            addClaimer(playerUUID)
            return true
        }
        if (lootMode.isTimeEnabled) if (claim.get().isExpired) {
            claim.get().refresh()
            return true
        }
        return false
    }

    fun getLootClaim(playerUUID: UUID): Optional<LootClaim> {
        return claims.stream().filter { claim: LootClaim -> claim.uuid == playerUUID }.findFirst()
    }

    fun addClaimer(playerUUID: UUID) {
        claims.add(LootClaim(playerUUID, Instant.now().plus(GenerationsCore.CONFIG.lootTime)))
    }


    fun removeClaimer(playerUUID: UUID) {
        claims.removeIf { a: LootClaim -> a.uuid == playerUUID }
    }

    fun shouldBreakBlock(): Boolean {
        return lootMode.isBreakable
    }

    val isCustomDrop: Boolean
        get() = this.doesCustomDrops && this.customDrops != null

    fun setCustomDrops(vararg customDrops: ItemStack?) {
        this.doesCustomDrops = true
        this.customDrops = NonNullList.of(ItemStack.EMPTY, *customDrops)
    }

    fun addCustomDrop(drop: ItemStack) {
        this.doesCustomDrops = true
        if (this.customDrops == null) this.customDrops = NonNullList.create()
        customDrops!!.add(drop)
    }

    enum class LootMode {
        ONCE_PER_PLAYER, TIMED, ONCE, UNLIMITED;

        val isDropOnce: Boolean
            get() = this == ONCE_PER_PLAYER || this == ONCE

        val isTimeEnabled: Boolean
            get() = this == TIMED

        val isBreakable: Boolean
            get() = this == ONCE
    }

    override fun getAngle(): Float {
        if (blockState.hasProperty(HorizontalDirectionalBlock.FACING)) {
            var dir = blockState.getValue(HorizontalDirectionalBlock.FACING)

            when (dir) {
                Direction.EAST -> dir = Direction.WEST
                Direction.WEST -> dir = Direction.EAST
                else -> {}
            }

            return dir.opposite.toYRot()
        }
        return 0f
    }
}

fun <T> T?.ifNotNull(function: (T) -> Unit) = this?.run(function)
