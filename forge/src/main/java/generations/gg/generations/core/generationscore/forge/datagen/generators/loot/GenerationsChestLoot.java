package generations.gg.generations.core.generationscore.forge.datagen.generators.loot;

import com.cobblemon.mod.common.CobblemonItems;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.loot.ResouceKeyEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

class GenerationsChestLoot implements LootTableSubProvider {
	private static final ResourceLocation BEAST_BALL = GenerationsCore.id("chests/beast_ball");
	private static final ResourceLocation CHERISH_BALL = GenerationsCore.id("chests/cherish_ball");
	private static final ResourceLocation DIVE_BALL = GenerationsCore.id("chests/dive_ball");
	private static final ResourceLocation DREAM_BALL = GenerationsCore.id("chests/dream_ball");
	private static final ResourceLocation DUSK_BALL = GenerationsCore.id("chests/dusk_ball");
	private static final ResourceLocation FAST_BALL = GenerationsCore.id("chests/fast_ball");
	private static final ResourceLocation FRIEND_BALL = GenerationsCore.id("chests/friend_ball");
	private static final ResourceLocation GIGATON_BALL = GenerationsCore.id("chests/gigaton_ball");
	private static final ResourceLocation GREAT_BALL = GenerationsCore.id("chests/great_ball");
	private static final ResourceLocation HEAL_BALL = GenerationsCore.id("chests/heal_ball");
	private static final ResourceLocation HEAVY_BALL = GenerationsCore.id("chests/heavy_ball");
	private static final ResourceLocation JET_BALL = GenerationsCore.id("chests/jet_ball");
	private static final ResourceLocation LEADEN_BALL = GenerationsCore.id("chests/leaden_ball");
	private static final ResourceLocation LEVEL_BALL = GenerationsCore.id("chests/level_ball");
	private static final ResourceLocation LOVE_BALL = GenerationsCore.id("chests/love_ball");
	private static final ResourceLocation LURE_BALL = GenerationsCore.id("chests/lure_ball");
	private static final ResourceLocation LUXURY_BALL = GenerationsCore.id("chests/luxury_ball");
	private static final ResourceLocation MASTER_BALL = GenerationsCore.id("chests/master_ball");
	private static final ResourceLocation MOON_BALL = GenerationsCore.id("chests/moon_ball");
	private static final ResourceLocation NEST_BALL = GenerationsCore.id("chests/nest_ball");
	private static final ResourceLocation NET_BALL = GenerationsCore.id("chests/net_ball");
	private static final ResourceLocation ORIGIN_BALL = GenerationsCore.id("chests/origin_ball");
	private static final ResourceLocation PARK_BALL = GenerationsCore.id("chests/park_ball");
	private static final ResourceLocation POKE_BALL = GenerationsCore.id("chests/poke_ball");
	private static final ResourceLocation PREMIER_BALL = GenerationsCore.id("chests/premier_ball");
	private static final ResourceLocation QUICK_BALL = GenerationsCore.id("chests/quick_ball");
	private static final ResourceLocation REPEAT_BALL = GenerationsCore.id("chests/repeat_ball");
	private static final ResourceLocation SAFARI_BALL = GenerationsCore.id("chests/safari_ball");
	private static final ResourceLocation SPORT_BALL = GenerationsCore.id("chests/sport_ball");
	private static final ResourceLocation STRANGE_BALL = GenerationsCore.id("chests/strange_ball");
	private static final ResourceLocation TIMER_BALL = GenerationsCore.id("chests/timer_ball");
	private static final ResourceLocation ULTRA_BALL = GenerationsCore.id("chests/ultra_ball");
	private static final ResourceLocation WING_BALL = GenerationsCore.id("chests/wing_ball");
	@Override
	public void generate(@NotNull BiConsumer<ResourceLocation, LootTable.Builder> output) {
		//output.accept(BuiltInLootTables.ABANDONED_MINESHAFT, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).name(GenerationsCore.id("chests/heavy_ball").toString())));
		output.accept(BEAST_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1.0F, 3.0F),
						CobblemonItems.POKE_BALL.asItem(), CobblemonItems.ANTIDOTE.asItem(), CobblemonItems.ASSAULT_VEST.asItem(),
						CobblemonItems.BABIRI_BERRY.asItem(), CobblemonItems.BEAST_BALL.asItem(), GenerationsItems.BIG_NUGGET.get(),
						CobblemonItems.BLACK_SLUDGE.asItem(), CobblemonItems.BRIGHT_POWDER.asItem(), GenerationsItems.BRITTLE_BONES.get(),
						GenerationsItems.CELL_BATTERY.get(), GenerationsItems.CHALKY_STONE.get(), CobblemonItems.CHARCOAL.asItem(),
						CobblemonItems.CHARTI_BERRY.asItem(), CobblemonItems.CHOICE_BAND.asItem(), CobblemonItems.CHOICE_SPECS.asItem(),
						CobblemonItems.CHOPLE_BERRY.asItem(), CobblemonItems.COLBUR_BERRY.asItem(), GenerationsItems.COMET_SHARD.get(),
						GenerationsItems.COURAGE_CANDY_XL.get(), GenerationsItems.DNA_SPLICERS.get(), GenerationsItems.DRAGON_GEM.get(),
						GenerationsItems.DYNAMAX_CANDY.get(), CobblemonItems.ENIGMA_BERRY.asItem(), CobblemonItems.EXPERIENCE_CANDY_XL.asItem(),
						GenerationsItems.FAIRY_GEM.get(), GenerationsItems.FIRE_GEM.get(), CobblemonItems.FLAME_ORB.asItem(),
						CobblemonItems.FULL_HEAL.asItem(), CobblemonItems.GANLON_BERRY.asItem(), GenerationsItems.GIGANTAMIX.get(),
						CobblemonItems.HEAL_POWDER.asItem(), CobblemonItems.JABOCA_BERRY.asItem(), GenerationsItems.LAVA_COOKIE.get(),
						GenerationsItems.PERMIT.get(), CobblemonItems.POMEG_BERRY.asItem(), CobblemonItems.POWER_BELT.asItem(),
						CobblemonItems.RAWST_BERRY.asItem(), GenerationsItems.ROCK_INCENSE.get(), GenerationsItems.SEA_INCENSE.get(),
						GenerationsItems.SMART_CANDY_L.get(), GenerationsItems.SODA_POP.get(), GenerationsItems.STRANGE_SOUVENIR.get(),
						GenerationsItems.WEAKNESS_POLICY.get(), CobblemonItems.WEPEAR_BERRY.asItem(), GenerationsItems.WIDE_LENS.get(),
						GenerationsItems.X_SPECIAL_DEFENSE_2.get(), CobblemonItems.X_SPEED.asItem(), GenerationsItems.X_SPEED_6.get(),
						CobblemonItems.YACHE_BERRY.asItem(), GenerationsItems.YELLOW_NECTAR.get(), GenerationsItems.DRAGON_CANDY.get(),
						GenerationsItems.YELLOW_SHARD.get(), CobblemonItems.ZINC.asItem(), GenerationsItems.ZOOM_LENS.get(),
						Items.PURPUR_BLOCK, Items.END_STONE, Items.PRISMARINE, Items.GLOWSTONE_DUST, Items.NETHER_WART,
						Items.SPIDER_EYE, Items.WARPED_FUNGUS, Items.MUSIC_DISC_CAT, GenerationsItems.Z_INGOT.get(),
						Items.AMETHYST_SHARD, GenerationsItems.RUBY.get(), GenerationsItems.LAVA_CRYSTAL.get(),
						GenerationsItems.BELLIGERENT_WING.get(), GenerationsItems.TM_44.get(), GenerationsItems.TM_78.get(),
						GenerationsItems.TM_100.get(), GenerationsItems.TM_115.get(), GenerationsItems.TM_156.get(),
						GenerationsItems.TM_169.get(), CobblemonItems.SURPRISE_MULCH.asItem(), CobblemonItems.GROWTH_MULCH.asItem(),
						Items.NETHERITE_SCRAP, CobblemonItems.RICH_MULCH.asItem(), GenerationsItems.DYNITE_ORE.get(),
						GenerationsItems.TM_181.get(), GenerationsItems.TM_199.get(), GenerationsItems.TM_221.get()
				).add(ResouceKeyEntry.resourceKeyContents("lugia_disc")).add(key("lavender_town_disc")).add(key("mt_pyre_disc"))));


		output.accept(CHERISH_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1.0F, 2.0F),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ABILITY_PATCH.get(), GenerationsItems.AMULET_COIN.get(),
						CobblemonItems.ANTIDOTE.asItem(), GenerationsItems.BACHS_FOOD_TIN.get(), GenerationsItems.BEACH_GLASS.get(),
						CobblemonItems.BERRY_JUICE.asItem(), CobblemonItems.BERRY_SWEET.asItem(), GenerationsItems.BLACK_FLUTE.get(),
						GenerationsItems.BOILED_EGG.get(), GenerationsItems.BREAD.get(), CobblemonItems.CALCIUM.asItem(),
						CobblemonItems.CHERISH_BALL.asItem(), CobblemonItems.CHIPPED_POT.asItem(), GenerationsItems.DARK_GEM.get(),
						GenerationsItems.DISCOUNT_COUPON.get(), CobblemonItems.ELECTIRIZER.asItem(), GenerationsItems.ELECTRIC_GEM.get(),
						CobblemonItems.EVERSTONE.asItem(), CobblemonItems.EXPERIENCE_CANDY_M.asItem(), GenerationsItems.FIRE_GEM.get(),
						CobblemonItems.FLAME_ORB.asItem(), CobblemonItems.FLOWER_SWEET.asItem(), GenerationsItems.FRIED_FOOD.get(),
						CobblemonItems.FRIEND_BALL.asItem(), GenerationsItems.FRUIT_BUNCH.get(), GenerationsItems.GALARICA_TWIG.get(),
						CobblemonItems.GALARICA_WREATH.asItem(), GenerationsItems.HEART_SCALE.get(), CobblemonItems.HONDEW_BERRY.asItem(),
						GenerationsItems.HONEY.get(), CobblemonItems.HP_UP.asItem(), GenerationsItems.ICE_GEM.get(),
						CobblemonItems.JABOCA_BERRY.asItem(), GenerationsItems.LAX_INCENSE.get(), GenerationsItems.LIGHT_BALL.get(), CobblemonItems.LOVE_BALL.asItem(),
						CobblemonItems.LOVE_SWEET.asItem(), GenerationsItems.METRONOME.get(), GenerationsItems.MIGHTY_CANDY_L.get(),
						CobblemonItems.MOON_BALL.asItem(), CobblemonItems.PECHA_BERRY.asItem(), CobblemonItems.PERSIM_BERRY.asItem(),
						GenerationsItems.PINK_SCARF.get(), CobblemonItems.POKE_BALL.asItem(), CobblemonItems.POTION.asItem(),
						CobblemonItems.POWER_BELT.asItem(), GenerationsItems.PURE_INCENSE.get(), GenerationsItems.RED_FLUTE.get(),
						GenerationsItems.RELIC_SILVER.get(), GenerationsItems.RESET_URGE.get(), GenerationsItems.ROSE_INCENSE.get(),
						CobblemonItems.SACHET.asItem(), GenerationsItems.SAUSAGES.get(), GenerationsItems.SHALOUR_SABLE.get(), CobblemonItems.SHARP_BEAK.asItem(),
						CobblemonItems.SILK_SCARF.asItem(), GenerationsItems.SMALL_BOUQUET.get(), GenerationsItems.SMART_CANDY.get(),
						GenerationsItems.SPICE_MIX.get(), CobblemonItems.STARF_BERRY.asItem(), GenerationsItems.STICKY_BARB.get(),
						GenerationsItems.STRETCHY_SPRING.get(), CobblemonItems.SWEET_APPLE.asItem(), GenerationsItems.SWEET_HEART.get(),
						CobblemonItems.TART_APPLE.asItem(), CobblemonItems.TOXIC_ORB.asItem(), GenerationsItems.TROPICAL_SHELL.get(),
						GenerationsItems.WAVE_INCENSE.get(), GenerationsItems.X_SPECIAL_ATTACK_6.get(), GenerationsItems.ROTOM_CATALOG.get(),
						Items.GOLD_INGOT, Items.SADDLE, Items.QUARTZ, Items.PRISMARINE_SHARD, Items.RABBIT_HIDE,
						Items.MUSIC_DISC_CAT, GenerationsItems.EXP_CHARM.get(), GenerationsItems.ROCKSTAR_COSTUME.get(),
						GenerationsItems.BELLE_COSTUME.get(), GenerationsItems.POPSTAR_COSTUME.get(), GenerationsItems.PHD_COSTUME.get(),
						GenerationsItems.LIBRE_COSTUME.get(), GenerationsItems.RUSTY_IRON_KEY_1.get(), GenerationsItems.SHATTERED_ICE_KEY_1.get(),
						GenerationsItems.CRUMBLED_ROCK_KEY_1.get(), GenerationsItems.FRAGMENTED_DRAGO_KEY_1.get(), GenerationsItems.DISCHARGED_ELEKI_KEY_1.get(),
						GenerationsItems.SHATTERED_RELIC_SONG_1.get(), CobblemonItems.SERIOUS_MINT.asItem(), CobblemonItems.LONELY_MINT.asItem(),
						CobblemonItems.BRAVE_MINT.asItem(), GenerationsItems.DRAGON_MEMORY_DRIVE.get(), GenerationsItems.DRACO_PLATE.get(),
						GenerationsItems.ICE_CANDY.get(), GenerationsItems.PSYCHIC_CANDY.get(), GenerationsItems.TM_10.get(),
						GenerationsItems.TM_34.get(), GenerationsItems.TM_46.get(), GenerationsItems.TM_52.get(),
						GenerationsItems.TM_69.get(), GenerationsItems.TM_124.get(), GenerationsItems.TM_135.get(),
						GenerationsItems.TM_143.get(), GenerationsItems.TM_4.get(), GenerationsItems.TM_16.get(),
						GenerationsItems.TM_41.get(), GenerationsItems.TM_54.get(), GenerationsItems.TM_59.get(),
						GenerationsItems.TM_63.get(), GenerationsItems.TM_74.get(), GenerationsItems.TM_75.get(),
						GenerationsItems.TM_85.get(), GenerationsItems.TM_92.get(), GenerationsItems.TM_98.get(),
						GenerationsItems.TM_109.get(), GenerationsItems.TM_120.get(), GenerationsItems.TM_128.get(),
						GenerationsItems.TM_129.get(), GenerationsItems.TM_138.get(), GenerationsItems.TM_161.get(),
						GenerationsItems.TM_201.get(), GenerationsItems.TM_227.get()
				)));

		//output.accept(BuiltInLootTables.RUINED_PORTAL, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))).withPool(CHERISH_BALL));

		output.accept(DIVE_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1.0F, 2.0F),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ABSORB_BULB.get(), GenerationsItems.BEACH_GLASS.get(),
						GenerationsItems.BLUE_SHARD.get(), GenerationsItems.DAMP_ROCK.get(), CobblemonItems.DEEP_SEA_SCALE.asItem(),
						CobblemonItems.DEEP_SEA_TOOTH.asItem(), CobblemonItems.DIVE_BALL.asItem(), GenerationsItems.HEALTH_CANDY.get(),
						GenerationsItems.ICE_GEM.get(), CobblemonItems.ICE_HEAL.asItem(), CobblemonItems.ICE_STONE.asItem(),
						GenerationsItems.ICY_ROCK.get(), CobblemonItems.LANSAT_BERRY.asItem(), GenerationsItems.LARGE_LEEK.get(),
						GenerationsItems.LAX_INCENSE.get(), CobblemonItems.LEPPA_BERRY.asItem(), CobblemonItems.LEVEL_BALL.asItem(),
						CobblemonItems.LIGHT_CLAY.asItem(), GenerationsItems.LUCKY_PUNCH.get(), CobblemonItems.LUM_BERRY.asItem(),
						GenerationsItems.LUMINOUS_MOSS.get(), GenerationsItems.LUMIOSE_GALETTE.get(), GenerationsItems.LURE.get(),
						CobblemonItems.LURE_BALL.asItem(), CobblemonItems.LUXURY_BALL.asItem(), CobblemonItems.MAGO_BERRY.asItem(),
						GenerationsItems.MARBLE.get(), GenerationsItems.MAX_LURE.get(), CobblemonItems.MAX_POTION.asItem(),
						GenerationsItems.MIGHTY_CANDY_L.get(), CobblemonItems.MOON_STONE.asItem(), CobblemonItems.MYSTIC_WATER.asItem(),
						CobblemonItems.NET_BALL.asItem(), CobblemonItems.PARALYZE_HEAL.asItem(), GenerationsItems.PASS_ORB.get(),
						CobblemonItems.PASSHO_BERRY.asItem(), CobblemonItems.PERSIM_BERRY.asItem(), CobblemonItems.PINAP_BERRY.asItem(),
						GenerationsItems.POKE_DOLL.get(), CobblemonItems.POTION.asItem(), CobblemonItems.POWER_BELT.asItem(),
						CobblemonItems.PRISM_SCALE.asItem(), GenerationsItems.PUNGENT_ROOT.get(), GenerationsItems.PURE_INCENSE.get(),
						CobblemonItems.RABUTA_BERRY.asItem(), GenerationsItems.RELIC_GOLD.get(), GenerationsItems.SEA_INCENSE.get(),
						GenerationsItems.SHOAL_SALT.get(), GenerationsItems.SHOAL_SHELL.get(), GenerationsItems.SMOKED_POKE_TAIL.get(),
						GenerationsItems.SMOOTH_ROCK.get(), CobblemonItems.SOFT_SAND.asItem(), GenerationsItems.SPRINK_LOTAD.get(),
						GenerationsItems.STRETCHY_SPRING.get(), GenerationsItems.SUPER_LURE.get(), CobblemonItems.TANGA_BERRY.asItem(),
						GenerationsItems.TOUGH_CANDY_XL.get(), GenerationsItems.TROPICAL_SHELL.get(), CobblemonItems.WACAN_BERRY.asItem(),
						GenerationsItems.WATER_GEM.get(), CobblemonItems.WATMEL_BERRY.asItem(), GenerationsItems.WAVE_INCENSE.get(),
						CobblemonItems.YACHE_BERRY.asItem(), Items.SPONGE, Items.PRISMARINE, Items.SCUTE, Items.INK_SAC,
						Items.PRISMARINE_SHARD, Items.PRISMARINE_CRYSTALS, Items.MUSIC_DISC_BLOCKS, GenerationsItems.DYNITE_ORE.get(),
						GenerationsItems.OLD_ROD.get(), GenerationsItems.GOOD_ROD.get(), GenerationsItems.SUPER_ROD.get(),
						GenerationsItems.WAILMER_PAIL.get(), GenerationsItems.LAVA_CRYSTAL.get(), CobblemonItems.ADAMANT_MINT.asItem(),
						CobblemonItems.NAUGHTY_MINT.asItem(), CobblemonItems.BOLD_MINT.asItem(), GenerationsItems.WATER_MEMORY_DRIVE.get(),
						GenerationsItems.SPLASH_PLATE.get(), GenerationsItems.POKEMAIL_AIR.get(), GenerationsItems.POKEMAIL_BLOOM.get(),
						GenerationsItems.POKEMAIL_BRICK.get(), GenerationsItems.POKEMAIL_BRIDGE_D.get(), GenerationsItems.POKEMAIL_BRIDGE_M.get(),
						GenerationsItems.POKEMAIL_BRIDGE_S.get(), GenerationsItems.POKEMAIL_BRIDGE_T.get(), GenerationsItems.POKEMAIL_BRIDGE_V.get(),
						GenerationsItems.WATER_CANDY.get(), GenerationsItems.SHATTERED_ICE_KEY_1.get(), GenerationsItems.SHATTERED_ICE_KEY_2.get(),
						GenerationsItems.SHATTERED_ICE_KEY_3.get(), GenerationsItems.SHATTERED_ICE_KEY_4.get(), GenerationsItems.TM_11.get(),
						GenerationsItems.TM_22.get(), GenerationsItems.TM_50.get(), GenerationsItems.TM_77.get(), GenerationsItems.TM_110.get(),
						GenerationsItems.TM_123.get(), GenerationsItems.TM_142.get(), GenerationsItems.TM_145.get(), GenerationsItems.TM_154.get(),
						GenerationsItems.TM_188.get(), GenerationsItems.TM_196.get(), GenerationsItems.TM_208.get(), GenerationsItems.TM_209.get()
				)));

		output.accept(DREAM_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1.0F, 2.0F),
						CobblemonItems.POKE_BALL.asItem(), CobblemonItems.AWAKENING.asItem(), CobblemonItems.BIG_ROOT.asItem(),
						GenerationsItems.CELL_BATTERY.get(), GenerationsItems.CHALKY_STONE.get(), CobblemonItems.CHESTO_BERRY.asItem(),
						CobblemonItems.CLOVER_SWEET.asItem(), GenerationsItems.COURAGE_CANDY.get(), CobblemonItems.CRACKED_POT.asItem(),
						GenerationsItems.DRAGON_GEM.get(), CobblemonItems.DREAM_BALL.asItem(), CobblemonItems.DUSK_BALL.asItem(),
						CobblemonItems.EXPERIENCE_CANDY_L.asItem(), GenerationsItems.FAIRY_GEM.get(), GenerationsItems.FRESH_CREAM.get(),
						GenerationsItems.FRIED_FOOD.get(), CobblemonItems.IAPAPA_BERRY.asItem(), CobblemonItems.KEBIA_BERRY.asItem(),
						GenerationsItems.LARGE_LEEK.get(), GenerationsItems.LEEK.get(), GenerationsItems.LEMONADE.get(),
						GenerationsItems.LIGHT_BALL.get(), GenerationsItems.LOOKER_TICKET.get(), GenerationsItems.MARBLE.get(),
						CobblemonItems.MYSTIC_WATER.asItem(), GenerationsItems.NORMAL_GEM.get(), GenerationsItems.ODD_INCENSE.get(),
						GenerationsItems.ODD_KEYSTONE.get(), CobblemonItems.PARALYZE_HEAL.asItem(), CobblemonItems.PAYAPA_BERRY.asItem(),
						CobblemonItems.PERSIM_BERRY.asItem(), GenerationsItems.POISON_GEM.get(), GenerationsItems.POLISHED_MUD_BALL.get(),
						CobblemonItems.POWER_LENS.asItem(), GenerationsItems.PSYCHIC_GEM.get(), GenerationsItems.RAGE_CANDY_BAR.get(),
						CobblemonItems.RAZOR_CLAW.asItem(), CobblemonItems.RAZOR_FANG.asItem(), GenerationsItems.RED_CARD.get(),
						GenerationsItems.RED_NECTAR.get(), GenerationsItems.RED_SCARF.get(), GenerationsItems.RED_SHARD.get(),
						GenerationsItems.RELIC_BAND.get(), GenerationsItems.ICY_WING.get(), GenerationsItems.ROOM_SERVICE.get(),
						CobblemonItems.ROSELI_BERRY.asItem(), GenerationsItems.SCOPE_LENS.get(), CobblemonItems.SILK_SCARF.asItem(),
						CobblemonItems.SITRUS_BERRY.asItem(), GenerationsItems.SMART_CANDY_XL.get(), GenerationsItems.SNOWBALL.get(),
						GenerationsItems.SODA_POP.get(), GenerationsItems.SOUL_DEW.get(), GenerationsItems.STAR_PIECE.get(),
						GenerationsItems.STARDUST.get(), GenerationsItems.THROAT_SPRAY.get(), CobblemonItems.THUNDER_STONE.asItem(),
						CobblemonItems.TWISTED_SPOON.asItem(), CobblemonItems.UPGRADE.asItem(), CobblemonItems.WHIPPED_DREAM.asItem(),
						GenerationsItems.WIDE_LENS.get(), CobblemonItems.X_ACCURACY.asItem(), GenerationsItems.X_DEFENSE_6.get(),
						GenerationsItems.X_SPECIAL_ATTACK_2.get(), Items.PURPUR_BLOCK, Items.SLIME_BALL, Items.GHAST_TEAR,
						Items.CRIMSON_FUNGUS, Items.MUSIC_DISC_BLOCKS, GenerationsItems.MARK_CHARM.get(), GenerationsItems.DYNITE_ORE.get(),
						GenerationsItems.RUSTY_IRON_KEY_2.get(), GenerationsItems.SHATTERED_ICE_KEY_2.get(),
						GenerationsItems.CRUMBLED_ROCK_KEY_2.get(), GenerationsItems.FRAGMENTED_DRAGO_KEY_2.get(),
						GenerationsItems.DISCHARGED_ELEKI_KEY_2.get(), GenerationsItems.SHATTERED_RELIC_SONG_2.get(),
						GenerationsItems.LAVA_CRYSTAL.get(), CobblemonItems.RELAXED_MINT.asItem(), CobblemonItems.IMPISH_MINT.asItem(),
						CobblemonItems.GENTLE_MINT.asItem(), GenerationsItems.POKEMAIL_AIR.get(), GenerationsItems.POKEMAIL_BLOOM.get(),
						GenerationsItems.POKEMAIL_BRICK.get(), GenerationsItems.POKEMAIL_BRIDGE_D.get(), GenerationsItems.POKEMAIL_BRIDGE_M.get(),
						GenerationsItems.POKEMAIL_BRIDGE_S.get(), GenerationsItems.POKEMAIL_BRIDGE_T.get(), GenerationsItems.POKEMAIL_BRIDGE_V.get(),
						GenerationsItems.POKEMAIL_BUBBLE.get(), GenerationsItems.POKEMAIL_DREAM.get(), GenerationsItems.POKEMAIL_FAB.get(),
						GenerationsItems.POKEMAIL_FAVORED.get(), GenerationsItems.FIRE_CANDY.get(), GenerationsItems.ICE_CANDY.get(),
						GenerationsItems.PSYCHIC_CANDY.get(), GenerationsItems.TM_8.get(), GenerationsItems.TM_24.get(),
						GenerationsItems.TM_38.get(), GenerationsItems.TM_49.get(), GenerationsItems.TM_67.get(), GenerationsItems.TM_107.get(),
						GenerationsItems.TM_118.get(), GenerationsItems.TM_125.get(), GenerationsItems.TM_141.get(), GenerationsItems.TM_144.get(),
						GenerationsItems.TM_153.get(), GenerationsItems.TM_157.get(), GenerationsItems.TM_165.get(), GenerationsItems.TM_10.get(),
						GenerationsItems.TM_34.get(), GenerationsItems.TM_46.get(), GenerationsItems.TM_52.get(), GenerationsItems.TM_59.get(),
						GenerationsItems.TM_63.get(), GenerationsItems.TM_74.get(), GenerationsItems.TM_75.get(), GenerationsItems.TM_85.get(),
						GenerationsItems.TM_92.get(), GenerationsItems.TM_98.get(), GenerationsItems.TM_109.get(), GenerationsItems.TM_120.get(),
						GenerationsItems.TM_128.get(), GenerationsItems.TM_129.get(), GenerationsItems.TM_138.get(), GenerationsItems.TM_161.get()
				).add(key("lugia_disc")).add(key("lavender_town_disc")).add(key("mt_pyre_disc"))));

		output.accept(DUSK_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), CobblemonItems.BELUE_BERRY.asItem(), CobblemonItems.BIG_ROOT.asItem(),
						CobblemonItems.BLACK_GLASSES.asItem(), CobblemonItems.BLACK_SLUDGE.asItem(),
						GenerationsItems.BRITTLE_BONES.get(), CobblemonItems.CLEANSE_TAG.asItem(), CobblemonItems.COLBUR_BERRY.asItem(),
						GenerationsItems.DARK_GEM.get(), CobblemonItems.DUSK_BALL.asItem(), CobblemonItems.DUSK_STONE.asItem(),
						GenerationsItems.EVIOLITE.get(), GenerationsItems.EXPERT_BELT.get(), GenerationsItems.GHOST_GEM.get(),
						GenerationsItems.GRISEOUS_ORB.get(), GenerationsItems.HEALTH_FEATHER.get(), GenerationsItems.HEAT_ROCK.get(),
						CobblemonItems.KASIB_BERRY.asItem(), CobblemonItems.MAX_ETHER.asItem(), GenerationsItems.MIGHTY_CANDY_L.get(),
						GenerationsItems.MIXED_MUSHROOMS.get(), CobblemonItems.MOON_STONE.asItem(), CobblemonItems.MYSTIC_WATER.asItem(),
						CobblemonItems.NEVER_MELT_ICE.asItem(), CobblemonItems.NOMEL_BERRY.asItem(), GenerationsItems.NUGGET.get(),
						CobblemonItems.OCCA_BERRY.asItem(), GenerationsItems.ODD_INCENSE.get(), GenerationsItems.PACK_OF_POTATOES.get(),
						GenerationsItems.PEARL.get(), CobblemonItems.PECHA_BERRY.asItem(), CobblemonItems.POISON_BARB.asItem(),
						CobblemonItems.POWER_LENS.asItem(), CobblemonItems.RABUTA_BERRY.asItem(), CobblemonItems.RAZOR_FANG.asItem(),
						CobblemonItems.REAPER_CLOTH.asItem(), GenerationsItems.RELIC_STATUE.get(), GenerationsItems.RING_TARGET.get(),
						CobblemonItems.SAFETY_GOGGLES.asItem(), GenerationsItems.SCOPE_LENS.get(), CobblemonItems.SHINY_STONE.asItem(),
						GenerationsItems.SMART_CANDY_XL.get(), GenerationsItems.SNOWBALL.get(), CobblemonItems.SPELL_TAG.asItem(),
						CobblemonItems.STAR_SWEET.asItem(), CobblemonItems.SUN_STONE.asItem(), GenerationsItems.THICK_CLUB.get(),
						CobblemonItems.TOXIC_ORB.asItem(), CobblemonItems.TWISTED_SPOON.asItem(), GenerationsItems.WIDE_LENS.get(),
						CobblemonItems.WISE_GLASSES.asItem(), GenerationsItems.WISHING_PIECE.get(), CobblemonItems.X_ACCURACY.asItem(),
						GenerationsItems.X_ATTACK_3.get(), GenerationsItems.X_SPECIAL_ATTACK_2.get(), GenerationsItems.X_SPECIAL_DEFENSE_6.get(),
						GenerationsItems.SINISTER_WING.get(), GenerationsItems.X_SPEED_2.get(),
						Items.COBWEB, Items.END_STONE, Items.BONE, Items.SPIDER_EYE, Items.PHANTOM_MEMBRANE, Items.MUSIC_DISC_FAR,
						GenerationsItems.DARK_SOUL.get(), GenerationsItems.DRAGON_SOUL.get(), CobblemonItems.LAX_MINT.asItem(),
						CobblemonItems.TIMID_MINT.asItem(), CobblemonItems.HASTY_MINT.asItem(), GenerationsItems.POISON_MEMORY_DRIVE.get(),
						GenerationsItems.GHOST_MEMORY_DRIVE.get(), GenerationsItems.TOXIC_PLATE.get(), GenerationsItems.SPOOKY_PLATE.get(),
						GenerationsItems.GHOST_CANDY.get(), GenerationsItems.DARK_CANDY.get(), GenerationsItems.TM_17.get(),
						GenerationsItems.TM_29.get(), GenerationsItems.TM_42.get(), GenerationsItems.TM_61.get(), GenerationsItems.TM_114.get(),
						GenerationsItems.TM_151.get(), GenerationsItems.TM_3.get(), GenerationsItems.TM_18.get(), GenerationsItems.TM_30.get(),
						GenerationsItems.TM_43.get(), GenerationsItems.TM_62.get(), GenerationsItems.TM_87.get(), GenerationsItems.TM_94.get(),
						GenerationsItems.TM_108.get(), GenerationsItems.TM_140.get(),
						GenerationsItems.TM_174.get(), GenerationsItems.TM_187.get(), GenerationsItems.TM_212.get(),
						GenerationsItems.TM_177.get(), GenerationsItems.TM_198.get(), GenerationsItems.TM_224.get()
				).add(key("iris_disc")).add(key("nessa_disc")).add(key("team_rocket_disc")).add(key("sada_and_turo_disc")).add(key("south_province_disc")).add(key("rival_disc")).add(key("lusamine_disc")).add(key("xy_legendary_disc")).add(key("kanto_disc")).add(key("ultra_necrozma_disc"))));


		output.accept(FAST_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ABILITY_URGE.get(), CobblemonItems.AGUAV_BERRY.asItem(),
						CobblemonItems.BELUE_BERRY.asItem(), CobblemonItems.BLACK_BELT.asItem(), GenerationsItems.BLUNDER_POLICY.get(),
						CobblemonItems.CARBOS.asItem(), CobblemonItems.CHESTO_BERRY.asItem(), CobblemonItems.CHOICE_SCARF.asItem(),
						GenerationsItems.EJECT_BUTTON.get(), GenerationsItems.ELECTRIC_SEED.get(), GenerationsItems.ESCAPE_ROPE.get(),
						CobblemonItems.EXPERIENCE_CANDY_XS.asItem(), CobblemonItems.FAST_BALL.asItem(), CobblemonItems.GROWTH_MULCH.asItem(),
						GenerationsItems.HEALTH_CANDY.get(), CobblemonItems.IAPAPA_BERRY.asItem(), GenerationsItems.ICE_GEM.get(),
						GenerationsItems.INSTANT_NOODLES.get(), CobblemonItems.KELPSY_BERRY.asItem(),
						CobblemonItems.LIECHI_BERRY.asItem(), GenerationsItems.MACHO_BRACE.get(), CobblemonItems.MAGMARIZER.asItem(),
						CobblemonItems.MAGNET.asItem(), CobblemonItems.MIRACLE_SEED.asItem(), GenerationsItems.MOOMOO_CHEESE.get(),
						CobblemonItems.MOON_BALL.asItem(), CobblemonItems.MOON_STONE.asItem(), GenerationsItems.BELLIGERENT_WING.get(),
						CobblemonItems.NEST_BALL.asItem(), CobblemonItems.NET_BALL.asItem(), GenerationsItems.PACK_OF_POTATOES.get(),
						CobblemonItems.PAYAPA_BERRY.asItem(), GenerationsItems.PINK_NECTAR.get(), GenerationsItems.POLISHED_MUD_BALL.get(),
						CobblemonItems.POWER_ANKLET.asItem(), CobblemonItems.QUALOT_BERRY.asItem(), CobblemonItems.QUICK_BALL.asItem(),
						GenerationsItems.QUICK_CANDY_L.get(), CobblemonItems.QUICK_POWDER.asItem(), GenerationsItems.RELIC_CROWN.get(),
						GenerationsItems.REPEL.get(), GenerationsItems.ROLLER_SKATES.get(), CobblemonItems.SALAC_BERRY.asItem(),
						GenerationsItems.SNOWBALL.get(), CobblemonItems.TAMATO_BERRY.asItem(), GenerationsItems.THROAT_SPRAY.get(),
						GenerationsItems.TOUGH_CANDY_L.get(), CobblemonItems.ULTRA_BALL.asItem(), CobblemonItems.WATMEL_BERRY.asItem(),
						GenerationsItems.ELECTRIC_CANDY.get(), GenerationsItems.X_ACCURACY_6.get(), CobblemonItems.X_ATTACK.asItem(),
						GenerationsItems.X_DEFENSE_3.get(), GenerationsItems.FIERY_WING.get(), Items.GLOWSTONE_DUST, Items.NETHER_WART,
						Items.PRISMARINE_SHARD, Items.WARPED_FUNGUS, Items.MUSIC_DISC_FAR, GenerationsItems.RUSTY_IRON_KEY_3.get(),
						GenerationsItems.SHATTERED_ICE_KEY_3.get(), GenerationsItems.CRUMBLED_ROCK_KEY_3.get(),
						GenerationsItems.FRAGMENTED_DRAGO_KEY_3.get(), GenerationsItems.DISCHARGED_ELEKI_KEY_3.get(),
						GenerationsItems.SHATTERED_RELIC_SONG_3.get(), CobblemonItems.EXPERIENCE_CANDY_S.asItem(),
						GenerationsItems.ICE_MEMORY_DRIVE.get(), GenerationsItems.ICICLE_PLATE.get(), GenerationsItems.POKEMAIL_AIR.get(),
						GenerationsItems.POKEMAIL_BLOOM.get(), GenerationsItems.POKEMAIL_BRICK.get(), GenerationsItems.POKEMAIL_BRIDGE_D.get(),
						GenerationsItems.POKEMAIL_BRIDGE_M.get(), GenerationsItems.POKEMAIL_BRIDGE_S.get(), GenerationsItems.POKEMAIL_BRIDGE_T.get(),
						GenerationsItems.POKEMAIL_BRIDGE_V.get(), GenerationsItems.POKEMAIL_BUBBLE.get(), GenerationsItems.POKEMAIL_DREAM.get(),
						GenerationsItems.POKEMAIL_FAB.get(), GenerationsItems.POKEMAIL_FAVORED.get(), GenerationsItems.POKEMAIL_FLAME.get(),
						GenerationsItems.POKEMAIL_GLITTER.get(), GenerationsItems.POKEMAIL_GRASS.get(), GenerationsItems.POKEMAIL_GREET.get(),
						GenerationsItems.POKEMAIL_HARBOR.get(), GenerationsItems.POKEMAIL_HEART.get(), GenerationsItems.POKEMAIL_INQUIRY.get(),
						GenerationsItems.POKEMAIL_LIKE.get(), GenerationsItems.POKEMAIL_MECH.get(), GenerationsItems.POKEMAIL_MOSAIC.get(),
						GenerationsItems.TM_9.get(), GenerationsItems.TM_23.get(), GenerationsItems.TM_48.get(), GenerationsItems.TM_68.get(),
						GenerationsItems.TM_72.get(), GenerationsItems.TM_82.get(), GenerationsItems.TM_96.get(), GenerationsItems.TM_126.get(),
						GenerationsItems.TM_136.get(), GenerationsItems.TM_147.get(), GenerationsItems.TM_166.get()
				)));

		output.accept(FRIEND_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ABILITY_URGE.get(), GenerationsItems.ADRENALINE_ORB.get(),
						CobblemonItems.APICOT_BERRY.asItem(), GenerationsItems.BACHS_FOOD_TIN.get(), CobblemonItems.BERRY_SWEET.asItem(),
						GenerationsItems.BIG_MALASADA.get(), GenerationsItems.BOBS_FOOD_TIN.get(), CobblemonItems.BRIGHT_POWDER.asItem(),
						CobblemonItems.CALCIUM.asItem(), CobblemonItems.CHOICE_SPECS.asItem(), CobblemonItems.CHOPLE_BERRY.asItem(),
						GenerationsItems.EJECT_BUTTON.get(), GenerationsItems.EJECT_PACK.get(), CobblemonItems.ELECTIRIZER.asItem(),
						GenerationsItems.ENERGY_POWDER.get(), CobblemonItems.ENIGMA_BERRY.asItem(), GenerationsItems.EVIOLITE.get(),
						CobblemonItems.EXPERIENCE_CANDY_S.asItem(), CobblemonItems.FIRE_STONE.asItem(), CobblemonItems.FLOWER_SWEET.asItem(),
						GenerationsItems.FOCUS_SASH.get(), GenerationsItems.FRESH_CREAM.get(), GenerationsItems.FRIED_FOOD.get(),
						CobblemonItems.FRIEND_BALL.asItem(), GenerationsItems.FULL_INCENSE.get(), CobblemonItems.GALARICA_CUFF.asItem(),
						GenerationsItems.GALARICA_TWIG.get(), GenerationsItems.GREEN_SCARF.get(), CobblemonItems.GREPA_BERRY.asItem(),
						CobblemonItems.GROWTH_MULCH.asItem(), CobblemonItems.HEAL_POWDER.asItem(), GenerationsItems.HEALTH_CANDY_XL.get(),
						GenerationsItems.HEART_SCALE.get(), GenerationsItems.ROTOM_CATALOG.get(), GenerationsItems.LUSTROUS_GLOBE.get(),
						GenerationsItems.GRISEOUS_CORE.get(), GenerationsItems.ADAMANT_CRYSTAL.get(),
						CobblemonItems.HONDEW_BERRY.asItem(), GenerationsItems.ITEM_DROP.get(), CobblemonItems.KINGS_ROCK.asItem(),
						CobblemonItems.LANSAT_BERRY.asItem(), CobblemonItems.LEFTOVERS.asItem(), GenerationsItems.LIGHT_BALL.get(),
						CobblemonItems.LUXURY_BALL.asItem(), CobblemonItems.MAX_ELIXIR.asItem(), GenerationsItems.MOOMOO_CHEESE.get(),
						CobblemonItems.OCCA_BERRY.asItem(), CobblemonItems.OVAL_STONE.asItem(), GenerationsItems.PACKAGED_CURRY.get(),
						GenerationsItems.PASTA.get(), GenerationsItems.PINK_NECTAR.get(), GenerationsItems.POKE_TOY.get(),
						CobblemonItems.POMEG_BERRY.asItem(), CobblemonItems.POWER_WEIGHT.asItem(), GenerationsItems.PRECOOKED_BURGER.get(),
						GenerationsItems.PROTECTIVE_PADS.get(), CobblemonItems.QUALOT_BERRY.asItem(), GenerationsItems.QUICK_CANDY_L.get(),
						CobblemonItems.QUICK_POWDER.asItem(), CobblemonItems.RAWST_BERRY.asItem(), CobblemonItems.RAZOR_CLAW.asItem(),
						GenerationsItems.REPEL.get(), CobblemonItems.REVIVAL_HERB.asItem(), GenerationsItems.RING_TARGET.get(),
						CobblemonItems.SACHET.asItem(), GenerationsItems.SAUSAGES.get(), GenerationsItems.SHED_SHELL.get(),
						CobblemonItems.SILVER_POWDER.asItem(), CobblemonItems.STAR_SWEET.asItem(), GenerationsItems.STATIC_WING.get(),
						CobblemonItems.TANGA_BERRY.asItem(), GenerationsItems.THROAT_SPRAY.get(), GenerationsItems.TIN_OF_BEANS.get(),
						CobblemonItems.TWISTED_SPOON.asItem(), CobblemonItems.WACAN_BERRY.asItem(), GenerationsItems.WEAKNESS_POLICY.get(),
						CobblemonItems.WHIPPED_DREAM.asItem(), CobblemonItems.X_ATTACK.asItem(), GenerationsItems.SHATTERED_RELIC_SONG_1.get(),
						GenerationsItems.SHATTERED_ICE_KEY_2.get(), GenerationsItems.SHATTERED_ICE_KEY_3.get(), GenerationsItems.SHATTERED_ICE_KEY_4.get(),
						Items.PORKCHOP, Items.MILK_BUCKET, Items.RABBIT_FOOT, Items.MUSIC_DISC_STRAD, GenerationsItems.BUG_CANDY.get(),
						GenerationsItems.ROCK_CANDY.get(), GenerationsItems.PURPLE_JUICE.get(), GenerationsItems.BLUE_JUICE.get(),
						GenerationsItems.GREEN_JUICE.get(), GenerationsItems.PINK_JUICE.get(), GenerationsItems.BASIC_SWEET_POKE_PUFF.get(),
						GenerationsItems.BASIC_MINT_POKE_PUFF.get(), GenerationsItems.BASIC_CITRUS_POKE_PUFF.get(), GenerationsItems.BASIC_MOCHA_POKE_PUFF.get(),
						GenerationsItems.BASIC_SPICE_POKE_PUFF.get(), GenerationsItems.SUN_FLUTE.get(), GenerationsItems.MOON_FLUTE.get(),
						GenerationsItems.N_SOLARIZER.get(), GenerationsItems.N_LUNARIZER.get(), GenerationsItems.MELODY_FLUTE.get(),
						GenerationsItems.TM_15.get(), GenerationsItems.TM_21.get(), GenerationsItems.TM_60.get(), GenerationsItems.TM_95.get(),
						GenerationsItems.TM_105.get(), GenerationsItems.TM_131.get(), GenerationsItems.TM_162.get(), GenerationsItems.TM_36.get(),
						GenerationsItems.TM_51.get(), GenerationsItems.TM_76.get(), GenerationsItems.TM_86.get(), GenerationsItems.TM_101.get(),
						GenerationsItems.TM_116.get(), GenerationsItems.TM_105.get(), GenerationsItems.DYNITE_ORE.get(), GenerationsItems.TM_201.get(), GenerationsItems.TM_227.get()
				).add(key("hi_tech_earbuds"))));

		output.accept(GIGATON_BALL, LootTable.lootTable()
				.withPool(createPool(ConstantValue.exactly(1.0F),
						CobblemonItems.POKE_BALL.asItem(), CobblemonItems.HEAVY_BALL.asItem(),
						CobblemonItems.HEAVY_DUTY_BOOTS.asItem(), CobblemonItems.HYPER_POTION.asItem(),
						CobblemonItems.IRON.asItem(), GenerationsItems.IRON_BALL.get(),
						GenerationsItems.DRAGON_SOUL.get(), GenerationsItems.METEORITE_SHARD.get(),
						GenerationsItems.LEGEND_PLATE.get(), GenerationsItems.ROCK_GEM.get(),
						CobblemonItems.ROCKY_HELMET.asItem(), CobblemonItems.SAFETY_GOGGLES.asItem(),
						GenerationsItems.SPRINK_LOTAD.get(), GenerationsItems.STEEL_GEM.get(),
						GenerationsItems.STICKY_BARB.get(), GenerationsItems.STRANGE_SOUVENIR.get(),
						GenerationsItems.STRETCHY_SPRING.get(), CobblemonItems.TIMER_BALL.asItem(),
						GenerationsItems.TOUGH_CANDY_XL.get(), CobblemonItems.UPGRADE.asItem(),
						GenerationsItems.STEEL_CANDY.get(), CobblemonItems.EXPERIENCE_CANDY_XL.asItem(),
						GenerationsItems.TIN_OF_BEANS.get(), GenerationsItems.SMOKED_POKE_TAIL.get(),
						GenerationsItems.LARGE_LEEK.get(), GenerationsItems.PACK_OF_POTATOES.get(),
						GenerationsItems.BOTTLE_CAP.get(), GenerationsItems.EJECT_BUTTON.get(),
						CobblemonItems.ASSAULT_VEST.asItem(), CobblemonItems.KINGS_ROCK.asItem(),
						CobblemonItems.METAL_POWDER.asItem(), GenerationsItems.METRONOME.get(),
						GenerationsItems.SOOTHE_BELL.get(), CobblemonItems.BABIRI_BERRY.asItem(),
						GenerationsItems.IRON_PLATE.get(), CobblemonItems.METAL_COAT.asItem(),
						GenerationsItems.STEEL_MEMORY_DRIVE.get(), CobblemonItems.ENIGMA_BERRY.asItem(),
						CobblemonItems.SITRUS_BERRY.asItem(), CobblemonItems.ORAN_BERRY.asItem(),
						CobblemonItems.FIGY_BERRY.asItem(), GenerationsItems.ABSORB_BULB.get(),
						GenerationsItems.DAMP_ROCK.get(), GenerationsItems.CRUMBLED_ROCK_KEY_1.get(),
						GenerationsItems.CRUMBLED_ROCK_KEY_2.get(), GenerationsItems.CRUMBLED_ROCK_KEY_3.get(),
						GenerationsItems.CRUMBLED_ROCK_KEY_4.get(), GenerationsItems.LUSTROUS_GLOBE.get(),
						GenerationsItems.GRISEOUS_CORE.get(), GenerationsItems.ADAMANT_CRYSTAL.get(),
						GenerationsItems.EVIOLITE.get(), CobblemonItems.HARD_STONE.asItem(),
						CobblemonItems.LUCKY_EGG.asItem(), CobblemonItems.POWER_ANKLET.asItem(),
						CobblemonItems.POWER_BAND.asItem(), CobblemonItems.POWER_BELT.asItem(),
						CobblemonItems.POWER_BRACER.asItem(), CobblemonItems.POWER_LENS.asItem(),
						CobblemonItems.POWER_WEIGHT.asItem(), CobblemonItems.ROCKY_HELMET.asItem(),
						GenerationsItems.ROOM_SERVICE.get(), CobblemonItems.SOFT_SAND.asItem(),
						GenerationsItems.THICK_CLUB.get(), GenerationsItems.WIDE_LENS.get(), GenerationsItems.DYNITE_ORE.get(),
						GenerationsItems.Z_INGOT.get(), GenerationsItems.TM_31.get(), GenerationsItems.TM_53.get(),
						GenerationsItems.TM_93.get(), GenerationsItems.TM_43.get(), GenerationsItems.TM_99.get(),
						GenerationsItems.TM_104.get(), GenerationsItems.TM_121.get(), GenerationsItems.TM_170.get(),
						GenerationsItems.TM_180.get(), GenerationsItems.TM_223.get(), GenerationsItems.TM_225.get(),
						GenerationsItems.TM_179.get(), GenerationsItems.TM_220.get(), GenerationsItems.TM_184.get(),
						GenerationsItems.TM_192.get(), GenerationsItems.TM_213.get(), GenerationsItems.TM_229.get())
						.add(key("cascarrafa_city_disc")).add(key("slumbering_weald_disc"))
						.add(key("pokemon_center_disc")).add(key("azalea_town_disc"))
						.add(key("lillie_disc")).add(key("route_228_disc"))
						.add(key("jubilife_village_disc")).add(key("goldenrod_city_disc"))
						.add(key("eterna_city_disc")).add(key("vermilion_city_disc"))));

		output.accept(GREAT_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.MIRROR.get(),
						GenerationsItems.BACHS_FOOD_TIN.get(), CobblemonItems.BERRY_JUICE.asItem(),
						GenerationsItems.BIG_MUSHROOM.get(), GenerationsItems.BIG_NUGGET.get(),
						CobblemonItems.BLACK_GLASSES.asItem(), GenerationsItems.BLUE_FLUTE.get(),
						GenerationsItems.BREAD.get(), CobblemonItems.BRIGHT_POWDER.asItem(),
						GenerationsItems.BUG_GEM.get(), GenerationsItems.CHALKY_STONE.get(),
						CobblemonItems.CHERI_BERRY.asItem(), CobblemonItems.CHILAN_BERRY.asItem(),
						CobblemonItems.CHOICE_SCARF.asItem(), CobblemonItems.CLEANSE_TAG.asItem(),
						GenerationsItems.SINISTER_WING.get(), GenerationsItems.COCONUT_MILK.get(),
						GenerationsItems.DAMP_ROCK.get(), CobblemonItems.DEEP_SEA_SCALE.asItem(),
						GenerationsItems.DIRE_HIT_2.get(), CobblemonItems.DIVE_BALL.asItem(),
						CobblemonItems.DRAGON_SCALE.asItem(), CobblemonItems.DURIN_BERRY.asItem(),
						CobblemonItems.ETHER.asItem(), CobblemonItems.EXPERIENCE_CANDY_S.asItem(),
						GenerationsItems.FANCY_APPLE.get(), GenerationsItems.FIRE_GEM.get(),
						GenerationsItems.FRESH_WATER.get(), CobblemonItems.GANLON_BERRY.asItem(),
						GenerationsItems.GRASS_GEM.get(), CobblemonItems.GREAT_BALL.asItem(),
						CobblemonItems.HEAL_BALL.asItem(), CobblemonItems.HYPER_POTION.asItem(),
						GenerationsItems.ITEM_URGE.get(), CobblemonItems.LEAF_STONE.asItem(),
						GenerationsItems.LUMIOSE_GALETTE.get(), GenerationsItems.MARBLE.get(),
						CobblemonItems.MAX_ETHER.asItem(), CobblemonItems.MENTAL_HERB.asItem(),
						CobblemonItems.MICLE_BERRY.asItem(), GenerationsItems.MIGHTY_CANDY.get(),
						GenerationsItems.MIXED_MUSHROOMS.get(), GenerationsItems.MOOMOO_MILK.get(),
						CobblemonItems.NEST_BALL.asItem(), GenerationsItems.NUGGET.get(),
						CobblemonItems.ORAN_BERRY.asItem(), GenerationsItems.PACK_OF_POTATOES.get(),
						CobblemonItems.PARALYZE_HEAL.asItem(), CobblemonItems.PASSHO_BERRY.asItem(),
						GenerationsItems.PEARL.get(), CobblemonItems.PETAYA_BERRY.asItem(),
						CobblemonItems.POWER_LENS.asItem(), CobblemonItems.PRISM_SCALE.asItem(),
						GenerationsItems.PUNGENT_ROOT.get(), CobblemonItems.QUICK_BALL.asItem(),
						GenerationsItems.RAGE_CANDY_BAR.get(), GenerationsItems.RED_NECTAR.get(),
						CobblemonItems.REVIVAL_HERB.asItem(), CobblemonItems.RINDO_BERRY.asItem(),
						CobblemonItems.SAFARI_BALL.asItem(), CobblemonItems.SILK_SCARF.asItem(),
						GenerationsItems.SMART_CANDY_L.get(), CobblemonItems.STRAWBERRY_SWEET.asItem(),
						GenerationsItems.TIN_OF_BEANS.get(), CobblemonItems.ULTRA_BALL.asItem(),
						GenerationsItems.X_SPECIAL_ATTACK_2.get(), CobblemonItems.BLACK_APRICORN.asItem(),
						CobblemonItems.BLUE_APRICORN.asItem(), CobblemonItems.GREEN_APRICORN.asItem(),
						CobblemonItems.PINK_APRICORN.asItem(), CobblemonItems.RED_APRICORN.asItem(),
						CobblemonItems.WHITE_APRICORN.asItem(), CobblemonItems.YELLOW_APRICORN.asItem(),
						Items.END_STONE, Items.GOLD_INGOT, Items.GHAST_TEAR, Items.BLAZE_POWDER,
						Items.MUSIC_DISC_STRAD, GenerationsItems.CRYSTAL.get(), GenerationsItems.Z_INGOT.get(),
						GenerationsItems.SAPPHIRE.get(), GenerationsItems.SILICON.get(), GenerationsItems.MEW_FOSSIL.get(),
						GenerationsItems.RUSTY_IRON_KEY_1.get(), GenerationsItems.SHATTERED_ICE_KEY_1.get(),
						GenerationsItems.CRUMBLED_ROCK_KEY_1.get(), GenerationsItems.FRAGMENTED_DRAGO_KEY_1.get(),
						GenerationsItems.DISCHARGED_ELEKI_KEY_1.get(), GenerationsItems.SHATTERED_RELIC_SONG_1.get(),
						CobblemonItems.EXPERIENCE_CANDY_M.asItem(), GenerationsItems.TM_5.get(),
						GenerationsItems.TM_28.get(), GenerationsItems.TM_35.get(), GenerationsItems.TM_55.get(),
						GenerationsItems.TM_84.get(), GenerationsItems.TM_90.get(), GenerationsItems.TM_106.get(),
						GenerationsItems.TM_133.get(), GenerationsItems.TM_149.get(), GenerationsItems.TM_44.get(),
						GenerationsItems.TM_78.get(), GenerationsItems.TM_100.get(), GenerationsItems.TM_115.get(),
						GenerationsItems.TM_156.get(), GenerationsItems.TM_169.get(), GenerationsItems.TM_180.get(),
						GenerationsItems.TM_223.get(), GenerationsItems.TM_225.get())
						.add(key("cascarrafa_city_disc"))
						.add(key("slumbering_weald_disc"))
						.add(key("pokemon_center_disc"))
						.add(key("azalea_town_disc"))
						.add(key("lillie_disc"))
						.add(key("route_228_disc"))
						.add(key("jubilife_village_disc"))
						.add(key("goldenrod_city_disc"))
						.add(key("eterna_city_disc"))
						.add(key("vermilion_city_disc"))));

		output.accept(HEAL_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), CobblemonItems.AGUAV_BERRY.asItem(),
						CobblemonItems.ANTIDOTE.asItem(), CobblemonItems.ASPEAR_BERRY.asItem(),
						CobblemonItems.AWAKENING.asItem(), CobblemonItems.BERRY_JUICE.asItem(),
						CobblemonItems.BIG_ROOT.asItem(), CobblemonItems.BLACK_SLUDGE.asItem(),
						GenerationsItems.BLUE_SCARF.get(), GenerationsItems.BRITTLE_BONES.get(),
						CobblemonItems.BURN_HEAL.asItem(), GenerationsItems.CASTELIACONE.get(),
						CobblemonItems.CHERI_BERRY.asItem(), CobblemonItems.CHESTO_BERRY.asItem(),
						CobblemonItems.CLEANSE_TAG.asItem(), GenerationsItems.COCONUT_MILK.get(),
						GenerationsItems.COURAGE_CANDY.get(), CobblemonItems.CUSTAP_BERRY.asItem(),
						CobblemonItems.DREAM_BALL.asItem(), GenerationsItems.EJECT_PACK.get(),
						CobblemonItems.ELIXIR.asItem(), GenerationsItems.ENERGY_POWDER.get(),
						CobblemonItems.ENIGMA_BERRY.asItem(), CobblemonItems.FIGY_BERRY.asItem(),
						GenerationsItems.FRESH_WATER.get(), CobblemonItems.FULL_HEAL.asItem(),
						CobblemonItems.FULL_RESTORE.asItem(), CobblemonItems.GREAT_BALL.asItem(),
						CobblemonItems.GUARD_SPEC.asItem(), CobblemonItems.HEAL_BALL.asItem(),
						CobblemonItems.HEAL_POWDER.asItem(), CobblemonItems.HP_UP.asItem(),
						CobblemonItems.HYPER_POTION.asItem(), CobblemonItems.ICE_HEAL.asItem(),
						CobblemonItems.LANSAT_BERRY.asItem(), GenerationsItems.LEMONADE.get(),
						CobblemonItems.LEPPA_BERRY.asItem(), CobblemonItems.LUM_BERRY.asItem(),
						CobblemonItems.MAGO_BERRY.asItem(), CobblemonItems.MAX_POTION.asItem(),
						CobblemonItems.MAX_REVIVE.asItem(), GenerationsItems.MOOMOO_MILK.get(),
						GenerationsItems.OLD_GATEAU.get(), CobblemonItems.PARALYZE_HEAL.asItem(),
						CobblemonItems.PINAP_BERRY.asItem(), GenerationsItems.PURPLE_NECTAR.get(),
						GenerationsItems.RAGE_CANDY_BAR.get(), CobblemonItems.REVIVE.asItem(),
						CobblemonItems.SALAC_BERRY.asItem(), GenerationsItems.SHALOUR_SABLE.get(),
						GenerationsItems.SHED_SHELL.get(), GenerationsItems.SODA_POP.get(),
						CobblemonItems.SUPER_POTION.asItem(), CobblemonItems.TAMATO_BERRY.asItem(),
						GenerationsItems.POISON_CANDY.get(), GenerationsItems.FAIRY_CANDY.get(),
						GenerationsItems.X_SPECIAL_DEFENSE_2.get(), GenerationsItems.X_SPEED_2.get(),
						Items.MILK_BUCKET, Items.MUSIC_DISC_MELLOHI, Items.TOTEM_OF_UNDYING,
						GenerationsItems.HOOPA_RING.get(), GenerationsItems.GRACIDEA.get(),
						CobblemonItems.EXPERIENCE_CANDY_S.asItem(), GenerationsItems.TM_13.get(),
						GenerationsItems.TM_26.get(), GenerationsItems.TM_45.get(), GenerationsItems.TM_83.get(),
						GenerationsItems.TM_91.get(), GenerationsItems.TM_102.get(), GenerationsItems.TM_148.get(),
						GenerationsItems.TM_2.get(), GenerationsItems.TM_19.get(), GenerationsItems.TM_37.get(),
						GenerationsItems.TM_79.get(), GenerationsItems.TM_127.get(), GenerationsItems.TM_139.get(),
						GenerationsItems.TM_201.get(), GenerationsItems.TM_227.get())
						.add(key("cascarrafa_city_disc"))
						.add(key("slumbering_weald_disc"))
						.add(key("pokemon_center_disc"))
						.add(key("azalea_town_disc"))
						.add(key("lillie_disc"))
						.add(key("route_228_disc"))
						.add(key("jubilife_village_disc"))
						.add(key("goldenrod_city_disc"))
						.add(key("eterna_city_disc"))
						.add(key("vermilion_city_disc"))));

		output.accept(HEAVY_BALL, LootTable.lootTable()
				.withPool(createPool(ConstantValue.exactly(1.0F),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ADAMANT_ORB.get(),
						GenerationsItems.AIR_BALLOON.get(), CobblemonItems.BABIRI_BERRY.asItem(),
						GenerationsItems.BIG_PEARL.get(), GenerationsItems.BINDING_BAND.get(),
						CobblemonItems.CHESTO_BERRY.asItem(), CobblemonItems.CHILAN_BERRY.asItem(),
						CobblemonItems.CHOICE_BAND.asItem(), CobblemonItems.COBA_BERRY.asItem(),
						GenerationsItems.COURAGE_CANDY.get(), GenerationsItems.COURAGE_CANDY_XL.get(),
						GenerationsItems.DIRE_HIT_3.get(), CobblemonItems.DRAGON_FANG.asItem(),
						CobblemonItems.DUBIOUS_DISC.asItem(), GenerationsItems.FIGHTING_GEM.get(),
						CobblemonItems.FIGY_BERRY.asItem(), GenerationsItems.FLOAT_STONE.get(),
						GenerationsItems.FULL_INCENSE.get(), CobblemonItems.GANLON_BERRY.asItem(),
						GenerationsItems.GREEN_SHARD.get(), GenerationsItems.GROUND_GEM.get(),
						CobblemonItems.HABAN_BERRY.asItem(), CobblemonItems.HARD_STONE.asItem(),
						GenerationsItems.HEALTH_CANDY_L.get(), CobblemonItems.HEAVY_BALL.asItem(),
						CobblemonItems.HEAVY_DUTY_BOOTS.asItem(), CobblemonItems.HYPER_POTION.asItem(),
						CobblemonItems.IRON.asItem(), GenerationsItems.IRON_BALL.get(),
						GenerationsItems.ITEM_DROP.get(), CobblemonItems.KEE_BERRY.asItem(),
						GenerationsItems.LAGGING_TAIL.get(), GenerationsItems.LAVA_COOKIE.get(),
						CobblemonItems.LIFE_ORB.asItem(), GenerationsItems.MACHO_BRACE.get(),
						CobblemonItems.MAGMARIZER.asItem(), CobblemonItems.MARANGA_BERRY.asItem(),
						CobblemonItems.MAX_ETHER.asItem(), CobblemonItems.METAL_COAT.asItem(),
						CobblemonItems.PECHA_BERRY.asItem(), GenerationsItems.POKE_TOY.get(),
						CobblemonItems.POWER_BAND.asItem(), GenerationsItems.PROTECTIVE_PADS.get(),
						GenerationsItems.QUICK_CANDY.get(), GenerationsItems.RED_SHARD.get(),
						GenerationsItems.RELIC_COPPER.get(), CobblemonItems.REPEAT_BALL.asItem(),
						GenerationsItems.ICY_WING.get(), GenerationsItems.ROCK_GEM.get(),
						CobblemonItems.ROCKY_HELMET.asItem(), CobblemonItems.SAFETY_GOGGLES.asItem(),
						GenerationsItems.SPRINK_LOTAD.get(), GenerationsItems.STEEL_GEM.get(),
						GenerationsItems.STICKY_BARB.get(), GenerationsItems.STRANGE_SOUVENIR.get(),
						GenerationsItems.STRETCHY_SPRING.get(), CobblemonItems.TIMER_BALL.asItem(),
						GenerationsItems.TOUGH_CANDY_XL.get(), CobblemonItems.UPGRADE.asItem(),
						GenerationsItems.X_ATTACK_2.get(), GenerationsItems.X_DEFENSE_6.get(),
						GenerationsItems.X_SPECIAL_DEFENSE_6.get(), Items.IRON_INGOT, Items.GUNPOWDER,
						Items.QUARTZ, Items.MUSIC_DISC_MELLOHI, Items.NETHERITE_SCRAP,
						GenerationsItems.MEWTWO_ARMOR.get(), CobblemonItems.EXPERIENCE_CANDY_L.asItem(),
						GenerationsItems.ROCK_MEMORY_DRIVE.get(), GenerationsItems.STEEL_MEMORY_DRIVE.get(),
						GenerationsItems.STONE_PLATE.get(), GenerationsItems.IRON_PLATE.get(),
						GenerationsItems.FRAGMENTED_DRAGO_KEY_1.get(), GenerationsItems.FRAGMENTED_DRAGO_KEY_2.get(),
						GenerationsItems.FRAGMENTED_DRAGO_KEY_3.get(), GenerationsItems.FRAGMENTED_DRAGO_KEY_4.get(),
						GenerationsItems.ROCK_CANDY.get(), GenerationsItems.STEEL_CANDY.get(),
						GenerationsItems.TM_36.get(), GenerationsItems.TM_51.get(),
						GenerationsItems.TM_76.get(), GenerationsItems.TM_86.get(),
						GenerationsItems.TM_101.get(), GenerationsItems.TM_116.get(),
						GenerationsItems.TM_150.get(), GenerationsItems.TM_31.get(),
						GenerationsItems.TM_53.get(), GenerationsItems.TM_93.get(),
						GenerationsItems.TM_43.get(), GenerationsItems.TM_99.get(),
						GenerationsItems.TM_104.get(), GenerationsItems.TM_121.get(),
						GenerationsItems.TM_180.get(), GenerationsItems.TM_223.get(),
						GenerationsItems.TM_225.get(), GenerationsItems.Z_INGOT.get(),
						GenerationsItems.TM_176.get(), GenerationsItems.TM_186.get(), GenerationsItems.TM_215.get()
						)));

		output.accept(JET_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), CobblemonItems.COBA_BERRY.asItem(),
						GenerationsItems.FLYING_GEM.get(), GenerationsItems.FLYING_MEMORY_DRIVE.get(),
						CobblemonItems.SHARP_BEAK.asItem(), GenerationsItems.IRON_BALL.get(),
						GenerationsItems.SKY_PLATE.get(), GenerationsItems.CLEVER_FEATHER.get(),
						GenerationsItems.GENIUS_FEATHER.get(), GenerationsItems.HEALTH_FEATHER.get(),
						GenerationsItems.MUSCLE_FEATHER.get(), GenerationsItems.PRETTY_FEATHER.get(),
						GenerationsItems.RESIST_FEATHER.get(), GenerationsItems.SWIFT_FEATHER.get(),
						GenerationsItems.EJECT_PACK.get(), GenerationsItems.CELL_BATTERY.get(),
						GenerationsItems.EJECT_BUTTON.get(), CobblemonItems.MIRACLE_SEED.asItem(),
						GenerationsItems.LIGHT_BALL.get(), CobblemonItems.POWER_HERB.asItem(),
						CobblemonItems.MENTAL_HERB.asItem(), CobblemonItems.MIRROR_HERB.asItem(),
						CobblemonItems.WHITE_HERB.asItem(), CobblemonItems.SILK_SCARF.asItem(),
						CobblemonItems.MAX_POTION.asItem(), GenerationsItems.MAX_REPEL.get(),
						CobblemonItems.METAL_POWDER.asItem(), GenerationsItems.METRONOME.get(),
						CobblemonItems.NANAB_BERRY.asItem(), CobblemonItems.POMEG_BERRY.asItem(),
						CobblemonItems.POWER_ANKLET.asItem(), CobblemonItems.POWER_BAND.asItem(),
						GenerationsItems.PRECOOKED_BURGER.get(), CobblemonItems.PREMIER_BALL.asItem(),
						CobblemonItems.PROTECTOR.asItem(), CobblemonItems.QUICK_BALL.asItem(),
						CobblemonItems.CHESTO_BERRY.asItem(), CobblemonItems.CHOICE_SCARF.asItem(),
						GenerationsItems.TERRAIN_EXTENDER.get(), CobblemonItems.AWAKENING.asItem(),
						CobblemonItems.ENERGY_ROOT.asItem(), CobblemonItems.HEAL_POWDER.asItem(),
						GenerationsItems.COURAGE_CANDY.get(), GenerationsItems.QUICK_CANDY.get(),
						GenerationsItems.LEMONADE.get(), CobblemonItems.PARALYZE_HEAL.asItem(),
						CobblemonItems.ZINC.asItem(), GenerationsItems.RELIC_SILVER.get(),
						GenerationsItems.RELIC_COPPER.get(), GenerationsItems.RELIC_GOLD.get(),
						GenerationsItems.ODD_KEYSTONE.get(), GenerationsItems.POKEMAIL_BRIDGE_D.get(),
						GenerationsItems.POKEMAIL_BRIDGE_M.get(), GenerationsItems.POKEMAIL_BRIDGE_S.get(),
						GenerationsItems.POKEMAIL_BRIDGE_T.get(), GenerationsItems.POKEMAIL_BRIDGE_V.get(),
						GenerationsItems.FLYING_CANDY.get(), GenerationsItems.HEALTH_CANDY_XL.get(),
						GenerationsItems.HONEY.get(), GenerationsItems.LEEK.get(), GenerationsItems.ENIGMA_FRAGMENT.get(),
						CobblemonItems.LEFTOVERS.asItem(), GenerationsItems.LONE_EARRING.get(),
						GenerationsItems.LUSTROUS_ORB.get(), GenerationsItems.BREAD.get(),
						GenerationsItems.PASTA.get(), GenerationsItems.FANCY_APPLE.get(),
						GenerationsItems.SPICE_MIX.get(), GenerationsItems.PACKAGED_CURRY.get(),
						GenerationsItems.X_SPEED_6.get(), CobblemonItems.X_ACCURACY.asItem(),
						GenerationsItems.X_ATTACK_3.get(), CobblemonItems.EXPERIENCE_CANDY_M.asItem(),
						CobblemonItems.EXPERIENCE_CANDY_XS.asItem(), GenerationsItems.ORB.get(),
						GenerationsItems.TM_14.get(), GenerationsItems.TM_27.get(),
						GenerationsItems.TM_40.get(), GenerationsItems.TM_65.get(),
						GenerationsItems.TM_97.get(), GenerationsItems.TM_113.get(),
						GenerationsItems.TM_160.get(), GenerationsItems.TM_164.get(),
						GenerationsItems.TM_197.get(), GenerationsItems.TM_216.get())
 						.add(key("cascarrafa_city_disc"))
 						.add(key("slumbering_weald_disc"))
 						.add(key("pokemon_center_disc"))
 						.add(key("azalea_town_disc"))
 						.add(key("lillie_disc"))
 						.add(key("route_228_disc"))
 						.add(key("jubilife_village_disc"))
 						.add(key("goldenrod_city_disc"))
 						.add(key("eterna_city_disc"))
 						.add(key("vermilion_city_disc"))));

		output.accept(LEADEN_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.RUSTY_FRAGMENT.get(),
						GenerationsItems.RUSTY_IRON_KEY_1.get(), GenerationsItems.RUSTY_IRON_KEY_2.get(),
						GenerationsItems.RUSTY_IRON_KEY_3.get(), GenerationsItems.RUSTY_IRON_KEY_4.get(),
						GenerationsItems.MIRROR.get(), GenerationsItems.METEORITE_SHARD.get(),
						GenerationsItems.ROCK_GEM.get(), CobblemonItems.ROCKY_HELMET.asItem(),
						CobblemonItems.SAFETY_GOGGLES.asItem(), GenerationsItems.SPRINK_LOTAD.get(),
						GenerationsItems.STEEL_GEM.get(), GenerationsItems.AIR_BALLOON.get(),
						CobblemonItems.BABIRI_BERRY.asItem(), GenerationsItems.BIG_PEARL.get(),
						GenerationsItems.BINDING_BAND.get(), CobblemonItems.CHESTO_BERRY.asItem(),
						CobblemonItems.CHILAN_BERRY.asItem(), CobblemonItems.CHOICE_BAND.asItem(),
						CobblemonItems.IRON.asItem(), GenerationsItems.IRON_BALL.get(),
						GenerationsItems.ITEM_DROP.get(), CobblemonItems.KEE_BERRY.asItem(),
						GenerationsItems.LAGGING_TAIL.get(), GenerationsItems.LAVA_COOKIE.get(),
						CobblemonItems.LIFE_ORB.asItem(), GenerationsItems.MACHO_BRACE.get(),
						CobblemonItems.MAGMARIZER.asItem(), CobblemonItems.MARANGA_BERRY.asItem(),
						CobblemonItems.MAX_ETHER.asItem(), CobblemonItems.METAL_COAT.asItem(),
						GenerationsItems.FIRE_CANDY.get(), GenerationsItems.ROCK_CANDY.get(),
						GenerationsItems.STEEL_CANDY.get(), GenerationsItems.BRITTLE_BONES.get(),
						GenerationsItems.SALAD_MIX.get(), GenerationsItems.FRIED_FOOD.get(),
						GenerationsItems.MOOMOO_CHEESE.get(), GenerationsItems.EJECT_PACK.get(),
						CobblemonItems.GANLON_BERRY.asItem(), CobblemonItems.APICOT_BERRY.asItem(),
						CobblemonItems.KEE_BERRY.asItem(), CobblemonItems.MARANGA_BERRY.asItem(),
						GenerationsItems.FLOAT_STONE.get(), CobblemonItems.FOCUS_BAND.asItem(),
						GenerationsItems.FOCUS_SASH.get(), GenerationsItems.LAGGING_TAIL.get(),
						GenerationsItems.GRIP_CLAW.get(), CobblemonItems.DEEP_SEA_TOOTH.asItem(),
						GenerationsItems.METRONOME.get(), GenerationsItems.RING_TARGET.get(),
						GenerationsItems.SHED_SHELL.get(), GenerationsItems.SHELL_BELL.get(),
						GenerationsItems.ROCK_INCENSE.get(), GenerationsItems.PURE_INCENSE.get(),
						GenerationsItems.WEAKNESS_POLICY.get(), GenerationsItems.ZOOM_LENS.get(),
						GenerationsItems.CRYSTAL.get(), GenerationsItems.RUBY.get(),
						GenerationsItems.SAPPHIRE.get(), GenerationsItems.SILICON.get(),
						CobblemonItems.EXPERIENCE_CANDY_L.asItem(), GenerationsItems.TM_8.get(),
						GenerationsItems.TM_24.get(), GenerationsItems.TM_38.get(),
						GenerationsItems.TM_49.get(), GenerationsItems.TM_67.get(),
						GenerationsItems.TM_107.get(), GenerationsItems.TM_118.get(),
						GenerationsItems.TM_125.get(), GenerationsItems.TM_141.get(),
						GenerationsItems.TM_144.get(), GenerationsItems.TM_153.get(),
						GenerationsItems.TM_157.get(), GenerationsItems.TM_165.get(),
						GenerationsItems.TM_36.get(), GenerationsItems.TM_51.get(),
						GenerationsItems.TM_76.get(), GenerationsItems.TM_86.get(),
						GenerationsItems.TM_101.get(), GenerationsItems.TM_116.get(), GenerationsItems.TM_150.get(),
						GenerationsItems.TM_176.get(), GenerationsItems.TM_186.get(), GenerationsItems.TM_215.get())
						.add(key("cerulean_city_disc"))
						.add(key("icirrus_city_disc"))
						.add(key("laverre_city_disc"))
						.add(key("surf_disc"))
						.add(key("lake_of_rage_disc"))
						.add(key("penny_disc"))
						.add(key("nemona_disc"))
						.add(key("zinnia_disc"))
						.add(key("cynthia_disc"))
						.add(key("deoxys_disc"))));

		output.accept(LEVEL_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ABSORB_BULB.get(), GenerationsItems.AMULET_COIN.get(),
						CobblemonItems.BLACK_BELT.asItem(), GenerationsItems.BLUE_SHARD.get(), GenerationsItems.BOBS_FOOD_TIN.get(),
						CobblemonItems.CARBOS.asItem(), CobblemonItems.ELECTIRIZER.asItem(), CobblemonItems.ETHER.asItem(),
						CobblemonItems.EVERSTONE.asItem(), CobblemonItems.EXPERIENCE_CANDY_XL.asItem(), CobblemonItems.EXP_SHARE.asItem(),
						CobblemonItems.FIRE_STONE.asItem(), GenerationsItems.GRASS_GEM.get(), GenerationsItems.GRASSY_SEED.get(),
						CobblemonItems.HARD_STONE.asItem(), GenerationsItems.HEAT_ROCK.get(), CobblemonItems.HEAVY_DUTY_BOOTS.asItem(),
						CobblemonItems.HONDEW_BERRY.asItem(), GenerationsItems.ICY_ROCK.get(), GenerationsItems.INSTANT_NOODLES.get(),
						GenerationsItems.IRON_BALL.get(), GenerationsItems.ITEM_URGE.get(), CobblemonItems.KELPSY_BERRY.asItem(),
						GenerationsItems.LAGGING_TAIL.get(), CobblemonItems.LANSAT_BERRY.asItem(), GenerationsItems.LARGE_LEEK.get(),
						GenerationsItems.LEAF_LETTER.get(), CobblemonItems.LEVEL_BALL.asItem(), CobblemonItems.LUCKY_EGG.asItem(),
						CobblemonItems.LUM_BERRY.asItem(), CobblemonItems.LURE_BALL.asItem(), CobblemonItems.MAGMARIZER.asItem(),
						GenerationsItems.MAX_LURE.get(), CobblemonItems.METAL_COAT.asItem(), GenerationsItems.MIGHTY_CANDY_XL.get(),
						CobblemonItems.ORAN_BERRY.asItem(), CobblemonItems.OVAL_STONE.asItem(), GenerationsItems.PASTA.get(),
						GenerationsItems.PEARL_STRING.get(), CobblemonItems.POWER_BRACER.asItem(), CobblemonItems.PRISM_SCALE.asItem(),
						CobblemonItems.PROTECTOR.asItem(), CobblemonItems.QUICK_POWDER.asItem(), CobblemonItems.RARE_CANDY.asItem(),
						CobblemonItems.RAZOR_CLAW.asItem(), CobblemonItems.RAZOR_FANG.asItem(), CobblemonItems.REAPER_CLOTH.asItem(),
						CobblemonItems.RIBBON_SWEET.asItem(), CobblemonItems.RINDO_BERRY.asItem(), GenerationsItems.ROCK_GEM.get(),
						GenerationsItems.ROCK_INCENSE.get(), CobblemonItems.SACHET.asItem(), CobblemonItems.SAFARI_BALL.asItem(),
						CobblemonItems.SHINY_STONE.asItem(), GenerationsItems.SMALL_BOUQUET.get(), GenerationsItems.SMART_CANDY_L.get(),
						GenerationsItems.SOOTHE_BELL.get(), CobblemonItems.SPELL_TAG.asItem(), GenerationsItems.SPICE_MIX.get(),
						CobblemonItems.STRAWBERRY_SWEET.asItem(), CobblemonItems.SUN_STONE.asItem(), CobblemonItems.SWEET_APPLE.asItem(),
						GenerationsItems.STATIC_WING.get(), CobblemonItems.TAMATO_BERRY.asItem(), CobblemonItems.TART_APPLE.asItem(),
						GenerationsItems.THICK_CLUB.get(), CobblemonItems.THUNDER_STONE.asItem(), GenerationsItems.TOUGH_CANDY_XL.get(),
						CobblemonItems.ULTRA_BALL.asItem(), CobblemonItems.UPGRADE.asItem(), GenerationsItems.UTILITY_UMBRELLA.get(),
						GenerationsItems.WATER_GEM.get(), CobblemonItems.WATER_STONE.asItem(), CobblemonItems.WHIPPED_DREAM.asItem(),
						CobblemonItems.ZINC.asItem(), Items.GUNPOWDER, Items.PORKCHOP, Items.SLIME_BALL, Items.WARPED_FUNGUS,
						Items.MUSIC_DISC_WARD, GenerationsItems.PURPLE_JUICE.get(), GenerationsItems.RED_JUICE.get(),
						GenerationsItems.YELLOW_JUICE.get(), GenerationsItems.BLUE_JUICE.get(), GenerationsItems.GREEN_JUICE.get(),
						GenerationsItems.PINK_JUICE.get(), GenerationsItems.EXP_CHARM.get(), CobblemonItems.BERRY_SWEET.asItem(),
						GenerationsItems.CRUMBLED_ROCK_KEY_1.get(), GenerationsItems.CRUMBLED_ROCK_KEY_2.get(),
						GenerationsItems.CRUMBLED_ROCK_KEY_3.get(), GenerationsItems.CRUMBLED_ROCK_KEY_4.get(),
						GenerationsItems.ROCKSTAR_COSTUME.get(), GenerationsItems.BELLE_COSTUME.get(), GenerationsItems.POPSTAR_COSTUME.get(),
						GenerationsItems.PHD_COSTUME.get(), GenerationsItems.LIBRE_COSTUME.get(), GenerationsItems.ORB.get(),
						GenerationsItems.RAINBOW_WING.get(), CobblemonItems.EXPERIENCE_CANDY_XS.asItem(), CobblemonItems.EXPERIENCE_CANDY_S.asItem(),
						CobblemonItems.EXPERIENCE_CANDY_M.asItem(), CobblemonItems.EXPERIENCE_CANDY_L.asItem(), CobblemonItems.EXPERIENCE_CANDY_XL.asItem(),
						CobblemonItems.QUIET_MINT.asItem(), CobblemonItems.RASH_MINT.asItem(), CobblemonItems.MILD_MINT.asItem(),
						CobblemonItems.CALM_MINT.asItem(), GenerationsItems.FIGHTING_CANDY.get(), GenerationsItems.TM_1.get(),
						GenerationsItems.TM_6.get(), GenerationsItems.TM_7.get(), GenerationsItems.TM_25.get(), GenerationsItems.TM_32.get(),
						GenerationsItems.TM_47.get(), GenerationsItems.TM_58.get(), GenerationsItems.TM_64.get(), GenerationsItems.TM_73.get(),
						GenerationsItems.TM_89.get(), GenerationsItems.TM_112.get(), GenerationsItems.TM_134.get(), GenerationsItems.TM_158.get(),
						GenerationsItems.TM_167.get(), GenerationsItems.TM_190.get(), GenerationsItems.TM_194.get(), GenerationsItems.TM_206.get(),
						GenerationsItems.DYNITE_ORE.get()
				)));

		output.accept(LOVE_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ABILITY_URGE.get(), GenerationsItems.AIR_BALLOON.get(),
						GenerationsItems.BINDING_BAND.get(), CobblemonItems.BURN_HEAL.asItem(), CobblemonItems.CHERI_BERRY.asItem(),
						CobblemonItems.CHERISH_BALL.asItem(), CobblemonItems.CLOVER_SWEET.asItem(), GenerationsItems.COURAGE_CANDY_XL.get(),
						CobblemonItems.DESTINY_KNOT.asItem(), CobblemonItems.DRAGON_SCALE.asItem(), CobblemonItems.DUBIOUS_DISC.asItem(),
						CobblemonItems.EVERSTONE.asItem(), CobblemonItems.EXPERIENCE_CANDY_XS.asItem(), GenerationsItems.FANCY_APPLE.get(),
						GenerationsItems.FRESH_WATER.get(), CobblemonItems.GUARD_SPEC.asItem(), CobblemonItems.HABAN_BERRY.asItem(),
						GenerationsItems.HEALTH_CANDY_L.get(), GenerationsItems.HONEY.get(), CobblemonItems.HP_UP.asItem(),
						CobblemonItems.ICE_HEAL.asItem(), CobblemonItems.ICE_STONE.asItem(), CobblemonItems.LEPPA_BERRY.asItem(),
						CobblemonItems.LOVE_BALL.asItem(), CobblemonItems.LOVE_SWEET.asItem(), CobblemonItems.MAX_POTION.asItem(),
						CobblemonItems.OVAL_STONE.asItem(), GenerationsItems.PEARL_STRING.get(), GenerationsItems.PINK_SCARF.get(),
						GenerationsItems.POKE_DOLL.get(), CobblemonItems.POTION.asItem(), CobblemonItems.POWER_ANKLET.asItem(),
						CobblemonItems.POWER_BRACER.asItem(), GenerationsItems.MIRROR.get(),
						GenerationsItems.QUICK_CANDY.get(), CobblemonItems.RIBBON_SWEET.asItem(), CobblemonItems.ROWAP_BERRY.asItem(),
						GenerationsItems.SAUSAGES.get(), CobblemonItems.SITRUS_BERRY.asItem(), GenerationsItems.SMOKED_POKE_TAIL.get(),
						GenerationsItems.SMOOTH_ROCK.get(), CobblemonItems.STARF_BERRY.asItem(), GenerationsItems.STRANGE_SOUVENIR.get(),
						CobblemonItems.STRAWBERRY_SWEET.asItem(), GenerationsItems.TOUGH_CANDY.get(), GenerationsItems.WATER_GEM.get(),
						CobblemonItems.WATER_STONE.asItem(), CobblemonItems.WATMEL_BERRY.asItem(), CobblemonItems.WEPEAR_BERRY.asItem(),
						GenerationsItems.WHITE_FLUTE.get(), CobblemonItems.WHITE_HERB.asItem(), GenerationsItems.X_DEFENSE_3.get(),
						Items.SCUTE, Items.MUSIC_DISC_WARD, GenerationsItems.PURPLE_JUICE.get(), GenerationsItems.RED_JUICE.get(),
						GenerationsItems.YELLOW_JUICE.get(), GenerationsItems.BLUE_JUICE.get(), GenerationsItems.GREEN_JUICE.get(),
						GenerationsItems.PINK_JUICE.get(), GenerationsItems.SUN_FLUTE.get(), GenerationsItems.MOON_FLUTE.get(),
						GenerationsItems.N_SOLARIZER.get(), GenerationsItems.N_LUNARIZER.get(), GenerationsItems.MELODY_FLUTE.get(),
						GenerationsItems.LIGHT_SOUL.get(), CobblemonItems.EXPERIENCE_CANDY_M.asItem(), GenerationsItems.POKEMAIL_BUBBLE.get(),
						GenerationsItems.POKEMAIL_DREAM.get(), GenerationsItems.POKEMAIL_FAB.get(), GenerationsItems.POKEMAIL_FAVORED.get(),
						GenerationsItems.POKEMAIL_FLAME.get(), GenerationsItems.POKEMAIL_GLITTER.get(), GenerationsItems.POKEMAIL_GRASS.get(),
						GenerationsItems.POKEMAIL_GREET.get(), GenerationsItems.POKEMAIL_HARBOR.get(), GenerationsItems.POKEMAIL_HEART.get(),
						GenerationsItems.POKEMAIL_INQUIRY.get(), GenerationsItems.POKEMAIL_LIKE.get(), GenerationsItems.POKEMAIL_MECH.get(),
						GenerationsItems.POKEMAIL_MOSAIC.get(), GenerationsItems.FAIRY_CANDY.get(), GenerationsItems.TM_2.get(),
						GenerationsItems.TM_19.get(), GenerationsItems.TM_37.get(), GenerationsItems.TM_79.get(),
						GenerationsItems.TM_127.get(), GenerationsItems.TM_139.get(), GenerationsItems.TM_178.get(),
						GenerationsItems.TM_217.get(), GenerationsItems.TM_218.get(), GenerationsItems.TM_228.get(),
						GenerationsItems.TM_174.get(), GenerationsItems.TM_187.get(), GenerationsItems.TM_212.get(),
						GenerationsItems.TM_177.get(), GenerationsItems.TM_198.get(), GenerationsItems.TM_224.get())
						.add(key("cerulean_city_disc"))
						.add(key("icirrus_city_disc"))
						.add(key("laverre_city_disc"))
						.add(key("surf_disc"))
						.add(key("lake_of_rage_disc"))
						.add(key("penny_disc"))
						.add(key("nemona_disc"))
						.add(key("zinnia_disc"))
						.add(key("cynthia_disc"))
						.add(key("deoxys_disc"))));

		output.accept(LURE_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ADRENALINE_ORB.get(), CobblemonItems.BABIRI_BERRY.asItem(),
						CobblemonItems.BIG_ROOT.asItem(), GenerationsItems.BLUE_SHARD.get(), CobblemonItems.BLUK_BERRY.asItem(),
						GenerationsItems.BOBS_FOOD_TIN.get(), CobblemonItems.CHARCOAL.asItem(), CobblemonItems.CHARTI_BERRY.asItem(),
						CobblemonItems.CLEANSE_TAG.asItem(), CobblemonItems.COBA_BERRY.asItem(), CobblemonItems.EXPERIENCE_CANDY_XS.asItem(),
						GenerationsItems.GREEN_SHARD.get(), CobblemonItems.GROWTH_MULCH.asItem(), GenerationsItems.HEAT_ROCK.get(),
						GenerationsItems.HONEY.get(), CobblemonItems.ICE_HEAL.asItem(), CobblemonItems.JABOCA_BERRY.asItem(),
						CobblemonItems.KASIB_BERRY.asItem(), CobblemonItems.KEBIA_BERRY.asItem(), GenerationsItems.LAGGING_TAIL.get(),
						GenerationsItems.LAX_INCENSE.get(), GenerationsItems.LEAF_LETTER.get(), GenerationsItems.LEMONADE.get(),
						GenerationsItems.LONE_EARRING.get(), GenerationsItems.LUCK_INCENSE.get(), GenerationsItems.LUCKY_PUNCH.get(),
						GenerationsItems.LURE.get(), CobblemonItems.LURE_BALL.asItem(), CobblemonItems.MAGO_BERRY.asItem(),
						GenerationsItems.MAX_LURE.get(), GenerationsItems.MAX_REPEL.get(), CobblemonItems.METAL_POWDER.asItem(),
						GenerationsItems.OLD_GATEAU.get(), CobblemonItems.PAMTRE_BERRY.asItem(), GenerationsItems.PASS_ORB.get(),
						GenerationsItems.PASTA.get(), CobblemonItems.PINAP_BERRY.asItem(), GenerationsItems.POKE_DOLL.get(),
						GenerationsItems.POKE_TOY.get(), CobblemonItems.POWER_HERB.asItem(), GenerationsItems.POWER_PLANT_PASS.get(),
						GenerationsItems.PURE_INCENSE.get(), GenerationsItems.PURPLE_NECTAR.get(), CobblemonItems.RAWST_BERRY.asItem(),
						GenerationsItems.RED_CARD.get(), GenerationsItems.REPEL.get(), GenerationsItems.ROCK_INCENSE.get(),
						CobblemonItems.ROCKY_HELMET.asItem(), GenerationsItems.ROSE_INCENSE.get(), CobblemonItems.ROWAP_BERRY.asItem(),
						GenerationsItems.SALAD_MIX.get(), GenerationsItems.SEA_INCENSE.get(), CobblemonItems.SMOKE_BALL.asItem(),
						CobblemonItems.SPELON_BERRY.asItem(), GenerationsItems.SPRINK_LOTAD.get(), CobblemonItems.STARF_BERRY.asItem(),
						GenerationsItems.SUPER_LURE.get(), GenerationsItems.SUPER_REPEL.get(), GenerationsItems.SWEET_HEART.get(),
						GenerationsItems.TINY_MUSHROOM.get(), GenerationsItems.TOUGH_CANDY_L.get(), CobblemonItems.WACAN_BERRY.asItem(),
						GenerationsItems.WAVE_INCENSE.get(), GenerationsItems.X_ACCURACY_3.get(), GenerationsItems.X_SPEED_2.get(),
						Items.PRISMARINE, Items.FEATHER, Items.BONE, Items.GHAST_TEAR, GenerationsItems.Z_INGOT.get(),
						Items.PRISMARINE_CRYSTALS, Items.MUSIC_DISC_CHIRP, GenerationsItems.MARK_CHARM.get(),
						GenerationsItems.FILM.get(), GenerationsItems.CAMERA.get(), GenerationsItems.WAILMER_PAIL.get(),
						GenerationsItems.RUSTY_IRON_KEY_1.get(), GenerationsItems.RUSTY_IRON_KEY_2.get(),
						GenerationsItems.RUSTY_IRON_KEY_3.get(), GenerationsItems.RUSTY_IRON_KEY_4.get(),
						CobblemonItems.EXPERIENCE_CANDY_S.asItem(), GenerationsItems.GRASS_CANDY.get(), GenerationsItems.TM_20.get(),
						GenerationsItems.TM_33.get(), GenerationsItems.TM_56.get(), GenerationsItems.TM_71.get(),
						GenerationsItems.TM_81.get(), GenerationsItems.TM_111.get(), GenerationsItems.TM_119.get(),
						GenerationsItems.TM_137.get(), GenerationsItems.TM_146.get(), GenerationsItems.TM_155.get(),
						GenerationsItems.TM_159.get(), GenerationsItems.TM_168.get(), GenerationsItems.TM_5.get(),
						GenerationsItems.TM_28.get(), GenerationsItems.TM_35.get(), GenerationsItems.TM_55.get(),
						GenerationsItems.TM_84.get(), GenerationsItems.TM_90.get(), GenerationsItems.TM_106.get(),
						GenerationsItems.TM_133.get(), GenerationsItems.TM_149.get()
				)));

		output.accept(LUXURY_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ABILITY_CAPSULE.get(), GenerationsItems.AMULET_COIN.get(),
						CobblemonItems.BELUE_BERRY.asItem(), GenerationsItems.BIG_NUGGET.get(), GenerationsItems.BLACK_FLUTE.get(),
						GenerationsItems.BLUE_SCARF.get(), CobblemonItems.BLUK_BERRY.asItem(), GenerationsItems.COCONUT_MILK.get(),
						GenerationsItems.COURAGE_CANDY_L.get(), CobblemonItems.CRACKED_POT.asItem(), CobblemonItems.DAWN_STONE.asItem(),
						CobblemonItems.DEEP_SEA_TOOTH.asItem(), CobblemonItems.DRAGON_FANG.asItem(), CobblemonItems.DUSK_STONE.asItem(),
						GenerationsItems.ELEVATOR_KEY.get(), CobblemonItems.EXPERIENCE_CANDY_M.asItem(), CobblemonItems.EXP_SHARE.asItem(),
						GenerationsItems.FESTIVAL_TICKET.get(), CobblemonItems.FLOWER_SWEET.asItem(), CobblemonItems.FULL_HEAL.asItem(),
						GenerationsItems.FIERY_WING.get(), CobblemonItems.GREAT_BALL.asItem(), GenerationsItems.GREEN_SCARF.get(),
						CobblemonItems.GREPA_BERRY.asItem(), GenerationsItems.HEALTH_CANDY_L.get(), CobblemonItems.ICE_HEAL.asItem(),
						CobblemonItems.ICE_STONE.asItem(), CobblemonItems.LOVE_SWEET.asItem(), GenerationsItems.LUCK_INCENSE.get(),
						CobblemonItems.LUCKY_EGG.asItem(), GenerationsItems.LUMIOSE_GALETTE.get(), GenerationsItems.LURE.get(),
						CobblemonItems.LUXURY_BALL.asItem(), GenerationsItems.MARBLE.get(), GenerationsItems.MAX_LURE.get(),
						GenerationsItems.MIGHTY_CANDY_XL.get(), CobblemonItems.NOMEL_BERRY.asItem(), GenerationsItems.ODD_KEYSTONE.get(),
						CobblemonItems.PAMTRE_BERRY.asItem(), GenerationsItems.PEARL.get(), GenerationsItems.PERMIT.get(),
						GenerationsItems.PEWTER_CRUNCHIES.get(), CobblemonItems.POWER_BAND.asItem(), CobblemonItems.PREMIER_BALL.asItem(),
						GenerationsItems.PSYCHIC_SEED.get(), GenerationsItems.QUICK_CANDY_XL.get(), GenerationsItems.RARE_BONE.get(),
						GenerationsItems.RED_FLUTE.get(), GenerationsItems.ROOM_SERVICE.get(), GenerationsItems.SHOAL_SALT.get(),
						CobblemonItems.SILK_SCARF.asItem(), GenerationsItems.SMART_CANDY_XL.get(), GenerationsItems.SOOTHE_BELL.get(),
						GenerationsItems.SWEET_HEART.get(), GenerationsItems.TOUGH_CANDY.get(), GenerationsItems.TROPICAL_SHELL.get(),
						GenerationsItems.X_DEFENSE_6.get(), GenerationsItems.YELLOW_FLUTE.get(), Items.QUARTZ_BLOCK,
						Items.DIAMOND, Items.LEATHER, GenerationsItems.ELEGANT_WING.get(), Items.MUSIC_DISC_CHIRP, Items.NETHERITE_SCRAP,
						GenerationsItems.CRYSTAL.get(), GenerationsItems.SAPPHIRE.get(),
						GenerationsItems.SILICON.get(), GenerationsItems.SPARKLING_SHARD.get(), CobblemonItems.EXPERIENCE_CANDY_L.asItem(),
						GenerationsItems.POISON_CANDY.get(), GenerationsItems.DRAGON_CANDY.get(), GenerationsItems.TM_13.get(),
						GenerationsItems.TM_26.get(), GenerationsItems.TM_45.get(), GenerationsItems.TM_83.get(), GenerationsItems.TM_91.get(),
						GenerationsItems.TM_102.get(), GenerationsItems.TM_148.get(), GenerationsItems.TM_44.get(), GenerationsItems.TM_78.get(),
						GenerationsItems.TM_100.get(), GenerationsItems.TM_115.get(), GenerationsItems.TM_156.get(), GenerationsItems.TM_169.get(),
						GenerationsItems.TM_178.get(), GenerationsItems.TM_217.get(), GenerationsItems.TM_218.get(), GenerationsItems.TM_228.get(),
						GenerationsItems.TM_174.get(), GenerationsItems.TM_187.get(), GenerationsItems.TM_212.get(),
						GenerationsItems.TM_177.get(), GenerationsItems.TM_198.get(), GenerationsItems.TM_224.get())
						.add(key("iris_disc"))
						.add(key("nessa_disc"))
						.add(key("team_rocket_disc"))
						.add(key("sada_and_turo_disc"))
						.add(key("south_province_disc"))
						.add(key("rival_disc"))
						.add(key("lusamine_disc"))
						.add(key("xy_legendary_disc"))
						.add(key("kanto_disc"))
						.add(key("ultra_necrozma_disc"))));

		output.accept(MASTER_BALL, LootTable.lootTable()
				.withPool(createPool(ConstantValue.exactly(1),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ABILITY_CAPSULE.get(), GenerationsItems.ABILITY_PATCH.get(),
						GenerationsItems.AMULET_COIN.get(), CobblemonItems.ASSAULT_VEST.asItem(), CobblemonItems.BEAST_BALL.asItem(),
						GenerationsItems.BLACK_FLUTE.get(), GenerationsItems.BLUE_SHARD.get(), GenerationsItems.BOTTLE_CAP.get(),
						CobblemonItems.BURN_HEAL.asItem(), CobblemonItems.CHERI_BERRY.asItem(), CobblemonItems.CHESTO_BERRY.asItem(),
						CobblemonItems.CHOICE_SCARF.asItem(), CobblemonItems.CHOPLE_BERRY.asItem(), GenerationsItems.COURAGE_CANDY_XL.get(),
						CobblemonItems.CRACKED_POT.asItem(), GenerationsItems.DAMP_ROCK.get(), CobblemonItems.DAWN_STONE.asItem(),
						GenerationsItems.DIRE_HIT_3.get(), GenerationsItems.DISCOUNT_COUPON.get(), GenerationsItems.DNA_SPLICERS.get(),
						GenerationsItems.DYNAMAX_CANDY.get(), GenerationsItems.ELECTRIC_GEM.get(), GenerationsItems.ELEVATOR_KEY.get(),
						CobblemonItems.EXPERIENCE_CANDY_XL.asItem(), CobblemonItems.FULL_RESTORE.asItem(), GenerationsItems.GIGANTAMIX.get(),
						GenerationsItems.GOLD_BOTTLE_CAP.get(), GenerationsItems.GRISEOUS_ORB.get(), GenerationsItems.HEALTH_CANDY.get(),
						CobblemonItems.IRON.asItem(), CobblemonItems.LEFTOVERS.asItem(), CobblemonItems.LIFE_ORB.asItem(),
						CobblemonItems.LUCKY_EGG.asItem(), GenerationsItems.LUSTROUS_ORB.get(), CobblemonItems.MASTER_BALL.asItem(),
						GenerationsItems.MAX_HONEY.get(), CobblemonItems.NEVER_MELT_ICE.asItem(), GenerationsItems.ODD_KEYSTONE.get(),
						CobblemonItems.PARK_BALL.asItem(), GenerationsItems.PEWTER_CRUNCHIES.get(), CobblemonItems.POISON_BARB.asItem(),
						CobblemonItems.PP_MAX.asItem(), GenerationsItems.PSYCHIC_GEM.get(), GenerationsItems.QUICK_CANDY_XL.get(),
						GenerationsItems.RARE_BONE.get(), CobblemonItems.RARE_CANDY.asItem(), GenerationsItems.RED_CARD.get(),
						GenerationsItems.RELIC_BAND.get(), GenerationsItems.RELIC_CROWN.get(), GenerationsItems.RELIC_STATUE.get(),
						GenerationsItems.RELIC_VASE.get(), CobblemonItems.ROCKY_HELMET.asItem(), GenerationsItems.SOUL_DEW.get(),
						CobblemonItems.TOXIC_ORB.asItem(), GenerationsItems.WISHING_PIECE.get(), GenerationsItems.X_ACCURACY_6.get(),
						Items.NETHER_STAR, Items.TRIDENT, Items.NAUTILUS_SHELL, Items.NETHERITE_SCRAP,
						Items.MUSIC_DISC_OTHERSIDE, GenerationsItems.RUSTY_FRAGMENT.get(), GenerationsItems.SCROLL_PAGE.get(),
						GenerationsItems.ORB.get(), GenerationsItems.RAINBOW_WING.get(), GenerationsItems.SINISTER_WING.get(),
						GenerationsItems.FIERY_WING.get(), GenerationsItems.HEALTH_FEATHER.get(), GenerationsItems.BELLIGERENT_WING.get(),
						GenerationsItems.ELEGANT_WING.get(), GenerationsItems.ICY_WING.get(), GenerationsItems.STATIC_WING.get(),
						GenerationsItems.SILVER_WING.get(), GenerationsItems.LAVA_CRYSTAL.get(), CobblemonItems.EXPERIENCE_CANDY_XL.asItem(),
						GenerationsItems.DRACO_PLATE.get(), GenerationsItems.DREAD_PLATE.get(), GenerationsItems.EARTH_PLATE.get(),
						GenerationsItems.FIST_PLATE.get(), GenerationsItems.FLAME_PLATE.get(), GenerationsItems.ICICLE_PLATE.get(),
						GenerationsItems.INSECT_PLATE.get(), GenerationsItems.IRON_PLATE.get(), GenerationsItems.MEADOW_PLATE.get(),
						GenerationsItems.MIND_PLATE.get(), GenerationsItems.PIXIE_PLATE.get(), GenerationsItems.SKY_PLATE.get(),
						GenerationsItems.SPLASH_PLATE.get(), GenerationsItems.SPOOKY_PLATE.get(), GenerationsItems.STONE_PLATE.get(),
						GenerationsItems.TOXIC_PLATE.get(), GenerationsItems.ZAP_PLATE.get(), GenerationsItems.LEGEND_PLATE.get(),
						GenerationsItems.SPARKLING_SHARD.get(), GenerationsItems.MIRROR.get(), GenerationsItems.DARK_SOUL.get(),
						GenerationsItems.LIGHT_SOUL.get(), GenerationsItems.METEORITE_SHARD.get(), GenerationsItems.MEW_DNA_FIBER.get(),
						GenerationsItems.LUSTROUS_GLOBE.get(), GenerationsItems.GRISEOUS_CORE.get(), GenerationsItems.ADAMANT_CRYSTAL.get(),
						GenerationsItems.TM_9.get(), GenerationsItems.TM_23.get(), GenerationsItems.TM_48.get(),
						GenerationsItems.TM_68.get(), GenerationsItems.TM_72.get(), GenerationsItems.TM_82.get(),
						GenerationsItems.TM_96.get(), GenerationsItems.TM_126.get(), GenerationsItems.TM_136.get(),
						GenerationsItems.TM_147.get(), GenerationsItems.TM_166.get(), GenerationsItems.MEW_FOSSIL.get()
				)));

		output.accept(MOON_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.AIR_BALLOON.get(), GenerationsItems.BEACH_GLASS.get(),
						GenerationsItems.BIG_PEARL.get(), GenerationsItems.BLUE_SCARF.get(), CobblemonItems.BLUK_BERRY.asItem(),
						CobblemonItems.CALCIUM.asItem(), CobblemonItems.CARBOS.asItem(), GenerationsItems.CASTELIACONE.get(),
						GenerationsItems.CHALKY_STONE.get(), CobblemonItems.CHARTI_BERRY.asItem(), CobblemonItems.COLBUR_BERRY.asItem(),
						CobblemonItems.DAWN_STONE.asItem(), CobblemonItems.DUSK_STONE.asItem(), CobblemonItems.EVERSTONE.asItem(),
						GenerationsItems.EVIOLITE.get(), CobblemonItems.EXPERIENCE_CANDY_M.asItem(), GenerationsItems.FAIRY_GEM.get(),
						CobblemonItems.FRIEND_BALL.asItem(), GenerationsItems.GALARICA_TWIG.get(), CobblemonItems.GANLON_BERRY.asItem(),
						GenerationsItems.GHOST_GEM.get(), GenerationsItems.GREEN_SHARD.get(), GenerationsItems.GROUND_GEM.get(),
						CobblemonItems.HEAVY_BALL.asItem(), CobblemonItems.HYPER_POTION.asItem(),
						GenerationsItems.ICY_ROCK.get(), CobblemonItems.IRON.asItem(), GenerationsItems.ITEM_URGE.get(),
						CobblemonItems.KEBIA_BERRY.asItem(), CobblemonItems.KINGS_ROCK.asItem(), CobblemonItems.LEPPA_BERRY.asItem(),
						CobblemonItems.LIGHT_CLAY.asItem(), GenerationsItems.LURE.get(), GenerationsItems.LUSTROUS_ORB.get(),
						CobblemonItems.MAGNET.asItem(), CobblemonItems.MAX_ETHER.asItem(), CobblemonItems.METAL_COAT.asItem(),
						CobblemonItems.METAL_POWDER.asItem(), GenerationsItems.METRONOME.get(), GenerationsItems.MISTY_SEED.get(),
						CobblemonItems.MOON_BALL.asItem(), CobblemonItems.MOON_STONE.asItem(), CobblemonItems.NEVER_MELT_ICE.asItem(),
						CobblemonItems.PAYAPA_BERRY.asItem(), GenerationsItems.POISON_GEM.get(), CobblemonItems.POWER_WEIGHT.asItem(),
						CobblemonItems.PROTECTOR.asItem(), GenerationsItems.PSYCHIC_GEM.get(), GenerationsItems.QUICK_CANDY_L.get(),
						CobblemonItems.REAPER_CLOTH.asItem(), GenerationsItems.RED_SCARF.get(), GenerationsItems.RED_SHARD.get(),
						GenerationsItems.RELIC_VASE.get(), GenerationsItems.RING_TARGET.get(), CobblemonItems.ROSELI_BERRY.asItem(),
						CobblemonItems.SHINY_STONE.asItem(), GenerationsItems.SHOAL_SHELL.get(), CobblemonItems.SITRUS_BERRY.asItem(),
						GenerationsItems.SMALL_BOUQUET.get(), GenerationsItems.SMOOTH_ROCK.get(), CobblemonItems.SPELL_TAG.asItem(),
						GenerationsItems.STAR_PIECE.get(), CobblemonItems.STAR_SWEET.asItem(), GenerationsItems.STARDUST.get(),
						GenerationsItems.STEEL_GEM.get(), CobblemonItems.SUN_STONE.asItem(), CobblemonItems.THUNDER_STONE.asItem(),
						GenerationsItems.TOUGH_CANDY_L.get(), CobblemonItems.WATER_STONE.asItem(), CobblemonItems.WEPEAR_BERRY.asItem(),
						GenerationsItems.WISHING_PIECE.get(), CobblemonItems.X_SPEED.asItem(), Items.PURPUR_BLOCK,
						Items.GLOWSTONE_DUST, Items.NETHER_WART, Items.PRISMARINE_CRYSTALS, Items.CRIMSON_FUNGUS,
						Items.MUSIC_DISC_11, GenerationsItems.WISHING_STAR.get(), Items.AMETHYST_SHARD, GenerationsItems.RUBY.get(),
						GenerationsItems.MEWTWO_ARMOR.get(), GenerationsItems.METEORITE_SHARD.get(), GenerationsItems.PSYCHIC_MEMORY_DRIVE.get(),
						GenerationsItems.FAIRY_MEMORY_DRIVE.get(), GenerationsItems.MIND_PLATE.get(), GenerationsItems.PIXIE_PLATE.get(),
						GenerationsItems.PSYCHIC_CANDY.get(), GenerationsItems.FAIRY_CANDY.get(), GenerationsItems.TM_4.get(),
						GenerationsItems.TM_16.get(), GenerationsItems.TM_41.get(), GenerationsItems.TM_54.get(),
						GenerationsItems.TM_59.get(), GenerationsItems.TM_63.get(), GenerationsItems.TM_74.get(),
						GenerationsItems.TM_75.get(), GenerationsItems.TM_85.get(), GenerationsItems.TM_92.get(),
						GenerationsItems.TM_98.get(), GenerationsItems.TM_109.get(), GenerationsItems.TM_120.get(),
						GenerationsItems.TM_128.get(), GenerationsItems.TM_129.get(), GenerationsItems.TM_138.get(),
						GenerationsItems.TM_161.get(), GenerationsItems.TM_2.get(), GenerationsItems.TM_19.get(),
						GenerationsItems.TM_37.get(), GenerationsItems.TM_79.get(), GenerationsItems.TM_127.get(),
						GenerationsItems.TM_139.get()).add(key("hi_tech_earbuds"))));

		output.accept(NEST_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.BUG_GEM.get(), CobblemonItems.CHOICE_SPECS.asItem(),
						CobblemonItems.CLEANSE_TAG.asItem(), GenerationsItems.CLEVER_FEATHER.get(), CobblemonItems.COBA_BERRY.asItem(),
						GenerationsItems.COURAGE_CANDY_L.get(), GenerationsItems.DIRE_HIT_2.get(), CobblemonItems.EXPERIENCE_CANDY_S.asItem(),
						GenerationsItems.FLUFFY_TAIL.get(), GenerationsItems.FLYING_GEM.get(), GenerationsItems.FRUIT_BUNCH.get(),
						GenerationsItems.GALARICA_TWIG.get(), GenerationsItems.GENIUS_FEATHER.get(), GenerationsItems.GRASSY_SEED.get(),
						CobblemonItems.GREPA_BERRY.asItem(), CobblemonItems.HABAN_BERRY.asItem(), GenerationsItems.HEALTH_FEATHER.get(),
						CobblemonItems.KEBIA_BERRY.asItem(), CobblemonItems.LEAF_STONE.asItem(), GenerationsItems.LEEK.get(),
						CobblemonItems.LIECHI_BERRY.asItem(), CobblemonItems.LUM_BERRY.asItem(), CobblemonItems.MENTAL_HERB.asItem(),
						GenerationsItems.MIGHTY_CANDY_XL.get(), GenerationsItems.MIXED_MUSHROOMS.get(), GenerationsItems.MUSCLE_FEATHER.get(),
						CobblemonItems.NANAB_BERRY.asItem(), CobblemonItems.NEST_BALL.asItem(), GenerationsItems.PACKAGED_CURRY.get(),
						GenerationsItems.PEARL_STRING.get(), GenerationsItems.PINK_NECTAR.get(), CobblemonItems.POISON_BARB.asItem(),
						GenerationsItems.POKE_TOY.get(), CobblemonItems.POWER_HERB.asItem(), GenerationsItems.PRETTY_FEATHER.get(),
						GenerationsItems.PURPLE_NECTAR.get(), CobblemonItems.REPEAT_BALL.asItem(), GenerationsItems.RESIST_FEATHER.get(),
						GenerationsItems.SALAD_MIX.get(), CobblemonItems.SHARP_BEAK.asItem(), GenerationsItems.SILVER_LEAF.get(),
						GenerationsItems.SUPER_REPEL.get(), CobblemonItems.SWEET_APPLE.asItem(), GenerationsItems.SWIFT_FEATHER.get(),
						CobblemonItems.WHITE_HERB.asItem(), CobblemonItems.WIKI_BERRY.asItem(), CobblemonItems.X_ACCURACY.asItem(),
						GenerationsItems.X_ATTACK_2.get(), CobblemonItems.X_DEFENSE.asItem(), CobblemonItems.X_SP_ATK.asItem(),
						CobblemonItems.X_SP_DEF.asItem(), Items.COBWEB, Items.FEATHER, Items.MUSIC_DISC_11,
						GenerationsItems.CRYSTAL.get(), GenerationsItems.SAPPHIRE.get(),
						GenerationsItems.SILICON.get(), GenerationsItems.FLYING_MEMORY_DRIVE.get(), GenerationsItems.SKY_PLATE.get(),
						GenerationsItems.POKEMAIL_ORANGE.get(), GenerationsItems.POKEMAIL_REPLY.get(), GenerationsItems.POKEMAIL_RETRO.get(),
						GenerationsItems.POKEMAIL_RSVP.get(), GenerationsItems.POKEMAIL_SHADOW.get(), GenerationsItems.POKEMAIL_SNOW.get(),
						GenerationsItems.POKEMAIL_SPACE.get(), GenerationsItems.POKEMAIL_STEEL.get(), GenerationsItems.POKEMAIL_THANKS.get(),
						GenerationsItems.POKEMAIL_TROPIC.get(), GenerationsItems.POKEMAIL_TUNNEL.get(), GenerationsItems.POKEMAIL_WAVE.get(),
						GenerationsItems.POKEMAIL_WOOD.get(), GenerationsItems.FLYING_CANDY.get(), GenerationsItems.BUG_CANDY.get(),
						GenerationsItems.TM_14.get(), GenerationsItems.TM_27.get(), GenerationsItems.TM_40.get(),
						GenerationsItems.TM_65.get(), GenerationsItems.TM_97.get(), GenerationsItems.TM_105.get(),
						GenerationsItems.TM_190.get(), GenerationsItems.TM_194.get(), GenerationsItems.TM_206.get(),
						GenerationsItems.TM_131.get(), GenerationsItems.TM_162.get(),
						GenerationsItems.TM_179.get(), GenerationsItems.TM_220.get(), GenerationsItems.TM_184.get(),
						GenerationsItems.TM_192.get(), GenerationsItems.TM_213.get(), GenerationsItems.TM_229.get())
						.add(key("cerulean_city_disc"))
						.add(key("icirrus_city_disc"))
						.add(key("laverre_city_disc"))
						.add(key("surf_disc"))
						.add(key("lake_of_rage_disc"))
						.add(key("penny_disc"))
						.add(key("nemona_disc"))
						.add(key("zinnia_disc"))
						.add(key("cynthia_disc"))
						.add(key("deoxys_disc"))));

		output.accept(NET_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 3),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ABSORB_BULB.get(), GenerationsItems.BIG_PEARL.get(),
						CobblemonItems.CHILAN_BERRY.asItem(), CobblemonItems.CHOICE_SPECS.asItem(), CobblemonItems.CLOVER_SWEET.asItem(),
						GenerationsItems.COURAGE_CANDY_XL.get(), CobblemonItems.DIRE_HIT.asItem(), CobblemonItems.DURIN_BERRY.asItem(),
						CobblemonItems.ELIXIR.asItem(), CobblemonItems.ENERGY_ROOT.asItem(), CobblemonItems.FRIEND_BALL.asItem(),
						CobblemonItems.GALARICA_CUFF.asItem(), GenerationsItems.GRASS_GEM.get(), CobblemonItems.KEE_BERRY.asItem(),
						GenerationsItems.LEAF_LETTER.get(), CobblemonItems.LEAF_STONE.asItem(), GenerationsItems.LUCK_INCENSE.get(),
						CobblemonItems.LUM_BERRY.asItem(), CobblemonItems.LURE_BALL.asItem(), CobblemonItems.MAGOST_BERRY.asItem(),
						CobblemonItems.MARANGA_BERRY.asItem(), CobblemonItems.MAX_ELIXIR.asItem(), CobblemonItems.MICLE_BERRY.asItem(),
						CobblemonItems.MIRACLE_SEED.asItem(), CobblemonItems.NET_BALL.asItem(), CobblemonItems.NOMEL_BERRY.asItem(),
						CobblemonItems.PASSHO_BERRY.asItem(), CobblemonItems.PETAYA_BERRY.asItem(), GenerationsItems.PLASMA_CARD.get(),
						GenerationsItems.RED_NECTAR.get(), CobblemonItems.REVIVAL_HERB.asItem(), CobblemonItems.RINDO_BERRY.asItem(),
						GenerationsItems.ROSE_INCENSE.get(), GenerationsItems.SEA_INCENSE.get(), GenerationsItems.SHOAL_SHELL.get(),
						CobblemonItems.SILVER_POWDER.asItem(), CobblemonItems.SPORT_BALL.asItem(), GenerationsItems.SUPER_LURE.get(),
						CobblemonItems.TANGA_BERRY.asItem(), CobblemonItems.TART_APPLE.asItem(), CobblemonItems.WEPEAR_BERRY.asItem(),
						GenerationsItems.WATER_CANDY.get(), GenerationsItems.BUG_CANDY.get(), GenerationsItems.X_ACCURACY_2.get(),
						GenerationsItems.X_ATTACK_3.get(), CobblemonItems.X_DEFENSE.asItem(), CobblemonItems.X_SP_ATK.asItem(),
						CobblemonItems.X_SP_DEF.asItem(), CobblemonItems.X_SPEED.asItem(), CobblemonItems.YACHE_BERRY.asItem(),
						GenerationsItems.YELLOW_FLUTE.get(), GenerationsItems.ZOOM_LENS.get(), Items.STRING,
						Items.SLIME_BALL, Items.RABBIT_HIDE, Items.MUSIC_DISC_13, GenerationsItems.OLD_ROD.get(),
						GenerationsItems.GOOD_ROD.get(), GenerationsItems.SUPER_ROD.get(),
						GenerationsItems.WAILMER_PAIL.get(), CobblemonItems.JOLLY_MINT.asItem(), CobblemonItems.NAIVE_MINT.asItem(),
						CobblemonItems.MODEST_MINT.asItem(), GenerationsItems.GROUND_MEMORY_DRIVE.get(),
						GenerationsItems.BUG_MEMORY_DRIVE.get(), GenerationsItems.EARTH_PLATE.get(), GenerationsItems.INSECT_PLATE.get(),
						GenerationsItems.TM_11.get(), GenerationsItems.TM_22.get(), GenerationsItems.TM_50.get(),
						GenerationsItems.TM_77.get(), GenerationsItems.TM_110.get(), GenerationsItems.TM_123.get(),
						GenerationsItems.TM_142.get(), GenerationsItems.TM_145.get(), GenerationsItems.TM_154.get(),
						GenerationsItems.TM_15.get(), GenerationsItems.TM_21.get(), GenerationsItems.TM_60.get(),
						GenerationsItems.TM_95.get(), GenerationsItems.TM_105.get(), GenerationsItems.TM_131.get(),
						GenerationsItems.TM_162.get(), GenerationsItems.TM_188.get(), GenerationsItems.TM_196.get(),
						GenerationsItems.TM_208.get(), GenerationsItems.TM_209.get(), GenerationsItems.TM_190.get(),
						GenerationsItems.TM_194.get(), GenerationsItems.TM_206.get(), GenerationsItems.TM_182.get(),
						GenerationsItems.TM_185.get(), GenerationsItems.TM_219.get())
						.add(key("cascarrafa_city_disc"))
						.add(key("slumbering_weald_disc"))
						.add(key("pokemon_center_disc"))
						.add(key("azalea_town_disc"))
						.add(key("lillie_disc"))
						.add(key("route_228_disc"))
						.add(key("jubilife_village_disc"))
						.add(key("goldenrod_city_disc"))
						.add(key("eterna_city_disc"))
						.add(key("vermilion_city_disc"))));

		output.accept(ORIGIN_BALL, LootTable.lootTable()
				.withPool(createPool(ConstantValue.exactly(1),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.SCROLL_PAGE.get(), GenerationsItems.MEW_DNA_FIBER.get(),
						GenerationsItems.LEGEND_PLATE.get(), CobblemonItems.BLACK_APRICORN.asItem(), CobblemonItems.BLUE_APRICORN.asItem(),
						CobblemonItems.GREEN_APRICORN.asItem(), CobblemonItems.PINK_APRICORN.asItem(), CobblemonItems.RED_APRICORN.asItem(),
						CobblemonItems.WHITE_APRICORN.asItem(), CobblemonItems.YELLOW_APRICORN.asItem(), GenerationsItems.HEALTH_CANDY_XL.get(),
						GenerationsItems.HONEY.get(), GenerationsItems.LEEK.get(), CobblemonItems.LEFTOVERS.asItem(),
						GenerationsItems.LONE_EARRING.get(), GenerationsItems.LUSTROUS_ORB.get(), GenerationsItems.GRISEOUS_ORB.get(),
						GenerationsItems.ADAMANT_ORB.get(), GenerationsItems.LUSTROUS_GLOBE.get(), GenerationsItems.GRISEOUS_CORE.get(),
						GenerationsItems.ADAMANT_CRYSTAL.get(), GenerationsItems.GRACIDEA.get(),
						GenerationsItems.RUBY.get(), GenerationsItems.RELIC_BAND.get(), GenerationsItems.RELIC_CROWN.get(),
						GenerationsItems.RELIC_STATUE.get(), GenerationsItems.RELIC_VASE.get(), GenerationsItems.FRUIT_BUNCH.get(),
						GenerationsItems.COCONUT_MILK.get(), GenerationsItems.INSTANT_NOODLES.get(), GenerationsItems.FRESH_CREAM.get(),
						GenerationsItems.EXP_CHARM.get(), CobblemonItems.ANTIDOTE.asItem(), CobblemonItems.APICOT_BERRY.asItem(),
						CobblemonItems.ASPEAR_BERRY.asItem(), CobblemonItems.BABIRI_BERRY.asItem(), CobblemonItems.BERRY_JUICE.asItem(),
						CobblemonItems.BURN_HEAL.asItem(), CobblemonItems.GROWTH_MULCH.asItem(), CobblemonItems.RICH_MULCH.asItem(),
						CobblemonItems.SURPRISE_MULCH.asItem(), CobblemonItems.BERRY_SWEET.asItem(), CobblemonItems.CLOVER_SWEET.asItem(),
						CobblemonItems.FLOWER_SWEET.asItem(), CobblemonItems.LOVE_SWEET.asItem(), CobblemonItems.RIBBON_SWEET.asItem(),
						CobblemonItems.STAR_SWEET.asItem(), CobblemonItems.STRAWBERRY_SWEET.asItem(), CobblemonItems.DEEP_SEA_SCALE.asItem(),
						CobblemonItems.DEEP_SEA_TOOTH.asItem(), CobblemonItems.DRAGON_SCALE.asItem(), CobblemonItems.DUBIOUS_DISC.asItem(),
						CobblemonItems.ELECTIRIZER.asItem(), CobblemonItems.KINGS_ROCK.asItem(), CobblemonItems.MAGMARIZER.asItem(),
						CobblemonItems.METAL_COAT.asItem(), CobblemonItems.OVAL_STONE.asItem(), CobblemonItems.PRISM_SCALE.asItem(),
						CobblemonItems.PROTECTOR.asItem(), CobblemonItems.RAZOR_CLAW.asItem(), CobblemonItems.RAZOR_FANG.asItem(),
						CobblemonItems.REAPER_CLOTH.asItem(), CobblemonItems.SACHET.asItem(), CobblemonItems.UPGRADE.asItem(),
						CobblemonItems.WHIPPED_DREAM.asItem(), CobblemonItems.CHIPPED_POT.asItem(), CobblemonItems.CRACKED_POT.asItem(),
						CobblemonItems.GALARICA_CUFF.asItem(), CobblemonItems.GALARICA_WREATH.asItem(), CobblemonItems.SWEET_APPLE.asItem(),
						CobblemonItems.TART_APPLE.asItem(), GenerationsItems.TM_9.get(), GenerationsItems.TM_23.get(),
						GenerationsItems.TM_48.get(), GenerationsItems.TM_68.get(), GenerationsItems.TM_72.get(),
						GenerationsItems.TM_82.get(), GenerationsItems.TM_96.get(), GenerationsItems.TM_126.get(),
						GenerationsItems.TM_136.get(), GenerationsItems.TM_147.get(), GenerationsItems.TM_166.get(),
						GenerationsItems.TM_44.get(), GenerationsItems.TM_78.get(), GenerationsItems.TM_100.get(),
						GenerationsItems.TM_115.get(), GenerationsItems.TM_156.get(), GenerationsItems.TM_169.get(), GenerationsItems.MEW_FOSSIL.get(),
						GenerationsItems.TM_175.get(), GenerationsItems.TM_214.get(), GenerationsItems.TM_200.get(),
						GenerationsItems.TM_222.get(), GenerationsItems.TM_226.get())
						.add(key("iris_disc"))
						.add(key("nessa_disc"))
						.add(key("team_rocket_disc"))
						.add(key("sada_and_turo_disc"))
						.add(key("south_province_disc"))
						.add(key("rival_disc"))
						.add(key("lusamine_disc"))
						.add(key("xy_legendary_disc"))
						.add(key("kanto_disc"))
						.add(key("ultra_necrozma_disc"))));

		output.accept(PARK_BALL, LootTable.lootTable()
				.withPool(createPool(ConstantValue.exactly(1),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ABILITY_PATCH.get(), GenerationsItems.ADAMANT_ORB.get(),
						GenerationsItems.AIR_BALLOON.get(), GenerationsItems.BIG_MALASADA.get(), GenerationsItems.BOILED_EGG.get(),
						GenerationsItems.BOTTLE_CAP.get(), CobblemonItems.CHARTI_BERRY.asItem(), CobblemonItems.CHILAN_BERRY.asItem(),
						CobblemonItems.CHOICE_BAND.asItem(), GenerationsItems.COMET_SHARD.get(), CobblemonItems.DESTINY_KNOT.asItem(),
						GenerationsItems.ELEVATOR_KEY.get(), CobblemonItems.EXPERIENCE_CANDY_XS.asItem(), GenerationsItems.FIGHTING_GEM.get(),
						GenerationsItems.FLUFFY_TAIL.get(), GenerationsItems.SILVER_WING.get(), GenerationsItems.RAINBOW_WING.get(),
						GenerationsItems.GOLD_BOTTLE_CAP.get(), GenerationsItems.GOLD_LEAF.get(), GenerationsItems.GRASS_GEM.get(),
						GenerationsItems.GREEN_SCARF.get(), GenerationsItems.GRUBBY_HANKY.get(), GenerationsItems.HEALTH_CANDY_XL.get(),
						GenerationsItems.HONEY.get(), GenerationsItems.LEEK.get(), CobblemonItems.LEFTOVERS.asItem(),
						GenerationsItems.LONE_EARRING.get(), GenerationsItems.LUSTROUS_ORB.get(), CobblemonItems.MASTER_BALL.asItem(),
						GenerationsItems.MAX_MUSHROOMS.get(), GenerationsItems.ODD_KEYSTONE.get(), CobblemonItems.PARK_BALL.asItem(),
						CobblemonItems.PP_MAX.asItem(), GenerationsItems.RARE_BONE.get(), GenerationsItems.RELIC_COPPER.get(),
						GenerationsItems.RELIC_GOLD.get(), GenerationsItems.RELIC_SILVER.get(), GenerationsItems.ROCK_GEM.get(),
						CobblemonItems.SPELON_BERRY.asItem(), GenerationsItems.TOUGH_CANDY_XL.get(), GenerationsItems.WHITE_FLUTE.get(),
						GenerationsItems.X_ACCURACY_3.get(), CobblemonItems.X_DEFENSE.asItem(), GenerationsItems.X_SPECIAL_ATTACK_6.get(),
						GenerationsItems.X_SPECIAL_DEFENSE_6.get(), GenerationsItems.X_SPEED_3.get(), GenerationsItems.X_SPEED_6.get(),
						GenerationsItems.YELLOW_FLUTE.get(), Items.DIAMOND, Items.MUSIC_DISC_OTHERSIDE,
						GenerationsItems.RUSTY_IRON_KEY_4.get(), GenerationsItems.SHATTERED_ICE_KEY_4.get(),
						GenerationsItems.CRUMBLED_ROCK_KEY_4.get(), GenerationsItems.FRAGMENTED_DRAGO_KEY_4.get(),
						GenerationsItems.DISCHARGED_ELEKI_KEY_4.get(), GenerationsItems.SHATTERED_RELIC_SONG_4.get(),
						GenerationsItems.SUN_FLUTE.get(), GenerationsItems.MOON_FLUTE.get(), GenerationsItems.N_SOLARIZER.get(),
						GenerationsItems.N_LUNARIZER.get(), GenerationsItems.MELODY_FLUTE.get(), GenerationsItems.HOOPA_RING.get(),
						GenerationsItems.GRACIDEA.get(), CobblemonItems.SERIOUS_MINT.asItem(), CobblemonItems.LONELY_MINT.asItem(),
						CobblemonItems.BRAVE_MINT.asItem(), CobblemonItems.ADAMANT_MINT.asItem(), CobblemonItems.NAUGHTY_MINT.asItem(),
						CobblemonItems.BOLD_MINT.asItem(), CobblemonItems.RELAXED_MINT.asItem(), CobblemonItems.IMPISH_MINT.asItem(),
						CobblemonItems.GENTLE_MINT.asItem(), CobblemonItems.LAX_MINT.asItem(), CobblemonItems.TIMID_MINT.asItem(),
						CobblemonItems.HASTY_MINT.asItem(), CobblemonItems.JOLLY_MINT.asItem(), CobblemonItems.NAIVE_MINT.asItem(),
						CobblemonItems.MODEST_MINT.asItem(), CobblemonItems.QUIET_MINT.asItem(), CobblemonItems.RASH_MINT.asItem(),
						CobblemonItems.MILD_MINT.asItem(), CobblemonItems.CALM_MINT.asItem(), CobblemonItems.SASSY_MINT.asItem(),
						CobblemonItems.CAREFUL_MINT.asItem(), GenerationsItems.BUG_MEMORY_DRIVE.get(), GenerationsItems.DARK_MEMORY_DRIVE.get(),
						GenerationsItems.DRAGON_MEMORY_DRIVE.get(), GenerationsItems.ELECTRIC_MEMORY_DRIVE.get(),
						GenerationsItems.FAIRY_MEMORY_DRIVE.get(), GenerationsItems.FIGHTING_MEMORY_DRIVE.get(),
						GenerationsItems.FIRE_MEMORY_DRIVE.get(), GenerationsItems.FLYING_MEMORY_DRIVE.get(),
						GenerationsItems.GHOST_MEMORY_DRIVE.get(), GenerationsItems.GRASS_MEMORY_DRIVE.get(),
						GenerationsItems.GROUND_MEMORY_DRIVE.get(), GenerationsItems.ICE_MEMORY_DRIVE.get(),
						GenerationsItems.POISON_MEMORY_DRIVE.get(), GenerationsItems.PSYCHIC_MEMORY_DRIVE.get(),
						GenerationsItems.ROCK_MEMORY_DRIVE.get(), GenerationsItems.STEEL_MEMORY_DRIVE.get(),
						GenerationsItems.WATER_MEMORY_DRIVE.get(), GenerationsItems.TM_8.get(), GenerationsItems.TM_24.get(),
						GenerationsItems.TM_38.get(), GenerationsItems.TM_49.get(), GenerationsItems.TM_67.get(),
						GenerationsItems.TM_107.get(), GenerationsItems.TM_118.get(), GenerationsItems.TM_125.get(),
						GenerationsItems.TM_141.get(), GenerationsItems.TM_144.get(), GenerationsItems.TM_153.get(),
						GenerationsItems.TM_157.get(), GenerationsItems.TM_165.get(), GenerationsItems.TM_20.get(),
						GenerationsItems.TM_33.get(), GenerationsItems.TM_56.get(), GenerationsItems.TM_71.get(),
						GenerationsItems.TM_81.get(), GenerationsItems.TM_111.get(), GenerationsItems.TM_119.get(),
						GenerationsItems.TM_137.get(), GenerationsItems.TM_146.get(), GenerationsItems.TM_155.get(),
						GenerationsItems.TM_159.get(), GenerationsItems.TM_168.get(),
						GenerationsItems.TM_176.get(), GenerationsItems.TM_186.get(), GenerationsItems.TM_215.get()
				)));

		output.accept(POKE_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 3),
						CobblemonItems.POKE_BALL.asItem(), CobblemonItems.AGUAV_BERRY.asItem(), GenerationsItems.AIR_BALLOON.get(),
						CobblemonItems.ANTIDOTE.asItem(), CobblemonItems.APICOT_BERRY.asItem(), CobblemonItems.ASPEAR_BERRY.asItem(),
						CobblemonItems.BABIRI_BERRY.asItem(), GenerationsItems.BACHS_FOOD_TIN.get(), GenerationsItems.BOBS_FOOD_TIN.get(),
						GenerationsItems.BOILED_EGG.get(), GenerationsItems.BREAD.get(), CobblemonItems.BURN_HEAL.asItem(),
						GenerationsItems.CELL_BATTERY.get(), CobblemonItems.CHARTI_BERRY.asItem(), CobblemonItems.CHESTO_BERRY.asItem(),
						CobblemonItems.CUSTAP_BERRY.asItem(), CobblemonItems.DIRE_HIT.asItem(), CobblemonItems.ELIXIR.asItem(),
						GenerationsItems.ENERGY_POWDER.get(), CobblemonItems.ENERGY_ROOT.asItem(), GenerationsItems.ESCAPE_ROPE.get(),
						GenerationsItems.FLOAT_STONE.get(), GenerationsItems.FLUFFY_TAIL.get(), CobblemonItems.FOCUS_BAND.asItem(),
						CobblemonItems.FRIEND_BALL.asItem(), CobblemonItems.GALARICA_CUFF.asItem(), CobblemonItems.GREAT_BALL.asItem(),
						CobblemonItems.GUARD_SPEC.asItem(), CobblemonItems.HEAL_POWDER.asItem(), GenerationsItems.HEALTH_CANDY.get(),
						CobblemonItems.IAPAPA_BERRY.asItem(), GenerationsItems.INSTANT_NOODLES.get(), CobblemonItems.LEVEL_BALL.asItem(),
						CobblemonItems.LOVE_BALL.asItem(), CobblemonItems.LURE_BALL.asItem(), CobblemonItems.MARANGA_BERRY.asItem(),
						CobblemonItems.METAL_POWDER.asItem(), CobblemonItems.MICLE_BERRY.asItem(), GenerationsItems.MOOMOO_CHEESE.get(),
						CobblemonItems.ORAN_BERRY.asItem(), GenerationsItems.PACKAGED_CURRY.get(), CobblemonItems.PETAYA_BERRY.asItem(),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.PRECOOKED_BURGER.get(), GenerationsItems.PRETTY_FEATHER.get(),
						CobblemonItems.QUALOT_BERRY.asItem(), GenerationsItems.QUICK_CANDY.get(), CobblemonItems.REPEAT_BALL.asItem(),
						GenerationsItems.ROTOM_CATALOG.get(), GenerationsItems.REPEL.get(), GenerationsItems.SHED_SHELL.get(),
						GenerationsItems.SMART_CANDY.get(), CobblemonItems.SMOKE_BALL.asItem(), CobblemonItems.TIMER_BALL.asItem(),
						GenerationsItems.TINY_MUSHROOM.get(), GenerationsItems.TOUGH_CANDY.get(), CobblemonItems.X_ATTACK.asItem(),
						GenerationsItems.X_SPECIAL_ATTACK_3.get(), CobblemonItems.BLACK_APRICORN.asItem(), CobblemonItems.BLUE_APRICORN.asItem(),
						CobblemonItems.GREEN_APRICORN.asItem(), CobblemonItems.PINK_APRICORN.asItem(), CobblemonItems.RED_APRICORN.asItem(),
						CobblemonItems.WHITE_APRICORN.asItem(), CobblemonItems.YELLOW_APRICORN.asItem(), Items.IRON_INGOT,
						Items.LEATHER, Items.INK_SAC, Items.MUSIC_DISC_13, GenerationsItems.FILM.get(), GenerationsItems.CAMERA.get(),
						GenerationsItems.CRYSTAL.get(), GenerationsItems.SAPPHIRE.get(), GenerationsItems.SILICON.get(),
						GenerationsItems.RUSTY_IRON_KEY_2.get(), GenerationsItems.SHATTERED_ICE_KEY_2.get(), GenerationsItems.CRUMBLED_ROCK_KEY_2.get(),
						GenerationsItems.FRAGMENTED_DRAGO_KEY_2.get(), GenerationsItems.DISCHARGED_ELEKI_KEY_2.get(), GenerationsItems.SHATTERED_RELIC_SONG_2.get(),
						CobblemonItems.EXPERIENCE_CANDY_XS.asItem(), GenerationsItems.POKEMAIL_ORANGE.get(), GenerationsItems.POKEMAIL_REPLY.get(),
						GenerationsItems.POKEMAIL_RETRO.get(), GenerationsItems.POKEMAIL_RSVP.get(), GenerationsItems.POKEMAIL_SHADOW.get(),
						GenerationsItems.POKEMAIL_SNOW.get(), GenerationsItems.POKEMAIL_SPACE.get(), GenerationsItems.POKEMAIL_STEEL.get(),
						GenerationsItems.POKEMAIL_THANKS.get(), GenerationsItems.POKEMAIL_TROPIC.get(), GenerationsItems.POKEMAIL_TUNNEL.get(),
						GenerationsItems.POKEMAIL_WAVE.get(), GenerationsItems.POKEMAIL_WOOD.get(), GenerationsItems.DYNITE_ORE.get(), GenerationsItems.TM_172.get(),
						GenerationsItems.TM_183.get(), GenerationsItems.TM_191.get(), GenerationsItems.TM_193.get(), GenerationsItems.TM_202.get(),
						GenerationsItems.TM_203.get(), GenerationsItems.TM_204.get(), GenerationsItems.TM_205.get(), GenerationsItems.TM_182.get(),
						GenerationsItems.TM_185.get(), GenerationsItems.TM_219.get()
				)));

		output.accept(PREMIER_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.BIG_MALASADA.get(), GenerationsItems.BIG_MUSHROOM.get(),
						CobblemonItems.BLACK_GLASSES.asItem(), GenerationsItems.BLUE_FLUTE.get(), GenerationsItems.BOTTLE_CAP.get(),
						CobblemonItems.CARBOS.asItem(), GenerationsItems.CASTELIACONE.get(), CobblemonItems.CHIPPED_POT.asItem(),
						CobblemonItems.CORNN_BERRY.asItem(), GenerationsItems.DARK_GEM.get(), CobblemonItems.DEEP_SEA_SCALE.asItem(),
						CobblemonItems.DESTINY_KNOT.asItem(), GenerationsItems.DIRE_HIT_3.get(), GenerationsItems.DISCOUNT_COUPON.get(),
						GenerationsItems.DRAGON_GEM.get(), CobblemonItems.DRAGON_SCALE.asItem(), CobblemonItems.DURIN_BERRY.asItem(),
						GenerationsItems.DYNAMAX_CANDY.get(), GenerationsItems.ELECTRIC_GEM.get(), CobblemonItems.EXPERIENCE_CANDY_L.asItem(),
						GenerationsItems.FANCY_APPLE.get(), GenerationsItems.FRUIT_BUNCH.get(), CobblemonItems.GALARICA_WREATH.asItem(),
						CobblemonItems.GUARD_SPEC.asItem(), GenerationsItems.HEALTH_CANDY_XL.get(), GenerationsItems.HEART_SCALE.get(),
						GenerationsItems.ELEGANT_WING.get(), GenerationsItems.ICE_GEM.get(), GenerationsItems.ITEM_DROP.get(),
						GenerationsItems.LOOKER_TICKET.get(), CobblemonItems.MAGOST_BERRY.asItem(), GenerationsItems.MOOMOO_MILK.get(),
						CobblemonItems.MUSCLE_BAND.asItem(), GenerationsItems.NORMAL_GEM.get(), GenerationsItems.NUGGET.get(),
						GenerationsItems.POISON_GEM.get(), CobblemonItems.PP_UP.asItem(), CobblemonItems.PREMIER_BALL.asItem(),
						CobblemonItems.PRISM_SCALE.asItem(), CobblemonItems.PROTEIN.asItem(), CobblemonItems.RIBBON_SWEET.asItem(),
						CobblemonItems.RINDO_BERRY.asItem(), CobblemonItems.ROCKY_HELMET.asItem(), CobblemonItems.ROWAP_BERRY.asItem(),
						GenerationsItems.SCOPE_LENS.get(), GenerationsItems.STEEL_GEM.get(), GenerationsItems.TINY_MUSHROOM.get(),
						CobblemonItems.ULTRA_BALL.asItem(), GenerationsItems.UTILITY_UMBRELLA.get(), CobblemonItems.WHITE_HERB.asItem(),
						GenerationsItems.GHOST_CANDY.get(), GenerationsItems.X_ATTACK_2.get(), Items.GOLD_INGOT,
						Items.INK_SAC, Items.BLAZE_POWDER, Items.QUARTZ, Items.MUSIC_DISC_STAL, GenerationsItems.EXP_CHARM.get(),
						GenerationsItems.SPARKLING_SHARD.get(), GenerationsItems.ELECTRIC_MEMORY_DRIVE.get(), GenerationsItems.ZAP_PLATE.get(),
						GenerationsItems.TM_17.get(), GenerationsItems.TM_29.get(), GenerationsItems.TM_42.get(),
						GenerationsItems.TM_61.get(), GenerationsItems.TM_114.get(), GenerationsItems.TM_151.get(),
						GenerationsItems.TM_3.get(), GenerationsItems.TM_18.get(), GenerationsItems.TM_30.get(),
						GenerationsItems.TM_43.get(), GenerationsItems.TM_62.get(), GenerationsItems.TM_87.get(),
						GenerationsItems.TM_94.get(), GenerationsItems.TM_108.get(), GenerationsItems.TM_140.get(), CobblemonItems.HEAVY_BALL.asItem())
						.add(key("iris_disc"))
						.add(key("nessa_disc"))
						.add(key("team_rocket_disc"))
						.add(key("sada_and_turo_disc"))
						.add(key("south_province_disc"))
						.add(key("rival_disc"))
						.add(key("lusamine_disc"))
						.add(key("xy_legendary_disc"))
						.add(key("kanto_disc"))
						.add(key("ultra_necrozma_disc"))
						.add(key("hi_tech_earbuds"))));

		output.accept(QUICK_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), CobblemonItems.ANTIDOTE.asItem(), CobblemonItems.BERRY_JUICE.asItem(),
						GenerationsItems.BLUE_SHARD.get(), GenerationsItems.BLUNDER_POLICY.get(), GenerationsItems.BOILED_EGG.get(),
						GenerationsItems.CASTELIACONE.get(), GenerationsItems.CELL_BATTERY.get(), CobblemonItems.CHOICE_SCARF.asItem(),
						CobblemonItems.CUSTAP_BERRY.asItem(), GenerationsItems.FLOAT_STONE.get(), GenerationsItems.FRESH_CREAM.get(),
						GenerationsItems.FULL_INCENSE.get(), GenerationsItems.GRIP_CLAW.get(), CobblemonItems.HABAN_BERRY.asItem(),
						CobblemonItems.HARD_STONE.asItem(), GenerationsItems.HEALTH_FEATHER.get(), GenerationsItems.HEART_SCALE.get(),
						GenerationsItems.HONEY.get(), CobblemonItems.KASIB_BERRY.asItem(), GenerationsItems.LAGGING_TAIL.get(),
						CobblemonItems.MAX_POTION.asItem(), GenerationsItems.MAX_REPEL.get(), CobblemonItems.METAL_POWDER.asItem(),
						GenerationsItems.METRONOME.get(), CobblemonItems.NANAB_BERRY.asItem(), CobblemonItems.POMEG_BERRY.asItem(),
						CobblemonItems.POWER_ANKLET.asItem(), CobblemonItems.POWER_BAND.asItem(), GenerationsItems.PRECOOKED_BURGER.get(),
						CobblemonItems.PREMIER_BALL.asItem(), CobblemonItems.PROTECTOR.asItem(), GenerationsItems.DISCHARGED_ELEKI_KEY_1.get(),
						GenerationsItems.DISCHARGED_ELEKI_KEY_2.get(), GenerationsItems.DISCHARGED_ELEKI_KEY_3.get(), GenerationsItems.DISCHARGED_ELEKI_KEY_4.get(),
						CobblemonItems.QUICK_BALL.asItem(), GenerationsItems.QUICK_CANDY_XL.get(), CobblemonItems.QUICK_CLAW.asItem(),
						GenerationsItems.RED_FLUTE.get(), GenerationsItems.ICY_WING.get(), GenerationsItems.ROLLER_SKATES.get(),
						GenerationsItems.ROOM_SERVICE.get(), CobblemonItems.SALAC_BERRY.asItem(), GenerationsItems.SMOKED_POKE_TAIL.get(),
						CobblemonItems.SPELON_BERRY.asItem(), GenerationsItems.STATIC_WING.get(), GenerationsItems.TERRAIN_EXTENDER.get(),
						GenerationsItems.THICK_CLUB.get(), GenerationsItems.NORMAL_CANDY.get(), GenerationsItems.ELECTRIC_CANDY.get(),
						GenerationsItems.X_ACCURACY_2.get(), GenerationsItems.X_ATTACK_3.get(), GenerationsItems.X_SPECIAL_ATTACK_3.get(),
						GenerationsItems.X_SPECIAL_DEFENSE_3.get(), GenerationsItems.X_SPEED_6.get(), GenerationsItems.YELLOW_NECTAR.get(),
						GenerationsItems.YELLOW_SHARD.get(), CobblemonItems.ZINC.asItem(), GenerationsItems.ZOOM_LENS.get(),
						Items.FEATHER, Items.PORKCHOP, Items.RABBIT_FOOT, Items.MUSIC_DISC_STAL, GenerationsItems.DRAGON_SOUL.get(),
						CobblemonItems.SASSY_MINT.asItem(), CobblemonItems.CAREFUL_MINT.asItem(), GenerationsItems.DARK_MEMORY_DRIVE.get(),
						GenerationsItems.DREAD_PLATE.get(), GenerationsItems.TM_1.get(), GenerationsItems.TM_6.get(),
						GenerationsItems.TM_7.get(), GenerationsItems.TM_25.get(), GenerationsItems.TM_32.get(),
						GenerationsItems.TM_47.get(), GenerationsItems.TM_57.get(), GenerationsItems.TM_66.get(),
						GenerationsItems.TM_70.get(), GenerationsItems.TM_80.get(), GenerationsItems.TM_88.get(),
						GenerationsItems.TM_103.get(), GenerationsItems.TM_117.get(), GenerationsItems.TM_122.get(),
						GenerationsItems.TM_130.get(), GenerationsItems.TM_132.get(), GenerationsItems.TM_152.get(),
						GenerationsItems.TM_163.get(), GenerationsItems.TM_171.get(), GenerationsItems.TM_9.get(),
						GenerationsItems.TM_23.get(), GenerationsItems.TM_48.get(), GenerationsItems.TM_68.get(),
						GenerationsItems.TM_72.get(), GenerationsItems.TM_82.get(), GenerationsItems.TM_96.get(),
						GenerationsItems.TM_126.get(), GenerationsItems.TM_136.get(), GenerationsItems.TM_147.get(),
						GenerationsItems.TM_166.get(), GenerationsItems.TM_173.get(), GenerationsItems.TM_210.get(), GenerationsItems.TM_211.get()
				).add(key("cascarrafa_city_disc")).add(key("slumbering_weald_disc")).add(key("pokemon_center_disc"))
								.add(key("azalea_town_disc")).add(key("lillie_disc")).add(key("route_228_disc"))
								.add(key("jubilife_village_disc")).add(key("goldenrod_city_disc"))
								.add(key("eterna_city_disc")).add(key("vermilion_city_disc"))));

		output.accept(REPEAT_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 3),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.AIR_BALLOON.get(), GenerationsItems.BINDING_BAND.get(),
						GenerationsItems.CELL_BATTERY.get(), CobblemonItems.CHOPLE_BERRY.asItem(), GenerationsItems.SINISTER_WING.get(),
						CobblemonItems.COLBUR_BERRY.asItem(), CobblemonItems.CUSTAP_BERRY.asItem(), GenerationsItems.EJECT_BUTTON.get(),
						GenerationsItems.ELECTRIC_SEED.get(), CobblemonItems.FAST_BALL.asItem(), CobblemonItems.FOCUS_BAND.asItem(),
						GenerationsItems.FOCUS_SASH.get(), GenerationsItems.FRIED_FOOD.get(), GenerationsItems.GRASSY_SEED.get(),
						CobblemonItems.GUARD_SPEC.asItem(), CobblemonItems.HEAVY_BALL.asItem(), CobblemonItems.HYPER_POTION.asItem(),
						GenerationsItems.ITEM_URGE.get(), GenerationsItems.LONE_EARRING.get(), GenerationsItems.MAX_REPEL.get(),
						GenerationsItems.MISTY_SEED.get(), GenerationsItems.OLD_GATEAU.get(), CobblemonItems.PASSHO_BERRY.asItem(),
						GenerationsItems.POWER_PLANT_PASS.get(), CobblemonItems.PROTEIN.asItem(), GenerationsItems.PSYCHIC_SEED.get(),
						GenerationsItems.RED_CARD.get(), CobblemonItems.REPEAT_BALL.asItem(), CobblemonItems.REVIVE.asItem(),
						CobblemonItems.SMOKE_BALL.asItem(), GenerationsItems.TIN_OF_BEANS.get(), GenerationsItems.X_ATTACK_6.get(),
						GenerationsItems.X_DEFENSE_2.get(), CobblemonItems.X_SP_ATK.asItem(), GenerationsItems.X_SPECIAL_DEFENSE_3.get(),
						CobblemonItems.X_SPEED.asItem(), GenerationsItems.YELLOW_SHARD.get(), CobblemonItems.ZINC.asItem(),
						CobblemonItems.BLACK_APRICORN.asItem(), CobblemonItems.BLUE_APRICORN.asItem(), CobblemonItems.GREEN_APRICORN.asItem(),
						CobblemonItems.PINK_APRICORN.asItem(), CobblemonItems.RED_APRICORN.asItem(), CobblemonItems.WHITE_APRICORN.asItem(),
						CobblemonItems.YELLOW_APRICORN.asItem(), Items.STRING, Items.MUSIC_DISC_MALL,
						GenerationsItems.FRAGMENTED_DRAGO_KEY_1.get(), GenerationsItems.FRAGMENTED_DRAGO_KEY_2.get(), GenerationsItems.FRAGMENTED_DRAGO_KEY_3.get(),
						GenerationsItems.FRAGMENTED_DRAGO_KEY_4.get(), GenerationsItems.FIGHTING_CANDY.get(), GenerationsItems.FILM.get(),
						GenerationsItems.CAMERA.get(), GenerationsItems.POKEMAIL_ORANGE.get(), GenerationsItems.POKEMAIL_REPLY.get(),
						GenerationsItems.POKEMAIL_RETRO.get(), GenerationsItems.POKEMAIL_RSVP.get(), GenerationsItems.POKEMAIL_SHADOW.get(),
						GenerationsItems.POKEMAIL_SNOW.get(), GenerationsItems.POKEMAIL_SPACE.get(), GenerationsItems.POKEMAIL_STEEL.get(),
						GenerationsItems.POKEMAIL_THANKS.get(), GenerationsItems.POKEMAIL_TROPIC.get(), GenerationsItems.POKEMAIL_TUNNEL.get(),
						GenerationsItems.POKEMAIL_WAVE.get(), GenerationsItems.POKEMAIL_WOOD.get(), GenerationsItems.TM_12.get(),
						GenerationsItems.TM_39.get(), GenerationsItems.TM_58.get(), GenerationsItems.TM_64.get(), GenerationsItems.TM_73.get(),
						GenerationsItems.TM_89.get(), GenerationsItems.TM_112.get(), GenerationsItems.TM_134.get(), GenerationsItems.TM_158.get(),
						GenerationsItems.TM_167.get(), GenerationsItems.TM_172.get(),
						GenerationsItems.TM_183.get(), GenerationsItems.TM_191.get(), GenerationsItems.TM_193.get(), GenerationsItems.TM_202.get(),
						GenerationsItems.TM_203.get(), GenerationsItems.TM_204.get(), GenerationsItems.TM_205.get(), GenerationsItems.TM_173.get(), GenerationsItems.TM_210.get(),
						GenerationsItems.TM_211.get(), GenerationsItems.TM_182.get(), GenerationsItems.TM_185.get(), GenerationsItems.TM_219.get()
				)));

		output.accept(SAFARI_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ADRENALINE_ORB.get(), CobblemonItems.AGUAV_BERRY.asItem(),
						GenerationsItems.BALM_MUSHROOM.get(), GenerationsItems.BIG_MUSHROOM.get(), GenerationsItems.BUG_GEM.get(),
						GenerationsItems.COMET_SHARD.get(), GenerationsItems.COURAGE_CANDY_L.get(), CobblemonItems.DRAGON_SCALE.asItem(),
						CobblemonItems.ETHER.asItem(), CobblemonItems.EXPERIENCE_CANDY_S.asItem(), CobblemonItems.EXP_SHARE.asItem(),
						GenerationsItems.FANCY_APPLE.get(), GenerationsItems.FESTIVAL_TICKET.get(), CobblemonItems.FIGY_BERRY.asItem(),
						GenerationsItems.FRESH_WATER.get(), GenerationsItems.FRUIT_BUNCH.get(), GenerationsItems.FULL_INCENSE.get(),
						CobblemonItems.GALARICA_WREATH.asItem(), CobblemonItems.GREAT_BALL.asItem(), GenerationsItems.GROUND_GEM.get(),
						GenerationsItems.DISCHARGED_ELEKI_KEY_1.get(), GenerationsItems.DISCHARGED_ELEKI_KEY_2.get(), GenerationsItems.DISCHARGED_ELEKI_KEY_3.get(),
						GenerationsItems.DISCHARGED_ELEKI_KEY_4.get(), CobblemonItems.HEAL_BALL.asItem(), GenerationsItems.INSTANT_NOODLES.get(),
						CobblemonItems.KASIB_BERRY.asItem(), GenerationsItems.LEEK.get(), CobblemonItems.LEFTOVERS.asItem(),
						CobblemonItems.MENTAL_HERB.asItem(), GenerationsItems.MIGHTY_CANDY.get(), CobblemonItems.MIRACLE_SEED.asItem(),
						GenerationsItems.ODD_INCENSE.get(), CobblemonItems.PAMTRE_BERRY.asItem(), GenerationsItems.PASS_ORB.get(),
						GenerationsItems.POLISHED_MUD_BALL.get(), GenerationsItems.RED_SCARF.get(), CobblemonItems.REVIVE.asItem(),
						GenerationsItems.ROCK_INCENSE.get(), CobblemonItems.ROSELI_BERRY.asItem(), CobblemonItems.SAFARI_BALL.asItem(),
						GenerationsItems.SALAD_MIX.get(), GenerationsItems.SILVER_LEAF.get(), CobblemonItems.SILVER_POWDER.asItem(),
						GenerationsItems.SOOTHE_BELL.get(), GenerationsItems.STAR_PIECE.get(), GenerationsItems.SUPER_REPEL.get(),
						GenerationsItems.TINY_MUSHROOM.get(), CobblemonItems.WIKI_BERRY.asItem(), CobblemonItems.WISE_GLASSES.asItem(),
						GenerationsItems.X_ACCURACY_3.get(), GenerationsItems.X_ATTACK_6.get(), GenerationsItems.X_DEFENSE_2.get(),
						GenerationsItems.X_SPECIAL_ATTACK_6.get(), GenerationsItems.X_SPECIAL_DEFENSE_3.get(), GenerationsItems.YELLOW_NECTAR.get(),
						CobblemonItems.BLACK_APRICORN.asItem(), CobblemonItems.BLUE_APRICORN.asItem(), CobblemonItems.GREEN_APRICORN.asItem(),
						CobblemonItems.PINK_APRICORN.asItem(), CobblemonItems.RED_APRICORN.asItem(), CobblemonItems.WHITE_APRICORN.asItem(),
						CobblemonItems.YELLOW_APRICORN.asItem(), Items.MILK_BUCKET, Items.BONE, Items.SPIDER_EYE, Items.CRIMSON_FUNGUS,
						Items.MUSIC_DISC_MALL, GenerationsItems.BASIC_SWEET_POKE_PUFF.get(), GenerationsItems.BASIC_MINT_POKE_PUFF.get(),
						GenerationsItems.BASIC_CITRUS_POKE_PUFF.get(), GenerationsItems.BASIC_MOCHA_POKE_PUFF.get(), GenerationsItems.BASIC_SPICE_POKE_PUFF.get(),
						GenerationsItems.RUSTY_FRAGMENT.get(), GenerationsItems.SCROLL_PAGE.get(), GenerationsItems.GRASS_MEMORY_DRIVE.get(),
						GenerationsItems.MEADOW_PLATE.get(), GenerationsItems.GRASS_CANDY.get(), GenerationsItems.GROUND_CANDY.get(),
						GenerationsItems.TM_20.get(), GenerationsItems.TM_33.get(), GenerationsItems.TM_56.get(),
						GenerationsItems.TM_71.get(), GenerationsItems.TM_81.get(), GenerationsItems.TM_111.get(),
						GenerationsItems.TM_119.get(), GenerationsItems.TM_137.get(), GenerationsItems.TM_146.get(),
						GenerationsItems.TM_155.get(), GenerationsItems.TM_159.get(), GenerationsItems.TM_168.get(),
						GenerationsItems.TM_5.get(), GenerationsItems.TM_28.get(), GenerationsItems.TM_35.get(),
						GenerationsItems.TM_55.get(), GenerationsItems.TM_84.get(), GenerationsItems.TM_90.get(),
						GenerationsItems.TM_106.get(), GenerationsItems.TM_133.get(), GenerationsItems.TM_149.get(),
						GenerationsItems.TM_189.get(), GenerationsItems.TM_195.get(), GenerationsItems.TM_207.get(),
						GenerationsItems.TM_181.get(), GenerationsItems.TM_199.get(), GenerationsItems.TM_221.get()
				)));

		output.accept(SPORT_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), CobblemonItems.APICOT_BERRY.asItem(), CobblemonItems.ASPEAR_BERRY.asItem(),
						CobblemonItems.ASSAULT_VEST.asItem(), CobblemonItems.BLACK_BELT.asItem(), CobblemonItems.BLACK_GLASSES.asItem(),
						GenerationsItems.BLUE_FLUTE.get(), GenerationsItems.BREAD.get(), CobblemonItems.BRIGHT_POWDER.asItem(),
						CobblemonItems.CHARCOAL.asItem(), CobblemonItems.CHILAN_BERRY.asItem(), CobblemonItems.CHOPLE_BERRY.asItem(),
						GenerationsItems.DIRE_HIT_3.get(), CobblemonItems.DIVE_BALL.asItem(), CobblemonItems.ENERGY_ROOT.asItem(),
						GenerationsItems.EXPERT_BELT.get(), GenerationsItems.FIGHTING_GEM.get(), CobblemonItems.FIRE_STONE.asItem(),
						CobblemonItems.FOCUS_BAND.asItem(), GenerationsItems.FOCUS_SASH.get(), CobblemonItems.HARD_STONE.asItem(),
						CobblemonItems.HEAL_BALL.asItem(), CobblemonItems.HEAVY_DUTY_BOOTS.asItem(), CobblemonItems.IRON.asItem(),
						CobblemonItems.KELPSY_BERRY.asItem(), CobblemonItems.KINGS_ROCK.asItem(), GenerationsItems.LAVA_COOKIE.get(),
						CobblemonItems.LEVEL_BALL.asItem(), CobblemonItems.LIECHI_BERRY.asItem(), GenerationsItems.LUCKY_PUNCH.get(),
						GenerationsItems.MACHO_BRACE.get(), GenerationsItems.MIGHTY_CANDY.get(), CobblemonItems.MUSCLE_BAND.asItem(),
						GenerationsItems.BELLIGERENT_WING.get(), CobblemonItems.OCCA_BERRY.asItem(), GenerationsItems.PINK_SCARF.get(),
						CobblemonItems.POWER_ANKLET.asItem(), CobblemonItems.POWER_BRACER.asItem(), GenerationsItems.ELEGANT_WING.get(),
						GenerationsItems.PROTECTIVE_PADS.get(), CobblemonItems.PROTEIN.asItem(), GenerationsItems.PUNGENT_ROOT.get(),
						CobblemonItems.QUICK_CLAW.asItem(), CobblemonItems.SAFETY_GOGGLES.asItem(), GenerationsItems.SHOAL_SALT.get(),
						GenerationsItems.SMART_CANDY.get(), CobblemonItems.SOFT_SAND.asItem(), GenerationsItems.SPICE_MIX.get(),
						CobblemonItems.SPORT_BALL.asItem(), GenerationsItems.STICKY_BARB.get(), CobblemonItems.SUPER_POTION.asItem(),
						GenerationsItems.TERRAIN_EXTENDER.get(), GenerationsItems.UTILITY_UMBRELLA.get(), GenerationsItems.WHITE_FLUTE.get(),
						GenerationsItems.WIDE_LENS.get(), GenerationsItems.FIGHTING_CANDY.get(), GenerationsItems.X_DEFENSE_3.get(),
						CobblemonItems.X_SP_DEF.asItem(), Items.STRING, Items.GUNPOWDER, Items.SADDLE,
						Items.RABBIT_FOOT, Items.RABBIT_HIDE, Items.MUSIC_DISC_WAIT, GenerationsItems.MARK_CHARM.get(),
						GenerationsItems.ROCKSTAR_COSTUME.get(), GenerationsItems.BELLE_COSTUME.get(), GenerationsItems.POPSTAR_COSTUME.get(),
						GenerationsItems.PHD_COSTUME.get(), GenerationsItems.LIBRE_COSTUME.get(), GenerationsItems.OLD_ROD.get(),
						GenerationsItems.GOOD_ROD.get(), GenerationsItems.SUPER_ROD.get(), GenerationsItems.RUSTY_IRON_KEY_4.get(),
						GenerationsItems.SHATTERED_ICE_KEY_4.get(), GenerationsItems.CRUMBLED_ROCK_KEY_4.get(), GenerationsItems.FRAGMENTED_DRAGO_KEY_4.get(),
						GenerationsItems.DISCHARGED_ELEKI_KEY_4.get(), GenerationsItems.SHATTERED_RELIC_SONG_4.get(), GenerationsItems.FIRE_MEMORY_DRIVE.get(),
						GenerationsItems.FIGHTING_MEMORY_DRIVE.get(), GenerationsItems.FLAME_PLATE.get(), GenerationsItems.FIST_PLATE.get(),
						GenerationsItems.TM_12.get(), GenerationsItems.TM_39.get(), GenerationsItems.TM_58.get(),
						GenerationsItems.TM_64.get(), GenerationsItems.TM_73.get(), GenerationsItems.TM_89.get(),
						GenerationsItems.TM_112.get(), GenerationsItems.TM_134.get(), GenerationsItems.TM_158.get(),
						GenerationsItems.TM_167.get(), GenerationsItems.TM_189.get(), GenerationsItems.TM_195.get(), GenerationsItems.TM_207.get(),
						GenerationsItems.TM_179.get(), GenerationsItems.TM_220.get(), GenerationsItems.TM_184.get(),
						GenerationsItems.TM_192.get(), GenerationsItems.TM_213.get(), GenerationsItems.TM_229.get()
				)));

		output.accept(STRANGE_BALL, LootTable.lootTable()
				.withPool(createPool(ConstantValue.exactly(1),
						CobblemonItems.POKE_BALL.asItem(), CobblemonItems.SURPRISE_MULCH.asItem(), CobblemonItems.GROWTH_MULCH.asItem(),
						CobblemonItems.RICH_MULCH.asItem(), GenerationsItems.SCROLL_PAGE.get(), GenerationsItems.RUSTY_FRAGMENT.get(),
						GenerationsItems.SPARKLING_SHARD.get(), GenerationsItems.HOOPA_RING.get(), GenerationsItems.DRAGON_SOUL.get(),
						GenerationsItems.LEGEND_PLATE.get(), GenerationsItems.GHOST_CANDY.get(), GenerationsItems.PERMIT.get(),
						GenerationsItems.PLASMA_CARD.get(), CobblemonItems.POKE_BALL.asItem(), CobblemonItems.POWER_WEIGHT.asItem(),
						CobblemonItems.PP_UP.asItem(), CobblemonItems.RARE_CANDY.asItem(), GenerationsItems.RESET_URGE.get(),
						GenerationsItems.ROLLER_SKATES.get(), GenerationsItems.ROOM_SERVICE.get(), GenerationsItems.SHALOUR_SABLE.get(),
						GenerationsItems.PRECOOKED_BURGER.get(), GenerationsItems.PUNGENT_ROOT.get(), GenerationsItems.ORB.get(),
						GenerationsItems.MIXED_MUSHROOMS.get(), GenerationsItems.GIGANTAMIX.get(), GenerationsItems.Z_INGOT.get(),
						GenerationsItems.POKEMAIL_RETRO.get(), GenerationsItems.POKEMAIL_RSVP.get(), GenerationsItems.POKEMAIL_SPACE.get(),
						CobblemonItems.ENIGMA_BERRY.asItem(), CobblemonItems.POMEG_BERRY.asItem(), CobblemonItems.YACHE_BERRY.asItem(),
						CobblemonItems.HONDEW_BERRY.asItem(), GenerationsItems.ROCKSTAR_COSTUME.get(), GenerationsItems.BELLE_COSTUME.get(),
						GenerationsItems.POPSTAR_COSTUME.get(), GenerationsItems.PHD_COSTUME.get(), GenerationsItems.LIBRE_COSTUME.get(),
						GenerationsItems.MEWTWO_ARMOR.get(), GenerationsItems.ROTOM_CATALOG.get(), GenerationsItems.PINK_NECTAR.get(),
						GenerationsItems.PURPLE_NECTAR.get(), GenerationsItems.RED_NECTAR.get(), GenerationsItems.YELLOW_NECTAR.get(),
						GenerationsItems.OLD_ROD.get(), GenerationsItems.GOOD_ROD.get(), GenerationsItems.SUPER_ROD.get(),
						GenerationsItems.WAILMER_PAIL.get(), GenerationsItems.SPRINK_LOTAD.get(), GenerationsItems.REPEL.get(),
						GenerationsItems.SUPER_REPEL.get(), GenerationsItems.MAX_REPEL.get(), GenerationsItems.CAMERA.get(),
						GenerationsItems.FILM.get(), GenerationsItems.SNAP_CAMERA.get(), GenerationsItems.FULL_INCENSE.get(),
						GenerationsItems.LAX_INCENSE.get(), GenerationsItems.ODD_INCENSE.get(), GenerationsItems.PURE_INCENSE.get(),
						GenerationsItems.ROCK_INCENSE.get(), GenerationsItems.ROSE_INCENSE.get(), GenerationsItems.SEA_INCENSE.get(),
						GenerationsItems.WAVE_INCENSE.get(), GenerationsItems.TM_17.get(), GenerationsItems.TM_29.get(),
						GenerationsItems.TM_42.get(), GenerationsItems.TM_61.get(), GenerationsItems.TM_114.get(),
						GenerationsItems.TM_151.get(), GenerationsItems.TM_3.get(), GenerationsItems.TM_18.get(),
						GenerationsItems.TM_30.get(), GenerationsItems.TM_43.get(), GenerationsItems.TM_62.get(),
						GenerationsItems.TM_87.get(), GenerationsItems.TM_94.get(), GenerationsItems.TM_108.get(),
						GenerationsItems.TM_140.get(), GenerationsItems.TM_189.get(), GenerationsItems.TM_195.get(), GenerationsItems.TM_207.get(),
						Items.TOTEM_OF_UNDYING, GenerationsItems.ENIGMA_FRAGMENT.get(), GenerationsItems.MEW_FOSSIL.get(),
						GenerationsItems.TM_175.get(), GenerationsItems.TM_214.get(), GenerationsItems.TM_200.get(),
						GenerationsItems.TM_222.get(), GenerationsItems.TM_226.get())
						.add(key("lugia_disc"))
						.add(key("lavender_town_disc"))
						.add(key("mt_pyre_disc"))));

		output.accept(TIMER_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 3),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ABILITY_URGE.get(), GenerationsItems.BLUNDER_POLICY.get(),
						CobblemonItems.BRIGHT_POWDER.asItem(), CobblemonItems.COBA_BERRY.asItem(), GenerationsItems.COCONUT_MILK.get(),
						CobblemonItems.CORNN_BERRY.asItem(), GenerationsItems.DAMP_ROCK.get(), GenerationsItems.DIRE_HIT_2.get(),
						CobblemonItems.DUBIOUS_DISC.asItem(), GenerationsItems.ELECTRIC_SEED.get(), GenerationsItems.ESCAPE_ROPE.get(),
						CobblemonItems.FAST_BALL.asItem(), GenerationsItems.FESTIVAL_TICKET.get(), GenerationsItems.FLUFFY_TAIL.get(),
						GenerationsItems.FRESH_CREAM.get(), CobblemonItems.FULL_RESTORE.asItem(), GenerationsItems.GRIP_CLAW.get(),
						GenerationsItems.GRUBBY_HANKY.get(), CobblemonItems.HEAL_POWDER.asItem(), GenerationsItems.HEALTH_CANDY_L.get(),
						GenerationsItems.HEAT_ROCK.get(), CobblemonItems.IAPAPA_BERRY.asItem(), GenerationsItems.ICY_ROCK.get(),
						CobblemonItems.KASIB_BERRY.asItem(), CobblemonItems.KINGS_ROCK.asItem(), GenerationsItems.LAVA_COOKIE.get(),
						CobblemonItems.LIFE_ORB.asItem(), CobblemonItems.LIGHT_CLAY.asItem(), CobblemonItems.LUCKY_EGG.asItem(),
						CobblemonItems.MAGOST_BERRY.asItem(), CobblemonItems.MAX_REVIVE.asItem(), CobblemonItems.MUSCLE_BAND.asItem(),
						CobblemonItems.NANAB_BERRY.asItem(), CobblemonItems.NOMEL_BERRY.asItem(), GenerationsItems.PEWTER_CRUNCHIES.get(),
						GenerationsItems.PLASMA_CARD.get(), CobblemonItems.POMEG_BERRY.asItem(), CobblemonItems.POWER_HERB.asItem(),
						CobblemonItems.QUICK_CLAW.asItem(), CobblemonItems.RABUTA_BERRY.asItem(), CobblemonItems.REVIVE.asItem(),
						CobblemonItems.SALAC_BERRY.asItem(), GenerationsItems.SALAD_MIX.get(), CobblemonItems.SHARP_BEAK.asItem(),
						GenerationsItems.SILVER_LEAF.get(), GenerationsItems.ORB.get(), GenerationsItems.ROTOM_CATALOG.get(),
						CobblemonItems.SPORT_BALL.asItem(), GenerationsItems.TERRAIN_EXTENDER.get(),
						CobblemonItems.TIMER_BALL.asItem(), GenerationsItems.TIN_OF_BEANS.get(), CobblemonItems.WIKI_BERRY.asItem(),
						CobblemonItems.WISE_GLASSES.asItem(), GenerationsItems.ICE_CANDY.get(), GenerationsItems.X_ACCURACY_2.get(),
						GenerationsItems.X_ATTACK_6.get(), GenerationsItems.X_SPECIAL_ATTACK_3.get(), Items.SADDLE,
						Items.LEATHER, Items.BLAZE_POWDER, Items.MUSIC_DISC_WAIT, GenerationsItems.BASIC_SWEET_POKE_PUFF.get(),
						GenerationsItems.BASIC_MINT_POKE_PUFF.get(), GenerationsItems.BASIC_CITRUS_POKE_PUFF.get(), GenerationsItems.BASIC_MOCHA_POKE_PUFF.get(),
						GenerationsItems.BASIC_SPICE_POKE_PUFF.get(), Items.AMETHYST_SHARD, GenerationsItems.RUBY.get(),
						GenerationsItems.POKEMAIL_ORANGE.get(), GenerationsItems.POKEMAIL_REPLY.get(), GenerationsItems.POKEMAIL_RETRO.get(),
						GenerationsItems.POKEMAIL_RSVP.get(), GenerationsItems.POKEMAIL_SHADOW.get(), GenerationsItems.POKEMAIL_SNOW.get(),
						GenerationsItems.POKEMAIL_SPACE.get(), GenerationsItems.POKEMAIL_STEEL.get(), GenerationsItems.POKEMAIL_THANKS.get(),
						GenerationsItems.POKEMAIL_TROPIC.get(), GenerationsItems.POKEMAIL_TUNNEL.get(), GenerationsItems.POKEMAIL_WAVE.get(),
						GenerationsItems.POKEMAIL_WOOD.get(), CobblemonItems.SURPRISE_MULCH.asItem(), CobblemonItems.GROWTH_MULCH.asItem(), GenerationsItems.TM_172.get(),
						GenerationsItems.TM_183.get(), GenerationsItems.TM_191.get(), GenerationsItems.TM_193.get(), GenerationsItems.TM_202.get(),
						GenerationsItems.TM_203.get(), GenerationsItems.TM_204.get(), GenerationsItems.TM_205.get(),
						GenerationsItems.TM_173.get(), GenerationsItems.TM_210.get(), GenerationsItems.TM_211.get(),
						GenerationsItems.TM_181.get(), GenerationsItems.TM_199.get(), GenerationsItems.TM_221.get(),
						GenerationsItems.TM_178.get(), GenerationsItems.TM_217.get(), GenerationsItems.TM_218.get(),
						GenerationsItems.TM_228.get())
						.add(key("cerulean_city_disc"))
						.add(key("icirrus_city_disc"))
						.add(key("laverre_city_disc"))
						.add(key("surf_disc"))
						.add(key("lake_of_rage_disc"))
						.add(key("penny_disc"))
						.add(key("nemona_disc"))
						.add(key("zinnia_disc"))
						.add(key("cynthia_disc"))
						.add(key("deoxys_disc"))));

		output.accept(ULTRA_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 3),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ABSORB_BULB.get(), GenerationsItems.ADRENALINE_ORB.get(),
						CobblemonItems.AWAKENING.asItem(), GenerationsItems.BALM_MUSHROOM.get(), CobblemonItems.BERRY_SWEET.asItem(),
						CobblemonItems.BLACK_BELT.asItem(), CobblemonItems.BLACK_SLUDGE.asItem(),
						CobblemonItems.BLUK_BERRY.asItem(), GenerationsItems.BLUNDER_POLICY.get(), CobblemonItems.BURN_HEAL.asItem(),
						CobblemonItems.CALCIUM.asItem(), GenerationsItems.CELL_BATTERY.get(), CobblemonItems.CHARCOAL.asItem(),
						CobblemonItems.CHERI_BERRY.asItem(), CobblemonItems.CHERI_BERRY.asItem(), CobblemonItems.CHIPPED_POT.asItem(),
						CobblemonItems.CHOICE_BAND.asItem(), CobblemonItems.CLOVER_SWEET.asItem(), CobblemonItems.CORNN_BERRY.asItem(),
						GenerationsItems.COURAGE_CANDY_L.get(), CobblemonItems.DEEP_SEA_TOOTH.asItem(), CobblemonItems.DIRE_HIT.asItem(),
						CobblemonItems.DRAGON_FANG.asItem(), CobblemonItems.DREAM_BALL.asItem(), CobblemonItems.DUSK_BALL.asItem(),
						GenerationsItems.EJECT_PACK.get(), CobblemonItems.EXPERIENCE_CANDY_L.asItem(), GenerationsItems.EXPERT_BELT.get(),
						CobblemonItems.FAST_BALL.asItem(), CobblemonItems.FLAME_ORB.asItem(), GenerationsItems.FRUIT_BUNCH.get(),
						CobblemonItems.FULL_HEAL.asItem(), CobblemonItems.FULL_RESTORE.asItem(), GenerationsItems.FIERY_WING.get(),
						GenerationsItems.GHOST_GEM.get(), GenerationsItems.GRIP_CLAW.get(), GenerationsItems.GRUBBY_HANKY.get(),
						CobblemonItems.HEAL_BALL.asItem(), GenerationsItems.HEALTH_CANDY_XL.get(), CobblemonItems.HEAVY_DUTY_BOOTS.asItem(),
						CobblemonItems.KEE_BERRY.asItem(), CobblemonItems.KELPSY_BERRY.asItem(), GenerationsItems.LOOKER_TICKET.get(),
						CobblemonItems.MAGNET.asItem(), CobblemonItems.MAX_ELIXIR.asItem(), CobblemonItems.MAX_REVIVE.asItem(),
						GenerationsItems.MIGHTY_CANDY_XL.get(), CobblemonItems.NEVER_MELT_ICE.asItem(), GenerationsItems.NORMAL_GEM.get(),
						GenerationsItems.PERMIT.get(), GenerationsItems.PLASMA_CARD.get(), CobblemonItems.POKE_BALL.asItem(),
						CobblemonItems.POWER_WEIGHT.asItem(), CobblemonItems.PP_UP.asItem(), CobblemonItems.RARE_CANDY.asItem(),
						GenerationsItems.RESET_URGE.get(), GenerationsItems.ROLLER_SKATES.get(), GenerationsItems.ROOM_SERVICE.get(),
						GenerationsItems.SHALOUR_SABLE.get(), CobblemonItems.SHARP_BEAK.asItem(), CobblemonItems.SOFT_SAND.asItem(),
						GenerationsItems.SOUL_DEW.get(), GenerationsItems.STARDUST.get(), CobblemonItems.SUPER_POTION.asItem(),
						GenerationsItems.THROAT_SPRAY.get(), GenerationsItems.TOUGH_CANDY_L.get(), CobblemonItems.ULTRA_BALL.asItem(),
						GenerationsItems.WEAKNESS_POLICY.get(), GenerationsItems.WHITE_FLUTE.get(), GenerationsItems.X_ACCURACY_6.get(),
						GenerationsItems.X_SPECIAL_ATTACK_6.get(), GenerationsItems.X_SPECIAL_DEFENSE_2.get(), Items.SADDLE,
						Items.LEATHER, Items.BLAZE_POWDER, Items.MUSIC_DISC_WAIT, GenerationsItems.BASIC_SWEET_POKE_PUFF.get(),
						GenerationsItems.BASIC_MINT_POKE_PUFF.get(), GenerationsItems.BASIC_CITRUS_POKE_PUFF.get(), GenerationsItems.BASIC_MOCHA_POKE_PUFF.get(),
						GenerationsItems.BASIC_SPICE_POKE_PUFF.get(), Items.AMETHYST_SHARD, GenerationsItems.RUBY.get(),
						GenerationsItems.POKEMAIL_ORANGE.get(), GenerationsItems.POKEMAIL_REPLY.get(), GenerationsItems.POKEMAIL_RETRO.get(),
						GenerationsItems.POKEMAIL_RSVP.get(), GenerationsItems.POKEMAIL_SHADOW.get(), GenerationsItems.POKEMAIL_SNOW.get(),
						GenerationsItems.POKEMAIL_SPACE.get(), GenerationsItems.POKEMAIL_STEEL.get(), GenerationsItems.POKEMAIL_THANKS.get(),
						GenerationsItems.POKEMAIL_TROPIC.get(), GenerationsItems.POKEMAIL_TUNNEL.get(), GenerationsItems.POKEMAIL_WAVE.get(),
						GenerationsItems.POKEMAIL_WOOD.get(), CobblemonItems.SURPRISE_MULCH.asItem(), CobblemonItems.GROWTH_MULCH.asItem(),
						GenerationsItems.TM_173.get(), GenerationsItems.TM_210.get(), GenerationsItems.TM_211.get(),
						GenerationsItems.TM_188.get(), GenerationsItems.TM_196.get(), GenerationsItems.TM_208.get(), GenerationsItems.TM_209.get(),
						GenerationsItems.TM_197.get(), GenerationsItems.TM_216.get(),
						GenerationsItems.TM_181.get(), GenerationsItems.TM_199.get(), GenerationsItems.TM_221.get(),
						GenerationsItems.TM_175.get(), GenerationsItems.TM_214.get(), GenerationsItems.TM_200.get(),
						GenerationsItems.TM_222.get(), GenerationsItems.TM_226.get())
						.add(key("iris_disc"))
						.add(key("nessa_disc"))
						.add(key("team_rocket_disc"))
						.add(key("sada_and_turo_disc"))
						.add(key("south_province_disc"))
						.add(key("rival_disc"))
						.add(key("lusamine_disc"))
						.add(key("xy_legendary_disc"))
						.add(key("kanto_disc"))
						.add(key("ultra_necrozma_disc"))));

		output.accept(WING_BALL, LootTable.lootTable()
				.withPool(createPool(UniformGenerator.between(1, 2),
						CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ICY_WING.get(), GenerationsItems.ELEGANT_WING.get(),
						GenerationsItems.HEALTH_FEATHER.get(), GenerationsItems.STATIC_WING.get(), GenerationsItems.BELLIGERENT_WING.get(),
						GenerationsItems.FIERY_WING.get(), GenerationsItems.SINISTER_WING.get(), GenerationsItems.SILVER_WING.get(),
						GenerationsItems.RAINBOW_WING.get(), CobblemonItems.COBA_BERRY.asItem(), GenerationsItems.FLYING_GEM.get(),
						GenerationsItems.FLYING_MEMORY_DRIVE.get(), GenerationsItems.IRON_BALL.get(),
						CobblemonItems.SHARP_BEAK.asItem(), GenerationsItems.SKY_PLATE.get(), GenerationsItems.UTILITY_UMBRELLA.get(),
						GenerationsItems.AIR_BALLOON.get(), CobblemonItems.GREPA_BERRY.asItem(), CobblemonItems.LANSAT_BERRY.asItem(),
						CobblemonItems.LUM_BERRY.asItem(), GenerationsItems.LIGHT_BALL.get(), GenerationsItems.PINK_NECTAR.get(),
						GenerationsItems.PURPLE_NECTAR.get(), GenerationsItems.RED_NECTAR.get(), GenerationsItems.YELLOW_NECTAR.get(),
						CobblemonItems.POWER_HERB.asItem(), CobblemonItems.MENTAL_HERB.asItem(), CobblemonItems.MIRROR_HERB.asItem(),
						CobblemonItems.WHITE_HERB.asItem(), GenerationsItems.ICY_ROCK.get(), GenerationsItems.HEAT_ROCK.get(),
						CobblemonItems.ASSAULT_VEST.asItem(), GenerationsItems.ELECTRIC_SEED.get(), GenerationsItems.GRASSY_SEED.get(),
						GenerationsItems.MISTY_SEED.get(), GenerationsItems.PSYCHIC_SEED.get(), CobblemonItems.MIRACLE_SEED.asItem(),
						CobblemonItems.ANTIDOTE.asItem(), CobblemonItems.MAX_ELIXIR.asItem(), CobblemonItems.HYPER_POTION.asItem(),
						GenerationsItems.MOOMOO_MILK.get(), CobblemonItems.GREAT_BALL.asItem(), GenerationsItems.FRESH_WATER.get(),
						GenerationsItems.SODA_POP.get(), CobblemonItems.POTION.asItem(), CobblemonItems.CALCIUM.asItem(),
						GenerationsItems.HEALTH_CANDY.get(), GenerationsItems.QUICK_CANDY.get(), GenerationsItems.SMART_CANDY.get(),
						GenerationsItems.SILVER_LEAF.get(), GenerationsItems.GOLD_LEAF.get(), GenerationsItems.HEART_SCALE.get(),
						GenerationsItems.STARDUST.get(), GenerationsItems.POKEMAIL_HARBOR.get(), GenerationsItems.POKEMAIL_INQUIRY.get(),
						GenerationsItems.POKEMAIL_TROPIC.get(), GenerationsItems.POKEMAIL_AIR.get(), GenerationsItems.POKEMAIL_REPLY.get(),
						GenerationsItems.POKEMAIL_SPACE.get(), CobblemonItems.CHOICE_BAND.asItem(), GenerationsItems.COMET_SHARD.get(),
						CobblemonItems.DESTINY_KNOT.asItem(), GenerationsItems.BOILED_EGG.get(), GenerationsItems.SAUSAGES.get(),
						GenerationsItems.BOBS_FOOD_TIN.get(), GenerationsItems.BACHS_FOOD_TIN.get(), GenerationsItems.GRIP_CLAW.get(),
						GenerationsItems.REPEL.get(), GenerationsItems.SUPER_REPEL.get(), CobblemonItems.EXPERIENCE_CANDY_XS.asItem(),
						CobblemonItems.EXPERIENCE_CANDY_S.asItem(), GenerationsItems.FLYING_CANDY.get(), GenerationsItems.X_SPEED_2.get(),
						CobblemonItems.X_SPEED.asItem(), GenerationsItems.X_ACCURACY_6.get(), CobblemonItems.X_ATTACK.asItem(),
						GenerationsItems.TM_14.get(), GenerationsItems.TM_27.get(), GenerationsItems.TM_40.get(),
						GenerationsItems.TM_65.get(), GenerationsItems.TM_97.get(), GenerationsItems.TM_113.get(),
						GenerationsItems.TM_160.get(), GenerationsItems.TM_164.get(),
						GenerationsItems.TM_197.get(), GenerationsItems.TM_216.get())
						.add(key("cerulean_city_disc"))
						.add(key("icirrus_city_disc"))
						.add(key("laverre_city_disc"))
						.add(key("surf_disc"))
						.add(key("lake_of_rage_disc"))
						.add(key("penny_disc"))
						.add(key("nemona_disc"))
						.add(key("zinnia_disc"))
						.add(key("cynthia_disc"))
						.add(key("deoxys_disc"))));
	}

	private LootPool.Builder createPool(NumberProvider rolls, Item... items) {
		LootPool.Builder pool = LootPool.lootPool().setRolls(rolls);
		for (Item item : items)
			pool.add(LootItem.lootTableItem(item));

		return pool;
	}

	public static LootPoolSingletonContainer.Builder<?> key(String key) {
		return ResouceKeyEntry.resourceKeyContents(ResourceKey.create(Registries.ITEM, new ResourceLocation("generations_music", key)));
	}
}
