import com.lambda.client.command.ClientCommand
import com.lambda.client.util.text.MessageSendHelper

object VClip: ClientCommand(
    name = "vclip",
    description = "Attempts to teleport through blocks vertically"
) {
    init {
        float("y") { yArg ->
            execute {
                mc.player.setPositionAndUpdate(mc.player.posX, mc.player.posY + yArg.value, mc.player.posZ)
                MessageSendHelper.sendChatMessage("You have been teleported to Y=${Math.round(mc.player.posY)}.")
            }
        }
    }
}