package generations.gg.generations.core.generationscore.common.world.container;

//public class CalyrexSteedContainer /*extends GenericChestContainer*/ {

//    public CalyrexSteedContainer(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
//        super(GenerationsContainers.CALYREX_STEED, containerId, playerInventory, buf);
//    }
//
//    public CalyrexSteedContainer(int containerId, Inventory playerInventory, GenericContainer container, int lockedSlot) {
//        super(GenerationsContainers.CALYREX_STEED, containerId, playerInventory, container, lockedSlot);
//    }
//
//    @Override
//    public Slot getSlot(Container container, int slot, int x, int y) {
//        if(container instanceof Inventory) return super.getSlot(container, slot, x, y);
//        return new PredicateSlotItemHandler(container, slot, x, y, itemStack -> itemStack.is(Items.CARROT));
//    }
//
//    @Override
//    public void save(Player player) {
//        var stack = playerInventory.getItem(this.getLocked());
//        if(stack.getItem() instanceof CalyrexSteedItem walkmon) {
//            walkmon.save((CalyrexSteedItem.CarrotHolder) getContainer(), stack);
//        }
//    }
//}
