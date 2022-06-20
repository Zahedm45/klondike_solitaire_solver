package cdio.group21.litaire.view

import android.os.Bundle
import android.content.ContentValues
import android.content.pm.ActivityInfo
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.VectorDrawable
import android.os.Build
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.concurrent.Executors
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider

import cdio.group21.litaire.R

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import cdio.group21.litaire.databinding.FragmentCameraBinding
import cdio.group21.litaire.databinding.FragmentLandingPageBinding
import cdio.group21.litaire.viewmodels.SharedViewModel


/**
 * A camera [Fragment] subclass.
 * Used to control the view of the CameraX extension.
 */
class FragmentCamera : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var imageCapture: ImageCapture? = null

    private lateinit var cameraExecutor: ExecutorService



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this@FragmentCamera.requireActivity().setRequestedOrientation(
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this@FragmentCamera.requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.btImageCapture.setOnClickListener(){
            takePhoto()
        }

        binding.btReturn?.setOnClickListener(){
            findNavController().navigate(R.id.action_fragmentCamera_to_LandingPage)
        }
        binding.btCameraReset?.setOnClickListener(){
            updateUIclean()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startCamera()
        //binding.btImageCapture.setOnClickListener { takePhoto() }
        cameraExecutor = Executors.newSingleThreadExecutor()

        sharedViewModel.getPreviewBitmap().observe(viewLifecycleOwner){
            Log.d("Call:", "set image background")
            binding.ivPreviewFrame?.setImageBitmap(it)
            binding.btCheckmark?.visibility = View.VISIBLE
            val vectorDrawable : AnimatedVectorDrawable = binding.btCheckmark?.drawable as AnimatedVectorDrawable
            vectorDrawable.start()
            updateUIpreview()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
        this@FragmentCamera.requireActivity().setRequestedOrientation(
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        updateUIclean()

    }

    override fun onPause() {
        super.onPause()
        updateUIclean()
    }

    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        // Create time stamped name and MediaStore entry.
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(this.requireActivity().contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues)
            .build()

        // Set up image capture listener, which is triggered after photo has
        // been taken
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this.requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                override fun
                        onImageSaved(output: ImageCapture.OutputFileResults){
                    sharedViewModel.setPreviewBitmap(MediaStore.Images.Media.getBitmap(this@FragmentCamera.requireContext().contentResolver, output.savedUri))
                    //output.savedUri?.let { sharedViewModel.setURI(it) }
                    val msg = "Photo capture succeeded: ${output.savedUri}"
                    Toast.makeText(this@FragmentCamera.requireContext(), msg, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, msg)
                }
            }
        )
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this.requireContext())

        cameraProviderFuture.addListener(Runnable{
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }
            imageCapture = ImageCapture.Builder().build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture)

            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this.requireContext()))
    }

    private fun updateUIpreview(){
        binding.btImageCapture.visibility = View.GONE
        binding.ivDottedRectangle.visibility = View.GONE
        binding.btCameraReset?.visibility = View.VISIBLE
        binding.ivPreviewFrame?.visibility = View.VISIBLE
    }

    private fun updateUIclean(){
        binding.btImageCapture.visibility = View.VISIBLE
        binding.ivDottedRectangle.visibility = View.VISIBLE
        binding.btCameraReset?.visibility = View.GONE
        binding.btCheckmark?.visibility = View.GONE
        binding.ivPreviewFrame?.visibility = View.GONE
    }

    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
    }


}