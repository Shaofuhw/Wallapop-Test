import enums.ConsoleInputTypes
import enums.Orientation
import enums.RoverCommand
import utils.ConsoleUtils

class Rover(private val coordinates: Coordinates,
            private var orientation: Orientation,
            private val map: Map) {

    fun runCommand(command: RoverCommand?) {
        if (command != null) {
            when (command) {
                RoverCommand.FORWARD -> this.moveForward()
                RoverCommand.BACKWARD -> this.moveBackwards()
                RoverCommand.TURN_LEFT -> this.turnLeft()
                RoverCommand.TURN_RIGHT -> this.turnRight()
            }
        }
    }

    fun printPosition() {
        val position = this.coordinates.getPosition()
        println("\nCurrent rover position:")
        println("[${position.first}, ${position.second}] Facing ${orientation.name}")
    }

    fun getCoordinates(): Coordinates {
        return this.coordinates
    }

    fun getOrientation(): Orientation {
        return this.orientation
    }

    private fun moveForward() {
        when (this.orientation) {
            Orientation.NORTH -> this.coordinates.increaseY(this.map)
            Orientation.EAST -> this.coordinates.increaseX(this.map)
            Orientation.SOUTH -> this.coordinates.decreaseY(this.map)
            Orientation.WEST -> this.coordinates.decreaseX(this.map)
        }
    }

    private fun moveBackwards() {
        when (this.orientation) {
            Orientation.NORTH -> this.coordinates.decreaseY(this.map)
            Orientation.EAST -> this.coordinates.decreaseX(this.map)
            Orientation.SOUTH -> this.coordinates.increaseY(this.map)
            Orientation.WEST -> this.coordinates.increaseX(this.map)
        }
    }

    private fun turnLeft() {
        when (this.orientation) {
            Orientation.NORTH -> this.orientation = Orientation.WEST
            Orientation.EAST -> this.orientation = Orientation.NORTH
            Orientation.SOUTH -> this.orientation = Orientation.EAST
            Orientation.WEST -> this.orientation = Orientation.SOUTH
        }
    }

    private fun turnRight() {
        when (this.orientation) {
            Orientation.NORTH -> this.orientation = Orientation.EAST
            Orientation.EAST -> this.orientation = Orientation.SOUTH
            Orientation.SOUTH -> this.orientation = Orientation.WEST
            Orientation.WEST -> this.orientation = Orientation.NORTH
        }
    }

    object Builder {
        fun setup(map: Map, consoleUtils: ConsoleUtils = ConsoleUtils()): Rover {
            val mapSize = map.getMapSize()
            val roverX = consoleUtils.readValidInput("Insert horizontal initial rover position:", ConsoleInputTypes.LIMITED_NUMBER, mapSize.first)
            val roverY = consoleUtils.readValidInput("Insert vertical initial rover position:", ConsoleInputTypes.LIMITED_NUMBER, mapSize.second)
            val orientation = consoleUtils.readValidInput("Insert initial rover direction: (n, e, s, w)", ConsoleInputTypes.ORIENTATION)

            val coordinates = Coordinates(Integer.parseInt(roverX), Integer.parseInt(roverY))
            return Rover(coordinates, Orientation.find(orientation) ?: Orientation.NORTH, map)
        }
    }

}