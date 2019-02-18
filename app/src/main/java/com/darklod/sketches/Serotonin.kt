package com.darklod.sketches

import com.darklod.app.R
import com.darklod.app.Sketch
import processing.core.PFont
import java.io.File
import java.util.*


class Serotonin : Sketch() {
    override val date: Date = dateFormat.parse("01/09/2017")
    override val title = "Serotonin"
    override val description = "Something cool"
    override val image = R.drawable.serotonin

    private lateinit var font : PFont

    override fun settings() {
        fullScreen()
    }

    override fun setup() {
        val filename = "${fontPath()}${File.separator}free_sans.ttf"
        font = createFont(filename, 100f)
    }

    override fun draw() {
        background(255)

        pushMatrix()

        translate(width/2f, height/2f)

        scale(1.5f)

        noStroke()
        fill(30f, 30f, 30f)

        translate(-100f, 0f)
        rotate(18 * PI / 180)

        pushMatrix()
        rotate(90 * PI / 180)
        Utils(this).polygon(0, 0, 120, 6, 15, null)
        Utils(this).polygon(0, 0, 100, 6, 15, listOf(0,2,4))
        popMatrix()

        pushMatrix()
        rotate(30 * PI / 180)
        beginShape()
        vertex(-120f, -7.5f)
        vertex(-230f, -7.5f)
        vertex(-230f, +7.5f)
        vertex(-120f, +7.5f)
        endShape()

        ellipse(-250f, 0f, 60f, 60f)
        popMatrix()

        Utils(this).polygon(195, 0, 95, 5, 15, null)
        Utils(this).polygon(195, 0, 75, 5, 15, listOf(4))

        ellipse(223f, 90f, 60f, 60f)

        rotate(110 * PI / 180)
        translate(-50f, -180f)
        beginShape()
        vertex(-120f, -7.5f)
        vertex(-200f, -7.5f)
        vertex(-200f, +7.5f)
        vertex(-120f, +7.5f)
        endShape()

        translate(-194f, 3f)

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
        text("Serotonin: C₁₀H₁₂N₂O", width/2f, 100f)

        noLoop()
    }
}