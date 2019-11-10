package amcr.gamelauncher

data class Config(
    var windowTitle: String = "Game Launcher",
    var games: Array<Game>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Config
        if (windowTitle != other.windowTitle) return false
        if (!games.contentEquals(other.games)) return false
        return true
    }

    override fun hashCode(): Int {
        var result = windowTitle.hashCode()
        result = 31 * result + games.contentHashCode()
        return result
    }
}

data class Game(
    var title: String = "no data",
    var author: String = "no data",
    var description: String = "no data",
    var image: String? = null,
    var cmdWin: String = "",
    var cmdMac: String = ""
)
