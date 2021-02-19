import enums.ConsoleInputTypes
import utils.ConsoleUtils
import kotlin.math.ceil
import kotlin.random.Random

class Map(
    private val xSize: Int,
    private val ySize: Int,
    private var obstacles: Set<Pair<Int, Int>> = emptySet()
) {

    fun getMapSize(): Pair<Int, Int> {
        return Pair(this.xSize, this.ySize)
    }

    fun setObstacles(obstacles: List<Pair<Int, Int>>) {
        this.obstacles = obstacles.toSet()
        val obstaclesString = obstacles.joinToString {
            "[${it.first}, ${it.second}]"
        }
        println("Obstacles found on $obstaclesString")
    }

    fun generateRandomObstacles(): List<Pair<Int, Int>> {
        val numberOfPoints = this.xSize * this.ySize
        val numberOfObstacles = ceil((numberOfPoints / 3).toDouble()).toInt()
        val obstacles = mutableListOf<Pair<Int, Int>>()
        repeat (numberOfObstacles) {
            val randomX = Random.nextInt(1, this.xSize)
            val randomY = Random.nextInt(1, this.ySize)
            obstacles.add(Pair(randomX, randomY))
        }
        return obstacles
    }

    fun isObstacle(point: Pair<Int, Int>): Boolean {
        val isObstacle = this.obstacles.contains(point)
        if (isObstacle) {
            println("Point [${point.first}, ${point.second}] contains an obstacle.")
        }
        return isObstacle
    }

    object Builder {
        fun setup(consoleUtils: ConsoleUtils = ConsoleUtils()): Map {
            val sizeX = consoleUtils.readValidInput("Insert horizontal map size:", ConsoleInputTypes.NUMBER)
            val sizeY = consoleUtils.readValidInput("Insert vertical map size:", ConsoleInputTypes.NUMBER)
            return Map(Integer.parseInt(sizeX), Integer.parseInt(sizeY), emptySet())
        }
    }
}