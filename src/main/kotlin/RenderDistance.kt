import com.lambda.client.command.ClientCommand
import com.lambda.client.util.text.MessageSendHelper
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.GameSettings

object RenderDistance: ClientCommand(
    name = "renderdistance",
    description = "Set your render distance to a specific value"
) {
    init {
        int("Render Distance") { rdArg ->
            execute {
                if (rdArg.value > 0) {
                Minecraft.getMinecraft().gameSettings.renderDistanceChunks = rdArg.value
                MessageSendHelper.sendChatMessage("Your render distance has been set to ${rdArg.value}.")
            } else {
                MessageSendHelper.sendErrorMessage("Invalid render distance.")
            }
            }
        }
    }
}