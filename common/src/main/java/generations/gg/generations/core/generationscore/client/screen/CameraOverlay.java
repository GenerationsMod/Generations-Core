package generations.gg.generations.core.generationscore.client.screen;

import com.cobblemon.mod.common.client.render.item.PokemonItemRenderer;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.item.PokemonItem;
import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.world.item.CameraItem;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class CameraOverlay {
    private static final ResourceLocation CAMERA_OVERLAY_BORDER = new ResourceLocation("generations_core:textures/gui/camera_overlay_border.png");
    private static final ResourceLocation CAMERA_OVERLAY_FOCUS = new ResourceLocation("generations_core:textures/gui/camera_overlay_focus.png");
    private static final ResourceLocation CAMERA_OVERLAY_MIDDLE = new ResourceLocation("generations_core:textures/gui/camera_overlay_middle.png");



    public static boolean renderCamera(GuiGraphics graphics, float delta) {
        var player = Minecraft.getInstance().player;
        if(Minecraft.getInstance().screen == null && player != null && player.getMainHandItem().getItem() instanceof CameraItem) {
            var scale = 1.0f;

            var width = graphics.guiWidth();
            var height = graphics.guiHeight();

            if(width > 430) {
                if(height > 430) {
                    if(width > height) {
                        scale = height / 430f;
                    } else {
                        scale = width / 430f;
                    }
                } else {
                    scale = height / 430f;
                }
            } else {
                if (height > 430) {
                    scale = width / 430f ;
                } else {
                    if(width > height) {
                        scale = height / 430f;
                    } else {
                        scale = width / 430f;
                    }
                }
            }

            var scaledWidth = 430f * scale;
            var scaledHeight = 430f * scale;
            var rectX = (graphics.guiWidth() - scaledWidth) / 2f;
            var rectY = (graphics.guiHeight() - scaledHeight) / 2f;



            ScreenUtils.drawRect(graphics, 0, 0, rectX, height, 0xff000000);
            ScreenUtils.drawRect(graphics, rectX, 0, 430, rectY, 0xff000000);
            ScreenUtils.drawRect(graphics, width - rectX, 0, rectX, height, 0xff000000);
            ScreenUtils.drawRect(graphics, rectX, height - rectY, 430, rectY, 0xff000000);

            graphics.pose().pushPose();
            graphics.pose().translate(rectX, rectY, 0);
            graphics.pose().scale(scale, scale, 1.0f);


            if(player.getInventory().hasAnyMatching(stack -> stack.is(GenerationsItems.FILM.get()))) {
                if(player.getInventory().getFreeSlot() > -1) {
                    var value = player.getCooldowns().getCooldownPercent(player.getMainHandItem().getItem(), delta);
                    renderTextureOverlay(graphics, CAMERA_OVERLAY_FOCUS, 1f, 1f, Mth.lerp(value, 1f, 0f));
                } else {
                    renderTextureOverlay(graphics, CAMERA_OVERLAY_FOCUS, 0.3f, 0.3f, 0.3f);
                }
            }
            else {
                renderTextureOverlay(graphics, CAMERA_OVERLAY_FOCUS, 0f, 0f, 0f);
            }

            renderTextureOverlay(graphics, CAMERA_OVERLAY_MIDDLE, 1.0f, 1.0f, 1.0f);
            renderTextureOverlay(graphics, CAMERA_OVERLAY_BORDER, 1.0f, 1.0f, 1.0f);

            graphics.pose().pushPose();

            graphics.pose().translate(22, 99, 0f);

            if(Minecraft.getInstance().crosshairPickEntity instanceof PokemonEntity pokemon) {
                graphics.pose().pushPose();
                graphics.pose().scale(3,3, 1);
                graphics.renderItem(PokemonItem.from(pokemon.getPokemon(), 1), 0, 0);


                graphics.pose().popPose();
            }

            graphics.pose().translate(384, 0, 0f);

            ScreenUtils.drawTextWithHeight(graphics, String.valueOf(player.getInventory().items.stream().filter(a -> a.is(GenerationsItems.FILM.get())).mapToInt(ItemStack::getCount).sum()), 0, 0, 27, 0xffffffff, ScreenUtils.Position.RIGHT);

            graphics.pose().popPose();


            graphics.pose().popPose();

            return true;
        }

        return false;
    }

    private static void renderTextureOverlay(GuiGraphics guiGraphics, ResourceLocation location, float r, float g, float b) {
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.depthMask(false);
        guiGraphics.setColor(r, g, b, 1.0f);
        guiGraphics.blit(location, 0, 0, -90, 0.0F, 0.0F, 430, 430, 430, 430);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
