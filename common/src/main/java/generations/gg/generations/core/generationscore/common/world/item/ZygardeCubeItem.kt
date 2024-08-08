package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature
import com.cobblemon.mod.common.util.asTranslated
import com.cobblemon.mod.common.util.party
import generations.gg.generations.core.generationscore.common.world.dialogue.DialogueGraph
import generations.gg.generations.core.generationscore.common.world.dialogue.DialoguePlayer
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.AbstractNode
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.ChooseNode
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.ConsumerNode
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.SayNode
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import java.util.function.Consumer

class ZygardeCubeItem(properties: Properties) : Item(properties),
    LangTooltip {
    override fun appendHoverText(
        stack: ItemStack,
        level: Level?,
        tooltipComponents: MutableList<Component>,
        isAdvanced: TooltipFlag
    ) {
        tooltipComponents.add(Component.translatable("item.zygarde_cell.tooltip"))
        tooltipComponents.add(Component.translatable("item.zygarde_cell.tooltip.lore1"))
        tooltipComponents.add(Component.translatable("item.zygarde_cell.tooltip.lore2", stack.damageValue, stack.maxDamage))
//        LangTooltip.appendHoverText(stack, level, tooltipComponents, isAdvanced)
    }

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {

        val stack = player.getItemInHand(usedHand)

        if (usedHand != InteractionHand.MAIN_HAND) return InteractionResultHolder.pass(stack)

        if(player is ServerPlayer && player.isShiftKeyDown) {
            val graph =
                DialogueGraph(
                    createDialogue(stack, player)
                )
            DialoguePlayer(
                graph,
                null,
                player,
                false
            )
            return InteractionResultHolder.success(stack)
        } else {
            return InteractionResultHolder.pass(stack)
        }
    }

    private fun createDialogue(stack: ItemStack, player: ServerPlayer): AbstractNode {
        var party = Cobblemon.storage.getParty(player)

        if(stack.damageValue == 0) return SayNode(
            listOf("You lack any zygarde cells."),
            null
        )


        var map = party.asSequence().filter { it.species.resourceIdentifier.path == "zygarde" }.filterNot { it.ability.name == "powerconstruct" }.flatMap {  it.aspects }.toList()
        var has10 = map.any { it.contains("zygrade-form-10%") }
        var has50 = map.any { it.contains("zygrade-form-50%") }

        var nodeMap = mutableMapOf<String, AbstractNode>()

        var choice =
            ChooseNode(
                "gui.zygarde_cube".asTranslated().string,
                nodeMap
            )

        if(stack.damageValue >= 10) {
            createMergeNode(map = nodeMap, 10)
        }

        if(stack.damageValue >= 40 && has10) {
            createMergeNode(nodeMap, 10, destination = 50, amount = 40)
        }
        if(stack.damageValue >= 50) {
            createMergeNode(nodeMap, 50)
            if(has50) createMergeNode(nodeMap, 10, destination = 50, amount = 50, powerconstruct = true)
        }

        if(stack.damageValue >= 90 && has10) {
            createMergeNode(nodeMap, 10, destination = 10, amount = 90, powerconstruct = true)
            createMergeNode(nodeMap, 10, destination = 50, amount = 90, powerconstruct = true)
        }

        if(stack.damageValue >= 100) {
            createMergeNode(nodeMap, 10, powerconstruct = true)
            createMergeNode(nodeMap, 50, powerconstruct = true)
        }

        return choice
    }

    fun createMergeNode(map: MutableMap<String, AbstractNode>, original: Int, destination: Int = -1, amount: Int = -1, powerconstruct: Boolean = false) {
        var ext = if(destination != -1 && amount != -1) "_with_" + amount + "_to_" + destination else ""
        var powerconstructName = if(powerconstruct) "_power_construct" else ""

        var name = "gui.zygarde_cube.merge_$original$ext$powerconstructName"

        var consumer: Consumer<ServerPlayer>

        if(ext.isEmpty()) {
            consumer = Consumer {
                val item = it.mainHandItem

                if (item.`is`(GenerationsItems.ZYGARDE_CUBE.get()) && item.damageValue >= amount) {
                    val store = Cobblemon.storage.getParty(it)


                    var pokemon = PokemonProperties.parse("species=zygarde level=70" + if(original == 10) " zygarde_form=10%" else "" + if(powerconstruct) " ability=powerconstruct" else "").create()

                    item.hurt(amount, it.random, it)

//                    it.sendSystemMessage(("$name.accept").asTranslated(), false)
                    store.add(pokemon)
                }
            }
        } else {
            consumer = Consumer { System.out.println("Oh No!") }
        }

        map["$name.name".asTranslated().string] =
            SayNode(
                listOf("$name.accept".asTranslated().string),
                ConsumerNode(
                    consumer
                )
            )
    }

    fun createZygardeTen(player: ServerPlayer) {
        val item = player.mainHandItem

        if (item.`is`(GenerationsItems.ZYGARDE_CUBE.get()) && item.damageValue >= 10) {
            val store = Cobblemon.storage.getParty(player)

            val pokemon = PokemonSpecies.getByName("zygarde")?.let { PokemonProperties.parse("species:zygarde lvl=70 zygarde=10%") }?.create() ?: return

            item.hurtAndBreak(10, player) {}

            player.sendSystemMessage("pixelmon.zygarde_machine.interact.merge10".asTranslated(), false)
            store.add(pokemon)
        }
    }

    fun merg(player: ServerPlayer) {
        val item = player.mainHandItem

        if (item.`is`(GenerationsItems.ZYGARDE_CUBE.get()) && item.damageValue >= 50) {
            val store = Cobblemon.storage.getParty(player)

            val pokemon = PokemonSpecies.getByName("zygarde")?.let { PokemonProperties.parse("species:zygarde lvl=70") }?.create() ?: return

            item.hurtAndBreak(50, player) {}

            player.sendSystemMessage("pixelmon.zygarde_machine.interact.merge50".asTranslated(), false)
            store.add(pokemon)
        }
    }

    fun combineTen(player: ServerPlayer) {
        val item = player.mainHandItem

        if (item.`is`(GenerationsItems.ZYGARDE_CUBE.get()) && item.damageValue >= 40) {

            val party = player.party()

            var pokemon = party.filterNotNull().filter { it.species.resourceIdentifier.path == "zygarde" }
                .firstOrNull { it.aspects.contains("10%") }
                ?.let { it -> it.features.removeIf { it.name == "zygarde" && it is StringSpeciesFeature && it.value == "10%" } }
                ?: return

            item.hurtAndBreak(40, player) {}

            player.sendSystemMessage("pixelmon.zygarde_machine.interact.merge10with40".asTranslated(), false)
        }
    }

    companion object {
        const val FULL = 100
    }
}
