package generations.gg.generations.core.generationscore.forge.datagen.generators.loot;

import com.cobblemon.mod.common.CobblemonItems;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public class GenerationsChestLoot implements LootTableSubProvider {
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
	private static final ResourceLocation TIMER_BALL = GenerationsCore.id("chests/timer_ball");
	private static final ResourceLocation ULTRA_BALL = GenerationsCore.id("chests/ultra_ball");
	private static final ResourceLocation WING_BALL = GenerationsCore.id("chests/wing_ball");
	@Override
	public void generate(@NotNull BiConsumer<ResourceLocation, LootTable.Builder> output) {
		//output.accept(BuiltInLootTables.ABANDONED_MINESHAFT, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).name(GenerationsCore.id("chests/heavy_ball").toString())));
		output.accept(BEAST_BALL, LootTable.lootTable().withPool(createPool(UniformGenerator.between(1.0F, 3.0F),
				CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ANTIDOTE.get(), CobblemonItems.ASSAULT_VEST.asItem(),
				CobblemonItems.BABIRI_BERRY.asItem(), CobblemonItems.BEAST_BALL.asItem(), GenerationsItems.BIG_NUGGET.get(),
				CobblemonItems.BLACK_SLUDGE.asItem(), GenerationsItems.BRIGHT_POWDER.get(), GenerationsItems.BRITTLE_BONES.get(),
				GenerationsItems.CELL_BATTERY.get(), GenerationsItems.CHALKY_STONE.get(), GenerationsItems.CHARCOAL.get(),
				CobblemonItems.CHARTI_BERRY.asItem(), CobblemonItems.CHOICE_BAND.asItem(), CobblemonItems.CHOICE_SPECS.asItem(),
				CobblemonItems.CHOPLE_BERRY.asItem(), CobblemonItems.COLBUR_BERRY.asItem(), GenerationsItems.COMET_SHARD.get(),
				GenerationsItems.COURAGE_CANDY_XL.get(), GenerationsItems.DNA_SPLICERS.get(), GenerationsItems.DRAGON_GEM.get(),
				GenerationsItems.DYNAMAX_CANDY.get(), CobblemonItems.ENIGMA_BERRY.asItem(), CobblemonItems.EXPERIENCE_CANDY_XL.asItem(),
				GenerationsItems.FAIRY_GEM.get(), GenerationsItems.FIRE_GEM.get(), CobblemonItems.FLAME_ORB.asItem(),
				GenerationsItems.FULL_HEAL.get(), CobblemonItems.GANLON_BERRY.asItem(), GenerationsItems.GIGANTAMIX.get(),
				CobblemonItems.HEAL_POWDER.asItem(), CobblemonItems.JABOCA_BERRY.asItem(), GenerationsItems.LAVA_COOKIE.get(),
				GenerationsItems.PERMIT.get(), CobblemonItems.POMEG_BERRY.asItem(), GenerationsItems.POWER_BELT.get(),
				CobblemonItems.RAWST_BERRY.asItem(), GenerationsItems.ROCK_INCENSE.get(), GenerationsItems.SEA_INCENSE.get(),
				GenerationsItems.SMART_CANDY_L.get(), GenerationsItems.SODA_POP.get(), GenerationsItems.STRANGE_SOUVENIR.get(),
				GenerationsItems.WEAKNESS_POLICY.get(), CobblemonItems.WEPEAR_BERRY.asItem(), GenerationsItems.WIDE_LENS.get(),
				GenerationsItems.X_SPECIAL_DEFENSE_2.get(), GenerationsItems.X_SPEED.get(), GenerationsItems.X_SPEED_6.get(),
				CobblemonItems.YACHE_BERRY.asItem(), GenerationsItems.YELLOW_NECTAR.get(), GenerationsItems.DRAGON_CANDY.get(),
				GenerationsItems.YELLOW_SHARD.get(), CobblemonItems.ZINC.asItem(), GenerationsItems.ZOOM_LENS.get(),
				Items.PURPUR_BLOCK, Items.END_STONE, Items.PRISMARINE, Items.GLOWSTONE_DUST, Items.NETHER_WART,
				Items.SPIDER_EYE, Items.WARPED_FUNGUS, Items.MUSIC_DISC_CAT, GenerationsItems.Z_INGOT.get(),
				Items.AMETHYST_SHARD, GenerationsItems.RUBY.get(), GenerationsItems.LAVA_CRYSTAL.get(),
				GenerationsItems.BELLIGERENT_WING.get(), GenerationsItems.LUGIA_DISC.get(), GenerationsItems.LAVENDER_TOWN_DISC.get(),
				GenerationsItems.MT_PYRE_DISC.get(), GenerationsItems.TM_44.get(), GenerationsItems.TM_78.get(),
				GenerationsItems.TM_100.get(), GenerationsItems.TM_115.get(), GenerationsItems.TM_156.get(),
				GenerationsItems.TM_169.get(), CobblemonItems.SURPRISE_MULCH.asItem(), CobblemonItems.GROWTH_MULCH.asItem(),
				CobblemonItems.RICH_MULCH.asItem())));


		output.accept(CHERISH_BALL, LootTable.lootTable().withPool(createPool(UniformGenerator.between(1.0F, 2.0F),
				CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ABILITY_PATCH.get(), GenerationsItems.AMULET_COIN.get(),
				GenerationsItems.ANTIDOTE.get(), GenerationsItems.BACHS_FOOD_TIN.get(), GenerationsItems.BEACH_GLASS.get(),
				CobblemonItems.BERRY_JUICE.asItem(), CobblemonItems.BERRY_SWEET.asItem(), GenerationsItems.BLACK_FLUTE.get(),
				GenerationsItems.BOILED_EGG.get(), GenerationsItems.BREAD.get(), CobblemonItems.CALCIUM.asItem(),
				CobblemonItems.CHERISH_BALL.asItem(), CobblemonItems.CHIPPED_POT.asItem(), GenerationsItems.DARK_GEM.get(),
				GenerationsItems.DISCOUNT_COUPON.get(), CobblemonItems.ELECTIRIZER.asItem(), GenerationsItems.ELECTRIC_GEM.get(),
				GenerationsItems.EVERSTONE.get(), CobblemonItems.EXPERIENCE_CANDY_M.asItem(), GenerationsItems.FIRE_GEM.get(),
				CobblemonItems.FLAME_ORB.asItem(), CobblemonItems.FLOWER_SWEET.asItem(), GenerationsItems.FRIED_FOOD.get(),
				CobblemonItems.FRIEND_BALL.asItem(), GenerationsItems.FRUIT_BUNCH.get(), GenerationsItems.GALARICA_TWIG.get(),
				CobblemonItems.GALARICA_WREATH.asItem(), GenerationsItems.HEART_SCALE.get(), CobblemonItems.HONDEW_BERRY.asItem(),
				GenerationsItems.HONEY.get(), CobblemonItems.HP_UP.asItem(), GenerationsItems.ICE_GEM.get(),
				CobblemonItems.JABOCA_BERRY.asItem(), GenerationsItems.LAX_INCENSE.get(), GenerationsItems.LIGHT_BALL.get(), CobblemonItems.LOVE_BALL.asItem(),
				CobblemonItems.LOVE_SWEET.asItem(), GenerationsItems.METRONOME.get(), GenerationsItems.MIGHTY_CANDY_L.get(),
				CobblemonItems.MOON_BALL.asItem(), CobblemonItems.PECHA_BERRY.asItem(), CobblemonItems.PERSIM_BERRY.asItem(),
				GenerationsItems.PINK_SCARF.get(), CobblemonItems.POKE_BALL.asItem(), GenerationsItems.POTION.get(),
				GenerationsItems.POWER_BELT.get(), GenerationsItems.PURE_INCENSE.get(), GenerationsItems.RED_FLUTE.get(),
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
				GenerationsItems.TM_129.get(), GenerationsItems.TM_138.get(), GenerationsItems.TM_161.get())));

		//output.accept(BuiltInLootTables.RUINED_PORTAL, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))).withPool(CHERISH_BALL));

		output.accept(DIVE_BALL, LootTable.lootTable().withPool(createPool(UniformGenerator.between(1.0F, 2.0F),
				CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ABSORB_BULB.get(), GenerationsItems.BEACH_GLASS.get(),
				GenerationsItems.BLUE_SHARD.get(), GenerationsItems.DAMP_ROCK.get(), CobblemonItems.DEEP_SEA_SCALE.asItem(),
				CobblemonItems.DEEP_SEA_TOOTH.asItem(), CobblemonItems.DIVE_BALL.asItem(), GenerationsItems.HEALTH_CANDY.get(),
				GenerationsItems.ICE_GEM.get(), GenerationsItems.ICE_HEAL.get(), CobblemonItems.ICE_STONE.asItem(),
				GenerationsItems.ICY_ROCK.get(), CobblemonItems.LANSAT_BERRY.asItem(), GenerationsItems.LARGE_LEEK.get(),
				GenerationsItems.LAX_INCENSE.get(), CobblemonItems.LEPPA_BERRY.asItem(), CobblemonItems.LEVEL_BALL.asItem(),
				CobblemonItems.LIGHT_CLAY.asItem(), GenerationsItems.LUCKY_PUNCH.get(), CobblemonItems.LUM_BERRY.asItem(),
				GenerationsItems.LUMINOUS_MOSS.get(), GenerationsItems.LUMIOSE_GALETTE.get(), GenerationsItems.LURE.get(),
				CobblemonItems.LURE_BALL.asItem(), CobblemonItems.LUXURY_BALL.asItem(), CobblemonItems.MAGO_BERRY.asItem(),
				GenerationsItems.MARBLE.get(), GenerationsItems.MAX_LURE.get(), GenerationsItems.MAX_POTION.get(),
				GenerationsItems.MIGHTY_CANDY_L.get(), CobblemonItems.MOON_STONE.asItem(), CobblemonItems.MYSTIC_WATER.asItem(),
				CobblemonItems.NET_BALL.asItem(), GenerationsItems.PARALYZE_HEAL.get(), GenerationsItems.PASS_ORB.get(),
				CobblemonItems.PASSHO_BERRY.asItem(), CobblemonItems.PERSIM_BERRY.asItem(), CobblemonItems.PINAP_BERRY.asItem(),
				GenerationsItems.POKE_DOLL.get(), GenerationsItems.POTION.get(), GenerationsItems.POWER_BELT.get(),
				CobblemonItems.PRISM_SCALE.asItem(), GenerationsItems.PUNGENT_ROOT.get(), GenerationsItems.PURE_INCENSE.get(),
				CobblemonItems.RABUTA_BERRY.asItem(), GenerationsItems.RELIC_GOLD.get(), GenerationsItems.SEA_INCENSE.get(),
				GenerationsItems.SHOAL_SALT.get(), GenerationsItems.SHOAL_SHELL.get(), GenerationsItems.SMOKED_POKE_TAIL.get(),
				GenerationsItems.SMOOTH_ROCK.get(), CobblemonItems.SOFT_SAND.asItem(), GenerationsItems.SPRINK_LOTAD.get(),
				GenerationsItems.STRETCHY_SPRING.get(), GenerationsItems.SUPER_LURE.get(), CobblemonItems.TANGA_BERRY.asItem(),
				GenerationsItems.TOUGH_CANDY_XL.get(), GenerationsItems.TROPICAL_SHELL.get(), CobblemonItems.WACAN_BERRY.asItem(),
				GenerationsItems.WATER_GEM.get(), CobblemonItems.WATMEL_BERRY.asItem(), GenerationsItems.WAVE_INCENSE.get(),
				CobblemonItems.YACHE_BERRY.asItem(), Items.SPONGE, Items.PRISMARINE, Items.SCUTE, Items.INK_SAC,
				Items.PRISMARINE_SHARD, Items.PRISMARINE_CRYSTALS, Items.MUSIC_DISC_BLOCKS,
				GenerationsItems.OLD_ROD.get(), GenerationsItems.GOOD_ROD.get(), GenerationsItems.SUPER_ROD.get(),
				GenerationsItems.WAILMER_PAIL.get(), GenerationsItems.LAVA_CRYSTAL.get(), CobblemonItems.ADAMANT_MINT.asItem(),
				CobblemonItems.NAUGHTY_MINT.asItem(), CobblemonItems.BOLD_MINT.asItem(), GenerationsItems.WATER_MEMORY_DRIVE.get(),
				GenerationsItems.SPLASH_PLATE.get(), GenerationsItems.POKEMAIL_AIR.get(), GenerationsItems.POKEMAIL_BLOOM.get(),
				GenerationsItems.POKEMAIL_BRICK.get(), GenerationsItems.POKEMAIL_BRIDGE_D.get(), GenerationsItems.POKEMAIL_BRIDGE_M.get(),
				GenerationsItems.POKEMAIL_BRIDGE_S.get(), GenerationsItems.POKEMAIL_BRIDGE_T.get(), GenerationsItems.POKEMAIL_BRIDGE_V.get(),
				GenerationsItems.WATER_CANDY.get(), GenerationsItems.SHATTERED_ICE_KEY_1.get(), GenerationsItems.SHATTERED_ICE_KEY_2.get(),
				GenerationsItems.SHATTERED_ICE_KEY_3.get(), GenerationsItems.SHATTERED_ICE_KEY_4.get(), GenerationsItems.TM_11.get(),
				GenerationsItems.TM_22.get(), GenerationsItems.TM_50.get(), GenerationsItems.TM_77.get(), GenerationsItems.TM_110.get(),
				GenerationsItems.TM_123.get(), GenerationsItems.TM_142.get(), GenerationsItems.TM_145.get(), GenerationsItems.TM_154.get()
		)));

		output.accept(DREAM_BALL, LootTable.lootTable().withPool(createPool(UniformGenerator.between(1.0F, 2.0F),
				CobblemonItems.POKE_BALL.asItem(), GenerationsItems.AWAKENING.get(), CobblemonItems.BIG_ROOT.asItem(),
				GenerationsItems.CELL_BATTERY.get(), GenerationsItems.CHALKY_STONE.get(), CobblemonItems.CHESTO_BERRY.asItem(),
				CobblemonItems.CLOVER_SWEET.asItem(), GenerationsItems.COURAGE_CANDY.get(), CobblemonItems.CRACKED_POT.asItem(),
				GenerationsItems.DRAGON_GEM.get(), CobblemonItems.DREAM_BALL.asItem(), CobblemonItems.DUSK_BALL.asItem(),
				CobblemonItems.EXPERIENCE_CANDY_L.asItem(), GenerationsItems.FAIRY_GEM.get(), GenerationsItems.FRESH_CREAM.get(),
				GenerationsItems.FRIED_FOOD.get(), CobblemonItems.IAPAPA_BERRY.asItem(), CobblemonItems.KEBIA_BERRY.asItem(),
				GenerationsItems.LARGE_LEEK.get(), GenerationsItems.LEEK.get(), GenerationsItems.LEMONADE.get(),
				GenerationsItems.LIGHT_BALL.get(), GenerationsItems.LOOKER_TICKET.get(), GenerationsItems.MARBLE.get(),
				CobblemonItems.MYSTIC_WATER.asItem(), GenerationsItems.NORMAL_GEM.get(), GenerationsItems.ODD_INCENSE.get(),
				GenerationsItems.ODD_KEYSTONE.get(), GenerationsItems.PARALYZE_HEAL.get(), CobblemonItems.PAYAPA_BERRY.asItem(),
				CobblemonItems.PERSIM_BERRY.asItem(), GenerationsItems.POISON_GEM.get(), GenerationsItems.POLISHED_MUD_BALL.get(),
				GenerationsItems.POWER_LENS.get(), GenerationsItems.PSYCHIC_GEM.get(), GenerationsItems.RAGE_CANDY_BAR.get(),
				CobblemonItems.RAZOR_CLAW.asItem(), CobblemonItems.RAZOR_FANG.asItem(), GenerationsItems.RED_CARD.get(),
				GenerationsItems.RED_NECTAR.get(), GenerationsItems.RED_SCARF.get(), GenerationsItems.RED_SHARD.get(),
				GenerationsItems.RELIC_BAND.get(), GenerationsItems.ICY_WING.get(), GenerationsItems.ROOM_SERVICE.get(),
				CobblemonItems.ROSELI_BERRY.asItem(), GenerationsItems.SCOPE_LENS.get(), CobblemonItems.SILK_SCARF.asItem(),
				CobblemonItems.SITRUS_BERRY.asItem(), GenerationsItems.SMART_CANDY_XL.get(), GenerationsItems.SNOWBALL.get(),
				GenerationsItems.SODA_POP.get(), GenerationsItems.SOUL_DEW.get(), GenerationsItems.STAR_PIECE.get(),
				GenerationsItems.STARDUST.get(), GenerationsItems.THROAT_SPRAY.get(), CobblemonItems.THUNDER_STONE.asItem(),
				CobblemonItems.TWISTED_SPOON.asItem(), CobblemonItems.UPGRADE.asItem(), CobblemonItems.WHIPPED_DREAM.asItem(),
				GenerationsItems.WIDE_LENS.get(), GenerationsItems.X_ACCURACY.get(), GenerationsItems.X_DEFENSE_6.get(),
				GenerationsItems.X_SPECIAL_ATTACK_2.get(), Items.PURPUR_BLOCK, Items.SLIME_BALL, Items.GHAST_TEAR,
				Items.CRIMSON_FUNGUS, Items.MUSIC_DISC_BLOCKS, GenerationsItems.MARK_CHARM.get(),
				GenerationsItems.RUSTY_IRON_KEY_2.get(), GenerationsItems.SHATTERED_ICE_KEY_2.get(),
				GenerationsItems.CRUMBLED_ROCK_KEY_2.get(), GenerationsItems.FRAGMENTED_DRAGO_KEY_2.get(),
				GenerationsItems.DISCHARGED_ELEKI_KEY_2.get(), GenerationsItems.SHATTERED_RELIC_SONG_2.get(),
				GenerationsItems.LAVA_CRYSTAL.get(), GenerationsItems.LUGIA_DISC.get(), GenerationsItems.LAVENDER_TOWN_DISC.get(),
				GenerationsItems.MT_PYRE_DISC.get(), CobblemonItems.RELAXED_MINT.asItem(), CobblemonItems.IMPISH_MINT.asItem(),
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
		)));


	}

	private LootPool.Builder createPool(NumberProvider rolls, Item... items) {
		LootPool.Builder pool = LootPool.lootPool().setRolls(rolls);
		for (var item : items)
			pool.add(LootItem.lootTableItem(item));

		return pool;
	}
}
