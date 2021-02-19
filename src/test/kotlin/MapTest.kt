import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import enums.ConsoleInputTypes
import org.junit.Test
import utils.ConsoleUtils
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class MapTest {

    @Test
    fun testBuilder() {
        val xSize = 10
        val ySize = 15
        val consoleUtilsMock = mock<ConsoleUtils> {
            on {
                readValidInput("Insert horizontal map size:", ConsoleInputTypes.NUMBER)
            } doReturn(xSize.toString())
            on {
                readValidInput("Insert vertical map size:", ConsoleInputTypes.NUMBER)
            } doReturn(ySize.toString())
        }

        val map = Map.Builder.setup(consoleUtilsMock)
        assertEquals(Pair(xSize, ySize), map.getMapSize())
    }

    @Test
    fun testIsObstacle() {
        val consoleOutContent = ByteArrayOutputStream()
        System.setOut(PrintStream(consoleOutContent))

        val obstacles = setOf(Pair(1, 2), Pair(5, 4), Pair(8, 1))
        val map = Map(10, 10, obstacles)
        assert(map.isObstacle(Pair(1, 2)))
    }

}