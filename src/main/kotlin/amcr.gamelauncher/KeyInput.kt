package amcr.gamelauncher

import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class KeyInput : KeyAdapter() {
    private var enter = 0
    private var up = 0
    private var down = 0
    val enterJustPressed: Boolean
        get() {
            if (enter > 0) enter++
            return enter == 2
        }
    val upPressed: Boolean
        get() {
            if (up > 0) up++
            return up == 2 || up > 20 && up % 5 == 0
        }
    val downPressed: Boolean
        get() {
            if (down > 0) down++
            return down == 2 || down > 20 && down % 5 == 0
        }

    override fun keyPressed(e: KeyEvent?) {
        when (e?.keyCode) {
            KeyEvent.VK_ENTER -> if (enter == 0) enter = 1
            KeyEvent.VK_UP -> if (up == 0) up = 1
            KeyEvent.VK_DOWN -> if (down == 0) down = 1
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        when (e?.keyCode) {
            KeyEvent.VK_ENTER -> enter = 0
            KeyEvent.VK_UP -> up = 0
            KeyEvent.VK_DOWN -> down = 0
        }
    }
}
