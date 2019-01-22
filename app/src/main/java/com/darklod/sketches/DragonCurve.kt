package com.darklod.sketches

import android.content.pm.ActivityInfo
import com.darklod.app.R
import com.darklod.app.Sketch
import processing.core.PConstants

class DragonCurve : Sketch() {
    override val date = "Undefined"
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
        frameRate(160f)

        rules["X"] = "X+YF+"
        rules["Y"] = "-FX-Y"

        background(250)
        strokeWeight(4f)
        stroke(0f, 0f, 0f, 255f)
        colorMode(HSB, 360f, 100f, 100f)

        x = width/2f
        y = height/2f

        axiom = lindenmayer(axiom, rules, iterations)
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

    private fun lindenmayer(axiom : String, rules : HashMap<String, String>, iterations : Int) : String {
        if (iterations == 0) {
            return axiom
        } else {
            val splitted = ArrayList<String>(axiom.split(""))

            for ((i, s : String) in splitted.withIndex()) {
                if (rules.containsKey(s)) {
                    splitted[i] = rules.getValue(s)
                }
            }

            return lindenmayer(splitted.joinToString(""), rules, iterations - 1)
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