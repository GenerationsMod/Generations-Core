package generations.gg.generations.core.generationscore.world.npc.display;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class HeldItemsInfo {

    public static Codec<HeldItemsInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.CODEC.fieldOf("helmet").forGetter(HeldItemsInfo::getHelmet),
            ItemStack.CODEC.fieldOf("chestplate").forGetter(HeldItemsInfo::getChestplate),
            ItemStack.CODEC.fieldOf("leggings").forGetter(HeldItemsInfo::getLeggings),
            ItemStack.CODEC.fieldOf("boots").forGetter(HeldItemsInfo::getBoots),
            ItemStack.CODEC.fieldOf("mainhand").forGetter(HeldItemsInfo::getMainhand),
            ItemStack.CODEC.fieldOf("offhand").forGetter(HeldItemsInfo::getOffhand)
    ).apply(instance, HeldItemsInfo::new));

    private ItemStack helmet, chestplate, leggings, boots, mainhand, offhand;

    public HeldItemsInfo(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, ItemStack mainhand, ItemStack offhand) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.mainhand = mainhand;
        this.offhand = offhand;
    }

    public HeldItemsInfo(CompoundTag tag) {
        this(ItemStack.of(tag.getCompound("helmet")),
                ItemStack.of(tag.getCompound("chestplate")),
                ItemStack.of(tag.getCompound("leggings")),
                ItemStack.of(tag.getCompound("boots")),
                ItemStack.of(tag.getCompound("mainhand")),
                ItemStack.of(tag.getCompound("offhand")));
    }

    public HeldItemsInfo(FriendlyByteBuf buf) {
        this(buf.readItem(),
                buf.readItem(),
                buf.readItem(),
                buf.readItem(),
                buf.readItem(),
                buf.readItem());
    }

    public CompoundTag serializeToNbt() {
        CompoundTag tag = new CompoundTag();

        tag.put("helmet", GenerationsUtils.serializeStack(helmet));
        tag.put("chestplate", GenerationsUtils.serializeStack(chestplate));
        tag.put("leggings", GenerationsUtils.serializeStack(leggings));
        tag.put("boots", GenerationsUtils.serializeStack(boots));
        tag.put("mainhand", GenerationsUtils.serializeStack(mainhand));
        tag.put("offhand", GenerationsUtils.serializeStack(offhand));

        return tag;
    }

    public void serializeToByteBuf(FriendlyByteBuf buf) {
        buf.writeItem(helmet);
        buf.writeItem(chestplate);
        buf.writeItem(leggings);
        buf.writeItem(boots);
        buf.writeItem(mainhand);
        buf.writeItem(offhand);
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public ItemStack getMainhand() {
        return mainhand;
    }

    public ItemStack getOffhand() {
        return offhand;
    }

    public void setHelmet(ItemStack helmet) {
        this.helmet = helmet;
    }

    public void setChestplate(ItemStack chestplate) {
        this.chestplate = chestplate;
    }

    public void setLeggings(ItemStack leggings) {
        this.leggings = leggings;
    }

    public void setBoots(ItemStack boots) {
        this.boots = boots;
    }

    public void setMainhand(ItemStack mainhand) {
        this.mainhand = mainhand;
    }

    public void setOffhand(ItemStack offhand) {
        this.offhand = offhand;
    }

    public HeldItemsInfo copy() {
        return new HeldItemsInfo(helmet.copy(), chestplate.copy(), leggings.copy(), boots.copy(), mainhand.copy(), offhand.copy());
    }

    public void setHeldItem(EquipmentSlot equipmentslottype, ItemStack stack) {
        switch (equipmentslottype) {
            case MAINHAND -> setMainhand(stack);
            case OFFHAND -> setOffhand(stack);
            case FEET -> setBoots(stack);
            case LEGS -> setLeggings(stack);
            case CHEST -> setChestplate(stack);
            case HEAD -> setHelmet(stack);
        }
    }
}