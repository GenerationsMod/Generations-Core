package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class BallLootBlockEntity extends ModelProvidingBlockEntity {
    private UUID owner = null;
    private final List<LootClaim> claims = new ArrayList<>();
    private LootMode lootMode = LootMode.ONCE_PER_PLAYER;
    private boolean doesCustomDrops = false;
    private boolean visible = true;
    private NonNullList<ItemStack> customDrops = null;

    @Override
    protected void readNbt(CompoundTag nbt, HolderLookup.Provider provider) {
        owner = nbt.contains("owner") ? nbt.getUUID("owner") : null;

        claims.clear();
        if(nbt.contains("claims")) {
            var listTag = nbt.getList("claims", CompoundTag.TAG_COMPOUND);

            for (int i = 0; i < listTag.size(); i++) {
                var tag = listTag.getCompound(i);
                var uuid = tag.getUUID("uuid");
                var instant = tag.getLongArray("instant");

                claims.add(new LootClaim(uuid, Instant.ofEpochSecond(instant[0], instant[1])));
            }
        }

        lootMode = LootMode.valueOf(nbt.getString("lootMode"));
        doesCustomDrops = nbt.getBoolean("doesCustomDrops");
        visible = nbt.getBoolean("visible");

        if(nbt.contains("customDrops"))
            customDrops = nbt.getList("customDrops", CompoundTag.TAG_COMPOUND).stream().map(CompoundTag.class::cast).map(ItemStack::of).collect(Collectors.toCollection(NonNullList::create));
    }

    @Override
    protected void writeNbt(CompoundTag nbt, HolderLookup.Provider provider) {
        if(owner != null)nbt.putUUID("owner", owner);
        nbt.put("claims", claims.stream().map(a -> {
            var tag = new CompoundTag();
            tag.putUUID("uuid", a.uuid);
            var instant = a.time;
            tag.putLongArray("instant", new long[] { instant.getEpochSecond(), instant.getNano() });

            return tag;
        }).collect(ListTag::new, AbstractList::add, AbstractCollection::addAll));

        nbt.putString("lootMode", lootMode.name());
        nbt.putBoolean("doesCustomDrops", doesCustomDrops);
        nbt.putBoolean("visible", visible);

        if(customDrops != null)
            nbt.put("customDrops", customDrops.stream().map(GenerationsUtils::toCompoundTag).collect(ListTag::new, AbstractList::add, AbstractCollection::addAll));
    }

    public BallLootBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(GenerationsBlockEntities.BALL_LOOT.get(), pPos, pBlockState);
    }

//    @Override
//    public String getVariant() {
//        return getBlockState().getBlock() instanceof BallLootBlock loot ? loot.getType() : "poke";
//    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public boolean canClaim(UUID playerUUID) {
        if (lootMode == LootMode.UNLIMITED) return true;
        
        Optional<LootClaim> claim = this.getLootClaim(playerUUID);

        if (claim.isEmpty()) {
            addClaimer(playerUUID);
            return true;
        }
        if (this.lootMode.isTimeEnabled())
            if (claim.get().isExpired()) {
                claim.get().refresh();
                return true;
            }
        return false;
    }

    public Optional<LootClaim> getLootClaim(UUID playerUUID) {
        return this.claims.stream().filter(claim -> claim.uuid.equals(playerUUID)).findFirst();
    }

    public void addClaimer(UUID playerUUID) {
        this.claims.add(new LootClaim(playerUUID, Instant.now().plus(GenerationsCore.CONFIG.lootTime)));
    }


    public void removeClaimer(UUID playerUUID) {
        this.claims.removeIf(a -> a.uuid.equals(playerUUID));
    }

    public boolean shouldBreakBlock() {
        return lootMode.isBreakable();
    }

    public void setLootMode(LootMode lootMode) {
        this.lootMode = lootMode;
    }

    public LootMode getLootMode() {
        return this.lootMode;
    }

    public boolean isCustomDrop() {
        return this.doesCustomDrops && this.customDrops != null;
    }

    public NonNullList<ItemStack> getCustomDrops() {
        return this.customDrops;
    }

    public void setCustomDrops(ItemStack ... customDrops) {
        this.doesCustomDrops = true;
        this.customDrops = NonNullList.of(ItemStack.EMPTY, customDrops);
    }

    public void addCustomDrop(ItemStack drop) {
        this.doesCustomDrops = true;
        if(this.customDrops == null)
            this.customDrops = NonNullList.create();
        this.customDrops.add(drop);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        sync();
    }

    public enum LootMode {
        ONCE_PER_PLAYER, TIMED, ONCE, UNLIMITED;

        public boolean isDropOnce() {
            return this == ONCE_PER_PLAYER || this == ONCE;
        }

        public boolean isTimeEnabled() {
            return this == TIMED;
        }

        public boolean isBreakable() {
            return this == ONCE;
        }
    }

    @Override
    public float getAngle() {
        if (getBlockState().hasProperty(HorizontalDirectionalBlock.FACING)) {
            var dir = getBlockState().getValue(HorizontalDirectionalBlock.FACING);

            switch (dir) {
                case EAST -> dir = Direction.WEST;
                case WEST -> dir = Direction.EAST;
                default -> {}
            }

            return dir.getOpposite().toYRot();
        }
        return 0f;
    }
}
