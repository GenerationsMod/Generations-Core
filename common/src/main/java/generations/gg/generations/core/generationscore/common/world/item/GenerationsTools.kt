package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.ItemPlatformRegistry
import generations.gg.generations.core.generationscore.common.util.extensions.supplier
import generations.gg.generations.core.generationscore.common.world.item.tools.*
import generations.gg.generations.core.generationscore.common.world.item.tools.effects.*
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.*
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.Blocks
import java.util.function.Function
import java.util.function.Supplier

object GenerationsTools: ItemPlatformRegistry() {
    /**
     * Tools
     */
    val CHARGE_STONE: ToolSet = ToolSet.create("charge_stone", GenerationsTiers.CHARGE_STONE)

    val VOLCANIC_STONE: ToolSet = ToolSet.create("volcanic_stone", GenerationsTiers.CHARGE_STONE)

    val AMETHYST: ToolSet = ToolSet.create("amethyst", GenerationsTiers.AMETHYST)

    val CRYSTAL: ToolSet = ToolSet.create("crystal", GenerationsTiers.CRYSTAL)

    val DAWN_STONE: ToolSet = ToolSet.create("dawn_stone", GenerationsTiers.DAWN_STONE , PotionToolEffect(MobEffects.HEALTH_BOOST, 0, 6000, 1))

    val DUSK_STONE: ToolSet = ToolSet.create("dusk_stone", GenerationsTiers.DUSK_STONE, PotionToolEffect(MobEffects.INVISIBILITY, 0, 6000, 1))

    val FIRE_STONE: ToolSet = ToolSet.create("fire_stone", GenerationsTiers.FIRE_STONE, true, TransformToolEffect(Blocks.WATER, Blocks.OBSIDIAN, 1))

    val ICE_STONE: ToolSet = ToolSet.create("ice_stone", GenerationsTiers.ICE_STONE , TransformToolEffect(Blocks.WATER, Blocks.ICE, 1))

    val LEAF_STONE: ToolSet = ToolSet.create("leaf_stone", GenerationsTiers.LEAF_STONE, BoneMealToolEffect(12))

    val MOON_STONE: ToolSet = ToolSet.create("moon_stone", GenerationsTiers.MOON_STONE, PotionToolEffect(MobEffects.NIGHT_VISION, 0, 6000, 1))

    val RUBY: ToolSet = ToolSet.create("ruby", GenerationsTiers.RUBY)

    val SAPPHIRE: ToolSet = ToolSet.create("sapphire", GenerationsTiers.SAPPHIRE)

    val SILICON: ToolSet = ToolSet.create("silicon", GenerationsTiers.SILICON)

    val SUN_STONE: ToolSet = ToolSet.create("sun_stone", GenerationsTiers.SUN_STONE, true, PlaceItemToolEffect(Items.TORCH as BlockItem, 5)) //TODO: Replace with temp light source derived from tinker's construct's light source

    val THUNDER_STONE: ToolSet = ToolSet.create("thunder_stone", GenerationsTiers.THUNDER_STONE, EnchantmentToolEffect(Enchantments.EFFICIENCY, 3, 1))

    val WATER_STONE: ToolSet = ToolSet.create("water_stone", GenerationsTiers.WATER_STONE, EnchantmentToolEffect(Enchantments.EFFICIENCY, 3, 1))

    val ULTRITE: ToolSet = ToolSet.create("ultrite", GenerationsTiers.ULTRITE, true)

    val DIAMOND_HAMMER = register("diamond_hammer", { properties -> GenerationsHammerItem(Tiers.DIAMOND, properties) }, CreativeModeTabs.TOOLS_AND_UTILITIES)
    val GOLDEN_HAMMER = register("golden_hammer", { properties -> GenerationsHammerItem(Tiers.GOLD, properties) }, CreativeModeTabs.TOOLS_AND_UTILITIES)
    val IRON_HAMMER = register("iron_hammer", { properties -> GenerationsHammerItem(Tiers.IRON, properties) }, CreativeModeTabs.TOOLS_AND_UTILITIES)
    val NETHERITE_HAMMER = register("netherite_hammer", { properties -> GenerationsHammerItem(Tiers.NETHERITE, properties) }, CreativeModeTabs.TOOLS_AND_UTILITIES)

    //	public static final RegistrySupplier<Item> ULTRITE_HAMMER = register("ultrite_hammer", properties -> new GenerationsHammerItem(GenerationsTiers.ULTRITE, 4.0F, -3.0F, properties), CreativeModeTabs.TOOLS_AND_UTILITIES);
    val STONE_HAMMER = register("stone_hammer", { properties -> GenerationsHammerItem(Tiers.STONE, properties) }, CreativeModeTabs.TOOLS_AND_UTILITIES)
    val WOODEN_HAMMER = register("wooden_hammer", { properties -> GenerationsHammerItem(Tiers.WOOD, properties) }, CreativeModeTabs.TOOLS_AND_UTILITIES)

    private fun <T : Item> register(
        name: String,
        function: (Item.Properties) -> T,
        tab: ResourceKey<CreativeModeTab>
    ): T = create(name.generationsResource(), function.invoke(of().`arch$tab`(tab)))


    fun of(): Item.Properties {
        return Item.Properties()
    }

    override fun init(consumer: (ResourceLocation, Item) -> Unit) {
        GenerationsCore.LOGGER.info("Registering Generations Tools")
        super.init(consumer)
    }

    data class ToolSet(
        val shovel: GenerationsShovelItem,
        val pickaxe: GenerationsPickaxeItem,
        val axe: GenerationsAxeItem,
        val hoe: GenerationsHoeItem,
        val hammer: GenerationsHammerItem,
        val sword: GenerationsSwordItem
    ) {

        companion object {
            fun create(name: String, tier: Tier, vararg toolEffects: ToolEffect): ToolSet {
                return create(name, tier, false, *toolEffects)
            }

            fun create(
                name: String,
                tier: Tier,
                fireProof: Boolean = false,
                vararg toolEffects: ToolEffect
            ): ToolSet {
                return ToolSet(
                    register("${name}_shovel", ::GenerationsShovelItem, tier, CreativeModeTabs.TOOLS_AND_UTILITIES, fireProof, *toolEffects),
                    register("${name}_pickaxe", ::GenerationsPickaxeItem, tier, CreativeModeTabs.TOOLS_AND_UTILITIES, fireProof, *toolEffects),
                    register("${name}_axe", ::GenerationsAxeItem, tier, CreativeModeTabs.TOOLS_AND_UTILITIES, fireProof, *toolEffects),
                    register("${name}_hoe", ::GenerationsHoeItem, tier, CreativeModeTabs.TOOLS_AND_UTILITIES, fireProof, *toolEffects),
                    register("${name}_hammer", ::GenerationsHammerItem, tier, CreativeModeTabs.TOOLS_AND_UTILITIES, fireProof, *toolEffects),
                    register("${name}_sword", ::GenerationsSwordItem, tier, CreativeModeTabs.COMBAT, fireProof, *toolEffects)
                )
            }

            private fun <T> register(
                name: String,
                supplier: (Tier, Item.Properties) -> T,
                tier: Tier,
                tab: ResourceKey<CreativeModeTab>,
                fireProof: Boolean,
                vararg toolEffects: ToolEffect
            ): T where T : Item, T : ToolEffectHolder<T> {
                return register(
                    name,
                    { properties: Item.Properties ->
                        if (fireProof) properties.fireResistant()
                        supplier.invoke(tier, properties).addToolEffects(*toolEffects)
                    }, tab
                )
            }
        }
    }
}
