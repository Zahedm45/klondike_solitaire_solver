package cdio.group21.litaire.tflite

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.RectF
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import cdio.group21.litaire.API.Prediction
import cdio.group21.litaire.API.RoboflowAPI
import cdio.group21.litaire.API.RoboflowResult
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.utils.*
import cdio.group21.litaire.utils.extensions.BitmapSlice
import cdio.group21.litaire.utils.extensions.pmap
import cdio.group21.litaire.utils.extensions.split

/**
 * This method was taken from the internet (implemented by tfl) and has been modified by us.
 */

data class DetectionConfig(val num_rows: UInt, val num_columns: UInt, val overlap_percent: Float)

object ObjectRecognition {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun processImage(context: Context, bitmap: Bitmap, config: DetectionConfig): List<DetectionResult> {
        //println("Start of processImage: ${Thread.currentThread()}")
        val bitmaps: Array2D<BitmapSlice> = bitmap.split(config.num_rows, config.num_columns, config.overlap_percent)
        val results = bitmaps.pmap2D { bitmapSlice -> RoboflowAPI.getPrediction(bitmapSlice.bitmap) }
        //@Suppress("NAME_SHADOWING") val sizes = bitmaps.map { bitmapSlice -> Size(bitmapSlice.bitmap.width.toUShort(), bitmapSlice.bitmap.height.toUShort()) }

        //val bitmap_offset = bitmaps.map2D { slice -> slice.position }
        // Merge the results
        val predictions = mergeResults(results, bitmaps)

        // Step 4: Parse the detection result and show it

        val resultToDisplay = predictions.pmap {
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

    private suspend fun mergeResults(
        results:Array2D<RoboflowResult?>,
        bitmapSlices: Array2D<BitmapSlice>
    ): List<Prediction> {

        fun offsetPrediction(offset: Point, prediction: Prediction): Prediction {
            prediction.x = prediction.x?.plus(offset.x)
            prediction.y = prediction.y?.plus(offset.y)
            return prediction
        }

        val offsetPredictions = results.mapIndexed2D { y, x, result ->
            result?.predictions?.map() { prediction ->
                offsetPrediction(bitmapSlices[y][x].position, prediction)
            } ?: emptyList()
        }

        return offsetPredictions.flatten().flatten()
    }
}
