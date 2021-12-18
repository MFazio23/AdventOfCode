package com.twelfthnightdj.advent2021.day17

import com.twelfthnightdj.advent2021.AocDays

class Day17 : AocDays() {

    override var dayId = 17
    private var xMin = 0
    private var xMax = 0
    private var yMin = 0
    private var yMax = 0
    private var xRangeMin = 0
    private var xRangeMax = 0
    var maxY = 0

    override fun partA(): String {
        val (xRange, yRange) = inputAsString.split("target area: ")[1].split(", ")
        xMin = xRange.split("=")[1].split("..")[0].toInt()
        xMax = xRange.split("=")[1].split("..")[1].toInt()
        yMin = yRange.split("=")[1].split("..")[0].toInt()
        yMax = yRange.split("=")[1].split("..")[1].toInt()

        determineXRange()

        (xRangeMin..xRangeMax).forEach { x ->
            (5..1000).forEach { y ->
                val high = shoot(x, y)
                maxY = maxOf(maxY, high)
            }
        }
        return "$maxY"
    }

    private fun determineXRange() {
        var index = 0
        var total = 0
        while (xRangeMax == 0) {
            index++
            total += index
            if (total < xMin) xRangeMin = index
            if (total > xMax) xRangeMax = (index - 1)
        }
    }

    private fun shoot(x: Int, y: Int): Int {
        var currentX = 0
        var currentY = 0
        var dx = x
        var dy = y
        var mY = 0
        while(keepGoing(dx, currentX, currentY)) {
            currentX += dx
            currentY += dy
            dx = maxOf(--dx, 0)
            dy--
            mY = maxOf(mY, currentY)
            if (checkTarget(currentX, currentY)) return mY
        }
        return 0
    }

    private fun keepGoing(dx: Int, currentX: Int, currentY: Int): Boolean {
        if ((dx == 0) && (currentX !in (xMin..xMax))) {
            return false
        }
        if (currentY < yMin) return false
        if (currentX > xMax) return false
        return true
    }

    private fun checkTarget(x: Int, y: Int) = ((x in (xMin..xMax)) && (y in (yMin..yMax)))

}
