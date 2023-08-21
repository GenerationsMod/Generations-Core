package generations.gg.generations.core.generationscore.world.item;

import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class StatueEditorItem extends Item {
    public StatueEditorItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null && context.getPlayer() instanceof ServerPlayer serverPlayer) {
            BlockPos pos = context.getClickedPos();
            StatueEntity statueEntity = new StatueEntity(GenerationsEntities.STATUE_ENTITY.get(), serverPlayer.level());

            statueEntity.getStatueData().setOrientation(context.getHorizontalDirection().toYRot());

            statueEntity.setPos(Vec3.upFromBottomCenterOf(pos, 1));
            serverPlayer.level().addFreshEntity(statueEntity);
        }
        return InteractionResult.PASS;
    }
}
