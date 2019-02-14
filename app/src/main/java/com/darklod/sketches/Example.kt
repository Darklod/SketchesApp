package com.darklod.sketches

import android.content.pm.ActivityInfo
import com.darklod.app.R
import com.darklod.app.Sketch
import java.util.*

class Example : Sketch() {

    override val description = "good description"
    override val date: Date = dateFormat.parse("09/01/2019")
    override val title = "Example"
    override val image = R.drawable.work_in_progress

    init {
        orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun settings() {
        fullScreen()
    }

    override fun setup() {
        orientation(PORTRAIT)
        background(0)
        fill(255)
    }

    override fun draw() {
        stroke(255)
        if (mousePressed) {
            ellipse(mouseX.toFloat(), mouseY.toFloat(), 50f, 50f)
        }
    }
}
