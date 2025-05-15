package generations.gg.generations.core.generationscore.common.world.entity

import generations.gg.generations.core.generationscore.common.world.item.legends.RubyRodItem
import generations.gg.generations.core.generationscore.common.world.item.legends.RubyRodItem.Companion.getFishedShard
import generations.gg.generations.core.generationscore.common.world.loot.GenerationCoreLootTables
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.stats.Stats
import net.minecraft.tags.ItemTags
import net.minecraft.util.Mth
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ExperienceOrb
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.FishingHook
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.storage.loot.LootParams
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.parameters.LootContextParams
import net.minecraft.world.phys.Vec3
import kotlin.math.max
import kotlin.math.sqrt

class TieredFishingHookEntity @JvmOverloads constructor(
    arg: EntityType<TieredFishingHookEntity?>,
    arg2: Level,
    i: Int = 0,
    j: Int = 0,
    tier: Teir = Teir.OLD
) :
    FishingHook(arg, arg2) {
    @JvmField
    val tier: Teir

    init {
        this.noCulling = true
        this.luck = max(0.0, i.toDouble()).toInt()
        this.lureSpeed = max(0.0, j.toDouble()).toInt()
        this.tier = tier
    }

    constructor(
        arg: Player,
        arg2: Level,
        i: Int,
        j: Int,
        tier: Teir
    ) : this(GenerationsEntities.TIERED_FISHING_BOBBER.get(), arg2, i, j, tier) {
        this.owner = arg
        val f = arg.xRot
        val f1 = arg.yRot
        val f2 = Mth.cos(-f1 * (Math.PI.toFloat() / 180) - Math.PI.toFloat())
        val f3 = Mth.sin(-f1 * (Math.PI.toFloat() / 180) - Math.PI.toFloat())
        val f4 = -Mth.cos(-f * (Math.PI.toFloat() / 180))
        val f5 = Mth.sin(-f * (Math.PI.toFloat() / 180))
        val d0 = arg.x - f3.toDouble() * 0.3
        val d1 = arg.eyeY
        val d2 = arg.z - f2.toDouble() * 0.3
        this.moveTo(d0, d1, d2, f1, f)
        var vec3 = Vec3(-f3.toDouble(), Mth.clamp(-(f5 / f4), -5.0f, 5.0f).toDouble(), -f2.toDouble())
        val d3 = vec3.length()
        vec3 = vec3.multiply(
            0.6 / d3 + random.triangle(0.5, 0.0103365),
            0.6 / d3 + random.triangle(0.5, 0.0103365),
            0.6 / d3 + random.triangle(0.5, 0.0103365)
        )
        this.deltaMovement = vec3
        this.yRot = (Mth.atan2(vec3.x, vec3.z) * 57.2957763671875).toFloat()
        this.xRot =
            (Mth.atan2(vec3.y, vec3.horizontalDistance()) * 57.2957763671875).toFloat()
        this.yRotO = this.yRot
        this.xRotO = this.xRot
    }

    override fun retrieve(stack: ItemStack): Int {
        val player = this.playerOwner
        if (!level().isClientSide && player != null && !this.shouldStopFishing(player)) {
            var i = 0

            if (this.hookedIn != null) {
                if (tier == Teir.RUBY) return 0

                this.pullEntity(this.hookedIn)
                CriteriaTriggers.FISHING_ROD_HOOKED.trigger(
                    player as ServerPlayer, stack,
                    this, emptyList()
                )
                level().broadcastEntityEvent(this, 31.toByte())
                i = if (hookedIn is ItemEntity) 3 else 5
            } else if (this.nibble > 0) {
                val lootParams = (LootParams.Builder(level() as ServerLevel)).withParameter(
                    LootContextParams.ORIGIN,
                    this.position()
                ).withParameter(LootContextParams.TOOL, stack).withParameter(
                    LootContextParams.THIS_ENTITY,
                    this
                ).withLuck(
                    luck.toFloat() + player.luck
                ).create(LootContextParamSets.FISHING)

                val list = tier.process(lootParams, stack)

                CriteriaTriggers.FISHING_ROD_HOOKED.trigger(
                    player as ServerPlayer, stack,
                    this, list
                )
                for (itemstack in list) {
                    val itementity = ItemEntity(
                        this.level(),
                        this.x,
                        this.y,
                        this.z, itemstack
                    )
                    val d0 = player.getX() - this.x
                    val d1 = player.getY() - this.y
                    val d2 = player.getZ() - this.z
                    val d3 = 0.1
                    itementity.setDeltaMovement(
                        d0 * 0.1,
                        d1 * 0.1 + sqrt(sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08,
                        d2 * 0.1
                    )
                    level().addFreshEntity(itementity)
                    player.level().addFreshEntity(
                        ExperienceOrb(
                            player.level(), player.getX(), player.getY() + 0.5, player.getZ() + 0.5,
                            random.nextInt(6) + 1
                        )
                    )
                    if (!itemstack.`is`(ItemTags.FISHES)) continue
                    player.awardStat(Stats.FISH_CAUGHT, 1)
                }
                i = 1
            }
            if (this.onGround()) {
                if (tier != Teir.RUBY) i = 2
            }
            this.discard()

            return i
        }

        return 0
    }

    enum class Teir(private val resourceLocation: ResourceKey<LootTable>) {
        OLD(GenerationCoreLootTables.FISHING_OLD),
        GOOD(GenerationCoreLootTables.FISHING_GOOD),
        SUPER(GenerationCoreLootTables.FISHING_SUPER),
        RUBY(GenerationCoreLootTables.FISHING_RUBY);

        fun process(lootParams: LootParams, stack: ItemStack): ObjectArrayList<ItemStack> {
            val level = lootParams.level
            val loottable = level.server.reloadableRegistries().getLootTable(
                resourceLocation
            )

            var list = loottable.getRandomItems(lootParams)

            if (this == RUBY) {
                var tries = 0

                // Get the initial shard counts from the rod
                val currentShards = getFishedShard(stack)

                while (tries < 3) {
                    // Step 1: Sanitize the list using the current shard counts
                    list = RubyRodItem.sanitizeList(list, currentShards!!)

                    // Step 2: If the list is empty after sanitization, retry
                    if (list.isEmpty) {
                        tries++
                        list = loottable.getRandomItems(lootParams)
                        continue
                    }

                    // Step 3: Save the updated shard counts to the rod's NBT
//                    RubyRodItem.saveShardCounts(stack, currentShards) TODO: Recreate if needed

                    // Step 4: Return the sanitized and valid list
                    return list
                }

                // If no valid list is found after 3 tries, return an empty list
                return ObjectArrayList()
            }

            return list
        }
    }
}
