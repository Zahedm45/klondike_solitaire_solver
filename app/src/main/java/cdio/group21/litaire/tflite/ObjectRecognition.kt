package cdio.group21.litaire.tflite

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import cdio.group21.litaire.data.DetectionResult

import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.vision.detector.ObjectDetector
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.math.roundToInt


/**
 * This method was taken from the internet (implemented by tfl) and has been modified by us.
 */

    object ObjectRecognition {

        fun processImage(context: Context, bitmap: Bitmap): List<DetectionResult> {
            println("Start of processImage: ${Thread.currentThread()}")
            val confidLevel = 0.3f

            // Step 1: Create TFLite's TensorImage object
            val image = TensorImage.fromBitmap(bitmap)

            // Step 2: Initialize the detector object
            val options = ObjectDetector.ObjectDetectorOptions.builder()
                //.setMaxResults()
                .setScoreThreshold(confidLevel)
                .build()
            val detector = ObjectDetector.createFromFileAndOptions(
                context,
                "model_metadata.tflite",
                options
            )


            // Step 3: Feed given image to the detector
            val results = detector.detect(image)

            // Step 4: Parse the detection result and show it
            val resultToDisplay = results.map {
                // Get the top-1 category and craft the display text
                val category = it.categories.first()
                val text = "${category.label}${category.score.times(100).toInt()}%"

                // Create a data object to display the detection result

                DetectionResult(it.boundingBox, text)
            }
            Log.i(ContentValues.TAG, "result.. $resultToDisplay")
            println("End of processImage: ${Thread.currentThread()}")
            return resultToDisplay
        }
}

fun Bitmap.split(num_rows: Int, num_columns: Int, overlap_percent: Double = 0.1): Array<Array<Bitmap>> {
    val height = (this.height / num_rows)
    val width = (this.width / num_columns)
    val overlap = Pair(height * overlap_percent, width * overlap_percent)
    val nullBitmap: Bitmap? = null

    val bitmaps = Pair(num_rows, num_columns).createArray(nullBitmap)

    for (i in 0 until num_rows) {
        for (j in 0 until num_columns) {
            val x = max((j * width - overlap.second).roundToInt(), 0)
            val y = max((i * height - overlap.first).roundToInt(), 0)
            //val w = min(((j + 1) * width + overlap.second).roundToInt(), this.width) - x
            //val h = min(((i + 1) * height + overlap.first).roundToInt(), this.height) - y
            val x_end = min((j * width + width + overlap.second).roundToInt(), this.width)
            val y_end = min((i * height + height + overlap.first).roundToInt(), this.height)
            val w = x_end - x
            val h = y_end - y
            val bitmapSlice: Bitmap = Bitmap.createBitmap(this, x, y, w, h)
            bitmaps[i][j] = bitmapSlice
        }
    }
    @Suppress("UNCHECKED_CAST") // We know, that none of the bitmaps are null (the type system guarantees this)
    return bitmaps as Array<Array<Bitmap>>
}

inline fun<reified T> Pair<Int, Int>.createArray(initialValue:T) = Array(this.first){ Array(this.second){initialValue}}