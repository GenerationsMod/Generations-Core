package generations.gg.generations.core.generationscore.client.render;

import generations.gg.generations.core.generationscore.client.render.rarecandy.PixelmonInstance;

public interface PixelmonInstanceProvider {
    public PixelmonInstance getInstance();
    void setInstance(PixelmonInstance instance);
}