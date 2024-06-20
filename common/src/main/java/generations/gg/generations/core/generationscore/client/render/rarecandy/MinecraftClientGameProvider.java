package generations.gg.generations.core.generationscore.client.render.rarecandy;

import org.joml.Matrix4f;

public class MinecraftClientGameProvider {
    private static final double START_TIME = System.currentTimeMillis();

    public static double getTimePassed() {
        return (System.currentTimeMillis() - START_TIME) / 1000f;
    }
}