package amcr.gamelauncher

import java.awt.Image
import java.io.FileInputStream
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.math.abs

const val WINDOW_WIDTH = 1200
const val WINDOW_HEIGHT = 800

val isWindows = System.getProperty("os.name").toLowerCase()
    .startsWith("windows")
val isMacOS = System.getProperty("os.name").toLowerCase()
    .startsWith("mac")

fun image(path: String): Image {
    var img: Image? = null
    try {
        FileInputStream(path).let {
            img = ImageIO.read(it)
            it.close()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return img ?: error("cannot load image: $path")
}

fun Int.approach(goal: Int, delta: Int): Int =
    when {
        abs(this - goal) < delta -> goal
        this < goal -> this + delta
        else -> this - delta
    }
