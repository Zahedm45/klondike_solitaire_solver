package cdio.group21.litaire.data

import android.graphics.RectF
import cdio.group21.litaire.API.Prediction

data class DetectionResult(val boundingBox: RectF, val card: Card2, val confidence: Double) {
	companion object {
		fun fromPrediction(prediction: Prediction): DetectionResult {
			val category = prediction.class_ ?: ""
			val suit = Suit.fromChar(category.last())
			val rank = Rank.fromChar(category.replace("10", "T")[0])
			val card = Card2(suit, rank)

			val width = prediction.width ?: 0.0F
			val height = prediction.height ?: 0.0F
			val xTop = prediction.x ?: 0.0F
			val yTop = prediction.y ?: 0.0F
			val xBottom = (prediction.x ?: 0.0F) + width
			val yBottom = (prediction.y ?: 0.0F) + height

			val boundingBox = RectF(xTop, yTop, xBottom, yBottom)

			return DetectionResult(boundingBox, card, prediction.confidence ?: 0.0)
		}
	}
}