package cdio.group21.litaire.data

data class SortedResult(
    var centerX: Float,
    var centerY: Float,
    var block: ArrayList<DetectionResult> = ArrayList()
) {

    fun deepCopy(
        centerX: Float = this.centerX,
        centerY: Float = this.centerY,
        block: ArrayList<DetectionResult> = this.block.map { it.deepCopy()} as ArrayList<DetectionResult>
    ) = SortedResult(centerX, centerY, block)

}
