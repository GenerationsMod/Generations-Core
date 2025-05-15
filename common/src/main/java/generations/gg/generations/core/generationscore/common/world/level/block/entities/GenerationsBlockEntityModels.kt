package generations.gg.generations.core.generationscore.common.world.level.block.entities

import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.resources.ResourceLocation

object GenerationsBlockEntityModels {
    val DEFAULT: ResourceLocation = GenerationsCore.id("")
    val ABUNDANT_SHRINE: ResourceLocation = block("shrines/abundant_shrine.pk")
    val BOX: ResourceLocation = block("decorations/box/box.pk")
    @JvmField
    val CELESTIAL_ALTAR: ResourceLocation = block("shrines/celestial_altar.pk")
    @JvmField
    val COOKING_POT: ResourceLocation = block("utility_blocks/cooking_pot.pk")
    val MOLTRES_SHRINE: ResourceLocation = block("shrines/moltres_shrine.pk")
    val ARTICUNO_SHRINE: ResourceLocation = block("shrines/articuno_shrine.pk")
    @JvmField
    val GROUDON_SHRINE: ResourceLocation = block("shrines/weather_trio/groudon_shrine.pk")
    val HOUSE_LAMP: ResourceLocation = block("decorations/house_lamp.pk")
    @JvmField
    val KYOGRE_SHRINE: ResourceLocation = block("shrines/weather_trio/kyogre_shrine.pk")
    @JvmField
    val LUGIA_SHRINE: ResourceLocation = block("shrines/lugia_shrine.pk")
    val DARK_MODEL: ResourceLocation = block("shrines/lunar_shrine/dark_shrine.pk")
    val LIGHT_MODEL: ResourceLocation = block("shrines/lunar_shrine/light_shrine.pk")
    @JvmField
    val MELOETTA_MUSIC_BOX: ResourceLocation = block("shrines/meloetta_music_box.pk")
    val PASTEL_BEAN_BAG: ResourceLocation = block("decorations/pastel_bean_bag.pk")
    val ROTOM_PC: ResourceLocation = block("utility_blocks/rotom_pc.pk")
    val TABLE_PC: ResourceLocation = block("utility_blocks/table_pc.pk")
    val POKECENTER_SCARLET_SIGN: ResourceLocation = block("decorations/pokecenter_scarlet_sign.pk")
    val RAYQUAZA_SHRINE: ResourceLocation = block("shrines/weather_trio/groudon_shrine.pk") //TODO: Change to Rayquaza when model is available
    @JvmField
    val REGICE_SHRINE: ResourceLocation = block("shrines/regis/regice_shrine.pk")
    @JvmField
    val REGIDRAGO_SHRINE: ResourceLocation = block("shrines/regis/regidrago_shrine.pk")
    @JvmField
    val REGIELEKI_SHRINE: ResourceLocation = block("shrines/regis/regieleki_shrine.pk")
    @JvmField
    val REGIGIGAS_SHRINE: ResourceLocation = block("shrines/regis/regigigas_shrine.pk")
    @JvmField
    val REGIROCK_SHRINE: ResourceLocation = block("shrines/regis/regirock_shrine.pk")
    @JvmField
    val REGISTEEL_SHRINE: ResourceLocation = block("shrines/regis/registeel_shrine.pk")
    val SNORLAX_BEAN_BAG: ResourceLocation = block("decorations/snorlax_bean_bag.pk")
    val ZAPDOS_SHRINE: ResourceLocation = block("shrines/zapdos_shrine.pk")
    @JvmField
    val SWITCH: ResourceLocation = block("decorations/switch.pk")
    @JvmField
    val TAO_TRIO_SHRINE: ResourceLocation = block("shrines/tao_trio_shrine.pk")
    @JvmField
    val TAPU_SHRINE: ResourceLocation = block("shrines/tapu_shrine.pk")
    @JvmField
    val TIME_SPACE_ALTAR: ResourceLocation = block("shrines/creation_trio/time_space_altar.pk")
    @JvmField
    val TRASH_CAN: ResourceLocation = block("decorations/trash_can.pk")
    @JvmField
    val VENDING_MACHINE: ResourceLocation = block("utility_blocks/vending_machine.pk")
    val BENCH: ResourceLocation = block("decorations/bench.pk")
    @JvmField
    val SHELF: ResourceLocation = block("decorations/shelf.pk")
    val BOOKSHELF: ResourceLocation = block("decorations/bookshelf.pk")
    val COUCH: ResourceLocation = block("decorations/couch.pk")
    val DESK: ResourceLocation = block("decorations/desk.pk")
    val STREET_LAMP: ResourceLocation = block("decorations/street_lamp.pk")
    @JvmField
    val DOUBLE_STREET_LAMP: ResourceLocation = block("decorations/double_street_lamp.pk")
    val FLOOR_CUSHION: ResourceLocation = block("decorations/floor_cushion.pk")
    @JvmField
    val FRIDGE: ResourceLocation = block("decorations/fridge.pk")
    val LITWICK_CANDLE: ResourceLocation = block("decorations/litwick_candle.pk")
    val LITWICK_CANDLES: ResourceLocation = block("decorations/litwick_candles.pk")
    @JvmField
    val POKEBALL_PILLAR: ResourceLocation = block("decorations/pokeball_pillar.pk")
    @JvmField
    val BUSH: ResourceLocation = block("decorations/bush.pk")
    @JvmField
    val HDTV: ResourceLocation = block("decorations/hdtv.pk")
    val SWIVEL_CHAIR: ResourceLocation = block("decorations/swivel_chair.pk")
    @JvmField
    val WORK_DESK: ResourceLocation = block("decorations/work_desk.pk")
    @JvmField
    val SHOP: ResourceLocation = block("decorations/shop.pk")
    val BALL_DISPLAY: ResourceLocation = block("decorations/ball_display.pk")
    val POKEBALL: ResourceLocation = block("utility_blocks/pokeball.pk")
    @JvmField
    val BREEDER: ResourceLocation = block("utility_blocks/breeder/breeder.pk")
    val RKS_MACHINE: ResourceLocation = block("utility_blocks/rks_machine.pk")
    @JvmField
    val LIGHT_CRYSTAL: ResourceLocation = block("shrines/light_crystal.pk")
    @JvmField
    val DARK_CRYSTAL: ResourceLocation = block("shrines/dark_crystal.pk")
    @JvmField
    val SCARECROW: ResourceLocation = block("utility_blocks/scarecrow.pk")
    @JvmField
    val PRISON_BOTTLE: ResourceLocation = block("shrines/prison_bottle.pk")
    var HO_OH_SHRINE: ResourceLocation = block("shrines/ho_oh_shrine.pk")
    var PC: ResourceLocation = block("utility_blocks/pc.pk")

    fun block(path: String): ResourceLocation {
        return GenerationsCore.id("models/block/$path")
    }
}
