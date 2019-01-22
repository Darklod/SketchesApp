package com.darklod.app

import android.content.pm.ActivityInfo
import processing.core.PApplet
import java.io.Serializable

abstract class Sketch : Serializable, Cloneable, PApplet() {
    abstract val description : String
    abstract val date : String
    abstract val title : String
    abstract val image : Int

    var orientation : Int = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

}

