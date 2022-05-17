package com.garthskidstuff.drake.ui.main

import com.garthskidstuff.drake.model.main.MainModel
import com.garthskidstuff.drake.ui.base.IEffect
import com.garthskidstuff.drake.ui.base.IEvent
import com.garthskidstuff.drake.ui.base.IState

class MainContract {

    val initialModel = Model(MainModel.Idle)

    // Events that the user performed
    sealed class Event : IEvent {
        object OnScreenTapped : Event()
        object OnShowToastClicked : Event()
    }

    // Model to be presented
    data class Model (
        val model : MainModel
    ) : IState

    sealed class Effect : IEffect {
        object ShowToast : Effect()
    }
}