import com.lambda.client.module.Category
import com.lambda.client.plugin.api.PluginModule
import com.lambda.client.util.threads.safeListener
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.GameSettings
import net.minecraftforge.fml.common.gameevent.TickEvent
import org.lwjgl.opengl.Display

internal object UnfocusedFPS: PluginModule(
    name = "UnfocusedFPS",
    description = "Reduces framerate when the game is minimized",
    category = Category.RENDER,
    pluginMain = APLoader
) {
private val FpsCap by setting("Frame Cap", 5, 5..60, 5)
private val FpsMax by setting("Frame Max", 260, 10..260, 10)
private val Vsync by setting("Vsync", true)
private var Settings = Minecraft.getMinecraft().gameSettings
init {
    safeListener<TickEvent.ClientTickEvent> {
        Settings.enableVsync = Vsync
        Display.setVSyncEnabled(Settings.enableVsync)
        if (!Display.isActive()) Settings.limitFramerate = FpsCap
        else Settings.limitFramerate = FpsMax
    }
}
}
