package com.garthskidstuff.drake.model.main

import com.garthskidstuff.drake.ui.base.IState

data class Box(val name : String, val top : UByte, val height : UByte, val left : UByte, val width : UByte)

sealed class MainModel : IState {
    object Idle : MainModel()
    object Loading : MainModel()
    data class Displaying(val diagram : Set<Box>)
}
