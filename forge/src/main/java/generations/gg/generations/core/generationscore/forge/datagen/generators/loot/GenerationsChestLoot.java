package generations.gg.generations.core.generationscore.forge.datagen.generators.loot;

import com.cobblemon.mod.common.CobblemonItems;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public class GenerationsChestLoot implements LootTableSubProvider {
	private static final ResourceLocation BEAST_BALL = GenerationsCore.id("chests/beast_ball");
	@Override
	public void generate(@NotNull BiConsumer<ResourceLocation, LootTable.Builder> output) {
		//output.accept(BuiltInLootTables.ABANDONED_MINESHAFT, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).name(GenerationsCore.id("chests/heavy_ball").toString())));
		output.accept(BEAST_BALL, LootTable.lootTable().withPool(createPool(UniformGenerator.between(1.0F, 3.0F),
				CobblemonItems.POKE_BALL.asItem(), GenerationsItems.ANTIDOTE.get(), CobblemonItems.ASSAULT_VEST.asItem(), CobblemonItems.BABIRI_BERRY.asItem(),
				CobblemonItems.BEAST_BALL.asItem(), GenerationsItems.BIG_NUGGET.get(), CobblemonItems.BLACK_SLUDGE.asItem(), GenerationsItems.BRIGHT_POWDER.get(),
				GenerationsItems.BRITTLE_BONES.get(), GenerationsItems.CELL_BATTERY.get(), GenerationsItems.CHALKY_STONE.get(), GenerationsItems.CHARCOAL.get(),
				CobblemonItems.CHARTI_BERRY.asItem(), CobblemonItems.CHOICE_BAND.asItem(), CobblemonItems.CHOICE_SPECS.asItem(), CobblemonItems.CHOPLE_BERRY.asItem(),
				GenerationsItems.COMET_SHARD.get(), GenerationsItems.COURAGE_CANDY_XL.get(), GenerationsItems.DNA_SPLICERS.get(), GenerationsItems.DRAGON_GEM.get(),
				GenerationsItems.DYNAMAX_CANDY.get(), CobblemonItems.ENIGMA_BERRY.asItem(), CobblemonItems.EXPERIENCE_CANDY_XL.asItem(), GenerationsItems.FAIRY_GEM.get(),
				GenerationsItems.FIRE_GEM.get(), CobblemonItems.FLAME_ORB.asItem(), GenerationsItems.FULL_HEAL.get(), CobblemonItems.GANLON_BERRY.asItem(),
				GenerationsItems.GIGANTAMIX.get(), CobblemonItems.HEAL_POWDER.asItem(), CobblemonItems.JABOCA_BERRY.asItem(), GenerationsItems.LAVA_COOKIE.get(),
				GenerationsItems.PERMIT.get(), CobblemonItems.POMEG_BERRY.asItem(), GenerationsItems.POWER_BELT.get(), CobblemonItems.RAWST_BERRY.asItem(),
				GenerationsItems.ROCK_INCENSE.get(), GenerationsItems.SEA_INCENSE.get(), GenerationsItems.SMART_CANDY_L.get(), GenerationsItems.SODA_POP.get(),
				GenerationsItems.STRANGE_SOUVENIR.get(), GenerationsItems.WEAKNESS_POLICY.get(), CobblemonItems.WEPEAR_BERRY.asItem(), GenerationsItems.WIDE_LENS.get())));
	}

	private LootPool.Builder createPool(NumberProvider rolls, Item... items) {
		LootPool.Builder pool = LootPool.lootPool().setRolls(rolls);
		for (var item : items)
			pool.add(LootItem.lootTableItem(item));

		return pool;
	}
}
