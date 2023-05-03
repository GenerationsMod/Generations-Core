package com.pokemod.pokemod.world.item;

import com.pokemod.pokemod.PokeMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public enum MailType {
    AIR(() -> PokeModItems.POKEMAIL_AIR_CLOSED),
    BLOOM(() -> PokeModItems.POKEMAIL_BLOOM_CLOSED),
    BRICK(() -> PokeModItems.POKEMAIL_BRICK_CLOSED),
    BRIDGE_D(() -> PokeModItems.POKEMAIL_BRIDGE_D_CLOSED),
    BRIDGE_M(() -> PokeModItems.POKEMAIL_BRIDGE_M_CLOSED),
    BRIDGE_S(() -> PokeModItems.POKEMAIL_BRIDGE_S_CLOSED),
    BRIDGE_T(() -> PokeModItems.POKEMAIL_BRIDGE_T_CLOSED),
    BRIDGE_V(() -> PokeModItems.POKEMAIL_BRIDGE_V_CLOSED),
    BUBBLE(() -> PokeModItems.POKEMAIL_BUBBLE_CLOSED),
    DREAM(() -> PokeModItems.POKEMAIL_DREAM_CLOSED),
    FAB(() -> PokeModItems.POKEMAIL_FAB_CLOSED),
    FAVORED(() -> PokeModItems.POKEMAIL_FAVORED_CLOSED),
    FLAME(() -> PokeModItems.POKEMAIL_FLAME_CLOSED),
    GLITTER(() -> PokeModItems.POKEMAIL_GLITTER_CLOSED),
    GRASS(() -> PokeModItems.POKEMAIL_GRASS_CLOSED),
    GREET(() -> PokeModItems.POKEMAIL_GREET_CLOSED),
    HARBOR(() -> PokeModItems.POKEMAIL_HARBOR_CLOSED),
    HEART(() -> PokeModItems.POKEMAIL_HEART_CLOSED),
    INQUIRY(() -> PokeModItems.POKEMAIL_INQUIRY_CLOSED),
    LIKE(() -> PokeModItems.POKEMAIL_LIKE_CLOSED),
    MECH(() -> PokeModItems.POKEMAIL_MECH_CLOSED),
    MOSAIC(() -> PokeModItems.POKEMAIL_MOSAIC_CLOSED),
    ORANGE(() -> PokeModItems.POKEMAIL_ORANGE_CLOSED),
    REPLY(() -> PokeModItems.POKEMAIL_REPLY_CLOSED),
    RETRO(() -> PokeModItems.POKEMAIL_RETRO_CLOSED),
    RSVP(() -> PokeModItems.POKEMAIL_RSVP_CLOSED),
    SHADOW(() -> PokeModItems.POKEMAIL_SHADOW_CLOSED),
    SNOW(() -> PokeModItems.POKEMAIL_SNOW_CLOSED),
    SPACE(() -> PokeModItems.POKEMAIL_SPACE_CLOSED),
    STEEL(() -> PokeModItems.POKEMAIL_STEEL),
    THANKS(() -> PokeModItems.POKEMAIL_THANKS_CLOSED),
    TROPIC(() -> PokeModItems.POKEMAIL_TROPIC_CLOSED),
    TUNNEL(() -> PokeModItems.POKEMAIL_TUNNEL_CLOSED),
    WAVE(() -> PokeModItems.POKEMAIL_WAVE_CLOSED),
    WOOD(() -> PokeModItems.POKEMAIL_WOOD_CLOSED);

    private final ResourceLocation location;
    private final Supplier<RegistryObject<Item>> sealed;

    MailType(Supplier<RegistryObject<Item>> sealed) {
        this.location = PokeMod.id("textures/gui/mail/" + name().toLowerCase().replace("_", "") + "mail.png");
        this.sealed = sealed;
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public Supplier<RegistryObject<Item>> getSealed() {
        return sealed;
    }
}
