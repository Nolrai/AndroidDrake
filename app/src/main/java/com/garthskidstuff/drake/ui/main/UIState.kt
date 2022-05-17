package com.garthskidstuff.drake.ui.main

import com.garthskidstuff.drake.businessLogic.BLState
import com.garthskidstuff.drake.businessLogic.Box

sealed class UIState {
    object Idle : UIState()
    data class NotIdle(val boxes: Set<Box>) : UIState()
}

fun mkNotIdle(s: BLState): UIState {
    return UIState.NotIdle(s.boxes)
}