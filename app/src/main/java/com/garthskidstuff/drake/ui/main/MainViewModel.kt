package com.garthskidstuff.drake.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    fun pushAction(uiAction: UIAction) {
        _uiState.update { uiAction.runAction(it) }
    }

    private val _uiState: MutableStateFlow<UIState> = MutableStateFlow(UIState.Idle)
    val uiState = _uiState.asStateFlow()

}