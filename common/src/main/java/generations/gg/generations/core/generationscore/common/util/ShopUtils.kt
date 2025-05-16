package generations.gg.generations.core.generationscore.common.util

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.exceptions.CommandSyntaxException
import generations.gg.generations.core.generationscore.common.api.player.PlayerMoneyHandler
import generations.gg.generations.core.generationscore.common.world.entity.ShopOfferProvider
import generations.gg.generations.core.generationscore.common.world.shop.SimpleShopEntry
import net.minecraft.commands.arguments.item.ItemInput
import net.minecraft.commands.arguments.item.ItemParser
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistryAccess
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import java.math.BigDecimal
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

object ShopUtils {
    fun buy(player: Player, stack: ItemStack, perItemPrice: Int, amount: Int) {
        val `var` =
            Consumer { ab: Boolean ->
                if (ab) giveStack(player, stack)
            }
        takePokeDollars(player, perItemPrice * amount).thenAccept(`var`)
    }

    fun sell(player: Player, stack: ItemStack, perItemPrice: Int, amount: Int) {
        val `var` =
            Consumer { ab: Boolean ->
                if (ab) takeStack(player, stack)
            }

        if (hasAmount(player, stack, amount)) {
            givePokeDollars(player, perItemPrice * amount).thenAccept(`var`)
        }
    }

    fun giveStack(player: Player, itemstack: ItemStack) {
        if (itemstack.isEmpty) return

        val itemEntity = player.drop(itemstack, true, true)
        if (itemEntity != null) {
            itemEntity.setNoPickUpDelay()
            itemEntity.setTarget(player.uuid)
        }
    }

    fun giveStack(player: Player, itemstack: ItemStack, amount: Int) {
        var itemstack = itemstack
        if (itemstack.isEmpty) return

        itemstack = itemstack.copyWithCount(itemstack.count * amount)

        val itemEntity = player.drop(itemstack, true, true)
        if (itemEntity != null) {
            itemEntity.setNoPickUpDelay()
            itemEntity.setTarget(player.uuid)
        }
    }

    @JvmOverloads
    fun takeStack(player: Player, itemStack: ItemStack, neededCount: Int = 1) {
        var neededCount = neededCount
        if (itemStack.isEmpty) return

        neededCount *= itemStack.count

        for (stack in player.inventory.items) {
            if (matches(itemStack, stack)) {
                val count = stack.count
                stack.shrink(neededCount)
                neededCount -= count
            }

            if (neededCount <= 0) {
                break
            }
        }
    }

    @JvmStatic
    fun matches(stack1: ItemStack, stack2: ItemStack): Boolean {
        return ItemStack.isSameItem(stack1, stack2) && ItemStack.isSameItemSameComponents(stack1, stack2)
    }

    fun hasAmount(player: Player, itemStack: ItemStack): Boolean {
        if (itemStack.isEmpty) return true

        return getAmountInInventory(player, itemStack) >= itemStack.count
    }

    fun hasAmount(player: Player, itemStack: ItemStack, amount: Int): Boolean {
        if (itemStack.isEmpty) return true

        return getAmountInInventory(player, itemStack) >= (itemStack.count * amount)
    }

    fun hasItem(player: Player, itemStack: ItemStack): Boolean {
        if (itemStack.isEmpty) return true

        for (stack in player.inventory.items) {
            if (matches(itemStack, stack)) return true
        }

        return false
    }

    @JvmStatic
    fun getAmountInInventory(player: Player, itemStack: ItemStack): Int {
        if (itemStack.isEmpty) return 0

        var currentCount = 0
        for (stack in player.inventory.items) {
            if (matches(itemStack, stack)) currentCount += stack.count
        }

        return currentCount
    }

//    fun stringToStack(s: String?): ItemStack {
//        val items: HolderLookup<Item> =
//            RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY).lookupOrThrow(
//                Registries.ITEM
//            )
//        try {
//            val itemResult: ItemParser.ItemResult = ItemParser().parseForItem(items, StringReader(s))
//            val itemInput = ItemInput(itemResult.item(), itemResult.nbt())
//            return itemInput.createItemStack(1, false)
//        } catch (e: CommandSyntaxException) {
//            return ItemStack.EMPTY
//        }
//    }
//
//    fun stackToString(itemStack: ItemStack?): String {
//        return if (itemStack == null || itemStack.isEmpty) "minecraft:empty" else (BuiltInRegistries.ITEM.getKey(
//            itemStack.item
//        )
//            .toString() + (if (itemStack.hasTag()) itemStack.getTag().toString() else ""))
//    }

    //    public static boolean canAfford(Player player, int pokedollars) {
    //        return PlayerPokeDollars.of(player).getBalance() >= pokedollars;
    //    }
    fun givePokeDollars(player: Player, pokedollars: Int): CompletableFuture<Boolean> {
        return PlayerMoneyHandler.of(player).deposit(BigDecimal.valueOf(pokedollars.toLong()))
    }

    fun takePokeDollars(player: Player, pokedollars: Int): CompletableFuture<Boolean> {
        return PlayerMoneyHandler.of(player).withdraw(BigDecimal.valueOf(pokedollars.toLong()))
    }

    fun validateItemForProvider(provider: ShopOfferProvider, stack: ItemStack, price: Int, isBuy: Boolean): Boolean {
        if (provider.offers == null) return false

        return Arrays.stream(provider.offers!!.entries)
            .anyMatch { entry: SimpleShopEntry ->
                matches(entry.item, stack)
                        && entry.item.count == stack.count && (if (isBuy) entry.buyPrice == price else entry.sellPrice == price)
            }
    }
}