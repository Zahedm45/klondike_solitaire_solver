package cdio.group21.litaire.view

import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.*
import android.net.Uri
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
import androidx.navigation.fragment.findNavController
import cdio.group21.litaire.R
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.SortedResult
import cdio.group21.litaire.databinding.FragmentLandingPageBinding
import cdio.group21.litaire.viewmodels.LandingPageViewModel
import cdio.group21.litaire.viewmodels.SharedViewModel


class FragmentLandingPage : Fragment() {

    private var _binding: FragmentLandingPageBinding? = null

    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel: LandingPageViewModel by viewModels()


    private val selectPictureLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
        it?.apply {
            binding.ivBackground.setImageURI(it)
            viewModel.setImageBitmap(uriToBitmap(it))
            sharedViewModel.setImageBitmap(uriToBitmap(it))
            updateUItoLoading()
        }


    }

    private var tempImageUri: Uri? = null
    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback{
        if(it.resultCode == RESULT_OK){
            binding.ivBackground.setImageURI(tempImageUri)
            viewModel.setImageBitmap(uriToBitmap(tempImageUri!!))
            sharedViewModel.setImageBitmap(uriToBitmap(tempImageUri!!))
            updateUItoLoading()
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLandingPageBinding.inflate(inflater, container, false)
        viewModel.getImageBitmap().observe(viewLifecycleOwner) {
            binding.ivBackground.setImageBitmap(it)
            viewModel.processImage(this.requireContext(), it)
            //findNavController().navigate(R.id.action_LandingPage_to_fragmentSuggestion)
        }

        viewModel.getDetectionList().observe(viewLifecycleOwner){
            prin(it)
            findPosition(it)
            foundationAndWaste(it)
            val imgResult = drawDetectionResult(viewModel.getImageBitmap().value!!, it)
            sharedViewModel.setImageBitmap(imgResult)
            findNavController().navigate(R.id.action_LandingPage_to_fragmentSuggestion)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    private fun findPosition(
        results: List<DetectionResult>
    ) {

        val centerXBlock: ArrayList<SortedResult> = ArrayList()
        val alignment = 2.0F

        results.forEach { crr ->

            var blockFound = false
            val box = crr.boundingBox
            var width = 0.0f
            for (it in centerXBlock) {
                //alignment = Math.abs(box.width() - it.block[0].boundingBox.width())
                 width = it.block[0].boundingBox.width()/2.0F
                val delta = Math.abs(box.centerX() - it.centerX)

                Log.i(TAG, "Width: ${width}, delta: ${delta}")

                if (delta < width) {
                    it.block.add(crr)
                    blockFound = true
                    break
                }
            }


            if (!blockFound) {
                val list : ArrayList<DetectionResult> = ArrayList()
                list.add(crr)
                val newToBeAdded = SortedResult(centerX = box.centerX(), centerY = box.centerY(), block = list)
                centerXBlock.add(newToBeAdded)
            }
        }


        printOut(centerXBlock)
        //sortAccordingToXCoordinate(centerXBlock)

    }





    private fun sortAccordingToXCoordinate(centerXBlock: ArrayList<SortedResult>) {
        centerXBlock.sortBy { it.centerX }

        centerXBlock.forEach { curr ->
            curr.block.sortBy {
                it.boundingBox.centerY()
            }
        }
        printOut(centerXBlock)

    }

    val visited: ArrayList<DetectionResult> = ArrayList()

    private fun foundationAndWaste(results: List<DetectionResult>) {
        val xSort = results.sortedBy { it.boundingBox.centerX() }
        val ySort = results.sortedBy { it.boundingBox.centerY() }


        Log.i(TAG, "Hello ->  x: ${xSort[0]}, y: ${ySort[0]}")



/*        for (i in xSort.indices){
            if (xSort[i] == ySort[i]) {
                Log.i(TAG, "helloooo ${ySort[i]}")
                break
            }
        }*/
    }



    private fun printOut(sortedResult: ArrayList<SortedResult>){

        sortedResult.forEach {
            Log.i(TAG, "Block: ${it.centerX}")
            it.block.forEach { crr ->
                Log.i(TAG, "Block: x: ${crr.boundingBox.centerX()}, y: ${crr.boundingBox.centerY()}, ${crr}")

            }
        }

    }

    private fun prin(results: List<DetectionResult>) {

        results.forEach {
            Log.i(TAG, "Block2: x: ${it.boundingBox.centerX()}, y: ${it.boundingBox.centerY()}, ${it}")
        }
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

        val val1 = true

        detectionResults.forEach {
            if (val1 == true){

                // draw bounding box
                pen.color = Color.RED
                pen.strokeWidth = 0.7F
                pen.style = Paint.Style.STROKE
                val box = it.boundingBox
                canvas.drawRect(box, pen)


                val tagSize = Rect(0, 0, 0, 0)

                // calculate the right font size
                pen.style = Paint.Style.FILL_AND_STROKE
                pen.color = Color.RED
                pen.strokeWidth = 1.5F

                pen.textSize = 25F
                pen.getTextBounds(it.text, 0, it.text.length, tagSize)
                val fontSize: Float = (pen.textSize * box.width())/ tagSize.width()

                // adjust the font size so texts are inside the bounding box
                if (fontSize < pen.textSize) pen.textSize = fontSize + 10.0F

                var margin = (box.width() - tagSize.width()) / 2.0F
                //if (margin < 0F) margin = 0F

                canvas.drawText(
                    it.text, box.left + margin,
                    box.top + tagSize.height().times(1F), pen
                )


            }

        }
        return outputBitmap
    }
    private fun uriToBitmap(uri: Uri) : Bitmap{
        val bitmap = MediaStore.Images.Media.getBitmap(this.requireActivity().contentResolver, uri)
        return bitmap
    }

    private fun updateUItoLoading(){
        binding.ivAlbumButton.visibility = View.GONE
        binding.ivCameraButton.visibility = View.GONE
        binding.tvTitle.visibility = View.GONE
        binding.tvAction.text = "Loading..."
        binding.pbThinking.visibility = View.VISIBLE
    }


}




