package generations.gg.generations.core.generationscore.common.world.entity

import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import net.minecraft.core.NonNullList
import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.ResourceKey
import net.minecraft.world.Containers
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.HasCustomInventoryScreen
import net.minecraft.world.entity.SlotAccess
import net.minecraft.world.entity.monster.piglin.PiglinAi
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.vehicle.ContainerEntity
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ChestMenu
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.storage.loot.LootTable

class GenerationsChestBoatEntity(entityType: EntityType<out GenerationsChestBoatEntity?>, level: Level) :
    GenerationsBoatEntity(entityType, level), HasCustomInventoryScreen, ContainerEntity {
    private var itemStacks: NonNullList<ItemStack>
    private var lootTable: ResourceKey<LootTable>? = null
    private var lootTableSeed: Long = 0

    init {
        this.itemStacks = NonNullList.withSize(27, ItemStack.EMPTY)
    }

    constructor(level: Level, d: Double, e: Double, f: Double) : this(
        GenerationsEntities.CHEST_BOAT_ENTITY.get(),
        level
    ) {
        this.setPos(d, e, f)
        this.xo = d
        this.yo = e
        this.zo = f
    }

    override fun getSinglePassengerXOffset(): Float {
        return 0.15f
    }

    override fun getMaxPassengers(): Int {
        return 1
    }

    override fun addAdditionalSaveData(compoundTag: CompoundTag) {
        super.addAdditionalSaveData(compoundTag)
        this.addChestVehicleSaveData(compoundTag, this.registryAccess())
    }

    override fun readAdditionalSaveData(compoundTag: CompoundTag) {
        super.readAdditionalSaveData(compoundTag)
        this.readChestVehicleSaveData(compoundTag, this.registryAccess())
    }

    public override fun destroy(damageSource: DamageSource) {
        super.destroy(damageSource)
        this.chestVehicleDestroyed(damageSource, this.level(), this)
    }

    override fun remove(removalReason: RemovalReason) {
        if (!level().isClientSide && removalReason.shouldDestroy()) Containers.dropContents(
            this.level(),
            this,
            this
        )

        super.remove(removalReason)
    }

    override fun interact(player: Player, interactionHand: InteractionHand): InteractionResult {
        if (this.canAddPassenger(player) && !player.isSecondaryUseActive) {
            return super.interact(player, interactionHand)
        } else {
            val interactionResult = this.interactWithContainerVehicle(player)
            if (interactionResult.consumesAction()) {
                this.gameEvent(GameEvent.CONTAINER_OPEN, player)
                PiglinAi.angerNearbyPiglins(player, true)
            }

            return interactionResult
        }
    }

    override fun openCustomInventoryScreen(player: Player) {
        player.openMenu(this)
        if (!player.level().isClientSide) {
            this.gameEvent(GameEvent.CONTAINER_OPEN, player)
            PiglinAi.angerNearbyPiglins(player, true)
        }
    }

    override fun clearContent() {
        this.clearChestVehicleContent()
    }

    override fun getContainerSize(): Int {
        return 27
    }

    override fun getItem(i: Int): ItemStack {
        return this.getChestVehicleItem(i)
    }

    override fun removeItem(i: Int, j: Int): ItemStack {
        return this.removeChestVehicleItem(i, j)
    }

    override fun removeItemNoUpdate(i: Int): ItemStack {
        return this.removeChestVehicleItemNoUpdate(i)
    }

    override fun setItem(i: Int, itemStack: ItemStack) {
        this.setChestVehicleItem(i, itemStack)
    }

    override fun getSlot(i: Int): SlotAccess {
        return this.getChestVehicleSlot(i)
    }

    override fun setChanged() {
    }

    override fun stillValid(player: Player): Boolean {
        return this.isChestVehicleStillValid(player)
    }

    override fun createMenu(i: Int, inventory: Inventory, player: Player): AbstractContainerMenu? {
        if (this.lootTable != null && player.isSpectator) {
            return null
        } else {
            this.unpackLootTable(inventory.player)
            return ChestMenu.threeRows(i, inventory, this)
        }
    }

    fun unpackLootTable(player: Player?) {
        this.unpackChestVehicleLootTable(player)
    }

    override fun getLootTable(): ResourceKey<LootTable>? {
        return this.lootTable
    }

    override fun setLootTable(lootTable: ResourceKey<LootTable>?) {
        this.lootTable = lootTable
    }

    override fun getLootTableSeed(): Long {
        return this.lootTableSeed
    }

    override fun setLootTableSeed(l: Long) {
        this.lootTableSeed = l
    }

    override fun getItemStacks(): NonNullList<ItemStack> {
        return this.itemStacks
    }

    override fun clearItemStacks() {
        this.itemStacks = NonNullList.withSize(this.containerSize, ItemStack.EMPTY)
    }

    override fun stopOpen(player: Player) {
        level().gameEvent(GameEvent.CONTAINER_CLOSE, this.position(), GameEvent.Context.of(player))
    }

    override fun getDropItem(): Item {
        return when (this.modBoatType) {
            Type.GHOST -> GenerationsItems.GHOST_CHEST_BOAT_ITEM.get()
            Type.ULTRA_DARK -> GenerationsItems.ULTRA_DARK_CHEST_BOAT_ITEM.get()
            Type.ULTRA_JUNGLE -> GenerationsItems.ULTRA_JUNGLE_CHEST_BOAT_ITEM.get()
        }
    }

    companion object {
        private const val CONTAINER_SIZE = 27
    }
}
