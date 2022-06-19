package cdio.group21.litaire.API

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoboflowResult(
	var predictions: List<Prediction>? = null
)

@JsonClass(generateAdapter = true)
data class ImageBody(
	var image: String
)

@JsonClass(generateAdapter = true)
class Prediction(
	var x: Float,
	var y: Float,
	var width: Float,
	var height: Float,
	@com.squareup.moshi.Json(name = "class") var class_: String,
	var confidence: Double
) {
	/*
	var x: Float? = null

	var y: Float? = null

	var width: Float? = null

	var height: Float? = null

	@com.squareup.moshi.Json(name = "class")
	var class_: String? = null

	var confidence: Double? = null
	 */
}