package com.darklod.sketches

import com.darklod.app.R
import com.darklod.app.Sketch
import processing.core.PFont
import java.io.File
import java.util.*

class HexTime : Sketch() {
    override val title: String = "HexTime"
    override val date: Date = dateFormat.parse("06/04/2017")
    override val description: String = "..."
    override val image: Int = R.drawable.hextime

    private lateinit var font : PFont

    override fun settings() {
        fullScreen()
    }

    override fun setup() {
        val filename = "${fontPath()}${File.separator}chasing_hearts.ttf"
        font = createFont(filename, 100f)
    }

    override fun draw() {
        val h = hour()
        val m = minute()
        val s = second()

        val hh = String.format("%02x", h)
        val hm = String.format("%02x", m)
        val hs = String.format("%02x", s)

        val hex = "#$hh$hm$hs"

        background(h.toFloat(), m.toFloat(), s.toFloat())

        translate(width / 2f,height / 2f)

        fill(255)
        textFont(font)
        textSize(100f)
        textAlign(CENTER, CENTER)
        text(hex.toUpperCase(), 0f, 0f)
    }
}