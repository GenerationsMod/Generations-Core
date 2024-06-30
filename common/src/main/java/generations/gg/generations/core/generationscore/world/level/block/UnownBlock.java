package generations.gg.generations.core.generationscore.world.level.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class UnownBlock extends Block {
    private final String glyph;

    public UnownBlock(String glyph, BlockBehaviour.Properties properties) {
        super(properties);
        this.glyph = glyph;
    }

    public String getGlyph() {
        return glyph;
    }
}
