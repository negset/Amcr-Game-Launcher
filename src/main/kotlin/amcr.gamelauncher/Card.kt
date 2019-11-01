package amcr.gamelauncher

import java.awt.Color
import java.awt.Graphics2D
import kotlin.math.max

class Card(val game: Game) {
    var x = 650
    var y = 0
    var refPos = 0
    private val active
        get() = refPos == 0
    private val bg = getImage("res/card.png")
    private val img = game.image?.let { getImage(it) }

    fun draw(g: Graphics2D) {
        g.run {
            drawImage(bg, x, y, null)
            color = Color.RED
            drawString(game.title, x + 50, y + 50)
            color = Color.DARK_GRAY
            drawString("by ${game.author}", x + 70, y + 90)

            if (active)
                img?.let {
                    var w = it.getWidth(null).toFloat()
                    var h = it.getHeight(null).toFloat()
                    val scale = 500f / max(w, h)
                    w *= scale
                    h *= scale

                    color = Color.BLUE
                    fillRect(45, 145, w.toInt() + 10, h.toInt() + 10)
                    drawImage(it, 50, 150, w.toInt(), h.toInt(), null)
                }
        }
    }

    fun update() {
        x = x.approach(if (active) 600 else 650, 10)
        y = y.approach(200 + refPos * 150, 30)
    }
}
