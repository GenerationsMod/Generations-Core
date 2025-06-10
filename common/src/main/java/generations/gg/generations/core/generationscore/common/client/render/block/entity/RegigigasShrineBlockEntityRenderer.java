package generations.gg.generations.core.generationscore.common.client.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.common.GenerationsStorage;
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.RegigigasShrineBlockEntity;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.*;
import java.util.function.BiConsumer;

import static java.lang.Math.*;

public class RegigigasShrineBlockEntityRenderer extends GeneralUseBlockEntityRenderer<RegigigasShrineBlockEntity> {
    public static final Map<Item, Vector3f> map = Util.make(new HashMap<>(), m -> {
        m.put(GenerationsItems.REGICE_ORB.get(), GenerationsUtils.rgbFromInt(0x78AAC2));
        m.put(GenerationsItems.REGIROCK_ORB.get(), GenerationsUtils.rgbFromInt(0XC7412B));
        m.put(GenerationsItems.REGISTEEL_ORB.get(), GenerationsUtils.rgbFromInt(0X79797B));
        m.put(GenerationsItems.REGIDRAGO_ORB.get(), GenerationsUtils.rgbFromInt(0x851534));
        m.put(GenerationsItems.REGIELEKI_ORB.get(), GenerationsUtils.rgbFromInt(0XE0E731));
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

        var list = GenerationsStorage.INSTANCE.getREGI_ORBS().get(shrineBlock);

        for (int i = 0; i < list.stacks().size(); i++) {
            var color = map.get(list.stacks().get(i).resource().getItem());

            if(color != null) colors.add(color);
        }

        if(colors.isEmpty()) return;
        else {
            System.out.println(colors);
        }

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
