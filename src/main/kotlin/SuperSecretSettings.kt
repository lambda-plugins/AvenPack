import com.lambda.client.module.Category
import com.lambda.client.plugin.api.PluginModule
import com.lambda.client.event.listener.listener
import net.minecraft.client.renderer.OpenGlHelper
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.gameevent.TickEvent

internal object SuperSecretSettings: PluginModule(
    name = "SuperSecretShaders",
    category = Category.RENDER,
    description = "Pre 1.9 Shaders",
    pluginMain = APLoader
) {
    private val Shader by setting("Shader", Shaders.Flip)

    private var activeShader = "art"

    init {
        listener<TickEvent.ClientTickEvent> {
            ensureReload()
        }
        onEnable /*listener<TickEvent.ClientTickEvent>*/ {
            if (OpenGlHelper.shadersSupported && mc.renderViewEntity is EntityPlayer) {
                mc.entityRenderer.shaderGroup.deleteShaderGroup()
                try {
                    mc.entityRenderer.loadShader(ResourceLocation("shaders/post/" + getShaderLocation() + ".json"))
                    activeShader = getShaderLocation()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            } else /*if (mc.entityRenderer.shaderGroup != null && mc.currentScreen == null)*/ {
                mc.entityRenderer.shaderGroup.deleteShaderGroup()
                disable()
            }
        }
        onDisable {
            mc.entityRenderer.shaderGroup.deleteShaderGroup()
            disable()
        }
    }

    private fun getShaderLocation() : String {
        return when (Shader) {
            Shaders.Art -> "art"
            Shaders.Bits -> "bits"
            Shaders.Blobs -> "blobs"
            Shaders.Blur -> "blur"
            Shaders.Bumpy -> "bumpy"
            Shaders.Saturation -> "color_convolve"
            Shaders.Creep -> "creeper"
            Shaders.Deconverge -> "deconverge"
            Shaders.Desaturate -> "desaturate"
            Shaders.Flip -> "flip"
            Shaders.Green -> "green"
            Shaders.Invert -> "invert"
            Shaders.Notch -> "notch"
            Shaders.NTSC -> "ntsc"
            Shaders.Pencil -> "pencil"
            Shaders.Sobel -> "sobel"
            Shaders.Spider -> "spider"
            Shaders.Wobble -> "wobble"
        }
    }

    private fun ensureReload() {
        if (activeShader != getShaderLocation()) {
            disable()
            enable()
        }
    }
    enum class Shaders {
        Art, Bits, Blobs, Blur, Bumpy, Saturation, Creep, Deconverge, Desaturate, Flip, Green, Invert, Notch, NTSC, Pencil, Sobel, Spider, Wobble
    }
}
