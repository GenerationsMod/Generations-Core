//package generations.gg.generations.core.generationscore.common.network.packets.statue;
//
//import generations.gg.generations.core.generationscore.common.GenerationsCore;
//import generations.gg.generations.core.generationscore.common.GenerationsCore;
//import generations.gg.generations.core.generationscore.common.GenerationsCore;
//import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
//import generations.gg.generations.core.generationscore.common.world.entity.StatueEntity;
//import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
//import generations.gg.generations.core.generationscore.common.world.entity.StatueEntity;
//import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
//import generations.gg.generations.core.generationscore.common.world.entity.StatueEntity;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.resources.ResourceLocation;
//import org.apache.logging.log4j.status.StatusData;
//
//public record S2CUpdateStatueInfoPacket(int entityId, StatueEntity.StatueInfo data) implements GenerationsNetworkPacket<S2CUpdateStatueInfoPacket> {
//    public static final ResourceLocation ID = GenerationsCore.id("client_update_statue_info");
//
//    public static S2CUpdateStatueInfoPacket decode(FriendlyByteBuf buf) {
//        return new S2CUpdateStatueInfoPacket(buf.readInt(), new StatueEntity.StatueInfo(buf));
//    }
//
//    @Override
//    public void encode(FriendlyByteBuf buf) {
//        buf.writeInt(this.entityId);
//        data.serializeToByteBuf(buf);
//    }
//
//    @Override
//    public ResourceLocation getId() {
//        return ID;
//    }
//
//}