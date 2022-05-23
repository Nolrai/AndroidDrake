package com.garthskidstuff.drake.ui.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.RectF
import android.util.Log
import com.garthskidstuff.drake.R.color.*
import com.garthskidstuff.drake.businessLogic.Diagram
import com.garthskidstuff.drake.businessLogic.ToJSON
import org.json.JSONObject
import java.security.InvalidKeyException

sealed class UIState : ToJSON {

    abstract val tag: String

    companion object Factory {
        fun fromJSON(jsonObject: JSONObject): UIState {
            return when (val key = jsonObject.getString("tag")) {
                Idle.tag -> Idle
                DisplayDiagram().tag -> DisplayDiagram(jsonObject)
                else -> throw InvalidKeyException("$key is not a valid UIState tag")
            }
        }
    }

    abstract fun draw(canvas: Canvas?, context: Context)

    fun encodeToByteArray(): ByteArray {
        val string = toJSON().toString(4)
        Log.d("UIState", "encodeToByteArray: string = $string")
        return string.encodeToByteArray()
    }

    override fun toJSON(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("tag", this.tag)
        addBodyToJSON(jsonObject)
        return jsonObject
    }

    abstract fun addBodyToJSON(jsonObject: JSONObject)

    object Idle : UIState() {
        override fun draw(canvas: Canvas?, context: Context) {
            canvas?.drawColor(context.getColor(teal_700))
        }

        override fun addBodyToJSON(jsonObject: JSONObject) {
            //do nothing
        }

        override val tag: String = "Idle"
    }

    data class DisplayDiagram(val diagram: Diagram = Diagram()) : UIState() {

        constructor(jsonObject: JSONObject) : this(Diagram(jsonObject.getJSONObject("diagram")))

        override fun draw(canvas: Canvas?, context: Context) {
            canvas?.drawColor(context.getColor(purple_700))
            diagram.boxes.forEach {
                val rect =
                    RectF(
                        it.left.toFloat() * 100,
                        it.top.toFloat() * 100,
                        it.right.toFloat() * 100,
                        it.bottom.toFloat() * 100
                    )
                val paint = Paint()
                paint.color = context.getColor(black)
                paint.style = Style.STROKE
                canvas?.drawRoundRect(rect, 20.0f, 20.0f, Paint())
            }
        }

        override val tag: String = "DisplayDiagram"

        override fun addBodyToJSON(jsonObject: JSONObject) {
            jsonObject.put("diagram", diagram.toJSON())
        }
    }
}