package cdio.group21.litaire.data

import android.graphics.RectF

class DetectionResult(
    val boundingBox: RectF, val percentage: Int, var card: Card
    ) {

    fun toText() : String {
        return card.value.toString() + card.suit + percentage
    }
}