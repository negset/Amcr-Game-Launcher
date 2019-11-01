package amcr.gamelauncher

import com.moandjiezana.toml.Toml
import java.awt.*
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import javax.swing.JPanel

class MyPanel : JPanel(), Runnable {
    private val keyInput = KeyInput()
    private val myFont = Font.createFont(Font.TRUETYPE_FONT,
            File("res/font.ttf")).deriveFont(24f)

    private val cards: Array<Card>
    private var cursor = 0

    private val logo = getImage("res/logo.png")
    private val select = Wave("res/select.wav")
    private val enter = Wave("res/enter.wav")

    init {
        preferredSize = Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
        isFocusable = true
        addKeyListener(keyInput)

        val toml = Toml().read(InputStreamReader(
                FileInputStream("config.toml"), "UTF-8"))
        val config = toml.to(Config::class.java)

        cards = Array(config.games.size) {
            Card(config.games[it]).apply {
                y = 200 + it * 150
            }
        }

        Thread(this).start()
    }

    override fun run() {
        while (true) {
            repaint()
            update()

            try {
                Thread.sleep(20)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        (g as Graphics2D).run {
            setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
            font = myFont

            color = Color(205, 235, 255)
            fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT)

            drawImage(logo, 50, 550, null)

            color = Color.BLACK
            drawString("選択: ↑↓  決定: Enter", 20, 50)

            cards.forEach { it.draw(this) }
        }
    }

    private fun update() {
        keyInput.update()

        cards.withIndex().forEach { (i, card) ->
            card.refPos = i - cursor
            card.update()
        }

        when {
            keyInput.up -> {
                cursor--
                cursor = (cursor + cards.size) % cards.size
                select.play()
            }
            keyInput.down -> {
                cursor++
                cursor = (cursor + cards.size) % cards.size
                select.play()
            }
            keyInput.enter -> {
                enter.play()
                val r = Runtime.getRuntime()
                when {
                    isWindows ->
                        r.exec(arrayOf("cmd", "/c", cards[cursor].game.cmdWin))
                    isMacOS ->
                        r.exec(arrayOf("sh", "-c", cards[cursor].game.cmdMac))
                }
            }
        }
    }
}
