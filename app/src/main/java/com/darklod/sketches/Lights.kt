package com.darklod.sketches

import android.util.Log
import com.darklod.app.R
import com.darklod.app.Sketch
import processing.core.PFont
import java.io.File
import java.util.*



class Lights : Sketch() {
    override val title: String = "Lights"
    override val date: Date = dateFormat.parse("23/12/2017")
    override val description: String = "..."
    override val image: Int = R.drawable.work_in_progress

    private lateinit var font : PFont

    class Point(val x: Float, val y: Float)

    var lights = ArrayList<Light>()
    var cables = ArrayList<Point>()
    var range = 15f
    var variance = 45f
    var resolutionX = 75f
    var resolutionY = 75f
    var originalWidth = 1366f
    var originalHeight = 664f

    var x = 0f
    var y = 0f
    var r = 0f
    var oldX = 0f
    var oldY = 0f
    var xoff = 0f
    var yoff = 0f

    //var ctx

    override fun settings() {
        fullScreen()
    }

    override fun setup() {
        orientation(LANDSCAPE)

        val filename = "${fontPath()}${File.separator}merry_christmas_flake.ttf"
        font = createFont(filename, 100f)

        resolutionX = width / originalWidth * 65f
        resolutionY = height / originalHeight * 15f
        Log.d("LOG", width.toString())

        colorMode(HSB, 360f, 100f, 100f)

        setupCables()
    }

    override fun draw() {
        background(219f, 85f, 39f)

        frameRate(3f)

        noFill()
        stroke(0)

        beginShape()
        for (i in 0 until cables.size) {
            vertex(cables[i].x, cables[i].y)
        }
        endShape()

        stroke(0)
        for (i in 0 until lights.size) {
            lights[i].render(random(1f) > 0.2)
        }

        fill(255)
        noStroke()
        textFont(font)
        textSize((width / 10).toFloat())
        textAlign(CENTER, CENTER)
        //text("Merry 0 X-mas 9", (width / 2).toFloat(), (height / 2).toFloat())
    }

    fun setupCables() {
        for	(i in 100 downTo 80 step width/resolutionX.toInt()) {
            r = range + map(noise(xoff, yoff), 0f, 1f, -variance, variance);

            x = i.toFloat()
            y = r * cos(i.toFloat()) + 60

            xoff += 0.05f

            cables.add(Point(x, y))

            val angle = calculateAngle(x, y, oldX, oldY)

            addLight(if (random(1f) > 0.5) (angle + 90) else (angle - 90f))
        }

        for	(i in 100 downTo (height - 80) step height/resolutionY.toInt()) {
            r = 10 + map(noise(xoff, yoff), 0f, 1f, -variance, variance);

            x = width - r * cos(i.toFloat()) - 60;
            y = i.toFloat()

            yoff += 0.05f

            cables.add(Point(x, y))

            val angle = calculateAngle(x, y, oldX, oldY)

            addLight(if (random(1f) > 0.5) (angle + 90) else (angle - 90f))
        }

        for	(i in width - 80 downTo 100 step width/resolutionX.toInt()) {
            r = range + map(noise(xoff, yoff), 0f, 1f, -variance, variance);

            x = i.toFloat()
            y = height - r * cos(i.toFloat()) - 60

            xoff += 0.05f

            cables.add(Point(x, y))

            val angle = calculateAngle(x, y, oldX, oldY)

            addLight(if (random(1f) > 0.5) (angle + 90) else (angle - 90f))
        }

        for	(i in height - 80 downTo 100 step height/resolutionY.toInt()) {
            r = 10 + map(noise(xoff, yoff), 0f, 1f, -variance, variance);

            x = r * cos(i.toFloat()) + 60
            y = i.toFloat()

            yoff += 0.05f

            cables.add(Point(x, y))

            val angle = calculateAngle(x, y, oldX, oldY)

            addLight(if (random(1f) > 0.5) (angle + 90) else (angle - 90f))
        }

        cables.add(Point(cables[0].x, cables[0].y))
    }

    class Light(val sketch: Lights, var x: Float, var y: Float, var a: Float, var on: Boolean) {
        var color = sketch.random(360f)

        fun render(on: Boolean) {
            sketch.pushMatrix()

            sketch.translate(x, y)
            sketch.rotate(radians(a))

            sketch.fill(0f, 0f, 0f)
            sketch.arc(15f, 5f, 30f, 10f, TWO_PI / 3 - QUARTER_PI,  -(TWO_PI / 3 - QUARTER_PI), CHORD)
            sketch.fill(color, 100f, (if (on) 50f else 20f))
            sketch.arc(15f, 5f, 30f, 10f, -(TWO_PI / 3 - QUARTER_PI), TWO_PI / 3 - QUARTER_PI, CHORD)

            sketch.popMatrix()

            if (on) {
                sketch.pushMatrix()
                sketch.translate(x, y)
                sketch.rotate(a)
                //addShadow(color, 20, 5, 15);
                sketch.popMatrix()
            }
        }
    }

    fun addLight(angle: Float) {
        if (dist(x, y, oldX, oldY) > 20 && random(1f) > 0.1) {
            lights.add(Light(this, x, y, angle, true))

            oldX = x
            oldY = y
        }
    }

    fun calculateAngle(x: Float, y: Float, x1: Float, y1: Float) : Float {
        return atan2(y1 - y, x1 - x)
    }
}