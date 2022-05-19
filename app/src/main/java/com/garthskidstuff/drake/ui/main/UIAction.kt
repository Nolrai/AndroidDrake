package com.garthskidstuff.drake.ui.main

import com.garthskidstuff.drake.businessLogic.Diagram

sealed class UIAction {
    object Click : UIAction() {
        override fun runAction(uiState: UIState): UIState {
            return when (uiState) {
                is UIState.Idle -> UIState.DisplayDiagram(Diagram())
                is UIState.DisplayDiagram -> UIState.DisplayDiagram(uiState.diagram.addBox())
            }
        }
    }

    abstract fun runAction(uiState: UIState): UIState
}