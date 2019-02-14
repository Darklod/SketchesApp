package com.darklod.sketches

import com.darklod.app.R
import com.darklod.app.Sketch
import java.util.*


class CellularAutomata : Sketch() {
    override val title: String = "Cellular Automata"
    override val date: Date = dateFormat.parse("02/12/2016")
    override val description: String = "..."
    override val image: Int = R.drawable.cellularautomata

    private var arr = ArrayList<Int>()
    private var n = 100
    private var cellsize = 2
    private var y = 0

    override fun settings() {
        super.settings()
        fullScreen()
    }

    override fun setup() {
        n = width / cellsize

        background(0)

        fill(0f, 255f, 0f)
        noStroke()

        for (i in 0 until n) {
            arr.add(Math.floor(Math.random() * 2).toInt())
        }
    }

    override fun draw() {
        val tmp = ArrayList<Int>()
        for (i in 0 until n) {
            tmp.add(process(arr[(i + 1) % n], arr[i], arr[if (i < 1)  n - 1 else i - 1]))
        }
        printStuff(tmp)
        arr = tmp

        if (y > height / cellsize) noLoop()

        y++
    }

    private fun process(a : Int, b : Int, c : Int) : Int {
        val n = a + b * 2 + c * 4

        return when (n) {
            3, 4, 5, 7 -> 1
            else -> 0
        }
    }

    private fun printStuff(arr : ArrayList<Int>) {
        for (i in 0 until arr.size) {
            if (arr[i] == 1) {
                rect(i * cellsize.toFloat(),
                     y * cellsize.toFloat(),
                      cellsize.toFloat(),
                      cellsize.toFloat())
            }
        }
    }
}