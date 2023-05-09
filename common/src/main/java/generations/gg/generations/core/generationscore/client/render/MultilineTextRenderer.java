package generations.gg.generations.core.generationscore.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.pokemod.pokemod.client.screen.ScreenUtils;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class MultilineTextRenderer {

    public static void drawCentered(PoseStack matrices, List<String> lines, int x, int y, int width, int color, boolean shadow) {
        var font = Minecraft.getInstance().font;

        for (var line : lines) {
            var separatedStrings = new ArrayList<String>();
            var split = line.split(" ");
            var lineString = new StringBuilder();

            for (int i = 0; i < split.length; i++) {
                String currentStr = split[i];
                String nextStr = split.length > i + 1 ? split[i + 1] : "";
                lineString.append(" ");
                lineString.append(currentStr);

                if (font.width(lineString + " " + nextStr) * 0.6f >= width || i == split.length - 1) {
                    separatedStrings.add(lineString.toString());
                    lineString = new StringBuilder();
                }
            }

            for (var s : separatedStrings) {
                ScreenUtils.drawScaledText(matrices, s, x, y, 0.6f, color, false);
                y += 6;
            }
        }
    }
}
