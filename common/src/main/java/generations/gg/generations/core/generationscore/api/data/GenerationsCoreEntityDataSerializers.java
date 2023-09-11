package generations.gg.generations.core.generationscore.api.data;

import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;

public class GenerationsCoreEntityDataSerializers {
    public static final EntityDataSerializer<StatueEntity.StatueInfo> STATUE_INFO = EntityDataSerializer.simple((buf, data) -> data.serializeToByteBuf(buf), StatueEntity.StatueInfo::new);

    public static void init() {
        EntityDataSerializers.registerSerializer(STATUE_INFO);
    }
}
