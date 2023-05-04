package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

public class StatueEditorItem extends Item {
    public StatueEditorItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null && context.getPlayer() instanceof ServerPlayer serverPlayer) {
//            BlockPos pos = context.getClickedPos();
//            StatueEntity npcEntity = new StatueEntity(PokeModEntities.STATUE.get(), serverPlayer.level);
//            VoxelShape collisionShape = serverPlayer.level.getBlockState(pos).getBlockSupportShape(serverPlayer.level, pos);
//            double blockHeight = collisionShape.isEmpty() ? 0 : collisionShape.bounds().maxY;
//            npcEntity.setPos(pos.getX() + 0.5, pos.getY() + blockHeight, pos.getZ() + 0.5);
//            serverPlayer.level.addFreshEntity(npcEntity);
        }
        return InteractionResult.PASS;
    }
}
