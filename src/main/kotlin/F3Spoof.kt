import com.lambda.client.module.Category
import com.lambda.client.plugin.api.PluginModule
import com.lambda.client.event.listener.listener
import net.minecraftforge.client.event.RenderGameOverlayEvent

internal object F3Spoof: PluginModule(
    name = "F3Spoof",
    category = Category.MISC,
    description = "Modifies your Debug Menu",
    pluginMain = APLoader
) {
    private val hideCoords = setting("Coords", true)
    private val hideFrames = setting("FPS", false)
    private val hideBiome = setting("Biome", false)
    private val useCustomText = setting("Use custom text", false)
    private val customText by setting("Custom text", "Lambda", { useCustomText.value })

    var replaceText = "Lambda"

    init {
        listener<RenderGameOverlayEvent.Text> {
            replaceText = if (useCustomText.value) customText else "Lambda"
            for (i in 0 until it.left.size) {
                if (hideCoords.value) {
                    if (it.left[i].contains("Looking"))
                        it.left[i] = "Looking at $replaceText"
                    if (it.left[i].contains("XYZ"))
                        it.left[i] = "XYZ: $replaceText"
                    if (it.left[i].contains("Block:"))
                        it.left[i] = "Block: $replaceText"
                    if (it.left[i].contains("Chunk:"))
                        it.left[i] = "Chunk: $replaceText"
                    if (it.left[i].contains("Facing:"))
                        it.left[i] = "Facing: $replaceText"
                }
                if (hideFrames.value) {
                    if (it.left[i].contains("fps"))
                        it.left[i] = "$replaceText fps"
                }
                if (hideBiome.value) {
                    if (it.left[i].contains("Biome:"))
                        it.left[i] = "Biome: $replaceText"
                }
            }
        }
    }
}
