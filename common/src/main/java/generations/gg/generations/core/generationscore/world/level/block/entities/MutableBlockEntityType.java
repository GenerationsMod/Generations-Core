package generations.gg.generations.core.generationscore.world.level.block.entities;

import com.mojang.datafixers.types.Type;
import generations.gg.generations.core.generationscore.mixin.BlockEntityTypeAccessor;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericModelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Direct copy of https://github.com/DimensionalDevelopment/DimDoors/blob/1.19/src/main/java/org/dimdev/dimdoors/api/block/entity/MutableBlockEntityType.java
public class MutableBlockEntityType<T extends BlockEntity> extends BlockEntityType<T> {
	public static Set<GenericModelBlock<?>> blocksToAdd = new HashSet<>();


	public MutableBlockEntityType(BlockEntityFactory<? extends T> factory, Set<Block> blocks, Type<?> type) {
		// ensure that the Set is mutable
		super(factory, new HashSet<>(blocks), type);
	}

	public boolean addBlock(Block block) {
		return ((BlockEntityTypeAccessor) this).getBlocks().add(block);
	}

	public boolean removeBlock(Block block) {
		return ((BlockEntityTypeAccessor) this).getBlocks().remove(block);
	}


	public static final class Builder<T extends BlockEntity> {
		private final BlockEntityFactory<? extends T> factory;
		private final Set<Block> blocks;

		private Builder(BlockEntityFactory<? extends T> factory, Set<Block> blocks) {
			this.factory = factory;
			this.blocks = blocks;
		}

		public static <T extends BlockEntity> Builder<T> create(BlockEntityFactory<? extends T> factory, Block... blocks) {
			// ensure mutability
			return new Builder<>(factory, new HashSet<>(Arrays.asList(blocks)));
		}

		public MutableBlockEntityType<T> build() {
			return build(null);
		}

		public MutableBlockEntityType<T> build(Type<?> type) {
			return new MutableBlockEntityType<>(this.factory, this.blocks, type);
		}
	}


	// exists for convenience so that no access widener for BlockEntityType.BlockEntityFactory is necessary
	@FunctionalInterface
	public interface BlockEntityFactory<T extends BlockEntity> extends BlockEntityType.BlockEntitySupplier<T> {
	}
}