package generations.gg.generations.core.generationscore.world.level.block.entities;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class GenerationsBlockEntityModels {
    public static final ResourceLocation DEFAULT = GenerationsCore.id("");
    public static final ResourceLocation ABUNDANT_SHRINE = block("shrines/abundant_shrine.pk");
    public static final ResourceLocation BOX = block("decorations/box/box.pk");
    public static final ResourceLocation CELESTIAL_ALTAR = block("shrines/celestial_altar.pk");
    public static final ResourceLocation COOKING_POT = block("utility_blocks/cooking_pot.pk");
    public static final ResourceLocation MOLTRES_SHRINE = block("shrines/moltres_shrine.pk");
    public static final ResourceLocation ARTICUNO_SHRINE = block("shrines/articuno_shrine.pk");
    public static final ResourceLocation GROUDON_SHRINE = block("shrines/weather_trio/groudon_shrine.pk");
    public static final ResourceLocation HOUSE_LAMP = block("decorations/house_lamp.pk");
    public static final ResourceLocation KYOGRE_SHRINE = block("shrines/weather_trio/kyogre_shrine.pk");
    public static final ResourceLocation LUGIA_SHRINE = block("shrines/lugia_shrine.pk");
    public static final ResourceLocation DARK_MODEL = block("shrines/lunar_shrine/dark_shrine.pk");
    public static final ResourceLocation LIGHT_MODEL = block("shrines/lunar_shrine/light_shrine.pk");
    public static final ResourceLocation MELOETTA_MUSIC_BOX = block("shrines/meloetta_music_box.pk");
    public static final ResourceLocation PASTEL_BEAN_BAG = block("decorations/pastel_bean_bag.pk");
    public static final ResourceLocation ROTOM_PC = block("utility_blocks/rotom_pc.pk");
    public static final ResourceLocation TABLE_PC = block("utility_blocks/table_pc.pk");
    public static final ResourceLocation POKECENTER_SCARLET_SIGN = block("decorations/pokecenter_scarlet_sign.pk");
    public static final ResourceLocation RAYQUAZA_SHRINE = block("shrines/weather_trio/groudon_shrine.pk"); //TODO: Change to Rayquaza when model is available
    public static final ResourceLocation REGICE_SHRINE = block("shrines/regis/regice_shrine.pk");
    public static final ResourceLocation REGIDRAGO_SHRINE = block("shrines/regis/regidrago_shrine.pk");
    public static final ResourceLocation REGIELEKI_SHRINE = block("shrines/regis/regieleki_shrine.pk");
    public static final ResourceLocation REGIGIGAS_SHRINE = block("shrines/regis/regigigas_shrine.pk");
    public static final ResourceLocation REGIROCK_SHRINE = block("shrines/regis/regirock_shrine.pk");
    public static final ResourceLocation REGISTEEL_SHRINE = block("shrines/regis/registeel_shrine.pk");
    public static final ResourceLocation SNORLAX_BEAN_BAG = block("decorations/snorlax_bean_bag.pk");
    public static final ResourceLocation ZAPDOS_SHRINE = block("shrines/zapdos_shrine.pk");
    public static final ResourceLocation SWITCH = block("decorations/switch.pk");
    public static final ResourceLocation TAO_TRIO_SHRINE = block("shrines/tao_trio_shrine.pk");
    public static final ResourceLocation TAPU_SHRINE = block("shrines/tapu_shrine.pk");
    public static final ResourceLocation TIME_SPACE_ALTAR = block("shrines/creation_trio/time_space_altar.pk");
    public static final ResourceLocation TRASH_CAN  = block("decorations/trash_can.pk");
    public static final ResourceLocation VENDING_MACHINE = block("utility_blocks/vending_machine.pk");
    public static final ResourceLocation BENCH = block("decorations/bench.pk");
    public static final ResourceLocation SHELF = block("decorations/shelf.pk");
    public static final ResourceLocation BOOKSHELF = block("decorations/bookshelf.pk");
    public static final ResourceLocation COUCH = block("decorations/couch.pk");
    public static final ResourceLocation DESK = block("decorations/desk.pk");
    public static final ResourceLocation STREET_LAMP = block("decorations/street_lamp.pk");
    public static final ResourceLocation DOUBLE_STREET_LAMP = block("decorations/double_street_lamp.pk");
    public static final ResourceLocation FLOOR_CUSHION = block("decorations/floor_cushion.pk");
    public static final ResourceLocation FRIDGE = block("decorations/fridge.pk");
    public static final ResourceLocation LITWICK_CANDLE = block("decorations/litwick_candle.pk");
    public static final ResourceLocation LITWICK_CANDLES = block("decorations/litwick_candles.pk");
    public static final ResourceLocation POKEBALL_PILLAR = block("decorations/pokeball_pillar.pk");
    public static final ResourceLocation BUSH = block("decorations/bush.pk");
    public static final ResourceLocation HDTV = block("decorations/hdtv.pk");
    public static final ResourceLocation SWIVEL_CHAIR = block("decorations/swivel_chair.pk");
    public static final ResourceLocation WORK_DESK = block("decorations/work_desk.pk");
    public static final ResourceLocation SHOP = block("decorations/shop.pk");
    public static final ResourceLocation BALL_DISPLAY = block("decorations/ball_display.pk");
    public static final ResourceLocation POKEBALL = block("utility_blocks/pokeball.pk");
    public static final ResourceLocation BREEDER = block("utility_blocks/breeder/breeder.pk");
    public static final ResourceLocation RKS_MACHINE = block("utility_blocks/rks_machine.pk");
    public static final ResourceLocation LIGHT_CRYSTAL = block("shrines/light_crystal.pk");
    public static final ResourceLocation DARK_CRYSTAL = block("shrines/dark_crystal.pk");
    public static final ResourceLocation SCARECROW = block("utility_blocks/scarecrow.pk");
    public static ResourceLocation HO_OH_SHRINE = block("shrines/ho_oh_shrine.pk");;

    public static ResourceLocation block(String path) {
        return GenerationsCore.id("models/block/" + path);
    }
}
