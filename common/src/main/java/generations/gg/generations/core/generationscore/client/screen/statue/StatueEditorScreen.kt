package generations.gg.generations.core.generationscore.client.screen.statue

import com.cobblemon.mod.common.api.pokemon.PokemonProperties.Companion.parse
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.client.gui.drawProfilePokemon
import com.cobblemon.mod.common.client.gui.summary.widgets.ModelWidget
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.PoseType
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.mojang.blaze3d.platform.Lighting
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import generations.gg.generations.core.generationscore.GenerationsCore
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils
import generations.gg.generations.core.generationscore.client.screen.widget.AngleSelectionWidget
import generations.gg.generations.core.generationscore.client.screen.widget.ImageCheckbox
import generations.gg.generations.core.generationscore.network.GenerationsNetwork
import generations.gg.generations.core.generationscore.network.packets.statue.C2SUpdateStatueInfoPacket
import generations.gg.generations.core.generationscore.world.entity.StatueEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.components.EditBox
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.gui.screens.inventory.InventoryScreen
import net.minecraft.client.gui.screens.inventory.SmithingScreen
import net.minecraft.client.renderer.LightTexture
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import org.joml.Math
import org.joml.Quaternionf
import org.joml.Vector3f

class StatueEditorScreen(val statue: StatueEntity) : Screen(Component.empty()) {
    private var x = 0
    private var y = 0
    private var parserString: String
    private var orientationWidget: AbstractWidget? = null
    private var statickCheckbox: AbstractWidget? = null
    private var nameTextField: AbstractWidget? = null
    private var scaleTextField: AbstractWidget? = null
    private var materialTextField: AbstractWidget? = null
    private var timestampTextField: AbstractWidget? = null
    private var parserTextField: AbstractWidget? = null
    private var interactableCheckbox: AbstractWidget? = null
    private var modelWidget: AbstractWidget? = null
    private var updateButton: Button? = null
    private var poseTextField: EditBox? = null

    init {
        parserString = statue.statueData.properties.asString(" ")
    }

    override fun init() {
        height = 191
        x = width / 2 - 96
        y = height / 2 - 92
        val info = statue.statueData
        parserTextField = addRenderableWidget(
            ScreenUtils.createTextField(
                x + 7, y + 7, 125, 14, 500,
                parserString
            ) { s: String -> parserString = s })
        updateButton = addRenderableWidget(Button.builder(Component.literal("Update")) { button: Button? ->
            statue.statueData.properties = parse(parserString, " ", "=")
        }
            .size(51, 16).pos(x + 135, y + 6).build())
        nameTextField = addRenderableWidget(
            ScreenUtils.createTextField(
                x + 59,
                y + 92,
                126,
                14,
                50,
                info.label,
                { a: String? -> true }) { s: String? -> statue.statueData.label = s })
        poseTextField = addRenderableWidget(
            ScreenUtils.createTextField(
                x + 59,
                y + 110,
                126,
                14,
                50,
                info.poseType.toString(),
                { a: String? -> true }) { s: String? -> statue.statueData.setPosType(s) })
        timestampTextField = addRenderableWidget(
            ScreenUtils.createTextField(
                x + 59,
                y + 128,
                76,
                14,
                25,
                info.frame.toString(),
                { s: String ->
                    if (s.isEmpty()) {
                        return@createTextField true
                    }
                    try {
                        if (parseFloat(s) >= 0) {
                            return@createTextField true
                        }
                    } catch (ignored: Exception) {
                    }
                    false
                }) { s: String ->
                val value: Float = if (s.isEmpty()) 0f else parseFloat(s)
                statue.statueData.setProgress(value)
                updateStatueData()
            })
        scaleTextField = addRenderableWidget(
            ScreenUtils.createTextField(x + 59, y + 146, 36, 14, 5, info.scale.toString(),
                { s: String ->
                    if (s.isEmpty()) {
                        return@createTextField true
                    }
                    try {
                        if (parseFloat(s) >= 0) {
                            return@createTextField true
                        }
                    } catch (ignored: Exception) {
                    }
                    false
                }
            ) { s: String ->
                val scale = parseFloat(s)
                if (scale <= 0) {
                    statue.statueData.scale = 1.0f
                    updateStatueData()
                } else {
                    statue.statueData.scale = scale
                    updateStatueData()
                }
            })

        materialTextField = addRenderableWidget(
            ScreenUtils.createTextField(x + 59, y + 146 + 18, 126, 14, 500, info?.material()?.path ?: "", { true }) {
                it.takeIf { it.isNotEmpty() }.run {
                    statue.statueData.setMaterial(it)
                    updateStatueData()
                }
            }
        )

        statickCheckbox = addRenderableWidget(
            ImageCheckbox(
                x + 170, y + 127, 16, 16, TEXTURE, 0, 166,
                {
                    statue.statueData.setIsStatic(true)
                    updateStatueData()
                },
                {
                    statue.statueData.setIsStatic(false)
                    updateStatueData()
                },
                info.isStatic
            )
        )
        interactableCheckbox = addRenderableWidget(
            ImageCheckbox(
                x + 170, y + 145, 16, 16, TEXTURE, 0, 166,
                {
                    statue.statueData.isSacredAshInteractable = true
                    updateStatueData()
                },
                {
                    statue.statueData.isSacredAshInteractable = false
                    updateStatueData()
                },
                info.isSacredAshInteractable
            )
        )
        orientationWidget = addRenderableWidget(AngleSelectionWidget(
            x + 43, y + 47, 15, statue.statueData.orientation, 5, 0x000000
        ) { _: Float, angle: Float ->
            statue.statueData.orientation = Math.floor(angle)
            updateStatueData()
        })
        modelWidget = addRenderableWidget(
            ModelWidget(
                x + 122,
                y + 25,
                63,
                63,
                info.properties.asRenderablePokemon(),
                1.9090909f,
                -325f,
                -9.545454
            )
        )
    }

    private fun updateStatueData() {
        GenerationsCore.implementation.networkManager.sendPacketToServer(
            C2SUpdateStatueInfoPacket(
                statue.id,
                statue.statueData
            )
        )
    }

    override fun render(poseStack: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        poseStack.pose().pushPose()
        poseStack.pose().translate(x.toDouble(), y.toDouble(), 0.0)
        poseStack.pose().pushPose()
        poseStack.fill(122, 25, 122 + 63, 25 + 63, -0x1000000)
        poseStack.enableScissor(x + 122, y + 25, x + 122 + 63, y + 25 + 63)
        poseStack.pose().translate((63 / 2f).toDouble(), 63 - 5.0, 0.0)
        statue.delegate.setPose(statue.statueData.poseType.toString())
        var data = statue.statueData.asRenderablePokemon()

        var aspects = data.aspects

//        if(statue.statueData.material() != null) {
//            aspects = aspects.toMutableSet().let { it + (statue.statueData.material().toString()) }
//        }
//
//        drawProfilePokemon(data.species.resourceIdentifier,
//            aspects,
//            statue.statueData.poseType,
//            poseStack.pose(),
//            Quaternionf().rotationXYZ(Math.toRadians(13f), Math.toRadians(-35f), Math.toRadians(0f)),
//            statue.delegate,
//            partialTick,
//            1f
//        )
        poseStack.disableScissor()
        poseStack.pose().popPose()
        poseStack.blit(STATUE, 0, 0, 0f, 0f, 256, 191, 256, 256)
        poseStack.pose().popPose()
        super.render(poseStack, mouseX, mouseY, partialTick)
        ScreenUtils.drawText(
            poseStack,
            "Static:",
            (x + 168).toFloat(),
            (y + 131).toFloat(),
            0x5F5F60,
            ScreenUtils.Position.RIGHT
        )
        ScreenUtils.drawText(
            poseStack,
            "Interactable:",
            (x + 168).toFloat(),
            (y + 149).toFloat(),
            0x5F5F60,
            ScreenUtils.Position.RIGHT
        )
        ScreenUtils.drawText(
            poseStack,
            "Name:",
            (x + 56).toFloat(),
            (y + 95).toFloat(),
            0x5F5F60,
            ScreenUtils.Position.RIGHT
        )
        ScreenUtils.drawText(
            poseStack,
            "Animation",
            (x + 56).toFloat(),
            (y + 113).toFloat(),
            0x5F5F60,
            ScreenUtils.Position.RIGHT
        )
        ScreenUtils.drawText(
            poseStack,
            "Timestamp:",
            (x + 56).toFloat(),
            (y + 131).toFloat(),
            0x5F5F60,
            ScreenUtils.Position.RIGHT
        )
        ScreenUtils.drawText(
            poseStack,
            "Scale:",
            (x + 56).toFloat(),
            (y + 149).toFloat(),
            0x5F5F60,
            ScreenUtils.Position.RIGHT
        )
        ScreenUtils.drawText(
            poseStack,
            "Material:",
            (x + 56).toFloat(),
            (y + 167).toFloat(),
            0x5F5F60,
            ScreenUtils.Position.RIGHT
        )
        poseStack.drawString(font, "N", x + 56, y + 36, 0x000000, false)
        poseStack.drawString(font, "E", x + 78, y + 59, 0x000000, false)
        poseStack.drawString(font, "W", x + 34, y + 59, 0x000000, false)
        poseStack.drawString(font, "S", x + 56, y + 82, 0x000000, false)
        poseStack.drawString(
            font,
            "Orientation: " + String.format("%.2f", statue.statueData.orientation),
            x + 11,
            y + 24,
            0x5F5F60,
            false
        )
    }

    override fun onClose() {
        GenerationsNetwork.INSTANCE.sendToServer(C2SUpdateStatueInfoPacket(statue.id, statue.statueData))
        super.onClose()
    }

    override fun isPauseScreen(): Boolean {
        return false
    }

    companion object {
        private val TEXTURE = GenerationsCore.id("textures/gui/npc/customization.png")
        private val STATUE = GenerationsCore.id("textures/gui/statue/statue_gui.png")
        @Throws(NumberFormatException::class)
        fun parseFloat(s: String): Float {
            var s = s
            if (s.endsWith(".")) {
                s = s + "0"
            }
            return if (s.isEmpty()) 1.0f else s.toFloat()
        }

        @Throws(NumberFormatException::class)
        fun parseInt(s: String): Int {
            var s = s
            if (s.contains(".")) {
                s = s.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
            }
            return if (s.isEmpty()) 0 else s.toInt()
        }
    }

    fun drawProfilePokemon(
        species: ResourceLocation,
        aspects: Set<String>,
        poseType: PoseType,
        matrixStack: PoseStack,
        rotation: Quaternionf,
        state: PoseableEntityState<PokemonEntity>?,
        partialTicks: Float,
        scale: Float = 20F
    ) {
        val model = PokemonModelRepository.getPoser(species, aspects)
        val texture = PokemonModelRepository.getTexture(species, aspects, state?.animationSeconds ?: 0F)

        val context = RenderContext()
        PokemonModelRepository.getTextureNoSubstitute(species, aspects, 0f).let { it -> context.put(RenderContext.TEXTURE, it) }
        context.put(RenderContext.SCALE, PokemonSpecies.getByIdentifier(species)!!.getForm(aspects).baseScale)
        context.put(RenderContext.SPECIES, species)
        context.put(RenderContext.ASPECTS, aspects)

        val renderType = model.getLayer(texture, false, false)

        RenderSystem.applyModelViewMatrix()
        matrixStack.scale(scale, scale, -scale)

        if (state != null) {
            model.getPose(poseType)?.let { state.setPose(it.poseName) }
            state.timeEnteredPose = 0F
            state.updatePartialTicks(partialTicks)
            model.setupAnimStateful(null, state, 0F, 0F, 0F, 0F, 0F)
        } else {
            model.setupAnimStateless(poseType)
        }
        matrixStack.translate(model.profileTranslation.x, model.profileTranslation.y,  model.profileTranslation.z - 4.0)
        matrixStack.scale(model.profileScale, model.profileScale, 1 / model.profileScale)

        matrixStack.mulPose(rotation)
        Lighting.setupForEntityInInventory()
        val entityRenderDispatcher = Minecraft.getInstance().entityRenderDispatcher
        rotation.conjugate()
        entityRenderDispatcher.overrideCameraOrientation(rotation)
        entityRenderDispatcher.setRenderShadow(true)

        val bufferSource = Minecraft.getInstance().renderBuffers().bufferSource()
        val buffer = bufferSource.getBuffer(renderType)
        val light1 = Vector3f(-1F, 1F, 1.0F)
        val light2 = Vector3f(1.3F, -1F, 1.0F)
        RenderSystem.setShaderLights(light1, light2)
        val packedLight = LightTexture.pack(11, 7)

        model.withLayerContext(bufferSource, state, PokemonModelRepository.getLayers(species, aspects)) {
            model.render(context, matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F)
            bufferSource.endBatch()
        }
        model.setDefault()
        entityRenderDispatcher.setRenderShadow(true)
        Lighting.setupFor3DItems()
    }
}