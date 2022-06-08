package cdio.group21.litaire.data

import cdio.group21.litaire.API.Prediction

data class SortedResult(
    var centerX: Float,
    var centerY: Float,
    var block: ArrayList<DetectionResult> = ArrayList()
)
