package com.garthskidstuff.drake.ui.main

import android.content.Context
import android.widget.Toast

sealed class UIEvent {

    data class GenericError(val error: Throwable) : UIEvent() {
        override fun show(context: Context) {
            val toast: Toast = Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG)
            toast.show()
        }
    }

    abstract fun show(context: Context)

}