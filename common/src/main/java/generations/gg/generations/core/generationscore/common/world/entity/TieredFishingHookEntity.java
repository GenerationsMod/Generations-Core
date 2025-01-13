package generations.gg.generations.core.generationscore.common.world.entity;

import generations.gg.generations.core.generationscore.common.world.item.legends.RubyRodItem;
import generations.gg.generations.core.generationscore.common.world.loot.GenerationCoreLootTables;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Map;

public class TieredFishingHookEntity extends FishingHook {
    private final Teir tier;

    public TieredFishingHookEntity(EntityType<TieredFishingHookEntity> arg, Level arg2, int i, int j, Teir tier) {
        super(arg, arg2);
        this.noCulling = true;
        this.luck = Math.max(0, i);
        this.lureSpeed = Math.max(0, j);
        this.tier = tier;
    }

    public TieredFishingHookEntity(EntityType<TieredFishingHookEntity> arg, Level arg2) {
        this(arg, arg2, 0, 0, Teir.OLD);
    }

    public TieredFishingHookEntity(Player arg, Level arg2, int i, int j, Teir tier) {
        this(GenerationsEntities.TIERED_FISHING_BOBBER.get(), arg2, i, j, tier);
        this.setOwner(arg);
        float f = arg.getXRot();
        float f1 = arg.getYRot();
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180));
        double d0 = arg.getX() - (double)f3 * 0.3;
        double d1 = arg.getEyeY();
        double d2 = arg.getZ() - (double)f2 * 0.3;
        this.moveTo(d0, d1, d2, f1, f);
        Vec3 vec3 = new Vec3(-f3, Mth.clamp(-(f5 / f4), -5.0f, 5.0f), -f2);
        double d3 = vec3.length();
        vec3 = vec3.multiply(0.6 / d3 + this.random.triangle(0.5, 0.0103365), 0.6 / d3 + this.random.triangle(0.5, 0.0103365), 0.6 / d3 + this.random.triangle(0.5, 0.0103365));
        this.setDeltaMovement(vec3);
        this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * 57.2957763671875));
        this.setXRot((float)(Mth.atan2(vec3.y, vec3.horizontalDistance()) * 57.2957763671875));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    public int retrieve(@NotNull ItemStack stack) {
        Player player = this.getPlayerOwner();
        if (!this.level().isClientSide && player != null && !this.shouldStopFishing(player)) {
            int i = 0;

            if (this.getHookedIn() != null) {
                if(tier == Teir.RUBY) return 0;

                this.pullEntity(this.getHookedIn());
                CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayer)player, stack, this, Collections.emptyList());
                this.level().broadcastEntityEvent(this, (byte)31);
                i = this.getHookedIn() instanceof ItemEntity ? 3 : 5;
            } else if (this.nibble > -1) {
                LootParams lootParams = (new LootParams.Builder((ServerLevel)this.level())).withParameter(LootContextParams.ORIGIN, this.position()).withParameter(LootContextParams.TOOL, stack).withParameter(LootContextParams.THIS_ENTITY, this).withLuck((float)this.luck + player.getLuck()).create(LootContextParamSets.FISHING);

                ObjectArrayList<ItemStack> list = tier.process(lootParams, stack);

                CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayer)player, stack, this, list);
                for (ItemStack itemstack : list) {
                    ItemEntity itementity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), itemstack);
                    double d0 = player.getX() - this.getX();
                    double d1 = player.getY() - this.getY();
                    double d2 = player.getZ() - this.getZ();
                    double d3 = 0.1;
                    itementity.setDeltaMovement(d0 * 0.1, d1 * 0.1 + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08, d2 * 0.1);
                    this.level().addFreshEntity(itementity);
                    player.level().addFreshEntity(new ExperienceOrb(player.level(), player.getX(), player.getY() + 0.5, player.getZ() + 0.5, this.random.nextInt(6) + 1));
                    if (!itemstack.is(ItemTags.FISHES)) continue;
                    player.awardStat(Stats.FISH_CAUGHT, 1);
                }
                i = 1;
            }
            if (this.onGround()) {
                if(tier != Teir.RUBY) i = 2;
            }
            this.discard();

            return i;
        }

        return 0;
    }

    public Teir getTier() {
        return tier;
    }

    public enum Teir {
        OLD(GenerationCoreLootTables.FISHING_OLD),
        GOOD(GenerationCoreLootTables.FISHING_GOOD),
        SUPER(GenerationCoreLootTables.FISHING_SUPER),
        RUBY(GenerationCoreLootTables.FISHING_RUBY);

        private final ResourceLocation resourceLocation;

        Teir(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public ObjectArrayList<ItemStack> process(LootParams lootParams, ItemStack stack) {
            var level = lootParams.getLevel();
            var loottable = level.getServer().getLootData().getLootTable(resourceLocation);

            ObjectArrayList<ItemStack> list = loottable.getRandomItems(lootParams);

            if (this == RUBY) {
                int tries = 0;

                // Get the initial shard counts from the rod
                Map<RubyRodItem.LakeTrioShardType, Byte> currentShards = RubyRodItem.getFishedShard(stack);

                while (tries < 3) {
                    // Step 1: Sanitize the list using the current shard counts
                    list = RubyRodItem.sanitizeList(list, currentShards);

                    // Step 2: If the list is empty after sanitization, retry
                    if (list.isEmpty()) {
                        tries++;
                        list = loottable.getRandomItems(lootParams);
                        continue;
                    }

                    // Step 3: Save the updated shard counts to the rod's NBT
                    RubyRodItem.saveShardCounts(stack, currentShards);

                    // Step 4: Return the sanitized and valid list
                    return list;
                }

                // If no valid list is found after 3 tries, return an empty list
                return new ObjectArrayList<>();
            }

            return list;
        }
    }
}
