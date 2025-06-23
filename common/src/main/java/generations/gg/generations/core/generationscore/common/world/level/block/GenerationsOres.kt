package generations.gg.generations.core.generationscore.common.world.level.block

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsOreSet
import net.minecraft.core.Holder
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

object GenerationsOres: BlockPlatformRegistry() {
    /*
	 * Stone Ores (Charge Stone Variants) Temporarly disabled till ready to use in chargestone cave modules

	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_DAWN_STONE_ORE = registerBlockItem("charge_stone_dawn_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_DUSK_STONE_ORE = registerBlockItem("charge_stone_dusk_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_FIRE_STONE_ORE = registerBlockItem("charge_stone_fire_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_ICE_STONE_ORE = registerBlockItem("charge_stone_ice_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_LEAF_STONE_ORE = registerBlockItem("charge_stone_leaf_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_SHINY_STONE_ORE = registerBlockItem("charge_stone_shiny_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_SUN_STONE_ORE = registerBlockItem("charge_stone_sun_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_THUNDER_STONE_ORE = registerBlockItem("charge_stone_thunder_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_WATER_STONE_ORE = registerBlockItem("charge_stone_water_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_MOON_STONE_ORE = registerBlockItem("charge_stone_moon_stone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	 */
	@JvmField
	val MEGASTONE_ORE_SET: GenerationsOreSet = GenerationsOreSet("megastone_ore", GenerationsItems.MEGASTONE_SHARD)
    @JvmField
	val METEORITE_ORE_SET: GenerationsOreSet = GenerationsOreSet("meteorite_ore")


    /**
     * Other Ores
     */
	@JvmField
	val CRYSTAL_ORE_SET: GenerationsOreSet =
        GenerationsOreSet("crystal_ore", GenerationsItems.CRYSTAL, UniformInt.of(2, 5))
    @JvmField
	val RUBY_ORE_SET: GenerationsOreSet = GenerationsOreSet("ruby_ore", GenerationsItems.RUBY, UniformInt.of(2, 5))
    @JvmField
	val SAPPHIRE_ORE_SET: GenerationsOreSet =
        GenerationsOreSet("sapphire_ore", GenerationsItems.SAPPHIRE, UniformInt.of(2, 5))
    @JvmField
	val SILICON_ORE_SET: GenerationsOreSet = GenerationsOreSet("silicon_ore", GenerationsItems.SILICON)
    @JvmField
	val Z_CRYSTAL_ORE_SET: GenerationsOreSet = GenerationsOreSet("z_crystal_ore")


    // Charge Stone Vanilla Ores
    /*
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_COAL_ORE = registerOreBlockItem("charge_stone_coal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE).dropsLike(Blocks.COAL_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_DIAMOND_ORE = registerOreBlockItem("charge_stone_diamond_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).dropsLike(Blocks.DIAMOND_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_EMERALD_ORE = registerOreBlockItem("charge_stone_emerald_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_ORE).dropsLike(Blocks.EMERALD_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_GOLD_ORE = registerOreBlockItem("charge_stone_gold_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE).dropsLike(Blocks.GOLD_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_IRON_ORE = registerOreBlockItem("charge_stone_iron_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).dropsLike(Blocks.IRON_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_COPPER_ORE = registerOreBlockItem("charge_stone_copper_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE).dropsLike(Blocks.COPPER_ORE)));
	public static final RegistrySupplier<DropExperienceBlock> CHARGE_STONE_LAPIS_LAZULI_ORE = registerOreBlockItem("charge_stone_lapis_lazuli_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.LAPIS_ORE).dropsLike(Blocks.LAPIS_ORE)));
	public static final RegistrySupplier<RedStoneOreBlock> CHARGE_STONE_REDSTONE_ORE = registerOreBlockItem("charge_stone_redstone_ore", () -> new RedStoneOreBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_ORE).dropsLike(Blocks.REDSTONE_ORE)));
	 */
    private fun register(name: String, itemSupplier: (Item.Properties) -> Item): Holder<Item> = GenerationsItems.ITEMS.create(name, { itemSupplier.invoke(Item.Properties()) })

    fun <T : Block> registerOreBlockItem(name: String, blockSupplier: () -> T): Holder<T> {
        val block =  create(name, blockSupplier)
        register(name) { properties -> GenerationsBlockItem(block, properties) }
        return block
    }

    override fun init() {
        GenerationsCore.LOGGER.info("Registering Generations Ores")
        super.init()
    }
}