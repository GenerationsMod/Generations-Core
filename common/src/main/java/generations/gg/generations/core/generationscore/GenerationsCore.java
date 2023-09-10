/**
 * The Main Class of the Generations-Core mod. (Common)
 * Registers the mod's items and blocks with Minecraft using Architectury.
 * @author J.T. McQuigg
 *
 * CopyRight (c) 2023 Generations-Mod
 */

package generations.gg.generations.core.generationscore;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.data.DataProvider;
import com.cobblemon.mod.common.api.pokemon.helditem.HeldItemProvider;
import com.mojang.logging.LogUtils;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import generations.gg.generations.core.generationscore.api.data.GenerationsCoreEntityDataSerializers;
import generations.gg.generations.core.generationscore.config.Config;
import generations.gg.generations.core.generationscore.config.ConfigLoader;
import generations.gg.generations.core.generationscore.world.container.GenerationsContainers;
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.item.GenerationsArmor;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.GenerationsTools;
import generations.gg.generations.core.generationscore.world.item.PixelmonInteractions;
import generations.gg.generations.core.generationscore.world.item.creativetab.GenerationsCreativeTabs;
import generations.gg.generations.core.generationscore.world.level.block.*;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.sound.GenerationsSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.function.Function;

/**
 * The Main Class of the Generations-Core mod. (Common)
 * Registers the mod's items and blocks with Minecraft using Architectury.
 * @author J.T. McQuigg, WaterPicker
 */
public class GenerationsCore
{

	/** The mod id of the Generations-Core mod. */
	public static final String MOD_ID = "generations_core";

	/** The random source for Generations-Core mod. */
	public static final RandomSource RANDOM_SOURCE = RandomSource.create();

	/** The logger for the Generations-Core mod. */
	public static final Logger LOGGER = LogUtils.getLogger();

	/** The config for the Generations-Core mod. */
	public static Config CONFIG;
	public static GenerationsImplementation implementation;

	public static DataProvider dataProvider = GenerationsDataProvider.INSTANCE;

	/** Config Directory **/
	public static Path CONFIG_DIRECTORY;

	/**
	 * Initializes the Generations-Core mod.
	 * @param configDirectory The config directory for the Generations-Core mod.
	 */
	public static void init(GenerationsImplementation implementation, @NotNull Path configDirectory) {
		GenerationsCore.implementation = implementation;
		GenerationsCoreEntityDataSerializers.init();
		CONFIG_DIRECTORY = configDirectory;
		GenerationsSounds.init();
		GenerationsCreativeTabs.init();
		GenerationsBlocks.init();
		GenerationsPokeDolls.init();
		GenerationsWood.init();
		GenerationsOres.init();
		GenerationsDecorationBlocks.init();
		GenerationsUtilityBlocks.init();
		GenerationsShrines.init();
		GenerationsBlockEntities.init();
		GenerationsEntities.init();
		GenerationsItems.init();
		GenerationsArmor.init();
		GenerationsTools.init();
		GenerationsPaintings.init();
		GenerationsContainers.init();

		GenerationsDataProvider.INSTANCE.registerDefaults();

		CONFIG = ConfigLoader.loaderConfig(Config.class, MOD_ID, "main");

		CobblemonEvents.init();
		InteractionEvent.INTERACT_ENTITY.register((player, entity, hand) -> {
			var stack = player.getItemInHand(hand);
			var result = PixelmonInteractions.process(entity, player, stack);
			if(result.interruptsFurtherEvaluation() && stack.getItem() instanceof PixelmonInteractions.PixelmonInteraction interaction && interaction.isConsumed()) stack.shrink(1);
			return result;
		});
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