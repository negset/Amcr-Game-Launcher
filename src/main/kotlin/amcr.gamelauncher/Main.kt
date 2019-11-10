package amcr.gamelauncher

import com.moandjiezana.toml.Toml
import java.io.FileInputStream
import java.io.InputStreamReader
import javax.swing.ImageIcon
import javax.swing.JFrame

fun main() {
    val toml = Toml().read(
        InputStreamReader(
            FileInputStream("config.toml"), "UTF-8"
        )
    )
    val config = toml.to(Config::class.java)

    object : JFrame() {
        init {
            title = config.windowTitle
            iconImage = ImageIcon("res/icon.png").image
            defaultCloseOperation = EXIT_ON_CLOSE
            isResizable = false
            contentPane.add(MyPanel(config))
            pack()
            isVisible = true
        }
    }
}
