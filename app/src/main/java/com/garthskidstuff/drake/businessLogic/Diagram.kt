package com.garthskidstuff.drake.businessLogic

import android.graphics.Rect

data class Diagram(val name: String = "NewDiagram", val boxes: Set<Box> = HashSet()) {
    fun addBox(): Diagram {
        val length: Int = boxes.size
        val box =
            Box(
                "Box: $length", Rect(length, length, length + 1, length + 1)
            )
        val newSet = boxes.toMutableSet()
        newSet.add(box)
        return Diagram(name, newSet)
    }
}

data class Box(
    val id: String,
    val rect: Rect
)