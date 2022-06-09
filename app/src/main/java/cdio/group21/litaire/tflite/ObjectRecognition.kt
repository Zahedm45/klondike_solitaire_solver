package cdio.group21.litaire.tflite

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.RectF
import android.util.Log
import cdio.group21.litaire.API.Prediction
import cdio.group21.litaire.API.RoboflowAPI
import cdio.group21.litaire.API.RoboflowResult
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.utils.map
import cdio.group21.litaire.utils.mapIndexed
import cdio.group21.litaire.utils.split


/**
 * This method was taken from the internet (implemented by tfl) and has been modified by us.
 */

data class DetectionConfig(val num_rows: UShort, val num_columns: UShort, val overlap_percent: Float)

object ObjectRecognition {

    suspend fun processImage(context: Context, bitmap: Bitmap, config: DetectionConfig): List<DetectionResult> {
        println("Start of processImage: ${Thread.currentThread()}")
        val bitmaps: Array<Array<Bitmap>> = bitmap.split(config.num_rows, config.num_columns, config.overlap_percent)
        @Suppress("NAME_SHADOWING") val results = bitmaps.map { bitmap -> RoboflowAPI.getPrediction(context, bitmap) }
        @Suppress("NAME_SHADOWING") val sizes = bitmaps.map { bitmap -> Pair(bitmap.width, bitmap.height) }
        // Merge the results
        val predictions = mergeResults(results, sizes)

        // Step 4: Parse the detection result and show it

        val resultToDisplay = predictions.map {
            // Get the top-1 category and craft the display text
            val category = it.class_ ?: ""
            val score = it.confidence ?: 0.0
            val text = "${category}${score.times(100).toInt()}%"
            val width = it.width ?: 0.0F
            val height = it.height ?: 0.0F
            val xTop = (it.x ?: 0.0F)
            val yTop = (it.y ?: 0.0F)
            val xBottom = ((it.x ?: 0.0F)) + width
            val yBottom = ((it.y ?: 0.0F)) + height

            val boundingBox = RectF(xTop, yTop, xBottom, yBottom)

            DetectionResult(boundingBox, text)
        }
        Log.i(ContentValues.TAG, "result.. $resultToDisplay")
        println("End of processImage: ${Thread.currentThread()}")
        return resultToDisplay
    }

    private fun mergeResults(
        results: Array<Array<RoboflowResult?>>,
        bitmap_sizes: Array<Array<Pair<Int, Int>>>
    ): List<Prediction> {
        val bitmap_offset = bitmap_sizes.mapIndexed { indicies, size ->
            var x_offset = 0
            var y_offset = 0
            for (i in 0 until indicies.first) {
                x_offset += bitmap_sizes[i][indicies.second].first
            }
            for (i in 0 until indicies.second) {
                y_offset += bitmap_sizes[indicies.first][i].second
            }
            Pair(x_offset, y_offset)
        }

        val offsetPredictions = results.mapIndexed { indicies, result ->
            result?.predictions?.map { prediction ->
                prediction.x =
                    prediction.x?.plus(bitmap_offset[indicies.first][indicies.second].first)
                prediction.y =
                    prediction.y?.plus(bitmap_offset[indicies.first][indicies.second].second)
                prediction
            } ?: listOf()
        }

        return offsetPredictions.flatten().flatten()
    }
}
