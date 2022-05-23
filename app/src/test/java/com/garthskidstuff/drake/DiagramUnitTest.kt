package com.garthskidstuff.drake

import com.garthskidstuff.drake.businessLogic.Box
import com.garthskidstuff.drake.businessLogic.Diagram
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test


class DiagramTest {

    private val exampleJSON: String =
        "{\n" +
                "   \"name\": \"NewDiagram\",\n" +
                "   \"boxes\": [\n" +
                "       {\n" +
                "           \"id\": \"Box: 0\",\n" +
                "          \"left\": 0,\n" +
                "           \"top\": 0,\n" +
                "           \"right\": 1,\n" +
                "          \"bottom\": 1\n" +
                "       },\n" +
                "       {\n" +
                "           \"id\": \"Box: 1\",\n" +
                "           \"left\": 1,\n" +
                "           \"top\": 1,\n" +
                "           \"right\": 2,\n" +
                "           \"bottom\": 2\n" +
                "       },\n" +
                "       {\n" +
                "           \"id\": \"Box: 2\",\n" +
                "           \"left\": 2,\n" +
                "          \"top\": 2,\n" +
                "           \"right\": 3,\n" +
                "           \"bottom\": 3\n" +
                "      },\n" +
                "      {\n" +
                "          \"id\": \"Box: 3\",\n" +
                "           \"left\": 3,\n" +
                "           \"top\": 3,\n" +
                "          \"right\": 4,\n" +
                "           \"bottom\": 4\n" +
                "      }\n" +
                "   ]\n" +
                "}"

    @Test
    fun constructorTest() {
        val jsonObject = JSONObject(exampleJSON)
        assertEquals(jsonObject.toString(2), Diagram(jsonObject).toJSON().toString(2))
    }

    @Test
    fun addBoxTest() {

    }

    @Test
    fun toJSONTest() {

    }

}

class BoxTest {

    private val exampleJSON: String =
        "{\n" +
                "  \"id\": \"Box: 0\",\n" +
                "  \"left\": 0,\n" +
                "  \"top\": 0,\n" +
                "  \"right\": 1,\n" +
                "  \"bottom\": 1\n" +
                "},\n"

    @Test
    fun constructorTest() {
        val jsonObject = JSONObject(exampleJSON)
        val throughBox = Box(JSONObject(exampleJSON)).toJSON()
        assertEquals(jsonObject.toString(2), throughBox.toString(2))
    }

    @Test
    fun toJSONTest() {

    }
}