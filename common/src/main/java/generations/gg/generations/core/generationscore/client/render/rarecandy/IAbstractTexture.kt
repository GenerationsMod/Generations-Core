package generations.gg.generations.core.generationscore.client.render.rarecandy

import gg.generations.rarecandy.renderer.loading.ITexture
import net.minecraft.resources.ResourceLocation

interface ITextureWithResourceLocation: ITexture {
    var location: ResourceLocation
}