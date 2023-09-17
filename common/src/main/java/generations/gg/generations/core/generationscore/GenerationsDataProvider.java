package generations.gg.generations.core.generationscore;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.data.DataProvider;
import com.cobblemon.mod.common.api.data.DataRegistry;
import com.cobblemon.mod.common.platform.events.PlatformEvents;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import generations.gg.generations.core.generationscore.network.packets.S2CUnlockReloadPacket;
import generations.gg.generations.core.generationscore.world.dialogue.Dialogues;
import generations.gg.generations.core.generationscore.world.npc.NpcPresets;
import generations.gg.generations.core.generationscore.world.shop.ShopPresets;
import generations.gg.generations.core.generationscore.world.shop.Shops;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.cobblemon.mod.common.util.DistributionUtilsKt.server;
import static generations.gg.generations.core.generationscore.GenerationsCore.LOGGER;
import static generations.gg.generations.core.generationscore.GenerationsCore.id;
import static java.util.Collections.emptyList;

public class GenerationsDataProvider implements DataProvider {
    public static final GenerationsDataProvider INSTANCE = new GenerationsDataProvider();

    // Both Forge n Fabric keep insertion order so if a registry depends on another simply register it after
    public boolean canReload = true;
    // Both Forge n Fabric keep insertion order so if a registry depends on another simply register it after
    private Set<DataRegistry> registries = new HashSet<>();
    private List<UUID> synchronizedPlayerIds = new ArrayList<>();

    private final Map<UUID, List<@NotNull Function0<Unit>>> scheduledActions = new HashMap<>();

    public void registerDefaults() {
        this.register(Dialogues.instance());
        this.register(Shops.instance());
        this.register(ShopPresets.instance());
        this.register(NpcPresets.instance());

        PlatformEvents.SERVER_PLAYER_LOGOUT.subscribe(Priority.HIGH, it -> {
            synchronizedPlayerIds.remove(it.getPlayer().getUUID());
            new S2CUnlockReloadPacket().sendToPlayer(it.getPlayer());
            return Unit.INSTANCE;
        });

        EnvExecutor.runInEnv(Env.CLIENT, () -> () ->
                GenerationsCore.implementation.registerResourceReloader(
                        id("client_resources"),
                        new SimpleResourceReloader(PackType.CLIENT_RESOURCES),
                        PackType.CLIENT_RESOURCES,
                        emptyList()
                ));

        GenerationsCore.implementation.registerResourceReloader(id("data_resources"), new SimpleResourceReloader(PackType.SERVER_DATA), PackType.SERVER_DATA, emptyList());
    }

    public void doAfterSync(ServerPlayer player, @NotNull Function0<Unit> action) {
        if (synchronizedPlayerIds.contains(player.getUUID())) {
            action.invoke();
        } else {
            this.scheduledActions.computeIfAbsent(player.getUUID(), it -> new ArrayList<>()).add(action);
        }
    }

    public <T extends DataRegistry> T register(T registry) {
//         Only send message once
//        if (this.registries.isEmpty()) {
//            LOGGER.info("Note: Cobblemon data registries are only loaded once per server instance as Pokémon species are not safe to reload.");
//        }
        this.registries.add(registry);
        LOGGER.info("Registered the {} registry", registry.getId().toString());
        LOGGER.debug("Registered the {} registry of class {}", registry.getId().toString(), registry.getClass().getCanonicalName());
        return registry;
    }

    public DataRegistry fromIdentifier(ResourceLocation registryIdentifier) {
        return this.registries.stream().filter(it -> it.getId().equals(registryIdentifier)).findAny().orElse(null);
    }

    public void sync(ServerPlayer player) {
        if (!player.connection.connection.isMemoryConnection()) {
            this.registries.forEach(registry -> registry.sync(player));
        }

//        CobblemonEvents.DATA_SYNCHRONIZED.emit(player)
        var waitingActions = this.scheduledActions.remove(player.getUUID());
        if (waitingActions == null) return;
        waitingActions.forEach(Function0::invoke);
    }


    class SimpleResourceReloader implements ResourceManagerReloadListener {
        private final PackType type;

        SimpleResourceReloader(PackType type) {
            this.type = type;
        }

        @Override
            public void onResourceManagerReload(ResourceManager manager) {
                // Check for a server running, this is due to the create a world screen triggering datapack reloads, these are fine to happen as many times as needed as players may be in the process of adding their datapacks.
                var isInGame = server() != null;
                if (isInGame && this.type == PackType.SERVER_DATA && !GenerationsDataProvider.INSTANCE.canReload) {
                    return;
                }

                GenerationsDataProvider.this.registries.stream().filter(it -> it.getType() == this.type).forEach(it -> it.reload(manager));
                if (isInGame && this.type == PackType.SERVER_DATA) {
                    canReload = false;
                }
            }

        public PackType type() {
            return type;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (SimpleResourceReloader) obj;
            return Objects.equals(this.type, that.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type);
        }

        @Override
        public String toString() {
            return "SimpleResourceReloader[" +
                    "type=" + type + ']';
        }

        }
}
