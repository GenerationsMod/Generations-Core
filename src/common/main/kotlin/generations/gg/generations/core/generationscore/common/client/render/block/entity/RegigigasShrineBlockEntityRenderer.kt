package generations.gg.generations.core.generationscore.common.client.render.block.entity

import com.mojang.blaze3d.vertex.PoseStack
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils.rgbFromInt
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.id
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.RegigigasShrineBlockEntity
import net.minecraft.Util
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.core.particles.DustParticleOptions
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.phys.Vec3
import org.joml.Vector3f
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
        val map: Map<ResourceLocation, Vector3f> = Util.make(
            HashMap()
        ) { m ->
            m[GenerationsItems.REGICE_ORB.id] = rgbFromInt(0x78AAC2)
            m[GenerationsItems.REGIROCK_ORB.id] = rgbFromInt(0XC7412B)
            m[GenerationsItems.REGISTEEL_ORB.id] = rgbFromInt(0X79797B)
            m[GenerationsItems.REGIDRAGO_ORB.id] = rgbFromInt(0x851534)
            m[GenerationsItems.REGIELEKI_ORB.id] = rgbFromInt(0XE0E731)
        }

        fun processOrbs(shrineBlock: RegigigasShrineBlockEntity) {
            val colors: MutableList<Vector3f> = ArrayList()

            val list = shrineBlock.container

            for (i in list.items.indices) {
                val color = map[list.items[i].item.builtInRegistryHolder().id]

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
