package generations.gg.generations.core.generationscore.client.screen;

import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;

public class SpawnerEditScreen extends Screen {

    private final BlockPos bePos;

    public SpawnerEditScreen(BlockPos pos) {
        super(GameNarrator.NO_TITLE);
        this.bePos = pos;
    }
}
