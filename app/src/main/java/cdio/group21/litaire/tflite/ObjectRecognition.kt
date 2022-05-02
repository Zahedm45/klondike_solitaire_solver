package cdio.group21.litaire.tflite

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import cdio.group21.litaire.data.DetectionResult

import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.vision.detector.ObjectDetector


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
                "yolov4_float16.tflite",
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
