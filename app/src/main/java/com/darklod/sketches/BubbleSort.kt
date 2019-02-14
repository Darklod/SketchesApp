package com.darklod.sketches

import com.darklod.app.R
import com.darklod.app.Sketch
import java.util.*
import kotlin.math.roundToInt


class BubbleSort : Sketch() {
    override val description = "good description"
    override val date: Date = dateFormat.parse("06/04/2017")
    override val title = "Bubble Sort"
    override val image = R.drawable.work_in_progress

    private var numbers = ArrayList<Int>()
    private var num = 100
    private var min = 1f
    private var max = 30f
    private var blockSize = 0f
    private var swapped = true

    override fun settings() {
        fullScreen()
    }

    override fun setup() {
        orientation(LANDSCAPE)

        colorMode(HSB, 360f, 100f, 50f)

        max = height - 100f

        for (i in 0 until num) {
            numbers.add(random(min, max).roundToInt())
        }

        blockSize = width / num.toFloat()

        frameRate(40f)
    }

    override fun draw() {
        background(0)

        fill(255)
        textSize(30f)
        text("Bubble Sort - O(n²)", 20f, 50f)

        translate(width / 2f - numbers.size * blockSize / 2f, height.toFloat())

        for (x in 0 until numbers.size) {
            val col = map(numbers[x].toFloat(), min, max, 0f, 360f)
            fill(col, 100f, 50f)
            rect(x * blockSize, -numbers[x].toFloat(), blockSize, numbers[x].toFloat())
        }

        if (swapped) {
            swapped = false
            for (i in 0 until numbers.size - 1) {
                if (numbers[i] > numbers[i + 1]) {
                    val temp = numbers[i]
                    numbers[i] = numbers[i + 1]
                    numbers[i + 1] = temp
                    swapped = true
                }
            }
        }
    }
}