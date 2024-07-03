package generations.gg.generations.core.generationscore.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.world.item.CameraItem;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;

import java.util.function.Predicate;

public class CameraOverlay {
    private static final ResourceLocation CAMERA_OVERLAY = new ResourceLocation("generations_core:textures/gui/camera_overlay.png");

    public static boolean renderCamera(GuiGraphics graphics, float delta) {
        var player = Minecraft.getInstance().player;
        if(Minecraft.getInstance().screen == null && player != null && player.getMainHandItem().getItem() instanceof CameraItem) {
            var value = player.getCooldowns().getCooldownPercent(player.getMainHandItem().getItem(), delta);

            if(player.getInventory().hasAnyMatching(stack -> stack.is(GenerationsItems.FILM.get())))

                renderTextureOverlay(graphics,1f, 1f, Mth.lerp(value, 1f, 0f));
            else {
                renderTextureOverlay(graphics, 0f, 0f, 0f);
            }

            return true;
        }

        return false;
    }

    private static void renderTextureOverlay(GuiGraphics guiGraphics, float r, float g, float b) {
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.depthMask(false);
        guiGraphics.setColor(r, g, b, 1.0f);
        guiGraphics.blit(CAMERA_OVERLAY, 0, 0, -90, 0.0F, 0.0F, guiGraphics.guiWidth(), guiGraphics.guiHeight(), guiGraphics.guiWidth(), guiGraphics.guiHeight());
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
