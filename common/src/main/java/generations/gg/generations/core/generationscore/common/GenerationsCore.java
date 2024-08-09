/**
 * The Main Class of the Generations-Core mod. (Common)
 * Registers the mod's items and blocks with Minecraft using Architectury.
 * @author Joseph T. McQuigg
 *
 * CopyRight (c) 2023 Generations-Mod
 */

package generations.gg.generations.core.generationscore.common;

import com.cobblemon.mod.common.api.data.DataProvider;
import com.cobblemon.mod.common.api.spawning.detail.SpawnDetail;
import com.cobblemon.mod.common.api.storage.player.PlayerDataExtensionRegistry;
import com.mojang.logging.LogUtils;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import generations.gg.generations.core.generationscore.common.api.data.GenerationsCoreEntityDataSerializers;
import generations.gg.generations.core.generationscore.common.api.events.general.EntityEvents;
import generations.gg.generations.core.generationscore.common.api.player.AccountInfo;
import generations.gg.generations.core.generationscore.common.api.player.BiomesVisited;
import generations.gg.generations.core.generationscore.common.api.player.Caught;
import generations.gg.generations.core.generationscore.common.config.Config;
import generations.gg.generations.core.generationscore.common.config.ConfigLoader;
import generations.gg.generations.core.generationscore.common.world.container.GenerationsContainers;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.AbstractNodeTypes;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.spawning.LocationLogicTypes;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.spawning.YawLogicTypes;
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.common.world.entity.ZygardeCellEntity;
import generations.gg.generations.core.generationscore.common.world.item.*;
import generations.gg.generations.core.generationscore.common.world.item.creativetab.GenerationsCreativeTabs;
import generations.gg.generations.core.generationscore.common.world.item.legends.EnchantableItem;
import generations.gg.generations.core.generationscore.common.world.level.block.*;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.loot.LootPoolEntryTypes;
import generations.gg.generations.core.generationscore.common.world.recipe.*;
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds;
import generations.gg.generations.core.generationscore.common.world.spawning.ZygardeCellDetail;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.PackType;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;
import org.slf4j.Logger;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * The Main Class of the Generations-Core mod. (Common)
 * Registers the mod's items and blocks with Minecraft using Architectury.
 * @author Joseph T. McQuigg, WaterPicker
 */
public class GenerationsCore
{

	/** The mod id of the Generations-Core mod. */
	public static final String MOD_ID = "generations_core";

	/** The logger for the Generations-Core mod. */
	public static final Logger LOGGER = LogUtils.getLogger();

	/** The config for the Generations-Core mod. */
	public static Config CONFIG;
	public static GenerationsImplementation implementation;

	public static DataProvider dataProvider = GenerationsDataProvider.INSTANCE;

	/**
	 * Initializes the Generations-Core mod.
	 */
	public static void init(GenerationsImplementation implementation) {
		CONFIG = ConfigLoader.loadConfig(Config.class, "core", "main");
		GenerationsCore.implementation = implementation;

		SpawnDetail.Companion.registerSpawnType(ZygardeCellDetail.TYPE, ZygardeCellDetail.class);

		GenerationsCoreEntityDataSerializers.init();
		GenerationsSounds.init();
		GenerationsBlocks.init();
		GenerationsPokeDolls.init();
		GenerationsWood.init();
		GenerationsOres.init();
		GenerationsDecorationBlocks.init();
		LootPoolEntryTypes.init();
		GenerationsUtilityBlocks.init();
		GenerationsShrines.init();
		GenerationsItems.init();
		GenerationsBlockEntities.init();
		GenerationsEntities.init();
		GenerationsCreativeTabs.init();
		GenerationsArmor.init();
		GenerationsTools.init();
		GenerationsPaintings.init();
		GenerationsContainers.init();
		YawLogicTypes.init();
		LocationLogicTypes.init();
		AbstractNodeTypes.init();
		RksResultType.init();
		initRecipes();
		GenerationsCoreRecipeTypes.init();
		GenerationsCoreRecipeSerializers.init();
		GenerationsCoreStats.init();

		GenerationsDataProvider.INSTANCE.registerDefaults();

		PlayerDataExtensionRegistry.INSTANCE.register(AccountInfo.KEY, AccountInfo.class, false);
		PlayerDataExtensionRegistry.INSTANCE.register(Caught.KEY, Caught.class, false);
		PlayerDataExtensionRegistry.INSTANCE.register(BiomesVisited.KEY, BiomesVisited.class, false);

		GenerationsCobblemonEvents.init();
		EntityEvents.JUMP.register(entity -> {
			if (entity instanceof ServerPlayer player)
				if (entity.level().getBlockState(entity.blockPosition()).getBlock() instanceof ElevatorBlock block)
					block.takeElevator(entity.level(), entity.blockPosition().below(), player, Direction.UP);
		});

		InteractionEvent.INTERACT_ENTITY.register((player, entity, hand) -> {
			var stack = player.getItemInHand(hand);

			if(player.level().isClientSide()) return EventResult.pass();

			var result = PixelmonInteractions.process(entity, player, stack);
			if(result.interruptsFurtherEvaluation() && stack.getItem() instanceof PixelmonInteractions.PixelmonInteraction interaction && interaction.isConsumed()) stack.shrink(1);
			else if(stack.is(GenerationsItems.ZYGARDE_CUBE.get()) && entity instanceof ZygardeCellEntity) {
				if (stack.getDamageValue() != ZygardeCubeItem.FULL) {
					stack.setDamageValue(stack.getDamageValue() + 1);
					player.displayClientMessage(Component.translatable("item.generations_core.zygarde_cube.tooltip.cell_add"), false);
					player.level().playSound(null, player.blockPosition(), GenerationsSounds.ZYGARDE_CELL.get(), SoundSource.BLOCKS, 0.5f, 1.0f);
					entity.remove(Entity.RemovalReason.DISCARDED);

					result = EventResult.interruptTrue();
				} else {
					player.displayClientMessage(Component.translatable("item.generations_core.zygarde_cube.tooltip.cell_full"), false);
					result = EventResult.pass();
				}
			}

			return result;
		});
	}

	private static void initRecipes() {
		getImplementation().getIngredients().register("time_capsule", TimeCapsuleIngredient.class, TimeCapsuleIngredientSerializer.INSTANCE);
		getImplementation().getIngredients().register("pokemon_item", PokemonItemIngredient.class, PokemonItemIngredient.PokemonItemIngredientSerializer.INSTANCE);
		getImplementation().getIngredients().register("damage", DamageIngredient.class, DamageIngredientSerializer.INSTANCE);
	}

	public static void initBuiltinPacks(TriConsumer<PackType, ResourceLocation, MutableComponent> consumer) {
		consumer.accept(PackType.CLIENT_RESOURCES, GenerationsCore.id("smooth_pokemon"), Component.literal("Smooth Pokemon Models"));
	}

	public static void onAnvilChange(ItemStack left, ItemStack right, Player player, Consumer<ItemStack> output, IntConsumer cost, IntConsumer materialCost) {
		if(left.getItem() instanceof EnchantableItem enchantableItem && enchantableItem.neededEnchantmentLevel(player) > 0 && !EnchantableItem.isEnchanted(left) && right.isEmpty()) {
			output.accept(EnchantableItem.setEnchanted(left.copy(), true));
			cost.accept(100);
			materialCost.accept(0);
		}
	}

	/**
	 * Creates a {@link ResourceLocation} with the Generations-Core Mod id.
	 */
	public static ResourceLocation id(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

	public static GenerationsImplementation getImplementation() {
		return implementation;
	}

	public static void setImplementation(GenerationsImplementation implementation) {
		GenerationsCore.implementation = implementation;
	}
}