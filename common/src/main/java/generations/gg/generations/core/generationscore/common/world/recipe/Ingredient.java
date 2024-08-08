////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//package generations.gg.generations.core.generationscore.world.recipe;
//
//import com.google.common.collect.BiMap;
//import com.google.common.collect.ImmutableBiMap;
//import com.google.common.collect.Lists;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParseException;
//import com.google.gson.JsonSyntaxException;
//import com.mojang.datafixers.util.Either;
//import com.mojang.serialization.Codec;
//import it.unimi.dsi.fastutil.ints.IntArrayList;
//import it.unimi.dsi.fastutil.ints.IntComparators;
//import it.unimi.dsi.fastutil.ints.IntList;
//
//import java.util.*;
//import java.util.function.Function;
//import java.util.function.Predicate;
//import java.util.stream.Stream;
//import java.util.stream.StreamSupport;
//import net.minecraft.core.Holder;
//import net.minecraft.core.registries.BuiltInRegistries;
//import net.minecraft.core.registries.Registries;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.tags.TagKey;
//import net.minecraft.util.GsonHelper;
//import net.minecraft.world.entity.animal.Cod;
//import net.minecraft.world.entity.player.StackedContents;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.ShapedRecipe;
//import net.minecraft.world.level.ItemLike;
//import org.bson.types.Code;
//import org.jetbrains.annotations.Nullable;
//
//public final class Ingredient implements Predicate<ItemStack> {
//    public static final Ingredient EMPTY = new Ingredient(Stream.empty());
//    private final Value<?>[] values;
//    @Nullable
//    private ItemStack[] itemStacks;
//    @Nullable
//    private IntList stackingIds;
//
//    private Ingredient(Stream<? extends Value<?>> values) {
//        this.values = values.toArray(Value[]::new);
//    }
//
//    public ItemStack[] getItems() {
//        if (this.itemStacks == null) {
//            this.itemStacks = Arrays.stream(this.values).flatMap((value) -> value.getItems().stream()).distinct().toArray(ItemStack[]::new);
//        }
//
//        return this.itemStacks;
//    }
//
//    public boolean test(@Nullable ItemStack stack) {
//        if (stack == null) {
//            return false;
//        } else if (this.isEmpty()) {
//            return stack.isEmpty();
//        } else {
//            for (ItemStack itemStack : this.getItems()) {
//                if (itemStack.is(stack.getItem())) {
//                    return true;
//                }
//            }
//
//            return false;
//        }
//    }
//
//    public IntList getStackingIds() {
//        if (this.stackingIds == null) {
//            ItemStack[] itemStacks = this.getItems();
//            this.stackingIds = new IntArrayList(itemStacks.length);
//
//            for (ItemStack itemStack : getItems()) {
//                this.stackingIds.add(StackedContents.getStackingIndex(itemStack));
//            }
//
//            this.stackingIds.sort(IntComparators.NATURAL_COMPARATOR);
//        }
//
//        return this.stackingIds;
//    }
//
//    public void toNetwork(FriendlyByteBuf buffer) {
//        buffer.writeCollection(Arrays.asList(this.getItems()), FriendlyByteBuf::writeItem);
//    }
//
//    public JsonElement toJson() {
//        if (this.values.length == 1) {
//            return this.values[0].serialize();
//        } else {
//            JsonArray jsonArray = new JsonArray();
//            Value<?>[] var2 = this.values;
//            int var3 = var2.length;
//
//            for (Value<?> value : var2) {
//                jsonArray.add(value.serialize());
//            }
//
//            return jsonArray;
//        }
//    }
//
//    public boolean isEmpty() {
//        return this.values.length == 0;
//    }
//
//    private static Ingredient fromValues(Stream<? extends Value<?>> stream) {
//        Ingredient ingredient = new Ingredient(stream);
//        return ingredient.isEmpty() ? EMPTY : ingredient;
//    }
//
//    public static Ingredient of() {
//        return EMPTY;
//    }
//
//    public static Ingredient of(ItemLike... items) {
//        return of(Arrays.stream(items).map(ItemStack::new));
//    }
//
//    public static Ingredient of(ItemStack... stacks) {
//        return of(Arrays.stream(stacks));
//    }
//
//    public static Ingredient of(Stream<ItemStack> stacks) {
//        return fromValues(stacks.filter((itemStack) -> {
//            return !itemStack.isEmpty();
//        }).map(ItemValue::new));
//    }
//
//    public static Ingredient of(TagKey<Item> tag) {
//        return fromValues(Stream.of(new TagValue(tag)));
//    }
//
//    public static Ingredient fromNetwork(FriendlyByteBuf buffer) {
//        return fromValues(buffer.readList(FriendlyByteBuf::readItem).stream().map(ItemValue::new));
//    }
//
//    public static Ingredient fromJson(@Nullable JsonElement json) {
//        return fromJson(json, true);
//    }
//
//    public static final Codec<Ingredient> CODEC = Codec.either(Value.CODEC, Value.CODEC.listOf()).<List<Value<?>>>xmap(a -> a.map(List::of, Function.identity()), values -> values.size() > 1 ? Either.left(values.get(0)) : Either.right(values)).xmap(Collection::stream, Stream::toList).xmap(Ingredient::new, ingredient -> Arrays.stream(ingredient.values));
//
//    public static Ingredient fromJson(@Nullable JsonElement json, boolean canBeEmpty) {
//
//
//        if (json != null && !json.isJsonNull()) {
//            if (json.isJsonObject()) {
//                return fromValues(Stream.of(valueFromJson(json.getAsJsonObject())));
//            } else if (json.isJsonArray()) {
//                JsonArray jsonArray = json.getAsJsonArray();
//                if (jsonArray.size() == 0 && !canBeEmpty) {
//                    throw new JsonSyntaxException("Item array cannot be empty, at least one item must be defined");
//                } else {
//                    return fromValues(StreamSupport.stream(jsonArray.spliterator(), false).map((jsonElement) -> {
//                        return valueFromJson(GsonHelper.convertToJsonObject(jsonElement, "item"));
//                    }));
//                }
//            } else {
//                throw new JsonSyntaxException("Expected item to be object or array of objects");
//            }
//        } else {
//            throw new JsonSyntaxException("Item cannot be null");
//        }
//    }
//
//    private static Value valueFromJson(JsonObject json) {
//        if (json.has("item") && json.has("tag")) {
//            throw new JsonParseException("An ingredient entry is either a tag or an item, not both");
//        } else if (json.has("item")) {
//            Item item = ShapedRecipe.itemFromJson(json);
//            return new ItemValue(new ItemStack(item));
//        } else if (json.has("tag")) {
//            ResourceLocation resourceLocation = new ResourceLocation(GsonHelper.getAsString(json, "tag"));
//            TagKey<Item> tagKey = TagKey.create(Registries.ITEM, resourceLocation);
//            return new TagValue(tagKey);
//        } else {
//            throw new JsonParseException("An ingredient entry needs either a tag or an item");
//        }
//    }
//
//    interface Value<T extends Value<T>> {
//        Codec<Value<?>> CODEC = Codec.STRING.dispatch(value -> ValueType.VALUE_ID.get(value.key()), s -> ValueType.ID_VALUE.get(s).codec());
//        Collection<ItemStack> getItems();
//
//        ValueType<?> key();
//
//        JsonObject serialize();
//
//        record ValueType<T extends Value<T>>(Codec<T> codec) {
//            public static Map<String, ValueType<?>> ID_VALUE = new HashMap<>();
//            public static Map<ValueType<?>, String> VALUE_ID = new HashMap<>();
//
//            public static ValueType<ItemValue> ITEM = register("item", new ValueType<>(ItemValue.CODEC));
//            public static ValueType<TagValue> TAG = register("tag", new ValueType<>(TagValue.CODEC));
//
//            public static <T extends Value<T>> ValueType<T> register(String id, ValueType<T> type) {
//                ID_VALUE.put(id, type);
//                VALUE_ID.put(type, id);
//                return type;
//            }
//        }
//    }
//
//    public record TagValue(TagKey<Item> tag) implements Value<TagValue> {
//        public static final Codec<TagValue> CODEC = TagKey.codec(Registries.ITEM).xmap(TagValue::new, TagValue::tag);
//        public Collection<ItemStack> getItems() {
//            List<ItemStack> list = Lists.newArrayList();
//
//            for (Holder<Item> itemHolder : BuiltInRegistries.ITEM.getTagOrEmpty(this.tag)) {
//                list.add(new ItemStack(itemHolder));
//            }
//
//            return list;
//        }
//
//        @Override
//        public ValueType<?> key() {
//            return ValueType.TAG;
//        }
//
//        public JsonObject serialize() {
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("tag", this.tag.location().toString());
//            return jsonObject;
//        }
//    }
//
//    public record ItemValue(ItemStack item) implements Value<ItemValue> {
//        public static Codec<ItemValue> CODEC = Codec.either(BuiltInRegistries.ITEM.byNameCodec(), ItemStack.CODEC).xmap(a -> a.map(Item::getDefaultInstance, b -> b), b -> b.getCount() > 1 && b.hasTag() ? Either.right(b) : Either.left(b.getItem())).xmap(ItemValue::new, ItemValue::item);
//
//        public Collection<ItemStack> getItems() {
//            return Collections.singleton(this.item);
//        }
//
//        @Override
//        public ValueType<?> key() {
//            return ValueType.ITEM;
//        }
//
//        public JsonObject serialize() {
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("item", BuiltInRegistries.ITEM.getKey(this.item.getItem()).toString());
//            return jsonObject;
//        }
//    }
//}
