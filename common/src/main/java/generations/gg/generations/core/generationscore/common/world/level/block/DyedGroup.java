package generations.gg.generations.core.generationscore.common.world.level.block;

import net.minecraft.core.Holder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public record DyedGroup(Map<DyeColor, Holder<Block>> block)  {
    public Block[] toArray() {
        return block().values().stream().map(Holder::value).map(a -> (Block) a).toArray(Block[]::new);
    }
}
