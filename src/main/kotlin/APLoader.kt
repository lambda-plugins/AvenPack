import com.lambda.client.plugin.api.Plugin

internal object APLoader: Plugin() {

    override fun onLoad() {
        // Load any modules, commands, or HUD elements here
        modules.add(AntiToast)
        modules.add(ClientSideTime)
        commands.add(EChest)
        modules.add(F3Spoof)
        modules.add(FogColor)
        commands.add(FOV)
        commands.add(ReloadSounds)
        commands.add(RenderDistance)
        modules.add(SelfWeb)
        modules.add(SuperSecretSettings)
        modules.add(UnfocusedSettings)
        commands.add(VClip)
        hudElements.add(BaseHuntLine)
    }

    override fun onUnload() {
        // Here you can unregister threads etc...
    }
}
