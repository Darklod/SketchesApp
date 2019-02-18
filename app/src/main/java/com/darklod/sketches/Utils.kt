package com.darklod.sketches

import com.darklod.app.Sketch
import processing.core.PApplet
import java.util.ArrayList
import java.util.HashMap

class Utils(val sketch: Sketch) {

    fun polygon(x: Int, y: Int, radius: Int, sides: Int, border: Int, sidesToShow: List<Int>?) {
        val angle = PApplet.TWO_PI / sides

        var k = 0
        var a = 0f

        while  (a < PApplet.TWO_PI) {
            if (sidesToShow?.contains(k) == false) {
                k++
                a += angle
                continue
            }

            val sx = x + PApplet.cos(a) * radius
            val sy = y + PApplet.sin(a) * radius

            val sx2 = x + PApplet.cos(a) * (radius + border)
            val sy2 = y + PApplet.sin(a) * (radius + border)

            val sx3 = x + PApplet.cos(a + angle) * radius
            val sy3 = y + PApplet.sin(a + angle) * radius

            val sx4 = x + PApplet.cos(a + angle) * (radius + border)
            val sy4 = y + PApplet.sin(a + angle) * (radius + border)

            sketch.beginShape(PApplet.QUADS)
            sketch.vertex(sx, sy)
            sketch.vertex(sx3, sy3)
            sketch.vertex(sx4, sy4)
            sketch.vertex(sx2, sy2)
            sketch.endShape()

            k++
            a += angle
        }
    }

    fun lindenmayer(axiom : String, rules : HashMap<String, String>, iterations : Int) : String {
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
}