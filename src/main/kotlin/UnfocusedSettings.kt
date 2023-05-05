import com.lambda.client.module.Category
import com.lambda.client.plugin.api.PluginModule
import com.lambda.client.util.threads.safeListener
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.GameSettings
import net.minecraftforge.fml.common.gameevent.TickEvent
import org.lwjgl.opengl.Display

internal object UnfocusedSettings: PluginModule(
    name = "UnfocusedSettings",
    description = "Reduces framerate and render distance when the game is minimized",
    category = Category.RENDER,
    pluginMain = APLoader
) {
private val FpsMax by setting("Frame Max", 260, 10..260, 10)
private val FpsCap by setting("Frame Cap", 5, 5..60, 5)
private val Vsync by setting("Vsync", true)
private val ChunkControl by setting("Chunk Control", false)
private val ChunkMax by setting("Chunk Max", 8, 2..64, 1, { ChunkControl == true })
private val ChunkCap by setting("Chunk Cap", 2, 2..64, 1, { ChunkControl == true })
private var Settings = Minecraft.getMinecraft().gameSettings
init {
    safeListener<TickEvent.ClientTickEvent> {
        Settings.enableVsync = Vsync
        Display.setVSyncEnabled(Settings.enableVsync)
        if (Display.isActive()) Settings.limitFramerate = FpsMax
        else Settings.limitFramerate = FpsCap

        if (ChunkControl == true){
        if (Display.isActive()) Settings.renderDistanceChunks = ChunkMax
        else Settings.renderDistanceChunks = ChunkCap}}
}
}