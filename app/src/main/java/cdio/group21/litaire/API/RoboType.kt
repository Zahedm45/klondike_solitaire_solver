package cdio.group21.litaire.API

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class RoboflowResult(
 var predictions: List<Prediction>? = null
)

@JsonClass(generateAdapter = true)
data class ImageBody(
    var image: String
)

@JsonClass(generateAdapter = true)
class Prediction {
    var x: Int? = null

    var y: Int? = null

    var width: Int? = null

    var height: Int? = null

    @com.squareup.moshi.Json(name = "class")
    var class_: String? = null

    var confidence: Double? = null
}