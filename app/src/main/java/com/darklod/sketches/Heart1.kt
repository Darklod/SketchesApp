package com.darklod.sketches

import com.darklod.app.R
import com.darklod.app.Sketch
import processing.core.PFont
import android.content.res.AssetFileDescriptor
import android.util.Log
import java.io.*


// HEART PARAMETRIC FUNCTION
// x = 16sin^3t
// y = 13cost-5cos(2t)-2cos(3t)-cos(4t)

class Heart1 : Sketch() {
    override val title: String = "Parametric Heart"
    override val date: String = "27/12/2016"
    override val description: String = "..."
    override val image: Int = R.drawable.work_in_progress

    private lateinit var font : PFont

    override fun settings() {
        super.settings()
        fullScreen()

        val file = File(sketchPath + File.separator + "flea_market_finds.ttf")
        try {
            if (!file.exists()) {
                val assetManager = this.activity.assets
                val afd: AssetFileDescriptor = assetManager.openFd("flea_market_finds.ttf")
                file.createNewFile()

                copyFdToFile(afd.fileDescriptor, file)
            }
        } catch (ex: IOException) {
            Log.e("LOG", ex.message)
            ex.printStackTrace()
        }

        //font = createFont("flea_market_finds.ttf", 30f, true, null)
        font = loadFont("flea_market_finds.ttf") //flea_market_finds.ttf
    }

    override fun setup() {
        background(255f, 0f, 100f)
        fill(255)

        pushMatrix()
        translate(50f, height - height / 6f)

        textFont(font)
        textSize(height / 25f)
        text("x(t) = 16sin³t\ny(t) = 13cost - 5cos2t - 2cos3t - cos4t", 10f, 30f)
        popMatrix()

        translate(width / 2f, height / 2f)

        val s = height * 0.02f

        noStroke()
        beginShape()
        var t = 0f

        while (t < TWO_PI) {
            val x = 16 * pow(sin(t), 3f)
            val y = -1 * (13 * cos(t) - 5 * cos((2 * t)) - 2 * cos((3 * t)) - cos((4 * t)))

            vertex(x * s, y * s)

            t += HALF_PI / 30f
        }
        endShape()
    }

    fun copyFdToFile(src: FileDescriptor, dst: File) {
        val inChannel = FileInputStream(src).getChannel()
        val outChannel = FileOutputStream(dst).getChannel()
        try {
            inChannel!!.transferTo(0, inChannel.size(), outChannel)
        } finally {
            if (inChannel != null)
                inChannel.close()
            if (outChannel != null)
                outChannel.close()
        }
    }


}