import enums.ConsoleInputTypes
import org.junit.Before
import org.junit.Test
import utils.ConsoleUtils
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*
import kotlin.test.assertEquals

class ConsoleUtilsTest {

    private val consoleOutContent = ByteArrayOutputStream()

    @Before
    fun setUpStreams() {
        System.setOut(PrintStream(this.consoleOutContent))
    }

    @Test
    fun testReadValidInputForNumber() {
        val inputStream = "1".byteInputStream()
        val consoleUtils = ConsoleUtils(Scanner(inputStream))

        val value = consoleUtils.readValidInput("Test Positive Number", ConsoleInputTypes.NUMBER)
        assertEquals(consoleOutContent.toString().trim(), "Test Positive Number")
        assertEquals(value, "1")
    }

    @Test
    fun testReadValidInputForLimitedNumber() {
        val inputStream = "5".byteInputStream()
        val consoleUtils = ConsoleUtils(Scanner(inputStream))

        val value = consoleUtils.readValidInput("Test Positive Number", ConsoleInputTypes.LIMITED_NUMBER, 10)
        assertEquals(consoleOutContent.toString().trim(), "Test Positive Number")
        assertEquals(value, "5")
    }

    @Test
    fun testReadValidInputForRoverCommand() {
        val inputStream = "f".byteInputStream()
        val consoleUtils = ConsoleUtils(Scanner(inputStream))

        val value = consoleUtils.readValidInput("Test Rover Command", ConsoleInputTypes.ROVER_COMMAND)
        assertEquals(consoleOutContent.toString().trim(), "Test Rover Command")
        assertEquals(value, "f")
    }

    @Test
    fun testReadValidInputForOrientation() {
        val inputStream = "n".byteInputStream()
        val consoleUtils = ConsoleUtils(Scanner(inputStream))

        val value = consoleUtils.readValidInput("Test Orientation", ConsoleInputTypes.ORIENTATION)
        assertEquals(consoleOutContent.toString().trim(), "Test Orientation")
        assertEquals(value, "n")
    }

}