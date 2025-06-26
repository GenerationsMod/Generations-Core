package generations.gg.generations.core.generationscore.common.client.render.rarecandy

import org.joml.Matrix4f

class StatueInstance(transformationMatrix: Matrix4f?, viewMatrix: Matrix4f?, materialId: String?) :
    CobblemonInstance(transformationMatrix, viewMatrix, materialId) {

    var material: String? = null
}
