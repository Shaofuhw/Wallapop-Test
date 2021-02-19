package enums

enum class RoverCommand(val command: String) {
    FORWARD("f"),
    BACKWARD("b"),
    TURN_LEFT("l"),
    TURN_RIGHT("r");

    companion object {
        fun find(command: String): RoverCommand? = values().find { it.command == command }
    }
}