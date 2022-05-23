package com.garthskidstuff.drake.ui.main

import android.content.Context
import android.util.Log
import com.garthskidstuff.drake.businessLogic.Diagram
import org.json.JSONObject

sealed class UIAction {
    val tag: String = "UIAction"

    object Click : UIAction() {
        override fun runAction(viewModel: MainViewModel, context: Context): UIState {
            return when (val state = viewModel.uiState.value) {
                is UIState.Idle -> UIState.DisplayDiagram(Diagram())
                is UIState.DisplayDiagram -> UIState.DisplayDiagram(state.diagram.addBox())
            }
        }
    }

    class Load(private val name: String? = null) : UIAction() {
        override fun runAction(viewModel: MainViewModel, context: Context): UIState {
            val seq = context.filesDir.walkTopDown()
            return try {
                val file = seq.filter { it.name.equals(name ?: "state.txt") }.firstOrNull()
                if (file != null) {
                    val string = file.readText()
                    Log.i(tag, "runAction: string = $string")
                    UIState.fromJSON(JSONObject(string))
                } else {
                    UIState.Idle
                }
            } catch (ex: Exception) {
                Log.e(tag, "Load::runAction: ", ex)
                viewModel.tryPushEvent(UIEvent.GenericError(ex))
                UIState.Idle
            }
        }
    }

    abstract fun runAction(viewModel: MainViewModel, context: Context): UIState
}