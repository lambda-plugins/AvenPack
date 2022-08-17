import com.lambda.client.plugin.api.Plugin

internal object APLoader: Plugin() {

    override fun onLoad() {
        // Load any modules, commands, or HUD elements here
        modules.add(AntiSoundBug)
        modules.add(AntiToast)
        modules.add(AutoDupe)
        modules.add(Backpack)
        modules.add(ClientSideTime)
        modules.add(F3Spoof)
        modules.add(FogColor)
        modules.add(Parkour)
        modules.add(SelfWeb)
        modules.add(SuperSecretSettings)
        modules.add(UnfocusedFPS)
        commands.add(VClip)
    }

    override fun onUnload() {
        // Here you can unregister threads etc...
    }
}
