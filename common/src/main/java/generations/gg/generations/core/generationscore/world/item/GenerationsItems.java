package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.pokemon.Species;
import com.google.common.collect.Streams;
import dev.architectury.core.item.ArchitecturyRecordItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.entity.GenerationsBoatEntity;
import generations.gg.generations.core.generationscore.world.entity.GenerationsChestBoatEntity;
import generations.gg.generations.core.generationscore.world.entity.TieredFishingHookEntity;
import generations.gg.generations.core.generationscore.world.entity.block.MagmaCrystalEntity;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.item.creativetab.GenerationsCreativeTabs;
import generations.gg.generations.core.generationscore.world.item.curry.CurryIngredient;
import generations.gg.generations.core.generationscore.world.item.curry.CurryType;
import generations.gg.generations.core.generationscore.world.item.curry.ItemCurry;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import generations.gg.generations.core.generationscore.world.sound.GenerationsSounds;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * Generations Items
 * @see net.minecraft.world.item.Item
 * @author J.T. McQuigg
 * @author WaterPicker
 */
@SuppressWarnings("unused")
public class GenerationsItems {

    /** Generations Items Deferred Register */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);

    /** Generations Ribbons Deferred Register */
    public static final DeferredRegister<Item> RIBBONS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);

    /** Generations Badges Deferred Register */
    public static final DeferredRegister<Item> BADGES = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);

    /** Generations Pokeballs Deferred Register */
    public static final DeferredRegister<Item> POKEBALLS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);

    /**
     * Pokeballs
     */
    public static final RegistrySupplier<Item> POKE_BALL = registerPokeBall("poke_ball", Item::new); //createPokeball(PokeBall.POKE_BALL);
    public static final RegistrySupplier<Item> ULTRA_BALL = registerPokeBall("ultra_ball", Item::new); //createPokeball(PokeBall.ULTRA_BALL);
    public static final RegistrySupplier<Item> GREAT_BALL = registerPokeBall("great_ball", Item::new); //createPokeball(PokeBall.GREAT_BALL);
    public static final RegistrySupplier<Item> MASTER_BALL = registerPokeBall("master_ball", Item::new); //createPokeball(PokeBall.MASTER_BALL);
    public static final RegistrySupplier<Item> LEVEL_BALL = registerPokeBall("level_ball", Item::new); //createPokeball(PokeBall.LEVEL_BALL);
    public static final RegistrySupplier<Item> MOON_BALL = registerPokeBall("moon_ball", Item::new); //createPokeball(PokeBall.MOON_BALL);
    public static final RegistrySupplier<Item> FRIEND_BALL = registerPokeBall("friend_ball", Item::new); //createPokeball(PokeBall.FRIEND_BALL);
    public static final RegistrySupplier<Item> LOVE_BALL = registerPokeBall("love_ball", Item::new); //createPokeball(PokeBall.LOVE_BALL);
    public static final RegistrySupplier<Item> SAFARI_BALL = registerPokeBall("safari_ball", Item::new); //createPokeball(PokeBall.SAFARI_BALL);
    public static final RegistrySupplier<Item> HEAVY_BALL = registerPokeBall("heavy_ball", Item::new); //createPokeball(PokeBall.HEAVY_BALL);
    public static final RegistrySupplier<Item> FAST_BALL = registerPokeBall("fast_ball", Item::new); //createPokeball(PokeBall.FAST_BALL);
    public static final RegistrySupplier<Item> REPEAT_BALL = registerPokeBall("repeat_ball", Item::new); //createPokeball(PokeBall.REPEAT_BALL);
    public static final RegistrySupplier<Item> TIMER_BALL = registerPokeBall("timer_ball", Item::new); //createPokeball(PokeBall.TIMER_BALL);
    public static final RegistrySupplier<Item> NEST_BALL = registerPokeBall("nest_ball", Item::new); //createPokeball(PokeBall.NEST_BALL);
    public static final RegistrySupplier<Item> NET_BALL = registerPokeBall("net_ball", Item::new); //createPokeball(PokeBall.NET_BALL);
    public static final RegistrySupplier<Item> DIVE_BALL = registerPokeBall("dive_ball", Item::new); //createPokeball(PokeBall.DIVE_BALL);
    public static final RegistrySupplier<Item> LUXURY_BALL = registerPokeBall("luxury_ball", Item::new); //createPokeball(PokeBall.LUXURY_BALL);
    public static final RegistrySupplier<Item> HEAL_BALL = registerPokeBall("heal_ball", Item::new); //createPokeball(PokeBall.HEAL_BALL);
    public static final RegistrySupplier<Item> DUSK_BALL = registerPokeBall("dusk_ball", Item::new); //createPokeball(PokeBall.DUSK_BALL);
    public static final RegistrySupplier<Item> PREMIER_BALL = registerPokeBall("premier_ball", Item::new); //createPokeball(PokeBall.PREMIER_BALL);
    public static final RegistrySupplier<Item> SPORT_BALL = registerPokeBall("sport_ball", Item::new); //createPokeball(PokeBall.SPORT_BALL);
    public static final RegistrySupplier<Item> PARK_BALL = registerPokeBall("park_ball", Item::new); //createPokeball(PokeBall.PARK_BALL);
    public static final RegistrySupplier<Item> QUICK_BALL = registerPokeBall("quick_ball", Item::new); //createPokeball(PokeBall.QUICK_BALL);
    public static final RegistrySupplier<Item> LURE_BALL = registerPokeBall("lure_ball", Item::new); //createPokeball(PokeBall.LURE_BALL);
    public static final RegistrySupplier<Item> CHERISH_BALL = registerPokeBall("cherish_ball", Item::new); //createPokeball(PokeBall.CHERISH_BALL);
    public static final RegistrySupplier<Item> GS_BALL = registerPokeBall("gs_ball", Item::new); //createPokeball(PokeBall.GS_BALL);
    public static final RegistrySupplier<Item> BEAST_BALL = registerPokeBall("beast_ball", Item::new); //createPokeball(PokeBall.BEAST_BALL);
    public static final RegistrySupplier<Item> DREAM_BALL = registerPokeBall("dream_ball", Item::new); //createPokeball(PokeBall.DREAM_BALL);
    public static final RegistrySupplier<Item> LEADEN_BALL = registerPokeBall("leaden_ball", Item::new); //createPokeball(PokeBall.LEADEN_BALL);
    public static final RegistrySupplier<Item> WING_BALL = registerPokeBall("wing_ball", Item::new); //createPokeball(PokeBall.WING_BALL);
    public static final RegistrySupplier<Item> GIGATON_BALL = registerPokeBall("gigaton_ball", Item::new); //createPokeball(PokeBall.GIGATON_BALL);
    public static final RegistrySupplier<Item> FEATHER_BALL = registerPokeBall("feather_ball", Item::new); //createPokeball(PokeBall.FEATHER_BALL);
    public static final RegistrySupplier<Item> JET_BALL = registerPokeBall("jet_ball", Item::new); //createPokeball(PokeBall.JET_BALL);
    public static final RegistrySupplier<Item> STRANGE_BALL = registerPokeBall("strange_ball", Item::new);
    public static final RegistrySupplier<Item> ORIGIN_BALL = registerPokeBall("origin_ball", Item::new);


    /**
     * Restoration Items
     */
    public static final RegistrySupplier<Item> POTION = register("potion", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> SUPER_POTION = register("super_potion", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> HYPER_POTION = register("hyper_potion", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> MAX_POTION = register("max_potion", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> REVIVE = register("revive", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> MAX_REVIVE = register("max_revive", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> ETHER = register("ether", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> MAX_ETHER = register("max_ether", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> ELIXIR = register("elixir", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> MAX_ELIXIR = register("max_elixir", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> PP_UP = register("pp_up", PpUpItem::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> PP_MAX = register("pp_max", properties -> new PpUpItem(properties, true), GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> FULL_HEAL = register("full_heal", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> FULL_RESTORE = register("full_restore", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> ANTIDOTE = register("antidote", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> PARALYZE_HEAL = register("paralyze_heal", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> AWAKENING = register("awakening", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> BURN_HEAL = register("burn_heal", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> ICE_HEAL = register("ice_heal", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> HEALTH_FEATHER = register("health_feather", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> MUSCLE_FEATHER = register("muscle_feather", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> RESIST_FEATHER = register("resist_feather", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> GENIUS_FEATHER = register("genius_feather", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> CLEVER_FEATHER = register("clever_feather", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> SWIFT_FEATHER = register("swift_feather", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> X_ATTACK = register("x_attack", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> X_DEFENSE = register("x_defense", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> X_SPECIAL_ATTACK = register("x_special_attack", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> X_SPECIAL_DEFENSE = register("x_special_defense", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> X_SPEED = register("x_speed", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> X_ACCURACY = register("x_accuracy", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> DIRE_HIT = register("dire_hit", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> GUARD_SPEC = register("guard_spec", Item::new, GenerationsCreativeTabs.RESTORATION);

    public static final RegistrySupplier<Item> PURPLE_JUICE = register("purple_juice", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> RED_JUICE = register("red_juice", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> YELLOW_JUICE = register("yellow_juice", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> BLUE_JUICE = register("blue_juice", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> GREEN_JUICE = register("green_juice", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> PINK_JUICE = register("pink_juice", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> COLORFUL_SHAKE = register("colorful_shake", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> PERILOUS_SOUP = register("perilous_soup", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> RARE_SODA = register("rare_soda", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> ULTRA_RARE_SODA = register("ultra_rare_soda", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> FRESH_WATER = register("fresh_water", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> SODA_POP = register("soda_pop", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> LEMONADE = register("lemonade", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> MOOMOO_MILK = register("moomoo_milk", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> LUMIOSE_GALETTE = register("lumiose_galette", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> SHALOUR_SABLE = register("shalour_sable", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> CASTELIACONE = register("casteliacone", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> OLD_GATEAU = register("old_gateau", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> BIG_MALASADA = register("big_malasada", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> LAVA_COOKIE = register("lava_cookie", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> RAGE_CANDY_BAR = register("rage_candy_bar", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> BLUE_FLUTE = register("blue_flute", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> RED_FLUTE = register("red_flute", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> YELLOW_FLUTE = register("yellow_flute", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> HEAL_POWDER = register("heal_powder", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> ENERGY_POWDER = register("energy_powder", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> ENERGY_ROOT = register("energy_root", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> REVIVAL_HERB = register("revival_herb", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> SWEET_HEART = register("sweet_heart", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> BASIC_SWEET_POKE_PUFF = register("basic_sweet_poke_puff", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> BASIC_CITRUS_POKE_PUFF = register("basic_citrus_poke_puff", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> BASIC_MINT_POKE_PUFF = register("basic_mint_poke_puff", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> BASIC_MOCHA_POKE_PUFF = register("basic_mocha_poke_puff", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> BASIC_SPICE_POKE_PUFF = register("basic_spice_poke_puff", Item::new, GenerationsCreativeTabs.RESTORATION);

    /**
     * Consumable Items
     */
    public static final RegistrySupplier<Item> DYNAMAX_CANDY = register("dynamax_candy", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> MAX_HONEY = register("max_honey", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> MAX_MUSHROOMS = register("max_mushrooms", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> MAX_POWDER = register("max_powder", Item::new, GenerationsCreativeTabs.RESTORATION);
    public static final RegistrySupplier<Item> MAX_SOUP = register("max_soup", Item::new, GenerationsCreativeTabs.RESTORATION);

    /**
     * TM ITEMS
     */
    public static final RegistrySupplier<Item> CUSTOM_TM = register("tm", CustomTechnicalMachineItem::new, null);


    public static final RegistrySupplier<Item> TM_1 = register("tm_1", properties -> new TechnicalMachineItem("takedown", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_2 = register("tm_2", properties -> new TechnicalMachineItem("charm", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_3 = register("tm_3", properties -> new TechnicalMachineItem("faketears", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_4 = register("tm_4", properties -> new TechnicalMachineItem("agility", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_5 = register("tm_5", properties -> new TechnicalMachineItem("mudslap", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_6 = register("tm_6", properties -> new TechnicalMachineItem("scaryface", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_7 = register("tm_7", properties -> new TechnicalMachineItem("protect", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_8 = register("tm_8", properties -> new TechnicalMachineItem("firefang", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_9 = register("tm_9", properties -> new TechnicalMachineItem("thunderfang", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_10 = register("tm_10", properties -> new TechnicalMachineItem("icefang", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_11 = register("tm_11", properties -> new TechnicalMachineItem("waterpulse", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_12 = register("tm_12", properties -> new TechnicalMachineItem("lowkick", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_13 = register("tm_13", properties -> new TechnicalMachineItem("acidspray", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_14 = register("tm_14", properties -> new TechnicalMachineItem("acrobatics", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_15 = register("tm_15", properties -> new TechnicalMachineItem("strugglebug", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_16 = register("tm_16", properties -> new TechnicalMachineItem("psybeam", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_17 = register("tm_17", properties -> new TechnicalMachineItem("confuseray", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_18 = register("tm_18", properties -> new TechnicalMachineItem("thief", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_19 = register("tm_19", properties -> new TechnicalMachineItem("disarmingvoice", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_20 = register("tm_20", properties -> new TechnicalMachineItem("trailblaze", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_21 = register("tm_21", properties -> new TechnicalMachineItem("pounce", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_22 = register("tm_22", properties -> new TechnicalMachineItem("chillingwater", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_23 = register("tm_23", properties -> new TechnicalMachineItem("chargebeam", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_24 = register("tm_24", properties -> new TechnicalMachineItem("firespin", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_25 = register("tm_25", properties -> new TechnicalMachineItem("facade", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_26 = register("tm_26", properties -> new TechnicalMachineItem("poisontail", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_27 = register("tm_27", properties -> new TechnicalMachineItem("aerialace", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_28 = register("tm_28", properties -> new TechnicalMachineItem("bulldoze", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_29 = register("tm_29", properties -> new TechnicalMachineItem("hex", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_30 = register("tm_30", properties -> new TechnicalMachineItem("snarl", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_31 = register("tm_31", properties -> new TechnicalMachineItem("metalclaw", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_32 = register("tm_32", properties -> new TechnicalMachineItem("switft", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_33 = register("tm_33", properties -> new TechnicalMachineItem("magicalleaf", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_34 = register("tm_34", properties -> new TechnicalMachineItem("icywind", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_35 = register("tm_35", properties -> new TechnicalMachineItem("mudshot", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_36 = register("tm_36", properties -> new TechnicalMachineItem("rocktomb", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_37 = register("tm_37", properties -> new TechnicalMachineItem("drainingkiss", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_38 = register("tm_38", properties -> new TechnicalMachineItem("flamecharge", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_39 = register("tm_39", properties -> new TechnicalMachineItem("lowsweep", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_40 = register("tm_40", properties -> new TechnicalMachineItem("aircutter", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_41 = register("tm_41", properties -> new TechnicalMachineItem("storedpower", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_42 = register("tm_42", properties -> new TechnicalMachineItem("nightshade", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_43 = register("tm_43", properties -> new TechnicalMachineItem("fling", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_44 = register("tm_44", properties -> new TechnicalMachineItem("dragontail", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_45 = register("tm_45", properties -> new TechnicalMachineItem("venoshock", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_46 = register("tm_46", properties -> new TechnicalMachineItem("avalanche", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_47 = register("tm_47", properties -> new TechnicalMachineItem("endure", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_48 = register("tm_48", properties -> new TechnicalMachineItem("voltswitch", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_49 = register("tm_49", properties -> new TechnicalMachineItem("sunnyday", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_50 = register("tm_50", properties -> new TechnicalMachineItem("raindance", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_51 = register("tm_51", properties -> new TechnicalMachineItem("sandstorm", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_52 = register("tm_52", properties -> new TechnicalMachineItem("snowscape", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_53 = register("tm_53", properties -> new TechnicalMachineItem("smartstrike", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_54 = register("tm_54", properties -> new TechnicalMachineItem("psyshock", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_55 = register("tm_55", properties -> new TechnicalMachineItem("dig", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_56 = register("tm_56", properties -> new TechnicalMachineItem("bulletseed", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_57 = register("tm_57", properties -> new TechnicalMachineItem("falseswipe", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_58 = register("tm_58", properties -> new TechnicalMachineItem("brickbreak", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_59 = register("tm_59", properties -> new TechnicalMachineItem("zenheadbutt", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_60 = register("tm_60", properties -> new TechnicalMachineItem("uturn", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_61 = register("tm_61", properties -> new TechnicalMachineItem("shadowclaw", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_62 = register("tm_62", properties -> new TechnicalMachineItem("foulplay", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_63 = register("tm_63", properties -> new TechnicalMachineItem("psychicfangs", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_64 = register("tm_64", properties -> new TechnicalMachineItem("bulkup", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_65 = register("tm_65", properties -> new TechnicalMachineItem("airslash", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_66 = register("tm_66", properties -> new TechnicalMachineItem("bodyslam", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_67 = register("tm_67", properties -> new TechnicalMachineItem("firepunch", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_68 = register("tm_68", properties -> new TechnicalMachineItem("thunderpunch", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_69 = register("tm_69", properties -> new TechnicalMachineItem("icepunch", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_70 = register("tm_70", properties -> new TechnicalMachineItem("sleeptalk", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_71 = register("tm_71", properties -> new TechnicalMachineItem("seedbomb", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_72 = register("tm_72", properties -> new TechnicalMachineItem("electroball", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_73 = register("tm_73", properties -> new TechnicalMachineItem("drainpunch", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_74 = register("tm_74", properties -> new TechnicalMachineItem("reflect", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_75 = register("tm_75", properties -> new TechnicalMachineItem("lightscreen", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_76 = register("tm_76", properties -> new TechnicalMachineItem("rockblast", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_77 = register("tm_77", properties -> new TechnicalMachineItem("waterfall", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_78 = register("tm_78", properties -> new TechnicalMachineItem("dragonclaw", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_79 = register("tm_79", properties -> new TechnicalMachineItem("dazzlinggleam", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_80 = register("tm_80", properties -> new TechnicalMachineItem("metronome", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_81 = register("tm_81", properties -> new TechnicalMachineItem("grassknot", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_82 = register("tm_82", properties -> new TechnicalMachineItem("thunderwave", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_83 = register("tm_83", properties -> new TechnicalMachineItem("poisonjab", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_84 = register("tm_84", properties -> new TechnicalMachineItem("stompingtantrum", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_85 = register("tm_85", properties -> new TechnicalMachineItem("rest", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_86 = register("tm_86", properties -> new TechnicalMachineItem("rockslide", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_87 = register("tm_87", properties -> new TechnicalMachineItem("taunt", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_88 = register("tm_88", properties -> new TechnicalMachineItem("swordsdance", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_89 = register("tm_89", properties -> new TechnicalMachineItem("bodypress", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_90 = register("tm_90", properties -> new TechnicalMachineItem("spikes", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_91 = register("tm_91", properties -> new TechnicalMachineItem("toxicspikes", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_92 = register("tm_92", properties -> new TechnicalMachineItem("imprison", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_93 = register("tm_93", properties -> new TechnicalMachineItem("flashcannon", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_94 = register("tm_94", properties -> new TechnicalMachineItem("darkpulse", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_95 = register("tm_95", properties -> new TechnicalMachineItem("leechlife", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_96 = register("tm_96", properties -> new TechnicalMachineItem("eerieimpulse", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_97 = register("tm_97", properties -> new TechnicalMachineItem("fly", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_98 = register("tm_98", properties -> new TechnicalMachineItem("skillswap", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_99 = register("tm_99", properties -> new TechnicalMachineItem("ironhead", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_100 = register("tm_100", properties -> new TechnicalMachineItem("dragondance", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_101 = register("tm_101", properties -> new TechnicalMachineItem("powergem", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_102 = register("tm_102", properties -> new TechnicalMachineItem("gunkshot", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_103 = register("tm_103", properties -> new TechnicalMachineItem("substitue", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_104 = register("tm_104", properties -> new TechnicalMachineItem("irondefense", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_105 = register("tm_105", properties -> new TechnicalMachineItem("xscissor", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_106 = register("tm_106", properties -> new TechnicalMachineItem("drillrun", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_107 = register("tm_107", properties -> new TechnicalMachineItem("willowisp", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_108 = register("tm_108", properties -> new TechnicalMachineItem("crunch", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_109 = register("tm_109", properties -> new TechnicalMachineItem("trick", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_110 = register("tm_110", properties -> new TechnicalMachineItem("liquidation", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_111 = register("tm_111", properties -> new TechnicalMachineItem("gigadrain", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_112 = register("tm_112", properties -> new TechnicalMachineItem("aurasphere", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_113 = register("tm_113", properties -> new TechnicalMachineItem("tailwind", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_114 = register("tm_114", properties -> new TechnicalMachineItem("shadowball", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_115 = register("tm_115", properties -> new TechnicalMachineItem("dragonpulse", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_116 = register("tm_116", properties -> new TechnicalMachineItem("stealthrock", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_117 = register("tm_117", properties -> new TechnicalMachineItem("hypervoice", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_118 = register("tm_118", properties -> new TechnicalMachineItem("heatwave", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_119 = register("tm_119", properties -> new TechnicalMachineItem("energyball", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_120 = register("tm_120", properties -> new TechnicalMachineItem("psychic", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_121 = register("tm_121", properties -> new TechnicalMachineItem("heavyslam", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_122 = register("tm_122", properties -> new TechnicalMachineItem("encore", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_123 = register("tm_123", properties -> new TechnicalMachineItem("surf", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_124 = register("tm_124", properties -> new TechnicalMachineItem("icespinner", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_125 = register("tm_125", properties -> new TechnicalMachineItem("flamethrower", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_126 = register("tm_126", properties -> new TechnicalMachineItem("thunderbolt", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_127 = register("tm_127", properties -> new TechnicalMachineItem("playrough", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_128 = register("tm_128", properties -> new TechnicalMachineItem("amnesia", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_129 = register("tm_129", properties -> new TechnicalMachineItem("calmmind", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_130 = register("tm_130", properties -> new TechnicalMachineItem("helpinghand", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_131 = register("tm_131", properties -> new TechnicalMachineItem("pollenpuff", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_132 = register("tm_132", properties -> new TechnicalMachineItem("batonpass", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_133 = register("tm_133", properties -> new TechnicalMachineItem("earthpower", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_134 = register("tm_134", properties -> new TechnicalMachineItem("reversal", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_135 = register("tm_135", properties -> new TechnicalMachineItem("icebeam", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_136 = register("tm_136", properties -> new TechnicalMachineItem("electricterrain", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_137 = register("tm_137", properties -> new TechnicalMachineItem("grassterrain", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_138 = register("tm_138", properties -> new TechnicalMachineItem("psychicterrain", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_139 = register("tm_139", properties -> new TechnicalMachineItem("mistyterrain", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_140 = register("tm_140", properties -> new TechnicalMachineItem("nastyplot", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_141 = register("tm_141", properties -> new TechnicalMachineItem("fireblast", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_142 = register("tm_142", properties -> new TechnicalMachineItem("hydropump", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_143 = register("tm_143", properties -> new TechnicalMachineItem("blizzard", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_144 = register("tm_144", properties -> new TechnicalMachineItem("firepledge", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_145 = register("tm_145", properties -> new TechnicalMachineItem("waterpledge", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_146 = register("tm_146", properties -> new TechnicalMachineItem("grasspledge", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_147 = register("tm_147", properties -> new TechnicalMachineItem("wildcharge", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_148 = register("tm_148", properties -> new TechnicalMachineItem("sludgebomb", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_149 = register("tm_149", properties -> new TechnicalMachineItem("earthquake", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_150 = register("tm_150", properties -> new TechnicalMachineItem("stoneedge", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_151 = register("tm_151", properties -> new TechnicalMachineItem("phantomforce", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_152 = register("tm_152", properties -> new TechnicalMachineItem("gigaimpact", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_153 = register("tm_153", properties -> new TechnicalMachineItem("blastburn", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_154 = register("tm_154", properties -> new TechnicalMachineItem("hydrocannon", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_155 = register("tm_155", properties -> new TechnicalMachineItem("frenzyplant", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_156 = register("tm_156", properties -> new TechnicalMachineItem("outrage", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_157 = register("tm_157", properties -> new TechnicalMachineItem("overheat", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_158 = register("tm_158", properties -> new TechnicalMachineItem("focusblast", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_159 = register("tm_159", properties -> new TechnicalMachineItem("leafstorm", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_160 = register("tm_160", properties -> new TechnicalMachineItem("hurricane", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_161 = register("tm_161", properties -> new TechnicalMachineItem("trickroom", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_162 = register("tm_162", properties -> new TechnicalMachineItem("bugbuzz", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_163 = register("tm_163", properties -> new TechnicalMachineItem("hyperbeam", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_164 = register("tm_164", properties -> new TechnicalMachineItem("bravebird", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_165 = register("tm_165", properties -> new TechnicalMachineItem("flareblitz", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_166 = register("tm_166", properties -> new TechnicalMachineItem("thunder", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_167 = register("tm_167", properties -> new TechnicalMachineItem("closecombat", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_168 = register("tm_168", properties -> new TechnicalMachineItem("solarbeam", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_169 = register("tm_169", properties -> new TechnicalMachineItem("dracometeor", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_170 = register("tm_170", properties -> new TechnicalMachineItem("steelbeam", properties), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> TM_171 = register("tm_171", properties -> new TechnicalMachineItem("terablast", properties), GenerationsCreativeTabs.PLAYER_ITEMS);


    /**
     * Badges
     */
    public static final RegistrySupplier<Item> BALANCE_BADGE = createBadge("balance_badge", null);
    public static final RegistrySupplier<Item> BEACON_BADGE = createBadge("beacon_badge", null);
    public static final RegistrySupplier<Item> BOULDER_BADGE = createBadge("boulder_badge", null);
    public static final RegistrySupplier<Item> CASCADE_BADGE = createBadge("cascade_badge", null);
    public static final RegistrySupplier<Item> COAL_BADGE = createBadge("coal_badge", null);
    public static final RegistrySupplier<Item> COBBLE_BADGE = createBadge("cobble_badge", null);
    public static final RegistrySupplier<Item> DYNAMO_BADGE = createBadge("dynamo_badge", null);
    public static final RegistrySupplier<Item> EARTH_BADGE = createBadge("earth_badge", null);
    public static final RegistrySupplier<Item> FEATHER_BADGE = createBadge("feather_badge", null);
    public static final RegistrySupplier<Item> FEN_BADGE = createBadge("fen_badge", null);
    public static final RegistrySupplier<Item> FOG_BADGE = createBadge("fog_badge", null);
    public static final RegistrySupplier<Item> FOREST_BADGE = createBadge("forest_badge", null);
    public static final RegistrySupplier<Item> GLACIER_BADGE = createBadge("glacier_badge", null);
    public static final RegistrySupplier<Item> HEAT_BADGE = createBadge("heat_badge", null);
    public static final RegistrySupplier<Item> HIVE_BADGE = createBadge("hive_badge", null);
    public static final RegistrySupplier<Item> ICICLE_BADGE = createBadge("icicle_badge", null);
    public static final RegistrySupplier<Item> KNUCKLE_BADGE = createBadge("knuckle_badge", null);
    public static final RegistrySupplier<Item> MARSH_BADGE = createBadge("marsh_badge", null);
    public static final RegistrySupplier<Item> MIND_BADGE = createBadge("mind_badge", null);
    public static final RegistrySupplier<Item> MINE_BADGE = createBadge("mine_badge", null);
    public static final RegistrySupplier<Item> MINERAL_BADGE = createBadge("mineral_badge", null);
    public static final RegistrySupplier<Item> PLAIN_BADGE = createBadge("plain_badge", null);
    public static final RegistrySupplier<Item> RAINBOW_BADGE = createBadge("rainbow_badge", null);
    public static final RegistrySupplier<Item> RAIN_BADGE = createBadge("rain_badge", null);
    public static final RegistrySupplier<Item> RELIC_BADGE = createBadge("relic_badge", null);
    public static final RegistrySupplier<Item> RISING_BADGE = createBadge("rising_badge", null);
    public static final RegistrySupplier<Item> SOUL_BADGE = createBadge("soul_badge", null);
    public static final RegistrySupplier<Item> STONE_BADGE = createBadge("stone_badge", null);
    public static final RegistrySupplier<Item> STORM_BADGE = createBadge("storm_badge", null);
    public static final RegistrySupplier<Item> THUNDER_BADGE = createBadge("thunder_badge", null);
    public static final RegistrySupplier<Item> VOLCANO_BADGE = createBadge("volcano_badge", null);
    public static final RegistrySupplier<Item> ZEPHYR_BADGE = createBadge("zephyr_badge", null);
    public static final RegistrySupplier<Item> BASIC_BADGE = createBadge("basic_badge", null);
    public static final RegistrySupplier<Item> BOLT_BADGE = createBadge("bolt_badge", null);
    public static final RegistrySupplier<Item> FREEZE_BADGE = createBadge("freeze_badge", null);
    public static final RegistrySupplier<Item> INSECT_BADGE = createBadge("insect_badge", null);
    public static final RegistrySupplier<Item> JET_BADGE = createBadge("jet_badge", null);
    public static final RegistrySupplier<Item> LEGEND_BADGE = createBadge("legend_badge", null);
    public static final RegistrySupplier<Item> QUAKE_BADGE = createBadge("quake_badge", null);
    public static final RegistrySupplier<Item> TOXIC_BADGE = createBadge("toxic_badge", null);
    public static final RegistrySupplier<Item> TRIO_BADGE = createBadge("trio_badge", null);
    public static final RegistrySupplier<Item> WAVE_BADGE = createBadge("wave_badge", null);
    public static final RegistrySupplier<Item> BUG_BADGE = createBadge("bug_badge", null);
    public static final RegistrySupplier<Item> CLIFF_BADGE = createBadge("cliff_badge", null);
    public static final RegistrySupplier<Item> RUMBLE_BADGE = createBadge("rumble_badge", null);
    public static final RegistrySupplier<Item> PLANT_BADGE = createBadge("plant_badge", null);
    public static final RegistrySupplier<Item> VOLTAGE_BADGE = createBadge("voltage_badge", null);
    public static final RegistrySupplier<Item> FAIRY_BADGE = createBadge("fairy_badge", null);
    public static final RegistrySupplier<Item> PSYCHIC_BADGE = createBadge("psychic_badge", null);
    public static final RegistrySupplier<Item> ICEBERG_BADGE = createBadge("iceberg_badge", null);
    public static final RegistrySupplier<Item> SPIKESHELL_BADGE = createBadge("spikeshell_badge", null);
    public static final RegistrySupplier<Item> SEARUBY_BADGE = createBadge("searuby_badge", null);
    public static final RegistrySupplier<Item> JADESTAR_BADGE = createBadge("jadestar_badge", null);
    public static final RegistrySupplier<Item> CORALEYE_BADGE = createBadge("coraleye_badge", null);
    public static final RegistrySupplier<Item> FREEDOM_BADGE = createBadge("freedom_badge", null);
    public static final RegistrySupplier<Item> HARMONY_BADGE = createBadge("harmony_badge", null);
    public static final RegistrySupplier<Item> PATIENCE_BADGE = createBadge("patience_badge", null);
    public static final RegistrySupplier<Item> PRIDE_BADGE = createBadge("pride_badge", null);
    public static final RegistrySupplier<Item> TRANQUILITY_BADGE = createBadge("tranquility_badge", null);
    public static final RegistrySupplier<Item> DARK_BADGE = createBadge("dark_badge", null);
    public static final RegistrySupplier<Item> DRAGON_BADGE = createBadge("dragon_badge", null);
    public static final RegistrySupplier<Item> FAIRY_BADGE_2 = createBadge("fairy_badge2", null);
    public static final RegistrySupplier<Item> FIRE_BADGE = createBadge("fire_badge", null);
    public static final RegistrySupplier<Item> GRASS_BADGE = createBadge("grass_badge", null);
    public static final RegistrySupplier<Item> ICE_BADGE = createBadge("ice_badge", null);
    public static final RegistrySupplier<Item> ROCK_BADGE = createBadge("rock_badge", null);
    public static final RegistrySupplier<Item> WATER_BADGE = createBadge("water_badge", null);
    public static final RegistrySupplier<Item> FIGHTING_BADGE = createBadge("fighting_badge", null);
    public static final RegistrySupplier<Item> GHOST_BADGE = createBadge("ghost_badge", null);
    public static final RegistrySupplier<Item> SHIELD_BADGE_COMPLETE = createBadge("shield_badge_complete", null);
    public static final RegistrySupplier<Item> SWORD_BADGE_COMPLETE = createBadge("sword_badge_complete", null);

    /**
     * Ribbons
     */
    public static final RegistrySupplier<Item> PALDEA_CHAMPION_RIBBON = createRibbon("paldea_champion_ribbon");
    public static final RegistrySupplier<Item> ABILITY_RIBBON = createRibbon("ability_ribbon");
    public static final RegistrySupplier<Item> ALERT_RIBBON = createRibbon("alert_ribbon");
    public static final RegistrySupplier<Item> ALOLA_CHAMPION_RIBBON = createRibbon("alola_champion_ribbon");
    public static final RegistrySupplier<Item> ARTIST_RIBBON = createRibbon("artist_ribbon");
    public static final RegistrySupplier<Item> BEAUTY_MASTER_RIBBON = createRibbon("beauty_master_ribbon");
    public static final RegistrySupplier<Item> BEAUTY_RIBBON_GREAT = createRibbon("beauty_ribbon_great");
    public static final RegistrySupplier<Item> BEAUTY_RIBBON_HOENN = createRibbon("beauty_ribbon_hoenn");
    public static final RegistrySupplier<Item> BEAUTY_RIBBON_HYPER = createRibbon("beauty_ribbon_hyper");
    public static final RegistrySupplier<Item> BEAUTY_RIBBON_MASTER_HOENN = createRibbon("beauty_ribbon_master_hoenn");
    public static final RegistrySupplier<Item> BEAUTY_RIBBON_MASTER_SINNOH = createRibbon("beauty_ribbon_master_sinnoh");
    public static final RegistrySupplier<Item> BEAUTY_RIBBON_SINNOH = createRibbon("beauty_ribbon_sinnoh");
    public static final RegistrySupplier<Item> BEAUTY_RIBBON_SUPER = createRibbon("beauty_ribbon_super");
    public static final RegistrySupplier<Item> BEAUTY_RIBBON_ULTRA = createRibbon("beauty_ribbon_ultra");
    public static final RegistrySupplier<Item> CARELESS_RIBBON = createRibbon("careless_ribbon");
    public static final RegistrySupplier<Item> CHAMPION_RIBBON = createRibbon("champion_ribbon");
    public static final RegistrySupplier<Item> CLEVERNESS_MASTER_RIBBON = createRibbon("cleverness_master_ribbon");
    public static final RegistrySupplier<Item> CONTEST_STAR_RIBBON = createRibbon("contest_star_ribbon");
    public static final RegistrySupplier<Item> COOL_RIBBON_GREAT = createRibbon("cool_ribbon_great");
    public static final RegistrySupplier<Item> COOL_RIBBON_HOENN = createRibbon("cool_ribbon_hoenn");
    public static final RegistrySupplier<Item> COOL_RIBBON_HYPER = createRibbon("cool_ribbon_hyper");
    public static final RegistrySupplier<Item> COOL_RIBBON_MASTER_HOENN = createRibbon("cool_ribbon_master_hoenn");
    public static final RegistrySupplier<Item> COOL_RIBBON_MASTER_SINNOH = createRibbon("cool_ribbon_master_sinnoh");
    public static final RegistrySupplier<Item> COOL_RIBBON_SINNOH = createRibbon("cool_ribbon_sinnoh");
    public static final RegistrySupplier<Item> COOL_RIBBON_SUPER = createRibbon("cool_ribbon_super");
    public static final RegistrySupplier<Item> COOL_RIBBON_ULTRA = createRibbon("cool_ribbon_ultra");
    public static final RegistrySupplier<Item> COOLNESS_MASTER_RIBBON = createRibbon("coolness_master_ribbon");
    public static final RegistrySupplier<Item> CUTE_RIBBON_GREAT = createRibbon("cute_ribbon_great");
    public static final RegistrySupplier<Item> CUTE_RIBBON_HOENN = createRibbon("cute_ribbon_hoenn");
    public static final RegistrySupplier<Item> CUTE_RIBBON_HYPER = createRibbon("cute_ribbon_hyper");
    public static final RegistrySupplier<Item> CUTE_RIBBON_MASTER_HOENN = createRibbon("cute_ribbon_master_hoenn");
    public static final RegistrySupplier<Item> CUTE_RIBBON_MASTER_SINNOH = createRibbon("cute_ribbon_master_sinnoh");
    public static final RegistrySupplier<Item> CUTE_RIBBON_SINNOH = createRibbon("cute_ribbon_sinnoh");
    public static final RegistrySupplier<Item> CUTE_RIBBON_SUPER = createRibbon("cute_ribbon_super");
    public static final RegistrySupplier<Item> CUTE_RIBBON_ULTRA = createRibbon("cute_ribbon_ultra");
    public static final RegistrySupplier<Item> CUTENESS_MASTER_RIBBON = createRibbon("cuteness_master_ribbon");
    public static final RegistrySupplier<Item> DOUBLE_ABILITY_RIBBON = createRibbon("double_ability_ribbon");
    public static final RegistrySupplier<Item> DOWNCAST_RIBBON = createRibbon("downcast_ribbon");
    public static final RegistrySupplier<Item> EARTH_RIBBON = createRibbon("earth_ribbon");
    public static final RegistrySupplier<Item> EFFORT_RIBBON = createRibbon("effort_ribbon");
    public static final RegistrySupplier<Item> EXPERT_BATTLER_RIBBON = createRibbon("expert_battler_ribbon");
    public static final RegistrySupplier<Item> BATTLE_TREE_GREAT_RIBBON = createRibbon("battle_tree_great_ribbon");
    public static final RegistrySupplier<Item> BATTLE_TREE_MASTER_RIBBON = createRibbon("battle_tree_master_ribbon");
    public static final RegistrySupplier<Item> TOWER_MASTER_RIBBON = createRibbon("tower_master_ribbon");
    public static final RegistrySupplier<Item> FOOTPRINT_RIBBON = createRibbon("footprint_ribbon");
    public static final RegistrySupplier<Item> RECORD_RIBBON = createRibbon("record_ribbon");
    public static final RegistrySupplier<Item> BEST_FRIENDS_RIBBON = createRibbon("best_friends_ribbon");
    public static final RegistrySupplier<Item> TRAINING_RIBBON = createRibbon("training_ribbon");
    public static final RegistrySupplier<Item> BATTLE_ROYALE_MASTER_RIBBON = createRibbon("battle_royale_master_ribbon");
    public static final RegistrySupplier<Item> MASTER_RANK_RIBBON = createRibbon("master_rank_ribbon");
    public static final RegistrySupplier<Item> PIONEER_RIBBON = createRibbon("pioneer_ribbon");
    public static final RegistrySupplier<Item> COUNTRY_RIBBON = createRibbon("country_ribbon");
    public static final RegistrySupplier<Item> WORLD_RIBBON = createRibbon("world_ribbon");
    public static final RegistrySupplier<Item> CLASSIC_RIBBON = createRibbon("classic_ribbon");
    public static final RegistrySupplier<Item> PREMIER_RIBBON = createRibbon("premier_ribbon");
    public static final RegistrySupplier<Item> EVENT_RIBBON = createRibbon("event_ribbon");
    public static final RegistrySupplier<Item> BIRTHDAY_RIBBON = createRibbon("birthday_ribbon");
    public static final RegistrySupplier<Item> SPECIAL_RIBBON = createRibbon("special_ribbon");
    public static final RegistrySupplier<Item> SOUVENIR_RIBBON = createRibbon("souvenir_ribbon");
    public static final RegistrySupplier<Item> WISHING_RIBBON = createRibbon("wishing_ribbon");
    public static final RegistrySupplier<Item> BATTLE_CHAMPION_RIBBON = createRibbon("battle_champion_ribbon");
    public static final RegistrySupplier<Item> REGIONAL_CHAMPION_RIBBON = createRibbon("regional_champion_ribbon");
    public static final RegistrySupplier<Item> NATIONAL_CHAMPION_RIBBON = createRibbon("national_champion_ribbon");
    public static final RegistrySupplier<Item> WORLD_CHAMPION_RIBBON = createRibbon("world_champion_ribbon");
    public static final RegistrySupplier<Item> GORGEOUS_RIBBON = createRibbon("gorgeous_ribbon");
    public static final RegistrySupplier<Item> GORGEOUS_ROYAL_RIBBON = createRibbon("gorgeous_royal_ribbon");
    public static final RegistrySupplier<Item> GREAT_ABILITY_RIBBON = createRibbon("great_ability_ribbon");
    public static final RegistrySupplier<Item> HOENN_CHAMPION_RIBBON = createRibbon("hoenn_champion_ribbon");
    public static final RegistrySupplier<Item> KALOS_CHAMPION_RIBBON = createRibbon("kalos_champion_ribbon");
    public static final RegistrySupplier<Item> LEGEND_RIBBON = createRibbon("legend_ribbon");
    public static final RegistrySupplier<Item> MULTI_ABILITY_RIBBON = createRibbon("multi_ability_ribbon");
    public static final RegistrySupplier<Item> NATIONAL_RIBBON = createRibbon("national_ribbon");
    public static final RegistrySupplier<Item> PAIR_ABILITY_RIBBON = createRibbon("pair_ability_ribbon");
    public static final RegistrySupplier<Item> RELAX_RIBBON = createRibbon("relax_ribbon");
    public static final RegistrySupplier<Item> ROYAL_RIBBON = createRibbon("royal_ribbon");
    public static final RegistrySupplier<Item> SHOCK_RIBBON = createRibbon("shock_ribbon");
    public static final RegistrySupplier<Item> SINNOH_CHAMPION_RIBBON = createRibbon("sinnoh_champion_ribbon");
    public static final RegistrySupplier<Item> SKILLFUL_BATTLER_RIBBON = createRibbon("skillful_battler_ribbon");
    public static final RegistrySupplier<Item> SMART_RIBBON_GREAT = createRibbon("smart_ribbon_great");
    public static final RegistrySupplier<Item> SMART_RIBBON_HOENN = createRibbon("smart_ribbon_hoenn");
    public static final RegistrySupplier<Item> SMART_RIBBON_HYPER = createRibbon("smart_ribbon_hyper");
    public static final RegistrySupplier<Item> SMART_RIBBON_MASTER_HOENN = createRibbon("smart_ribbon_master_hoenn");
    public static final RegistrySupplier<Item> SMART_RIBBON_MASTER_SINNOH = createRibbon("smart_ribbon_master_sinnoh");
    public static final RegistrySupplier<Item> SMART_RIBBON_SINNOH = createRibbon("smart_ribbon_sinnoh");
    public static final RegistrySupplier<Item> SMART_RIBBON_SUPER = createRibbon("smart_ribbon_super");
    public static final RegistrySupplier<Item> SMART_RIBBON_ULTRA = createRibbon("smart_ribbon_ultra");
    public static final RegistrySupplier<Item> SMILE_RIBBON = createRibbon("smile_ribbon");
    public static final RegistrySupplier<Item> SNOOZE_RIBBON = createRibbon("snooze_ribbon");
    public static final RegistrySupplier<Item> TOUGH_RIBBON_GREAT = createRibbon("tough_ribbon_great");
    public static final RegistrySupplier<Item> TOUGH_RIBBON_HOENN = createRibbon("tough_ribbon_hoenn");
    public static final RegistrySupplier<Item> TOUGH_RIBBON_HYPER = createRibbon("tough_ribbon_hyper");
    public static final RegistrySupplier<Item> TOUGH_RIBBON_MASTER_HOENN = createRibbon("tough_ribbon_master_hoenn");
    public static final RegistrySupplier<Item> TOUGH_RIBBON_MASTER_SINNOH = createRibbon("tough_ribbon_master_sinnoh");
    public static final RegistrySupplier<Item> TOUGH_RIBBON_SINNOH = createRibbon("tough_ribbon_sinnoh");
    public static final RegistrySupplier<Item> TOUGH_RIBBON_SUPER = createRibbon("tough_ribbon_super");
    public static final RegistrySupplier<Item> TOUGH_RIBBON_ULTRA = createRibbon("tough_ribbon_ultra");
    public static final RegistrySupplier<Item> TOUGHNESS_MASTER_RIBBON = createRibbon("toughness_master_ribbon");
    public static final RegistrySupplier<Item> VICTORY_RIBBON = createRibbon("victory_ribbon");
    public static final RegistrySupplier<Item> WINNING_RIBBON = createRibbon("winning_ribbon");
    public static final RegistrySupplier<Item> WORLD_ABILITY_RIBBON = createRibbon("world_ability_ribbon");
    public static final RegistrySupplier<Item> ONCE_IN_A_LIFETIME_RIBBON = createRibbon("once_in_a_lifetime_ribbon");
    public static final RegistrySupplier<Item> GALAR_CHAMP_RIBBON = createRibbon("galar_champ_ribbon");
    public static final RegistrySupplier<Item> ABILITY_SYMBOL = createRibbon("ability_symbol");
    public static final RegistrySupplier<Item> ABILITY_SYMBOL_SILVER = createRibbon("ability_symbol_silver");
    public static final RegistrySupplier<Item> BRAVE_SYMBOL = createRibbon("brave_symbol");
    public static final RegistrySupplier<Item> BRAVE_SYMBOL_SILVER = createRibbon("brave_symbol_silver");
    public static final RegistrySupplier<Item> GUTS_SYMBOL = createRibbon("guts_symbol");
    public static final RegistrySupplier<Item> GUTS_SYMBOL_SILVER = createRibbon("guts_symbol_silver");
    public static final RegistrySupplier<Item> KNOWLEDGE_SYMBOL = createRibbon("knowledge_symbol");
    public static final RegistrySupplier<Item> KNOWLEDGE_SYMBOL_SILVER = createRibbon("knowledge_symbol_silver");
    public static final RegistrySupplier<Item> LUCK_SYMBOL = createRibbon("luck_symbol");
    public static final RegistrySupplier<Item> LUCK_SYMBOL_SILVER = createRibbon("luck_symbol_silver");
    public static final RegistrySupplier<Item> SPIRITS_SYMBOL = createRibbon("spirits_symbol");
    public static final RegistrySupplier<Item> SPIRITS_SYMBOL_SILVER = createRibbon("spirits_symbol_silver");
    public static final RegistrySupplier<Item> TACTICS_SYMBOL = createRibbon("tactics_symbol");
    public static final RegistrySupplier<Item> TACTICS_SYMBOL_SILVER = createRibbon("tactics_symbol_silver");

    /**
     * Held Items
     */
    public static final RegistrySupplier<Item> ABSORB_BULB = register("absorb_bulb", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ABILITY_SHIELD = register("ability_shield", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ADRENALINE_ORB = register("adrenaline_orb", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> AIR_BALLOON = register("air_balloon", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> AMULET_COIN = register("amulet_coin", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> BERRY_JUICE = register("berry_juice", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> BINDING_BAND = register("binding_band", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> BLACK_FLUTE = register("black_flute", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> BLUNDER_POLICY = register("blunder_policy", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> BRIGHT_POWDER = register("bright_powder", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> CELL_BATTERY = register("cell_battery", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> CHARCOAL = register("charcoal", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> CHOICE_BAND = register("choice_band", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> CLEANSE_TAG = register("cleanse_tag", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> DAMP_ROCK = register("damp_rock", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> DESTINY_KNOT = register("destiny_knot", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> EJECT_BUTTON = register("eject_button", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> EJECT_PACK = register("eject_pack", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> EVERSTONE = register("everstone", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> EVIOLITE = register("eviolite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> EXP_ALL = register("exp_all", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> EXPERT_BELT = register("expert_belt", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FLAME_ORB = register("flame_orb", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FLOAT_STONE = register("float_stone", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FLUFFY_TAIL = register("fluffy_tail", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FOCUS_SASH = register("focus_sash", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GRIP_CLAW = register("grip_claw", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> HEAT_ROCK = register("heat_rock", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ICY_ROCK = register("icy_rock", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> IRON_BALL = register("iron_ball", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LAGGING_TAIL = register("lagging_tail", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LEEK = register("leek", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LIFE_ORB = register("life_orb", properties -> new BattleItem(properties) {
//        @Override
//        public void onTurnFinish(Battle battle, Turn turn) {
//
//        }
//
//        @Override
//        public void onUserAction(Battle battle, Turn turn, BattleAction action) {
//            // FIXME: check if move used. if move used to on turn finished.
//            throw new RuntimeException("e");
////            if (action instanceof MoveAction)
////                turn.pushChange(null, new HpStateChange(action.sender, action.sender.getParty().fieldPixelmon.getMaxHp() / 10));
//        }
    }, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LIGHT_BALL = register("light_ball", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LUCKY_PUNCH = register("lucky_punch", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LUMINOUS_MOSS = register("luminous_moss", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> MACHO_BRACE = register("macho_brace", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> MENTAL_HERB = register("mental_herb", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> METAL_POWDER = register("metal_powder", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> METRONOME = register("metronome", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> POKE_DOLL = register("poke_doll", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> POKE_TOY = register("poke_toy", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> POWER_ANKLET = register("power_anklet", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> POWER_BAND = register("power_band", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> POWER_BELT = register("power_belt", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> POWER_BRACER = register("power_bracer", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> POWER_HERB = register("power_herb", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> POWER_LENS = register("power_lens", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> POWER_WEIGHT = register("power_weight", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> PRISM_SCALE = register("prism_scale", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> PROTECTIVE_PADS = register("protective_pads", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> QUICK_POWDER = register("quick_powder", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> RED_CARD = register("red_card", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> RING_TARGET = register("ring_target", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ROOM_SERVICE = register("room_service", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SCOPE_LENS = register("scope_lens", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SHED_SHELL = register("shed_shell", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SHELL_BELL = register("shell_bell", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SMOKE_BALL = register("smoke_ball", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SMOOTH_ROCK = register("smooth_rock", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SNOWBALL = register("snowball", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SOOTHE_BELL = register("soothe_bell", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SOUL_DEW = register("soul_dew", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> STICKY_BARB = register("sticky_barb", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> TERRAIN_EXTENDER = register("terrain_extender", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> THICK_CLUB = register("thick_club", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> THROAT_SPRAY = register("throat_spray", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> TOXIC_ORB = register("toxic_orb", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> UP_GRADE = register("up_grade", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> UTILITY_UMBRELLA = register("utility_umbrella", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> WEAKNESS_POLICY = register("weakness_policy", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> WHITE_FLUTE = register("white_flute", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> WHITE_HERB = register("white_herb", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> WIDE_LENS = register("wide_lens", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ZOOM_LENS = register("zoom_lens", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> BURN_DRIVE = register("burn_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> CHILL_DRIVE = register("chill_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> DOUSE_DRIVE = register("douse_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SHOCK_DRIVE = register("shock_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);

    public static final RegistrySupplier<Item> BUG_GEM = register("bug_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> DARK_GEM = register("dark_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> DRAGON_GEM = register("dragon_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ELECTRIC_GEM = register("electric_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FAIRY_GEM = register("fairy_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FIGHTING_GEM = register("fighting_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FIRE_GEM = register("fire_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FLYING_GEM = register("flying_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GHOST_GEM = register("ghost_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GRASS_GEM = register("grass_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GROUND_GEM = register("ground_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ICE_GEM = register("ice_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> NORMAL_GEM = register("normal_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> POISON_GEM = register("poison_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> PSYCHIC_GEM = register("psychic_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ROCK_GEM = register("rock_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> STEEL_GEM = register("steel_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> WATER_GEM = register("water_gem", Item::new, GenerationsCreativeTabs.HELD_ITEMS);

    public static final RegistrySupplier<Item> FULL_INCENSE = register("full_incense", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LAX_INCENSE = register("lax_incense", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LUCK_INCENSE = register("luck_incense", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ODD_INCENSE = register("odd_incense", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> PURE_INCENSE = register("pure_incense", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ROCK_INCENSE = register("rock_incense", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ROSE_INCENSE = register("rose_incense", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SEA_INCENSE = register("sea_incense", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> WAVE_INCENSE = register("wave_incense", Item::new, GenerationsCreativeTabs.HELD_ITEMS);

    public static final RegistrySupplier<Item> ABOMASITE = register("abomasite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ABSOLITE = register("absolite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> AERODACTYLITE = register("aerodactylite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> AGGRONITE = register("aggronite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ALAKAZITE = register("alakazite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ALTARIANITE = register("altarianite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> AMPHAROSITE = register("ampharosite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> AUDINITE = register("audinite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> BANETTITE = register("banettite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> BEEDRILLITE = register("beedrillite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> BLASTOISINITE = register("blastoisinite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> BLAZIKENITE = register("blazikenite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> CAMERUPTITE = register("cameruptite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> CHARIZARDITE_X = register("charizardite_x", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> CHARIZARDITE_Y = register("charizardite_y", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> DIANCITE = register("diancite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GALLADITE = register("galladite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GARCHOMPITE = register("garchompite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GARDEVOIRITE = register("gardevoirite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GENGARITE = register("gengarite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GLALITITE = register("glalitite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GYARADOSITE = register("gyaradosite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> HERACRONITE = register("heracronite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> HOUNDOOMINITE = register("houndoominite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> KANGASKHANITE = register("kangaskhanite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LATIASITE = register("latiasite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LATIOSITE = register("latiosite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LOPUNNNITE = register("lopunnite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LUCARIONITE = register("lucarionite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> MANECTITE = register("manectite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> MAWILITE = register("mawilite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> MEDICHAMITE = register("medichamite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> METAGROSSITE = register("metagrossite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> MEWTWONITE_X = register("mewtwonite_x", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> MEWTWONITE_Y = register("mewtwonite_y", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> PIDGEOTITE = register("pidgeotite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> PINSIRITE = register("pinsirite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SABLENITE = register("sablenite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SALAMENCITE = register("salamencite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SCEPTILITE = register("sceptilite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SCIZORITE = register("scizorite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SHARPEDONITE = register("sharpedonite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SLOWBRONITE = register("slowbronite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> STEELIXITE = register("steelixite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SWAMPERTITE = register("swampertite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> TYRANITARITE = register("tyranitarite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> VENUSAURITE = register("venusaurite", Item::new, GenerationsCreativeTabs.HELD_ITEMS);

    public static final RegistrySupplier<Item> BUG_MEMORY_DRIVE = register("bug_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> DARK_MEMORY_DRIVE = register("dark_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> DRAGON_MEMORY_DRIVE = register("dragon_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ELECTRIC_MEMORY_DRIVE = register("electric_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FAIRY_MEMORY_DRIVE = register("fairy_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FIGHTING_MEMORY_DRIVE = register("fighting_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FIRE_MEMORY_DRIVE = register("fire_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FLYING_MEMORY_DRIVE = register("flying_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GHOST_MEMORY_DRIVE = register("ghost_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GRASS_MEMORY_DRIVE = register("grass_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GROUND_MEMORY_DRIVE = register("ground_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ICE_MEMORY_DRIVE = register("ice_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> POISON_MEMORY_DRIVE = register("poison_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> PSYCHIC_MEMORY_DRIVE = register("psychic_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ROCK_MEMORY_DRIVE = register("rock_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> STEEL_MEMORY_DRIVE = register("steel_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> WATER_MEMORY_DRIVE = register("water_memory_drive", Item::new, GenerationsCreativeTabs.HELD_ITEMS);

    public static final RegistrySupplier<Item> DRACO_PLATE = register("draco_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> DREAD_PLATE = register("dread_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> EARTH_PLATE = register("earth_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FIST_PLATE = register("fist_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FLAME_PLATE = register("flame_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ICICLE_PLATE = register("icicle_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> INSECT_PLATE = register("insect_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> IRON_PLATE = register("iron_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> MEADOW_PLATE = register("meadow_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> MIND_PLATE = register("mind_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> PIXIE_PLATE = register("pixie_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SKY_PLATE = register("sky_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SPLASH_PLATE = register("splash_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SPOOKY_PLATE = register("spooky_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> STONE_PLATE = register("stone_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> TOXIC_PLATE = register("toxic_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ZAP_PLATE = register("zap_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);

    public static final RegistrySupplier<Item> BUGINIUM_Z = register("buginium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> DARKINIUM_Z = register("darkinium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> DRAGONIUM_Z = register("dragonium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ELECTRIUM_Z = register("electrium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FAIRIUM_Z = register("fairium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FIGHTINIUM_Z = register("fightinium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FIRIUM_Z = register("firium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> FLYINIUM_Z = register("flyinium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GHOSTIUM_Z = register("ghostium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GRASSIUM_Z = register("grassium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GROUNDIUM_Z = register("groundium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ICIUM_Z = register("icium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> NORMALIUM_Z = register("normalium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> POISONIUM_Z = register("poisonium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> PSYCHIUM_Z = register("psychium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ROCKIUM_Z = register("rockium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> STEELIUM_Z = register("steelium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> WATERIUM_Z = register("waterium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);

    public static final RegistrySupplier<Item> ALORAICHIUM_Z = register("aloraichium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> DECIDIUM_Z = register("decidium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> EEVIUM_Z = register("eevium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> INCINIUM_Z = register("incinium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> KOMMONIUM_Z = register("kommonium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LUNALIUM_Z = register("lunalium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LYCANIUM_Z = register("lycanium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> MARSHADIUM_Z = register("marshadium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> MEWNIUM_Z = register("mewnium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> MIMIKIUM_Z = register("mimikium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> PIKANIUM_Z = register("pikanium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> PIKASHUNIUM_Z = register("pikashunium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> PRIMARIUM_Z = register("primarium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SNORLIUM_Z = register("snorlium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> SOLGANIUM_Z = register("solganium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> TAPUNIUM_Z = register("tapunium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ULTRANECROZIUM_Z = register("ultranecrozium_z", Item::new, GenerationsCreativeTabs.HELD_ITEMS);

    public static final RegistrySupplier<Item> ELECTRIC_SEED = register("electric_seed", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> MISTY_SEED = register("misty_seed", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GRASSY_SEED = register("grassy_seed", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> PSYCHIC_SEED = register("psychic_seed", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> RED_SCARF = register("red_scarf", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LEGEND_PLATE = register("legend_plate", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> LUSTROUS_GLOBE = register("lustrous_globe", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> ADAMANT_CRYSTAL = register("adamant_crystal", Item::new, GenerationsCreativeTabs.HELD_ITEMS);
    public static final RegistrySupplier<Item> GRISEOUS_CORE = register("griseous_core", Item::new, GenerationsCreativeTabs.HELD_ITEMS);

    /**
     * Vanilla Like Materials
     */
    public static final RegistrySupplier<Item> Z_INGOT = register("z_ingot", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> ALUMINUM_INGOT = register("aluminum_ingot", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> ALUMINUM_PLATE = register("aluminum_plate", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> RAW_ALUMINUM = register("raw_aluminum", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> ALUMINUM_NUGGET = register("aluminum_nugget", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);

    /**
     * Player Items
     */
    public static final RegistrySupplier<Item> MARK_CHARM = register("mark_charm", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> CATCHING_CHARM = register("catching_charm", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> EXP_CHARM = register("exp_charm", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    /*, TieredFishingHookEntity.Teir.OLD*/
    public static final RegistrySupplier<Item> OLD_ROD = register("old_rod", properties -> new TieredFishingRodItem(properties, TieredFishingHookEntity.Teir.OLD), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> GOOD_ROD = register("good_rod", properties -> new TieredFishingRodItem(properties, TieredFishingHookEntity.Teir.GOOD), GenerationsCreativeTabs.PLAYER_ITEMS);
    /*, TieredFishingHookEntity.Teir.SUPER*/
    public static final RegistrySupplier<Item> SUPER_ROD = register("super_rod", properties -> new TieredFishingRodItem(properties, TieredFishingHookEntity.Teir.SUPER), GenerationsCreativeTabs.PLAYER_ITEMS);
    /*, TieredFishingHookEntity.Teir.RUBY*/
    public static final RegistrySupplier<Item> RUBY_ROD = register("ruby_rod", properties -> new TieredFishingRodItem(properties, TieredFishingHookEntity.Teir.RUBY), GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> CAMERA = register("camera", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> SNAP_CAMERA = register("snap_camera", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> FILM = register("film", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> REPEL = register("repel", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> SUPER_REPEL = register("super_repel", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> MAX_REPEL = register("max_repel", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> WAILMER_PAIL = register("wailmer_pail", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> SPRINK_LOTAD = register("sprink_lotad", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    /*
    public static final RegistrySupplier<Item> RED_BIKE = register("red_bike", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> ORANGE_BIKE = register("orange_bike", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> YELLOW_BIKE = register("yellow_bike", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> GREEN_BIKE = register("green_bike", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> BLUE_BIKE = register("blue_bike", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> PURPLE_BIKE = register("purple_bike", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    */
    public static final RegistrySupplier<Item> ROTOM_CATALOG = register("rotom_catalog", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> POKEDEX = register("pokedex", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> LURE_MODULE = register("lure_module", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> BOTTLE_CAP = register("bottle_cap", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> GOLD_BOTTLE_CAP = register("gold_bottle_cap", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> ABILITY_CAPSULE = register("ability_capsule", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> ABILITY_PATCH = register("ability_patch", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> WISHING_STAR = register("wishing_star", Item::new, GenerationsCreativeTabs.PLAYER_ITEMS);
    public static final RegistrySupplier<Item> UNCHARGED_DYNAMAX_BAND = register("uncharged_dynamax_band", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.PLAYER_ITEMS);

    /**
     * Legendary Items
     */
    public static final RegistrySupplier<Item> ORB = register("orb", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> ADAMANT_ORB = register("adamant_orb", properties -> new CreationTrioItem(properties.stacksTo(1), "dialga", GenerationsCore.id("models/block/shrines/creation_trio/adamant_orb.pk")), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> GRISEOUS_ORB = register("griseous_orb", properties -> new CreationTrioItem(properties.stacksTo(1), "giratina", GenerationsCore.id("models/block/shrines/creation_trio/griseous_orb.pk")), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> LUSTROUS_ORB = register("lustrous_orb", properties -> new CreationTrioItem(properties.stacksTo(1), "palkia", GenerationsCore.id("models/block/shrines/creation_trio/lustrous_orb.pk")), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SHATTERED_ICE_KEY_1 = register("shattered_ice_key_1", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SHATTERED_ICE_KEY_2 = register("shattered_ice_key_2", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SHATTERED_ICE_KEY_3 = register("shattered_ice_key_3", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SHATTERED_ICE_KEY_4 = register("shattered_ice_key_4", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> ICEBERG_KEY = register("iceberg_key", properties -> new RegiKeyItem(properties, "regice"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> CRUMBLED_ROCK_KEY_1 = register("crumbled_rock_key_1", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> CRUMBLED_ROCK_KEY_2 = register("crumbled_rock_key_2", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> CRUMBLED_ROCK_KEY_3 = register("crumbled_rock_key_3", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> CRUMBLED_ROCK_KEY_4 = register("crumbled_rock_key_4", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> ROCK_PEAK_KEY = register("rock_peak_key", properties -> new RegiKeyItem(properties, "regirock"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> RUSTY_IRON_KEY_1 = register("rusty_iron_key_1", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> RUSTY_IRON_KEY_2 = register("rusty_iron_key_2", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> RUSTY_IRON_KEY_3 = register("rusty_iron_key_3", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> RUSTY_IRON_KEY_4 = register("rusty_iron_key_4", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> IRON_KEY = register("iron_key", properties -> new RegiKeyItem(properties, "registeel"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> FRAGMENTED_DRAGO_KEY_1 = register("fragmented_drago_key_1", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> FRAGMENTED_DRAGO_KEY_2 = register("fragmented_drago_key_2", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> FRAGMENTED_DRAGO_KEY_3 = register("fragmented_drago_key_3", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> FRAGMENTED_DRAGO_KEY_4 = register("fragmented_drago_key_4", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> DRAGO_KEY = register("drago_key", properties -> new RegiKeyItem(properties, "regidrago"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> DISCHARGED_ELEKI_KEY_1 = register("discharged_eleki_key_1", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> DISCHARGED_ELEKI_KEY_2 = register("discharged_eleki_key_2", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> DISCHARGED_ELEKI_KEY_3 = register("discharged_eleki_key_3", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> DISCHARGED_ELEKI_KEY_4 = register("discharged_eleki_key_4", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> ELEKI_KEY = register("eleki_key", properties -> new RegiKeyItem(properties, "regieleki"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SHATTERED_RELIC_SONG_1 = register("shattered_relic_song_1", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SHATTERED_RELIC_SONG_2 = register("shattered_relic_song_2", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SHATTERED_RELIC_SONG_3 = register("shattered_relic_song_3", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SHATTERED_RELIC_SONG_4 = register("shattered_relic_song_4", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> RELIC_SONG = register("relic_song", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> RED_CHAIN = register("red_chain", properties -> new RedChainItem(properties.stacksTo(1)), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> RED_CHAIN_SHARD = register("red_chain_shard", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> DNA_SPLICERS = register("dna_splicers", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> REINS_OF_UNITY = register("reins_of_unity", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> N_SOLARIZER = register("n_solarizer", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> N_LUNARIZER = register("n_lunarizer", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> LEGEND_FINDER = register("legend_finder", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> HOOPA_RING = register("hoopa_ring", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> RED_ORB = register("red_orb", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> FADED_RED_ORB = register("faded_red_orb", properties -> new WeatherTrioItem(properties.stacksTo(1).durability(300), ElementalTypes.INSTANCE.getFIRE()), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> FADED_BLUE_ORB = register("faded_blue_orb", properties -> new WeatherTrioItem(properties.stacksTo(1).durability(300), ElementalTypes.INSTANCE.getWATER()), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> FADED_JADED_ORB = register("faded_jade_orb", properties -> new WeatherTrioItem(properties.stacksTo(1).durability(300), ElementalTypes.INSTANCE.getFLYING()), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> BLUE_ORB = register("blue_orb", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> JADE_ORB = register("jade_orb", properties -> new WeatherTrioItem(properties.stacksTo(1).durability(300), ElementalTypes.INSTANCE.getFLYING()), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> LIGHT_STONE = register("light_stone", properties -> new TaoTrioStoneItem(properties.stacksTo(1).durability(100), "reshiram"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> DARK_STONE = register("dark_stone", properties -> new TaoTrioStoneItem(properties.stacksTo(1).durability(100), "zekrom"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> DRAGON_STONE = register("dragon_stone", properties -> new TaoTrioStoneItem(properties.stacksTo(1).durability(100), "kyurem"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> RAINBOW_WING = register("rainbow_wing", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> DARK_SOUL = register("dark_soul", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> DRAGON_SOUL = register("dragon_soul", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> MELODY_FLUTE = register("melody_flute", MelodyFluteItem::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SPARKLING_SHARD = register("sparkling_shard", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SPARKLING_STONE = register("sparkling_stone", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> RUSTY_FRAGMENT = register("rusty_fragment", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> RUSTY_SWORD = register("rusty_sword", properties -> new PostBattleUpdatingItemImpl(properties.stacksTo(1).durability(250), "zacian", "pixelmon.rusty_sword.amountfull", (player, stack, battle) -> Streams.stream(battle.pokemon().getTypes()).anyMatch(type -> type.equals(ElementalTypes.INSTANCE.getFAIRY()))), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> CROWNED_SWORD = register("crowned_sword", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> RUSTY_SHIELD = register("rusty_shield", properties -> new PostBattleUpdatingItemImpl(properties.stacksTo(1).durability(250), "zamazenta", "pixelmon.rusty_shield.amountfull", (player, stack, battle) -> Streams.stream(battle.pokemon().getTypes()).anyMatch(type -> type.equals(ElementalTypes.INSTANCE.getFIGHTING()))), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> CROWNED_SHIELD = register("crowned_shield", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SCROLL_PAGE = register("scroll_page", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SECRET_ARMOR_SCROLL = register("secret_armor_scroll", properties -> new PostBattleUpdatingItemImpl(properties.stacksTo(1).durability(100), "kubfu", "pixelmon.secret_armor_scoll.amountfull", (player, stack, battle) -> battle.isNpc()), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> ZYGARDE_CUBE = register("zygarde_cube", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> MELTAN_BOX = register("meltan_box", properties -> new PostBattleUpdatingItemImpl(properties.stacksTo(1).durability(200), "meltan", "pixelmon.meltanbox.amountfull", (player, stack, battle) -> Streams.stream(new PlayerPartyStore(player.getUuid()).iterator()).map(Pokemon::getSpecies).map(Species::getResourceIdentifier).map(ResourceLocation::toString).anyMatch(a -> a.equals("cobblemon:meltan")) && Streams.stream(battle.pokemon().getTypes()).anyMatch(type -> type.equals(ElementalTypes.INSTANCE.getSTEEL()))) {
        @Override
        protected void postSpawn(Level level, Player player, InteractionHand usedHand) {
            player.setItemInHand(usedHand, new ItemStack(MELTAN_BOX_CHARGED.get()));
        }
    }, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> MELTAN_BOX_CHARGED = register("meltan_box_charged", properties -> new EvolutionItem(properties.stacksTo(1)), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> TIME_GLASS = register("time_glass", properties -> new TimeGlassItem(properties.stacksTo(1).durability(100)), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> MOON_FLUTE = register("moon_flute", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SUN_FLUTE = register("sun_flute", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> LAVA_CRYSTAL = register("lava_crystal", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> JEWEL_OF_LIFE = register("jewel_of_life", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> PRISON_BOTTLE_STEM = register("prison_bottle_stem", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> PRISON_BOTTLE = register("prison_bottle", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> MIRROR = register("mirror", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> DARK_CRYSTAL = register("dark_crystal", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> UNENCHANTED_ICEROOT_CARROT = register("unenchanted_iceroot_carrot", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> ICEROOT_CARROT = register("iceroot_carrot", properties -> new CalyrexSteedItem(properties.stacksTo(1).durability(100).food(new FoodProperties.Builder().build()), GenerationsCore.id("glastrier"), UNENCHANTED_ICEROOT_CARROT), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> UNENCHANTED_SHADEROOT_CARROT = register("unenchanted_shaderoot_carrot", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SHADEROOT_CARROT = register("shaderoot_carrot", properties -> new CalyrexSteedItem(properties.stacksTo(1).durability(100).food(new FoodProperties.Builder().build()), GenerationsCore.id("spectrier"), UNENCHANTED_SHADEROOT_CARROT), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> ENIGMA_STONE = register("enigma_stone", properties -> new DistanceTraveledImplItem(properties.stacksTo(1), 3000) {
        private PokemonProperties latias = GenerationsUtils.parseProperties("latias");
        private PokemonProperties latios = GenerationsUtils.parseProperties("latios");

        @Override
        protected void onCompletion(ServerLevel level, Player player, InteractionHand usedHand) {
            var species = Math.random() > 0.5 ? latias : latios;
            PokemonUtil.spawn(species, level, player.getOnPos());
        }
    }, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> ENIGMA_SHARD = register("enigma_shard", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> ENIGMA_FRAGMENT = register("enigma_fragment", properties -> new Item(properties), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SACRED_ASH = register("sacred_ash", properties -> new SacredAshItem(properties.stacksTo(1).durability(1)), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SHARD_OF_WILLPOWER = register("shard_of_willpower", properties -> new Item(properties.stacksTo(9)), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SHARD_OF_EMOTION = register("shard_of_emotion", properties -> new Item(properties.stacksTo(9)), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SHARD_OF_KNOWLEDGE = register("shard_of_knowledge", properties -> new Item(properties.stacksTo(9)), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> CRYSTAL_OF_WILLPOWER = register("crystal_of_willpower", properties -> new LakeCrystalItem(properties.durability(100), "azelf"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> CRYSTAL_OF_EMOTION = register("crystal_of_emotion", properties -> new LakeCrystalItem(properties.durability(100), "mesprit"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> CRYSTAL_OF_KNOWLEDGE = register("crystal_of_knowledge", properties -> new LakeCrystalItem(properties.durability(100), "uxie"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> REGICE_ORB = register("regice_orb", properties -> new RegiOrbItem(properties, "regice"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> REGIROCK_ORB = register("regirock_orb", properties -> new RegiOrbItem(properties, "regirock"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> REGISTEEL_ORB = register("registeel_orb", properties -> new RegiOrbItem(properties,"registeel"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> REGIDRAGO_ORB = register("regidrago_orb", properties -> new RegiOrbItem(properties, "regidrago"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> REGIELEKI_ORB = register("regieleki_orb", properties -> new RegiOrbItem(properties, "regieleki"), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> MAGMA_CRYSTAL = register("magma_crystal", properties -> new Item(properties.stacksTo(1)) {
        public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
            ItemStack itemStack = player.getItemInHand(usedHand);

            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.ENDER_PEARL_THROW,
                    SoundSource.NEUTRAL,
                    0.5F,
                    0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            player.getCooldowns().addCooldown(this, 20);
            if (!level.isClientSide) {
                MagmaCrystalEntity magmaCrystal = new MagmaCrystalEntity(level, player); //TODO: Re enable
                magmaCrystal.setItem(itemStack);
                magmaCrystal.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(magmaCrystal);
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }

            return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
        }
    }, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> ICY_WING = register("icy_wing", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> ELEGANT_WING = register("elegant_wing", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> STATIC_WING = register("static_wing", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> BELLIGERENT_WING = register("belligerent_wing", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> FIERY_WING = register("fiery_wing", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SINISTER_WING = register("sinister_wing", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> MEW_DNA_FIBER = register("mew_dna_fiber", properties -> new Item(properties), GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> LIGHT_SOUL = register("light_soul", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> LIGHT_CRYSTAL = register("light_crystal", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> WONDER_EGG = register("wonder_egg", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> PHIONE_EGG = register("phione_egg", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);
    public static final RegistrySupplier<Item> SOUL_HEART = register("soul_heart", Item::new, GenerationsCreativeTabs.LEGENDARY_ITEMS);

    /**
     * Naturals
     */
    public static final RegistrySupplier<Item> CRYSTAL = register("crystal", Item::new, GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> RUBY = register("ruby", Item::new, GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> SAPPHIRE = register("sapphire", Item::new, GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> SILICON = register("silicon", Item::new, GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> TUMBLESTONE = register("tumblestone", Item::new, GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> BLACK_TUMBLESTONE = register("black_tumblestone", Item::new, GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> SKY_TUMBLESTONE = register("sky_tumblestone", Item::new, GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> RARE_TUMBLESTONE = register("rare_tumblestone", Item::new, GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> GALARICA_TWIG = register("galarica_twig", Item::new, GenerationsCreativeTabs.BUILDING_BLOCKS);

    /**
     * Utility Items
     */
    public static final RegistrySupplier<Item> POKEMON_WAND = register("pokemon_wand", Item::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> CHISEL = register("chisel", StatueEditorItem::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> GIFT_BOX = register("gift_box", Item::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> NPC_WAND = register("npc_wand", NpcWandItem::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> NPC_PATH_TOOL = register("npc_path_tool", NpcPathTool::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> ZONE_WAND = register("zone_wand", Item::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> BIKE_FRAME = register("bike_frame", Item::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> BIKE_HANDLEBARS = register("bike_handlebars", Item::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> BIKE_SEAT = register("bike_seat", Item::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> BIKE_WHEEL = register("bike_wheel", Item::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> HIDDEN_IRON_DOOR = register("hidden_iron_door", Item::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> HIDDEN_WOODEN_DOOR = register("hidden_wooden_door", Item::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> HIDDEN_LEVER = register("hidden_lever", Item::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> HIDDEN_PRESSURE_PLATE = register("hidden_pressure_plate", Item::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> HIDDEN_CUBE = register("hidden_cube", Item::new, GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> HIDDEN_BUTTON = register("hidden_button", Item::new, GenerationsCreativeTabs.UTILITY);

    /**
     * Form Items
     */
    public static final RegistrySupplier<Item> METEORITE = register("meteorite", MeteoriteItem::new, GenerationsCreativeTabs.FORM_ITEMS);
    public static final RegistrySupplier<Item> GRACIDEA = register("gracidea", Item::new, GenerationsCreativeTabs.FORM_ITEMS);
    public static final RegistrySupplier<Item> REVEAL_GLASS = register("reveal_glass", Item::new, GenerationsCreativeTabs.FORM_ITEMS);
    public static final RegistrySupplier<Item> ROCKSTAR_COSTUME = register("rockstar_costume", Item::new, GenerationsCreativeTabs.FORM_ITEMS);
    public static final RegistrySupplier<Item> BELLE_COSTUME = register("belle_costume", Item::new, GenerationsCreativeTabs.FORM_ITEMS);
    public static final RegistrySupplier<Item> POPSTAR_COSTUME = register("popstar_costume", Item::new, GenerationsCreativeTabs.FORM_ITEMS);
    public static final RegistrySupplier<Item> PHD_COSTUME = register("phd_costume", Item::new, GenerationsCreativeTabs.FORM_ITEMS);
    public static final RegistrySupplier<Item> LIBRE_COSTUME = register("libre_costume", Item::new, GenerationsCreativeTabs.FORM_ITEMS);
    public static final RegistrySupplier<Item> MEWTWO_ARMOR = register("mewtwo_armor", Item::new, GenerationsCreativeTabs.FORM_ITEMS);
    public static final RegistrySupplier<Item> PINK_NECTAR = register("pink_nectar", Item::new, GenerationsCreativeTabs.FORM_ITEMS);
    public static final RegistrySupplier<Item> PURPLE_NECTAR = register("purple_nectar", Item::new, GenerationsCreativeTabs.FORM_ITEMS);
    public static final RegistrySupplier<Item> RED_NECTAR = register("red_nectar", Item::new, GenerationsCreativeTabs.FORM_ITEMS);
    public static final RegistrySupplier<Item> YELLOW_NECTAR = register("yellow_nectar", Item::new, GenerationsCreativeTabs.FORM_ITEMS);

    /**
     * Mail
     */
    public static final RegistrySupplier<Item> POKEMAIL_AIR = register("pokemail_air", properties -> new MailItem(MailType.AIR, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_AIR_CLOSED = register("pokemail_air_closed", properties -> new ClosedMailItem(MailType.AIR, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BLOOM = register("pokemail_bloom", properties -> new MailItem(MailType.BLOOM, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BLOOM_CLOSED = register("pokemail_bloom_closed", properties -> new ClosedMailItem(MailType.BLOOM, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BRICK = register("pokemail_brick", properties -> new MailItem(MailType.BRICK, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BRICK_CLOSED = register("pokemail_brick_closed", properties -> new ClosedMailItem(MailType.BRICK, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BRIDGE_D = register("pokemail_bridge_d", properties -> new MailItem(MailType.BRIDGE_D, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BRIDGE_D_CLOSED = register("pokemail_bridge_d_closed", properties -> new ClosedMailItem(MailType.BRIDGE_D, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BRIDGE_M = register("pokemail_bridge_m", properties -> new MailItem(MailType.BRIDGE_M, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BRIDGE_M_CLOSED = register("pokemail_bridge_m_closed", properties -> new ClosedMailItem(MailType.BRIDGE_M, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BRIDGE_S = register("pokemail_bridge_s", properties -> new MailItem(MailType.BRIDGE_S, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BRIDGE_S_CLOSED = register("pokemail_bridge_s_closed", properties -> new ClosedMailItem(MailType.BRIDGE_S, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BRIDGE_T = register("pokemail_bridge_t", properties -> new MailItem(MailType.BRIDGE_T, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BRIDGE_T_CLOSED = register("pokemail_bridge_t_closed", properties -> new ClosedMailItem(MailType.BRIDGE_T, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BRIDGE_V = register("pokemail_bridge_v", properties -> new MailItem(MailType.BRIDGE_V, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BRIDGE_V_CLOSED = register("pokemail_bridge_v_closed", properties -> new ClosedMailItem(MailType.BRIDGE_V, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BUBBLE = register("pokemail_bubble", properties -> new MailItem(MailType.BUBBLE, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_BUBBLE_CLOSED = register("pokemail_bubble_closed", properties -> new ClosedMailItem(MailType.BUBBLE, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_DREAM = register("pokemail_dream", properties -> new MailItem(MailType.DREAM, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_DREAM_CLOSED = register("pokemail_dream_closed", properties -> new ClosedMailItem(MailType.DREAM, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_FAB = register("pokemail_fab", properties -> new MailItem(MailType.FAB, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_FAB_CLOSED = register("pokemail_fab_closed", properties -> new ClosedMailItem(MailType.FAB, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_FAVORED = register("pokemail_favored", properties -> new MailItem(MailType.FAVORED, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_FAVORED_CLOSED = register("pokemail_favored_closed", properties -> new ClosedMailItem(MailType.FAVORED, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_FLAME = register("pokemail_flame", properties -> new MailItem(MailType.FLAME, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_FLAME_CLOSED = register("pokemail_flame_closed", properties -> new ClosedMailItem(MailType.FLAME, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_GLITTER = register("pokemail_glitter", properties -> new MailItem(MailType.GLITTER, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_GLITTER_CLOSED = register("pokemail_glitter_closed", properties -> new ClosedMailItem(MailType.GLITTER, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_GRASS = register("pokemail_grass", properties -> new MailItem(MailType.GRASS, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_GRASS_CLOSED = register("pokemail_grass_closed", properties -> new ClosedMailItem(MailType.GRASS, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_GREET = register("pokemail_greet", properties -> new MailItem(MailType.GREET, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_GREET_CLOSED = register("pokemail_greet_closed", properties -> new ClosedMailItem(MailType.GREET, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_HARBOR = register("pokemail_harbor", properties -> new MailItem(MailType.HARBOR, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_HARBOR_CLOSED = register("pokemail_harbor_closed", properties -> new ClosedMailItem(MailType.HARBOR, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_HEART = register("pokemail_heart", properties -> new MailItem(MailType.HEART, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_HEART_CLOSED = register("pokemail_heart_closed", properties -> new ClosedMailItem(MailType.HEART, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_INQUIRY = register("pokemail_inquiry", properties -> new MailItem(MailType.INQUIRY, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_INQUIRY_CLOSED = register("pokemail_inquiry_closed", properties -> new ClosedMailItem(MailType.INQUIRY, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_LIKE = register("pokemail_like", properties -> new MailItem(MailType.LIKE, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_LIKE_CLOSED = register("pokemail_like_closed", properties -> new ClosedMailItem(MailType.LIKE, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_MECH = register("pokemail_mech", properties -> new MailItem(MailType.MECH, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_MECH_CLOSED = register("pokemail_mech_closed", properties -> new ClosedMailItem(MailType.MECH, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_MOSAIC = register("pokemail_mosaic", properties -> new MailItem(MailType.MOSAIC, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_MOSAIC_CLOSED = register("pokemail_mosaic_closed", properties -> new ClosedMailItem(MailType.MOSAIC, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_ORANGE = register("pokemail_orange", properties -> new MailItem(MailType.ORANGE, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_ORANGE_CLOSED = register("pokemail_orange_closed", properties -> new ClosedMailItem(MailType.ORANGE, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_REPLY = register("pokemail_reply", properties -> new MailItem(MailType.REPLY, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_REPLY_CLOSED = register("pokemail_reply_closed", properties -> new ClosedMailItem(MailType.REPLY, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_RETRO = register("pokemail_retro", properties -> new MailItem(MailType.RETRO, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_RETRO_CLOSED = register("pokemail_retro_closed", properties -> new ClosedMailItem(MailType.RETRO, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_RSVP = register("pokemail_rsvp", properties -> new MailItem(MailType.RSVP, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_RSVP_CLOSED = register("pokemail_rsvp_closed", properties -> new ClosedMailItem(MailType.RSVP, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_SHADOW = register("pokemail_shadow", properties -> new MailItem(MailType.SHADOW, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_SHADOW_CLOSED = register("pokemail_shadow_closed", properties -> new ClosedMailItem(MailType.SHADOW, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_SNOW = register("pokemail_snow", properties -> new MailItem(MailType.SNOW, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_SNOW_CLOSED = register("pokemail_snow_closed", properties -> new ClosedMailItem(MailType.SNOW, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_SPACE = register("pokemail_space", properties -> new MailItem(MailType.SPACE, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_SPACE_CLOSED = register("pokemail_space_closed", properties -> new ClosedMailItem(MailType.SPACE, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_STEEL = register("pokemail_steel", properties -> new MailItem(MailType.STEEL, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_STEEL_CLOSED = register("pokemail_steel_closed", properties -> new ClosedMailItem(MailType.STEEL, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_THANKS = register("pokemail_thanks", properties -> new MailItem(MailType.THANKS, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_THANKS_CLOSED = register("pokemail_thanks_closed", properties -> new ClosedMailItem(MailType.THANKS, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_TROPIC = register("pokemail_tropic", properties -> new MailItem(MailType.TROPIC, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_TROPIC_CLOSED = register("pokemail_tropic_closed", properties -> new ClosedMailItem(MailType.TROPIC, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_TUNNEL = register("pokemail_tunnel", properties -> new MailItem(MailType.TUNNEL, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_TUNNEL_CLOSED = register("pokemail_tunnel_closed", properties -> new ClosedMailItem(MailType.TUNNEL, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_WAVE = register("pokemail_wave", properties -> new MailItem(MailType.WAVE, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_WAVE_CLOSED = register("pokemail_wave_closed", properties -> new ClosedMailItem(MailType.WAVE, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_WOOD = register("pokemail_wood", properties -> new MailItem(MailType.WOOD, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);
    public static final RegistrySupplier<Item> POKEMAIL_WOOD_CLOSED = register("pokemail_wood_closed", properties -> new ClosedMailItem(MailType.WOOD, properties.stacksTo(1)), GenerationsCreativeTabs.POKEMAIL);

    /**
     * Valuable Items
     */
    public static final RegistrySupplier<Item> RELIC_GOLD = register("relic_gold", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> RELIC_COPPER = register("relic_copper", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> RELIC_BAND = register("relic_band", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> RELIC_SILVER = register("relic_silver", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> RELIC_STATUE = register("relic_statue", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> RELIC_VASE = register("relic_vase", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> RELIC_CROWN = register("relic_crown", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> HEART_SCALE = register("heart_scale", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> SHOAL_SHELL = register("shoal_shell", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> SHOAL_SALT = register("shoal_salt", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> STARDUST = register("stardust", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> RARE_BONE = register("rare_bone", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> SILVER_LEAF = register("silver_leaf", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> STRANGE_SOUVENIR = register("strange_souvenir", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> SLOWPOKE_TAIL = register("slowpoke_tail", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> ODD_KEYSTONE = register("odd_keystone", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> ESCAPE_ROPE = register("escape_rope", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> BEACH_GLASS = register("beach_glass", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> CHALKY_STONE = register("chalky_stone", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> LONE_EARRING = register("lone_earring", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> PRETTY_FEATHER = register("pretty_feather", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> MARBLE = register("marble", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> POLISHED_MUD_BALL = register("polished_mud_ball", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> STRETCHY_SPRING = register("stretchy_spring", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> TROPICAL_SHELL = register("tropical_shell", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> BALM_MUSHROOM = register("balm_mushroom", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> BIG_MUSHROOM = register("big_mushroom", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> BIG_NUGGET = register("big_nugget", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> BIG_PEARL = register("big_pearl", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> GOLD_LEAF = register("gold_leaf", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> SMALL_BOUQUET = register("small_bouquet", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> BLUE_SHARD = register("blue_shard", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> COMET_SHARD = register("comet_shard", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> GREEN_SHARD = register("green_shard", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> NUGGET = register("nugget", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> PEARL = register("pearl", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> PEARL_STRING = register("pearl_string", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> RED_SHARD = register("red_shard", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> STAR_PIECE = register("star_piece", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> TINY_MUSHROOM = register("tiny_mushroom", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> YELLOW_SHARD = register("yellow_shard", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> HONEY = register("honey", Item::new, GenerationsCreativeTabs.VALUABLES);

    public static final RegistrySupplier<Item> BUG_CANDY = register("bug_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> DARK_CANDY = register("dark_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> DRAGON_CANDY = register("dragon_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> ELECTRIC_CANDY = register("electric_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> FAIRY_CANDY = register("fairy_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> FIGHTING_CANDY = register("fighting_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> FIRE_CANDY = register("fire_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> FLYING_CANDY = register("flying_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> GHOST_CANDY = register("ghost_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> GRASS_CANDY = register("grass_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> GROUND_CANDY = register("ground_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> ICE_CANDY = register("ice_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> NORMAL_CANDY = register("normal_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> POISON_CANDY = register("poison_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> PSYCHIC_CANDY = register("psychic_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> ROCK_CANDY = register("rock_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> STEEL_CANDY = register("steel_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> WATER_CANDY = register("water_candy", Item::new, GenerationsCreativeTabs.VALUABLES);
    public static final RegistrySupplier<Item> NULL_CANDY = register("null_candy", Item::new, GenerationsCreativeTabs.VALUABLES);

    /**
     * Curry Ingredients
     */
    public static final RegistrySupplier<Item> BACHS_FOOD_TIN = register("bachs_food_tin", properties -> new CurryIngredient(CurryType.Rich, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> BOBS_FOOD_TIN = register("bobs_food_tin", properties -> new CurryIngredient(CurryType.Juicy, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> BOILED_EGG = register("boiled_egg", properties -> new CurryIngredient(CurryType.BoiledEgg, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> BREAD = register("bread", properties -> new CurryIngredient(CurryType.Toast, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> BRITTLE_BONES = register("brittle_bones", properties -> new CurryIngredient(CurryType.Bone, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> COCONUT_MILK = register("coconut_milk", properties -> new CurryIngredient(CurryType.Coconut, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> FANCY_APPLE = register("fancy_apple", properties -> new CurryIngredient(CurryType.Apple, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> FRESH_CREAM = register("fresh_cream", properties -> new CurryIngredient(CurryType.WhippedCream, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> FRIED_FOOD = register("fried_food", properties -> new CurryIngredient(CurryType.FriedFood, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> FRUIT_BUNCH = register("fruit_bunch", properties -> new CurryIngredient(CurryType.Tropical, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> INSTANT_NOODLES = register("instant_noodles", properties -> new CurryIngredient(CurryType.InstantNoodle, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> LARGE_LEEK = register("large_leek", properties -> new CurryIngredient(CurryType.Leek, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> MIXED_MUSHROOMS = register("mixed_mushrooms", properties -> new CurryIngredient(CurryType.MushroomMedley, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> MOOMOO_CHEESE = register("moomoo_cheese", properties -> new CurryIngredient(CurryType.CheeseCovered, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> PACK_OF_POTATOES = register("pack_of_potatoes", properties -> new CurryIngredient(CurryType.PlentyOfPotato, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> PACKAGED_CURRY = register("packaged_curry", properties -> new CurryIngredient(CurryType.Decorative, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> PASTA = register("pasta", properties -> new CurryIngredient(CurryType.Pasta, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> PRECOOKED_BURGER = register("precooked_burger", properties -> new CurryIngredient(CurryType.BurgerSteak, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> PUNGENT_ROOT = register("pungent_root", properties -> new CurryIngredient(CurryType.HerbMedley, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> SALAD_MIX = register("salad_mix", properties -> new CurryIngredient(CurryType.Salad, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> SAUSAGES = register("sausages", properties -> new CurryIngredient(CurryType.Sausage, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> SMOKED_POKE_TAIL = register("smoked_poke_tail", properties -> new CurryIngredient(CurryType.SmokedTail, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> SPICE_MIX = register("spice_mix", properties -> new CurryIngredient(CurryType.Seasoned, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> TIN_OF_BEANS = register("tin_of_beans", properties -> new CurryIngredient(CurryType.BeanMedley, properties), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> GIGANTAMIX = register("gigantamix", properties -> new CurryIngredient(CurryType.Gigantamax, properties), GenerationsCreativeTabs.CUISINE);

    /**
     * Player Consumables
     */
    public static final RegistrySupplier<Item> KOMALA_COFFEE = register("komala_coffee", properties -> new Item(properties.food(new FoodProperties.Builder().nutrition(8).saturationMod(0.8f).alwaysEat().build())), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> OMELETTE = register("omelette", properties -> new Item(properties.food(new FoodProperties.Builder().nutrition(8).saturationMod(0.8f).alwaysEat().build())), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> PINAP_JUICE = register("pinap_juice", properties -> new Item(properties.food(new FoodProperties.Builder().nutrition(8).saturationMod(0.8f).alwaysEat().build())), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> ROSERADE_TEA = register("roserade_tea", properties -> new Item(properties.food(new FoodProperties.Builder().nutrition(8).saturationMod(0.8f).alwaysEat().build())), GenerationsCreativeTabs.CUISINE);
    public static final RegistrySupplier<Item> TAPU_COCOA = register("tapu_cocoa", properties -> new Item(properties.food(new FoodProperties.Builder().nutrition(8).saturationMod(0.8f).alwaysEat().build())), GenerationsCreativeTabs.CUISINE);

    /**
     * Fossils
     */
    public static final RegistrySupplier<Item> ARMOR_FOSSIL = register("armor_fossil", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> BIRD_FOSSIL = register("bird_fossil", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> CLAW_FOSSIL = register("claw_fossil", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> COVER_FOSSIL = register("cover_fossil", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> DINO_FOSSIL = register("dino_fossil", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> DOME_FOSSIL = register("dome_fossil", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> DRAKE_FOSSIL = register("drake_fossil", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> FISH_FOSSIL = register("fish_fossil", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> FOSSIL = register("fossil", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> HELIX_FOSSIL = register("helix_fossil", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> JAW_FOSSIL = register("jaw_fossil", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> OLD_AMBER = register("old_amber", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> PLUME_FOSSIL = register("plume_fossil", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> ROOT_FOSSIL = register("root_fossil", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> SAIL_FOSSIL = register("sail_fossil", Item::new, GenerationsCreativeTabs.NATURAL);
    public static final RegistrySupplier<Item> SKULL_FOSSIL = register("skull_fossil", Item::new, GenerationsCreativeTabs.NATURAL);

    /**
     * Un-Implemented Items : Items currently have no in-game function
     */
    public static final RegistrySupplier<Item> ABILITY_URGE = register("ability_urge", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ADVENTURE_GUIDE = register("adventure_guide", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> APRICORN_BOX = register("apricorn_box", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> AQUA_SUIT = register("aqua_suit", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ARMOR_PASS = register("armor_pass", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ARMORITE_ORE = register("armorite_ore", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> AURORA_TICKET = register("aurora_ticket", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> AUTOGRAPH = register("autograph", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> AZURE_FLUTE = register("azure_flute", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> BAND_AUTOGRAPH = register("band_autograph", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> BASEMENT_KEY_1 = register("basement_key_1", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> BASEMENT_KEY_2 = register("basement_key_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> BERRY_PLANTER = register("berry_planter", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> BERRY_POUCH = register("berry_pouch", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> BIKE_VOUCHER = register("bike_voucher", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> BLACK_MANE_HAIR = register("black_mane_hair", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> BLUE_CARD = register("blue_card", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> BLUE_PETAL = register("blue_petal", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> BLUE_SCARF = register("blue_scarf", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> BLUE_SPHERE = register("blue_sphere", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> CAMPING_GEAR = register("camping_gear", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> CARD_KEY_1 = register("card_key_1", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> CARD_KEY_2 = register("card_key_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> CARROT_SEEDS = register("carrot_seeds", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> CLEAR_BELL = register("clear_bell", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> COIN = register("coin", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> COIN_CASE = register("coin_case", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> COLRESS_MACHINE = register("colress_machine", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> COMMON_STONE = register("common_stone", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> CONTEST_COSTUME_1 = register("contest_costume_1", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> CONTEST_COSTUME_2 = register("contest_costume_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> CONTEST_PASS = register("contest_pass", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> COUPON_1 = register("coupon_1", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> COUPON_2 = register("coupon_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> COUPON_3 = register("coupon_3", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> COURAGE_CANDY = register("courage_candy", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> COURAGE_CANDY_L = register("courage_candy_l", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> COURAGE_CANDY_XL = register("courage_candy_xl", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> CROWN_PASS = register("crown_pass", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> DATA_CARDS = register("data_cards", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> DEVON_PARTS = register("devon_parts", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> DEVON_SCOPE = register("devon_scope", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> DEVON_SCUBA_GEAR = register("devon_scuba_gear", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> DIRE_HIT_2 = register("dire_hit_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> DIRE_HIT_3 = register("dire_hit_3", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> DISCOUNT_COUPON = register("discount_coupon", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> DOWSING_MACHINE_1 = register("dowsing_machine_1", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> DOWSING_MACHINE_2 = register("dowsing_machine_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> DOWSING_MCHN = register("dowsing_mchn", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> DRAGON_SKULL = register("dragon_skull", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> DROPPED_ITEM_RED = register("dropped_item_red", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> DROPPED_ITEM_YELLOW = register("dropped_item_yellow", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> DYNITE_ORE = register("dynite_ore", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ELEVATOR_KEY = register("elevator_key", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ENDORSEMENT = register("endorsement", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> EON_FLUTE = register("eon_flute", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> EON_TICKET = register("eon_ticket", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> EXPLORER_KIT = register("explorer_kit", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> FAME_CHECKER = register("fame_checker", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> FASHION_CASE = register("fashion_case", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> FESTIVAL_TICKET = register("festival_ticket", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> FORAGE_BAG = register("forage_bag", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> GALACTIC_KEY = register("galactic_key", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> GB_SOUNDS = register("gb_sounds", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> GO_GOGGLES = register("go_goggles", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> GOLD_TEETH = register("gold_teeth", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> GOLDEN_NANAB_BERRY = register("golden_nanab_berry", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> GOLDEN_PINAP_BERRY = register("golden_pinap_berry", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> GOLDEN_RAZZ_BERRY = register("golden_razz_berry", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> GRAM = register("gram", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> GREEN_PETAL = register("green_petal", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> GREEN_SCARF = register("green_scarf", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> GREEN_SPHERE = register("green_sphere", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> GREEN_TEA = register("green_tea", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> GRUBBY_HANKY = register("grubby_hanky", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> HEALTH_CANDY = register("health_candy", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> HEALTH_CANDY_L = register("health_candy_l", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> HEALTH_CANDY_XL = register("health_candy_xl", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);

    public static final RegistrySupplier<Item> HI_TECH_EARBUDS = register("hi_tech_earbuds", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> HOLO_CASTER_1 = register("holo_caster_1", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> HOLO_CASTER_2 = register("holo_caster_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> HONOR_OF_KALOS = register("honor_of_kalos", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> INTRIGUING_STONE = register("intriguing_stone", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ITEM_DROP = register("item_drop", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ITEM_URGE = register("item_urge", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> JOURNAL = register("journal", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> KEY_STONE_1 = register("key_stone_1", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> KEY_STONE_2 = register("key_stone_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> KEY_TO_ROOM_1 = register("key_to_room_1", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> KEY_TO_ROOM_2 = register("key_to_room_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> KEY_TO_ROOM_4 = register("key_to_room_4", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> KEY_TO_ROOM_6 = register("key_to_room_6", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> LEAF_LETTER = register("leaf_letter", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> LEGENDARY_CLUE = register("legendary_clue", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> LENS_CASE = register("lens_case", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> LETTER = register("letter", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> LIBERTY_PASS = register("liberty_pass", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> LIFT_KEY = register("lift_key", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> LOCK_CAPSULE_1 = register("lock_capsule_1", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> LOCK_CAPSULE_2 = register("lock_capsule_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> LOOKER_TICKET = register("looker_ticket", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> LOOT_SACK = register("loot_sack", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> LOST_ITEM_1 = register("lost_item_1", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> LOST_ITEM_2 = register("lost_item_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> LUNAR_FEATHER = register("lunar_feather", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> LURE = register("lure", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MACHINE_PART = register("machine_part", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MAGMA_EMBLEM = register("magma_emblem", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MAGMA_SUIT = register("magma_suit", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MAKEUP_BAG = register("makeup_bag", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MAX_LURE = register("max_lure", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MEDAL_BOX = register("medal_box", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MEGA_CHARM = register("mega_charm", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MEGA_CUFF = register("mega_cuff", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MEGA_RING = register("mega_ring", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MEMBER_CARD = register("member_card", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> METEORITE_SHARD = register("meteorite_shard", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MEGASTONE_SHARD = register("mega_stone_shard", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MIGHTY_CANDY = register("mighty_candy", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MIGHTY_CANDY_L = register("mighty_candy_l", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MIGHTY_CANDY_XL = register("mighty_candy_xl", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> MYSTIC_TICKET = register("mystic_ticket", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> OAKS_LETTER = register("oaks_letter", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> OAKS_PARCEL = register("oaks_parcel", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> OLD_CHARM = register("old_charm", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> OLD_LETTER = register("old_letter", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> OLD_SEA_MAP = register("old_sea_map", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ORANGE_PETAL = register("orange_petal", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PAIR_OF_TICKETS = register("pair_of_tickets", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PAL_PAD = register("pal_pad", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PALE_SPHERE = register("pale_sphere", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PASS = register("pass", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PASS_ORB = register("pass_orb", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PERMIT = register("permit", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PEWTER_CRUNCHIES = register("pewter_crunchies", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PINK_PETAL = register("pink_petal", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PINK_SCARF = register("pink_scarf", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PLASMA_CARD = register("plasma_card", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> POFFIN = register("poffin", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> POFFIN_CASE = register("poffin_case", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> POINT_CARD = register("point_card", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> POKE_FLUTE = register("poke_flute", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> POKE_RADAR = register("poke_radar", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> POKEBLOCK_CASE = register("pokeblock_case", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> POKEBLOCK_KIT = register("pokeblock_kit", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> POKEMON_BOX_LINK = register("pokemon_box_link", properties -> new Item(properties.stacksTo(1)) {
        @Override
        public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
            return super.use(level, player, interactionHand);
        }
    }, GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> POKETCH_BLUE = register("poketch_blue", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> POKETCH_RED = register("poketch_red", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> POWDER_JAR = register("powder_jar", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> POWER_PLANT_PASS = register("power_plant_pass", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PRISM_SPHERE = register("prism_sphere", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PROFESSORS_MASK = register("professors_mask", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PROFS_LETTER = register("profs_letter", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PROP_CASE = register("prop_case", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> PURPLE_PETAL = register("purple_petal", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> QUICK_CANDY = register("quick_candy", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> QUICK_CANDY_L = register("quick_candy_l", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> QUICK_CANDY_XL = register("quick_candy_xl", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> RADIANT_PETAL = register("radiant_petal", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> RAINBOW_FLOWER = register("rainbow_flower", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> RAINBOW_PASS = register("rainbow_pass", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> RED_PETAL = register("red_petal", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> RED_SCALE = register("red_scale", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> RED_SPHERE = register("red_sphere", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> RESET_URGE = register("reset_urge", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> RIDE_PAGER = register("ride_pager", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ROLLER_SKATES = register("roller_skates", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ROTO_BARGAIN = register("roto_bargain", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ROTO_BOOST = register("roto_boost", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ROTO_CATCH = register("roto_catch", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ROTO_ENCOUNTER = register("roto_encounter", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ROTO_EXP_POINTS = register("roto_exp_points", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ROTO_FRIENDSHIP = register("roto_friendship", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ROTO_HATCH = register("roto_hatch", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ROTO_HP_RESTORE = register("roto_hp_restore", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ROTO_PP_RESTORE = register("roto_pp_restore", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ROTO_PRIZE_MONEY = register("roto_prize_money", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> ROTO_STEALTH = register("roto_stealth", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> RULE_BOOK = register("rule_book", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SCANNER = register("scanner", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SEAL_BAG = register("seal_bag", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SEAL_CASE = register("seal_case", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SECRET_KEY_1 = register("secret_key_1", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SECRET_KEY_2 = register("secret_key_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SECRET_MEDICINE = register("secret_medicine", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SILPH_SCOPE = register("silph_scope", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SILVER_NANAB_BERRY = register("silver_nanab_berry", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SILVER_PINAP_BERRY = register("silver_pinap_berry", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SILVER_RAZZ_BERRY = register("silver_razz_berry", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SILVER_WING = register("silver_wing", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SINNOH_STONE = register("sinnoh_stone", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SMART_CANDY = register("smart_candy", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SMART_CANDY_L = register("smart_candy_l", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SMART_CANDY_XL = register("smart_candy_xl", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SONIAS_BOOK = register("sonias_book", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SOOT_SACK = register("soot_sack", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SPRAYDUCK = register("sprayduck", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SQUIRT_BOTTLE = register("squirt_bottle", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SS_TICKET = register("ss_ticket", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> STORAGE_KEY_1 = register("storage_key_1", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> STORAGE_KEY_2 = register("storage_key_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> STYLE_CARD = register("style_card", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SUITE_KEY = register("suite_key", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SUPER_LURE = register("super_lure", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> SURGE_BADGE = register("surge_badge", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TEACHY_TV = register("teachy_tv", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TIDAL_BELL = register("tidal_bell", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TM_CASE = register("tm_case", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TM_MATERIAL = register("tm_material", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TMV_PASS = register("tmv_pass", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TOTEM_STICKER = register("totem_sticker", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TOUGH_CANDY = register("tough_candy", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TOUGH_CANDY_L = register("tough_candy_l", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TOUGH_CANDY_XL = register("tough_candy_xl", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TOWN_MAP_1 = register("town_map_1", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TOWN_MAP_2 = register("town_map_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TOWN_MAP_3 = register("town_map_3", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TRAVEL_TRUNK_1 = register("travel_trunk_1", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TRAVEL_TRUNK_2 = register("travel_trunk_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> TRI_PASS = register("tri_pass", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> UNOVA_STONE = register("unova_stone", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> UNOWN_REPORT = register("unown_report", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> VS_RECORDER = register("vs_recorder", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> VS_SEEKER = register("vs_seeker", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> WHITE_MANE_HAIR = register("white_mane_hair", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> WISHING_CHIP = register("wishing_chip", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> WISHING_PIECE = register("wishing_piece", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> WOODEN_CROWN = register("wooden_crown", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> WORKS_KEY = register("works_key", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_ACCURACY_2 = register("x_accuracy_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_ACCURACY_3 = register("x_accuracy_3", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_ACCURACY_6 = register("x_accuracy_6", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_ATTACK_2 = register("x_attack_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_ATTACK_3 = register("x_attack_3", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_ATTACK_6 = register("x_attack_6", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_DEFENSE_2 = register("x_defense_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_DEFENSE_3 = register("x_defense_3", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_DEFENSE_6 = register("x_defense_6", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_SPECIAL_ATTACK_2 = register("x_special_attack_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_SPECIAL_ATTACK_3 = register("x_special_attack_3", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_SPECIAL_ATTACK_6 = register("x_special_attack_6", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_SPECIAL_DEFENSE_2 = register("x_special_defense_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_SPECIAL_DEFENSE_3 = register("x_special_defense_3", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_SPECIAL_DEFENSE_6 = register("x_special_defense_6", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_SPEED_2 = register("x_speed_2", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_SPEED_3 = register("x_speed_3", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_SPEED_6 = register("x_speed_6", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_TRANSCEIVER_BLUE = register("x_transceiver_blue", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_TRANSCEIVER_RED = register("x_transceiver_red", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> X_TRANSCEIVER_YELLOW = register("x_transceiver_yellow", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> YELLOW_PETAL = register("yellow_petal", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> YELLOW_SCARF = register("yellow_scarf", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> Z_POWER_RING = register("z_power_ring", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);
    public static final RegistrySupplier<Item> Z_RING = register("z_ring", properties -> new Item(properties.stacksTo(1)), GenerationsCreativeTabs.UNIMPLEMENTED);

    /**
     * Items to be included in Minecraft Default Section.
     */

    public static final RegistrySupplier<Item> CURRY = register("curry", ItemCurry::new, GenerationsCreativeTabs.CUISINE);

    public static final RegistrySupplier<Item> ULTRA_DARK_SIGN = registerSign("ultra_dark_sign", properties -> new SignItem(properties.stacksTo(16), GenerationsWood.ULTRA_DARK_SIGN.get(), GenerationsWood.ULTRA_DARK_WALL_SIGN.get()), GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> ULTRA_DARK_HANGING_SIGN = registerSign("ultra_dark_hanging_sign", properties -> new HangingSignItem(GenerationsWood.ULTRA_DARK_HANGING_SIGN.get(), GenerationsWood.ULTRA_DARK_WALL_HANGING_SIGN.get(), properties.stacksTo(16)), GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> ULTRA_JUNGLE_SIGN = registerSign("ultra_jungle_sign", properties -> new SignItem(properties.stacksTo(16), GenerationsWood.ULTRA_JUNGLE_SIGN.get(), GenerationsWood.ULTRA_JUNGLE_WALL_SIGN.get()), GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> ULTRA_JUNGLE_HANGING_SIGN = registerSign("ultra_jungle_hanging_sign", properties -> new HangingSignItem(GenerationsWood.ULTRA_JUNGLE_HANGING_SIGN.get(), GenerationsWood.ULTRA_JUNGLE_WALL_HANGING_SIGN.get(), properties.stacksTo(16)), GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> GHOST_SIGN = registerSign("ghost_sign", properties -> new SignItem(properties.stacksTo(16), GenerationsWood.GHOST_SIGN.get(), GenerationsWood.GHOST_WALL_SIGN.get()), GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> GHOST_HANGING_SIGN = registerSign("ghost_hanging_sign", properties -> new HangingSignItem(GenerationsWood.GHOST_HANGING_SIGN.get(), GenerationsWood.GHOST_WALL_HANGING_SIGN.get(), properties.stacksTo(16)), GenerationsCreativeTabs.BUILDING_BLOCKS);

    public static final RegistrySupplier<Item> GHOST_BOAT_ITEM = register("ghost_boat", properties -> new GenerationsBoatItem(properties, GenerationsBoatEntity.Type.GHOST), GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> GHOST_CHEST_BOAT_ITEM = register("ghost_boat_with_chest", properties -> new GenerationsChestBoatItem(properties, GenerationsChestBoatEntity.Type.GHOST), GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> ULTRA_DARK_BOAT_ITEM = register("ultra_dark_boat", properties -> new GenerationsBoatItem(properties, GenerationsBoatEntity.Type.ULTRA_DARK), GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> ULTRA_DARK_CHEST_BOAT_ITEM = register("ultra_dark_boat_with_chest", properties -> new GenerationsChestBoatItem(properties, GenerationsChestBoatEntity.Type.ULTRA_DARK), GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> ULTRA_JUNGLE_BOAT_ITEM = register("ultra_jungle_boat", properties -> new GenerationsBoatItem(properties, GenerationsBoatEntity.Type.ULTRA_JUNGLE), GenerationsCreativeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Item> ULTRA_JUNGLE_CHEST_BOAT_ITEM = register("ultra_jungle_boat_with_chest", properties -> new GenerationsChestBoatItem(properties, GenerationsChestBoatEntity.Type.ULTRA_JUNGLE), GenerationsCreativeTabs.BUILDING_BLOCKS);

    public static final RegistrySupplier<Item> POKE_WALKMON = register("poke_walkmon", properties -> new WalkmonItem(properties, 1, "poke_walkmon"), GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> GREAT_WALKMON = register("great_walkmon", properties -> new WalkmonItem(properties, 2, "great_walkmon"), GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> ULTRA_WALKMON = register("ultra_walkmon", properties -> new WalkmonItem(properties, 3, "ultra_walkmon"), GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> MASTER_WALKMON = register("master_walkmon", properties -> new WalkmonItem(properties, 4, "master_walkmon"), GenerationsCreativeTabs.UTILITY);
    public static final RegistrySupplier<Item> AZALEA_TOWN_DISC = createMusicDisc("azalea_town_disc", GenerationsSounds.AZALEA_TOWN, 4000);
    public static final RegistrySupplier<Item> CASCARRAFA_CITY_DISC = createMusicDisc("cascarrafa_city_disc", GenerationsSounds.CASCARRAFA_CITY, 3396);
    public static final RegistrySupplier<Item> CERULEAN_CITY_DISC = createMusicDisc("cerulean_city_disc", GenerationsSounds.CERULEAN_CITY, 3677);
    public static final RegistrySupplier<Item> ETERNA_CITY_DISC = createMusicDisc("eterna_city_disc", GenerationsSounds.ETERNA_CITY, 2720);
    public static final RegistrySupplier<Item> GOLDENROD_CITY_DISC = createMusicDisc("goldenrod_city_disc", GenerationsSounds.GOLDENROD_CITY, 3642);
    public static final RegistrySupplier<Item> ICIRRUS_CITY_DISC = createMusicDisc("icirrus_city_disc", GenerationsSounds.ICIRRUS_CITY, 2958);
    public static final RegistrySupplier<Item> JUBILIFE_VILLAGE_DISC = createMusicDisc("jubilife_village_disc", GenerationsSounds.JUBILIFE_VILLAGE, 4048);
    public static final RegistrySupplier<Item> LAKE_OF_RAGE_DISC = createMusicDisc("lake_of_rage_disc", GenerationsSounds.LAKE_OF_RAGE, 2773);
    public static final RegistrySupplier<Item> LAVERRE_CITY_DISC = createMusicDisc("laverre_city_disc", GenerationsSounds.LAVERRE_CITY, 5626);
    public static final RegistrySupplier<Item> LILLIE_DISC = createMusicDisc("lillie_disc", GenerationsSounds.LILLIE, 6241);
    public static final RegistrySupplier<Item> POKEMON_CENTER_DISC = createMusicDisc("pokemon_center_disc", GenerationsSounds.POKEMON_CENTER, 4806);
    public static final RegistrySupplier<Item> ROUTE_228_DISC = createMusicDisc("route_228_disc", GenerationsSounds.ROUTE_228, 8374);
    public static final RegistrySupplier<Item> SLUMBERING_WEALD_DISC = createMusicDisc("slumbering_weald_disc", GenerationsSounds.SLUMBERING_WEALD, 5242);
    public static final RegistrySupplier<Item> SURF_DISC = createMusicDisc("surf_disc", GenerationsSounds.SURF, 5230);
    public static final RegistrySupplier<Item> VERMILION_CITY_DISC = createMusicDisc("vermilion_city_disc", GenerationsSounds.VERMILION_CITY, 4320);
    public static final RegistrySupplier<Item> CYNTHIA_DISC = createMusicDisc("cynthia_disc", GenerationsSounds.CYNTHIA, 7705);
    public static final RegistrySupplier<Item> DEOXYS_DISC = createMusicDisc("deoxys_disc", GenerationsSounds.DEOXYS, 8290);
    public static final RegistrySupplier<Item> IRIS_DISC = createMusicDisc("iris_disc", GenerationsSounds.IRIS, 5824);
    public static final RegistrySupplier<Item> KANTO_DISC = createMusicDisc("kanto_disc", GenerationsSounds.KANTO, 6748);
    public static final RegistrySupplier<Item> LUSAMINE_DISC = createMusicDisc("lusamine_disc", GenerationsSounds.LUSAMINE, 6756);
    public static final RegistrySupplier<Item> NEMONA_DISC = createMusicDisc("nemona_disc", GenerationsSounds.NEMONA, 3169);
    public static final RegistrySupplier<Item> NESSA_DISC = createMusicDisc("nessa_disc", GenerationsSounds.NESSA, 5276);
    public static final RegistrySupplier<Item> PENNY_DISC = createMusicDisc("penny_disc", GenerationsSounds.PENNY, 5342);
    public static final RegistrySupplier<Item> RIVAL_DISC = createMusicDisc("rival_disc", GenerationsSounds.RIVAL, 4426);
    public static final RegistrySupplier<Item> SADA_AND_TURO_DISC = createMusicDisc("sada_and_turo_disc", GenerationsSounds.SADA_AND_TURO, 6974);
    public static final RegistrySupplier<Item> SOUTH_PROVINCE_DISC = createMusicDisc("south_province_disc", GenerationsSounds.SOUTH_PROVINCE, 4285);
    public static final RegistrySupplier<Item> TEAM_ROCKET_DISC = createMusicDisc("team_rocket_disc", GenerationsSounds.TEAM_ROCKET, 3729);
    public static final RegistrySupplier<Item> ULTRA_NECROZMA_DISC = createMusicDisc("ultra_necrozma_disc", GenerationsSounds.ULTRA_NECROZMA, 5925);
    public static final RegistrySupplier<Item> XY_LEGENDARY_DISC = createMusicDisc("xy_legendary_disc", GenerationsSounds.XY_LEGENDARY, 5216);
    public static final RegistrySupplier<Item> ZINNIA_DISC = createMusicDisc("zinnia_disc", GenerationsSounds.ZINNIA, 6409);
    public static final RegistrySupplier<Item> LAVENDER_TOWN_DISC = createMusicDisc("lavender_town_disc", GenerationsSounds.LAVENDER_TOWN, 7376);
    public static final RegistrySupplier<Item> LUGIA_DISC = createMusicDisc("lugia_disc", GenerationsSounds.LUGIA, 6825);
    public static final RegistrySupplier<Item> MT_PYRE_DISC = createMusicDisc("mt_pyre_disc", GenerationsSounds.MT_PYRE, 4372);

    private static RegistrySupplier<Item> createMusicDisc(String name, RegistrySupplier<SoundEvent> sound, int ticks) {
        return register(name, properties -> new ArchitecturyRecordItem(0, sound, properties, ticks), GenerationsCreativeTabs.UNIMPLEMENTED);
    }

    public static Item.Properties of() {
        return new Item.Properties();
    }

    public static RegistrySupplier<Item> registerPokeBall(String name, Function<Item.Properties, Item> itemSupplier) {
        return POKEBALLS.register(name, () -> itemSupplier.apply(of().arch$tab(GenerationsCreativeTabs.POKEBALLS)));
    }

    public static RegistrySupplier<Item> register(String name, Function<Item.Properties, Item> itemSupplier, RegistrySupplier<CreativeModeTab> tab) {
        return ITEMS.register(name, () -> itemSupplier.apply(of().arch$tab(tab)));
    }

    public static RegistrySupplier<Item> registerSign(String name, Function<Item.Properties, Item> itemSupplier, RegistrySupplier<CreativeModeTab> tab) {
        return register(name, itemSupplier, tab);
    }

    public static RegistrySupplier<Item> createBadge(String id, Object object/*ElementType elementType*/) {
        /*elementType*/
        return BADGES.register(id, () -> new BadgeItem(new Item.Properties().arch$tab(GenerationsCreativeTabs.BADGES)));
    }

    public static RegistrySupplier<Item> createRibbon(String id) {
        return RIBBONS.register(id, () -> new RibbonItem(new Item.Properties().arch$tab(GenerationsCreativeTabs.RIBBONS)));
    }
    
    public static void init() {
        GenerationsCore.LOGGER.info("Registering Generations Items");
        ITEMS.register();
        BADGES.register();
        RIBBONS.register();
        GenerationsCore.LOGGER.info("Registering Generations PokeBalls");
        POKEBALLS.register();
    }
}
