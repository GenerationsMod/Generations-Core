package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;

public record YawLogicType<T extends YawLogic>(ResourceLocation id, Codec<T> codec, Class<? extends YawLogic> logicClass) {
}