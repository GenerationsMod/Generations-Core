package generations.gg.generations.core.generationscore.common.world.level.block.state.properties

import net.minecraft.sounds.SoundEvents
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.properties.BlockSetType

object GenerationsBlockSetTypes {
    //Wood BlockSetTypes
    @JvmField
    val ULTRA_DARK: BlockSetType = BlockSetType.register(BlockSetType("ultra_dark"))
    @JvmField
    val ULTRA_JUNGLE: BlockSetType = BlockSetType.register(BlockSetType("ultra_jungle"))
    @JvmField
    val GHOST: BlockSetType = BlockSetType.register(BlockSetType("ghost"))

    //Everything else
    @JvmField
    val ULTRA: BlockSetType = BlockSetType.register(
        BlockSetType(
            "ultra",
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            SoundType.STONE,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON
        )
    )
    val MARBLE: BlockSetType = BlockSetType.register(
        BlockSetType(
            "marble",
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            SoundType.STONE,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON
        )
    )
    val CHARGE_STONE: BlockSetType = BlockSetType.register(
        BlockSetType(
            "charge_stone",
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            SoundType.STONE,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON
        )
    )
    val VOLCANIC_STONE: BlockSetType = BlockSetType.register(
        BlockSetType(
            "volcanic_stone",
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            SoundType.STONE,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON
        )
    )
    val POKE_BRICK: BlockSetType = BlockSetType.register(
        BlockSetType(
            "poke_brick",
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            SoundType.STONE,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON
        )
    )

    val ENCHANTED_OBISIDIAN: BlockSetType = BlockSetType.register(
        BlockSetType(
            "enchanted_obsidian",
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            SoundType.STONE,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON
        )
    )
}
