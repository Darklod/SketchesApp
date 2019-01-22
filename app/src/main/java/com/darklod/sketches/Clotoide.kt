package com.darklod.sketches

import com.darklod.app.R
import com.darklod.app.Sketch


class Clotoide : Sketch() {
    override val title: String = "Clotoide"
    override val date: String = "22/01/19"
    override val description: String = "Una Clotoide"
    override val image: Int = R.drawable.work_in_progress

    private var dx = 0.0
    private var dy = 0.0
    private var t = -50.0
    private var oldX = 0.0
    private var oldY = 0.0
    private var S = 150
    private var N = 10000

    override fun settings() {
        super.settings()
        fullScreen()
    }

    override fun setup() {
        translate(width / 2f,height / 2f)

        background(0)
        stroke(237f, 20f, 61f)
        noFill()

        val scale = height/3
        val dt = S/N.toDouble()

        beginShape()
        while (N-- > 0) {
            dx = Math.cos(t * t) * dt
            dy = Math.sin(t * t) * dt

            t += dt

            val x = oldX + dx
            val y = oldY + dy

            vertex(x.toFloat() * scale, -y.toFloat() * scale)

            oldX = x
            oldY = y
        }
        endShape()
    }
}