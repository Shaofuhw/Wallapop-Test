import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import enums.ConsoleInputTypes
import enums.Orientation
import enums.RoverCommand
import org.junit.Test
import utils.ConsoleUtils
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class RoverTest {

    private val testX = 1

    private val testY = 10

    private val testCoordinates = Coordinates(testX, testY)

    private val testOrientation = Orientation.EAST

    private val testMap = Map(15, 15)

    private val consoleUtilsMock = mock<ConsoleUtils> {
        on {
            readValidInput("Insert horizontal initial rover position:", ConsoleInputTypes.LIMITED_NUMBER, 15)
        } doReturn(testX.toString())
        on {
            readValidInput("Insert vertical initial rover position:", ConsoleInputTypes.LIMITED_NUMBER, 15)
        } doReturn(testY.toString())
        on {
            readValidInput("Insert initial rover direction: (n, e, s, w)", ConsoleInputTypes.ORIENTATION)
        } doReturn(testOrientation.orientation)
    }

    @Test
    fun testSetup() {
        val rover = Rover.Builder.setup(this.testMap, this.consoleUtilsMock)
        val coordinates = rover.getCoordinates()
        val orientation = rover.getOrientation()

        assertEquals(Pair(testX, testY), coordinates.getPosition())
        assertEquals(testOrientation, orientation)
    }

    @Test
    fun testPrintPosition() {
        val consoleOutContent = ByteArrayOutputStream()
        System.setOut(PrintStream(consoleOutContent))

        val rover = Rover(this.testCoordinates, this.testOrientation, this.testMap)
        rover.printPosition()

        val separator = System.lineSeparator()
        assertEquals(consoleOutContent.toString().trim(), "Current rover position:$separator[$testX, $testY] Facing $testOrientation")
    }

    @Test
    fun testTurnLeftCommand() {
        val rover = Rover(this.testCoordinates, this.testOrientation, this.testMap)
        assertEquals(this.testOrientation, rover.getOrientation())

        rover.runCommand(RoverCommand.TURN_LEFT)
        assertEquals(Orientation.NORTH, rover.getOrientation())

        rover.runCommand(RoverCommand.TURN_LEFT)
        assertEquals(Orientation.WEST, rover.getOrientation())

        rover.runCommand(RoverCommand.TURN_LEFT)
        assertEquals(Orientation.SOUTH, rover.getOrientation())

        rover.runCommand(RoverCommand.TURN_LEFT)
        assertEquals(Orientation.EAST, rover.getOrientation())
    }

    @Test
    fun testTurnRightCommand() {
        val rover = Rover(this.testCoordinates, this.testOrientation, this.testMap)
        assertEquals(this.testOrientation, rover.getOrientation())

        rover.runCommand(RoverCommand.TURN_RIGHT)
        assertEquals(Orientation.SOUTH, rover.getOrientation())

        rover.runCommand(RoverCommand.TURN_RIGHT)
        assertEquals(Orientation.WEST, rover.getOrientation())

        rover.runCommand(RoverCommand.TURN_RIGHT)
        assertEquals(Orientation.NORTH, rover.getOrientation())

        rover.runCommand(RoverCommand.TURN_RIGHT)
        assertEquals(Orientation.EAST, rover.getOrientation())
    }

    @Test
    fun testForwardCommand() {
        val coordinatesMock = mock<Coordinates>()

        var rover = Rover(coordinatesMock, Orientation.EAST, this.testMap)
        rover.runCommand(RoverCommand.FORWARD)
        verify(coordinatesMock, times(1)).increaseX(this.testMap)

        rover = Rover(coordinatesMock, Orientation.NORTH, this.testMap)
        rover.runCommand(RoverCommand.FORWARD)
        verify(coordinatesMock, times(1)).increaseY(this.testMap)

        rover = Rover(coordinatesMock, Orientation.WEST, this.testMap)
        rover.runCommand(RoverCommand.FORWARD)
        verify(coordinatesMock, times(1)).decreaseX(this.testMap)

        rover = Rover(coordinatesMock, Orientation.SOUTH, this.testMap)
        rover.runCommand(RoverCommand.FORWARD)
        verify(coordinatesMock, times(1)).decreaseY(this.testMap)
    }

    @Test
    fun testBackwardCommand() {
        val coordinatesMock = mock<Coordinates>()

        var rover = Rover(coordinatesMock, Orientation.EAST, this.testMap)
        rover.runCommand(RoverCommand.BACKWARD)
        verify(coordinatesMock, times(1)).decreaseX(this.testMap)

        rover = Rover(coordinatesMock, Orientation.NORTH, this.testMap)
        rover.runCommand(RoverCommand.BACKWARD)
        verify(coordinatesMock, times(1)).decreaseY(this.testMap)

        rover = Rover(coordinatesMock, Orientation.WEST, this.testMap)
        rover.runCommand(RoverCommand.BACKWARD)
        verify(coordinatesMock, times(1)).increaseX(this.testMap)

        rover = Rover(coordinatesMock, Orientation.SOUTH, this.testMap)
        rover.runCommand(RoverCommand.BACKWARD)
        verify(coordinatesMock, times(1)).increaseY(this.testMap)
    }

}