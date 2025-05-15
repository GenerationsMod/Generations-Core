package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.world.item.legends.PostBattleUpdatingWithItem
import net.minecraft.world.item.Item

class SecretArmorScroll(properties: Item.Properties): PostBattleUpdatingWithItem(properties.stacksTo(1).durability(100), LegendKeys.KUBFU, "pixelmon.secret_armor_scoll.amountfull", { player, stack, battle -> battle.isNpc() }) {
    override fun spawnedLevel(): Int = 10
}