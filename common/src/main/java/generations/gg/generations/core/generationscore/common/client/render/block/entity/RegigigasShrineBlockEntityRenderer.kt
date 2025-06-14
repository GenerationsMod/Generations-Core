package generations.gg.generations.core.generationscore.common.client.render.block.entity

import com.mojang.blaze3d.vertex.PoseStack
import generations.gg.generations.core.generationscore.common.GenerationsStorage.REGI_ORBS
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils.rgbFromInt
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.RegigigasShrineBlockEntity
import net.minecraft.Util
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.core.particles.DustParticleOptions
import net.minecraft.world.item.Item
import net.minecraft.world.phys.Vec3
import org.joml.Vector3f
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

class RegigigasShrineBlockEntityRenderer(ctx: BlockEntityRendererProvider.Context) : GeneralUseBlockEntityRenderer<RegigigasShrineBlockEntity>(ctx) {
    override fun render(
        blockEntity: RegigigasShrineBlockEntity,
        partialTick: Float,
        stack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int
    ) {
        super.render(blockEntity, partialTick, stack, bufferSource, packedLight, packedOverlay)
        processOrbs(blockEntity)
    }

    companion object {
        val map: Map<Item, Vector3f> = Util.make(
            HashMap()
        ) { m: HashMap<Item, Vector3f> ->
            m[GenerationsItems.REGICE_ORB] = rgbFromInt(0x78AAC2)
            m[GenerationsItems.REGIROCK_ORB] = rgbFromInt(0XC7412B)
            m[GenerationsItems.REGISTEEL_ORB] = rgbFromInt(0X79797B)
            m[GenerationsItems.REGIDRAGO_ORB] = rgbFromInt(0x851534)
            m[GenerationsItems.REGIELEKI_ORB] = rgbFromInt(0XE0E731)
        }

        fun processOrbs(shrineBlock: RegigigasShrineBlockEntity) {
            val colors: MutableList<Vector3f> = ArrayList()

            val list = REGI_ORBS[shrineBlock]

            for (i in list.stacks().indices) {
                val color = map[list.stacks()[i].resource().item]

                if (color != null) colors.add(color)
            }

            if (colors.isEmpty()) return
            else {
                println(colors)
            }

            val center = Vec3(shrineBlock.blockPos.x + 0.5, shrineBlock.blockPos.y + 0.5, shrineBlock.blockPos.z + 0.5)
            val theta = ((shrineBlock.level!!.gameTime % 100.0) / 100.0) * 2 * Math.PI

            val radius = 1.5

            val dx = radius * cos(theta)
            val dy = radius * sin(theta)
            val dz = 0.0

            for (i in colors.indices) {
                val c = colors[i]
                val r = (i / colors.size.toFloat()) * 2 * Math.PI
                shrineBlock.level!!
                    .addAlwaysVisibleParticle(
                        DustParticleOptions(c, 1f),
                        center.x + rotateX(r, dx, dy),
                        center.y + dz,
                        center.z + rotateY(r, dx, dy),
                        0.0,
                        0.0,
                        0.0
                    )
            }
        }

        fun rotateX(r: Double, x: Double, y: Double): Double {
            return cos(r) * x - sin(r) * y
        }

        fun rotateY(r: Double, x: Double, y: Double): Double {
            return sin(r) * x + cos(r) * y
        }
    }
}
