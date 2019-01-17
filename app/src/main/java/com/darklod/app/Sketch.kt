package com.darklod.app

import processing.core.PApplet
import java.io.Serializable

abstract class Sketch : Serializable, PApplet() {
    abstract val description : String
    abstract val date : String
    abstract val title : String
    abstract val image : Int
}

