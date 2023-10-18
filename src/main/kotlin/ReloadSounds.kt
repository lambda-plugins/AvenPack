import com.lambda.client.LambdaMod
import com.lambda.client.util.text.MessageSendHelper
import com.lambda.client.command.ClientCommand
import net.minecraft.client.audio.SoundHandler
import net.minecraft.client.audio.SoundManager
import net.minecraftforge.fml.relauncher.ReflectionHelper


object ReloadSounds: ClientCommand(
    name = "reloadsounds",
    description = "Reloads your sound system"
) {
    private val sndManager = ReflectionHelper.getPrivateValue<SoundManager, SoundHandler>(SoundHandler::class.java, mc.soundHandler, "sndManager", "field_147694_f")
    init {
        execute {
            try {
                sndManager.reloadSoundSystem()
                MessageSendHelper.sendChatMessage("[ReloadSounds] Reloaded!")
            } catch (e: Exception) {
                println("[ReloadSounds] Failed to reload sounds.")
                LambdaMod.LOG.error(e)
            }
        }
    }
}