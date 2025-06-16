package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.tab
import generations.gg.generations.core.generationscore.common.util.ItemPlatformRegistry
import generations.gg.generations.core.generationscore.common.world.item.armor.ArmorEffect
import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorItem
import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorMaterials
import generations.gg.generations.core.generationscore.common.world.item.armor.effects.EnchantmentArmorEffect
import generations.gg.generations.core.generationscore.common.world.item.armor.effects.PotionArmorEffect
import generations.gg.generations.core.generationscore.common.world.item.armor.effects.SpeedModifier
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.*
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.Enchantments
import java.util.stream.Stream

object GenerationsArmor: ItemPlatformRegistry() {

    /**
     * Armor Sets
     */
    fun create(
        name: String,
        function: (Item.Properties) -> Item,
        tab: ResourceKey<CreativeModeTab>
    ): Item = create(name.generationsResource(), function.invoke(of()).tab(tab))

    val AETHER: ArmorSet = ArmorSet.create("aether", GenerationsArmorMaterials.AETHER) {
        speed(0.5)
    }

    val AQUA: ArmorSet = ArmorSet.create("aqua", GenerationsArmorMaterials.AQUA)
    val FLARE: ArmorSet = ArmorSet.create("flare", GenerationsArmorMaterials.FLARE)
    val GALACTIC: ArmorSet = ArmorSet.create("galactic", GenerationsArmorMaterials.GALACTIC) {
        speed(0.5)
    }
    val ULTRITE: ArmorSet = ArmorSet.create("ultrite", GenerationsArmorMaterials.ULTRITE)
    val MAGMA: ArmorSet = ArmorSet.create("magma", GenerationsArmorMaterials.MAGMA)
    val NEO_PLASMA: ArmorSet = ArmorSet.create("neo_plasma", GenerationsArmorMaterials.NEO_PLASMA)
    val PLASMA: ArmorSet = ArmorSet.create("plasma", GenerationsArmorMaterials.PLASMA)
    val ROCKET: ArmorSet = ArmorSet.create("rocket", GenerationsArmorMaterials.ROCKET)
    val SKULL: ArmorSet = ArmorSet.create("skull", GenerationsArmorMaterials.SKULL) {
        speed(0.5)
    }
    val ULTRA: ArmorSet = ArmorSet.create("ultra", GenerationsArmorMaterials.ULTRA) {
        potion(MobEffects.MOVEMENT_SPEED, 1)
        speed(0.25)
    }
    val CRYSTALLIZED: ArmorSet = ArmorSet.create("crystallized", GenerationsArmorMaterials.CRYSTAL) {
        potion(MobEffects.MOVEMENT_SPEED, 1)
        speed(0.1)
    }
    val DAWN_STONE: ArmorSet = ArmorSet.create("dawn_stone", GenerationsArmorMaterials.DAWN_STONE) {
        potion(MobEffects.JUMP, 3)
        speed(0.5)
    }
    val DUSK_STONE: ArmorSet = ArmorSet.create("dusk_stone", GenerationsArmorMaterials.DUSK_STONE) {
        potion(MobEffects.SATURATION, 4)
        speed(0.5)
    }
    val FIRE_STONE: ArmorSet = ArmorSet.create("fire_stone", GenerationsArmorMaterials.FIRE_STONE) {
        enchantment(Enchantments.FIRE_PROTECTION, 2)
        potion(MobEffects.FIRE_RESISTANCE, 1)
        speed(0.5)
    }

    val LEAF_STONE: ArmorSet = ArmorSet.create("leaf_stone", GenerationsArmorMaterials.LEAF_STONE) {
        enchantment(Enchantments.FEATHER_FALLING, 3)
        enchantment(Enchantments.THORNS, 3)
        speed(0.5)
    }
    val ICE_STONE: ArmorSet = ArmorSet.create("ice_stone", GenerationsArmorMaterials.ICE_STONE) {
        enchantment(Enchantments.FROST_WALKER, 2)
        speed(0.5)
    }
    val MOON_STONE: ArmorSet = ArmorSet.create("moon_stone", GenerationsArmorMaterials.MOON_STONE) {
        enchantment(Enchantments.PROTECTION, 4)
        enchantment(Enchantments.PROJECTILE_PROTECTION, 4)
        speed(0.5)
    }
    val SUN_STONE: ArmorSet = ArmorSet.create("sun_stone", GenerationsArmorMaterials.SUN_STONE) {
        enchantment(Enchantments.PROTECTION, 4)
        enchantment(Enchantments.PROJECTILE_PROTECTION, 4)
        speed(0.5)
    }
    val THUNDER_STONE: ArmorSet = ArmorSet.create("thunder_stone", GenerationsArmorMaterials.THUNDER_STONE) {
        potion(MobEffects.DIG_SPEED, 1)
        speed(0.5)
    }
    val WATER_STONE: ArmorSet = ArmorSet.create("water_stone", GenerationsArmorMaterials.WATER_STONE) {
        potion(MobEffects.WATER_BREATHING, 1)
        enchantment(Enchantments.AQUA_AFFINITY, 2)
        speed(0.5)
    }

    fun of(): Item.Properties {
        return Item.Properties()
    }

    override fun init(consumer: (ResourceLocation, Item) -> Unit) {
        GenerationsCore.LOGGER.info("Registering Generations Armor")
        super.init(consumer)
    }

    data class ArmorSet(
        val helmet: Item,
        val chestplate: Item,
        val leggings: Item,
        val boots: Item,
        val armorMaterial: ArmorMaterial
    ) {
        fun stream(): Stream<Item> {
            return Stream.of(
                helmet,
                chestplate,
                leggings,
                boots
            )
        }


        class Builder(private val name: String, private val armormaterial: ArmorMaterial) {
            private val effects: MutableList<ArmorEffect> = ArrayList()

            fun enchantment(enchantment: ResourceKey<Enchantment>, level: Int): Builder {
                effects.add(EnchantmentArmorEffect(enchantment, level))
                return this
            }

            fun potion(potionEffect: Holder<MobEffect>, amplifier: Int): Builder {
                effects.add(PotionArmorEffect(potionEffect, amplifier))
                return this
            }

            fun speed(speed: Double, speedWhenFull: Double = speed): Builder {
                effects.add(SpeedModifier(speed, speedWhenFull))
                return this
            }

            fun build(): ArmorSet {
                return create(name, armormaterial, *effects.toTypedArray())
            }
        }

        companion object {
            val MATERIAL_TO_SET = mutableMapOf<ArmorMaterial, ArmorSet>()

            fun create(name: String, armorMaterial: ArmorMaterial, block: Builder.() -> Unit): ArmorSet {
                val builder = builder(name, armorMaterial)
                block.invoke(builder)
                return builder.build()
            }

            fun builder(name: String, armorMaterial: ArmorMaterial): Builder {
                return Builder(name, armorMaterial)
            }

            fun create(
                name: String,
                armorMaterial: ArmorMaterial,
                vararg armorEffects: ArmorEffect
            ): ArmorSet {
                val holder = Holder.direct(armorMaterial) //TODO: Verify this doens't cause trouble

                return ArmorSet(
                    register(
                        name + "_helmet"
                    ) { properties: Item.Properties ->
                        GenerationsArmorItem(
                            holder,
                            ArmorItem.Type.HELMET,
                            properties
                        )
                    },
                    register(
                        name + "_chestplate"
                    ) { properties: Item.Properties ->
                        GenerationsArmorItem(
                            holder,
                            ArmorItem.Type.CHESTPLATE,
                            properties
                        )
                    },
                    register(
                        name + "_leggings"
                    ) { properties: Item.Properties ->
                        GenerationsArmorItem(
                            holder,
                            ArmorItem.Type.LEGGINGS,
                            properties
                        )
                    },
                    register(
                        name + "_boots"
                    ) { properties: Item.Properties ->
                        GenerationsArmorItem(
                            holder,
                            ArmorItem.Type.BOOTS,
                            properties,
                            *armorEffects
                        )
                    },
                    armorMaterial
                ).also { MATERIAL_TO_SET[armorMaterial] = it }
            }

            fun register(
                name: String,
                function: (Item.Properties) -> GenerationsArmorItem
            ): Item {
                return create(
                    name,
                    { t: Item.Properties -> function.invoke(t) }, CreativeModeTabs.COMBAT
                )
            }
        }
    }
}
