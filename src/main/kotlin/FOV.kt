import com.lambda.client.command.ClientCommand
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.GameSettings

object FOV: ClientCommand(
    name = "fov",
    description = "Set your FOV to a specific value"
) {
    init {
        float("fov") { fovArg ->
            execute {
                Minecraft.getMinecraft().gameSettings.fovSetting = fovArg.value
            }
        }
    }
}