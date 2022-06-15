package cdio.group21.litaire.view

import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.graphics.scale
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cdio.group21.litaire.R
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.Solitaire
import cdio.group21.litaire.data.Suit
import cdio.group21.litaire.databinding.FragmentLandingPageBinding
import cdio.group21.litaire.utils.extensions.forEachIndexed2D
import cdio.group21.litaire.viewmodels.LandingPageViewModel
import cdio.group21.litaire.viewmodels.SharedViewModel
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream


class FragmentLandingPage : Fragment() {

    private var _binding: FragmentLandingPageBinding? = null

    private val binding get() = _binding

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel: LandingPageViewModel by viewModels()
    private var tempImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLandingPageBinding.inflate(inflater, container, false)

        return binding!!.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getImageBitmap().observe(viewLifecycleOwner) {
            Log.i(TAG, "image width 2 ${it.width}")
            binding?.ivBackground?.setImageBitmap(it)
            sharedViewModel.processImage(it.copy(Bitmap.Config.RGB_565, false))
            Log.e("ImageBitmap: ", "has been observed")
            //findNavController().navigate(R.id.action_LandingPage_to_fragmentSuggestion)
        }

        sharedViewModel.getDetectionList().observe(viewLifecycleOwner) { detectionList ->
            sharedViewModel.updateGame(detectionList)
        }

        sharedViewModel.getGameState().observe(viewLifecycleOwner) { game ->
            val img = viewModel.getImageBitmap().value
            Log.e("Gamestate: ", "has been observed")
            if (img != null && game != null) {
                val imgResult = drawSolitaireGame(img, game)
                sharedViewModel.setPreviewBitmap(imgResult)
                binding?.ivBackground?.setImageBitmap(imgResult)
                findNavController().navigate(R.id.action_LandingPage_to_fragmentSuggestion)
            }

        }



        binding?.ivCameraButton?.setOnClickListener() {
            takePicture()
        }
        binding?.ivAlbumButton?.setOnClickListener() {
            selectPictureLauncher.launch("image/*")
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun takePicture() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera")
        tempImageUri = requireContext().contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )!!
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempImageUri)
        //cameraIntent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        cameraLauncher.launch(cameraIntent)
    }


    private val selectPictureLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            lifecycleScope.launch {
                it?.apply {
                    binding?.ivBackground?.setImageURI(it)
                    updateUItoLoading()
                    val bitmap = async { return@async uriToBitmap(it) }.await()
                    viewModel.setImageBitmap(bitmap)
                }

            }

        }


    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback {

            lifecycleScope.launch {
                if (it.resultCode == RESULT_OK) {
                    binding?.ivBackground?.setImageURI(tempImageUri)
                    updateUItoLoading()
                    val bitmap = async { return@async uriToBitmap(tempImageUri!!) }.await()
                    viewModel.setImageBitmap(bitmap)
                }
            }

        })


    /**
     * Draw solitaire game in a bitmap
     *
     */
    private fun drawSolitaireGame(
        bitmap: Bitmap,
        gameState: Solitaire
    ): Bitmap {
        val outputBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(outputBitmap)
        val pen = Paint()
        pen.textAlign = Paint.Align.LEFT


        val columns = gameState.tableau
        val startX = 0
        val xOffset = 125
        val startY = 0
        val yOffset = 150
        val width = 100
        val height = 125


        // Draw background
        val backgroundBox = Rect(0, 0 , canvas.width, canvas.height)
        pen.color = Color.GREEN
        pen.style = Paint.Style.FILL
        // Draw each card
        columns.forEachIndexed2D { row, column, card ->


            val xPos = startX + xOffset * row // This draws it vertically
            val yPos = startY + yOffset * column
            // Draw card with white background and black border
            pen.color = Color.WHITE
            pen.strokeWidth = 5F
            pen.style = Paint.Style.FILL
            val box = Rect(xPos, yPos, xPos + width, yPos + height)
            canvas.drawRect(box, pen)
            pen.color = Color.BLACK
            pen.style = Paint.Style.STROKE
            canvas.drawRect(box, pen)

            // Draw text
            val tagSize = Rect(0, 0, 0, 0)


            // calculate the right font size
            pen.style = Paint.Style.FILL_AND_STROKE
            pen.color =
                if (card.suit == Suit.HEART || card.suit == Suit.SPADE) Color.RED else Color.BLACK
            pen.strokeWidth = 2.5F

            pen.textSize = 40F
            val text = card.toString()
            pen.getTextBounds(text, 0, text.length, tagSize)
            val fontSize: Float = (pen.textSize)// tagSize.width()

            // adjust the font size so texts are inside the bounding box
            if (fontSize < pen.textSize) pen.textSize = fontSize + 10.0F

            val margin = (box.width() - tagSize.width()) / 2.0F

            canvas.drawText(
                text, box.left + margin,
                box.exactCenterY() - tagSize.height().times(.5F), pen
            )
        }
        return outputBitmap
    }


    /**
     *@param bitmap the bitmap representation of the image that was processed
     *@param detectionResults the list of Detectionresults
     *@return Bitmap of the ML processed image with a layered box and its detection rate
     */
    private fun drawDetectionResult(
        bitmap: Bitmap,
        detectionResults: List<DetectionResult>
    ): Bitmap {
        val outputBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(outputBitmap)
        val pen = Paint()
        pen.textAlign = Paint.Align.LEFT

        val detectionResults = detectionResults.distinctBy { it.card.toString() }


        detectionResults.forEach {
            // draw bounding box
            pen.color = Color.RED
            pen.strokeWidth = 0.7F
            pen.style = Paint.Style.STROKE
            val box = it.boundingBox
            canvas.drawRect(box, pen)


            val tagSize = Rect(0, 0, 0, 0)

            // calculate the right font size
            pen.style = Paint.Style.FILL_AND_STROKE
            pen.color =
                if (it.card.suit == Suit.DIAMOND || it.card.suit == Suit.HEART) Color.RED else Color.BLACK
            pen.strokeWidth = 2.5F

            pen.textSize = 60F
            val text = it.card.toString() + " " + (it.confidence * 100).toString() + "%"
            pen.getTextBounds(text, 0, text.length, tagSize)
            val fontSize: Float = (pen.textSize)// tagSize.width()

            // adjust the font size so texts are inside the bounding box
            if (fontSize < pen.textSize) pen.textSize = fontSize + 10.0F

            val margin = (box.width() - tagSize.width()) / 2.0F
            //if (margin < 0F) margin = 0F

            canvas.drawText(
                text, box.left + margin,
                box.top + tagSize.height().times(1F), pen
            )

        }
        return outputBitmap
    }

    private suspend fun uriToBitmap(uri: Uri): Bitmap {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(requireActivity().contentResolver, uri)
            val bitmap = ImageDecoder.decodeBitmap(source)

            val desiredWidth = 2048
            val scalingFactor = desiredWidth.toDouble() / bitmap.width.toDouble()
            val scaledBitmap = Bitmap.createScaledBitmap(
                bitmap,
                (bitmap.width * scalingFactor).toInt(),
                (bitmap.height * scalingFactor).toInt(), true
            )

            val baos = ByteArrayOutputStream()
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            val data = baos.toByteArray()

            return BitmapFactory.decodeByteArray(data, 0, data.size)
        } else {
            TODO("VERSION.SDK_INT < P")
        }
    }

    private inline fun updateUItoLoading() {
        binding?.ivAlbumButton?.visibility = View.GONE
        binding?.ivCameraButton?.visibility = View.GONE
        binding?.tvTitle?.visibility = View.GONE
        binding?.tvAction?.text = "Loading..."
        binding?.pbThinking?.visibility = View.VISIBLE
    }


}




