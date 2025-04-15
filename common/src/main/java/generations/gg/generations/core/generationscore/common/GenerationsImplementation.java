package generations.gg.generations.core.generationscore.common;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Supplier;

public interface GenerationsImplementation {

    void registerResourceReloader(ResourceLocation identifier, PreparableReloadListener reloader, PackType type, Collection<ResourceLocation> dependencies);

    /**
     * Register a block as Strippable.
     * @param log The log block of wood
     * @param stripped The stripped log block of wood
     */
    void registerStrippable(@NotNull Block log, @NotNull Block stripped);

    /**
     * Register a block as flammable.
     * @param blockIn The block to register
     * @param encouragement How much more likely the block is to catch fire compared to vanilla
     * @param flammability How much more likely the block is to spread fire compared to vanilla
     */
    void registerFlammable(@NotNull Block blockIn, int encouragement, int flammability);

    /**
     * Register a block as compostable.
     * @param block The block to register
     * @param chance The chance of the block to compost
     */
    void registerCompostables(@NotNull Block block, float chance);

    Supplier<CreativeModeTab> create(String name, Supplier<ItemStack> o, DeferredRegister<? extends ItemLike>... deferredRegister);

    enum ModAPI {
        FABRIC,
        FORGE
    }

    enum Environment {
        CLIENT,
        SERVER

    }
}
