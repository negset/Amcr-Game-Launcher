package amcr.gamelauncher

import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.Line
import javax.sound.sampled.LineEvent

class Wave(path: String) {
    private var clips = Array<Clip?>(10) { null }
    private var index = 0

    init {
        try {
            for (i in clips.indices) {
                val ais = AudioSystem.getAudioInputStream(File(path))
                clips[i] = AudioSystem.getLine(Line.Info(Clip::class.java)) as Clip
                clips[i]?.addLineListener { event ->
                    if (event.type === LineEvent.Type.STOP) {
                        clips[i]?.stop()
                        clips[i]?.framePosition = 0
                    }
                }
                clips[i]?.open(ais)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun play() {
        clips[index]?.start()
        index = (index + 1) % clips.size
    }

    fun close() {
        clips.forEach { it?.close() }
    }
}
