package generations.gg.generations.core.generationscore.client.render.rarecandy;

import gg.generations.rarecandy.arceus.core.DefaultRenderGraph;
import gg.generations.rarecandy.arceus.core.RareCandyScene;
import gg.generations.rarecandy.arceus.model.Material;
import gg.generations.rarecandy.arceus.model.Model;
import gg.generations.rarecandy.arceus.model.RenderingInstance;
import gg.generations.rarecandy.arceus.model.SmartObject;
import gg.generations.rarecandy.arceus.model.lowlevel.RenderData;
import gg.generations.rarecandy.arceus.model.lowlevel.VertexData;
import gg.generations.rarecandy.legacy.pipeline.ShaderProgram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11C.glDrawElements;

/**
 * simple (and probably naive) Render Graph for handling objects in a scene.
 */
public class GenerationsRenderGraph extends DefaultRenderGraph {
    public final RareCandyScene<RenderingInstance> objectManager;

    public GenerationsRenderGraph() {
        this(new RareCandyScene<>());
    }
    public GenerationsRenderGraph(RareCandyScene<RenderingInstance> objectManager) {
        super(objectManager);
        this.objectManager = objectManager;
    }
}
