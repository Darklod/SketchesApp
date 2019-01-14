package com.darklod.sketches

import com.darklod.app.R
import com.darklod.app.Sketch

class Example : Sketch() {

    override val description = "good description"
    override val date = "09/01/2019"
    override val title = "Example"
    override val image = R.drawable.work_in_progress

    override fun settings() {
        fullScreen(P2D)
    }

    override fun setup() {
        background(0)
        fill(255)
    }

    override fun draw() {
        stroke(255)
        line(0f, 0f , displayWidth.toFloat(), displayHeight.toFloat())
        if (mousePressed) {
            ellipse(mouseX.toFloat(), mouseY.toFloat(), 50f, 50f)
        }
    }
}
