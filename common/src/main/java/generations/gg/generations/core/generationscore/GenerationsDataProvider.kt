package generations.gg.generations.core.generationscore

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.api.data.DataProvider
import com.cobblemon.mod.common.api.data.DataRegistry
import com.cobblemon.mod.common.platform.events.PlatformEvents
import generations.gg.generations.core.generationscore.world.dialogue.Dialogues
import com.cobblemon.mod.common.util.ifClient
import com.cobblemon.mod.common.util.server
import generations.gg.generations.core.generationscore.GenerationsCore.LOGGER
import generations.gg.generations.core.generationscore.GenerationsCore.id
import generations.gg.generations.core.generationscore.network.S2CUnlockReloadPacket
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.ResourceManagerReloadListener
import java.util.*

object GenerationsDataProvider : DataProvider {

    // Both Forge n Fabric keep insertion order so if a registry depends on another simply register it after
    public var canReload = true
    // Both Forge n Fabric keep insertion order so if a registry depends on another simply register it after
    private val registries = linkedSetOf<DataRegistry>()
    private val synchronizedPlayerIds = mutableListOf<UUID>()

    private val scheduledActions = mutableMapOf<UUID, MutableList<() -> Unit>>()

    fun registerDefaults() {
        this.register(Dialogues.instance())

        PlatformEvents.SERVER_PLAYER_LOGOUT.subscribe {
            synchronizedPlayerIds.remove(it.player.uuid)
            S2CUnlockReloadPacket().sendToPlayer(it.player)
        }

        ifClient {
            Cobblemon.implementation.registerResourceReloader(id("client_resources"), SimpleResourceReloader(PackType.CLIENT_RESOURCES), PackType.CLIENT_RESOURCES, emptyList())
        }
        Cobblemon.implementation.registerResourceReloader(id("data_resources"), SimpleResourceReloader(PackType.SERVER_DATA), PackType.SERVER_DATA, emptyList())
    }

    override fun <T : DataRegistry> register(registry: T): T {
        // Only send message once
        if (this.registries.isEmpty()) {
            LOGGER.info("Note: Cobblemon data registries are only loaded once per server instance as Pokémon species are not safe to reload.")
        }
        this.registries.add(registry)
        LOGGER.info("Registered the {} registry", registry.id.toString())
        LOGGER.debug("Registered the {} registry of class {}", registry.id.toString(), registry::class.qualifiedName)
        return registry
    }

    override fun fromIdentifier(registryIdentifier: ResourceLocation): DataRegistry? = this.registries.find { it.id == registryIdentifier }

    override fun sync(player: ServerPlayer) {
        if (!player.connection.connection.isMemoryConnection) {
            this.registries.forEach { registry -> registry.sync(player) }
        }

//        CobblemonEvents.DATA_SYNCHRONIZED.emit(player)
        val waitingActions = this.scheduledActions.remove(player.uuid) ?: return
        waitingActions.forEach { it() }
    }

    override fun doAfterSync(player: ServerPlayer, action: () -> Unit) {
        if (player.uuid in synchronizedPlayerIds) {
            action()
        } else {
            this.scheduledActions.computeIfAbsent(player.uuid) { mutableListOf() }.add(action)
        }
    }

    private class SimpleResourceReloader(private val type: PackType) : ResourceManagerReloadListener {
        override fun onResourceManagerReload(manager: ResourceManager) {

            // Check for a server running, this is due to the create a world screen triggering datapack reloads, these are fine to happen as many times as needed as players may be in the process of adding their datapacks.
            val isInGame = server() != null
            if (isInGame && this.type == PackType.SERVER_DATA && !canReload) {
                return
            }
            registries.filter { it.type == this.type }
                .forEach { it.reload(manager) }
            if (isInGame && this.type == PackType.SERVER_DATA) {
                canReload = false
            }
        }

    }
}
