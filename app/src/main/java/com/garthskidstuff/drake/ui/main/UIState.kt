package com.garthskidstuff.drake.ui.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.RectF
import com.garthskidstuff.drake.R.color.*
import com.garthskidstuff.drake.businessLogic.Diagram

sealed class UIState {
    abstract fun draw(canvas: Canvas?, context: Context)

    object Idle : UIState() {
        override fun draw(canvas: Canvas?, context: Context) {
            canvas?.drawColor(context.getColor(teal_700))
        }
    }

    data class DisplayDiagram(val diagram: Diagram) : UIState() {
        override fun draw(canvas: Canvas?, context: Context) {
            canvas?.drawColor(context.getColor(purple_700))
            diagram.boxes.forEach {
                val rect =
                    RectF(
                        it.rect.left.toFloat() * 100,
                        it.rect.top.toFloat() * 100,
                        it.rect.right.toFloat() * 100,
                        it.rect.bottom.toFloat() * 100
                    )
                val paint = Paint()
                paint.color = context.getColor(black)
                paint.style = Style.STROKE
                canvas?.drawRoundRect(rect, 20.0f, 20.0f, Paint())
            }
        }
    }
}