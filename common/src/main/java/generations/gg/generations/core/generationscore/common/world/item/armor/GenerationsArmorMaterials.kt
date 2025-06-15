package generations.gg.generations.core.generationscore.common.world.item.armor

import com.cobblemon.mod.common.CobblemonItems
import com.google.common.base.Suppliers
import generations.gg.generations.core.generationscore.common.GenerationsCore.id
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import net.minecraft.Util
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import java.util.*

object GenerationsArmorMaterials: PlatformRegistry<ArmorMaterial>() {
    override val registry: Registry<ArmorMaterial> = BuiltInRegistries.ARMOR_MATERIAL
    override val resourceKey: ResourceKey<Registry<ArmorMaterial>> = Registries.ARMOR_MATERIAL

    val AETHER = register("aether", 15, intArrayOf(2, 5, 6, 2), 9, { GenerationsItems.SILICON })
    val AQUA = register("saphire", 15, intArrayOf(2, 5, 6, 2), 9, { GenerationsItems.SAPPHIRE })
    val FLARE = register("flare", 15, intArrayOf(2, 5, 6, 2), 9, { GenerationsItems.RUBY })
    val GALACTIC = register("galactic", 15, intArrayOf(2, 5, 6, 2), 9, { GenerationsItems.SILICON })
    val MAGMA = register("ruby", 15, intArrayOf(2, 5, 6, 2), 19, { GenerationsItems.RUBY })
    val NEO_PLASMA = register("neo_plasma", 15, intArrayOf(2, 5, 6, 2), 9, { GenerationsItems.CRYSTAL })
    val PLASMA = register("plasma", 15, intArrayOf(2, 5, 6, 2), 9, { GenerationsItems.CRYSTAL })
    val ROCKET = register("rocket", 15, intArrayOf(2, 5, 6, 2), 9, { Items.AMETHYST_SHARD })
    val SKULL = register("skull", 15, intArrayOf(2, 5, 6, 2), 9, { GenerationsItems.SILICON })
    val CRYSTAL = register("crystal", 15, intArrayOf(2, 5, 6, 2), 9, { GenerationsItems.CRYSTAL })

    val ULTRA = register("ultra", 33, intArrayOf(3, 6, 7, 3), 10, { GenerationsItems.Z_INGOT })
    val DAWN_STONE = register("dawn_stone", 33, intArrayOf(3, 6, 8, 3), 10, { CobblemonItems.DAWN_STONE }, 2.0f, 0.0f)
    val DUSK_STONE = register("dusk_stone", 33, intArrayOf(3, 6, 8, 3), 10, { CobblemonItems.DUSK_STONE }, 2.0f, 0.0f)
    val FIRE_STONE = register("fire_stone", 33, intArrayOf(3, 6, 8, 3), 10, { CobblemonItems.FIRE_STONE }, 2.0f, 0.0f)
    val ICE_STONE = register("ice_stone", 33, intArrayOf(3, 6, 8, 3), 10, { CobblemonItems.ICE_STONE }, 2.0f, 0.0f)
    val LEAF_STONE = register("leaf_stone", 33, intArrayOf(3, 6, 8, 3), 10, { CobblemonItems.LEAF_STONE }, 2.0f, 0.0f)
    val MOON_STONE = register("moon_stone", 33, intArrayOf(3, 6, 8, 3), 10, { CobblemonItems.MOON_STONE }, 2.0f, 0.0f)
    val SUN_STONE = register("sun_stone", 33, intArrayOf(3, 6, 8, 3), 10, { CobblemonItems.SUN_STONE }, 2.0f, 0.0f)
    val THUNDER_STONE = register("thunder_stone", 33, intArrayOf(3, 6, 8, 3), 10, { CobblemonItems.THUNDER_STONE }, 2.0f, 0.0f)
    val WATER_STONE = register("water_stone", 33, intArrayOf(3, 6, 8, 3), 10, { CobblemonItems.WATER_STONE }, 2.0f, 0.0f)

    val ULTRITE = register("ultrite", 42, intArrayOf(3, 6, 8, 3), 20, { GenerationsItems.ULTRITE_INGOT }, 4.0f, 0.2f)

    fun register(
        name: String,
        durabilityMultiplier: Int,
        slotProtections: IntArray,
        enchantmentValue: Int,
        repairIngredient: () -> Item,
        toughness: Float = 0.0f,
        knockbackResistance: Float = 0.0f
    ): ArmorMaterial {
        return create(name.generationsResource(), ArmorMaterial(
                Util.make(EnumMap(ArmorItem.Type::class.java)) { map: EnumMap<ArmorItem.Type?, Int?> ->
                    map[ArmorItem.Type.BOOTS] =
                        slotProtections[0]
                    map[ArmorItem.Type.LEGGINGS] = slotProtections[1]
                    map[ArmorItem.Type.CHESTPLATE] = slotProtections[2]
                    map[ArmorItem.Type.HELMET] = slotProtections[3]
                },
                enchantmentValue,
                SoundEvents.ARMOR_EQUIP_IRON,
                Suppliers.memoize { Ingredient.of(repairIngredient) },
                listOf(ArmorMaterial.Layer(id(name))),
                toughness,
                knockbackResistance
            )
        )
    }
}
