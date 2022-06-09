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

    object ObjectRecognition {

    suspend fun processImage(context: Context, bitmap: Bitmap): List<DetectionResult> {
        println("Start of processImage: ${Thread.currentThread()}")
        val num_rows = 5
        val num_cols = 4
        val overlap_percent = 0.2
        val bitmaps: Array<Array<Bitmap>> = bitmap.split(num_rows, num_cols, overlap_percent)
        val results = bitmaps.map { bitmap -> RoboflowAPI.getPrediction(context, bitmap) }
        val sizes = bitmaps.map { bitmap -> Pair(bitmap.width, bitmap.height) }
        // Merge the results
        val mergedResults = mergeResults(results, sizes)


        val result = RoboflowAPI.getPrediction(context, bitmap)

        // Step 4: Parse the detection result and show it

        val resultToDisplay = result?.predictions?.map {
            // Get the top-1 category and craft the display text
            val category = it.class_ ?: ""
            val score = it.confidence ?: 0.0
            val text = "${category}${score.times(100).toInt()}%"
            val width = it.width ?: 0
            val height = it.height ?: 0
            val xTop = (it.x ?: 0.0F) as Float
            val yTop = (it.y ?: 0.0F) as Float
            val xBottom = ((it.x ?: 0.0F) as Float) + width
            val yBottom = ((it.y ?: 0.0F) as Float) + height

            val boundingBox = RectF(xTop, yTop, xBottom, yBottom)

            DetectionResult(boundingBox, text)
        }
        Log.i(ContentValues.TAG, "result.. $resultToDisplay")
        println("End of processImage: ${Thread.currentThread()}")
        return resultToDisplay ?: listOf()
    }

    private fun mergeResults(
        results: Array<Array<RoboflowResult?>>,
        bitmap_sizes: Array<Array<Pair<Int, Int>>>
    ): RoboflowResult {
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

        return RoboflowResult(offsetPredictions.flatten().flatten())
    }
}
