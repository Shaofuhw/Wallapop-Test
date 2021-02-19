class Coordinates(private var x: Int, private var y: Int) {

    fun increaseX(map: Map) {
        val mapSize = map.getMapSize()
        val newValue = this.changeValue(this.x + 1, mapSize.first)
        if (!map.isObstacle(Pair(newValue, this.y))) {
            this.x = newValue
        }
    }

    fun decreaseX(map: Map) {
        val mapSize = map.getMapSize()
        val newValue = this.changeValue(this.x - 1, mapSize.first)
        if (!map.isObstacle(Pair(newValue, this.y))) {
            this.x = newValue
        }
    }

    fun increaseY(map: Map) {
        val mapSize = map.getMapSize()
        val newValue = this.changeValue(this.y + 1, mapSize.second)
        if (!map.isObstacle(Pair(this.x, newValue))) {
            this.y = newValue
        }
    }

    fun decreaseY(map: Map) {
        val mapSize = map.getMapSize()
        val newValue = this.changeValue(this.y - 1, mapSize.second)
        if (!map.isObstacle(Pair(this.x, newValue))) {
            this.y = newValue
        }
    }

    fun getPosition(): Pair<Int, Int> {
        return Pair(this.x, this.y)
    }

    private fun changeValue(value: Int, valueMax: Int): Int {
        return when {
            (value > valueMax) -> 1
            (value <= 0) -> valueMax
            else -> value
        }
    }

}