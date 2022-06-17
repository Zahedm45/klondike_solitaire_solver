package cdio.group21.litaire.data

import Card

data class SortedResult(
	var centerX: Float,
	var centerY: Float,
	var block: MutableList<DetectionResult> = mutableListOf()
) {

	fun deepCopy(
		centerX: Float = this.centerX,
		centerY: Float = this.centerY,
		block: MutableList<DetectionResult> = this.block.map { it.deepCopy() } as MutableList<DetectionResult>
	) = SortedResult(centerX, centerY, block)

}


data class SortedResult2(
	var block: MutableList<Card> = mutableListOf()
) {

	fun deepCopy(
		block: MutableList<Card> = this.block.map { it.deepCopy() } as MutableList<Card>
	) = SortedResult2(block)

}