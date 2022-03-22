package cdio.group21.litaire.view

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import cdio.group21.litaire.databinding.FragmentLandingPageBinding
import com.swein.easypermissionmanager.EasyPermissionManager
import java.io.File



class FragmentLandingPage : Fragment() {

    private var _binding: FragmentLandingPageBinding? = null

    private val binding get() = _binding!!

    //TODO used to select pictures from album - may need to initialize inside onViewCreated()
    private val selectPictureLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
        binding.ivInfo1.setImageURI(it)
    }

    private var tempImageUri: Uri? = null
    private var tempImageFilePath = ""
    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()){ success ->
        if (success){
            binding.tvTitle.visibility = View.GONE
            binding.ivBackground.setImageURI(tempImageUri)
        }

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
        promptPermissions()

        binding.ivCameraButton.setOnClickListener() {

            tempImageUri = FileProvider.getUriForFile(this.requireContext(),
                "cdio.group21.litaire.provider", createImageFile().also {
                    tempImageFilePath = it.absolutePath
                })
            cameraLauncher.launch(tempImageUri)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createImageFile() : File{
        val storageDirectory = this.activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("temp_image", "jpg", storageDirectory)
    }
}