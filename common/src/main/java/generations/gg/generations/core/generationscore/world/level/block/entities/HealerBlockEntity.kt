package generations.gg.generations.core.generationscore.world.level.block.entities

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.CobblemonSounds
import com.cobblemon.mod.common.api.pokeball.PokeBalls
import com.cobblemon.mod.common.api.text.green
import com.cobblemon.mod.common.block.HealingMachineBlock
import com.cobblemon.mod.common.pokeball.PokeBall
import com.cobblemon.mod.common.util.*
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.state.BlockState
import java.util.*
import kotlin.math.floor

class HealerBlockEntity(pos: BlockPos, state: BlockState) : DyedVariantBlockEntity<HealerBlockEntity>(GenerationsBlockEntities.HEALER.get(), pos, state) {
    var currentUser: UUID? = null
        private set
    var pokeBalls: MutableList<PokeBall> = mutableListOf<PokeBall>()
        private set
    var healTimeLeft: Int = 0
    var healingCharge: Float = 0.0F
    val isInUse: Boolean
        get() = currentUser != null
    var infinite: Boolean = false

    var currentSignal = 0
        private set

    var maxCharge: Float = 6F

    init {
        maxCharge = (Cobblemon.config.maxHealerCharge).coerceAtLeast(6F)
        this.updateRedstoneSignal()
        this.updateBlockChargeLevel()
    }

    fun setUser(user: UUID) {
        this.clearData()

        val player = user.getPlayer() ?: return
        val party = player.party()

        pokeBalls.clear()
        pokeBalls.addAll(party.map { it.caughtBall })
        this.currentUser = user
        this.healTimeLeft = 60

        sync()
    }

    fun canHeal(player: ServerPlayer): Boolean {
        if (Cobblemon.config.infiniteHealerCharge || this.infinite) {
            return true
        }
        val neededHealthPercent = player.party().getHealingRemainderPercent()
        return this.healingCharge >= neededHealthPercent
    }

    fun activate(player: ServerPlayer) {
        if (!Cobblemon.config.infiniteHealerCharge && this.healingCharge != maxCharge) {
            val neededHealthPercent = player.party().getHealingRemainderPercent()
            this.healingCharge = (healingCharge - neededHealthPercent).coerceIn(0F..maxCharge)
            this.updateRedstoneSignal()
        }
        this.setUser(player.uuid)
        alreadyHealing.add(player.uuid)
        updateBlockChargeLevel(HealingMachineBlock.MAX_CHARGE_LEVEL + 1)
        if (level != null && !level!!.isClientSide) level!!.playSoundServer(position = blockPos.toVec3d(), sound = CobblemonSounds.HEALING_MACHINE_ACTIVE, volume = 1F, pitch = 1F)
    }

    fun completeHealing() {
        val player = this.currentUser?.getPlayer() ?: return clearData()
        val party = player.party()

        party.heal()
        player.sendSystemMessage(lang("healingmachine.healed").green())
        updateBlockChargeLevel()
        clearData()
    }

    internal fun clearData() {
        this.currentUser?.let(alreadyHealing::remove)
        this.currentUser = null
        this.pokeBalls.clear()
        this.healTimeLeft = 0
        sync()
    }

    override fun readNbt(compoundTag: CompoundTag) {
        super.readNbt(compoundTag)

        this.pokeBalls.clear()

        if (compoundTag.hasUUID(DataKeys.HEALER_MACHINE_USER)) {
            this.currentUser = compoundTag.getUUID(DataKeys.HEALER_MACHINE_USER)
        }
        if (compoundTag.contains(DataKeys.HEALER_MACHINE_POKEBALLS)) {
            val pokeBallsTag = compoundTag.getCompound(DataKeys.HEALER_MACHINE_POKEBALLS)
            for (key in pokeBallsTag.allKeys) {
                val pokeBallId = pokeBallsTag.getString(key)
                if (pokeBallId.isEmpty()) {
                    continue
                }

                val pokeBall = PokeBalls.getPokeBall(ResourceLocation(pokeBallId))
                if (pokeBall != null) {
                    this.pokeBalls.add(pokeBall)
                }
            }
        }
        if (compoundTag.contains(DataKeys.HEALER_MACHINE_TIME_LEFT)) {
            this.healTimeLeft = compoundTag.getInt(DataKeys.HEALER_MACHINE_TIME_LEFT)
        }
        if (compoundTag.contains(DataKeys.HEALER_MACHINE_CHARGE)) {
            this.healingCharge = compoundTag.getFloat(DataKeys.HEALER_MACHINE_CHARGE).coerceIn(0F..maxCharge)
        }
        if (compoundTag.contains(DataKeys.HEALER_MACHINE_INFINITE)) {
            this.infinite = compoundTag.getBoolean(DataKeys.HEALER_MACHINE_INFINITE)
        }
    }

    override fun writeNbt(compoundTag: CompoundTag) {
        super.writeNbt(compoundTag)

        if (this.currentUser != null) {
            compoundTag.putUUID(DataKeys.HEALER_MACHINE_USER, this.currentUser!!)
        } else {
            compoundTag.remove(DataKeys.HEALER_MACHINE_USER)
        }

        if (pokeBalls.isNotEmpty()) {
            val pokeBallsTag = CompoundTag()
            var ballIndex = 1

            for (pokeBall in this.pokeBalls) {
                pokeBallsTag.putString("Pokeball$ballIndex", pokeBall.name.toString())
                ballIndex++
            }
            compoundTag.put(DataKeys.HEALER_MACHINE_POKEBALLS, pokeBallsTag)
        } else {
            compoundTag.remove(DataKeys.HEALER_MACHINE_POKEBALLS)
        }

        compoundTag.putInt(DataKeys.HEALER_MACHINE_TIME_LEFT, this.healTimeLeft)
        compoundTag.putFloat(DataKeys.HEALER_MACHINE_CHARGE, this.healingCharge)
        compoundTag.putBoolean(DataKeys.HEALER_MACHINE_INFINITE, this.infinite)
    }

    private fun updateRedstoneSignal() {
        if (Cobblemon.config.infiniteHealerCharge || this.infinite) {
            this.currentSignal = MAX_REDSTONE_SIGNAL
        }
        val remainder = ((this.healingCharge / maxCharge) * 100).toInt() / 10
        this.currentSignal = remainder.coerceAtMost(MAX_REDSTONE_SIGNAL)
    }

    private fun updateBlockChargeLevel(charge: Int? = null) {
        if (level != null && !level!!.isClientSide) {
            val chargeLevel = (charge ?:
            if (Cobblemon.config.infiniteHealerCharge || this.infinite) HealingMachineBlock.MAX_CHARGE_LEVEL
            else floor((healingCharge / maxCharge) * HealingMachineBlock.MAX_CHARGE_LEVEL).toInt()
                    ).coerceIn(0..HealingMachineBlock.MAX_CHARGE_LEVEL + 1)

            val state = level!!.getBlockState(blockPos)
            if (state != null && state.block is HealingMachineBlock) {
                val currentCharge = state.getValue(HealingMachineBlock.CHARGE_LEVEL).toInt()
                if (chargeLevel != currentCharge) level!!.setBlockAndUpdate(blockPos, state.setValue(HealingMachineBlock.CHARGE_LEVEL, chargeLevel))
            }
        }
    }

    companion object {
        private val alreadyHealing = hashSetOf<UUID>()
        const val MAX_REDSTONE_SIGNAL = 10

        internal val TICKER = BlockEntityTicker<HealerBlockEntity> { world, _, _, blockEntity ->
            if (world.isClientSide) return@BlockEntityTicker

            // Healing progression
            if (blockEntity.isInUse) {
                if (blockEntity.healTimeLeft > 0) {
                    blockEntity.healTimeLeft--
                    blockEntity.sync()
                } else {
                    blockEntity.completeHealing()
                }
            } else {
                // Recharging
                if (blockEntity.healingCharge < blockEntity.maxCharge) {
                    val chargePerTick = (Cobblemon.config.chargeGainedPerTick).coerceAtLeast(0F)
                    blockEntity.healingCharge = (blockEntity.healingCharge + chargePerTick).coerceIn(0F..blockEntity.maxCharge)
                    blockEntity.updateBlockChargeLevel()
                    blockEntity.updateRedstoneSignal()
                    blockEntity.sync()
                }
            }
        }

        fun isUsingHealer(player: Player) = this.alreadyHealing.contains(player.uuid)

    }
}
