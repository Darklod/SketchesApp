package com.darklod.sketches

import android.content.pm.ActivityInfo
import com.darklod.app.R
import com.darklod.app.Sketch
import java.util.*

class DragonCurve : Sketch() {
    override val date: Date = dateFormat.parse("04/12/2017")
    override val title = "Dragon Curve"
    override val description = "Something cool"
    override val image = R.drawable.dragoncurve

    init {
        orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    // TURTLE
    private var x = -50f
    private var y = 0f
    private var currentAngle = -90f
    private var step = 22f
    private var angle = 90f

    // LINDENMAYER
    private var axiom : String = "FX"
    private var iterations = 10
    private var rules = HashMap<String, String>()

    private var index = 0

    override fun settings() {
        fullScreen()
    }

    override fun setup() {
        orientation(LANDSCAPE)

        rules["X"] = "X+YF+"
        rules["Y"] = "-FX-Y"

        background(250)
        strokeWeight(4f)
        stroke(0f, 0f, 0f, 255f)
        colorMode(HSB, 360f, 100f, 50f)

        x = width/2f
        y = height/2f

        axiom = Utils(this).lindenmayer(axiom, rules, iterations)
    }

    override fun draw() {
        if (axiom.isNotEmpty() && index < axiom.length){
            drawSystem(axiom[index])

            if (index > axiom.length - 1)
                noLoop()
            else
                index++
        }
    }

    private fun drawSystem(k: Char) {
        when (k) {
            'F' -> {
                val x1 = x + step * cos(radians(currentAngle))
                val y1 = y + step * sin(radians(currentAngle))

                val color = map(index.toFloat(), 0f, axiom.length.toFloat(), 0f, 360f)
                stroke(color, 50f, 100f)
                line(x, y, x1, y1)

                x = x1
                y = y1
            }
            '+' -> currentAngle += angle
            '-' -> currentAngle -= angle
        }
    }
}