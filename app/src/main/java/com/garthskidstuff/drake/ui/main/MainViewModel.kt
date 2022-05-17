package com.garthskidstuff.drake.ui.main

import com.garthskidstuff.drake.ui.base.BaseViewModel

class MainViewModel : BaseViewModel<MainContract.Event, MainContract.Model, MainContract.Effect>() {

    override fun createInitialState(): MainContract.Model {
        val contract = MainContract()
        return contract.initialModel
    }

    /**
     * Handle each event
     */
    override fun handleEvent(event: MainContract.Event) {
        if (event is MainContract.Event.OnScreenTapped) {
            //TODO
        }
    }
}