package com.darklod.app

import com.darklod.sketches.*

class Sketches {
    companion object {
        val list = arrayListOf<Sketch>()

        init {
            list.add(CellularAutomata())
            list.add(Clotoide())
            list.add(BubbleSort())
            list.add(Heart1())
            list.add(Example())
            list.add(CircleChordsHeart())
            list.add(Circles())
            list.add(DragonCurve())
            list.add(SelectionSort())
        }
    }
}