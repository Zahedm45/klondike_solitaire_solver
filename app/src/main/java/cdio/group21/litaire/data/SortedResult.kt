package cdio.group21.litaire.data

import Card

data class SortedResult(
	var centerX: Float,
	var centerY: Float,
	var block: ArrayList<DetectionResult> = ArrayList()
) {

	fun deepCopy(
		centerX: Float = this.centerX,
		centerY: Float = this.centerY,
		block: ArrayList<DetectionResult> = this.block.map { it.deepCopy() } as ArrayList<DetectionResult>
	) = SortedResult(centerX, centerY, block)

}


data class SortedResult2(
	var block: ArrayList<Card> = ArrayList()
) {

	fun deepCopy(
		block: ArrayList<Card> = this.block.map { it.deepCopy() } as ArrayList<Card>
	) = SortedResult2(block)

}