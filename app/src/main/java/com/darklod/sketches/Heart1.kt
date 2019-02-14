package com.darklod.sketches

import android.util.Log
import com.darklod.app.R
import com.darklod.app.Sketch
import processing.core.PFont
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


// HEART PARAMETRIC FUNCTION
// x = 16sin^3t
// y = 13cost-5cos(2t)-2cos(3t)-cos(4t)

class Heart1 : Sketch() {
    override val title: String = "Parametric Heart"
    override val date: Date = dateFormat.parse("27/12/2016")
    override val description: String = "..."
    override val image: Int = R.drawable.heart1

    private lateinit var font : PFont

    override fun settings() {
        fullScreen()
    }

    override fun setup() {
        val filename = "${fontPath()}${File.separator}flea_market_finds.ttf"
        font = createFont(filename, 30f)

        background(255f, 0f, 100f)
        fill(255)

        pushMatrix()
        translate(50f, height - height / 6f)

        textFont(font)
        textSize(height / 25f)
        text("x(t) = 16sin³t\ny(t) = 13cost - 5cos2t - 2cos3t - cos4t", 10f, 30f)
        popMatrix()

        translate(width / 2f, height / 2f)

        val s = height * 0.02f

        noStroke()
        beginShape()
        var t = 0f

        while (t < TWO_PI) {
            val x = 16 * pow(sin(t), 3f)
            val y = -1 * (13 * cos(t) - 5 * cos((2 * t)) - 2 * cos((3 * t)) - cos((4 * t)))

            vertex(x * s, y * s)

            t += HALF_PI / 30f
        }
        endShape()
    }

}