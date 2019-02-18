package com.darklod.sketches

import com.darklod.app.R
import com.darklod.app.Sketch
import java.util.*

class GosperCurve : Sketch() {
    override val date: Date = dateFormat.parse("04/12/2017")
    override val title = "Gosper Curve"
    override val description = "Something cool"
    override val image = R.drawable.gospercurve

    // TURTLE
    private var x = 0f
    private var y = 0f
    private var currentAngle = 0f
    private var step = 18f
    private var angle = 60f

    // LINDENMAYER
    private var axiom : String = "A"
    private var iterations = 4
    private var rules = HashMap<String, String>()

    private var index = 0

    override fun settings() {
        fullScreen()
    }

    override fun setup() {
        orientation(LANDSCAPE)

        // L-SYSTEM RULES
        rules["A"] = "A+BF++BF-FA--FAFA-BF+"
        rules["B"] = "-FA+BFBF++BF+FA--FA-B"

        background(250)
        strokeWeight(4f)
        stroke(0f, 0f, 0f, 255f)
        colorMode(HSB, 360f, 100f, 50f)

        x = width/2f + step * 10f
        y = 50f

        // COMPUTE THE L-SYSTEM
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

    // TODO: REFACTORING
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