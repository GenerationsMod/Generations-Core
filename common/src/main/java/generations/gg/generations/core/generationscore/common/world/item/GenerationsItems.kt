package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.api.types.ElementalTypes
import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.api.types.tera.TeraTypes
import com.cobblemon.mod.common.item.PokeBallItem
import com.cobblemon.mod.common.pokemon.helditem.CobblemonHeldItemManager
import com.cobblemon.mod.common.util.cobblemonResource
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.tab
import generations.gg.generations.core.generationscore.common.util.ItemPlatformRegistry
import generations.gg.generations.core.generationscore.common.world.GenerationsPokeBalls
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsBoatEntity
import generations.gg.generations.core.generationscore.common.world.entity.TieredFishingHookEntity
import generations.gg.generations.core.generationscore.common.world.item.FormChangingItems.Companion.createFormChangingItem
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryIngredient
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryType
import generations.gg.generations.core.generationscore.common.world.item.curry.ItemCurry
import generations.gg.generations.core.generationscore.common.world.item.legends.*
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWood
import net.minecraft.resources.ResourceKey
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.HangingSignItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.JukeboxSong
import net.minecraft.world.item.SignItem

/**
 * Generations Items
 * @see net.minecraft.world.item.Item
 * @author Joseph T. McQuigg
 * @author WaterPicker
 */
@SuppressWarnings("unused")
object GenerationsItems {
    val TMS = mutableListOf<TechnicalMachineItem>()

    /** Generations Items Deferred Register */
    val ITEMS = object: ItemPlatformRegistry() {}

    /** Generations Ribbons Deferred Register */
    val RIBBONS = object: ItemPlatformRegistry() {}

    /** Generations Badges Deferred Register */
    val BADGES = object: ItemPlatformRegistry() {}

    /** Generations Unimplemented Deferred Register */
    val UNIMPLEMENTED = object: ItemPlatformRegistry() {}

    /** Generations Cusine Deferred Register */
    val CUISINE = object: ItemPlatformRegistry() {}

    /** Generations Natural Deferred Register */
    val NATURAL = object: ItemPlatformRegistry() {}

    /** Generations Restoration Deferred Register */
    val RESTORATION = object: ItemPlatformRegistry() {}

    /** Generations Player Items Deferred Register */
    val PLAYER_ITEMS = object: ItemPlatformRegistry() {}

    /** Generations HeldItems Deferred Register */
    val HELD_ITEMS = object: ItemPlatformRegistry() {}

    /** Generations Pokemail Deferred Register */
    val POKEMAIL = object: ItemPlatformRegistry() {}

    /** Generations Legendary Items Deferred Register */
    val LEGENDARY_ITEMS = object: ItemPlatformRegistry() {}

    /** Generations Utility Deferred Register */
    val UTILITY = object: ItemPlatformRegistry() {}

    /** Generations Valuables Deferred Register */
    val VALUABLES = object: ItemPlatformRegistry() {}

    /** Generations Form Items Deferred Register */
    val FORM_ITEMS = object: ItemPlatformRegistry() {}

    /** Generations Building Blocks Deferred Register */
    val BUILDING_BLOCKS = object: ItemPlatformRegistry() {}

    /**
     * Restoration Items
     */
    val PURPLE_JUICE = register("purple_juice", ::Item, RESTORATION)
    val RED_JUICE = register("red_juice", ::Item, RESTORATION)
    val YELLOW_JUICE = register("yellow_juice", ::Item, RESTORATION)
    val BLUE_JUICE = register("blue_juice", ::Item, RESTORATION)
    val GREEN_JUICE = register("green_juice", ::Item, RESTORATION)
    val PINK_JUICE = register("pink_juice", ::Item, RESTORATION)
    val COLORFUL_SHAKE = register("colorful_shake", ::Item, RESTORATION)
    val PERILOUS_SOUP = register("perilous_soup", ::Item, RESTORATION)
    val RARE_SODA = register("rare_soda", ::Item, RESTORATION)
    val ULTRA_RARE_SODA = register("ultra_rare_soda", ::Item, RESTORATION)
    val FRESH_WATER = register("fresh_water", ::Item, RESTORATION)
    val SODA_POP = register("soda_pop", ::Item, RESTORATION)
    val LEMONADE = register("lemonade", ::Item, RESTORATION)
    val MOOMOO_MILK = register("moomoo_milk", ::Item, RESTORATION)
    val LUMIOSE_GALETTE = register("lumiose_galette", ::Item, RESTORATION)
    val SHALOUR_SABLE = register("shalour_sable", ::Item, RESTORATION)
    val CASTELIACONE = register("casteliacone", ::Item, RESTORATION)
    val OLD_GATEAU = register("old_gateau", ::Item, RESTORATION)
    val BIG_MALASADA = register("big_malasada", ::Item, RESTORATION)
    val LAVA_COOKIE = register("lava_cookie", ::Item, RESTORATION)
    val RAGE_CANDY_BAR = register("rage_candy_bar", ::Item, RESTORATION)
    val BLUE_FLUTE = register("blue_flute", ::Item, RESTORATION)
    val RED_FLUTE = register("red_flute", ::Item, RESTORATION)
    val YELLOW_FLUTE = register("yellow_flute", ::Item, RESTORATION)
    val ENERGY_POWDER = register("energy_powder", ::Item, RESTORATION)
    val SWEET_HEART = register("sweet_heart", ::Item, RESTORATION)
    val BASIC_SWEET_POKE_PUFF = register("basic_sweet_poke_puff", ::Item, RESTORATION)
    val BASIC_CITRUS_POKE_PUFF = register("basic_citrus_poke_puff", ::Item, RESTORATION)
    val BASIC_MINT_POKE_PUFF = register("basic_mint_poke_puff", ::Item, RESTORATION)
    val BASIC_MOCHA_POKE_PUFF = register("basic_mocha_poke_puff", ::Item, RESTORATION)
    val BASIC_SPICE_POKE_PUFF = register("basic_spice_poke_puff", ::Item, RESTORATION)

    /**
     * Consumable Items
     */
    val DYNAMAX_CANDY = register("dynamax_candy", ::Item, RESTORATION)
    val MAX_HONEY = register("max_honey", ::Item, RESTORATION)
    val MAX_MUSHROOMS = register("max_mushrooms", ::Item, RESTORATION)
    val MAX_POWDER = register("max_powder", ::Item, RESTORATION)
    val MAX_SOUP = register("max_soup", ::Item, RESTORATION)

    /**
     * TM ITEMS
     */
    val CUSTOM_TM = registerTm("tm", ::CustomTechnicalMachineItem)


    val TM_1 = registerTmRegular("tm_1", "takedown")
    val TM_2 = registerTmRegular("tm_2", "charm")
    val TM_3 = registerTmRegular("tm_3", "faketears")
    val TM_4 = registerTmRegular("tm_4", "agility")
    val TM_5 = registerTmRegular("tm_5", "mudslap")
    val TM_6 = registerTmRegular("tm_6", "scaryface")
    val TM_7 = registerTmRegular("tm_7", "protect")
    val TM_8 = registerTmRegular("tm_8", "firefang")
    val TM_9 = registerTmRegular("tm_9", "thunderfang")
    val TM_10 = registerTmRegular("tm_10", "icefang")
    val TM_11 = registerTmRegular("tm_11", "waterpulse")
    val TM_12 = registerTmRegular("tm_12", "lowkick")
    val TM_13 = registerTmRegular("tm_13", "acidspray")
    val TM_14 = registerTmRegular("tm_14", "acrobatics")
    val TM_15 = registerTmRegular("tm_15", "strugglebug")
    val TM_16 = registerTmRegular("tm_16", "psybeam")
    val TM_17 = registerTmRegular("tm_17", "confuseray")
    val TM_18 = registerTmRegular("tm_18", "thief")
    val TM_19 = registerTmRegular("tm_19", "disarmingvoice")
    val TM_20 = registerTmRegular("tm_20", "trailblaze")
    val TM_21 = registerTmRegular("tm_21", "pounce")
    val TM_22 = registerTmRegular("tm_22", "chillingwater")
    val TM_23 = registerTmRegular("tm_23", "chargebeam")
    val TM_24 = registerTmRegular("tm_24", "firespin")
    val TM_25 = registerTmRegular("tm_25", "facade")
    val TM_26 = registerTmRegular("tm_26", "poisontail")
    val TM_27 = registerTmRegular("tm_27", "aerialace")
    val TM_28 = registerTmRegular("tm_28", "bulldoze")
    val TM_29 = registerTmRegular("tm_29", "hex")
    val TM_30 = registerTmRegular("tm_30", "snarl")
    val TM_31 = registerTmRegular("tm_31", "metalclaw")
    val TM_32 = registerTmRegular("tm_32", "swift")
    val TM_33 = registerTmRegular("tm_33", "magicalleaf")
    val TM_34 = registerTmRegular("tm_34", "icywind")
    val TM_35 = registerTmRegular("tm_35", "mudshot")
    val TM_36 = registerTmRegular("tm_36", "rocktomb")
    val TM_37 = registerTmRegular("tm_37", "drainingkiss")
    val TM_38 = registerTmRegular("tm_38", "flamecharge")
    val TM_39 = registerTmRegular("tm_39", "lowsweep")
    val TM_40 = registerTmRegular("tm_40", "aircutter")
    val TM_41 = registerTmRegular("tm_41", "storedpower")
    val TM_42 = registerTmRegular("tm_42", "nightshade")
    val TM_43 = registerTmRegular("tm_43", "fling")
    val TM_44 = registerTmRegular("tm_44", "dragontail")
    val TM_45 = registerTmRegular("tm_45", "venoshock")
    val TM_46 = registerTmRegular("tm_46", "avalanche")
    val TM_47 = registerTmRegular("tm_47", "endure")
    val TM_48 = registerTmRegular("tm_48", "voltswitch")
    val TM_49 = registerTmRegular("tm_49", "sunnyday")
    val TM_50 = registerTmRegular("tm_50", "raindance")
    val TM_51 = registerTmRegular("tm_51", "sandstorm")
    val TM_52 = registerTmRegular("tm_52", "snowscape")
    val TM_53 = registerTmRegular("tm_53", "smartstrike")
    val TM_54 = registerTmRegular("tm_54", "psyshock")
    val TM_55 = registerTmRegular("tm_55", "dig")
    val TM_56 = registerTmRegular("tm_56", "bulletseed")
    val TM_57 = registerTmRegular("tm_57", "falseswipe")
    val TM_58 = registerTmRegular("tm_58", "brickbreak")
    val TM_59 = registerTmRegular("tm_59", "zenheadbutt")
    val TM_60 = registerTmRegular("tm_60", "uturn")
    val TM_61 = registerTmRegular("tm_61", "shadowclaw")
    val TM_62 = registerTmRegular("tm_62", "foulplay")
    val TM_63 = registerTmRegular("tm_63", "psychicfangs")
    val TM_64 = registerTmRegular("tm_64", "bulkup")
    val TM_65 = registerTmRegular("tm_65", "airslash")
    val TM_66 = registerTmRegular("tm_66", "bodyslam")
    val TM_67 = registerTmRegular("tm_67", "firepunch")
    val TM_68 = registerTmRegular("tm_68", "thunderpunch")
    val TM_69 = registerTmRegular("tm_69", "icepunch")
    val TM_70 = registerTmRegular("tm_70", "sleeptalk")
    val TM_71 = registerTmRegular("tm_71", "seedbomb")
    val TM_72 = registerTmRegular("tm_72", "electroball")
    val TM_73 = registerTmRegular("tm_73", "drainpunch")
    val TM_74 = registerTmRegular("tm_74", "reflect")
    val TM_75 = registerTmRegular("tm_75", "lightscreen")
    val TM_76 = registerTmRegular("tm_76", "rockblast")
    val TM_77 = registerTmRegular("tm_77", "waterfall")
    val TM_78 = registerTmRegular("tm_78", "dragonclaw")
    val TM_79 = registerTmRegular("tm_79", "dazzlinggleam")
    val TM_80 = registerTmRegular("tm_80", "metronome")
    val TM_81 = registerTmRegular("tm_81", "grassknot")
    val TM_82 = registerTmRegular("tm_82", "thunderwave")
    val TM_83 = registerTmRegular("tm_83", "poisonjab")
    val TM_84 = registerTmRegular("tm_84", "stompingtantrum")
    val TM_85 = registerTmRegular("tm_85", "rest")
    val TM_86 = registerTmRegular("tm_86", "rockslide")
    val TM_87 = registerTmRegular("tm_87", "taunt")
    val TM_88 = registerTmRegular("tm_88", "swordsdance")
    val TM_89 = registerTmRegular("tm_89", "bodypress")
    val TM_90 = registerTmRegular("tm_90", "spikes")
    val TM_91 = registerTmRegular("tm_91", "toxicspikes")
    val TM_92 = registerTmRegular("tm_92", "imprison")
    val TM_93 = registerTmRegular("tm_93", "flashcannon")
    val TM_94 = registerTmRegular("tm_94", "darkpulse")
    val TM_95 = registerTmRegular("tm_95", "leechlife")
    val TM_96 = registerTmRegular("tm_96", "eerieimpulse")
    val TM_97 = registerTmRegular("tm_97", "fly")
    val TM_98 = registerTmRegular("tm_98", "skillswap")
    val TM_99 = registerTmRegular("tm_99", "ironhead")
    val TM_100 = registerTmRegular("tm_100", "dragondance")
    val TM_101 = registerTmRegular("tm_101", "powergem")
    val TM_102 = registerTmRegular("tm_102", "gunkshot")
    val TM_103 = registerTmRegular("tm_103", "substitute")
    val TM_104 = registerTmRegular("tm_104", "irondefense")
    val TM_105 = registerTmRegular("tm_105", "xscissor")
    val TM_106 = registerTmRegular("tm_106", "drillrun")
    val TM_107 = registerTmRegular("tm_107", "willowisp")
    val TM_108 = registerTmRegular("tm_108", "crunch")
    val TM_109 = registerTmRegular("tm_109", "trick")
    val TM_110 = registerTmRegular("tm_110", "liquidation")
    val TM_111 = registerTmRegular("tm_111", "gigadrain")
    val TM_112 = registerTmRegular("tm_112", "aurasphere")
    val TM_113 = registerTmRegular("tm_113", "tailwind")
    val TM_114 = registerTmRegular("tm_114", "shadowball")
    val TM_115 = registerTmRegular("tm_115", "dragonpulse")
    val TM_116 = registerTmRegular("tm_116", "stealthrock")
    val TM_117 = registerTmRegular("tm_117", "hypervoice")
    val TM_118 = registerTmRegular("tm_118", "heatwave")
    val TM_119 = registerTmRegular("tm_119", "energyball")
    val TM_120 = registerTmRegular("tm_120", "psychic")
    val TM_121 = registerTmRegular("tm_121", "heavyslam")
    val TM_122 = registerTmRegular("tm_122", "encore")
    val TM_123 = registerTmRegular("tm_123", "surf")
    val TM_124 = registerTmRegular("tm_124", "icespinner")
    val TM_125 = registerTmRegular("tm_125", "flamethrower")
    val TM_126 = registerTmRegular("tm_126", "thunderbolt")
    val TM_127 = registerTmRegular("tm_127", "playrough")
    val TM_128 = registerTmRegular("tm_128", "amnesia")
    val TM_129 = registerTmRegular("tm_129", "calmmind")
    val TM_130 = registerTmRegular("tm_130", "helpinghand")
    val TM_131 = registerTmRegular("tm_131", "pollenpuff")
    val TM_132 = registerTmRegular("tm_132", "batonpass")
    val TM_133 = registerTmRegular("tm_133", "earthpower")
    val TM_134 = registerTmRegular("tm_134", "reversal")
    val TM_135 = registerTmRegular("tm_135", "icebeam")
    val TM_136 = registerTmRegular("tm_136", "electricterrain")
    val TM_137 = registerTmRegular("tm_137", "grassyterrain")
    val TM_138 = registerTmRegular("tm_138", "psychicterrain")
    val TM_139 = registerTmRegular("tm_139", "mistyterrain")
    val TM_140 = registerTmRegular("tm_140", "nastyplot")
    val TM_141 = registerTmRegular("tm_141", "fireblast")
    val TM_142 = registerTmRegular("tm_142", "hydropump")
    val TM_143 = registerTmRegular("tm_143", "blizzard")
    val TM_144 = registerTmRegular("tm_144", "firepledge")
    val TM_145 = registerTmRegular("tm_145", "waterpledge")
    val TM_146 = registerTmRegular("tm_146", "grasspledge")
    val TM_147 = registerTmRegular("tm_147", "wildcharge")
    val TM_148 = registerTmRegular("tm_148", "sludgebomb")
    val TM_149 = registerTmRegular("tm_149", "earthquake")
    val TM_150 = registerTmRegular("tm_150", "stoneedge")
    val TM_151 = registerTmRegular("tm_151", "phantomforce")
    val TM_152 = registerTmRegular("tm_152", "gigaimpact")
    val TM_153 = registerTmRegular("tm_153", "blastburn")
    val TM_154 = registerTmRegular("tm_154", "hydrocannon")
    val TM_155 = registerTmRegular("tm_155", "frenzyplant")
    val TM_156 = registerTmRegular("tm_156", "outrage")
    val TM_157 = registerTmRegular("tm_157", "overheat")
    val TM_158 = registerTmRegular("tm_158", "focusblast")
    val TM_159 = registerTmRegular("tm_159", "leafstorm")
    val TM_160 = registerTmRegular("tm_160", "hurricane")
    val TM_161 = registerTmRegular("tm_161", "trickroom")
    val TM_162 = registerTmRegular("tm_162", "bugbuzz")
    val TM_163 = registerTmRegular("tm_163", "hyperbeam")
    val TM_164 = registerTmRegular("tm_164", "bravebird")
    val TM_165 = registerTmRegular("tm_165", "flareblitz")
    val TM_166 = registerTmRegular("tm_166", "thunder")
    val TM_167 = registerTmRegular("tm_167", "closecombat")
    val TM_168 = registerTmRegular("tm_168", "solarbeam")
    val TM_169 = registerTmRegular("tm_169", "dracometeor")
    val TM_170 = registerTmRegular("tm_170", "steelbeam")
    val TM_171 = registerTmRegular("tm_171", "terablast")
    val TM_172 = registerTmRegular("tm_172", "roar")
    val TM_173 = registerTmRegular("tm_173", "charge")
    val TM_174 = registerTmRegular("tm_174", "haze")
    val TM_175 = registerTmRegular("tm_175", "toxic")
    val TM_176 = registerTmRegular("tm_176", "sandtomb")
    val TM_177 = registerTmRegular("tm_177", "spite")
    val TM_178 = registerTmRegular("tm_178", "gravity")
    val TM_179 = registerTmRegular("tm_179", "smackdown")
    val TM_180 = registerTmRegular("tm_180", "gyroball")
    val TM_181 = registerTmRegular("tm_181", "knockoff")
    val TM_182 = registerTmRegular("tm_182", "bugbite")
    val TM_183 = registerTmRegular("tm_183", "superfang")
    val TM_184 = registerTmRegular("tm_184", "vacuumwave")
    val TM_185 = registerTmRegular("tm_185", "lunge")
    val TM_186 = registerTmRegular("tm_186", "highhorsepower")
    val TM_187 = registerTmRegular("tm_187", "iciclespear")
    val TM_188 = registerTmRegular("tm_188", "scald")
    val TM_189 = registerTmRegular("tm_189", "heatcrash")
    val TM_190 = registerTmRegular("tm_190", "solarblade")
    val TM_191 = registerTmRegular("tm_191", "uproar")
    val TM_192 = registerTmRegular("tm_192", "focuspunch")
    val TM_193 = registerTmRegular("tm_193", "weatherball")
    val TM_194 = registerTmRegular("tm_194", "grassyglide")
    val TM_195 = registerTmRegular("tm_195", "burningjealousy")
    val TM_196 = registerTmRegular("tm_196", "flipturn")
    val TM_197 = registerTmRegular("tm_197", "dualwingbeat")
    val TM_198 = registerTmRegular("tm_198", "poltergeist")
    val TM_199 = registerTmRegular("tm_199", "lashout")
    val TM_200 = registerTmRegular("tm_200", "scaleshot")
    val TM_201 = registerTmRegular("tm_201", "mistyexplosion")
    val TM_202 = registerTmRegular("tm_202", "painsplit")
    val TM_203 = registerTmRegular("tm_203", "psychup")
    val TM_204 = registerTmRegular("tm_204", "doubleedge")
    val TM_205 = registerTmRegular("tm_205", "endeavor")
    val TM_206 = registerTmRegular("tm_206", "petalblizzard")
    val TM_207 = registerTmRegular("tm_207", "temperflare")
    val TM_208 = registerTmRegular("tm_208", "whirlpool")
    val TM_209 = registerTmRegular("tm_209", "muddywater")
    val TM_210 = registerTmRegular("tm_210", "supercellslam")
    val TM_211 = registerTmRegular("tm_211", "electroweb")
    val TM_212 = registerTmRegular("tm_212", "tripleaxel")
    val TM_213 = registerTmRegular("tm_213", "coaching")
    val TM_214 = registerTmRegular("tm_214", "sludgewave")
    val TM_215 = registerTmRegular("tm_215", "scorchingsands")
    val TM_216 = registerTmRegular("tm_216", "featherdance")
    val TM_217 = registerTmRegular("tm_217", "futuresight")
    val TM_218 = registerTmRegular("tm_218", "expandingforce")
    val TM_219 = registerTmRegular("tm_219", "skittersmack")
    val TM_220 = registerTmRegular("tm_220", "meteorbeam")
    val TM_221 = registerTmRegular("tm_221", "throatchop")
    val TM_222 = registerTmRegular("tm_222", "breakingswipe")
    val TM_223 = registerTmRegular("tm_223", "metalsound")
    val TM_224 = registerTmRegular("tm_224", "curse")
    val TM_225 = registerTmRegular("tm_225", "hardpress")
    val TM_226 = registerTmRegular("tm_226", "dragoncheer")
    val TM_227 = registerTmRegular("tm_227", "alluringvoice")
    val TM_228 = registerTmRegular("tm_228", "psychicnoise")
    val TM_229 = registerTmRegular("tm_229", "upperhand")
    val TM_230 = registerTmRegular("tm_230", "defog")
    val TM_231 = registerTmRegular("tm_231", "roost")
    val TM_232 = registerTmRegular("tm_232", "mysticalfire")
    val TM_233 = registerTmRegular("tm_233", "powerwhip")
    val TM_234 = registerTmRegular("tm_234", "workup")
    val TM_235 = registerTmRegular("tm_235", "allyswitch")
    val TM_236 = registerTmRegular("tm_236", "triattack")
    val TM_237 = registerTmRegular("tm_237", "return")


    /**
     * Badges
     */
    val BALANCE_BADGE = createBadge("balance")
    val BEACON_BADGE = createBadge("beacon")
    val BOULDER_BADGE = createBadge("boulder")
    val CASCADE_BADGE = createBadge("cascade")
    val COAL_BADGE = createBadge("coal")
    val COBBLE_BADGE = createBadge("cobble")
    val DYNAMO_BADGE = createBadge("dynamo")
    val EARTH_BADGE = createBadge("earth")
    val FEATHER_BADGE = createBadge("feather")
    val FEN_BADGE = createBadge("fen")
    val FOG_BADGE = createBadge("fog")
    val FOREST_BADGE = createBadge("forest")
    val GLACIER_BADGE = createBadge("glacier")
    val HEAT_BADGE = createBadge("heat")
    val HIVE_BADGE = createBadge("hive")
    val ICICLE_BADGE = createBadge("icicle")
    val KNUCKLE_BADGE = createBadge("knuckle")
    val MARSH_BADGE = createBadge("marsh")
    val MIND_BADGE = createBadge("mind")
    val MINE_BADGE = createBadge("mine")
    val MINERAL_BADGE = createBadge("mineral")
    val PLAIN_BADGE = createBadge("plain")
    val RAINBOW_BADGE = createBadge("rainbow")
    val RAIN_BADGE = createBadge("rain")
    val RELIC_BADGE = createBadge("relic")
    val RISING_BADGE = createBadge("rising")
    val SOUL_BADGE = createBadge("soul")
    val STONE_BADGE = createBadge("stone")
    val STORM_BADGE = createBadge("storm")
    val THUNDER_BADGE = createBadge("thunder")
    val VOLCANO_BADGE = createBadge("volcano")
    val ZEPHYR_BADGE = createBadge("zephyr")
    val BASIC_BADGE = createBadge("basic")
    val BOLT_BADGE = createBadge("bolt")
    val FREEZE_BADGE = createBadge("freeze")
    val INSECT_BADGE = createBadge("insect")
    val JET_BADGE = createBadge("jet")
    val LEGEND_BADGE = createBadge("legend")
    val QUAKE_BADGE = createBadge("quake")
    val TOXIC_BADGE = createBadge("toxic")
    val TRIO_BADGE = createBadge("trio")
    val WAVE_BADGE = createBadge("wave")
    val BUG_BADGE = createBadge("bug")
    val CLIFF_BADGE = createBadge("cliff")
    val RUMBLE_BADGE = createBadge("rumble")
    val PLANT_BADGE = createBadge("plant")
    val VOLTAGE_BADGE = createBadge("voltage")
    val FAIRY_BADGE = createBadge("fairy")
    val PSYCHIC_BADGE = createBadge("psychic")
    val ICEBERG_BADGE = createBadge("iceberg")
    val SPIKESHELL_BADGE = createBadge("spikeshell")
    val SEARUBY_BADGE = createBadge("searuby")
    val JADESTAR_BADGE = createBadge("jadestar")
    val CORALEYE_BADGE = createBadge("coraleye")
    val FREEDOM_BADGE = createBadge("freedom")
    val HARMONY_BADGE = createBadge("harmony")
    val PATIENCE_BADGE = createBadge("patience")
    val PRIDE_BADGE = createBadge("pride")
    val TRANQUILITY_BADGE = createBadge("tranquility")
    val DARK_BADGE = createBadge("dark")
    val DRAGON_BADGE = createBadge("dragon")
    val FAIRY_2_BADGE = createBadge("fairy_2")
    val FIRE_BADGE = createBadge("fire")
    val GRASS_BADGE = createBadge("grass")
    val ICE_BADGE = createBadge("ice")
    val ROCK_BADGE = createBadge("rock")
    val WATER_BADGE = createBadge("water")
    val FIGHTING_BADGE = createBadge("fighting")
    val GHOST_BADGE = createBadge("ghost")
    val SURGE_BADGE = createBadge("surge")
    val COMPLETE_SHIELD_BADGE = createBadge("complete_shield")
    val COMPLETE_SWORD_BADGE = createBadge("complete_sword")

    /**
     * Ribbons
     */
    val PALDEA_CHAMPION_RIBBON = createRibbon("paldea_champion_ribbon")
    val ABILITY_RIBBON = createRibbon("ability_ribbon")
    val ALERT_RIBBON = createRibbon("alert_ribbon")
    val ALOLA_CHAMPION_RIBBON = createRibbon("alola_champion_ribbon")
    val ARTIST_RIBBON = createRibbon("artist_ribbon")
    val BEAUTY_MASTER_RIBBON = createRibbon("beauty_master_ribbon")
    val BEAUTY_RIBBON_GREAT = createRibbon("beauty_ribbon_great")
    val BEAUTY_RIBBON_HOENN = createRibbon("beauty_ribbon_hoenn")
    val BEAUTY_RIBBON_HYPER = createRibbon("beauty_ribbon_hyper")
    val BEAUTY_RIBBON_MASTER_HOENN = createRibbon("beauty_ribbon_master_hoenn")
    val BEAUTY_RIBBON_MASTER_SINNOH = createRibbon("beauty_ribbon_master_sinnoh")
    val BEAUTY_RIBBON_SINNOH = createRibbon("beauty_ribbon_sinnoh")
    val BEAUTY_RIBBON_SUPER = createRibbon("beauty_ribbon_super")
    val BEAUTY_RIBBON_ULTRA = createRibbon("beauty_ribbon_ultra")
    val CARELESS_RIBBON = createRibbon("careless_ribbon")
    val CHAMPION_RIBBON = createRibbon("champion_ribbon")
    val CLEVERNESS_MASTER_RIBBON = createRibbon("cleverness_master_ribbon")
    val CONTEST_STAR_RIBBON = createRibbon("contest_star_ribbon")
    val COOL_RIBBON_GREAT = createRibbon("cool_ribbon_great")
    val COOL_RIBBON_HOENN = createRibbon("cool_ribbon_hoenn")
    val COOL_RIBBON_HYPER = createRibbon("cool_ribbon_hyper")
    val COOL_RIBBON_MASTER_HOENN = createRibbon("cool_ribbon_master_hoenn")
    val COOL_RIBBON_MASTER_SINNOH = createRibbon("cool_ribbon_master_sinnoh")
    val COOL_RIBBON_SINNOH = createRibbon("cool_ribbon_sinnoh")
    val COOL_RIBBON_SUPER = createRibbon("cool_ribbon_super")
    val COOL_RIBBON_ULTRA = createRibbon("cool_ribbon_ultra")
    val COOLNESS_MASTER_RIBBON = createRibbon("coolness_master_ribbon")
    val CUTE_RIBBON_GREAT = createRibbon("cute_ribbon_great")
    val CUTE_RIBBON_HOENN = createRibbon("cute_ribbon_hoenn")
    val CUTE_RIBBON_HYPER = createRibbon("cute_ribbon_hyper")
    val CUTE_RIBBON_MASTER_HOENN = createRibbon("cute_ribbon_master_hoenn")
    val CUTE_RIBBON_MASTER_SINNOH = createRibbon("cute_ribbon_master_sinnoh")
    val CUTE_RIBBON_SINNOH = createRibbon("cute_ribbon_sinnoh")
    val CUTE_RIBBON_SUPER = createRibbon("cute_ribbon_super")
    val CUTE_RIBBON_ULTRA = createRibbon("cute_ribbon_ultra")
    val CUTENESS_MASTER_RIBBON = createRibbon("cuteness_master_ribbon")
    val DOUBLE_ABILITY_RIBBON = createRibbon("double_ability_ribbon")
    val DOWNCAST_RIBBON = createRibbon("downcast_ribbon")
    val EARTH_RIBBON = createRibbon("earth_ribbon")
    val EFFORT_RIBBON = createRibbon("effort_ribbon")
    val EXPERT_BATTLER_RIBBON = createRibbon("expert_battler_ribbon")
    val BATTLE_TREE_GREAT_RIBBON = createRibbon("battle_tree_great_ribbon")
    val BATTLE_TREE_MASTER_RIBBON = createRibbon("battle_tree_master_ribbon")
    val TOWER_MASTER_RIBBON = createRibbon("tower_master_ribbon")
    val FOOTPRINT_RIBBON = createRibbon("footprint_ribbon")
    val RECORD_RIBBON = createRibbon("record_ribbon")
    val BEST_FRIENDS_RIBBON = createRibbon("best_friends_ribbon")
    val TRAINING_RIBBON = createRibbon("training_ribbon")
    val BATTLE_ROYALE_MASTER_RIBBON = createRibbon("battle_royale_master_ribbon")
    val MASTER_RANK_RIBBON = createRibbon("master_rank_ribbon")
    val PIONEER_RIBBON = createRibbon("pioneer_ribbon")
    val COUNTRY_RIBBON = createRibbon("country_ribbon")
    val WORLD_RIBBON = createRibbon("world_ribbon")
    val CLASSIC_RIBBON = createRibbon("classic_ribbon")
    val PREMIER_RIBBON = createRibbon("premier_ribbon")
    val EVENT_RIBBON = createRibbon("event_ribbon")
    val BIRTHDAY_RIBBON = createRibbon("birthday_ribbon")
    val SPECIAL_RIBBON = createRibbon("special_ribbon")
    val SOUVENIR_RIBBON = createRibbon("souvenir_ribbon")
    val WISHING_RIBBON = createRibbon("wishing_ribbon")
    val BATTLE_CHAMPION_RIBBON = createRibbon("battle_champion_ribbon")
    val REGIONAL_CHAMPION_RIBBON = createRibbon("regional_champion_ribbon")
    val NATIONAL_CHAMPION_RIBBON = createRibbon("national_champion_ribbon")
    val WORLD_CHAMPION_RIBBON = createRibbon("world_champion_ribbon")
    val GORGEOUS_RIBBON = createRibbon("gorgeous_ribbon")
    val GORGEOUS_ROYAL_RIBBON = createRibbon("gorgeous_royal_ribbon")
    val GREAT_ABILITY_RIBBON = createRibbon("great_ability_ribbon")
    val HOENN_CHAMPION_RIBBON = createRibbon("hoenn_champion_ribbon")
    val KALOS_CHAMPION_RIBBON = createRibbon("kalos_champion_ribbon")
    val LEGEND_RIBBON = createRibbon("legend_ribbon")
    val MULTI_ABILITY_RIBBON = createRibbon("multi_ability_ribbon")
    val NATIONAL_RIBBON = createRibbon("national_ribbon")
    val PAIR_ABILITY_RIBBON = createRibbon("pair_ability_ribbon")
    val RELAX_RIBBON = createRibbon("relax_ribbon")
    val ROYAL_RIBBON = createRibbon("royal_ribbon")
    val SHOCK_RIBBON = createRibbon("shock_ribbon")
    val SINNOH_CHAMPION_RIBBON = createRibbon("sinnoh_champion_ribbon")
    val SKILLFUL_BATTLER_RIBBON = createRibbon("skillful_battler_ribbon")
    val SMART_RIBBON_GREAT = createRibbon("smart_ribbon_great")
    val SMART_RIBBON_HOENN = createRibbon("smart_ribbon_hoenn")
    val SMART_RIBBON_HYPER = createRibbon("smart_ribbon_hyper")
    val SMART_RIBBON_MASTER_HOENN = createRibbon("smart_ribbon_master_hoenn")
    val SMART_RIBBON_MASTER_SINNOH = createRibbon("smart_ribbon_master_sinnoh")
    val SMART_RIBBON_SINNOH = createRibbon("smart_ribbon_sinnoh")
    val SMART_RIBBON_SUPER = createRibbon("smart_ribbon_super")
    val SMART_RIBBON_ULTRA = createRibbon("smart_ribbon_ultra")
    val SMILE_RIBBON = createRibbon("smile_ribbon")
    val SNOOZE_RIBBON = createRibbon("snooze_ribbon")
    val TOUGH_RIBBON_GREAT = createRibbon("tough_ribbon_great")
    val TOUGH_RIBBON_HOENN = createRibbon("tough_ribbon_hoenn")
    val TOUGH_RIBBON_HYPER = createRibbon("tough_ribbon_hyper")
    val TOUGH_RIBBON_MASTER_HOENN = createRibbon("tough_ribbon_master_hoenn")
    val TOUGH_RIBBON_MASTER_SINNOH = createRibbon("tough_ribbon_master_sinnoh")
    val TOUGH_RIBBON_SINNOH = createRibbon("tough_ribbon_sinnoh")
    val TOUGH_RIBBON_SUPER = createRibbon("tough_ribbon_super")
    val TOUGH_RIBBON_ULTRA = createRibbon("tough_ribbon_ultra")
    val TOUGHNESS_MASTER_RIBBON = createRibbon("toughness_master_ribbon")
    val VICTORY_RIBBON = createRibbon("victory_ribbon")
    val WINNING_RIBBON = createRibbon("winning_ribbon")
    val WORLD_ABILITY_RIBBON = createRibbon("world_ability_ribbon")
    val ONCE_IN_A_LIFETIME_RIBBON = createRibbon("once_in_a_lifetime_ribbon")
    val GALAR_CHAMP_RIBBON = createRibbon("galar_champ_ribbon")

    val KNOWLEDGE_SYMBOL_SILVER = createRibbon("knowledge_symbol_silver")
    val KNOWLEDGE_SYMBOL = createRibbon("knowledge_symbol")
    val GUTS_SYMBOL_SILVER = createRibbon("guts_symbol_silver")
    val GUTS_SYMBOL = createRibbon("guts_symbol")
    val TACTICS_SYMBOL_SILVER = createRibbon("tactics_symbol_silver")
    val TACTICS_SYMBOL = createRibbon("tactics_symbol")
    val LUCK_SYMBOL_SILVER = createRibbon("luck_symbol_silver")
    val LUCK_SYMBOL = createRibbon("luck_symbol")
    val SPIRITS_SYMBOL_SILVER = createRibbon("spirits_symbol_silver")
    val SPIRITS_SYMBOL = createRibbon("spirits_symbol")
    val BRAVE_SYMBOL_SILVER = createRibbon("brave_symbol_silver")
    val BRAVE_SYMBOL = createRibbon("brave_symbol")
    val ABILITY_SYMBOL_SILVER = createRibbon("ability_symbol_silver")
    val ABILITY_SYMBOL = createRibbon("ability_symbol")

    /**
     * Held Items
     */
    val ADRENALINE_ORB = registerHeldItem("adrenaline_orb")
    val AMULET_COIN = registerHeldItem("amulet_coin")
    val BLACK_FLUTE = registerHeldItem("black_flute")
    val FLUFFY_TAIL = registerHeldItem("fluffy_tail")
    val GRIP_CLAW = registerHeldItem("grip_claw")
    val LAGGING_TAIL = registerHeldItem("lagging_tail")
    val LEEK = registerHeldItem("leek")
    val LUCKY_PUNCH = registerHeldItem("lucky_punch")
    val LUMINOUS_MOSS = registerHeldItem("luminous_moss")
    val MACHO_BRACE = registerHeldItem("macho_brace")

    //    val METRONOME = registerHeldItem("metronome") TODO: Add world converter entry to convert to cobblemon counterpart
    val POKE_DOLL = registerHeldItem("poke_doll")
    val POKE_TOY = registerHeldItem("poke_toy")

    //    val PROTECTIVE_PADS = registerHeldItem("protective_pads") TODO: Add world converter entry to convert to cobblemon counterpart
//    val ROOM_SERVICE = registerHeldItem("room_service") TODO: Add world converter entry to convert to cobblemon counterpart
    //    val SCOPE_LENS = registerHeldItem("scope_lens") TODO: Add world converter entry to convert to cobblemon counterpart
    //    val SHED_SHELL = registerHeldItem("shed_shell") TODO: Add world converter entry to convert to cobblemon counterpart
    val SNOWBALL = registerHeldItem("snowball")
    val SOUL_DEW = registerHeldItem("soul_dew")

    //    val TERRAIN_EXTENDER = registerHeldItem("terrain_extender") TODO: Add world converter entry to convert to cobblemon counterpart
    val THICK_CLUB = registerHeldItem("thick_club")

    //    val THROAT_SPRAY = registerHeldItem("throat_spray") TODO: Add world converter entry to convert to cobblemon counterpart
    val UP_GRADE = registerHeldItem("up_grade")

    //    val UTILITY_UMBRELLA = registerHeldItem("utility_umbrella") TODO: Add world converter entry to convert to cobblemon counterpart
    val WHITE_FLUTE = registerHeldItem("white_flute")

    //    val WIDE_LENS = registerHeldItem("wide_lens") TODO: Add world converter entry to convert to cobblemon counterpart
    //    val ZOOM_LENS = registerHeldItem("zoom_lens") TODO: Add world converter entry to convert to cobblemon counterpart
    val BURN_DRIVE = registerDrive("burn_drive", "burn")
    val CHILL_DRIVE = registerDrive("chill_drive", "chill")
    val DOUSE_DRIVE = registerDrive("douse_drive", "douse")
    val SHOCK_DRIVE = registerDrive("shock_drive", "shock")

    //Incense
    val FULL_INCENSE = registerHeldItem("full_incense")
    val LAX_INCENSE = registerHeldItem("lax_incense")
    val LUCK_INCENSE = registerHeldItem("luck_incense")
    val ODD_INCENSE = registerHeldItem("odd_incense")
    val PURE_INCENSE = registerHeldItem("pure_incense")
    val ROCK_INCENSE = registerHeldItem("rock_incense")
    val ROSE_INCENSE = registerHeldItem("rose_incense")
    val SEA_INCENSE = registerHeldItem("sea_incense")
    val WAVE_INCENSE = registerHeldItem("wave_incense")

    val ABOMASITE = registerHeldItem("abomasite")
    val ABSOLITE = registerHeldItem("absolite")
    val AERODACTYLITE = registerHeldItem("aerodactylite")
    val AGGRONITE = registerHeldItem("aggronite")
    val ALAKAZITE = registerHeldItem("alakazite")
    val ALTARIANITE = registerHeldItem("altarianite")
    val AMPHAROSITE = registerHeldItem("ampharosite")
    val AUDINITE = registerHeldItem("audinite")
    val BANETTITE = registerHeldItem("banettite")
    val BEEDRILLITE = registerHeldItem("beedrillite")
    val BLASTOISINITE = registerHeldItem("blastoisinite")
    val BLAZIKENITE = registerHeldItem("blazikenite")
    val CAMERUPTITE = registerHeldItem("cameruptite")
    val CHARIZARDITE_X = registerHeldItem("charizardite_x")
    val CHARIZARDITE_Y = registerHeldItem("charizardite_y")
    val DIANCITE = registerHeldItem("diancite")
    val GALLADITE = registerHeldItem("galladite")
    val GARCHOMPITE = registerHeldItem("garchompite")
    val GARDEVOIRITE = registerHeldItem("gardevoirite")
    val GENGARITE = registerHeldItem("gengarite")
    val GLALITITE = registerHeldItem("glalitite")
    val GYARADOSITE = registerHeldItem("gyaradosite")
    val HERACRONITE = registerHeldItem("heracronite")
    val HOUNDOOMINITE = registerHeldItem("houndoominite")
    val KANGASKHANITE = registerHeldItem("kangaskhanite")
    val LATIASITE = registerHeldItem("latiasite")
    val LATIOSITE = registerHeldItem("latiosite")
    val LOPUNNNITE = registerHeldItem("lopunnite")
    val LUCARIONITE = registerHeldItem("lucarionite")
    val MANECTITE = registerHeldItem("manectite")
    val MAWILITE = registerHeldItem("mawilite")
    val MEDICHAMITE = registerHeldItem("medichamite")
    val METAGROSSITE = registerHeldItem("metagrossite")
    val MEWTWONITE_X = registerHeldItem("mewtwonite_x")
    val MEWTWONITE_Y = registerHeldItem("mewtwonite_y")
    val PIDGEOTITE = registerHeldItem("pidgeotite")
    val PINSIRITE = registerHeldItem("pinsirite")
    val SABLENITE = registerHeldItem("sablenite")
    val SALAMENCITE = registerHeldItem("salamencite")
    val SCEPTILITE = registerHeldItem("sceptilite")
    val SCIZORITE = registerHeldItem("scizorite")
    val SHARPEDONITE = registerHeldItem("sharpedonite")
    val SLOWBRONITE = registerHeldItem("slowbronite")
    val STEELIXITE = registerHeldItem("steelixite")
    val SWAMPERTITE = registerHeldItem("swampertite")
    val TYRANITARITE = registerHeldItem("tyranitarite")
    val VENUSAURITE = registerHeldItem("venusaurite")

    val BUG_MEMORY_DRIVE = registerMemory("bug_memory_drive", ElementalTypes.BUG)
    val DARK_MEMORY_DRIVE = registerMemory("dark_memory_drive", ElementalTypes.DARK)
    val DRAGON_MEMORY_DRIVE = registerMemory("dragon_memory_drive", ElementalTypes.DRAGON)
    val ELECTRIC_MEMORY_DRIVE = registerMemory("electric_memory_drive", ElementalTypes.ELECTRIC)
    val FAIRY_MEMORY_DRIVE = registerMemory("fairy_memory_drive", ElementalTypes.FAIRY)
    val FIGHTING_MEMORY_DRIVE = registerMemory("fighting_memory_drive", ElementalTypes.FIGHTING)
    val FIRE_MEMORY_DRIVE = registerMemory("fire_memory_drive", ElementalTypes.FIRE)
    val FLYING_MEMORY_DRIVE = registerMemory("flying_memory_drive", ElementalTypes.FLYING)
    val GHOST_MEMORY_DRIVE = registerMemory("ghost_memory_drive", ElementalTypes.GHOST)
    val GRASS_MEMORY_DRIVE = registerMemory("grass_memory_drive", ElementalTypes.GRASS)
    val GROUND_MEMORY_DRIVE = registerMemory("ground_memory_drive", ElementalTypes.GROUND)
    val ICE_MEMORY_DRIVE = registerMemory("ice_memory_drive", ElementalTypes.ICE)
    val POISON_MEMORY_DRIVE = registerMemory("poison_memory_drive", ElementalTypes.POISON)
    val PSYCHIC_MEMORY_DRIVE = registerMemory("psychic_memory_drive", ElementalTypes.PSYCHIC)
    val ROCK_MEMORY_DRIVE = registerMemory("rock_memory_drive", ElementalTypes.ROCK)
    val STEEL_MEMORY_DRIVE = registerMemory("steel_memory_drive", ElementalTypes.STEEL)
    val WATER_MEMORY_DRIVE = registerMemory("water_memory_drive", ElementalTypes.WATER)

    val DRACO_PLATE = registerPlate("draco_plate", ElementalTypes.DRAGON)
    val DREAD_PLATE = registerPlate("dread_plate", ElementalTypes.DARK)
    val EARTH_PLATE = registerPlate("earth_plate", ElementalTypes.GROUND)
    val FIST_PLATE = registerPlate("fist_plate", ElementalTypes.FIGHTING)
    val FLAME_PLATE = registerPlate("flame_plate", ElementalTypes.FIRE)
    val ICICLE_PLATE = registerPlate("icicle_plate", ElementalTypes.ICE)
    val INSECT_PLATE = registerPlate("insect_plate", ElementalTypes.BUG)
    val IRON_PLATE = registerPlate("iron_plate", ElementalTypes.STEEL)
    val MEADOW_PLATE = registerPlate("meadow_plate", ElementalTypes.GRASS)
    val MIND_PLATE = registerPlate("mind_plate", ElementalTypes.PSYCHIC)
    val PIXIE_PLATE = registerPlate("pixie_plate", ElementalTypes.FAIRY)
    val SKY_PLATE = registerPlate("sky_plate", ElementalTypes.FLYING)
    val SPLASH_PLATE = registerPlate("splash_plate", ElementalTypes.WATER)
    val SPOOKY_PLATE = registerPlate("spooky_plate", ElementalTypes.GHOST)
    val STONE_PLATE = registerPlate("stone_plate", ElementalTypes.ROCK)
    val TOXIC_PLATE = registerPlate("toxic_plate", ElementalTypes.POISON)
    val ZAP_PLATE = registerPlate("zap_plate", ElementalTypes.ELECTRIC)

    val BUGINIUM_Z = registerHeldItem("buginium_z")
    val DARKINIUM_Z = registerHeldItem("darkinium_z")
    val DRAGONIUM_Z = registerHeldItem("dragonium_z")
    val ELECTRIUM_Z = registerHeldItem("electrium_z")
    val FAIRIUM_Z = registerHeldItem("fairium_z")
    val FIGHTINIUM_Z = registerHeldItem("fightinium_z")
    val FIRIUM_Z = registerHeldItem("firium_z")
    val FLYINIUM_Z = registerHeldItem("flyinium_z")
    val GHOSTIUM_Z = registerHeldItem("ghostium_z")
    val GRASSIUM_Z = registerHeldItem("grassium_z")
    val GROUNDIUM_Z = registerHeldItem("groundium_z")
    val ICIUM_Z = registerHeldItem("icium_z")
    val NORMALIUM_Z = registerHeldItem("normalium_z")
    val POISONIUM_Z = registerHeldItem("poisonium_z")
    val PSYCHIUM_Z = registerHeldItem("psychium_z")
    val ROCKIUM_Z = registerHeldItem("rockium_z")
    val STEELIUM_Z = registerHeldItem("steelium_z")
    val WATERIUM_Z = registerHeldItem("waterium_z")

    val ALORAICHIUM_Z = registerHeldItem("aloraichium_z")
    val DECIDIUM_Z = registerHeldItem("decidium_z")
    val EEVIUM_Z = registerHeldItem("eevium_z")
    val INCINIUM_Z = registerHeldItem("incinium_z")
    val KOMMONIUM_Z = registerHeldItem("kommonium_z")
    val LUNALIUM_Z = registerHeldItem("lunalium_z")
    val LYCANIUM_Z = registerHeldItem("lycanium_z")
    val MARSHADIUM_Z = registerHeldItem("marshadium_z")
    val MEWNIUM_Z = registerHeldItem("mewnium_z")
    val MIMIKIUM_Z = registerHeldItem("mimikium_z")
    val PIKANIUM_Z = registerHeldItem("pikanium_z")
    val PIKASHUNIUM_Z = registerHeldItem("pikashunium_z")
    val PRIMARIUM_Z = registerHeldItem("primarium_z")
    val SNORLIUM_Z = registerHeldItem("snorlium_z")
    val SOLGANIUM_Z = registerHeldItem("solganium_z")
    val TAPUNIUM_Z = registerHeldItem("tapunium_z")
    val ULTRANECROZIUM_Z = registerHeldItem("ultranecrozium_z")

//    val ELECTRIC_SEED = registerHeldItem("electric_seed") TODO: Add world converter entry to convert to cobblemon counterpart
//    val MISTY_SEED = registerHeldItem("misty_seed") TODO: Add world converter entry to convert to cobblemon counterpart
//    val GRASSY_SEED = registerHeldItem("grassy_seed") TODO: Add world converter entry to convert to cobblemon counterpart
//    val PSYCHIC_SEED = registerHeldItem("psychic_seed") TODO: Add world converter entry to convert to cobblemon counterpart
    val RED_SCARF = registerHeldItem("red_scarf")
    val LEGEND_PLATE = registerHeldItem("legend_plate")
    val LUSTROUS_GLOBE = registerHeldItem("lustrous_globe") { properties -> createFormChangingItem(properties, "origin", species = cobblemonResource("palkia")) }
    val ADAMANT_CRYSTAL = registerHeldItem("adamant_crystal") { properties -> createFormChangingItem(properties, "origin", species = cobblemonResource("dialga")) }
    val GRISEOUS_CORE = registerHeldItem("griseous_core") { properties -> createFormChangingItem(properties, "origin", species = cobblemonResource("giratina")) }
    val PUNCHING_GLOVE = registerHeldItem("punching_glove")
    val CLEAR_AMULET = registerHeldItem("clear_amulet")
    val BOOSTER_ENERGY = registerHeldItem("booster_energy")
    val FAIRY_FEATHER = registerHeldItem("fairy_feather")

    val TEAL_MASK = registerHeldItem("teal_mask") { properties -> createFormChangingItem(properties, "ogerpon_mask", "teal") }
    val WELLSPRING_MASK = registerHeldItem("wellspring_mask") { properties -> createFormChangingItem(properties, "ogre_mask", "wellspring") }
    val HEARTHFLAME_MASK = registerHeldItem("hearthflame_mask") { properties -> createFormChangingItem(properties, "ogre_mask", "hearthflame") }
    val CORNERSTONE_MASK = registerHeldItem("cornerstone_mask") { properties -> createFormChangingItem(properties, "ogre_mask", "cornerstone") }

    /**
    * Vanilla Like Materials
    */
    val Z_INGOT = register("z_ingot", ::Item, PLAYER_ITEMS)
    val ULTRITE_INGOT = register("ultrite_ingot", ::Item, PLAYER_ITEMS)
    val ULTRITE_REMNANT = register("ultrite_remnant", ::Item, PLAYER_ITEMS)
    val DYNITE_ORE = register("dynite_ore", ::Item, PLAYER_ITEMS)
    val MEGASTONE_SHARD = register("mega_stone_shard", ::Item, PLAYER_ITEMS)
    val KEY_STONE = register("key_stone", ::Item, PLAYER_ITEMS)

    val ULTRITE_UPGRADE_SMITHING_TEMPLATE = register("ultrite_upgrade_smithing_template", { UltriteSmithingTemplateItem() }, PLAYER_ITEMS)


    val COPPER_PLATE = register("copper_plate", ::Item, PLAYER_ITEMS)

    /**
    * Player Items
    */
    val MARK_CHARM = register("mark_charm", ::Item, PLAYER_ITEMS)
    val CATCHING_CHARM = register("catching_charm", ::Item, PLAYER_ITEMS)
    val EXP_CHARM = register("exp_charm", ::Item, PLAYER_ITEMS)
    val OLD_ROD = register("old_rod", { TieredFishingRodItem(it, TieredFishingHookEntity.Teir.OLD) }, PLAYER_ITEMS)
    val GOOD_ROD = register("good_rod", { TieredFishingRodItem(it, TieredFishingHookEntity.Teir.GOOD) }, PLAYER_ITEMS)
    val SUPER_ROD = register("super_rod", { TieredFishingRodItem(it, TieredFishingHookEntity.Teir.SUPER) }, PLAYER_ITEMS)
    val RUBY_ROD = register("ruby_rod", { RubyRodItem(it.durability(27), TieredFishingHookEntity.Teir.RUBY) }, PLAYER_ITEMS)
    val CAMERA = register("camera", ::CameraItem, PLAYER_ITEMS)
    val SNAP_CAMERA = register("snap_camera", ::CameraItem, PLAYER_ITEMS)
    val FILM = register("film", ::Item, PLAYER_ITEMS)
    val REPEL = register("repel", ::Item, PLAYER_ITEMS)
    val SUPER_REPEL = register("super_repel", ::Item, PLAYER_ITEMS)
    val MAX_REPEL = register("max_repel", ::Item, PLAYER_ITEMS)
    val WAILMER_PAIL = register("wailmer_pail", ::Item, PLAYER_ITEMS)
    val SPRINK_LOTAD = register("sprink_lotad", ::Item, PLAYER_ITEMS)
    val SPRAYDUCK = register("sprayduck", { Item(it.stacksTo(1)) }, PLAYER_ITEMS)
    val SQUIRT_BOTTLE = register("squirt_bottle", { Item(it.stacksTo(1)) }, PLAYER_ITEMS)
    val MEGA_BRACELET = register("mega_bracelet", { Item(it.stacksTo(1)) }, PLAYER_ITEMS)
    val MEGA_CHARM = register("mega_charm", { Item(it.stacksTo(1)) }, PLAYER_ITEMS)
    val MEGA_CUFF = register("mega_cuff", { Item(it.stacksTo(1)) }, PLAYER_ITEMS)
    val MEGA_RING = register("mega_ring", { Item(it.stacksTo(1)) }, PLAYER_ITEMS)
    val Z_POWER_RING = register("z_power_ring", { Item(it.stacksTo(1)) }, PLAYER_ITEMS)
    val Z_RING = register("z_ring", { Item(it.stacksTo(1)) }, PLAYER_ITEMS)
    val TERA_ORB = register("tera_orb", { Item(it.stacksTo(1)) }, PLAYER_ITEMS)

        /*
    public static final RegistrySupplier<Item> RED_BIKE = register("red_bike", Item::new, PLAYER_ITEMS);
    public static final RegistrySupplier<Item> ORANGE_BIKE = register("orange_bike", Item::new, PLAYER_ITEMS);
    public static final RegistrySupplier<Item> YELLOW_BIKE = register("yellow_bike", Item::new, PLAYER_ITEMS);
    public static final RegistrySupplier<Item> GREEN_BIKE = register("green_bike", Item::new, PLAYER_ITEMS);
    public static final RegistrySupplier<Item> BLUE_BIKE = register("blue_bike", Item::new, PLAYER_ITEMS);
    public static final RegistrySupplier<Item> PURPLE_BIKE = register("purple_bike", Item::new, PLAYER_ITEMS);
    */
    val ROTOM_CATALOG = register("rotom_catalog", ::RotomCatalog, PLAYER_ITEMS)
    val POKEDEX = register("pokedex", ::Item, PLAYER_ITEMS)
    val LURE_MODULE = register("lure_module", ::Item, PLAYER_ITEMS)
    val BOTTLE_CAP = register("bottle_cap", ::Item, PLAYER_ITEMS)
    val GOLD_BOTTLE_CAP = register("gold_bottle_cap", ::Item, PLAYER_ITEMS)
    val WISHING_STAR = register("wishing_star", ::Item, PLAYER_ITEMS)
    val DYNAMAX_BAND = register("dynamax_band", { Item(it.stacksTo(1)) }, PLAYER_ITEMS)
    val TIME_CAPSULE = register("time_capsule", { TimeCapsule(it.fireResistant().stacksTo(1)) }, PLAYER_ITEMS)
    val STRANGE_BALL = register("strange_ball", { PokeBallItem(GenerationsPokeBalls.STRANGE_BALL) })


//    private static RegistrySupplier<PokeBallItem> registerBallItem(PokeBall pokeBall) {
//        return register(pokeBall.getName().getPath(), () -> {
//            var item = new PokeBallItem(pokeBall);
//            pokeBall.item = item;
//        })
//
//        val item = create(pokeBall.name.path, PokeBallItem(pokeBall))
//        pokeBall.item = item
//        pokeBalls.add(item)
//        return item
//    }

    /**
     * Legendary Items
     */
    val ORB = register("orb", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val ADAMANT_ORB = register("adamant_orb", { CreationTrioItem(it.stacksTo(1), LegendKeys.DIALGA, GenerationsCore.id("models/block/shrines/creation_trio/adamant_orb.pk")) }, LEGENDARY_ITEMS)
    val GRISEOUS_ORB = register("griseous_orb", { CreationTrioItem(it.stacksTo(1), LegendKeys.GIRATINA, GenerationsCore.id("models/block/shrines/creation_trio/griseous_orb.pk")) }, LEGENDARY_ITEMS)
    val LUSTROUS_ORB = register("lustrous_orb", { CreationTrioItem(it.stacksTo(1), LegendKeys.PALKIA, GenerationsCore.id("models/block/shrines/creation_trio/lustrous_orb.pk")) }, LEGENDARY_ITEMS)
    val SHATTERED_ICE_KEY_1 = register("shattered_ice_key_1", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val SHATTERED_ICE_KEY_2 = register("shattered_ice_key_2", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val SHATTERED_ICE_KEY_3 = register("shattered_ice_key_3", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val SHATTERED_ICE_KEY_4 = register("shattered_ice_key_4", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val ICEBERG_KEY = register("iceberg_key", { RegiKeyItem(it, LegendKeys.REGICE) }, LEGENDARY_ITEMS)
    val CRUMBLED_ROCK_KEY_1 = register("crumbled_rock_key_1", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val CRUMBLED_ROCK_KEY_2 = register("crumbled_rock_key_2", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val CRUMBLED_ROCK_KEY_3 = register("crumbled_rock_key_3", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val CRUMBLED_ROCK_KEY_4 = register("crumbled_rock_key_4", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val ROCK_PEAK_KEY = register("rock_peak_key", { RegiKeyItem(it, LegendKeys.REGIROCK) }, LEGENDARY_ITEMS)
    val RUSTY_IRON_KEY_1 = register("rusty_iron_key_1", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val RUSTY_IRON_KEY_2 = register("rusty_iron_key_2", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val RUSTY_IRON_KEY_3 = register("rusty_iron_key_3", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val RUSTY_IRON_KEY_4 = register("rusty_iron_key_4", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val IRON_KEY = register("iron_key", { RegiKeyItem(it, LegendKeys.REGISTEEL) }, LEGENDARY_ITEMS)
    val FRAGMENTED_DRAGO_KEY_1 = register("fragmented_drago_key_1", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val FRAGMENTED_DRAGO_KEY_2 = register("fragmented_drago_key_2", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val FRAGMENTED_DRAGO_KEY_3 = register("fragmented_drago_key_3", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val FRAGMENTED_DRAGO_KEY_4 = register("fragmented_drago_key_4", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val DRAGO_KEY = register("drago_key", { RegiKeyItem(it, LegendKeys.REGIDRAGO) }, LEGENDARY_ITEMS)
    val DISCHARGED_ELEKI_KEY_1 = register("discharged_eleki_key_1", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val DISCHARGED_ELEKI_KEY_2 = register("discharged_eleki_key_2", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val DISCHARGED_ELEKI_KEY_3 = register("discharged_eleki_key_3", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val DISCHARGED_ELEKI_KEY_4 = register("discharged_eleki_key_4", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val ELEKI_KEY = register("eleki_key", { RegiKeyItem(it, LegendKeys.REGIELEKI) }, LEGENDARY_ITEMS)
    val SHATTERED_RELIC_SONG_1 = register("shattered_relic_song_1", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val SHATTERED_RELIC_SONG_2 = register("shattered_relic_song_2", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val SHATTERED_RELIC_SONG_3 = register("shattered_relic_song_3", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val SHATTERED_RELIC_SONG_4 = register("shattered_relic_song_4", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val RELIC_SONG = createRelicSong(false)
    val INERT_RELIC_SONG = createRelicSong(true)
    val RED_CHAIN = register("red_chain", { RedChainItem(it.stacksTo(1)) }, LEGENDARY_ITEMS)
    val DNA_SPLICERS = register("dna_splicers", { DnaSplicer(it) }, LEGENDARY_ITEMS)
    val REINS_OF_UNITY = register("reins_of_unity", { ReinsOfUnityItem(it.stacksTo(1)) }, LEGENDARY_ITEMS)
    val N_SOLARIZER = register("n_solarizer", { NecroizerItemItem(it, "solgaleo", "sunsteelstrike", "dusk") }, LEGENDARY_ITEMS)
    val N_LUNARIZER = register("n_lunarizer", { NecroizerItemItem(it, "lunala", "moongeistbeam", "dawn") }, LEGENDARY_ITEMS)
    val LEGEND_FINDER = register("legend_finder", ::Item, LEGENDARY_ITEMS)
    val HOOPA_RING = register("hoopa_ring", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val FADED_RED_ORB = register("faded_red_orb", { ElementalPostBattleUpdateItemImpl(it.stacksTo(1).durability(250), ElementalTypes.FIRE) }, LEGENDARY_ITEMS)
    val FADED_BLUE_ORB = register("faded_blue_orb", { ElementalPostBattleUpdateItemImpl(it.stacksTo(1).durability(250), ElementalTypes.WATER) }, LEGENDARY_ITEMS)
    val FADED_JADE_ORB = register("faded_jade_orb", { ElementalPostBattleUpdateItemImplImpl(it.stacksTo(1).durability(250), key = LegendKeys.RAYQUAZA, types = listOf(ElementalTypes.FLYING)) }, LEGENDARY_ITEMS)
    val RED_ORB = registerHeldItem("red_orb", ::Item)
    val BLUE_ORB = registerHeldItem("blue_orb", ::Item)
    val JADE_ORB = register("jade_orb", { JadeOrb(it.stacksTo(1)) }, LEGENDARY_ITEMS)
    val LIGHT_STONE = register("light_stone", { TaoTrioStoneItem(it.stacksTo(1).durability(100), LegendKeys.RESHIRAM) }, LEGENDARY_ITEMS)
    val DARK_STONE = register("dark_stone", { TaoTrioStoneItem(it.stacksTo(1).durability(100), LegendKeys.ZEKROM) }, LEGENDARY_ITEMS)
    val DRAGON_STONE = register("dragon_stone", { TaoTrioStoneItem(it.stacksTo(1).durability(100), LegendKeys.KYUREM) }, LEGENDARY_ITEMS)
    val RAINBOW_WING = register("rainbow_wing", { WingItem(it, "rainbow", ElementalTypes.FLYING, LegendKeys.HO_OH) }, LEGENDARY_ITEMS)
    val SILVER_WING = register("silver_wing", { WingItem(it.stacksTo(1), "silver", ElementalTypes.FLYING, LegendKeys.LUGIA) }, LEGENDARY_ITEMS)
    val DARK_SOUL = register("dark_soul", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val DRAGON_SOUL = register("dragon_soul", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val MELODY_FLUTE = register("melody_flute", ::MelodyFluteItem, LEGENDARY_ITEMS)
    val SPARKLING_SHARD = register("sparkling_shard", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val SPARKLING_STONE = register("sparkling_stone", { ElementalPostBattleUpdateItemImpl(it.stacksTo(1).durability(100), ElementalTypes.FAIRY) }, LEGENDARY_ITEMS)
    val RUSTY_FRAGMENT = register("rusty_fragment", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val CROWNED_SWORD = register("crowned_sword", { createFormChangingItem(it, "crowned", species = cobblemonResource("zacian")) }, LEGENDARY_ITEMS)
    val RUSTY_SWORD = register("rusty_sword", { ElementalPostBattleUpdateItemImplImpl(it.stacksTo(1).durability(100), key = LegendKeys.ZACIAN, itemToGiveUponSpawn = CROWNED_SWORD, types = listOf(ElementalTypes.STEEL)) }, LEGENDARY_ITEMS)
    val CROWNED_SHIELD = register("crowned_shield", { createFormChangingItem(it, "crowned", species = cobblemonResource("zamazenta")) }, LEGENDARY_ITEMS)
    val RUSTY_SHIELD = register("rusty_shield", { ElementalPostBattleUpdateItemImplImpl(it.stacksTo(1).durability(100), key = LegendKeys.ZAMAZENTA, itemToGiveUponSpawn = CROWNED_SHIELD, types = listOf(ElementalTypes.STEEL)) }, LEGENDARY_ITEMS)
    val SCROLL_PAGE = register("scroll_page", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val SECRET_ARMOR_SCROLL = register("secret_armor_scroll", ::SecretArmorScroll, LEGENDARY_ITEMS)
    val ZYGARDE_CUBE = register("zygarde_cube", { ZygardeCubeItem(it.stacksTo(1).durability(ZygardeCubeItem.FULL)) }, LEGENDARY_ITEMS)
    val MELTAN_BOX = register("meltan_box", { MeltanBox(it.stacksTo(1).durability(200)) }, LEGENDARY_ITEMS)
    val MELTAN_BOX_CHARGED = register("meltan_box_charged", ::MeltanBox, LEGENDARY_ITEMS)
    val TIME_GLASS = register("time_glass", { TimeGlassItem(it.stacksTo(1).durability(100)) }, LEGENDARY_ITEMS)
    val MOON_FLUTE = register("moon_flute", ::Item, LEGENDARY_ITEMS)
    val SUN_FLUTE = register("sun_flute", ::Item, LEGENDARY_ITEMS)
    val LAVA_CRYSTAL = register("lava_crystal", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val JEWEL_OF_LIFE = register("jewel_of_life", ::Item, LEGENDARY_ITEMS)
    val MIRROR = register("mirror", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val ICEROOT_CARROT = register("iceroot_carrot", { CalyrexSteedItem("iceroot", it.stacksTo(1).food(FoodProperties.Builder().alwaysEdible().build()), LegendKeys.GLASTRIER) }, LEGENDARY_ITEMS)
    val SHADEROOT_CARROT = register("shaderoot_carrot", { CalyrexSteedItem("shaderoot", it.stacksTo(1).food(FoodProperties.Builder().alwaysEdible().build()), LegendKeys.SPECTRIER) }, LEGENDARY_ITEMS)
    val ENIGMA_STONE = register("enigma_stone", { EnigmaStoneItem(it.stacksTo(1).durability(100)) }, LEGENDARY_ITEMS)
    val ENIGMA_SHARD = register("enigma_shard", { ItemWithLangTooltipImpl(it.stacksTo(64)) }, LEGENDARY_ITEMS)
    @JvmField val ENIGMA_FRAGMENT = register("enigma_fragment",{ ItemWithLangTooltipImpl(it.stacksTo(64).fireResistant() /* just incase it falls into lava while in nether*/) }, LEGENDARY_ITEMS)
    val SACRED_ASH = register("sacred_ash", { ItemWithLangTooltipImpl(it.stacksTo(1).durability(1)) }, LEGENDARY_ITEMS)
    val SHARD_OF_WILLPOWER = register("shard_of_willpower", { ItemWithLangTooltipImpl(it.stacksTo(9)) }, LEGENDARY_ITEMS)
    val SHARD_OF_EMOTION = register("shard_of_emotion", { ItemWithLangTooltipImpl(it.stacksTo(9)) }, LEGENDARY_ITEMS)
    val SHARD_OF_KNOWLEDGE = register("shard_of_knowledge", { ItemWithLangTooltipImpl(it.stacksTo(9)) }, LEGENDARY_ITEMS)
    val CRYSTAL_OF_WILLPOWER = register("crystal_of_willpower", { LakeCrystalItem(it.durability(100), LegendKeys.AZELF) }, LEGENDARY_ITEMS)
    val CRYSTAL_OF_EMOTION = register("crystal_of_emotion", { LakeCrystalItem(it.durability(100), LegendKeys.MESPRIT) }, LEGENDARY_ITEMS)
    val CRYSTAL_OF_KNOWLEDGE = register("crystal_of_knowledge", { LakeCrystalItem(it.durability(100), LegendKeys.UXIE) }, LEGENDARY_ITEMS)
    val REGICE_ORB = register("regice_orb", { RegiOrbItem(it, "regice") }, LEGENDARY_ITEMS)
    val REGIROCK_ORB = register("regirock_orb", { RegiOrbItem(it, "regirock") }, LEGENDARY_ITEMS)
    val REGISTEEL_ORB = register("registeel_orb", { RegiOrbItem(it, "registeel") }, LEGENDARY_ITEMS)
    val REGIDRAGO_ORB = register("regidrago_orb", { RegiOrbItem(it, "regidrago") }, LEGENDARY_ITEMS)
    val REGIELEKI_ORB = register("regieleki_orb", { RegiOrbItem(it, "regieleki") }, LEGENDARY_ITEMS)
    val MAGMA_CRYSTAL = register("magma_crystal", { MagmaCrystal(it.stacksTo(1)) }, LEGENDARY_ITEMS)
    val ICY_WING = register("icy_wing", { WingItem(it.stacksTo(1), "icy", ElementalTypes.ICE, LegendKeys.ARTICUNO) }, LEGENDARY_ITEMS)
    val ELEGANT_WING = register("elegant_wing", { WingItem(it.stacksTo(1), "elegant", ElementalTypes.PSYCHIC, LegendKeys.GALARIAN_ARTICUNO) }, LEGENDARY_ITEMS)
    val STATIC_WING = register("static_wing", { WingItem(it.stacksTo(1), "static", ElementalTypes.ELECTRIC, LegendKeys.ZAPDOS) }, LEGENDARY_ITEMS)
    val BELLIGERENT_WING = register("belligerent_wing", { WingItem(it.stacksTo(1), "belligerent", ElementalTypes.FIGHTING, LegendKeys.GALARIAN_ZAPDOS) }, LEGENDARY_ITEMS)
    val FIERY_WING = register("fiery_wing", { WingItem(it.stacksTo(1).fireResistant(), "fiery", ElementalTypes.FIRE, LegendKeys.MOLTRES) }, LEGENDARY_ITEMS)
    val SINISTER_WING = register("sinister_wing", { WingItem(it.stacksTo(1), "sinister", ElementalTypes.DARK, LegendKeys.GALARIAN_MOLTRES) }, LEGENDARY_ITEMS)
    val MEW_DNA_FIBER = register("mew_dna_fiber", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val MEW_FOSSIL = register("mew_fossil", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val LIGHT_SOUL = register("light_soul", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)

    //TODO: Turn back to 10,000 before release
    @JvmField
    val WONDER_EGG = register("wonder_egg", { LegendaryEggItem(it.stacksTo(1), LegendKeys.MANAPHY) }, LEGENDARY_ITEMS)

    //TODO: Turn back to 10,000 before release
    @JvmField
    val PHIONE_EGG = register("phione_egg", { LegendaryEggItem(it.stacksTo(1), LegendKeys.PHIONE) }, LEGENDARY_ITEMS)
    val SOUL_HEART = register("soul_heart", { SingleElmentPostUpdatingItem(it.stacksTo(1).durability(100), ElementalTypes.FAIRY) }, LEGENDARY_ITEMS)
    val BLUE_PETAL = register("blue_petal", ::Item, LEGENDARY_ITEMS)
    val GREEN_PETAL = register("green_petal", ::Item, LEGENDARY_ITEMS)
    val ORANGE_PETAL = register("orange_petal", ::Item, LEGENDARY_ITEMS)
    val PINK_PETAL = register("pink_petal", ::Item, LEGENDARY_ITEMS)
    val PURPLE_PETAL = register("purple_petal", ::Item, LEGENDARY_ITEMS)
    val RADIANT_PETAL = register("radiant_petal", ::Item, LEGENDARY_ITEMS)
    val RED_PETAL = register("red_petal", ::Item, LEGENDARY_ITEMS)
    val YELLOW_PETAL = register("yellow_petal", ::Item, LEGENDARY_ITEMS)
    val METEORITE_SHARD = register("meteorite_shard", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val BLACK_MANE_HAIR = register("black_mane_hair", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)
    val WHITE_MANE_HAIR = register("white_mane_hair", ::ItemWithLangTooltipImpl, LEGENDARY_ITEMS)

    /**
     * Naturals
     */
    val CRYSTAL = register("crystal", ::Item, PLAYER_ITEMS)
    val RUBY = register("ruby", ::Item, PLAYER_ITEMS)
    val SAPPHIRE = register("sapphire", ::Item, PLAYER_ITEMS)
    val SILICON = register("silicon", ::Item, PLAYER_ITEMS)

    /**
     * Utility Items
     */
    val POKEMON_WAND = register("pokemon_wand", ::Item, UTILITY)
    val CHISEL = register("chisel", ::StatueSpawnerItem, UTILITY)
    val SUICUNE_STATUE = register("suicune_statue", { StatueSpawnerItem(it, LegendKeys.SUICUNE) }, LEGENDARY_ITEMS)
    val RAIKOU_STATUE = register("raikou_statue", { StatueSpawnerItem(it, LegendKeys.RAIKOU) }, LEGENDARY_ITEMS)
    val ENTEI_STATUE = register("entei_statue", { StatueSpawnerItem(it, LegendKeys.ENTEI) }, LEGENDARY_ITEMS)

    val GIFT_BOX = register("gift_box", ::Item, UTILITY)
    val NPC_WAND = register("npc_wand", ::NpcWandItem, UTILITY)
    val NPC_PATH_TOOL = register("npc_path_tool", ::NpcPathTool, UTILITY)
    val ZONE_WAND = register("zone_wand", ::Item, UTILITY)
    val BIKE_FRAME = register("bike_frame", ::Item, UTILITY)
    val BIKE_HANDLEBARS = register("bike_handlebars", ::Item, UTILITY)
    val BIKE_SEAT = register("bike_seat", ::Item, UTILITY)
    val BIKE_WHEEL = register("bike_wheel", ::Item, UTILITY)
    val HIDDEN_IRON_DOOR = register("hidden_iron_door", ::Item, UTILITY)
    val HIDDEN_WOODEN_DOOR = register("hidden_wooden_door", ::Item, UTILITY)
    val HIDDEN_LEVER = register("hidden_lever", ::Item, UTILITY)
    val HIDDEN_PRESSURE_PLATE = register("hidden_pressure_plate", ::Item, UTILITY)
    val HIDDEN_CUBE = register("hidden_cube", ::Item, UTILITY)
    val HIDDEN_BUTTON = register("hidden_button", ::Item, UTILITY)

    /**
     * Form Items
     */
    val METEORITE = register("meteorite", ::MeteoriteItem, FORM_ITEMS)
    val GRACIDEA = register("gracidea", ::GracideaItem, FORM_ITEMS)
    val REVEAL_GLASS = register("reveal_glass", { createFormChangingItem(it, "therian") }, FORM_ITEMS)
    val ROCKSTAR_COSTUME = register("rockstar_costume", ::Item, FORM_ITEMS)
    val BELLE_COSTUME = register("belle_costume", ::Item, FORM_ITEMS)
    val POPSTAR_COSTUME = register("popstar_costume", ::Item, FORM_ITEMS)
    val PHD_COSTUME = register("phd_costume", ::Item, FORM_ITEMS)
    val LIBRE_COSTUME = register("libre_costume", ::Item, FORM_ITEMS)
    val MEWTWO_ARMOR = register("mewtwo_armor", ::Item, FORM_ITEMS)
    val PINK_NECTAR = register("pink_nectar", { NectarItem(it, "pa'u") }, FORM_ITEMS)
    val PURPLE_NECTAR = register("purple_nectar", { NectarItem(it, "sensu") }, FORM_ITEMS)
    val RED_NECTAR = register("red_nectar", { NectarItem(it, "") }, FORM_ITEMS)
    val YELLOW_NECTAR = register("yellow_nectar", { NectarItem(it, "pom-pom") }, FORM_ITEMS)
    val KANTO_CAP = registerCap("kanto")
    val WORLD_CAP = registerCap("world")
    val HOENN_CAP = registerCap("hoenn")
    val SINNOH_CAP = registerCap("sinnoh")
    val UNOVA_CAP = registerCap("unova")
    val KALOS_CAP = registerCap("kalos")
    val ALOLA_CAP = registerCap("alola")
    val PARTNER_CAP = registerCap("partner")
    val SYRUPY_APPLE = register("syrupy_apple", ::Item, FORM_ITEMS)

    /**
     * Mail
     */
    val POKEMAIL_AIR = registerMail("pokemail_air", MailTypes.AIR)
    val POKEMAIL_AIR_CLOSED = registerClosedMail("pokemail_air_closed", MailTypes.AIR)
    val POKEMAIL_BLOOM = registerMail("pokemail_bloom", MailTypes.BLOOM)
    val POKEMAIL_BLOOM_CLOSED = registerClosedMail("pokemail_bloom_closed", MailTypes.BLOOM)
    val POKEMAIL_BRICK = registerMail("pokemail_brick", MailTypes.BRICK)
    val POKEMAIL_BRICK_CLOSED = registerClosedMail("pokemail_brick_closed", MailTypes.BRICK)
    val POKEMAIL_BRIDGE_D = registerMail("pokemail_bridge_d", MailTypes.BRIDGE_D)
    val POKEMAIL_BRIDGE_D_CLOSED = registerClosedMail("pokemail_bridge_d_closed", MailTypes.BRIDGE_D)
    val POKEMAIL_BRIDGE_M = registerMail("pokemail_bridge_m", MailTypes.BRIDGE_M)
    val POKEMAIL_BRIDGE_M_CLOSED = registerClosedMail("pokemail_bridge_m_closed", MailTypes.BRIDGE_M)
    val POKEMAIL_BRIDGE_S = registerMail("pokemail_bridge_s", MailTypes.BRIDGE_S)
    val POKEMAIL_BRIDGE_S_CLOSED = registerClosedMail("pokemail_bridge_s_closed", MailTypes.BRIDGE_S)
    val POKEMAIL_BRIDGE_T = registerMail("pokemail_bridge_t", MailTypes.BRIDGE_T)
    val POKEMAIL_BRIDGE_T_CLOSED = registerClosedMail("pokemail_bridge_t_closed", MailTypes.BRIDGE_T)
    val POKEMAIL_BRIDGE_V = registerMail("pokemail_bridge_v", MailTypes.BRIDGE_V)
    val POKEMAIL_BRIDGE_V_CLOSED = registerClosedMail("pokemail_bridge_v_closed", MailTypes.BRIDGE_V)
    val POKEMAIL_BUBBLE = registerMail("pokemail_bubble", MailTypes.BUBBLE)
    val POKEMAIL_BUBBLE_CLOSED = registerClosedMail("pokemail_bubble_closed", MailTypes.BUBBLE)
    val POKEMAIL_DREAM = registerMail("pokemail_dream", MailTypes.DREAM)
    val POKEMAIL_DREAM_CLOSED = registerClosedMail("pokemail_dream_closed", MailTypes.DREAM)
    val POKEMAIL_FAB = registerMail("pokemail_fab", MailTypes.FAB)
    val POKEMAIL_FAB_CLOSED = registerClosedMail("pokemail_fab_closed", MailTypes.FAB)
    val POKEMAIL_FAVORED = registerMail("pokemail_favored", MailTypes.FAVORED)
    val POKEMAIL_FAVORED_CLOSED = registerClosedMail("pokemail_favored_closed", MailTypes.FAVORED)
    val POKEMAIL_FLAME = registerMail("pokemail_flame", MailTypes.FLAME)
    val POKEMAIL_FLAME_CLOSED = registerClosedMail("pokemail_flame_closed", MailTypes.FLAME)
    val POKEMAIL_GLITTER = registerMail("pokemail_glitter", MailTypes.GLITTER)
    val POKEMAIL_GLITTER_CLOSED = registerClosedMail("pokemail_glitter_closed", MailTypes.GLITTER)
    val POKEMAIL_GRASS = registerMail("pokemail_grass", MailTypes.GRASS)
    val POKEMAIL_GRASS_CLOSED = registerClosedMail("pokemail_grass_closed", MailTypes.GRASS)
    val POKEMAIL_GREET = registerMail("pokemail_greet", MailTypes.GREET)
    val POKEMAIL_GREET_CLOSED = registerClosedMail("pokemail_greet_closed", MailTypes.GREET)
    val POKEMAIL_HARBOR = registerMail("pokemail_harbor", MailTypes.HARBOR)
    val POKEMAIL_HARBOR_CLOSED = registerClosedMail("pokemail_harbor_closed", MailTypes.HARBOR)
    val POKEMAIL_HEART = registerMail("pokemail_heart", MailTypes.HEART)
    val POKEMAIL_HEART_CLOSED = registerClosedMail("pokemail_heart_closed", MailTypes.HEART)
    val POKEMAIL_INQUIRY = registerMail("pokemail_inquiry", MailTypes.INQUIRY)
    val POKEMAIL_INQUIRY_CLOSED = registerClosedMail("pokemail_inquiry_closed", MailTypes.INQUIRY)
    val POKEMAIL_LIKE = registerMail("pokemail_like", MailTypes.LIKE)
    val POKEMAIL_LIKE_CLOSED = registerClosedMail("pokemail_like_closed", MailTypes.LIKE)
    val POKEMAIL_MECH = registerMail("pokemail_mech", MailTypes.MECH)
    val POKEMAIL_MECH_CLOSED = registerClosedMail("pokemail_mech_closed", MailTypes.MECH)
    val POKEMAIL_MOSAIC = registerMail("pokemail_mosaic", MailTypes.MOSAIC)
    val POKEMAIL_MOSAIC_CLOSED = registerClosedMail("pokemail_mosaic_closed", MailTypes.MOSAIC)
    val POKEMAIL_ORANGE = registerMail("pokemail_orange", MailTypes.ORANGE)
    val POKEMAIL_ORANGE_CLOSED = registerClosedMail("pokemail_orange_closed", MailTypes.ORANGE)
    val POKEMAIL_REPLY = registerMail("pokemail_reply", MailTypes.REPLY)
    val POKEMAIL_REPLY_CLOSED = registerClosedMail("pokemail_reply_closed", MailTypes.REPLY)
    val POKEMAIL_RETRO = registerMail("pokemail_retro", MailTypes.RETRO)
    val POKEMAIL_RETRO_CLOSED = registerClosedMail("pokemail_retro_closed", MailTypes.RETRO)
    val POKEMAIL_RSVP = registerMail("pokemail_rsvp", MailTypes.RSVP)
    val POKEMAIL_RSVP_CLOSED = registerClosedMail("pokemail_rsvp_closed", MailTypes.RSVP)
    val POKEMAIL_SHADOW = registerMail("pokemail_shadow", MailTypes.SHADOW)
    val POKEMAIL_SHADOW_CLOSED = registerClosedMail("pokemail_shadow_closed", MailTypes.SHADOW)
    val POKEMAIL_SNOW = registerMail("pokemail_snow", MailTypes.SNOW)
    val POKEMAIL_SNOW_CLOSED = registerClosedMail("pokemail_snow_closed", MailTypes.SNOW)
    val POKEMAIL_SPACE = registerMail("pokemail_space", MailTypes.SPACE)
    val POKEMAIL_SPACE_CLOSED = registerClosedMail("pokemail_space_closed", MailTypes.SPACE)
    val POKEMAIL_STEEL = registerMail("pokemail_steel", MailTypes.STEEL)
    val POKEMAIL_STEEL_CLOSED = registerClosedMail("pokemail_steel_closed", MailTypes.STEEL)
    val POKEMAIL_THANKS = registerMail("pokemail_thanks", MailTypes.THANKS)
    val POKEMAIL_THANKS_CLOSED = registerClosedMail("pokemail_thanks_closed", MailTypes.THANKS)
    val POKEMAIL_TROPIC = registerMail("pokemail_tropic", MailTypes.TROPIC)
    val POKEMAIL_TROPIC_CLOSED = registerClosedMail("pokemail_tropic_closed", MailTypes.TROPIC)
    val POKEMAIL_TUNNEL = registerMail("pokemail_tunnel", MailTypes.TUNNEL)
    val POKEMAIL_TUNNEL_CLOSED = registerClosedMail("pokemail_tunnel_closed", MailTypes.TUNNEL)
    val POKEMAIL_WAVE = registerMail("pokemail_wave", MailTypes.WAVE)
    val POKEMAIL_WAVE_CLOSED = registerClosedMail("pokemail_wave_closed", MailTypes.WAVE)
    val POKEMAIL_WOOD = registerMail("pokemail_wood", MailTypes.WOOD)
    val POKEMAIL_WOOD_CLOSED = registerClosedMail("pokemail_wood_closed", MailTypes.WOOD)

    /**
     * Valuable Items
     */
    val RELIC_GOLD = register("relic_gold", ::Item, VALUABLES)
    val RELIC_COPPER = register("relic_copper", ::Item, VALUABLES)
    val RELIC_BAND = register("relic_band", ::Item, VALUABLES)
    val RELIC_SILVER = register("relic_silver", ::Item, VALUABLES)
    val RELIC_STATUE = register("relic_statue", ::Item, VALUABLES)
    val RELIC_VASE = register("relic_vase", ::Item, VALUABLES)
    val RELIC_CROWN = register("relic_crown", ::Item, VALUABLES)
    val HEART_SCALE = register("heart_scale", ::Item, VALUABLES)
    val SHOAL_SHELL = register("shoal_shell", ::Item, VALUABLES)
    val SHOAL_SALT = register("shoal_salt", ::Item, VALUABLES)
    val STARDUST = register("stardust", ::Item, VALUABLES)
    val RARE_BONE = register("rare_bone", ::Item, VALUABLES)
    val SILVER_LEAF = register("silver_leaf", ::Item, VALUABLES)
    val STRANGE_SOUVENIR = register("strange_souvenir", ::Item, VALUABLES)
    val SLOWPOKE_TAIL = register("slowpoke_tail", ::Item, VALUABLES)
    val ODD_KEYSTONE = register("odd_keystone", ::Item, VALUABLES)
    val ESCAPE_ROPE = register("escape_rope", ::Item, VALUABLES)
    val BEACH_GLASS = register("beach_glass", ::Item, VALUABLES)
    val CHALKY_STONE = register("chalky_stone", ::Item, VALUABLES)
    val LONE_EARRING = register("lone_earring", ::Item, VALUABLES)
    val PRETTY_FEATHER = register("pretty_feather", ::Item, VALUABLES)
    val MARBLE = register("marble", ::Item, VALUABLES)
    val POLISHED_MUD_BALL = register("polished_mud_ball", ::Item, VALUABLES)
    val STRETCHY_SPRING = register("stretchy_spring", ::Item, VALUABLES)
    val TROPICAL_SHELL = register("tropical_shell", ::Item, VALUABLES)
    val BALM_MUSHROOM = register("balm_mushroom", ::Item, VALUABLES)
    val BIG_MUSHROOM = register("big_mushroom", ::Item, VALUABLES)
    val BIG_NUGGET = register("big_nugget", ::Item, VALUABLES)
    val BIG_PEARL = register("big_pearl", ::Item, VALUABLES)
    val GOLD_LEAF = register("gold_leaf", ::Item, VALUABLES)
    val SMALL_BOUQUET = register("small_bouquet", ::Item, VALUABLES)
    val BLUE_SHARD = register("blue_shard", ::Item, VALUABLES)
    val COMET_SHARD = register("comet_shard", ::Item, VALUABLES)
    val GREEN_SHARD = register("green_shard", ::Item, VALUABLES)
    val NUGGET = register("nugget", ::Item, VALUABLES)
    val PEARL = register("pearl", ::Item, VALUABLES)
    val PEARL_STRING = register("pearl_string", ::Item, VALUABLES)
    val RED_SHARD = register("red_shard", ::Item, VALUABLES)
    val STAR_PIECE = register("star_piece", ::Item, VALUABLES)
    val TINY_MUSHROOM = register("tiny_mushroom", ::Item, VALUABLES)
    val YELLOW_SHARD = register("yellow_shard", ::Item, VALUABLES)
    val HONEY = register("honey", ::Item, VALUABLES)

    val BUG_CANDY = register("bug_candy", ::Item, VALUABLES)
    val DARK_CANDY = register("dark_candy", ::Item, VALUABLES)
    val DRAGON_CANDY = register("dragon_candy", ::Item, VALUABLES)
    val ELECTRIC_CANDY = register("electric_candy", ::Item, VALUABLES)
    val FAIRY_CANDY = register("fairy_candy", ::Item, VALUABLES)
    val FIGHTING_CANDY = register("fighting_candy", ::Item, VALUABLES)
    val FIRE_CANDY = register("fire_candy", ::Item, VALUABLES)
    val FLYING_CANDY = register("flying_candy", ::Item, VALUABLES)
    val GHOST_CANDY = register("ghost_candy", ::Item, VALUABLES)
    val GRASS_CANDY = register("grass_candy", ::Item, VALUABLES)
    val GROUND_CANDY = register("ground_candy", ::Item, VALUABLES)
    val ICE_CANDY = register("ice_candy", ::Item, VALUABLES)
    val NORMAL_CANDY = register("normal_candy", ::Item, VALUABLES)
    val POISON_CANDY = register("poison_candy", ::Item, VALUABLES)
    val PSYCHIC_CANDY = register("psychic_candy", ::Item, VALUABLES)
    val ROCK_CANDY = register("rock_candy", ::Item, VALUABLES)
    val STEEL_CANDY = register("steel_candy", ::Item, VALUABLES)
    val WATER_CANDY = register("water_candy", ::Item, VALUABLES)
    val NULL_CANDY = register("null_candy", ::Item, VALUABLES)

    /**
     * Curry Ingredients
     */
    val BACHS_FOOD_TIN = register("bachs_food_tin", { CurryIngredient(CurryType.Rich, it) }, CUISINE)
    val BOBS_FOOD_TIN = register("bobs_food_tin", { CurryIngredient(CurryType.Juicy, it) }, CUISINE)
    val BOILED_EGG = register("boiled_egg", { CurryIngredient(CurryType.BoiledEgg, it) }, CUISINE)
    val BREAD = register("bread", { CurryIngredient(CurryType.Toast, it) }, CUISINE)
    val BRITTLE_BONES = register("brittle_bones", { CurryIngredient(CurryType.Bone, it) }, CUISINE)
    val COCONUT_MILK = register("coconut_milk", { CurryIngredient(CurryType.Coconut, it) }, CUISINE)
    val FANCY_APPLE = register("fancy_apple", { CurryIngredient(CurryType.Apple, it) }, CUISINE)
    val FRESH_CREAM = register("fresh_cream", { CurryIngredient(CurryType.WhippedCream, it) }, CUISINE)
    val FRIED_FOOD = register("fried_food", { CurryIngredient(CurryType.FriedFood, it) }, CUISINE)
    val FRUIT_BUNCH = register("fruit_bunch", { CurryIngredient(CurryType.Tropical, it) }, CUISINE)
    val INSTANT_NOODLES = register("instant_noodles", { CurryIngredient(CurryType.InstantNoodle, it) }, CUISINE)
    val LARGE_LEEK = register("large_leek", { CurryIngredient(CurryType.Leek, it) }, CUISINE)
    val MIXED_MUSHROOMS = register("mixed_mushrooms", { CurryIngredient(CurryType.MushroomMedley, it) }, CUISINE)
    val MOOMOO_CHEESE = register("moomoo_cheese", { CurryIngredient(CurryType.CheeseCovered, it) }, CUISINE)
    val PACK_OF_POTATOES = register("pack_of_potatoes", { CurryIngredient(CurryType.PlentyOfPotato, it) }, CUISINE)
    val PACKAGED_CURRY = register("packaged_curry", { CurryIngredient(CurryType.Decorative, it) }, CUISINE)
    val PASTA = register("pasta", { CurryIngredient(CurryType.Pasta, it) }, CUISINE)
    val PRECOOKED_BURGER = register("precooked_burger", { CurryIngredient(CurryType.BurgerSteak, it) }, CUISINE)
    val PUNGENT_ROOT = register("pungent_root", { CurryIngredient(CurryType.HerbMedley, it) }, CUISINE)
    val SALAD_MIX = register("salad_mix", { CurryIngredient(CurryType.Salad, it) }, CUISINE)
    val SAUSAGES = register("sausages", { CurryIngredient(CurryType.Sausage, it) }, CUISINE)
    val SMOKED_POKE_TAIL = register("smoked_poke_tail", { CurryIngredient(CurryType.SmokedTail, it) }, CUISINE)
    val SPICE_MIX = register("spice_mix", { CurryIngredient(CurryType.Seasoned, it) }, CUISINE)
    val TIN_OF_BEANS = register("tin_of_beans", { CurryIngredient(CurryType.BeanMedley, it) }, CUISINE)
    val GIGANTAMIX = register("gigantamix", { CurryIngredient(CurryType.Gigantamax, it) }, CUISINE)

    /**
     * Player Consumables
     */
    val KOMALA_COFFEE = register("komala_coffee", { Item(it.food(FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).alwaysEdible().build())) }, CUISINE)
    val OMELETTE = register("omelette", { Item(it.food(FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).alwaysEdible().build())) }, CUISINE)
    val PINAP_JUICE = register("pinap_juice", { Item(it.food(FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).alwaysEdible().build())) }, CUISINE)
    val ROSERADE_TEA = register("roserade_tea", { Item(it.food(FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).alwaysEdible().build())) }, CUISINE)
    val TAPU_COCOA = register("tapu_cocoa", { Item(it.food(FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).alwaysEdible().build())) }, CUISINE)

    //Walkmons

    val POKE_WALKMON = register("poke_walkmon", { WalkmonItem(it, 1, "poke_walkmon") }, PLAYER_ITEMS)
    val GREAT_WALKMON = register("great_walkmon", { WalkmonItem(it, 2, "great_walkmon") }, PLAYER_ITEMS)
    val ULTRA_WALKMON = register("ultra_walkmon", { WalkmonItem(it, 3, "ultra_walkmon") }, PLAYER_ITEMS)
    val MASTER_WALKMON = register("master_walkmon", { WalkmonItem(it, 4, "master_walkmon") }, PLAYER_ITEMS)
    val HI_TECH_EARBUDS = register("hi_tech_earbuds", { WalkmonItem(it, 4, "hi_tech_earbuds") }, PLAYER_ITEMS)
    val GB_SOUNDS = register("gb_sounds", { WalkmonItem(it, 4, "hi_tech_earbuds") })

    //Discs
    val AZALEA_TOWN_DISC = createMusicDisc("azalea_town_disc", RecordSongs.AZALEA_TOWN);
    val CASCARRAFA_CITY_DISC = createMusicDisc("cascarrafa_city_disc", RecordSongs.CASCARRAFA_CITY);
    val CERULEAN_CITY_DISC = createMusicDisc("cerulean_city_disc", RecordSongs.CERULEAN_CITY);
    val ETERNA_CITY_DISC = createMusicDisc("eterna_city_disc", RecordSongs.ETERNA_CITY);
    val GOLDENROD_CITY_DISC = createMusicDisc("goldenrod_city_disc", RecordSongs.GOLDENROD_CITY);
    val ICIRRUS_CITY_DISC = createMusicDisc("icirrus_city_disc", RecordSongs.ICIRRUS_CITY);
    val JUBILIFE_VILLAGE_DISC = createMusicDisc("jubilife_village_disc", RecordSongs.JUBILIFE_VILLAGE);
    val LAKE_OF_RAGE_DISC = createMusicDisc("lake_of_rage_disc", RecordSongs.LAKE_OF_RAGE);
    val LAVERRE_CITY_DISC = createMusicDisc("laverre_city_disc", RecordSongs.LAVERRE_CITY);
    val LILLIE_DISC = createMusicDisc("lillie_disc", RecordSongs.LILLIE);
    val POKEMON_CENTER_DISC = createMusicDisc("pokemon_center_disc", RecordSongs.POKEMON_CENTER);
    val ROUTE_228_DISC = createMusicDisc("route_228_disc", RecordSongs.ROUTE_228);
    val SLUMBERING_WEALD_DISC = createMusicDisc("slumbering_weald_disc", RecordSongs.SLUMBERING_WEALD);
    val SURF_DISC = createMusicDisc("surf_disc", RecordSongs.SURF);
    val VERMILION_CITY_DISC = createMusicDisc("vermilion_city_disc", RecordSongs.VERMILION_CITY);
    val CYNTHIA_DISC = createMusicDisc("cynthia_disc", RecordSongs.CYNTHIA);
    val DEOXYS_DISC = createMusicDisc("deoxys_disc", RecordSongs.DEOXYS);
    val IRIS_DISC = createMusicDisc("iris_disc", RecordSongs.IRIS);
    val KANTO_DISC = createMusicDisc("kanto_disc", RecordSongs.KANTO);
    val LUSAMINE_DISC = createMusicDisc("lusamine_disc", RecordSongs.LUSAMINE);
    val NEMONA_DISC = createMusicDisc("nemona_disc", RecordSongs.NEMONA);
    val NESSA_DISC = createMusicDisc("nessa_disc", RecordSongs.NESSA);
    val PENNY_DISC = createMusicDisc("penny_disc", RecordSongs.PENNY);
    val RIVAL_DISC = createMusicDisc("rival_disc", RecordSongs.RIVAL);
    val SADA_AND_TURO_DISC = createMusicDisc("sada_and_turo_disc", RecordSongs.SADA_AND_TURO);
    val SOUTH_PROVINCE_DISC = createMusicDisc("south_province_disc", RecordSongs.SOUTH_PROVINCE);
    val TEAM_ROCKET_DISC = createMusicDisc("team_rocket_disc", RecordSongs.TEAM_ROCKET);
    val ULTRA_NECROZMA_DISC = createMusicDisc("ultra_necrozma_disc", RecordSongs.ULTRA_NECROZMA);
    val XY_LEGENDARY_DISC = createMusicDisc("xy_legendary_disc", RecordSongs.XY_LEGENDARY);
    val ZINNIA_DISC = createMusicDisc("zinnia_disc", RecordSongs.ZINNIA);
    val LAVENDER_TOWN_DISC = createMusicDisc("lavender_town_disc", RecordSongs.LAVENDER_TOWN);
    val LUGIA_DISC = createMusicDisc("lugia_disc", RecordSongs.LUGIA);
    val MT_PYRE_DISC = createMusicDisc("mt_pyre_disc", RecordSongs.MT_PYRE);

    /**
     * Tera Shards
     */
    val TERA_BUG_SHARD = createTeraShard("bug_tera_shard", TeraTypes.BUG)
    val TERA_DARK_SHARD = createTeraShard("dark_tera_shard", TeraTypes.DARK)
    val TERA_DRAGON_SHARD = createTeraShard("dragon_tera_shard", TeraTypes.DRAGON)
    val TERA_ELECTRIC_SHARD = createTeraShard("electric_tera_shard", TeraTypes.ELECTRIC)
    val TERA_FAIRY_SHARD = createTeraShard("fairy_tera_shard", TeraTypes.FAIRY)
    val TERA_FIGHTING_SHARD = createTeraShard("fighting_tera_shard", TeraTypes.FIGHTING)
    val TERA_FIRE_SHARD = createTeraShard("fire_tera_shard", TeraTypes.FIRE)
    val TERA_FLYING_SHARD = createTeraShard("flying_tera_shard", TeraTypes.FLYING)
    val TERA_GHOST_SHARD = createTeraShard("ghost_tera_shard", TeraTypes.GHOST)
    val TERA_GRASS_SHARD = createTeraShard("grass_tera_shard", TeraTypes.GRASS)
    val TERA_GROUND_SHARD = createTeraShard("ground_tera_shard", TeraTypes.GROUND)
    val TERA_ICE_SHARD = createTeraShard("ice_tera_shard", TeraTypes.ICE)
    val TERA_NORMAL_SHARD = createTeraShard("normal_tera_shard", TeraTypes.NORMAL)
    val TERA_POISON_SHARD = createTeraShard("poison_tera_shard", TeraTypes.POISON)
    val TERA_PSYCHIC_SHARD = createTeraShard("psychic_tera_shard", TeraTypes.PSYCHIC)
    val TERA_ROCK_SHARD = createTeraShard("rock_tera_shard", TeraTypes.ROCK)
    val TERA_STEEL_SHARD = createTeraShard("steel_tera_shard", TeraTypes.STEEL)
    val TERA_STELLAR_SHARD = createTeraShard("stellar_tera_shard", TeraTypes.STELLAR)
    val TERA_WATER_SHARD = createTeraShard("water_tera_shard", TeraTypes.WATER)

    /**
     * Un-Implemented Items : Items currently have no in-game function
     */
    val ABILITY_URGE = register("ability_urge", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val EXP_ALL = register("exp_all", ::Item, UNIMPLEMENTED)
    val ADVENTURE_GUIDE = register("adventure_guide", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val APRICORN_BOX = register("apricorn_box", { Item(it.stacksTo(1)) }, UNIMPLEMENTED)
    val AQUA_SUIT = register("aqua_suit", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ARMOR_PASS = register("armor_pass", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ARMORITE_ORE = register("armorite_ore", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val AURORA_TICKET = register("aurora_ticket", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val AUTOGRAPH = register("autograph", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val AZURE_FLUTE = register("azure_flute", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val BAND_AUTOGRAPH = register("band_autograph", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val BASEMENT_KEY_1 = register("basement_key_1", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val BASEMENT_KEY_2 = register("basement_key_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val BERRY_PLANTER = register("berry_planter", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val BERRY_POUCH = register("berry_pouch", { Item(it.stacksTo(1)) }, UNIMPLEMENTED)
    val BIKE_VOUCHER = register("bike_voucher", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val BLUE_CARD = register("blue_card", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val BLUE_SCARF = register("blue_scarf", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val BLUE_SPHERE = register("blue_sphere", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val CAMPING_GEAR = register("camping_gear", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val CARD_KEY_1 = register("card_key_1", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val CARD_KEY_2 = register("card_key_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val CARROT_SEEDS = register("carrot_seeds", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val CLEAR_BELL = register("clear_bell", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val COIN = register("coin", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val COIN_CASE = register("coin_case", { Item(it.stacksTo(1)) }, UNIMPLEMENTED)
    val COLRESS_MACHINE = register("colress_machine", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val COMMON_STONE = register("common_stone", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val CONTEST_COSTUME_1 = register("contest_costume_1", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val CONTEST_COSTUME_2 = register("contest_costume_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val CONTEST_PASS = register("contest_pass", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val COUPON_1 = register("coupon_1", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val COUPON_2 = register("coupon_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val COUPON_3 = register("coupon_3", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val COURAGE_CANDY = register("courage_candy", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val COURAGE_CANDY_L = register("courage_candy_l", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val COURAGE_CANDY_XL = register("courage_candy_xl", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val CROWN_PASS = register("crown_pass", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val DATA_CARDS = register("data_cards", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val DEVON_PARTS = register("devon_parts", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val DEVON_SCOPE = register("devon_scope", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val DEVON_SCUBA_GEAR = register("devon_scuba_gear", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val DIRE_HIT_2 = register("dire_hit_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val DIRE_HIT_3 = register("dire_hit_3", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val DISCOUNT_COUPON = register("discount_coupon", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val DOWSING_MACHINE_1 = register("dowsing_machine_1", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val DOWSING_MACHINE_2 = register("dowsing_machine_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val DOWSING_MCHN = register("dowsing_mchn", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val DRAGON_SKULL = register("dragon_skull", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val DROPPED_ITEM_RED = register("dropped_item_red", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val DROPPED_ITEM_YELLOW = register("dropped_item_yellow", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ELEVATOR_KEY = register("elevator_key", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ENDORSEMENT = register("endorsement", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val EON_FLUTE = register("eon_flute", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val EON_TICKET = register("eon_ticket", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val EXPLORER_KIT = register("explorer_kit", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val FAME_CHECKER = register("fame_checker", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val FASHION_CASE = register("fashion_case", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val FESTIVAL_TICKET = register("festival_ticket", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val FORAGE_BAG = register("forage_bag", { Item(it.stacksTo(1)) }, UNIMPLEMENTED)
    val GALACTIC_KEY = register("galactic_key", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val GO_GOGGLES = register("go_goggles", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val GOLD_TEETH = register("gold_teeth", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val GOLDEN_NANAB_BERRY = register("golden_nanab_berry", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val GOLDEN_PINAP_BERRY = register("golden_pinap_berry", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val GOLDEN_RAZZ_BERRY = register("golden_razz_berry", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val GRAM = register("gram", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)

    val GREEN_SCARF = register("green_scarf", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val GREEN_SPHERE = register("green_sphere", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val GREEN_TEA = register("green_tea", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val GRUBBY_HANKY = register("grubby_hanky", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val HEALTH_CANDY = register("health_candy", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val HEALTH_CANDY_L = register("health_candy_l", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val HEALTH_CANDY_XL = register("health_candy_xl", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val HOLO_CASTER_1 = register("holo_caster_1", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val HOLO_CASTER_2 = register("holo_caster_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val HONOR_OF_KALOS = register("honor_of_kalos", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val INTRIGUING_STONE = register("intriguing_stone", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ITEM_DROP = register("item_drop", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ITEM_URGE = register("item_urge", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val JOURNAL = register("journal", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val KEY_STONE_2 = register("key_stone_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val KEY_TO_ROOM_1 = register("key_to_room_1", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val KEY_TO_ROOM_2 = register("key_to_room_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val KEY_TO_ROOM_4 = register("key_to_room_4", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val KEY_TO_ROOM_6 = register("key_to_room_6", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val LEAF_LETTER = register("leaf_letter", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val LEGENDARY_CLUE = register("legendary_clue", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val LENS_CASE = register("lens_case", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val LETTER = register("letter", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val LIBERTY_PASS = register("liberty_pass", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val LIFT_KEY = register("lift_key", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val LOCK_CAPSULE_1 = register("lock_capsule_1", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val LOCK_CAPSULE_2 = register("lock_capsule_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val LOOKER_TICKET = register("looker_ticket", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val LOOT_SACK = register("loot_sack", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val LOST_ITEM_1 = register("lost_item_1", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val LOST_ITEM_2 = register("lost_item_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val LUNAR_FEATHER = register("lunar_feather", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val LURE = register("lure", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val MACHINE_PART = register("machine_part", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val MAGMA_EMBLEM = register("magma_emblem", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val MAGMA_SUIT = register("magma_suit", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val MAKEUP_BAG = register("makeup_bag", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val MAX_LURE = register("max_lure", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val MEDAL_BOX = register("medal_box", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val MEMBER_CARD = register("member_card", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val MIGHTY_CANDY = register("mighty_candy", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val MIGHTY_CANDY_L = register("mighty_candy_l", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val MIGHTY_CANDY_XL = register("mighty_candy_xl", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val MYSTIC_TICKET = register("mystic_ticket", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val OAKS_LETTER = register("oaks_letter", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val OAKS_PARCEL = register("oaks_parcel", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val OLD_CHARM = register("old_charm", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val OLD_LETTER = register("old_letter", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val OLD_SEA_MAP = register("old_sea_map", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val PAIR_OF_TICKETS = register("pair_of_tickets", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val PAL_PAD = register("pal_pad", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val PALE_SPHERE = register("pale_sphere", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val PASS = register("pass", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val PASS_ORB = register("pass_orb", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val PERMIT = register("permit", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val PEWTER_CRUNCHIES = register("pewter_crunchies", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val PINK_SCARF = register("pink_scarf", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val PLASMA_CARD = register("plasma_card", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val POFFIN = register("poffin", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val POFFIN_CASE = register("poffin_case", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val POINT_CARD = register("point_card", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val POKE_FLUTE = register("poke_flute", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val POKE_RADAR = register("poke_radar", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val POKEBLOCK_CASE = register("pokeblock_case", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val POKEBLOCK_KIT = register("pokeblock_kit", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val POKETCH_BLUE = register("poketch_blue", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val POKETCH_RED = register("poketch_red", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val POWDER_JAR = register("powder_jar", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val POWER_PLANT_PASS = register("power_plant_pass", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val PRISM_SPHERE = register("prism_sphere", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val PROFESSORS_MASK = register("professors_mask", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val PROFS_LETTER = register("profs_letter", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val PROP_CASE = register("prop_case", { Item(it.stacksTo(1)) }, UNIMPLEMENTED)
    val QUICK_CANDY = register("quick_candy", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val QUICK_CANDY_L = register("quick_candy_l", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val QUICK_CANDY_XL = register("quick_candy_xl", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)

    val RAINBOW_FLOWER = register("rainbow_flower", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val RAINBOW_PASS = register("rainbow_pass", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)

    val RED_SCALE = register("red_scale", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val RED_SPHERE = register("red_sphere", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val RESET_URGE = register("reset_urge", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val RIDE_PAGER = register("ride_pager", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ROLLER_SKATES = register("roller_skates", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ROTO_BARGAIN = register("roto_bargain", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ROTO_BOOST = register("roto_boost", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ROTO_CATCH = register("roto_catch", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ROTO_ENCOUNTER = register("roto_encounter", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ROTO_EXP_POINTS = register("roto_exp_points", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ROTO_FRIENDSHIP = register("roto_friendship", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ROTO_HATCH = register("roto_hatch", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ROTO_HP_RESTORE = register("roto_hp_restore", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ROTO_PP_RESTORE = register("roto_pp_restore", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ROTO_PRIZE_MONEY = register("roto_prize_money", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val ROTO_STEALTH = register("roto_stealth", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val RULE_BOOK = register("rule_book", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SCANNER = register("scanner", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SEAL_BAG = register("seal_bag", { Item(it.stacksTo(1)) }, UNIMPLEMENTED)
    val SEAL_CASE = register("seal_case", { Item(it.stacksTo(1)) }, UNIMPLEMENTED)
    val SECRET_KEY_1 = register("secret_key_1", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SECRET_KEY_2 = register("secret_key_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SECRET_MEDICINE = register("secret_medicine", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SILPH_SCOPE = register("silph_scope", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SILVER_NANAB_BERRY = register("silver_nanab_berry", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SILVER_PINAP_BERRY = register("silver_pinap_berry", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SILVER_RAZZ_BERRY = register("silver_razz_berry", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SINNOH_STONE = register("sinnoh_stone", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SMART_CANDY = register("smart_candy", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SMART_CANDY_L = register("smart_candy_l", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SMART_CANDY_XL = register("smart_candy_xl", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SONIAS_BOOK = register("sonias_book", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SOOT_SACK = register("soot_sack", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SS_TICKET = register("ss_ticket", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val STORAGE_KEY_1 = register("storage_key_1", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val STORAGE_KEY_2 = register("storage_key_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val STYLE_CARD = register("style_card", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SUITE_KEY = register("suite_key", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val SUPER_LURE = register("super_lure", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val TEACHY_TV = register("teachy_tv", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val TIDAL_BELL = register("tidal_bell", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val TM_CASE = register("tm_case", { Item(it.stacksTo(1)) }, UNIMPLEMENTED)
    val TM_MATERIAL = register("tm_material", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val TMV_PASS = register("tmv_pass", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val TOTEM_STICKER = register("totem_sticker", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val TOUGH_CANDY = register("tough_candy", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val TOUGH_CANDY_L = register("tough_candy_l", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val TOUGH_CANDY_XL = register("tough_candy_xl", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val TOWN_MAP_1 = register("town_map_1", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val TOWN_MAP_2 = register("town_map_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val TOWN_MAP_3 = register("town_map_3", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val TRAVEL_TRUNK_1 = register("travel_trunk_1", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val TRAVEL_TRUNK_2 = register("travel_trunk_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val TRI_PASS = register("tri_pass", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val UNOVA_STONE = register("unova_stone", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val UNOWN_REPORT = register("unown_report", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val VS_RECORDER = register("vs_recorder", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val VS_SEEKER = register("vs_seeker", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val WISHING_CHIP = register("wishing_chip", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val WISHING_PIECE = register("wishing_piece", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val WOODEN_CROWN = register("wooden_crown", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val WORKS_KEY = register("works_key", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_ACCURACY_2 = register("x_accuracy_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_ACCURACY_3 = register("x_accuracy_3", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_ACCURACY_6 = register("x_accuracy_6", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_ATTACK_2 = register("x_attack_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_ATTACK_3 = register("x_attack_3", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_ATTACK_6 = register("x_attack_6", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_DEFENSE_2 = register("x_defense_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_DEFENSE_3 = register("x_defense_3", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_DEFENSE_6 = register("x_defense_6", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_SPECIAL_ATTACK_2 = register("x_special_attack_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_SPECIAL_ATTACK_3 = register("x_special_attack_3", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_SPECIAL_ATTACK_6 = register("x_special_attack_6", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_SPECIAL_DEFENSE_2 = register("x_special_defense_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_SPECIAL_DEFENSE_3 = register("x_special_defense_3", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_SPECIAL_DEFENSE_6 = register("x_special_defense_6", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_SPEED_2 = register("x_speed_2", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_SPEED_3 = register("x_speed_3", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_SPEED_6 = register("x_speed_6", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_TRANSCEIVER_BLUE = register("x_transceiver_blue", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_TRANSCEIVER_RED = register("x_transceiver_red", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val X_TRANSCEIVER_YELLOW = register("x_transceiver_yellow", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val YELLOW_SCARF = register("yellow_scarf", { Item(it.stacksTo(64)) }, UNIMPLEMENTED)
    val GALARICA_TWIG = register("galarica_twig", ::Item, UNIMPLEMENTED)

    /**
     * Items to be included in Minecraft Default Section.
     */

    @JvmField
    val CURRY = register("curry", ::ItemCurry, CUISINE)

    @JvmField
    val ULTRA_DARK_SIGN = registerSign("ultra_dark_sign") {
        SignItem(
            it.stacksTo(16),
            GenerationsWood.ULTRA_DARK_SIGN,
            GenerationsWood.ULTRA_DARK_WALL_SIGN
        )
    }
    @JvmField
    val ULTRA_DARK_HANGING_SIGN = registerSign("ultra_dark_hanging_sign") {
        HangingSignItem(
            GenerationsWood.ULTRA_DARK_HANGING_SIGN,
            GenerationsWood.ULTRA_DARK_WALL_HANGING_SIGN,
            it.stacksTo(16)
        )
    }
    @JvmField
    val ULTRA_JUNGLE_SIGN = registerSign("ultra_jungle_sign") {
        SignItem(
            it.stacksTo(16),
            GenerationsWood.ULTRA_JUNGLE_SIGN,
            GenerationsWood.ULTRA_JUNGLE_WALL_SIGN
        )
    }
    @JvmField
    val ULTRA_JUNGLE_HANGING_SIGN = registerSign("ultra_jungle_hanging_sign") {
        HangingSignItem(
            GenerationsWood.ULTRA_JUNGLE_HANGING_SIGN,
            GenerationsWood.ULTRA_JUNGLE_WALL_HANGING_SIGN,
            it.stacksTo(16)
        )
    }
    @JvmField
    val GHOST_SIGN = registerSign("ghost_sign") {
        SignItem(
            it.stacksTo(16),
            GenerationsWood.GHOST_SIGN,
            GenerationsWood.GHOST_WALL_SIGN
        )
    }
    @JvmField
    val GHOST_HANGING_SIGN = registerSign("ghost_hanging_sign") {
        HangingSignItem(
            GenerationsWood.GHOST_HANGING_SIGN,
            GenerationsWood.GHOST_WALL_HANGING_SIGN,
            it.stacksTo(16)
        )
    }

    @JvmField
    val GHOST_BOAT_ITEM =
        register("ghost_boat", { GenerationsBoatItem(it, GenerationsBoatEntity.Type.GHOST) }, BUILDING_BLOCKS)
    @JvmField
    val GHOST_CHEST_BOAT_ITEM = register(
        "ghost_boat_with_chest",
        { GenerationsChestBoatItem(it, GenerationsBoatEntity.Type.GHOST) },
        BUILDING_BLOCKS
    )
    @JvmField
    val ULTRA_DARK_BOAT_ITEM = register(
        "ultra_dark_boat",
        { GenerationsBoatItem(it, GenerationsBoatEntity.Type.ULTRA_DARK) },
        BUILDING_BLOCKS
    )
    @JvmField
    val ULTRA_DARK_CHEST_BOAT_ITEM = register(
        "ultra_dark_boat_with_chest",
        { GenerationsChestBoatItem(it, GenerationsBoatEntity.Type.ULTRA_DARK) },
        BUILDING_BLOCKS
    )
    @JvmField
    val ULTRA_JUNGLE_BOAT_ITEM = register(
        "ultra_jungle_boat",
        { GenerationsBoatItem(it, GenerationsBoatEntity.Type.ULTRA_JUNGLE) },
        BUILDING_BLOCKS
    )
    @JvmField
    val ULTRA_JUNGLE_CHEST_BOAT_ITEM = register(
        "ultra_jungle_boat_with_chest",
        { GenerationsChestBoatItem(it, GenerationsBoatEntity.Type.ULTRA_JUNGLE) },
        BUILDING_BLOCKS
    )

    private fun createRelicSong(inert: Boolean): Item = register(
        (if (inert) "inert_" else "") + "relic_song",
        { RelicSongItem(it.stacksTo(1), inert) },
        LEGENDARY_ITEMS
    )

    private fun createTeraShard(name: String, teraType: TeraType): Item {
        return register(name, { TeraSharditem(it, teraType) }, PLAYER_ITEMS)
    }

    private fun registerHeldItem(
        name: String,
        function: (Item.Properties) -> Item = ::Item
    ): Item = register(
        name,
        { function.invoke(it).also { CobblemonHeldItemManager.registerRemap(it, name.replace("_", "")) } },
        HELD_ITEMS
    )

    fun of(): Item.Properties = Item.Properties()

    fun <T : Item> register(
        name: String,
        itemSupplier: (Item.Properties) -> T,
        register: ItemPlatformRegistry = ITEMS
    ): Item = register.create(name.generationsResource(), itemSupplier.invoke(of()))

    fun <T : MoveTeachingItem> registerTm(
        name: String,
        itemSupplier: (Item.Properties) -> T,
        register: ItemPlatformRegistry = ITEMS
    ): T = register.create(name.generationsResource(), itemSupplier.invoke(of()))

    fun registerTmRegular(name: String, move: String): TechnicalMachineItem =
        registerTm(name, { TechnicalMachineItem(move, it) }, PLAYER_ITEMS).also(TMS::add)

    private fun registerSign(name: String, itemSupplier: (Item.Properties) -> Item): Item = register(name, itemSupplier)

    private fun createBadge(id: String): Item = register(id + "_badge", ::BadgeItem, BADGES)

    private fun createRibbon(id: String): Item = register(id, ::RibbonItem, RIBBONS)

    private fun createMusicDisc(name: String, jukeboxSong: ResourceKey<JukeboxSong>): Item =
        register(
            name,
            { Item(it.jukeboxPlayable(jukeboxSong).stacksTo(1)).tab(CreativeModeTabs.TOOLS_AND_UTILITIES) },
            PLAYER_ITEMS
        )

    private fun registerClosedMail(name: String, type: MailType): Item = register(name, type::createClosedMailItem, POKEMAIL)

    private fun registerMail(name: String, type: MailType): Item = register(name, type::createMailItem, POKEMAIL)

    private fun registerPlate(name: String, type: ElementalType): Item = register(
        name,
        {
            createFormChangingItem(
                it,
                "type",
                type.name,
                cobblemonResource("arceus")
            ).also { CobblemonHeldItemManager.registerRemap(it, name.replace("_", "")) }
        },
        FORM_ITEMS
    )

    private fun registerDrive(name: String, type: String): Item = register(
        name,
        {
            createFormChangingItem(
                it,
                "drive",
                type,
                cobblemonResource("genesect")
            ).also { CobblemonHeldItemManager.registerRemap(it, name.replace("_", "")) }
        },
        FORM_ITEMS
    )

    private fun registerMemory(name: String, type: ElementalType): Item = register(
        name,
        {
            createFormChangingItem(
                it,
                "type",
                type.name,
                cobblemonResource("silvally")
            ).also { CobblemonHeldItemManager.registerRemap(it, name.replace("_", "").replace("drive", "")) }
        },
        FORM_ITEMS
    )

    private fun registerCap(name: String): Item =
        register("${name}_cap", { createFormChangingItem(it, "pikachu_cap", name) }, FORM_ITEMS)

    @JvmStatic
    fun init(consumer: (ItemPlatformRegistry) -> Unit) {

        GenerationsCore.LOGGER.info("Registering Generations Items")
        consumer.invoke(ITEMS)
        consumer.invoke(RIBBONS)
        consumer.invoke(BADGES)
        consumer.invoke(UNIMPLEMENTED)
        consumer.invoke(CUISINE)
        consumer.invoke(NATURAL)
        consumer.invoke(RESTORATION)
        consumer.invoke(PLAYER_ITEMS)
        consumer.invoke(HELD_ITEMS)
        consumer.invoke(POKEMAIL)
        consumer.invoke(LEGENDARY_ITEMS)
        consumer.invoke(UTILITY)
        consumer.invoke(VALUABLES)
        consumer.invoke(FORM_ITEMS)
        consumer.invoke(BUILDING_BLOCKS)
    }
}

