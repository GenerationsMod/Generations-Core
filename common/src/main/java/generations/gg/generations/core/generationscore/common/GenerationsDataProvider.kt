package generations.gg.generations.core.generationscore.common

import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.api.data.DataProvider
import com.cobblemon.mod.common.api.data.DataRegistry
import com.cobblemon.mod.common.api.reactive.SimpleObservable.subscribe
import com.cobblemon.mod.common.platform.events.PlatformEvents
import com.cobblemon.mod.common.platform.events.ServerPlayerEvent.Logout.player
import com.cobblemon.mod.common.util.ifClient
import com.cobblemon.mod.common.util.server
import dev.architectury.platform.Platform
import dev.architectury.utils.Env
import dev.architectury.utils.EnvExecutor
import generations.gg.generations.core.generationscore.common.client.CompiledModelLoader
import generations.gg.generations.core.generationscore.common.network.packets.S2CUnlockReloadPacket
import generations.gg.generations.core.generationscore.common.world.shop.ShopPresets
import generations.gg.generations.core.generationscore.common.world.shop.Shops
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.ResourceManagerReloadListener
import java.util.*
import java.util.function.Consumer
import kotlin.jvm.functions.Function0
import kotlin.jvm.functions.Function0.invoke

class GenerationsDataProvider : DataProvider {
    // Both Forge n Fabric keep insertion order so if a registry depends on another simply register it after
    internal var canReload = true
    // Both Forge n Fabric keep insertion order so if a registry depends on another simply register it after
    private val registries = linkedSetOf<DataRegistry>()
    private val synchronizedPlayerIds = mutableListOf<UUID>()

    private val scheduledActions = mutableMapOf<UUID, MutableList<() -> Unit>>()

    fun registerDefaults() {
        this.register(Shops)
        this.register(ShopPresets)
        PlatformEvents.SERVER_PLAYER_LOGOUT.subscribe(Priority.HIGH) {
            synchronizedPlayerIds.remove(it.player.uuid)
            S2CUnlockReloadPacket().sendToPlayer(it.player)
        }

        ifClient {
            GenerationsCore.implementation.registerResourceReloader(GenerationsCore.id("client_resources"), SimpleResourceReloader(PackType.CLIENT_RESOURCES), PackType.CLIENT_RESOURCES, emptyList())
            GenerationsCore.implementation.registerResourceReloader(GenerationsCore.id("model_registry"), CompiledModelLoader(), PackType.CLIENT_RESOURCES, emptyList())
        }

        GenerationsCore.implementation.registerResourceReloader(GenerationsCore.id("data_resources"), SimpleResourceReloader(PackType.SERVER_DATA), PackType.SERVER_DATA, emptyList())
    }

    override fun doAfterSync(player: ServerPlayer, action: () -> Unit) {
        if (synchronizedPlayerIds.contains(player.uuid)) {
            action.invoke()
        } else {
            scheduledActions.computeIfAbsent(player.uuid) { mutableListOf() }.add(action)
        }
    }

    override fun <T : DataRegistry> register(registry: T): T {
        registries.add(registry)
        GenerationsCore.LOGGER.info("Registered the {} registry", registry.id)
        GenerationsCore.LOGGER.debug("Registered the {} registry of class {}", registry.id, registry.javaClass.canonicalName)
        return registry
    }

    override fun fromIdentifier(registryIdentifier: ResourceLocation): DataRegistry? {
        return registries.stream().filter { it: DataRegistry? -> it!!.id == registryIdentifier }.findAny().orElse(null)
    }

    override fun sync(player: ServerPlayer) {
        if (!player.connection.connection.isMemoryConnection) {
            registries.forEach(Consumer { registry: DataRegistry? -> registry!!.sync(player) })
        }

        //        CobblemonEvents.DATA_SYNCHRONIZED.emit(player)
        val waitingActions = scheduledActions.remove(player.uuid) ?: return
        waitingActions.forEach { it() }
    }


    internal inner class SimpleResourceReloader(private val type: PackType) : ResourceManagerReloadListener {
        override fun onResourceManagerReload(manager: ResourceManager) {
            // Check for a server running, this is due to the create a world screen triggering datapack reloads, these are fine to happen as many times as needed as players may be in the process of adding their datapacks.
            val isInGame = server() != null
            if (isInGame && this.type == PackType.SERVER_DATA && !INSTANCE.canReload) {
                return
            }

            registries.stream().filter { it: DataRegistry? -> it!!.type == this.type }
                .forEach { it: DataRegistry? -> it!!.reload(manager) }
            if (isInGame && this.type == PackType.SERVER_DATA) {
                canReload = false
            }
        }

        fun type(): PackType {
            return type
        }

        override fun equals(obj: Any?): Boolean {
            if (obj === this) return true
            if (obj == null || obj.javaClass != this.javaClass) return false
            val that = obj as SimpleResourceReloader
            return this.type == that.type
        }

        override fun hashCode(): Int {
            return Objects.hash(type)
        }

        override fun toString(): String {
            return "SimpleResourceReloader[" +
                    "type=" + type + ']'
        }
    }

    companion object {
        @JvmField
        val INSTANCE: GenerationsDataProvider = GenerationsDataProvider()
    }
}
