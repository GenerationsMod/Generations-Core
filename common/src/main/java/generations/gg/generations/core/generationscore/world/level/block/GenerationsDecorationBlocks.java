package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.creativetab.GenerationsCreativeTabs;
import generations.gg.generations.core.generationscore.world.level.block.decorations.*;
import generations.gg.generations.core.generationscore.world.level.block.entities.BallDisplayBlock;
import generations.gg.generations.core.generationscore.world.level.block.entities.BallDisplayBlock.DisplayState;
import generations.gg.generations.core.generationscore.world.level.block.entities.VendingMachineBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class GenerationsDecorationBlocks {
    public static final DeferredRegister<Block> DECORATIONS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);
    public static final List<RegistrySupplier<DyedBlockItem<VendingMachineBlock>>> VENDING_MACHINE_BLOCKS = new ArrayList<>();
    public static final List<RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>>> PASTEL_BEAN_BAG_BLOCKS = new ArrayList<>();
    public static final List<RegistrySupplier<BallDisplayBlock>> BALL_DISPLAY_BLOCKS = new ArrayList<>();

    /**
     * Decoration Blocks
     */
    public static final RegistrySupplier<Block> HOUSE_LAMP = registerDecorationItem("house_lamp", () -> new HouseLampBlock(BlockBehaviour.Properties.of().destroyTime(1.0f).sound(SoundType.METAL).lightLevel((lightLevel) -> 15)));
    public static final RegistrySupplier<Block> SWITCH = registerDecorationItem("switch", () -> new SwitchBlock(BlockBehaviour.Properties.of().destroyTime(1.0f).sound(SoundType.METAL)));
    public static final RegistrySupplier<Block> LITWICK_CANDLE = registerDecorationItem("litwick_candle", () -> new LitwickCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel(state -> 11).destroyTime(0.7f)));
    public static final RegistrySupplier<Block> LITWICK_CANDLES = registerDecorationItem("litwick_candles", () -> new LitwickCandlesBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel(state -> 15).destroyTime(0.5f)));

    /**
     * Decoration Blocks (Vending Machine)
     */
    public static final RegistrySupplier<VendingMachineBlock> VENDING_MACHINE = registerBlock("vending_machine", () -> new VendingMachineBlock(BlockBehaviour.Properties.of().destroyTime(1.0f).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> WHITE_VENDING_MACHINE = registerVendingMachineItem("white_vending_machine", DyeColor.WHITE);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> LIGHT_GRAY_VENDING_MACHINE = registerVendingMachineItem("light_gray_vending_machine", DyeColor.LIGHT_GRAY);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> GRAY_VENDING_MACHINE = registerVendingMachineItem("gray_vending_machine", DyeColor.GRAY);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> BLACK_VENDING_MACHINE = registerVendingMachineItem("black_vending_machine", DyeColor.BLACK);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> BROWN_VENDING_MACHINE = registerVendingMachineItem("brown_vending_machine", DyeColor.BROWN);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> RED_VENDING_MACHINE = registerVendingMachineItem("red_vending_machine", DyeColor.RED);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> ORANGE_VENDING_MACHINE = registerVendingMachineItem("orange_vending_machine", DyeColor.ORANGE);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> YELLOW_VENDING_MACHINE = registerVendingMachineItem("yellow_vending_machine", DyeColor.YELLOW);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> LIME_VENDING_MACHINE = registerVendingMachineItem("lime_vending_machine", DyeColor.LIME);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> GREEN_VENDING_MACHINE = registerVendingMachineItem("green_vending_machine", DyeColor.GREEN);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> CYAN_VENDING_MACHINE = registerVendingMachineItem("cyan_vending_machine", DyeColor.CYAN);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> LIGHT_BLUE_VENDING_MACHINE = registerVendingMachineItem("light_blue_vending_machine", DyeColor.LIGHT_BLUE);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> BLUE_VENDING_MACHINE = registerVendingMachineItem("blue_vending_machine", DyeColor.BLUE);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> PURPLE_VENDING_MACHINE = registerVendingMachineItem("purple_vending_machine", DyeColor.PURPLE);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> MAGENTA_VENDING_MACHINE = registerVendingMachineItem("magenta_vending_machine", DyeColor.MAGENTA);
    public static final RegistrySupplier<DyedBlockItem<VendingMachineBlock>> PINK_VENDING_MACHINE = registerVendingMachineItem("pink_vending_machine", DyeColor.PINK);

    /**
     * Decoration Blocks (Bean Bags)
     */
    public static final RegistrySupplier<Block> SNORLAX_BEAN_BAG = registerDecorationItem("snorlax_bean_bag", () -> new BeanBagBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(.5f).ignitedByLava()));
    public static final RegistrySupplier<PastelBeanBagBlock> PASTEL_BEAN_BAG = registerBlock("pastel_bean_bag", () -> new PastelBeanBagBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(.5f).ignitedByLava()));
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> WHITE_PASTEL_BEAN_BAG = registerPastelBeanBag("white_pastel_bean_bag", DyeColor.WHITE);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> LIGHT_GRAY_PASTEL_BEAN_BAG = registerPastelBeanBag("light_gray_pastel_bean_bag", DyeColor.LIGHT_GRAY);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> GRAY_PASTEL_BEAN_BAG = registerPastelBeanBag("gray_pastel_bean_bag", DyeColor.GRAY);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> BLACK_PASTEL_BEAN_BAG = registerPastelBeanBag("black_pastel_bean_bag", DyeColor.BLACK);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> BROWN_PASTEL_BEAN_BAG = registerPastelBeanBag("brown_pastel_bean_bag", DyeColor.BROWN);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> RED_PASTEL_BEAN_BAG = registerPastelBeanBag("red_pastel_bean_bag", DyeColor.RED);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> ORANGE_PASTEL_BEAN_BAG = registerPastelBeanBag("orange_pastel_bean_bag", DyeColor.ORANGE);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> YELLOW_PASTEL_BEAN_BAG = registerPastelBeanBag("yellow_pastel_bean_bag", DyeColor.YELLOW);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> LIME_PASTEL_BEAN_BAG = registerPastelBeanBag("lime_pastel_bean_bag", DyeColor.LIME);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> GREEN_PASTEL_BEAN_BAG = registerPastelBeanBag("green_pastel_bean_bag", DyeColor.GREEN);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> CYAN_PASTEL_BEAN_BAG = registerPastelBeanBag("cyan_pastel_bean_bag", DyeColor.CYAN);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> LIGHT_BLUE_PASTEL_BEAN_BAG = registerPastelBeanBag("light_blue_pastel_bean_bag", DyeColor.LIGHT_BLUE);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> BLUE_PASTEL_BEAN_BAG = registerPastelBeanBag("blue_pastel_bean_bag", DyeColor.BLUE);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> PURPLE_PASTEL_BEAN_BAG = registerPastelBeanBag("purple_pastel_bean_bag", DyeColor.PURPLE);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> MAGENTA_PASTEL_BEAN_BAG = registerPastelBeanBag("magenta_pastel_bean_bag", DyeColor.MAGENTA);
    public static final RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> PINK_PASTEL_BEAN_BAG = registerPastelBeanBag("pink_pastel_bean_bag", DyeColor.PINK);

    /**
     * Decoration Blocks (Vending Machine)
     */
    //Ball Displays
    public static final RegistrySupplier<BallDisplayBlock> EMPTY_BALL_DISPLAY = registerBallDisplay(DisplayState.EMPTY);
    public static final RegistrySupplier<BallDisplayBlock> POKE_BALL_DISPLAY = registerBallDisplay(DisplayState.POKE);
    public static final RegistrySupplier<BallDisplayBlock> GREAT_BALL_DISPLAY = registerBallDisplay(DisplayState.GREAT);
    public static final RegistrySupplier<BallDisplayBlock> ULTRA_BALL_DISPLAY = registerBallDisplay(DisplayState.ULTRA);
    public static final RegistrySupplier<BallDisplayBlock> MASTER_BALL_DISPLAY = registerBallDisplay(DisplayState.MASTER);
    public static final RegistrySupplier<BallDisplayBlock> CHERISH_BALL_DISPLAY = registerBallDisplay(DisplayState.CHERISH);
    public static final RegistrySupplier<BallDisplayBlock> DIVE_BALL_DISPLAY = registerBallDisplay(DisplayState.DIVE);
    public static final RegistrySupplier<BallDisplayBlock> DUSK_BALL_DISPLAY = registerBallDisplay(DisplayState.DUSK);
    public static final RegistrySupplier<BallDisplayBlock> FAST_BALL_DISPLAY = registerBallDisplay(DisplayState.FAST);
    public static final RegistrySupplier<BallDisplayBlock> FRIEND_BALL_DISPLAY = registerBallDisplay(DisplayState.FRIEND);
    public static final RegistrySupplier<BallDisplayBlock> GS_BALL_DISPLAY = registerBallDisplay(DisplayState.GS);
    public static final RegistrySupplier<BallDisplayBlock> HEAL_BALL_DISPLAY = registerBallDisplay(DisplayState.HEAL);
    public static final RegistrySupplier<BallDisplayBlock> HEAVY_BALL_DISPLAY = registerBallDisplay(DisplayState.HEAVY);
    public static final RegistrySupplier<BallDisplayBlock> LEVEL_BALL_DISPLAY = registerBallDisplay(DisplayState.LEVEL);
    public static final RegistrySupplier<BallDisplayBlock> LOVE_BALL_DISPLAY = registerBallDisplay(DisplayState.LOVE);
    public static final RegistrySupplier<BallDisplayBlock> LURE_BALL_DISPLAY = registerBallDisplay(DisplayState.LURE);
    public static final RegistrySupplier<BallDisplayBlock> LUXURY_BALL_DISPLAY = registerBallDisplay(DisplayState.LUXURY);
    public static final RegistrySupplier<BallDisplayBlock> MOON_BALL_DISPLAY = registerBallDisplay(DisplayState.MOON);
    public static final RegistrySupplier<BallDisplayBlock> NEST_BALL_DISPLAY = registerBallDisplay(DisplayState.NEST);
    public static final RegistrySupplier<BallDisplayBlock> NET_BALL_DISPLAY = registerBallDisplay(DisplayState.NET);
    public static final RegistrySupplier<BallDisplayBlock> PARK_BALL_DISPLAY = registerBallDisplay(DisplayState.PARK);
    public static final RegistrySupplier<BallDisplayBlock> PREMIER_BALL_DISPLAY = registerBallDisplay(DisplayState.PREMIER);
    public static final RegistrySupplier<BallDisplayBlock> QUICK_BALL_DISPLAY = registerBallDisplay(DisplayState.QUICK);
    public static final RegistrySupplier<BallDisplayBlock> REPEAT_BALL_DISPLAY = registerBallDisplay(DisplayState.REPEAT);
    public static final RegistrySupplier<BallDisplayBlock> SAFARI_BALL_DISPLAY = registerBallDisplay(DisplayState.SAFARI);
    public static final RegistrySupplier<BallDisplayBlock> SPORT_BALL_DISPLAY = registerBallDisplay(DisplayState.SPORT);
    public static final RegistrySupplier<BallDisplayBlock> TIMER_BALL_DISPLAY = registerBallDisplay(DisplayState.TIMER);

    private static RegistrySupplier<BallDisplayBlock> registerBallDisplay(DisplayState state) {
        var block = registerDecorationItem(state.name().toLowerCase() + "_ball_display", () -> new BallDisplayBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), state));
        BALL_DISPLAY_BLOCKS.add(block);
        return block;
    }

    private static <T extends Block> RegistrySupplier<T> registerDecorationItem(String name, Supplier<T> blockSupplier) {
        RegistrySupplier<T> block = DECORATIONS.register(name, blockSupplier);
        register(name, properties -> new BlockItem(block.get(), properties));
        return block;
    }

    private static <T extends Block> RegistrySupplier<T> registerBlock(String name, Supplier<T> blockSupplier) {
        return DECORATIONS.register(name, blockSupplier);
    }

    private static RegistrySupplier<DyedBlockItem<VendingMachineBlock>> registerVendingMachineItem(String name, DyeColor color) {
        var item = register(name, properties -> new DyedBlockItem<>(VENDING_MACHINE.get(), color, properties));
        VENDING_MACHINE_BLOCKS.add(item);
        return item;
    }

    private static RegistrySupplier<DyedBlockItem<PastelBeanBagBlock>> registerPastelBeanBag(String name, DyeColor color) {
        var item = register(name, properties -> new DyedBlockItem<>(PASTEL_BEAN_BAG.get(), color, properties));
        PASTEL_BEAN_BAG_BLOCKS.add(item);
        return item;
    }

    private static <T extends BlockItem> RegistrySupplier<T> register(String name, Function<Item.Properties, T> itemSupplier) {
        return GenerationsItems.ITEMS.register(name, () -> itemSupplier.apply(new Item.Properties().arch$tab(GenerationsCreativeTabs.DECORATIONS)));
    }

    public static void init() {
        GenerationsCore.LOGGER.info("Registering Generations Decorations");
        DECORATIONS.register();
    }
}
