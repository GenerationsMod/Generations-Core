package generations.gg.generations.core.generationscore.compat.forge;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class VanillaCompatImpl {

    public static void registerStrippable(@NotNull Block log, @NotNull Block stripped) {
        if (!log.defaultBlockState().hasProperty(RotatedPillarBlock.AXIS))
            throw new IllegalArgumentException("Input block is missing required 'AXIS' property!");
        if (!stripped.defaultBlockState().hasProperty(RotatedPillarBlock.AXIS))
            throw new IllegalArgumentException("Result block is missing required 'AXIS' property!");
        if (AxeItem.STRIPPABLES instanceof ImmutableMap)
            AxeItem.STRIPPABLES = new HashMap<>(AxeItem.STRIPPABLES);

        AxeItem.STRIPPABLES.put(log, stripped);
    }
    public static void registerFlammable(@NotNull Block blockIn, int encouragement, int flammability) {
        ((FireBlock) Blocks.FIRE).setFlammable(blockIn, encouragement, flammability);
    }
}
