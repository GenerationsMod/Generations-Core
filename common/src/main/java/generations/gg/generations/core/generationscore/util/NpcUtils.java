package generations.gg.generations.core.generationscore.util;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.google.common.collect.Streams;
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.npc.display.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NpcUtils {

    public static final String MODEL_STEVE = "minecraft:steve";
    public static final String MODEL_ALEX = "minecraft:alex";

    public static final String PIXELMON_DATA_TAG_KEY = "pixelmonData";
    public static final String PIXELMON_DATA_POKEDEXENTRY_KEY = "PokedexEntry";
    public static final String PIXELMON_DATA_FORM_KEY = "Form";
    public static final String PIXELMON_DATA_SKIN_KEY = "SpecialSkin";

    public static final NpcDisplayData DEFAULT = new NpcDisplayData(
            "Test NPC", true, NpcDisplayData.Collision.PUSH_OTHERS,
            new RendererInfo(MODEL_STEVE, new CompoundTag(), 1.0F, Pose.STANDING.name(), "", false),
            new HeldItemsInfo(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY),
            new MovementInfo("still", BlockPos.ZERO, 0, Collections.emptyList(), 0.0F, 0),
            new RotationInfo("static", 45.0F, -10.0F, 0.0F)
    );

    private static List<String> ENTITY_TYPES;
    private static boolean hasLoadedEntities = false;

    public static Pokemon pixelmonDataFromTag(CompoundTag tag) {
        try {
            return Pokemon.Companion.loadFromNBT(tag.getCompound(PIXELMON_DATA_TAG_KEY));
        } catch (Exception e) {
            return new Pokemon();
        }
    }

    public static void loadValidEntityTypes(Level level) {
        if (!hasLoadedEntities) {
            List<String> invalidTypes = getInvalidTypes();
            List<EntityType<?>> entityTypes = new ArrayList<>();
            BuiltInRegistries.ENTITY_TYPE.forEach(entityType -> {
                ResourceLocation key = EntityType.getKey(entityType);
                if (!invalidTypes.contains(key.toString())
                        && entityType.create(level) instanceof LivingEntity) {
                    entityTypes.add(entityType);
                }
            });
            ENTITY_TYPES = entityTypes.stream()
                    .map(type -> EntityType.getKey(type).toString())
                    .collect(Collectors.toList());
            int index = 0;
            for (int i = 0; i < ENTITY_TYPES.size(); i++) {
                if (ENTITY_TYPES.get(i).startsWith("minecraft:")) {
                    index = i;
                    break;
                }
            }
            ENTITY_TYPES.add(index, MODEL_STEVE);
            ENTITY_TYPES.add(index + 1, MODEL_ALEX);
            hasLoadedEntities = true;
        }
    }

    public static List<String> getValidModelEntityTypes() {
        return !hasLoadedEntities ? Collections.emptyList() : ENTITY_TYPES;
    }

    public static boolean isTypeValid(String key) {
        return !hasLoadedEntities || ENTITY_TYPES.contains(key);
    }

    private static List<String> getInvalidTypes() {
        List<String> invalidTypes = Streams.stream(GenerationsEntities.ENTITIES.iterator())
                .map(registryObject -> EntityType.getKey(registryObject.get()).toString())
                .filter(s -> s.contains("npc"))
                .collect(Collectors.toList());
        invalidTypes.add(EntityType.getKey(GenerationsEntities.STATUE_ENTITY.get()).toString());
        // the ender dragon renderer isn't a LivingEntityRenderer, so it'd cause issues with our current implementation
        invalidTypes.add(EntityType.getKey(EntityType.ENDER_DRAGON).toString());
        return invalidTypes;
    }

}