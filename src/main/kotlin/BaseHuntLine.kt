import com.lambda.client.event.SafeClientEvent
import com.lambda.client.plugin.api.PluginLabelHud
import com.lambda.client.util.graphics.font.TextComponent
import com.lambda.client.util.math.VectorUtils.times
import net.minecraft.util.math.Vec3d

internal object BaseHuntLine : PluginLabelHud(
    name = "BaseHuntLine",
    category = Category.WORLD,
    description = "Base Hunt Line",
    pluginMain = APLoader
) {
    private val offset by setting("offset", 30000, 0..120000, 5000)
    private val decimalPlaces by setting("Decimal Places", 0, 0..4, 1)
    private val thousandsSeparator by setting("Thousands Separator", false)
    private val alignCoords by setting("Align Coords", true, description = "Align your coords to the center of the block")

    override fun SafeClientEvent.updateText() {
        val entity = mc.renderViewEntity ?: player

        displayText.add("Line", secondaryColor)
        displayText.addLine(getFormattedCoords(entity.positionVector))

        displayText.add("Offset", secondaryColor)
        displayText.addLine("$offset", primaryColor)
    }

    private fun getFormattedCoords(pos: Vec3d): TextComponent.TextElement {
        val alignValue = when {
            alignCoords -> 0.5f
            else -> 0.0f
        }
        val x = Math.abs(pos.x-alignValue)
        val z = Math.abs(pos.z-alignValue)
        val baseLine = when {
            x<=z/2 -> roundOrInt(x+offset)//straghts
            x/2>z -> roundOrInt(z-offset)//straghts
            x<=z -> roundOrInt(x-z+offset)//diagonal
            x>z ->  roundOrInt(x-z-offset)//diagonal
            else -> 0
        }
        return StringBuilder().run {
            append(baseLine)
            TextComponent.TextElement(toString(), primaryColor)
        }
    }

    private fun roundOrInt(input: Double): String {
        val separatorFormat = if (thousandsSeparator) "," else ""

        return "%$separatorFormat.${decimalPlaces}f".format(input)
    }

    private fun StringBuilder.appendWithComma(string: String) = append(if (length > 0) ", $string" else string)
}