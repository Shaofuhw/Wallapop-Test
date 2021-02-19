package enums

enum class Orientation(val orientation: String) {
    NORTH("n"),
    EAST("e"),
    SOUTH("s"),
    WEST("w");

    companion object {
        fun find(orientation: String): Orientation? = values().find { it.orientation == orientation }
    }
}