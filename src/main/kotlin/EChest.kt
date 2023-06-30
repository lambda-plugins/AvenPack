import com.lambda.client.command.ClientCommand
import com.lambda.client.event.listener.listener
import com.lambda.client.util.text.MessageSendHelper
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.inventory.Container
import net.minecraft.inventory.ContainerChest
import net.minecraft.inventory.InventoryBasic
import net.minecraftforge.fml.common.gameevent.TickEvent

object EChest: ClientCommand(
    name = "echest",
    description = "Displays the saved Enderchest GUI"
) {
    private var echestScreen: GuiScreen? = null
    init {
        listener<TickEvent.ClientTickEvent> {
            if (mc.currentScreen is GuiContainer) {
                val container: Container = (mc.currentScreen as GuiContainer).inventorySlots
                if (container is ContainerChest && container.lowerChestInventory is InventoryBasic) {
                    val basic = container.lowerChestInventory as InventoryBasic
                    if (basic.name.equals("Ender Chest", ignoreCase = true)) {
                        echestScreen = mc.currentScreen
                    }
                }
            }
        }
        execute {
            if(echestScreen != null && mc.player != null && mc.world != null) mc.displayGuiScreen(echestScreen)
            else MessageSendHelper.sendErrorMessage("[EChest] Open an ender chest first!")
        }
    }
}