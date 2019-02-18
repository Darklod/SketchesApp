package com.darklod.sketches

import com.darklod.app.R
import com.darklod.app.Sketch
import java.lang.Math.random
import java.lang.Math.round
import java.util.*


class Matrix : Sketch() {
    override val title: String = "Matrix"
    override val date: Date = dateFormat.parse("31/07/2017")
    override val description: String = "..."
    override val image: Int = R.drawable.matrix

    var fontSize = 20f
    private var streams = ArrayList<Stream>()

    override fun settings() {
        fullScreen()
    }

    override fun setup() {
        var j = 0f

        while (j < width) {
            val stream = Stream(this)
            stream.generate(j, random(-500f, 0f))
            streams.add(stream)
            j += fontSize
        }

        textSize(fontSize)
    }

    override fun draw() {
        background(0, 150f)

        for (i in 0 until streams.size) {
            streams[i].render()
        }
    }
}

class Particle(val sketch: Matrix, var x: Float, var y: Float, var speed: Float, val first: Boolean) {
    var value: String = ""
    var timeslice: Int = round(random() * 18 + 2).toInt()

    fun render() {
        sketch.text(this.value, this.x, this.y)
        this.rain()
        this.setRandomCharacter()
    }

    fun setRandomCharacter() {
        if (sketch.frameCount % this.timeslice == 0) {
            val off = round(random() * 96).toInt()
            this.value = Character.toString((0x30A0 + off).toChar())
        }
    }

    fun rain() {
        if (this.y >= sketch.height) {
            this.y = 0f
        } else {
            this.y += this.speed
        }
    }
}


class Stream(val sketch: Matrix) {
    var particles = ArrayList<Particle>()
    var speed = round(random() * 15 + 5).toFloat()
    var n = round(random() * 24 + 6)

    fun generate(x: Float, y: Float) {
        var first = round(random()).toInt() == 1

        for (i in 0 until this.n) {
            val p = Particle(sketch, x, y - i * sketch.fontSize, speed, first)
            p.setRandomCharacter()

            this.particles.add(p)

            first = false
        }
    }

    fun render() {
        particles.forEach{ p ->
            if (p.first) {
                sketch.fill(100f, 255f, 170f)
            } else {
                sketch.fill(0f, 255f, 70f)
            }
            sketch.text(p.value, p.x, p.y)

            p.rain()
            p.setRandomCharacter();
        }
    }
}
