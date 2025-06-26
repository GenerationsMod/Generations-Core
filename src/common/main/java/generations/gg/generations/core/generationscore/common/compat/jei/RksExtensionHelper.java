package generations.gg.generations.core.generationscore.common.compat.jei;

import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import mezz.jei.library.util.RecipeErrorUtil;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Stream;

public class RksExtensionHelper {
	private static final Logger LOGGER = LogManager.getLogger();

	private final List<Handler<? extends RksRecipe>> handlers = new ArrayList<>();
	private final Set<Class<? extends RksRecipe>> handledClasses = new HashSet<>();
	private final Map<RecipeHolder<? extends RksRecipe>, @Nullable IRksCategoryExtension<? extends RksRecipe>> cache = new IdentityHashMap<>();

	public <T extends RksRecipe> void addRecipeExtension(Class<? extends T> recipeClass, IRksCategoryExtension<T> recipeExtension) {
		if (!RksRecipe.class.isAssignableFrom(recipeClass)) {
			throw new IllegalArgumentException("Recipe handlers must handle a specific class that inherits from CraftingRecipe. Instead got: " + recipeClass);
		}
		if (this.handledClasses.contains(recipeClass)) {
			throw new IllegalArgumentException("A Recipe Extension has already been registered for this class:" + recipeClass);
		}
		this.handledClasses.add(recipeClass);
		this.handlers.add(new Handler<>(recipeClass, recipeExtension));
	}

	public <R extends RksRecipe> IRksCategoryExtension<R> getRecipeExtension(RecipeHolder<R> recipeHolder) {
		return getOptionalRecipeExtension(recipeHolder)
			.orElseThrow(() -> {
				String recipeName = RecipeErrorUtil.getNameForRecipe(recipeHolder);
				return new RuntimeException("Failed to create recipe extension for recipe: " + recipeName);
			});
	}

	public <R extends RksRecipe> Optional<IRksCategoryExtension<R>> getOptionalRecipeExtension(RecipeHolder<R> recipeHolder) {
		if (cache.containsKey(recipeHolder)) {
			IRksCategoryExtension<? extends RksRecipe> extension = cache.get(recipeHolder);
			if (extension != null) {
				IRksCategoryExtension<R> cast = (IRksCategoryExtension<R>) extension;
				return Optional.of(cast);
			}
			return Optional.empty();
		}

		Optional<IRksCategoryExtension<R>> result = getBestRecipeHandler(recipeHolder)
			.map(Handler::getExtension);

		cache.put(recipeHolder, result.orElse(null));

		return result;
	}

	private <T extends RksRecipe> Stream<Handler<T>> getRecipeHandlerStream(RecipeHolder<T> recipeHolder) {
		return handlers.stream()
			.flatMap(handler -> handler.optionalCast(recipeHolder).stream());
	}

	private <T extends RksRecipe> Optional<Handler<T>> getBestRecipeHandler(RecipeHolder<T> recipeHolder) {
		Class<? extends RksRecipe> recipeClass = recipeHolder.value().getClass();

		List<Handler<T>> assignableHandlers = new ArrayList<>();
		// try to find an exact match
		List<Handler<T>> allHandlers = getRecipeHandlerStream(recipeHolder).toList();
		for (Handler<T> handler : allHandlers) {
			Class<? extends RksRecipe> handlerRecipeClass = handler.getRecipeClass();
			if (handlerRecipeClass.equals(recipeClass)) {
				return Optional.of(handler);
			}
			// remove any handlers that are super of this one
			assignableHandlers.removeIf(h -> h.getRecipeClass().isAssignableFrom(handlerRecipeClass));
			// only add this if it's not a super class of another assignable handler
			if (assignableHandlers.stream().noneMatch(h -> handlerRecipeClass.isAssignableFrom(h.getRecipeClass()))) {
				assignableHandlers.add(handler);
			}
		}
		if (assignableHandlers.isEmpty()) {
			return Optional.empty();
		}
		if (assignableHandlers.size() == 1) {
			return Optional.of(assignableHandlers.getFirst());
		}

		// try super classes to get the closest match
		Class<?> superClass = recipeClass;
		while (!Object.class.equals(superClass)) {
			superClass = superClass.getSuperclass();
			for (Handler<T> handler : assignableHandlers) {
				if (handler.getRecipeClass().equals(superClass)) {
					return Optional.of(handler);
				}
			}
		}

		List<Class<? extends RksRecipe>> assignableClasses = assignableHandlers.stream()
			.<Class<? extends RksRecipe>>map(Handler::getRecipeClass)
			.toList();
		LOGGER.warn("Found multiple matching recipe handlers for {}: {}", recipeClass, assignableClasses);
		return Optional.of(assignableHandlers.getFirst());
	}

	private record Handler<T extends RksRecipe>(
		Class<? extends T> recipeClass,
		IRksCategoryExtension<T> extension
	) {
		public <V extends RksRecipe> Optional<Handler<V>> optionalCast(RecipeHolder<V> recipeHolder) {
			if (isHandled(recipeHolder)) {
				@SuppressWarnings("unchecked")
				Handler<V> cast = (Handler<V>) this;
				return Optional.of(cast);
			}
			return Optional.empty();
		}

		public boolean isHandled(RecipeHolder<?> recipeHolder) {
			Recipe<?> recipe = recipeHolder.value();
			if (recipeClass.isInstance(recipe)) {
				@SuppressWarnings("unchecked")
				RecipeHolder<T> cast = (RecipeHolder<T>) recipeHolder;
				return extension.isHandled(cast);
			}
			return false;
		}

		public Class<? extends T> getRecipeClass() {
			return recipeClass;
		}

		public IRksCategoryExtension<T> getExtension() {
			return extension;
		}
	}

}
