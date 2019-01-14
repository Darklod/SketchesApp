package com.darklod.app

import com.darklod.sketches.*

class Sketches {
    companion object {
        val list = arrayListOf<Sketch>()

        init {
            list.add(Example())
            list.add(DragonCurve())

            list.sortBy { x -> x.title }
        }
    }
}