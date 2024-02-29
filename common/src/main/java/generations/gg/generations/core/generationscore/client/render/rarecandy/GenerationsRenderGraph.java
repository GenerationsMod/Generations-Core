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
    private final List<SmartObject> updatableObjects = new ArrayList<>();
    private final Map<ShaderProgram, Map<Model, Map<Material, Map<VertexData, List<RenderingInstance>>>>> instanceMap = new HashMap<>();
    private final Map<RenderData, Boolean> modelHasNoInstanceVariants = new HashMap<>(); // TODO: check if the instances share the same material as the model. if so, do the rendering thing faster TM


    public GenerationsRenderGraph() {
        this(new RareCandyScene<>());
    }
    public GenerationsRenderGraph(RareCandyScene<RenderingInstance> objectManager) {
        super(objectManager);
        this.objectManager = objectManager;
    }

    public void render() {
        if (isDirty()) updateCache();

        updatableObjects.forEach(SmartObject::update);

        for (var shaderEntry : instanceMap.entrySet()) {
            var program = shaderEntry.getKey();
            program.bind();
            program.updateSharedUniforms();

            for (var modelEntry : shaderEntry.getValue().entrySet()) {
                var model = modelEntry.getKey();
                var data = model.data();
                program.updateModelUniforms(model); // TODO: add this

                for (var materialEntry : modelEntry.getValue().entrySet()) {
                    var material = materialEntry.getKey();
                    program.preMaterial().accept(material);

                    program.updateMaterialUniforms(model, material);

                    for (var layoutEntry : materialEntry.getValue().entrySet()) {
                        layoutEntry.getKey().bind();
                        data.bind();

                        for (var instance : layoutEntry.getValue()) {
                            program.updateInstanceUniforms(instance, material, model);
                            glDrawElements(data.mode.glType, data.indexCount, data.indexType.glType, 0);
                        }
                    }

                    program.postMaterial().accept(material);
                }
            }
        }
    }

    /**
     * Update the renderers storage objects, so it doesn't de-sync with the scene.
     */
    private void updateCache() {
        removedInstances.forEach(this::removeInstance);
        addedInstances.forEach(this::addInstance);
        removedInstances.clear();
        addedInstances.clear();
        markClean();
    }

    private void addInstanceImpl(RenderingInstance instance) {
        if (instance instanceof SmartObject object) updatableObjects.add(object);
        instanceMap.computeIfAbsent(instance.getMaterial().getProgram(), layout -> new HashMap<>())
                .computeIfAbsent(instance.getModel(), shaderProgram -> new HashMap<>())
                .computeIfAbsent(instance.getMaterial(), material -> new HashMap<>())
                .computeIfAbsent(instance.getModel().data().vertexData, program -> new ArrayList<>())
                .add(instance);
    }

    private void removeInstanceImpl(RenderingInstance instance) {
        if (instance instanceof SmartObject) updatableObjects.remove(instance);

        var program = instanceMap.get(instance.getMaterial().getProgram());

        //Needed to prevent explosion
        if(program != null) {
            var model = program.get(instance.getModel());
            if(model != null) {
                var material = model.get(instance.getMaterial());
                if(material != null) {
                    var data = material.get(instance.getModel().data().vertexData);
                    if(data != null) data.remove(instance);
                }
            }
        }

        instance.postRemove();
    }

    public void clear() {
        instanceMap.clear();
    }

    private boolean dirty;
    public List<RenderingInstance> addedInstances = new ArrayList<>();
    public List<RenderingInstance> removedInstances = new ArrayList<>();

    public <T extends RenderingInstance> void addInstance(T instance) {
        this.addedInstances.add(instance);
        this.dirty = true;
    }

    public <T extends RenderingInstance> void removeInstance(T instance) {
        if (instance != null) {
            this.removedInstances.add(instance);
            this.dirty = true;
        }
    }

    public boolean isDirty() {
        return this.dirty;
    }

    public void markClean() {
        this.dirty = false;
    }
}
