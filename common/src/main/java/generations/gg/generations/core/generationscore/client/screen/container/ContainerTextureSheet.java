package generations.gg.generations.core.generationscore.client.screen.container;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.NineSlice;
import generations.gg.generations.core.generationscore.client.screen.SubTexture;
import net.minecraft.resources.ResourceLocation;

public class ContainerTextureSheet {
    private static final ResourceLocation GENERIC_CONTAINER = GenerationsCore.id("textures/gui/container/generic_container.png" +
            "");

    public static SubTexture SLOT = new SubTexture(4, 4, 18, 18, GENERIC_CONTAINER, 256);
    public static NineSlice BACKGROUND = new NineSlice(26, 4, 18, 18, GENERIC_CONTAINER, 256, 6,6,6,6);
}
