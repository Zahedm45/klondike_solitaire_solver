package cdio.group21.litaire.view

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.ActivityInfo
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cdio.group21.litaire.R
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.databinding.FragmentLandingPageBinding
import cdio.group21.litaire.viewmodels.LandingPageViewModel
import cdio.group21.litaire.viewmodels.SharedViewModel
import id.zelory.compressor.Compressor
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


class FragmentLandingPage : Fragment() {

    private var _binding: FragmentLandingPageBinding? = null

    private val binding get() = _binding!!

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getImageBitmap().observe(viewLifecycleOwner) {
            Log.i(TAG, "image width 2 ${it.width}")
            binding.ivBackground.setImageBitmap(it)
            viewModel.processImage(this.requireContext(), it.copy(Bitmap.Config.RGB_565, false))
            //findNavController().navigate(R.id.action_LandingPage_to_fragmentSuggestion)
        }

        viewModel.getDetectionList().observe(viewLifecycleOwner){

            val img = viewModel.getImageBitmap().value

            if (img != null) {

                var imgResult = drawDetectionResult(img, it)


                if (it.isNotEmpty()) {
                    viewModel.detectFoundationAndWaste(it, viewModel.getImageBitmap().value!!)
                    viewModel.detectTableaus(viewModel.resultAfterFoundationWaste)
                    viewModel.setNewResults()


                    if (viewModel.waste != null) {
                        viewModel.printWaste(viewModel.waste!!)
                    }

                    viewModel.printFoundation(viewModel.foundation)
                    viewModel.printTableaus(viewModel.tableaus)

                    imgResult = drawDetectionResult(img, viewModel.newResult)
                }


                sharedViewModel.setImageBitmap(imgResult)
                findNavController().navigate(R.id.action_LandingPage_to_fragmentSuggestion)

            }

        }



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





    private val selectPictureLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
        lifecycleScope.launch {
            it?.apply {
                binding.ivBackground.setImageURI(it)
                viewModel.setImageBitmap(uriToBitmap(it))
                sharedViewModel.setImageBitmap(uriToBitmap(it))

                updateUItoLoading()
            }

        }

    }


    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback{

        lifecycleScope.launch{
            if(it.resultCode == RESULT_OK){
                binding.ivBackground.setImageURI(tempImageUri)
                viewModel.setImageBitmap(uriToBitmap(tempImageUri!!))
                sharedViewModel.setImageBitmap(uriToBitmap(tempImageUri!!))
                updateUItoLoading()
            }
        }

    })






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
            pen.color = if(it.text.contains("S") || it.text.contains("H")) Color.RED else Color.BLACK
            pen.strokeWidth = 4F

            pen.textSize = 50F
            pen.getTextBounds(it.text, 0, it.text.length, tagSize)
            val fontSize: Float = (pen.textSize * box.width())/ tagSize.width()

            // adjust the font size so texts are inside the bounding box
            if (fontSize < pen.textSize) pen.textSize = fontSize + 10.0F

            val margin = (box.width() - tagSize.width()) / 2.0F
            //if (margin < 0F) margin = 0F

            canvas.drawText(
                it.text, box.left + margin,
                box.top + tagSize.height().times(1F), pen
            )

        }
        return outputBitmap
    }
    private suspend fun uriToBitmap(uri: Uri): Bitmap {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

            val source =  ImageDecoder.createSource(requireActivity().contentResolver, uri)
            val bitmap  = ImageDecoder.decodeBitmap(source)
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            val data = baos.toByteArray()

            val file = File(requireContext().cacheDir, "temp.jpg")
            file.createNewFile()

            val fos = FileOutputStream(file)
            fos.write(data)
            fos.flush()
            fos.close()
            Log.i("Image Compression", file.readBytes().size.toString() + " bytes")
            val comp =  Compressor.compress(requireContext(), file)
            val compBitmap = BitmapFactory.decodeFile(comp.path)
            Log.i("Image Compression", comp.readBytes().size.toString() + " bytes")
            file.delete()

            return compBitmap

        } else {
            TODO("VERSION.SDK_INT < P")
        }
    }

    private fun updateUItoLoading(){
        binding.ivAlbumButton.visibility = View.GONE
        binding.ivCameraButton.visibility = View.GONE
        binding.tvTitle.visibility = View.GONE
        binding.tvAction.text = "Loading..."
        binding.pbThinking.visibility = View.VISIBLE
    }


}




