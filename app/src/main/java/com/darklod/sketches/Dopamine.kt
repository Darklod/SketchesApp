package com.darklod.sketches

import com.darklod.app.R
import com.darklod.app.Sketch
import processing.core.PFont
import java.io.File
import java.util.*

class Dopamine : Sketch() {
    override val date: Date = dateFormat.parse("31/08/2017")
    override val title = "Dopamine"
    override val description = "Something cool"
    override val image = R.drawable.dopamine

    private lateinit var font: PFont

    override fun settings() {
        fullScreen()
    }

    override fun setup() {
        val filename = "${fontPath()}${File.separator}free_sans.ttf"
        font = createFont(filename, 100f)
    }

    override fun draw() {
        background(255f)

        pushMatrix()

        translate(width/2f, height/2f)

        scale(1.5f)

        noStroke()
        fill(30f, 30f, 30f)

        pushMatrix()
        rotate(90 * PI / 180)
        Utils(this).polygon(0, 0, 120, 6, 15, null)
        Utils(this).polygon(0, 0, 100, 6, 15, listOf(0,2,4))
        popMatrix()

        rotate(30 * PI / 180)
        beginShape()
        vertex(-120f, -7.5f)
        vertex(-230f, -7.5f)
        vertex(-230f, +7.5f)
        vertex(-120f, +7.5f)
        endShape()

        ellipse(-250f, 0f, 60f, 60f)

        rotate(-60 * PI / 180)
        beginShape()
        vertex(-120f, -7.5f)
        vertex(-230f, -7.5f)
        vertex(-230f, +7.5f)
        vertex(-120f, +7.5f)
        endShape()

        ellipse(-250f, 0f, 60f, 60f)

        rotate(-180 * PI / 180)
        beginShape()
        vertex(-120f, -7.5f)
        vertex(-230f, -7.5f)
        vertex(-230f, +7.5f)
        vertex(-120f, +7.5f)
        endShape()

        translate(-224f, 3f)

        rotate(60 * PI /180)
        beginShape()
        vertex(-0f, -7.5f)
        vertex(-110f, -7.5f)
        vertex(-110f, +7.5f)
        vertex(-0f, +7.5f)
        endShape()

        translate(-104f, -4f)
        rotate(-60 * PI /180)
        beginShape()
        vertex(-0f, -7.5f)
        vertex(-70f, -7.5f)
        vertex(-70f, +7.5f)
        vertex(-0f, +7.5f)
        endShape()

        translate(-80f, 0f)

        ellipse(0f, 0f, 60f, 60f)

        popMatrix()

        textSize(30f)
        textFont(font)
        textAlign(CENTER, CENTER)
        text("Dopamine: C₈H₁₁NO₂", width/2f, 100f)

        noLoop()
    }
}