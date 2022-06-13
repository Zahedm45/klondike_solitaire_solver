package cdio.group21.litaire.data

import android.graphics.RectF

data class DetectionResult(val boundingBox: RectF, val card: Card2, val confidence: Double)