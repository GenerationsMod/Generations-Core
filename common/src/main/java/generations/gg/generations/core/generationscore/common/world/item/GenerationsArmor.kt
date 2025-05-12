package generations.gg.generations.core.generationscore.common.world.item

import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.item.armor.ArmorEffect
import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorItem
import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorMaterials
import generations.gg.generations.core.generationscore.common.world.item.armor.effects.DoubleSpeedArmorEffect
import generations.gg.generations.core.generationscore.common.world.item.armor.effects.EnchantmentArmorEffect
import generations.gg.generations.core.generationscore.common.world.item.armor.effects.PotionArmorEffect
import generations.gg.generations.core.generationscore.common.world.item.armor.effects.SpeedModifier
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.*
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.Enchantments
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Stream

object GenerationsArmor {
    val ARMOR: DeferredRegister<Item> = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM)

    /**
     * Armor Sets
     */
    fun register(
        name: String,
        function: Function<Item.Properties, Item>,
        tab: ResourceKey<CreativeModeTab>
    ): RegistrySupplier<Item> {
        return ARMOR.register(
            name
        ) { function.apply(of().`arch$tab`(tab)) }
    }

    fun register(
        name: String,
        function: Function<Item.Properties, Item>,
        tab: CreativeModeTab
    ): RegistrySupplier<Item> {
        return ARMOR.register(
            name
        ) { function.apply(of().`arch$tab`(tab)) }
    }

    val AETHER: ArmorSet = ArmorSet.create("aether", GenerationsArmorMaterials.AETHER) {
        speed(0.5f)
    }

    val AQUA: ArmorSet = ArmorSet.create("aqua", GenerationsArmorMaterials.AQUA)
    val FLARE: ArmorSet = ArmorSet.create("flare", GenerationsArmorMaterials.FLARE)
    val GALACTIC: ArmorSet = ArmorSet.create("galactic", GenerationsArmorMaterials.GALACTIC) {
        speed(0.5f)
    }
    val ULTRITE: ArmorSet = ArmorSet.create("ultrite", GenerationsArmorMaterials.ULTRITE)
    val MAGMA: ArmorSet = ArmorSet.create("magma", GenerationsArmorMaterials.MAGMA)
    val NEO_PLASMA: ArmorSet = ArmorSet.create("neo_plasma", GenerationsArmorMaterials.NEO_PLASMA)
    val PLASMA: ArmorSet = ArmorSet.create("plasma", GenerationsArmorMaterials.PLASMA)
    val ROCKET: ArmorSet = ArmorSet.create("rocket", GenerationsArmorMaterials.ROCKET)
    val SKULL: ArmorSet = ArmorSet.create("skull", GenerationsArmorMaterials.SKULL) {
        speed(0.5f)
    }
    val ULTRA: ArmorSet = ArmorSet.create("ultra", GenerationsArmorMaterials.ULTRA) {
        potion(MobEffects.MOVEMENT_SPEED, 1)
        speed(0.25f)
    }
    val CRYSTALLIZED: ArmorSet = ArmorSet.create("crystallized", GenerationsArmorMaterials.CRYSTAL) {
        potion(MobEffects.MOVEMENT_SPEED, 1)
        speed(0.1f)
    }
    val DAWN_STONE: ArmorSet = ArmorSet.create("dawn_stone", GenerationsArmorMaterials.DAWN_STONE) {
        potion(MobEffects.JUMP, 3)
        speed(0.5f)
    }
    val DUSK_STONE: ArmorSet = ArmorSet.create("dusk_stone", GenerationsArmorMaterials.DUSK_STONE) {
        potion(MobEffects.SATURATION, 4)
        speed(0.5f)
    }
    val FIRE_STONE: ArmorSet = ArmorSet.create("fire_stone", GenerationsArmorMaterials.FIRE_STONE) {
        enchantment(Enchantments.FIRE_PROTECTION, 2)
        potion(MobEffects.FIRE_RESISTANCE, 1)
        speed(0.5f)
    }

    val LEAF_STONE: ArmorSet = ArmorSet.create("leaf_stone", GenerationsArmorMaterials.LEAF_STONE) {
        enchantment(Enchantments.FEATHER_FALLING, 3)
        enchantment(Enchantments.THORNS, 3)
        speed(0.5f)
    }
    val ICE_STONE: ArmorSet = ArmorSet.create("ice_stone", GenerationsArmorMaterials.ICE_STONE) {
        enchantment(Enchantments.FROST_WALKER, 2)
        speed(0.5f)
    }
    val MOON_STONE: ArmorSet = ArmorSet.create("moon_stone", GenerationsArmorMaterials.MOON_STONE) {
        enchantment(Enchantments.PROTECTION, 4)
        enchantment(Enchantments.PROJECTILE_PROTECTION, 4)
        speed(0.5f)
    }
    val SUN_STONE: ArmorSet = ArmorSet.create("sun_stone", GenerationsArmorMaterials.SUN_STONE) {
        enchantment(Enchantments.PROTECTION, 4)
        enchantment(Enchantments.PROJECTILE_PROTECTION, 4)
        speed(0.5f)
    }
    val THUNDER_STONE: ArmorSet = ArmorSet.create("thunder_stone", GenerationsArmorMaterials.THUNDER_STONE) {
        potion(MobEffects.DIG_SPEED, 1)
        speed(0.5f)
    }
    val WATER_STONE: ArmorSet = ArmorSet.create("water_stone", GenerationsArmorMaterials.WATER_STONE) {
        potion(MobEffects.WATER_BREATHING, 1)
        enchantment(Enchantments.AQUA_AFFINITY, 2)
        speed(0.5f)
    }

    fun of(): Item.Properties {
        return Item.Properties()
    }

    @JvmStatic fun init() {
        GenerationsCore.LOGGER.info("Registering Generations Armor")
        ARMOR.register()
    }

    @JvmRecord
    data class ArmorSet(
        val helmet: RegistrySupplier<Item>,
        val chestplate: RegistrySupplier<Item>,
        val leggings: RegistrySupplier<Item>,
        val boots: RegistrySupplier<Item>,
        val armorMaterial: Holder<ArmorMaterial>
    ) {
        fun stream(): Stream<RegistrySupplier<Item>> {
            return Stream.of(
                helmet,
                chestplate,
                leggings,
                boots
            )
        }


        class Builder(private val name: String, private val armormaterial: Holder<ArmorMaterial>) {
            private val effects: MutableList<ArmorEffect> = ArrayList()

            fun enchantment(enchantment: ResourceKey<Enchantment>, level: Int): Builder {
                effects.add(EnchantmentArmorEffect(enchantment, level))
                return this
            }

            fun potion(potionEffect: Holder<MobEffect>, amplifier: Int): Builder {
                effects.add(PotionArmorEffect(potionEffect, amplifier))
                return this
            }

            fun speed(speed: Float): Builder {
                return speed(speed, false)
            }

            fun speed(speed: Float, doubleWhenFullSet: Boolean): Builder {
                effects.add(SpeedModifier(speed))
                if (doubleWhenFullSet) effects.add(DoubleSpeedArmorEffect())
                return this
            }

            fun build(): ArmorSet {
                return create(name, armormaterial, *effects.toTypedArray())
            }
        }

        companion object {
            fun create(name: String, armorMaterial: Holder<ArmorMaterial>, block: Builder.() -> Unit): ArmorSet {
                val builder = builder(name, armorMaterial)
                block.invoke(builder)
                return builder.build()
            }

            fun builder(name: String, armorMaterial: Holder<ArmorMaterial>): Builder {
                return Builder(name, armorMaterial)
            }

            fun create(
                name: String,
                armorMaterial: Holder<ArmorMaterial>,
                vararg armorEffects: ArmorEffect?
            ): ArmorSet {
                return ArmorSet(
                    register(
                        name + "_helmet"
                    ) { properties: Item.Properties ->
                        GenerationsArmorItem(
                            armorMaterial,
                            ArmorItem.Type.HELMET,
                            properties
                        )
                    },
                    register(
                        name + "_chestplate"
                    ) { properties: Item.Properties ->
                        GenerationsArmorItem(
                            armorMaterial,
                            ArmorItem.Type.CHESTPLATE,
                            properties
                        )
                    },
                    register(
                        name + "_leggings"
                    ) { properties: Item.Properties ->
                        GenerationsArmorItem(
                            armorMaterial,
                            ArmorItem.Type.LEGGINGS,
                            properties
                        )
                    },
                    register(
                        name + "_boots"
                    ) { properties: Item.Properties ->
                        GenerationsArmorItem(
                            armorMaterial,
                            ArmorItem.Type.BOOTS,
                            properties,
                            *armorEffects
                        )
                    },
                    armorMaterial
                )
            }

            fun register(
                name: String,
                function: Function<Item.Properties, GenerationsArmorItem>
            ): RegistrySupplier<Item> {
                return register(
                    name,
                    { t: Item.Properties -> function.apply(t) }, CreativeModeTabs.COMBAT
                )
            }
        }
    }
}
