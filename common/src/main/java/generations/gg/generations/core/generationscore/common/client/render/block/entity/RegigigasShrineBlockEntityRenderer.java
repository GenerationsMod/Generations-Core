package generations.gg.generations.core.generationscore.common.client.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.RegigigasShrineBlockEntity;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.*;
import java.util.function.BiConsumer;

import static java.lang.Math.*;

public class RegigigasShrineBlockEntityRenderer extends GeneralUseBlockEntityRenderer<RegigigasShrineBlockEntity> {
    public static final Map<Integer, Vector3f> map = Util.make(new HashMap<>(), m -> {
        BiConsumer<Integer, Integer> colorToVector3f = (id, color) -> m.put(id, GenerationsUtils.rgbFromInt(color));

        colorToVector3f.accept(0, 0x78AAC2);
        colorToVector3f.accept(1, 0x976C83);
        colorToVector3f.accept(2, 0x41382B);
        colorToVector3f.accept(3, 0x9F8C82);
        colorToVector3f.accept(4, 0x919191);
    });

    public RegigigasShrineBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(@NotNull RegigigasShrineBlockEntity blockEntity, float partialTick, @NotNull PoseStack stack, @NotNull MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        super.render(blockEntity, partialTick, stack, bufferSource, packedLight, packedOverlay);
        processOrbs(blockEntity);
    }

    public static void processOrbs(RegigigasShrineBlockEntity shrineBlock) {

        List<Vector3f> colors = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            if(!shrineBlock.getContainer().get(i).isEmpty()) {
                colors.add(map.get(i));
            }
        }

        if(colors.isEmpty()) return;

        Vec3 center = new Vec3(shrineBlock.getBlockPos().getX() + 0.5d, shrineBlock.getBlockPos().getY() + 0.5d, shrineBlock.getBlockPos().getZ() + 0.5d);
        double theta = (((Objects.requireNonNull(shrineBlock.getLevel()).getGameTime() % (double) 100) / (double) 100) * 2 * PI);

        double radius = 1.5;

        double dx = radius * Math.cos(theta);
        double dy = radius * Math.sin(theta);
        double dz = 0;

        for (int i = 0; i < colors.size(); i++) {
            Vector3f c = colors.get(i);
            double r = (i/(float) colors.size()) * 2 * PI;
            shrineBlock.getLevel().addAlwaysVisibleParticle(new DustParticleOptions(c, 1f), center.x + rotateX(r, dx, dy), center.y + dz, center.z + rotateY(r, dx, dy), 0.0D, 0.0D, 0.0D);
        }
    }

    public static double rotateX(double r, double x, double y) {
        return cos(r) * x - sin(r) * y;
    }

    public static double rotateY(double r, double x, double y) {
        return sin(r) * x + cos(r) * y;
    }
}
