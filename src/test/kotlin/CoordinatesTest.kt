import org.junit.Test
import kotlin.test.assertEquals

class CoordinatesTest {

    @Test
    fun testCoordinates() {
        val testMap = Map(5, 11)

        val coordinates = Coordinates(1, 10)
        assertEquals(Pair(1, 10), coordinates.getPosition())

        coordinates.decreaseX(testMap)
        assertEquals(Pair(5, 10), coordinates.getPosition())

        coordinates.decreaseX(testMap)
        assertEquals(Pair(4, 10), coordinates.getPosition())

        coordinates.increaseX(testMap)
        assertEquals(Pair(5, 10), coordinates.getPosition())

        coordinates.increaseX(testMap)
        assertEquals(Pair(1, 10), coordinates.getPosition())


        coordinates.increaseY(testMap)
        assertEquals(Pair(1, 11), coordinates.getPosition())

        coordinates.increaseY(testMap)
        assertEquals(Pair(1, 1), coordinates.getPosition())

        coordinates.decreaseY(testMap)
        assertEquals(Pair(1, 11), coordinates.getPosition())

        coordinates.decreaseY(testMap)
        assertEquals(Pair(1, 10), coordinates.getPosition())
    }

}