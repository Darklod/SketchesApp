package com.darklod.sketches

import com.darklod.app.R
import com.darklod.app.Sketch


class Circles : Sketch() {
    override val title: String = "Colored Circles"
    override val date: String = "19/12/2017"
    override val description: String = "..."
    override val image: Int = R.drawable.circles

    private var a = 0f  // angle
    private var r = 0f  // radius

    override fun settings() {
        fullScreen()
    }

    override fun setup() {
        colorMode(HSB, 360f, 100f, 100f)
        background(0)
        noStroke()

        r = height / 5f
    }

    override fun draw() {
        translate(width / 2f, height / 2f)

        val x = r * cos(a * PI / 180)
        val y = r * sin(a * PI / 180)

        fill(a, 100f, 100f, 40f)
        ellipse(x, y, r * 2, r * 2)

        a += 10f

        if (a > 360) noLoop()
    }
}