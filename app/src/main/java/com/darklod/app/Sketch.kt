package com.darklod.app

import processing.core.PApplet

abstract class Sketch : PApplet() {
    abstract val description : String
    abstract val date : String
    abstract val title : String
    abstract val image : Int
}