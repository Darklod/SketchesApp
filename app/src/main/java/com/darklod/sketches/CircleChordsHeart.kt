package com.darklod.sketches

import android.util.Log
import com.darklod.app.R
import com.darklod.app.Sketch
import java.util.*

class CircleChordsHeart : Sketch() {
    override val title: String = "Circle Chords Heart"
    override val date: Date = dateFormat.parse("27/12/2016")
    override val description: String = "..."
    override val image: Int = R.drawable.circlechords

    private var r = 100
    private var phase : Int = -1
    private var inc = 0f
    private var count = 0f
    private var begin = false

    override fun settings() {
        fullScreen()
    }

    override fun setup() {
        r = height / 3

        background(30f, 0f, 100f)
        stroke(255f,0f,55f)
        noFill()

        translate(width / 2f, height / 2f)
        ellipse(0f,0f,r * 2f,r *2f)

        frameRate(15f)
    }

    override fun draw() {
        translate(width / 2f, height / 2f)

        when (phase) {
            -1 -> {
                // Dovrebbe farlo il caso 0
                beginShape()
                var i = 0f
                while (i <= TWO_PI) {
                    val x = r * cos(i)
                    val y = r * sin(i)
                    vertex(x, y)
                    i += HALF_PI
                }
                endShape()
                phase++
            }
            0 -> {
                // BUG: Non Disegna Nulla
                if (!begin) {
                    inc = HALF_PI
                    count = 0f
                    begin = true
                    beginShape()
                    Log.d("LOG", "START")
                }

                val x = r * cos(count)
                val y = r * sin(count)

                vertex(x, y) // Mmmmh...

                Log.d("LOG", "x: $x, y: $y")

                if (count > TWO_PI) {
                    phase++
                    endShape()
                    begin = false
                    Log.d("LOG", "END")
                }
                count += inc
            }
            1 -> {
                if (!begin) {
                    inc = HALF_PI / 10f
                    count = HALF_PI / 10f
                    begin = true
                    beginShape()
                }

                var x = r * cos(count)
                var y = r * sin(count)

                var x1 = r * cos(count - HALF_PI)
                var y1 = r * sin(count - HALF_PI)

                line(x, y, x1, y1)

                val j = PI + count
                x = r * cos(j)
                y = r * sin(j)

                x1 = r * cos(j - HALF_PI)
                y1 = r * sin(j - HALF_PI)

                line(x, y, x1, y1)

                if (count >= HALF_PI - HALF_PI / 10f) {
                    phase++
                    endShape()
                    begin = false
                }
                count += inc
            }
            2 -> {
                if (!begin) {
                    inc = HALF_PI / 10f
                    count = 0f
                    begin = true
                    beginShape()
                }

                var ii = (count - HALF_PI) * 2f
                var x = r * cos(ii / 2f)
                var y = r * sin(ii / 2f)

                var x1 = r * cos(ii - HALF_PI)
                var y1 = r * sin(ii - HALF_PI)

                line(x, y, x1, y1)

                ii = (count + HALF_PI / 10f - PI) * 2f
                x = r * cos(ii / 2)
                y = r * sin(ii / 2)

                x1 = r * cos(ii - HALF_PI)
                y1 = r * sin(ii - HALF_PI)

                line(x, y, x1, y1)

                if (count >= HALF_PI - HALF_PI / 10f) {
                    phase++
                    endShape()
                    begin = false
                }
                count += inc
            }
            else -> noLoop()
        }
    }
}