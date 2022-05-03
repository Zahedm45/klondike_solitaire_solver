package cdio.group21.litaire.data

data class SortedResult(
    var centerX: Float,
    var centerY: Float,
    var block: ArrayList<DetectionResult> = ArrayList()
)
