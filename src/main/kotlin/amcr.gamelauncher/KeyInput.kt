package amcr.gamelauncher

import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class KeyInput : KeyAdapter() {
    var enter = false
    var up = false
    var down = false
    private var lastEnter = false
    private var lastUp = false
    private var lastDown = false

    override fun keyPressed(e: KeyEvent?) {
        when (e?.keyCode) {
            KeyEvent.VK_ENTER -> enter = true
            KeyEvent.VK_UP -> up = true
            KeyEvent.VK_DOWN -> down = true
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        when (e?.keyCode) {
            KeyEvent.VK_ENTER -> enter = false
            KeyEvent.VK_UP -> up = false
            KeyEvent.VK_DOWN -> down = false
        }
    }

    fun update() {
        if (lastEnter) enter = false
        if (lastUp) up = false
        if (lastDown) down = false
        lastEnter = enter
        lastUp = up
        lastDown = down
    }
}
