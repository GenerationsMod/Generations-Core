package generations.gg.generations.core.generationscore.world.entity;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.client.CobblemonResources;
import com.cobblemon.mod.common.client.entity.PokemonClientDelegate;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.pokemon.RenderablePokemon;
import com.cobblemon.mod.common.pokemon.Species;
import dev.architectury.utils.EnvExecutor;
import generations.gg.generations.core.generationscore.api.data.GenerationsCoreEntityDataSerializers;
import generations.gg.generations.core.generationscore.client.StatueEntityClient;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.PixelmonInstance;
import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.packets.statue.S2COpenStatueEditorScreenPacket;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.SacredAshItem;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

import static dev.architectury.utils.Env.CLIENT;

public class StatueEntity extends LivingEntity implements PixelmonInstanceProvider {
    public static final EntityDataAccessor<StatueInfo> STATUE_DATA = SynchedEntityData.defineId(StatueEntity.class, GenerationsCoreEntityDataSerializers.STATUE_INFO);

    private EntityDimensions dimensions;
    private boolean sizeChanged;

    /**
     * <b>CLIENT ONLY. DO NOT MODIFY</b>
     **/
    public StatueEntityClient delegate;

    public StatueEntity(EntityType<StatueEntity> arg, Level arg2) {
        super(arg, arg2);
        this.sizeChanged = true;
        setNoGravity(true);
        setInvulnerable(true);
        this.noPhysics = true;
        EnvExecutor.runInEnv(CLIENT, () -> () -> delegate = new StatueEntityClient());
    }

    public StatueEntity(Level level) {
        this(GenerationsEntities.STATUE_ENTITY.get(), level);
    }

    @Override
    public @NotNull Iterable<ItemStack> getArmorSlots() {
        return List.of();
    }

    @Override
    public @NotNull ItemStack getItemBySlot(@NotNull EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(@NotNull EquipmentSlot slot, @NotNull ItemStack stack) {

    }

    @Override
    public @NotNull InteractionResult interact(@NotNull Player player, @NotNull InteractionHand hand) {
        if (!level().isClientSide()) {
            var stack = player.getItemInHand(hand);

            if (stack.is(GenerationsItems.SACRED_ASH.get()) && getStatueData().isSacredAshInteractable() && SacredAshItem.isFullyCharged(stack)) {
                var entity = getStatueData().getProperties().createEntity(level());
                entity.setPos(Vec3.atBottomCenterOf(getOnPos()));
                level().addFreshEntity(entity);
                stack.shrink(1);
                return InteractionResult.SUCCESS;
            } else if (player.getItemInHand(hand).getItem().equals(GenerationsItems.CHISEL.get())) {
                GenerationsNetwork.INSTANCE.sendPacketToPlayer((ServerPlayer) player, new S2COpenStatueEditorScreenPacket(getId()));
            }
        }

        return super.interact(player, hand);
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        return super.hurt(source, amount);
    }

    @Override
    public @NotNull HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATUE_DATA, new StatueInfo());
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.put("data", getStatueData().serializeNbt());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setStatueInfo(new StatueInfo(tag.getCompound("data")));
        refreshInstance();
        this.sizeChanged = true;
    }

    public StatueInfo getStatueData() {
        return this.getEntityData().get(STATUE_DATA);
    }

    public void setStatueInfo(StatueInfo data) {
        this.getEntityData().set(STATUE_DATA, data);
        setRotationFromStatueData();
        refreshInstance();
        this.sizeChanged = true;
    }

    private void refreshInstance() {
        EnvExecutor.runInEnv(CLIENT, () -> () -> delegate.setInstance(null));
    }

    @Override
    public @NotNull EntityDimensions getDimensions(@NotNull Pose pose) {
        if (sizeChanged) {
            dimensions = getInfo().getForm().getHitbox();
            sizeChanged = false;
        }

        return dimensions;
    }

    private void setRotationFromStatueData() {
        var orientation = getStatueData().getOrientation() % 360;
        this.setYRot(orientation);
        this.yRotO = orientation;
        this.setYBodyRot(orientation);
        this.yBodyRotO = orientation;
    }

    public RenderablePokemon getInfo() {
        return getStatueData().getProperties().asRenderablePokemon();
    }

    public void updateStatueData() {
        setStatueInfo(getStatueData());
    }

    @Override
    public @NotNull InteractionResult interactAt(@NotNull Player player, @NotNull Vec3 vec, @NotNull InteractionHand hand) {
        return super.interactAt(player, vec, hand);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (key.equals(StatueEntity.STATUE_DATA)) {
            updateStatueData();
            refreshDimensions();
        }

        super.onSyncedDataUpdated(key);
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public PixelmonInstance getInstance() {
        return delegate != null ? delegate.getInstance() : null;
    }

    @Override
    public void setInstance(PixelmonInstance instance) {
        if(delegate != null) {
            delegate.setInstance(instance);
        }
    }

    @Override
    public float getScale() {
        return getStatueData().getScale();
    }

    @Override
    public Component getDisplayName() {
        String label = getStatueData().label;
        return label != null ? Component.nullToEmpty(label) : super.getDisplayName();
    }

    public static final class StatueInfo {
        private static ResourceLocation defaultSpecies = new ResourceLocation("cobblemon", "charizard");
        private PokemonProperties properties;
        private float orientation;
        private String animation;
        private boolean isStatic;
        private float progress;
        private float scale;

        private boolean sacredAshInteractable;
        private String label = null;

        public StatueInfo() {
            this(PokemonProperties.Companion.parse("species=charizard", " ", "="), "Statue", 0.0f, 1.0f, "", false, 0.0f, false);
        }

        public StatueInfo(PokemonProperties properties, String label, float orientation, float scale, String animation, boolean isStatic, float progress, boolean sacredAshInteractable) {
            this.properties = properties;
            this.label = label;
            this.orientation = orientation;
            this.scale = scale;
            this.animation = animation;
            this.isStatic = isStatic;
            this.progress = progress;
            this.sacredAshInteractable = sacredAshInteractable;
        }

        public StatueInfo(FriendlyByteBuf buf) {
            this(PokemonProperties.Companion.parse(buf.readUtf(), " ", "="), buf.readNullable(FriendlyByteBuf::readUtf), buf.readFloat(), buf.readFloat(), buf.readUtf(), buf.readBoolean(), buf.readFloat(), buf.readBoolean());
        }

        public StatueInfo(CompoundTag tag) {
            this(PokemonProperties.Companion.parse(tag.getString("properties"), " ", "="), tag.getString("label"), tag.getFloat("orientation"), tag.getFloat("scale"), tag.getString("animation"), tag.getBoolean("isStatic"), tag.getFloat("progress"), tag.getBoolean("sacredAshInteractable"));
        }

        public static StatueInfo of(PokemonProperties properties) {
            return new StatueInfo(properties, "", 0.0f, 1.0f, "", true, 0.0f, false);
        }

        public PokemonProperties getProperties() {
            return properties;
        }

        public RenderablePokemon asRenderablePokemon() {
            var properties = getProperties();

            Species species = null;

            if(properties.getSpecies() != null) {
                species = PokemonSpecies.INSTANCE.getByIdentifier(new ResourceLocation("cobblemon", properties.getSpecies()));

                if(species == null) {
                    species = PokemonSpecies.INSTANCE.getByIdentifier(defaultSpecies);
                }
            } else {
                species = PokemonSpecies.INSTANCE.getByIdentifier(defaultSpecies);
            }
            return new RenderablePokemon(species, properties.getAspects());
        }

        public void setProperties(PokemonProperties properties) {
            this.properties = properties;
        }

        public void setSacredAshInteractable(boolean sacredAshInteractable) {
            this.sacredAshInteractable = sacredAshInteractable;
        }

        public boolean isSacredAshInteractable() {
            return sacredAshInteractable;
        }

        public boolean isAnimated() {
            return !isStatic;
        }

        public String getAnimation() {
            return animation;
        }

        public float getFrame() {
            return progress;
        }

        public CompoundTag serializeNbt() {
            CompoundTag tag = new CompoundTag();
            tag.putString("properties", properties.asString(" "));
            tag.putFloat("orientation", orientation);
            tag.putFloat("scale", scale);
            tag.putString("animation", animation);
            tag.putBoolean("isStatic", isStatic);
            tag.putFloat("progress", progress);
            tag.putBoolean("sacredAshInteractable", sacredAshInteractable);
            if(label != null) tag.putString("label", label);
            return tag;
        }

        public void serializeToByteBuf(FriendlyByteBuf buf) {
            buf.writeUtf(properties.asString(" ")).writeNullable(label, FriendlyByteBuf::writeUtf);
            buf.writeFloat(orientation).writeFloat(scale);
            buf.writeUtf(animation).writeBoolean(isStatic).writeFloat(progress).writeBoolean(sacredAshInteractable);
        }

        public float getOrientation() {
            return orientation;
        }

        public void setOrientation(float orientation) {
            this.orientation = orientation;
        }

        public float getScale() {
            return scale;
        }

        public void setScale(float scale) {
            this.scale = scale;
        }

        public void setIsStatic(boolean isStatic) {
            this.isStatic = isStatic;
        }

        public boolean isStatic() {
            return isStatic;
        }

        public void setAnimation(String animation) {
            this.animation = animation;
        }

        public void setProgress(float timestamp) {
            this.progress = timestamp;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}