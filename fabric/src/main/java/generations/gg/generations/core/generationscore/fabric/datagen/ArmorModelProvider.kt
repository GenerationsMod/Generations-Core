package generations.gg.generations.core.generationscore.fabric.datagen

import com.cobblemon.mod.common.util.asResource
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.world.item.GenerationsArmor
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.ArmorMaterials
import net.minecraft.world.item.Item
import java.util.stream.Stream

internal class ArmorModelProvider(fabricDataOutput: FabricDataOutput) : FabricModelProvider(fabricDataOutput) {
    override fun generateBlockStateModels(blockModelGenerators: BlockModelGenerators) {
    }

    override fun generateItemModels(itemModelGenerators: ItemModelGenerators) {
        Stream.of(GenerationsArmor.ULTRITE,
                GenerationsArmor.DAWN_STONE,
                GenerationsArmor.DUSK_STONE,
                GenerationsArmor.FIRE_STONE,
                GenerationsArmor.ICE_STONE,
                GenerationsArmor.LEAF_STONE,
                GenerationsArmor.SUN_STONE,
                GenerationsArmor.MOON_STONE,
                GenerationsArmor.THUNDER_STONE,
                GenerationsArmor.WATER_STONE).flatMap(GenerationsArmor.ArmorSet::stream).map(RegistrySupplier<Item>::get)
            .map { obj -> ArmorItem::class.java.cast(obj) }
            .forEach({ itemModelGenerators.generateGenerationsArmorTrims(it) })
    }

    fun ItemModelGenerators.generateGenerationsArmorTrims(armorItem: ArmorItem) {
        val resourceLocation = ModelLocationUtils.getModelLocation(armorItem)

        // Modified texture references to include "armor/"
        val baseTexture = TextureMapping.getItemTexture(armorItem).toString().replace("item/", "item/armor/").asResource()
        val overlayTexture = TextureMapping.getItemTexture(armorItem, "_overlay").toString().replace("item/", "item/armor/").asResource()

        if (armorItem.material === ArmorMaterials.LEATHER) {
            ModelTemplates.TWO_LAYERED_ITEM.create(
                resourceLocation,
                TextureMapping.layered(baseTexture, overlayTexture),
                this.output
            ) { resourceLocationx, map ->
                generateBaseArmorTrimTemplate(resourceLocationx, map, armorItem.material)
            }
        } else {
            ModelTemplates.FLAT_ITEM.create(
                resourceLocation,
                TextureMapping.layer0(baseTexture),
                this.output
            ) { resourceLocationx, map ->
                generateBaseArmorTrimTemplate(resourceLocationx, map, armorItem.material)
            }
        }

        for (trimModelData in GENERATED_TRIM_MODELS) {
            val trimName = trimModelData.name(armorItem.material)
            val trimmedModel = getItemModelForTrimMaterial(resourceLocation, trimName)

            // Modify the texture reference for trims too
            val trimmedResourceLocation = ResourceLocation("trims/items/${armorItem.type.getName()}_trim_$trimName")

            if (armorItem.material === ArmorMaterials.LEATHER) {
                generateLayeredItem(trimmedModel, baseTexture, overlayTexture, trimmedResourceLocation)
            } else {
                generateLayeredItem(trimmedModel, baseTexture, trimmedResourceLocation)
            }
        }
    }

    val GENERATED_TRIM_MODELS = listOf(
        TrimModelData("quartz", 0.1F, mapOf()),
        TrimModelData("iron", 0.2F, mapOf(ArmorMaterials.IRON to "iron_darker")),
        TrimModelData("netherite", 0.3F, mapOf(ArmorMaterials.NETHERITE to "netherite_darker")),
        TrimModelData("redstone", 0.4F, mapOf()),
        TrimModelData("copper", 0.5F, mapOf()),
        TrimModelData("gold", 0.6F, mapOf(ArmorMaterials.GOLD to "gold_darker")),
        TrimModelData("emerald", 0.7F, mapOf()),
        TrimModelData("diamond", 0.8F, mapOf(ArmorMaterials.DIAMOND to "diamond_darker")),
        TrimModelData("lapis", 0.9F, mapOf()),
        TrimModelData("amethyst", 1.0F, mapOf())
    )

    data class TrimModelData(
        val name: String,
        val itemModelIndex: Float,
        val overrideArmorMaterials: Map<ArmorMaterial, String>
    ) {
        fun name(armorMaterial: ArmorMaterial): String = overrideArmorMaterials.getOrDefault(armorMaterial, name)
    }
}