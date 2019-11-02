package amcr.gamelauncher

import javax.swing.ImageIcon
import javax.swing.JFrame

fun main() {
    object : JFrame() {
        init {
            title = "Game Launcher"
            iconImage = ImageIcon("res/icon.png").image
            defaultCloseOperation = EXIT_ON_CLOSE
            isResizable = false
            contentPane.add(MyPanel())
            pack()
            isVisible = true
        }
    }
}
