package cdio.group21.litaire.view

import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import cdio.group21.litaire.databinding.FragmentLandingPageBinding
import cdio.group21.litaire.viewmodels.SharedViewModel
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.vision.detector.ObjectDetector
import java.io.File


class FragmentLandingPage : Fragment() {

    private var _binding: FragmentLandingPageBinding? = null

    private val binding get() = _binding!!

    private val viewModel: SharedViewModel by activityViewModels()

    //TODO used to select pictures from album - may need to initialize inside onViewCreated()
    private val selectPictureLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
        val bitmap = MediaStore.Images.Media.getBitmap(this.activity?.contentResolver, it)
        runObjectDetection(bitmap)
    }

    private var tempImageUri: Uri? = null
    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback{
        if(it.resultCode == RESULT_OK){
            binding.ivBackground.setImageURI(tempImageUri)
        }
    })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLandingPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO make camera landscape mode?
        binding.ivCameraButton.setOnClickListener() {
            takePicture()

        }

        binding.ivAlbumButton.setOnClickListener(){
            selectPictureLauncher.launch("image/*")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun takePicture(){
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera")
        tempImageUri = requireContext().contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )!!
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempImageUri)
        cameraLauncher.launch(cameraIntent)
    }

    //TODO: Should be separated in its own companion object of class or class - go through viewmodel
    private fun runObjectDetection(bitmap: Bitmap) {
        val confidLevel = 0.3f

        // Step 1: Create TFLite's TensorImage object
        val image = TensorImage.fromBitmap(bitmap)

        // Step 2: Initialize the detector object
        val options = ObjectDetector.ObjectDetectorOptions.builder()
            .setMaxResults(5)
            .setScoreThreshold(confidLevel)
            .build()
        val detector = ObjectDetector.createFromFileAndOptions(
            this.context,
            "spade8_2.tflite",
            options
        )



        // Step 3: Feed given image to the detector
        val results = detector.detect(image)

        // Step 4: Parse the detection result and show it
        val resultToDisplay = results.map {
            // Get the top-1 category and craft the display text
            val category = it.categories.first()
            val text = "${category.label}, ${category.score.times(100).toInt()}%"

            // Create a data object to display the detection result

            DetectionResult(it.boundingBox, text)
        }
        Log.i(TAG, "result.. $resultToDisplay" )
        // Draw the detection result on the bitmap and show it.
        val imgWithResult = drawDetectionResult(bitmap, resultToDisplay)

            binding.ivBackground.setImageBitmap(imgWithResult)

    }

    private fun drawDetectionResult(
        bitmap: Bitmap,
        detectionResults: List<DetectionResult>
    ): Bitmap {
        val outputBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(outputBitmap)
        val pen = Paint()
        pen.textAlign = Paint.Align.LEFT

        detectionResults.forEach {
            // draw bounding box
            pen.color = Color.RED
            pen.strokeWidth = 8F
            pen.style = Paint.Style.STROKE
            val box = it.boundingBox
            canvas.drawRect(box, pen)


            val tagSize = Rect(0, 0, 0, 0)

            // calculate the right font size
            pen.style = Paint.Style.FILL_AND_STROKE
            pen.color = Color.BLUE
            pen.strokeWidth = 2F

            pen.textSize = 96F
            pen.getTextBounds(it.text, 0, it.text.length, tagSize)
            val fontSize: Float = pen.textSize * box.width() / tagSize.width()

            // adjust the font size so texts are inside the bounding box
            if (fontSize < pen.textSize) pen.textSize = fontSize

            var margin = (box.width() - tagSize.width()) / 2.0F
            if (margin < 0F) margin = 0F
            canvas.drawText(
                it.text, box.left + margin,
                box.top + tagSize.height().times(1F), pen
            )
        }
        return outputBitmap
    }

}

data class DetectionResult(val boundingBox: RectF, val text: String)
