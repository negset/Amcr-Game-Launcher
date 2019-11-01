package amcr.gamelauncher

import javax.swing.JFrame
import javax.swing.ImageIcon

fun main() {
    object : JFrame() {
        init {
            title = "Game Launcher"
            iconImage = ImageIcon("res/icon.png").image
            isResizable = false
            contentPane.add(MyPanel())
            pack()
            defaultCloseOperation = EXIT_ON_CLOSE
            isVisible = true
        }
    }
}
