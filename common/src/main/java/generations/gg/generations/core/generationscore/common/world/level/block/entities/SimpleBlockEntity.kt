package generations.gg.generations.core.generationscore.common.world.level.block.entities

import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.AngleProvider
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

abstract class SimpleBlockEntity(pType: BlockEntityType<*>, pPos: BlockPos, pBlockState: BlockState) :
    BlockEntity(pType, pPos, pBlockState), AngleProvider {
    protected open fun readNbt(nbt: CompoundTag, provider: HolderLookup.Provider) {}
    protected open fun writeNbt(nbt: CompoundTag, provider: HolderLookup.Provider) {}

    fun sync() {
        setChanged()
        level?.sendBlockUpdated(blockPos, blockState, blockState, 2)
    }

    override fun loadAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.loadAdditional(nbt, provider)
        readNbt(nbt, provider)
    }

    override fun saveAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.saveAdditional(nbt, provider)
        writeNbt(nbt, provider)
    }

    override fun getUpdateTag(registries: HolderLookup.Provider): CompoundTag {
        val tag = CompoundTag()
        writeNbt(tag, registries)
        return tag
    }

    override fun getUpdatePacket(): Packet<ClientGamePacketListener>? {
        return ClientboundBlockEntityDataPacket.create(this)
    }

    override fun getAngle(): Float {
        if (blockState.hasProperty(HorizontalDirectionalBlock.FACING)) return blockState.getValue(
            HorizontalDirectionalBlock.FACING
        ).toYRot()
        return 0f
    }
}