package generations.gg.generations.core.generationscore.common.world.item;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public enum MailType {
    AIR(GenerationsItems.POKEMAIL_AIR_CLOSED),
    BLOOM(GenerationsItems.POKEMAIL_BLOOM_CLOSED),
    BRICK(GenerationsItems.POKEMAIL_BRICK_CLOSED),
    BRIDGE_D(GenerationsItems.POKEMAIL_BRIDGE_D_CLOSED),
    BRIDGE_M(GenerationsItems.POKEMAIL_BRIDGE_M_CLOSED),
    BRIDGE_S(GenerationsItems.POKEMAIL_BRIDGE_S_CLOSED),
    BRIDGE_T(GenerationsItems.POKEMAIL_BRIDGE_T_CLOSED),
    BRIDGE_V(GenerationsItems.POKEMAIL_BRIDGE_V_CLOSED),
    BUBBLE(GenerationsItems.POKEMAIL_BUBBLE_CLOSED),
    DREAM(GenerationsItems.POKEMAIL_DREAM_CLOSED),
    FAB(GenerationsItems.POKEMAIL_FAB_CLOSED),
    FAVORED(GenerationsItems.POKEMAIL_FAVORED_CLOSED),
    FLAME(GenerationsItems.POKEMAIL_FLAME_CLOSED),
    GLITTER(GenerationsItems.POKEMAIL_GLITTER_CLOSED),
    GRASS(GenerationsItems.POKEMAIL_GRASS_CLOSED),
    GREET(GenerationsItems.POKEMAIL_GREET_CLOSED),
    HARBOR(GenerationsItems.POKEMAIL_HARBOR_CLOSED),
    HEART(GenerationsItems.POKEMAIL_HEART_CLOSED),
    INQUIRY(GenerationsItems.POKEMAIL_INQUIRY_CLOSED),
    LIKE(GenerationsItems.POKEMAIL_LIKE_CLOSED),
    MECH(GenerationsItems.POKEMAIL_MECH_CLOSED),
    MOSAIC(GenerationsItems.POKEMAIL_MOSAIC_CLOSED),
    ORANGE(GenerationsItems.POKEMAIL_ORANGE_CLOSED),
    REPLY(GenerationsItems.POKEMAIL_REPLY_CLOSED),
    RETRO(GenerationsItems.POKEMAIL_RETRO_CLOSED),
    RSVP(GenerationsItems.POKEMAIL_RSVP_CLOSED),
    SHADOW(GenerationsItems.POKEMAIL_SHADOW_CLOSED),
    SNOW(GenerationsItems.POKEMAIL_SNOW_CLOSED),
    SPACE(GenerationsItems.POKEMAIL_SPACE_CLOSED),
    STEEL(GenerationsItems.POKEMAIL_STEEL_CLOSED),
    THANKS(GenerationsItems.POKEMAIL_THANKS_CLOSED),
    TROPIC(GenerationsItems.POKEMAIL_TROPIC_CLOSED),
    TUNNEL(GenerationsItems.POKEMAIL_TUNNEL_CLOSED),
    WAVE(GenerationsItems.POKEMAIL_WAVE_CLOSED),
    WOOD(GenerationsItems.POKEMAIL_WOOD_CLOSED);

    private final ResourceLocation location;
    private final RegistrySupplier<Item> sealed;

    MailType(RegistrySupplier<Item> sealed) {
        this.location = GenerationsCore.id("textures/gui/mail/" + name().toLowerCase().replace("_", "") + "mail.png");
        this.sealed = sealed;
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public RegistrySupplier<Item> getSealed() {
        return sealed;
    }
}
