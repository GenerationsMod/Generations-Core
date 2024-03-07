/**
 * The Main Class of the Generations-Core mod. (Common)
 * Registers the mod's items and blocks with Minecraft using Architectury.
 * @author Joseph T. McQuigg
 *
 * CopyRight (c) 2023 Generations-Mod
 */

package generations.gg.generations.core.generationscore;

import com.cobblemon.mod.common.api.data.DataProvider;
import com.cobblemon.mod.common.api.spawning.detail.SpawnDetail;
import com.cobblemon.mod.common.api.storage.player.PlayerDataExtensionRegistry;
import com.mojang.logging.LogUtils;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import generations.gg.generations.core.generationscore.api.data.GenerationsCoreEntityDataSerializers;
import generations.gg.generations.core.generationscore.api.events.general.EntityEvents;
import generations.gg.generations.core.generationscore.api.player.AccountInfo;
import generations.gg.generations.core.generationscore.api.player.BiomesVisited;
import generations.gg.generations.core.generationscore.api.player.Caught;
import generations.gg.generations.core.generationscore.config.Config;
import generations.gg.generations.core.generationscore.config.ConfigLoader;
import generations.gg.generations.core.generationscore.recipe.GenerationsIngredidents;
import generations.gg.generations.core.generationscore.world.container.GenerationsContainers;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNodeTypes;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.LocationLogicTypes;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.YawLogicTypes;
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.entity.ZygardeCellEntity;
import generations.gg.generations.core.generationscore.world.item.*;
import generations.gg.generations.core.generationscore.world.item.creativetab.GenerationsCreativeTabs;
import generations.gg.generations.core.generationscore.world.item.legends.EnchantableItem;
import generations.gg.generations.core.generationscore.world.level.block.*;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.recipe.GenerationsCoreRecipeSerializers;
import generations.gg.generations.core.generationscore.world.recipe.GenerationsCoreRecipeTypes;
import generations.gg.generations.core.generationscore.world.recipe.RksResultType;
import generations.gg.generations.core.generationscore.world.sound.GenerationsSounds;
import generations.gg.generations.core.generationscore.world.spawning.ZygardeCellDetail;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.InteractionResult;
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

		SpawnDetail.Companion.registerSpawnType(ZygardeCellDetail.Companion.getTYPE(), ZygardeCellDetail.class);

		GenerationsCoreEntityDataSerializers.init();
		GenerationsSounds.init();
		GenerationsBlocks.init();
		GenerationsPokeDolls.init();
		GenerationsWood.init();
		GenerationsOres.init();
		GenerationsDecorationBlocks.init();
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
		GenerationsIngredidents.init();
		GenerationsCoreRecipeTypes.init();
		GenerationsCoreRecipeSerializers.init();

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
			var result = PixelmonInteractions.process(entity, player, stack);
			if(result.interruptsFurtherEvaluation() && stack.getItem() instanceof PixelmonInteractions.PixelmonInteraction interaction && interaction.isConsumed()) stack.shrink(1);
			else if(stack.is(GenerationsItems.ZYGARDE_CUBE.get()) && entity instanceof ZygardeCellEntity) {
				if (stack.getDamageValue() != ZygardeCubeItem.FULL) {
					stack.setDamageValue(stack.getDamageValue() + 1);
					player.displayClientMessage(Component.translatable("item.generations_core.zygarde_cube.add_cell"), false);
//                level().playSound(null, blockPosition(), GenerationsSounds.ZYGARDE_CELL.get(), SoundSource.BLOCKS, 0.5f, 1.0f);
					entity.remove(Entity.RemovalReason.DISCARDED);

					result = EventResult.interruptTrue();
				} else {
					player.displayClientMessage(Component.translatable("item.generations_core.zygarde_cube.full_cell"), false);
					result = EventResult.pass();
				}
			}

			return result;
		});
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