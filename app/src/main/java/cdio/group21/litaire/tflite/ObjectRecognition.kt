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


/**
 * This method was taken from the internet (implemented by tfl) and has been modified by us.
 */

    object ObjectRecognition {

        suspend fun processImage(context: Context, bitmap: Bitmap): List<DetectionResult> {
            println("Start of processImage: ${Thread.currentThread()}")
            val results = RoboflowAPI.getPrediction(context, bitmap)

            // Step 4: Parse the detection result and show it
            val resultToDisplay = results?.predictions?.map {
                // Get the top-1 category and craft the display text
                val category = it.class_ ?: ""
                val score = it.confidence ?: 0.0
                val text = "${category}${score.times(100).toInt()}%"
                val width = it.width ?: 0
                val height = it.height ?: 0
                val xTop = (it.x ?: 0.0F) as Float
                val yTop = (it.y ?: 0.0F) as Float
                val xBottom = (( it.x?: 0.0F) as Float) + width
                val yBottom = ((it.y ?: 0.0F) as Float) + height

                val boundingBox = RectF(xTop, yTop, xBottom ,yBottom)

                DetectionResult(boundingBox, text)
            }
            Log.i(ContentValues.TAG, "result.. $resultToDisplay")
            println("End of processImage: ${Thread.currentThread()}")
            return resultToDisplay ?: listOf()
        }
    }
