package generations.gg.generations.core.generationscore.common.client.screen

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.item.PokemonItem
import com.mojang.blaze3d.systems.RenderSystem
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.item.CameraItem
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import net.minecraft.client.DeltaTracker
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import net.minecraft.world.item.ItemStack

object Overlays {
    private val CAMERA_OVERLAY_BORDER: ResourceLocation = ResourceLocation.parse("generations_core:textures/gui/camera_overlay_border.png")
    private val CAMERA_OVERLAY_FOCUS: ResourceLocation = ResourceLocation.parse("generations_core:textures/gui/camera_overlay_focus.png")
    private val CAMERA_OVERLAY_MIDDLE: ResourceLocation = ResourceLocation.parse("generations_core:textures/gui/camera_overlay_middle.png")
    private val PUMPKIN_BLUR_LOCATION: ResourceLocation = ResourceLocation.parse("textures/misc/pumpkinblur.png")


    fun renderCamera(graphics: GuiGraphics, delta: DeltaTracker): Boolean {
        val player = Minecraft.getInstance().player
        if (Minecraft.getInstance().screen == null && player != null && player.mainHandItem.item is CameraItem) {
            var scale = 1.0f

            val width = graphics.guiWidth()
            val height = graphics.guiHeight()

            scale = if (width > 430) {
                if (height > 430) {
                    if (width > height) {
                        height / 430f
                    } else {
                        width / 430f
                    }
                } else {
                    height / 430f
                }
            } else {
                if (height > 430) {
                    width / 430f
                } else {
                    if (width > height) {
                        height / 430f
                    } else {
                        width / 430f
                    }
                }
            }

            val scaledWidth = 430f * scale
            val scaledHeight = 430f * scale
            val rectX = (graphics.guiWidth() - scaledWidth) / 2f
            val rectY = (graphics.guiHeight() - scaledHeight) / 2f



            ScreenUtils.drawRect(graphics, 0f, 0f, rectX, height.toFloat(), -0x1000000)
            ScreenUtils.drawRect(graphics, rectX, 0f, 430f, rectY, -0x1000000)
            ScreenUtils.drawRect(graphics, width - rectX, 0f, rectX, height.toFloat(), -0x1000000)
            ScreenUtils.drawRect(graphics, rectX, height - rectY, 430f, rectY, -0x1000000)

            graphics.pose().pushPose()
            graphics.pose().translate(rectX, rectY, 0f)
            graphics.pose().scale(scale, scale, 1.0f)


            if (player.inventory.hasAnyMatching { stack: ItemStack ->
                    stack.`is`(
                        GenerationsItems.FILM
                    )
                }) {
                if (player.inventory.freeSlot > -1) {
                    val value = player.cooldowns.getCooldownPercent(player.mainHandItem.item, delta.getGameTimeDeltaPartialTick(true))
                    renderTextureOverlay(graphics, CAMERA_OVERLAY_FOCUS, 1f, 1f, Mth.lerp(value, 1f, 0f))
                } else {
                    renderTextureOverlay(graphics, CAMERA_OVERLAY_FOCUS, 0.3f, 0.3f, 0.3f)
                }
            } else {
                renderTextureOverlay(graphics, CAMERA_OVERLAY_FOCUS, 0f, 0f, 0f)
            }

            renderTextureOverlay(graphics, CAMERA_OVERLAY_MIDDLE, 1.0f, 1.0f, 1.0f)
            renderTextureOverlay(graphics, CAMERA_OVERLAY_BORDER, 1.0f, 1.0f, 1.0f)

            graphics.pose().pushPose()

            graphics.pose().translate(22f, 99f, 0f)

            val pokemon = Minecraft.getInstance().crosshairPickEntity?.instanceOrNull<PokemonEntity>()?.pokemon

            if (pokemon != null) {
                graphics.pose().pushPose()
                graphics.pose().scale(3f, 3f, 1f)
                graphics.renderItem(PokemonItem.from(pokemon, 1), 0, 0)


                graphics.pose().popPose()
            }

            graphics.pose().translate(384f, 0f, 0f)

            ScreenUtils.drawTextWithHeight(
                graphics,
                player.inventory.items.stream().filter { a: ItemStack -> a.`is`(GenerationsItems.FILM) }
                    .mapToInt { obj: ItemStack -> obj.count }.sum().toString(),
                0f,
                0f,
                27f,
                -0x1,
                ScreenUtils.Position.RIGHT
            )

            graphics.pose().popPose()


            graphics.pose().popPose()

            return true
        }

        return false
    }

    private fun renderTextureOverlay(
        guiGraphics: GuiGraphics,
        location: ResourceLocation,
        r: Float,
        g: Float,
        b: Float
    ) {
        RenderSystem.disableDepthTest()
        RenderSystem.enableBlend()
        RenderSystem.depthMask(false)
        guiGraphics.setColor(r, g, b, 1.0f)
        guiGraphics.blit(location, 0, 0, -90, 0.0f, 0.0f, 430, 430, 430, 430)
        RenderSystem.depthMask(true)
        RenderSystem.enableDepthTest()
        RenderSystem.disableBlend()
        guiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f)
    }

    @JvmStatic
    fun render(guiGraphics: GuiGraphics, deltaTracker: DeltaTracker) {
        if (Minecraft.getInstance().options.cameraType.isFirstPerson) {
            checkNotNull(Minecraft.getInstance().player)
            if (!Minecraft.getInstance().player!!.isScoping) {
                if (!renderCamera(guiGraphics, deltaTracker)) renderPumpkin(
                    guiGraphics,
                    deltaTracker
                )
            }
        }
    }

    fun renderPumpkin(guiGraphics: GuiGraphics, deltaTracker: DeltaTracker?): Boolean {
        if (Minecraft.getInstance().player!!.inventory.getArmor(3)
                .`is`(GenerationsBlocks.CURSED_CARVED_PUMPKIN.value().asItem())
        ) {
            RenderSystem.disableDepthTest()
            RenderSystem.enableBlend()
            RenderSystem.depthMask(false)
            guiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f)
            guiGraphics.blit(
                PUMPKIN_BLUR_LOCATION,
                0,
                0,
                -90,
                0.0f,
                0.0f,
                guiGraphics.guiWidth(),
                guiGraphics.guiHeight(),
                guiGraphics.guiWidth(),
                guiGraphics.guiHeight()
            )
            RenderSystem.depthMask(true)
            RenderSystem.enableDepthTest()
            RenderSystem.disableBlend()
            guiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f)

            return true
        }

        return false
    }
}
