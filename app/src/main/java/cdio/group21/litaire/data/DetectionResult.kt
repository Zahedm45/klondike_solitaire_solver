package cdio.group21.litaire.data

import android.graphics.RectF

data class DetectionResult(
    val boundingBox: RectF, val percentage: Int, var card: Card
    ) {

    fun toText() : String {
        return card.value.toString() + card.suit +"," + percentage
    }

    fun deepCopy(
        boundingBox: RectF = this.boundingBox,
        percentage: Int = this.percentage,
        card: Card = this.card
    ) = DetectionResult(boundingBox, percentage, card)
}






data class DetectionResult2(
    var card: Card
) {

    fun deepCopy(
        card: Card = this.card
    ) = DetectionResult2(card)
}