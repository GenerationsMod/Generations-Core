package generations.gg.generations.core.generationscore.common.world.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntry;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.Consumer;

public class ResouceKeyEntry extends LootPoolSingletonContainer {
    private final ResourceKey<Item> tag;
    private final boolean expand;

    ResouceKeyEntry(ResourceKey<Item> tag, boolean expand, int weight, int quality, LootItemCondition[] conditions, LootItemFunction[] functions) {
        super(weight, quality, conditions, functions);
        this.tag = tag;
        this.expand = expand;
    }

    @Override
    public LootPoolEntryType getType() {
        return LootPoolEntryTypes.RESOUCE_KEY.get();
    }

    @Override
    public void createItemStack(Consumer<ItemStack> stackConsumer, LootContext lootContext) {
        BuiltInRegistries.ITEM.getHolder(this.tag).ifPresent(arg -> stackConsumer.accept(new ItemStack(arg)));
    }

    private boolean expandTag(LootContext context, Consumer<LootPoolEntry> generatorConsumer) {
        if (this.canRun(context)) {
            BuiltInRegistries.ITEM.getHolder(this.tag).ifPresent(holder -> {
                generatorConsumer.accept(new LootPoolSingletonContainer.EntryBase(){

                    @Override
                    public void createItemStack(Consumer<ItemStack> stackConsumer, LootContext lootContext) {
                        stackConsumer.accept(new ItemStack(holder));
                    }
                });
            });


            return true;
        }
        return false;
    }

    @Override
    public boolean expand(LootContext arg, Consumer<LootPoolEntry> consumer) {
        if (this.expand) {
            return this.expandTag(arg, consumer);
        }
        return super.expand(arg, consumer);
    }

    public static LootPoolSingletonContainer.Builder<?> resourceKeyContents(ResourceKey<Item> tag) {
        return simpleBuilder((i, j, args, args2) -> new ResouceKeyEntry(tag, false, i, j, args, args2));
    }

    public static LootPoolSingletonContainer.Builder<?> resourceKeyContents(String key) {
        return resourceKeyContents(ResourceKey.create(Registries.ITEM, new ResourceLocation(key)));
    }

    public static LootPoolSingletonContainer.Builder<?> expandResouceKey(ResourceKey<Item> tag) {
        return simpleBuilder((i, j, args, args2) -> new ResouceKeyEntry(tag, true, i, j, args, args2));
    }

    public static class Serializer extends LootPoolSingletonContainer.Serializer<ResouceKeyEntry> {
        @Override
        public void serializeCustom(JsonObject object, ResouceKeyEntry context, JsonSerializationContext conditions) {
            super.serializeCustom(object, context, conditions);
            object.addProperty("name", context.tag.location().toString());
            object.addProperty("expand", context.expand);
        }

        @Override
        protected ResouceKeyEntry deserialize(JsonObject object, JsonDeserializationContext context, int weight, int quality, LootItemCondition[] conditions, LootItemFunction[] functions) {
            ResourceLocation resourceLocation = new ResourceLocation(GsonHelper.getAsString(object, "name"));
            ResourceKey<Item> tagKey = ResourceKey.create(Registries.ITEM, resourceLocation);
            boolean bl = GsonHelper.getAsBoolean(object, "expand");
            return new ResouceKeyEntry(tagKey, bl, weight, quality, conditions, functions);
        }
    }
}

