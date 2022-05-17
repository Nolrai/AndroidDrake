package com.garthskidstuff.drake.businessLogic

data class BLState(val boxes: Set<Box> = HashSet()) {
    fun mkBox(): BLState {
        val length: UInt = boxes.size.toUInt()
        val box =
            Box(
                "Box: $length", length * 2u, length * 2u, 1u, 1u
            )
        val newSet = boxes.toMutableSet()
        newSet.add(box)
        return BLState(newSet)
    }
}

data class Box(
    val id: String,
    val left: UInt,
    val top: UInt,
    val width: UInt,
    val height: UInt
)