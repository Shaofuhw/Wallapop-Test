package utils

import enums.ConsoleInputTypes
import enums.Orientation
import enums.RoverCommand
import java.util.*

class ConsoleUtils(private var reader: Scanner = Scanner(System.`in`)) {

    fun readValidInput(inputText: String, inputType: ConsoleInputTypes, maxValue: Int = 0): String {
        while (true) {
            println("\n$inputText")
            val value = this.reader.next()

            val isValidValue = try {
                (inputType == ConsoleInputTypes.NUMBER && Integer.parseInt(value) > 0) ||
                (inputType == ConsoleInputTypes.LIMITED_NUMBER && Integer.parseInt(value) > 0 && Integer.parseInt(value) <= maxValue) ||
                (inputType == ConsoleInputTypes.ORIENTATION && Orientation.values().any { it.orientation == value }) ||
                (inputType == ConsoleInputTypes.ROVER_COMMAND && RoverCommand.values().any { it.command == value })
            } catch (e: Exception) {
                false
            }

            if (isValidValue) {
                return value
            } else {
                println("Invalid value, please try again.\n")
            }
        }
    }

}