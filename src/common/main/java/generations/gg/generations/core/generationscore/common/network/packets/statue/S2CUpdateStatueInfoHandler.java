//package generations.gg.generations.core.generationscore.common.network.packets.statue;
//
//import generations.gg.generations.core.generationscore.common.client.screen.statue.StatueEditorScreen;
//import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler;
//import generations.gg.generations.core.generationscore.common.world.entity.StatueEntity;
//import net.minecraft.client.Minecraft;
//import net.minecraft.world.entity.Entity;
//
//public class S2CUpdateStatueInfoHandler implements ClientNetworkPacketHandler<S2CUpdateStatueInfoPacket> {
//
//    @Override
//    public void handle(S2CUpdateStatueInfoPacket packet, Minecraft minecraft) {
//        if (Minecraft.getInstance().level == null)
//            return;
//
//        Entity entity = Minecraft.getInstance().level.getEntity(packet.entityId());
//
//        System.out.println("Recieved id" + packet.entityId() + " " + entity);
//
//        if (entity instanceof StatueEntity statueEntity) {
//            statueEntity.setStatueInfo(packet.data());
//
//            if(Minecraft.getInstance().screen instanceof StatueEditorScreen screen) {
//                screen.getStatue().setStatueInfo(packet.data());
//            }
//        }
//    }
//}
