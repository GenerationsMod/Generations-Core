package generations.gg.generations.core.generationscore.common.client.screen.statue

import com.cobblemon.mod.common.api.pokemon.PokemonProperties.Companion.parse
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.client.gui.summary.widgets.ModelWidget
import com.cobblemon.mod.common.client.render.models.blockbench.PosableState
//import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.PoseType
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.mojang.blaze3d.platform.Lighting
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.entity.StatueClientDelegate
import generations.gg.generations.core.generationscore.common.client.screen.ScreenUtils
import generations.gg.generations.core.generationscore.common.client.screen.widget.AngleSelectionWidget
import generations.gg.generations.core.generationscore.common.client.screen.widget.ImageCheckbox
import generations.gg.generations.core.generationscore.common.network.packets.statue.UpdateStatuePacket
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.components.EditBox
import net.minecraft.client.gui.screens.Screen
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
    private var modelWidget: ModelWidget? = null
    private var updateButton: Button? = null
    private var poseTextField: EditBox? = null

    init {
        parserString = statue.properties.asString()
    }

    override fun init() {
        height = 191
        x = width / 2 - 96
        y = height / 2 - 92

        parserTextField = addRenderableWidget(
            ScreenUtils.createTextField(
                x + 7, y + 7, 125, 14, 500,
                parserString
            ) { s: String -> parserString = s })
        updateButton = addRenderableWidget(Button.builder(Component.literal("Update")) { button: Button? ->


            statue.properties = parse(parserString, " ", "=")
            modelWidget?.pokemon = statue.properties.asRenderablePokemon()
            UpdateStatuePacket.Properties(statue.id, statue.properties).sendToServer()
        }
            .size(51, 16).pos(x + 135, y + 6).build())
        nameTextField = addRenderableWidget(
            ScreenUtils.createTextField(
                x + 59,
                y + 92,
                126,
                14,
                50,
                statue.label ?: "",
                { true }) { s: String? -> run {
                    statue.label = s
                    UpdateStatuePacket.Label(statue.id, s).sendToServer()
                }
            })
        poseTextField = addRenderableWidget(
            ScreenUtils.createTextField(
                x + 59,
                y + 110,
                126,
                14,
                50,
                statue.poseType.toString(),
                { true }, fun(s: String?) {
                    this.run {
                        val poseType = s?.uppercase()?.takeIf { PoseType.entries.map { it.name }.any { a -> it == a } }?.let { PoseType.valueOf(it) }?.run {
                            statue.poseType = this
                            UpdateStatuePacket.PoseType(statue.id, this).sendToServer()
                        }
                    }
                })
        )
        timestampTextField = addRenderableWidget(
            ScreenUtils.createTextField(
                x + 59,
                y + 128,
                76,
                14,
                25,
                "${statue.staticAge + statue.staticPartial}",
                { s: String ->
                    if (s.isEmpty()) return@createTextField true
                    try {
                        if (parseFloat(s) >= 0) return@createTextField true
                    } catch (ignored: Exception) {}
                    false
                }) { s: String ->
                val value: Float = if (s.isEmpty()) 0f else parseFloat(s)
                statue.staticAge = value.floor().toInt()
                statue.staticPartial = value % 1F

                UpdateStatuePacket.StaticAge(statue.id, statue.staticAge).sendToServer()
                UpdateStatuePacket.StaticPartial(statue.id, statue.staticPartial).sendToServer()
            })
        scaleTextField = addRenderableWidget(
            ScreenUtils.createTextField(x + 59, y + 146, 36, 14, 5, statue.scale.toString(),
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
                var scale = parseFloat(s).coerceIn(0.5f, 5.0f)

                statue.scale = scale
                UpdateStatuePacket.Scale(statue.id, scale).sendToServer()
            })

        materialTextField = addRenderableWidget(
            ScreenUtils.createTextField(x + 59, y + 146 + 18, 126, 14, 500, statue.material ?: "", { true }) {
                it.takeIf { it.isNotEmpty() }.run {
                    statue.material = it
                    UpdateStatuePacket.Material(statue.id, it).sendToServer()
                }
            }
        )

        statickCheckbox = addRenderableWidget(
            ImageCheckbox(
                x + 170, y + 127, 16, 16, TEXTURE, 0, 166,
                {
                    statue.staticToggle = true
                    UpdateStatuePacket.StaticToggle(statue.id, true).sendToServer()
                },
                {
                    statue.staticToggle = false
                    UpdateStatuePacket.StaticToggle(statue.id, false).sendToServer()
                },
                statue.staticToggle
            )
        )
        interactableCheckbox = addRenderableWidget(
            ImageCheckbox(
                x + 170, y + 145, 16, 16, TEXTURE, 0, 166,
                {
                    statue.interactable = true
                    UpdateStatuePacket.Interactable(statue.id, true).sendToServer()
                },
                {
                    statue.interactable = false
                    UpdateStatuePacket.Interactable(statue.id, false).sendToServer()
                },
                statue.interactable
            )
        )
        orientationWidget = addRenderableWidget(AngleSelectionWidget(
            x + 43, y + 47, 15, statue.yRot, 5, 0x000000
        ) { _: Float, angle: Float ->
            statue.yRot = Math.floor(angle)
            UpdateStatuePacket.Orientation(statue.id, statue.yRot).sendToServer()
        })
        modelWidget = addRenderableWidget(
            ModelWidget(
                x + 122,
                y + 25,
                63,
                63,
                statue.properties.asRenderablePokemon(),
                1.9090909f,
                -325f,
                -9.545454
            )
        )
    }

    override fun render(poseStack: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        poseStack.pose().pushPose()
        poseStack.pose().translate(x.toDouble(), y.toDouble(), 0.0)
        poseStack.blit(STATUE, 0, 0, 0f, 0f, 256, 191, 256, 256)

        poseStack.pose().pushPose()
        poseStack.fill(122, 25, 122 + 63, 25 + 63, -0x1000000)
        poseStack.enableScissor(x + 122, y + 25, x + 122 + 63, y + 25 + 63)
        poseStack.pose().translate((63 / 2f).toDouble(), 63 - 5.0, 0.0)
        (statue.delegate as StatueClientDelegate).setPose(statue.poseType.toString())
//        var data = statue.renderablePokemon()

//        var aspects = data.aspects

//        if(statue.statueData.material() != null) {
//            aspects = aspects.toMutableSet().let { it + (statue.statueData.material().toString()) }
//        }

//        drawProfilePokemon(data.species.resourceIdentifier,
//            aspects,
//            statue.poseType,
//            poseStack.pose(),
//            Quaternionf().rotationXYZ(Math.toRadians(13f), Math.toRadians(-35f), Math.toRadians(0f)),
//            statue.delegate,
//            partialTick,
//            1f
//        )
        poseStack.disableScissor()
        poseStack.pose().popPose()

        poseStack.pose().popPose()
        super.render(poseStack, mouseX, mouseY, partialTick)
        ScreenUtils.drawText(poseStack, "Static:", (x + 168).toFloat(), (y + 131).toFloat(), 0x5F5F60, ScreenUtils.Position.RIGHT)
        ScreenUtils.drawText(poseStack, "Interactable:", (x + 168).toFloat(), (y + 149).toFloat(), 0x5F5F60, ScreenUtils.Position.RIGHT)
        ScreenUtils.drawText(poseStack, "Name:", (x + 56).toFloat(), (y + 95).toFloat(), 0x5F5F60, ScreenUtils.Position.RIGHT)
        ScreenUtils.drawText(poseStack, "Animation", (x + 56).toFloat(), (y + 113).toFloat(), 0x5F5F60, ScreenUtils.Position.RIGHT)
        ScreenUtils.drawText(poseStack, "Timestamp:", (x + 56).toFloat(), (y + 131).toFloat(), 0x5F5F60, ScreenUtils.Position.RIGHT)
        ScreenUtils.drawText(poseStack, "Scale:", (x + 56).toFloat(), (y + 149).toFloat(), 0x5F5F60, ScreenUtils.Position.RIGHT)
        ScreenUtils.drawText(poseStack, "Material:", (x + 56).toFloat(), (y + 167).toFloat(), 0x5F5F60, ScreenUtils.Position.RIGHT)
        poseStack.drawString(font, "N", x + 56, y + 36, 0x000000, false)
        poseStack.drawString(font, "E", x + 78, y + 59, 0x000000, false)
        poseStack.drawString(font, "W", x + 34, y + 59, 0x000000, false)
        poseStack.drawString(font, "S", x + 56, y + 82, 0x000000, false)
        poseStack.drawString(font, "Orientation: " + String.format("%.2f", statue.yRot), x + 11, y + 24, 0x5F5F60, false)
    }

    override fun isPauseScreen(): Boolean {
        return false
    }

    companion object {
        private val TEXTURE = GenerationsCore.id("textures/gui/npc/customization.png")
        private val STATUE = GenerationsCore.id("textures/gui/statue/statue_gui.png")
        @Throws(NumberFormatException::class)
        fun parseFloat(value: String): Float {
            var s = value
            if (s.endsWith(".")) {
                s += "0"
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
//
//    fun drawProfilePokemon(
//        species: ResourceLocation,
//        aspects: Set<String>,
//        poseType: PoseType,
//        matrixStack: PoseStack,
//        rotation: Quaternionf,
//        state: PosableState,
//        partialTicks: Float,
//        scale: Float = 20F
//    ) {
//        println("Blep: $species $aspects")
//        val model = PokemonModelRepository.getPoser(species, aspects)
//        val texture = PokemonModelRepository.getTexture(species, aspects, state?.animationSeconds ?: 0F)
//
//        val context = RenderContext()
//        PokemonModelRepository.getTextureNoSubstitute(species, aspects, 0f).let { it -> context.put(RenderContext.TEXTURE, it) }
//        context.put(RenderContext.SCALE, PokemonSpecies.getByIdentifier(species)!!.getForm(aspects).baseScale)
//        context.put(RenderContext.SPECIES, species)
//        context.put(RenderContext.ASPECTS, aspects)
//
//        val renderType = model.getLayer(texture, false, false)
//
//        RenderSystem.applyModelViewMatrix()
//        matrixStack.scale(scale, scale, -scale)
//
//        if (state != null) {
//            model.getPose(poseType)?.let { state.setPose(it.poseName) }
//            state.timeEnteredPose = 0F
//            state.updatePartialTicks(partialTicks)
//            model.setupAnimStateful(null, state, 0F, 0F, 0F, 0F, 0F)
//        } else {
//            model.setupAnimStateless(poseType)
//        }
//        matrixStack.translate(model.profileTranslation.x, model.profileTranslation.y,  model.profileTranslation.z - 4.0)
//        matrixStack.scale(model.profileScale, model.profileScale, 1 / model.profileScale)
//
//        matrixStack.mulPose(rotation)
//        Lighting.setupForEntityInInventory()
//        val entityRenderDispatcher = Minecraft.getInstance().entityRenderDispatcher
//        rotation.conjugate()
//        entityRenderDispatcher.overrideCameraOrientation(rotation)
//        entityRenderDispatcher.setRenderShadow(true)
//
//        val bufferSource = Minecraft.getInstance().renderBuffers().bufferSource()
//        val buffer = bufferSource.getBuffer(renderType)
//        val light1 = Vector3f(-1F, 1F, 1.0F)
//        val light2 = Vector3f(1.3F, -1F, 1.0F)
//        RenderSystem.setShaderLights(light1, light2)
//        val packedLight = LightTexture.pack(11, 7)
//
//        model.withLayerContext(bufferSource, state, PokemonModelRepository.getLayers(species, aspects)) {
//            model.render(context, matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F)
//            bufferSource.endBatch()
//        }
//        model.setDefault()
//        entityRenderDispatcher.setRenderShadow(true)
//        Lighting.setupFor3DItems()
//    }
}

fun Float.floor(): Float = Math.floor(this)
