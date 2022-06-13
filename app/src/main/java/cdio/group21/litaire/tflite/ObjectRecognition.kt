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
import cdio.group21.litaire.data.*
import cdio.group21.litaire.utils.*
import cdio.group21.litaire.utils.extensions.BitmapSlice
import cdio.group21.litaire.utils.extensions.pmap
import cdio.group21.litaire.utils.extensions.split

/**
 * This method was taken from the internet (implemented by tfl) and has been modified by us.
 * @author Zahed(s186517)
 */

data class DetectionConfig(val num_rows: UInt, val num_columns: UInt, val overlap_percent: Float)

object ObjectRecognition {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun processImage(bitmap: Bitmap, config: DetectionConfig): List<DetectionResult> {

        val bitmaps: Array2D<BitmapSlice> = bitmap.split(config.num_rows, config.num_columns, config.overlap_percent)
        val results = bitmaps.pmap2D { bitmapSlice -> RoboflowAPI.getPrediction(bitmapSlice.bitmap) }

        // Merge the results
        val predictions = mergeResults(results, bitmaps)

        // Step 4: Parse the detection result and show it

        val resultToDisplay = predictions.pmap {
            // Get the top-1 category and craft the display text
            val category = it.class_ ?: ""
            val suit = Suit.fromChar(category.last())
            val rank = Rank.fromChar(category.replace("10", "T")[0])
            val card = Card2(suit, rank)

            val width = it.width ?: 0.0F
            val height = it.height ?: 0.0F
            val xTop = (it.x ?: 0.0F)
            val yTop = (it.y ?: 0.0F)
            val xBottom = ((it.x ?: 0.0F)) + width
            val yBottom = ((it.y ?: 0.0F)) + height

            val boundingBox = RectF(xTop, yTop, xBottom, yBottom)

            DetectionResult(boundingBox, card, it.confidence ?: 0.0)
        }
        Log.i(ContentValues.TAG, "result.. $resultToDisplay")
        println("End of processImage: ${Thread.currentThread()}")
        return resultToDisplay
    }

    fun initGame(results: List<DetectionResult>): Solitaire {
        val cards = collectPositions(results)
        val sortedResult = cards.sortedBy { it.boundingBox.left }
        val solitaire = Solitaire.fromInitialCards(sortedResult.map { it.card })
        return solitaire
    }


    fun collectPositions(results: List<DetectionResult>): List<DetectionResult> {
        return results.distinctBy { it.card }
    }

    private fun mergeResults(
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
