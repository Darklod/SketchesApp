package com.darklod.app

import android.content.pm.ActivityInfo
import android.graphics.Typeface
import android.util.Log
import processing.core.PApplet
import processing.core.PFont
import java.io.File
import java.io.Serializable
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

abstract class Sketch : Serializable, Cloneable, PApplet() {
    abstract val description : String
    abstract val date : Date
    abstract val title : String
    abstract val image : Int

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ITALY)

    var orientation : Int = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    protected fun fontPath() : String {
        return "$sketchPath${File.separator}fonts"
    }

    override fun createFont(filename : String, size : Float): PFont {
        var typeface : Typeface? = null

        try {
            typeface = Typeface.createFromFile(filename)
        } catch (e : Exception) {
            Log.e("ERROR", e.message)
        } finally {
            if (typeface != null)
                return PFont(typeface, size.toInt(), true)
        }

        return createDefaultFont(size)
    }
}

