package amcr.gamelauncher

data class Config(var games: Array<Game>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Config
        if (!games.contentEquals(other.games)) return false
        return true
    }

    override fun hashCode(): Int {
        return games.contentHashCode()
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
