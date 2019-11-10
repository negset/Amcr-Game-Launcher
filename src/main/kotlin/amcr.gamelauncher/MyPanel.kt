package amcr.gamelauncher

import javafx.scene.media.AudioClip
import java.awt.*
import java.io.File
import javax.swing.JPanel

class MyPanel(config: Config) : JPanel(), Runnable {
    private val keyInput = KeyInput()
    private val myFont = Font.createFont(
        Font.TRUETYPE_FONT,
        File("res/font.ttf")
    ).deriveFont(24f)

    private val cards: Array<Card>
    private var cursor = 0
        set(value) {
            field = (value + cards.size) % cards.size
        }

    private val logo = getImage("res/logo.png")
    private val select = AudioClip(File("res/select.wav").toURI().toString())
    private val enter = AudioClip(File("res/enter.wav").toURI().toString())

    init {
        preferredSize = Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
        isFocusable = true
        addKeyListener(keyInput)

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
            setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON
            )
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

        cards.withIndex().forEach { (i, card) ->
            card.refPos = i - cursor
            card.update()
        }

        when {
            keyInput.upPressed -> {
                cursor--
                select.play()
            }
            keyInput.downPressed -> {
                cursor++
                select.play()
            }
            keyInput.enterJustPressed -> {
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
