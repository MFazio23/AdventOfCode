package advent2022.day09

class Snake(var x: Int, var y: Int) {
    var head = Point(x, y)
    var tail = Point(x, y)
    var tailTrack = mutableSetOf(tail)

    fun moveToPoint(newPoint: Point) {
        head.x = newPoint.x
        head.y = newPoint.y
        moveTail()
    }

    fun moveHead(instruction: Pair<String, Int>) {
        when (instruction.first) {
            "U" -> moveUp(instruction.second)
            "D" -> moveDown(instruction.second)
            "R" -> moveRight(instruction.second)
            "L" -> moveLeft(instruction.second)
        }
    }

    fun moveUp(distance: Int) {
        repeat(distance) {
            head.x++
            moveTail()
        }
    }

    fun moveDown(distance: Int) {
        repeat(distance) {
            head.x--
            moveTail()
        }
    }

    fun moveRight(distance: Int) {
        repeat(distance) {
            head.y++
            moveTail()
        }
    }

    fun moveLeft(distance: Int) {
        repeat(distance) {
            head.y--
            moveTail()
        }
    }

    private fun moveTail() {
        // horizontal
        when {
            head.x - tail.x >= 2 -> {
                tail = Point(head.x - 1, head.y)

                tailTrack.add(tail)
            }
            tail.x - head.x >= 2 -> {
                tail = Point(head.x + 1, head.y)
                tailTrack.add(tail)
            }
            // vertical
            head.y - tail.y >= 2 -> {
                tail = Point(head.x, head.y - 1)
                tailTrack.add(tail)
            }
            tail.y - head.y >= 2 -> {
                tail = Point(head.x, head.y + 1)
                tailTrack.add(tail)
            }
        }
    }
}

class Point(var x: Int, var y: Int) {
    override fun equals(other: Any?): Boolean {
        other as Point
        return x == other.x && y == other.y
    }

    override fun toString(): String {
        return "point ($x, $y)"
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}