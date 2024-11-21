package generations.gg.generations.core.generationscore.common.world.item.legends

import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import net.minecraft.world.item.ItemStack

open class ElementalPostBattleUpdateItemImpl(properties: Properties?, private val types: List<ElementalType>) :
    ElementalPostBattleUpdateItem(properties) {
    constructor(properties: Properties?, type: ElementalType) : this(properties, java.util.List.of<ElementalType>(type))

    override fun checkType(actor: PlayerBattleActor, stack: ItemStack, type: ElementalType): Boolean {
        return types.contains(type)
    }
}
