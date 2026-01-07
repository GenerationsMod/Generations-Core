package generations.gg.generations.core.generationscore.common.client.render.rarecandy

import gg.generations.rarecandy.renderer.textures.ITexture
import net.minecraft.resources.ResourceLocation

interface ITextureWithResourceLocation: ITexture {
    var location: ResourceLocation
}