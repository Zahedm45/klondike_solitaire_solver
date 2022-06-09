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
import cdio.group21.litaire.utils.*
import cdio.group21.litaire.utils.map


/**
 * This method was taken from the internet (implemented by tfl) and has been modified by us.
 */

data class DetectionConfig(val num_rows: UShort, val num_columns: UShort, val overlap_percent: Float)

object ObjectRecognition {

    suspend fun processImage(context: Context, bitmap: Bitmap, config: DetectionConfig): List<DetectionResult> {
        println("Start of processImage: ${Thread.currentThread()}")
        val bitmaps: Array2D<BitmapSlice> = bitmap.split(config.num_rows, config.num_columns, config.overlap_percent)
        val results = bitmaps.map { bitmapSlice -> RoboflowAPI.getPrediction(context, bitmapSlice.bitmap) }
        //@Suppress("NAME_SHADOWING") val sizes = bitmaps.map { bitmapSlice -> Size(bitmapSlice.bitmap.width.toUShort(), bitmapSlice.bitmap.height.toUShort()) }

        val bitmap_offset = bitmaps.map { slice -> slice.position }
        // Merge the results
        val predictions = mergeResults(results, bitmap_offset)

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
        results:Array2D<RoboflowResult?>,
        bitmap_offset: Array2D<Point>
    ): List<Prediction> {


        val offsetPredictions = results.mapIndexed { y, x, result ->
            result?.predictions?.map { prediction ->
                prediction.x =
                    prediction.x?.plus(bitmap_offset[y][x].x)
                prediction.y =
                    prediction.y?.plus(bitmap_offset[y][x].y)
                prediction
            } ?: listOf()
        }

        return offsetPredictions.flatten().flatten()
    }
}
