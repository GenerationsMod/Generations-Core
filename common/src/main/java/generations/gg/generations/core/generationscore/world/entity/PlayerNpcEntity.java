package generations.gg.generations.core.generationscore.world.entity;

import com.cobblemon.mod.common.CobblemonEntities;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.google.common.collect.ImmutableMap;
import generations.gg.generations.core.generationscore.api.data.GenerationsCoreEntityDataSerializers;
import generations.gg.generations.core.generationscore.mixin.LivingEntityAccessor;
import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.network.packets.npc.S2COpenNpcCustomizationScreenPacket;
import generations.gg.generations.core.generationscore.network.packets.shop.C2SShopItemPacket;
import generations.gg.generations.core.generationscore.util.NpcUtils;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import generations.gg.generations.core.generationscore.world.entity.ai.goal.PatrolPathGoal;
import generations.gg.generations.core.generationscore.world.entity.ai.goal.RandomWanderAroundPosGoal;
import generations.gg.generations.core.generationscore.world.item.NpcWandItem;
import generations.gg.generations.core.generationscore.world.npc.NpcPreset;
import generations.gg.generations.core.generationscore.world.npc.NpcPresets;
import generations.gg.generations.core.generationscore.world.npc.display.HeldItemsInfo;
import generations.gg.generations.core.generationscore.world.npc.display.NpcDisplayData;
import generations.gg.generations.core.generationscore.world.npc.display.RotationInfo;
import generations.gg.generations.core.generationscore.world.shop.Offers;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class PlayerNpcEntity extends PathfinderMob implements ShopOfferProvider {

    public static final EntityDataAccessor<Byte> DATA_PLAYER_MODE_CUSTOMISATION = SynchedEntityData.defineId(PlayerNpcEntity.class, EntityDataSerializers.BYTE);
    public static final EntityDataAccessor<NpcDisplayData> DATA_DISPLAY = SynchedEntityData.defineId(PlayerNpcEntity.class, GenerationsCoreEntityDataSerializers.NPC_PRESET);
    public static final EntityDataAccessor<CompoundTag> DATA_SHOP = SynchedEntityData.defineId(PlayerNpcEntity.class, EntityDataSerializers.COMPOUND_TAG);
    public static final EntityDataAccessor<String> DATA_DIALOGUE = SynchedEntityData.defineId(PlayerNpcEntity.class, EntityDataSerializers.STRING);

    private static final EntityDimensions SITTING_DIMENSIONS = EntityDimensions.scalable(0.6F, 1.3F);
    private static final Map<Pose, EntityDimensions> POSES = ImmutableMap.<Pose, EntityDimensions>builder().put(Pose.STANDING, Player.STANDING_DIMENSIONS).put(Pose.SLEEPING, SLEEPING_DIMENSIONS).put(Pose.FALL_FLYING, EntityDimensions.scalable(0.6F, 0.6F)).put(Pose.SWIMMING, EntityDimensions.scalable(0.6F, 0.6F)).put(Pose.SPIN_ATTACK, EntityDimensions.scalable(0.6f, 0.6f)).put(Pose.SITTING, SITTING_DIMENSIONS).put(Pose.CROUCHING, EntityDimensions.scalable(0.6F, 1.5F)).put(Pose.DYING, EntityDimensions.fixed(0.2F, 0.2F)).build();

    private final NonNullList<ItemStack> handItems;
    private final NonNullList<ItemStack> armorItems;

//    @OnlyIn(Dist.CLIENT)
    private ResourceLocation texture;
    private LivingEntity renderedEntity;
    private CompoundTag prevRenderedEntityTag;

    private ResourceLocation dialogue;
    private Offers offers;

    public PlayerNpcEntity(EntityType<PlayerNpcEntity> type, Level level) {
        super(type, level);
        this.handItems = NonNullList.withSize(2, ItemStack.EMPTY);
        this.armorItems = NonNullList.withSize(4, ItemStack.EMPTY);
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.getMainHandItem().getItem() instanceof NpcWandItem) {
                GenerationsNetwork.INSTANCE.sendPacketToPlayer(serverPlayer, new S2COpenNpcCustomizationScreenPacket(this.getId()));
            }
            else if (this.dialogue != null) {
                new DialoguePlayer(this.dialogue, this, serverPlayer, false);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setDisplayData(new NpcDisplayData(tag.getCompound("displayData")));
        if (tag.contains("shop")) {
            setOffers(Offers.of(tag.getCompound("shop"), level()));
        } else {
            setOffers(null);
        }
        if (tag.contains("dialogue")) {
            var key = tag.getString("dialogue");
            setDialogue(key.isBlank() ? null : new ResourceLocation(key));
        } else {
            setDialogue(null);
        }
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        if (source.is(DamageTypes.FALL)) {
            playAmbientSound();
            return false;
        }

        return source.is(DamageTypes.FELL_OUT_OF_WORLD) && !(source.getDirectEntity() instanceof Player);
    }

    @Override
    public void kill() {
        this.discard();
    }

    private void applyAi() {
        if (this.navigation.isInProgress()) {
            this.navigation.stop();
        }

        for (WrappedGoal goal : this.goalSelector.getAvailableGoals()) {
            this.goalSelector.removeGoal(goal);
        }

        var rotationType = getDisplayData().getRotationInfo().getType();
        var movementType = getDisplayData().getMovementInfo().getType();

        if (rotationType == RotationInfo.RotationType.NEAREST_PLAYER) {
            this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        }

        switch (movementType) {
            case WANDER_RANDOMLY -> {
                if (getDisplayData().getMovementInfo().getOrigin() != null) {
                    this.goalSelector.addGoal(0, new RandomWanderAroundPosGoal(this));
                }
            }
            case WANDER_PATH -> {
                if (getDisplayData().getMovementInfo().getPath() != null && getDisplayData().getMovementInfo().getPath().size() > 1) {
                    this.goalSelector.addGoal(0, new PatrolPathGoal(this));
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getOffers() != null) {
            this.offers.tick(level());
        }
    }

    @Override
    public void checkDespawn() {
    }

    @Override
    public @NotNull Iterable<ItemStack> getArmorSlots() {
        return this.armorItems;
    }

    @Override
    public @NotNull ItemStack getItemBySlot(EquipmentSlot slot) {
        return switch (slot.getType()) {
            case HAND -> this.handItems.get(slot.getIndex());
            case ARMOR -> this.armorItems.get(slot.getIndex());
        };
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, @NotNull ItemStack stack) {
        this.verifyEquippedItem(stack);
        switch (slot.getType()) {
            case HAND -> this.onEquipItem(slot, this.handItems.set(slot.getIndex(), stack), stack);
            case ARMOR -> this.onEquipItem(slot, this.armorItems.set(slot.getIndex(), stack), stack);
        }
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.put("displayData", getDisplayData().serializeToNbt());
        Offers shop = getOffers();
        if (shop != null) {
            tag.put("shop", shop.serializeToNbt());
        }
        if (this.dialogue != null) {
            tag.putString("dialogue", dialogue.toString());
        }
    }

    public boolean isModelPartShown(PlayerModelPart part) {
        return true;
        // TODO return (this.getEntityData().get(DATA_PLAYER_MODE_CUSTOMISATION) & part.getMask()) == part.getMask();
    }

    @Override
    public @NotNull HumanoidArm getMainArm() {
        return HumanoidArm.LEFT; // Easter Egg
    }

    @Override
    public boolean alwaysAccepts() {
        return super.alwaysAccepts();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_PLAYER_MODE_CUSTOMISATION, (byte) 0);
        this.entityData.define(DATA_DISPLAY, NpcUtils.DEFAULT.copy());
        this.entityData.define(DATA_SHOP, new CompoundTag());
        this.entityData.define(DATA_DIALOGUE, dialogue == null ? "" : dialogue.toString());
    }

    @Override
    public @NotNull EntityDimensions getDimensions(@NotNull Pose pose) {
        if (renderedEntity == null) {
            return POSES.getOrDefault(pose, Player.STANDING_DIMENSIONS).scale(getRenderedScale());
        }
        return renderedEntity.getDimensions(Pose.STANDING).scale(getRenderedScale());
    }

    @Override
    protected float getStandingEyeHeight(@NotNull Pose poseIn, @NotNull EntityDimensions sizeIn) {
        if (renderedEntity == null) {
            // values from Player#getStandingEyeHeight, except sitting since it's custom
            return switch (poseIn) {
                case SWIMMING, FALL_FLYING, SPIN_ATTACK -> 0.4f;
                case SITTING -> 1.1F;
                case CROUCHING -> 1.27F;
                default -> 1.62F;
            }  * getRenderedScale();
        }
        return ((LivingEntityAccessor) renderedEntity).generationscore_getEyeHeight(poseIn, sizeIn) * getRenderedScale();
    }

    public void setDialogue(@Nullable ResourceLocation key) {
        this.dialogue = key;
        this.entityData.set(DATA_DIALOGUE, key == null ? "" : key.toString());
    }

    public void loadOffers(ResourceLocation shopKey) {
        this.setOffers(Offers.of(shopKey, level()));
    }

    @Nullable
    public Offers getOffers() {
        if (this.offers == null) {
            CompoundTag tag = this.entityData.get(DATA_SHOP);
            if (tag.isEmpty())
                return null;

            this.offers = Offers.of(tag, level());
        }

        return this.offers;
    }

    public void setOffers(@Nullable Offers offers) {
        if (offers == null) {
            this.entityData.set(DATA_SHOP, new CompoundTag());
        } else {
            this.entityData.set(DATA_SHOP, offers.serializeToNbt());
        }
        this.offers = offers;
    }

//    @OnlyIn(Dist.CLIENT)
    @Override
    public GenerationsNetworkPacket<?> createItemPacket(ItemStack itemStack, int price, int amount, boolean isBuyPage) {
        return new C2SShopItemPacket(this.getId(), itemStack, price, amount, isBuyPage);
    }

    public NpcDisplayData getDisplayData() {
        return this.entityData.get(DATA_DISPLAY);
    }

    public void updateDisplayData() {
        this.setDisplayData(getDisplayData());
    }

    public void setDisplayData(@NotNull NpcDisplayData displayData) {
        this.entityData.set(DATA_DISPLAY, displayData);

        setHeldItems(displayData.getHeldItemsInfo());

        setRenderedEntityFromTag(getRenderedEntityTagWithId());
        setRotationFromDisplayData();

        setCustomName(Component.literal(displayData.getDisplayName()));
        setCustomNameVisible(displayData.isNameVisible());
        if (!this.level().isClientSide) {
            this.applyAi();
        }
    }

    public void setRotationFromDisplayData() {
        RotationInfo rotationInfo = getDisplayData().getRotationInfo();
        if (rotationInfo.getType() == RotationInfo.RotationType.STATIC) {
            float bodyY = rotationInfo.getBodyY() % 360;
            this.setYRot(bodyY);
            this.yRotO = bodyY;
            this.setYBodyRot(bodyY);
            this.yBodyRotO = bodyY;

            float headY = rotationInfo.getHeadY() % 360;
            this.setYHeadRot(headY);
            this.yHeadRotO = headY;

            float headX = rotationInfo.getHeadX() % 360;
            this.setXRot(headX);
            this.xRotO = headX;
        }
    }

    public void loadPreset(ResourceLocation id) {
        if (NpcPresets.instance().contains(id)) {
            var preset = NpcPresets.instance().get(id);
            setDisplayData(preset.getDisplayData());
            loadOffers(preset.getShopKey());
            setDialogue(preset.getDialogueKey());
        } else {
            setDisplayData(NpcUtils.DEFAULT.copy());
            setOffers(null);
            setDialogue(null);
        }
    }

    public NpcPreset toPreset() {
        return new NpcPreset(getDisplayData(), offers == null ? null : offers.getKey(), dialogue);
    }

    private void setHeldItems(HeldItemsInfo heldItems) {
        this.setItemSlot(EquipmentSlot.HEAD, heldItems.getHelmet());
        this.setItemSlot(EquipmentSlot.CHEST, heldItems.getChestplate());
        this.setItemSlot(EquipmentSlot.LEGS, heldItems.getLeggings());
        this.setItemSlot(EquipmentSlot.FEET, heldItems.getBoots());
        this.setItemSlot(EquipmentSlot.MAINHAND, heldItems.getMainhand());
        this.setItemSlot(EquipmentSlot.OFFHAND, heldItems.getOffhand());
    }

    public String getTexture() {
        return getDisplayData().getRendererInfo().getTexture();
    }

    public boolean isTextureUsername() {
        return getDisplayData().getRendererInfo().isTextureUsername();
    }

    public boolean isSitting() {
        return getDisplayData().getRendererInfo().getPose() == Pose.SITTING;
    }

    public float getRenderedScale() {
        return getDisplayData().getRendererInfo().getScale() <= 0f ? 1f : getDisplayData().getRendererInfo().getScale();
    }

    public String getRenderedEntityTypeKey() {
        return getDisplayData().getRendererInfo().getEntityTypeKey();
    }

    public CompoundTag getRenderedEntityTag() {
        return getDisplayData().getRendererInfo().getTag();
    }

    public CompoundTag getRenderedEntityTagWithoutId() {
        CompoundTag tag = getRenderedEntityTag();
        tag.remove("id");
        return tag;
    }

    public CompoundTag getRenderedEntityTagWithId() {
        CompoundTag tag = getRenderedEntityTag();
        tag.putString("id", getRenderedEntityTypeKey());
        return tag;
    }

    public LivingEntity getRenderedEntity() {
        if (renderedEntity == null
                || renderedEntity.getType() != this.getRenderedEntityType()
                || !getRenderedEntityTagWithId().equals(prevRenderedEntityTag)) {
            setRenderedEntityFromTag(getRenderedEntityTagWithId());
        }
        return renderedEntity;
    }

    public EntityType<?> getRenderedEntityType() {
        String entityTypeKey = getRenderedEntityTypeKey();
        if (entityTypeKey.equals(NpcUtils.MODEL_STEVE) || entityTypeKey.equals(NpcUtils.MODEL_ALEX))
            return this.getType();
        return EntityType.byString(entityTypeKey).orElse(this.getType());
    }

    private void setRenderedEntityFromTag(CompoundTag tag) {
        if (tag.getString("id").isBlank()
                || tag.getString("id").equals(EntityType.getKey(this.getType()).toString())
                || tag.getString("id").equals(NpcUtils.MODEL_STEVE)
                || tag.getString("id").equals(NpcUtils.MODEL_ALEX)) {
            this.renderedEntity = null;
            this.prevRenderedEntityTag = null;
            setPose(getDisplayData().getRendererInfo().getPose());
            refreshDimensions();
            return;
        }

        if (tag.getString("id").equals("pokemod:pixelmon")) {
            Pokemon data = NpcUtils.pixelmonDataFromTag(tag);
            prevRenderedEntityTag = tag;
            renderedEntity = new PokemonEntity(level(), data, CobblemonEntities.POKEMON);
            refreshDimensions();
            return;
        }
        Optional<Entity> entityOpt = EntityType.create(tag, this.level());
        if (entityOpt.isPresent()) {
            prevRenderedEntityTag = tag;
            renderedEntity = (LivingEntity) entityOpt.get();
            setRenderedEntityItems();
            refreshDimensions();
        }

    }

    public void setRenderedEntityItems() {
        if (renderedEntity != null) {
            if (renderedEntity instanceof Mob renderedMob) {
                renderedMob.setLeftHanded(this.getMainArm() == HumanoidArm.LEFT);
            }
            renderedEntity.setItemSlot(EquipmentSlot.MAINHAND, this.getItemBySlot(EquipmentSlot.MAINHAND));
            renderedEntity.setItemSlot(EquipmentSlot.OFFHAND, this.getItemBySlot(EquipmentSlot.OFFHAND));
            renderedEntity.setItemSlot(EquipmentSlot.HEAD, this.getItemBySlot(EquipmentSlot.HEAD));
            renderedEntity.setItemSlot(EquipmentSlot.CHEST, this.getItemBySlot(EquipmentSlot.CHEST));
            renderedEntity.setItemSlot(EquipmentSlot.LEGS, this.getItemBySlot(EquipmentSlot.LEGS));
            renderedEntity.setItemSlot(EquipmentSlot.FEET, this.getItemBySlot(EquipmentSlot.FEET));
        }
    }

//    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getTextureResourceLocation() {
        return texture;
    }

//    @OnlyIn(Dist.CLIENT)
    public void setTexture(ResourceLocation texture) {
        this.texture = texture;
    }

    @Override
    public boolean isPushable() {
        return getDisplayData().getCollision() == NpcDisplayData.Collision.FULL;
    }

    @Override
    protected void pushEntities() {
        if (getDisplayData().getCollision() != NpcDisplayData.Collision.NONE) {
            super.pushEntities();
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612D);
    }
}