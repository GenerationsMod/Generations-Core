package generations.gg.generations.core.generationscore.world.dialogue;

import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.*;

public class DialogueNodeTypes {
    public static final Registrar<DialogueNodeType<?>> DIALOGUE_NODE_TYPES = RegistrarManager.get(GenerationsCore.MOD_ID).<DialogueNodeType<?>>builder(GenerationsCore.id("dialogue_nodes")).build();

    public static final RegistrySupplier<DialogueNodeType<SayNode>> SAY = register("say", SayNode.CODEC, SayNode.class);
    public static final RegistrySupplier<DialogueNodeType<ChooseNode>> CHOOSE = register("choose", ChooseNode.CODEC, ChooseNode.class);
    public static final RegistrySupplier<DialogueNodeType<SwapStarterPicksNode>> SWAP_STARTER_PICKS = register("swap_starter_picks", SwapStarterPicksNode.CODEC, SwapStarterPicksNode.class);
    public static final RegistrySupplier<DialogueNodeType<OpenShopNode>> OPEN_SHOP = register("open_shop", OpenShopNode.CODEC, OpenShopNode.class);
    public static final RegistrySupplier<DialogueNodeType<HealNode>> HEAL = register("heal", HealNode.CODEC, HealNode.class);
    public static final RegistrySupplier<DialogueNodeType<GiveStarterNode>> GIVE_STARTER = register("give_starter", GiveStarterNode.CODEC, GiveStarterNode.class);
    public static final RegistrySupplier<DialogueNodeType<SpawnPokemonNode>> SPAWN_PIXELMON = register("spawn_pokemon", SpawnPokemonNode.CODEC, SpawnPokemonNode.class);

    public static <T extends AbstractNode> RegistrySupplier<DialogueNodeType<T>> register(String name, Codec<T> codec, Class<T> clazz) {
        return DIALOGUE_NODE_TYPES.register(GenerationsCore.id(name), () -> new DialogueNodeType<T>(codec, clazz));
    }

    public static void onInitialize() {
    }
}
