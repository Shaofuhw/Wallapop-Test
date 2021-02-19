import enums.ConsoleInputTypes
import enums.RoverCommand
import utils.ConsoleUtils

fun main() {

    val map = Map.Builder.setup()
    val obstacles = map.generateRandomObstacles()
    map.setObstacles(obstacles)

    val rover = Rover.Builder.setup(map)
    rover.printPosition()

    val consoleUtils = ConsoleUtils()

    while(true) {
        val inputText = "Insert command (f = forward, b = backward, l = turn left, r = turn right):"
        val command = RoverCommand.find(consoleUtils.readValidInput(inputText, ConsoleInputTypes.ROVER_COMMAND))
        rover.runCommand(command)
        rover.printPosition()
    }

}
