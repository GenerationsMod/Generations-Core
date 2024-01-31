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

	}

	private LootPool.Builder createPool(NumberProvider rolls, Item... items) {
		LootPool.Builder pool = LootPool.lootPool().setRolls(rolls);
		for (var item : items)
			pool.add(LootItem.lootTableItem(item));

		return pool;
	}
}
