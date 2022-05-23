package com.garthskidstuff.drake.businessLogic

import org.json.JSONArray
import org.json.JSONObject

interface ToJSON {
    fun toJSON(): JSONObject
}

data class Diagram(val name: String = "NewDiagram", val boxes: Set<Box> = HashSet()) : ToJSON {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString("name"),
        jsonObject.getJSONArray("boxes").toSet()
    )

    fun addBox(): Diagram {
        val length: Int = boxes.size
        val box =
            Box(
                "Box: $length", length, length, length + 1, length + 1
            )
        val newSet = boxes.toMutableSet()
        newSet.add(box)
        return Diagram(name, newSet)
    }

    override fun toJSON(): JSONObject {
        val top = JSONObject()
        top.put("name", name)
        top.put("boxes", JSONArray())
        for (box in boxes) {
            top.accumulate("boxes", box.toJSON())
        }
        return top
    }
}

private fun JSONArray.toSet(): Set<Box> {
    var set: Set<Box> = HashSet()
    for (i in 0 until length()) {
        set = set.plus(Box(getJSONObject(i)))
    }
    return set
}

data class Box(
    val id: String,
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
) : ToJSON {

    constructor(jsonObject: JSONObject) :
            this(
                jsonObject.getString("id"),
                jsonObject.getInt("left"),
                jsonObject.getInt("top"),
                jsonObject.getInt("right"),
                jsonObject.getInt("bottom")
            )

    override fun toJSON(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("left", left)
        jsonObject.put("top", top)
        jsonObject.put("right", right)
        jsonObject.put("bottom", bottom)
        return jsonObject
    }
}