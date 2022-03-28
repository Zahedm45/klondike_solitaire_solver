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
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import cdio.group21.litaire.R
import cdio.group21.litaire.databinding.FragmentLandingPageBinding
import cdio.group21.litaire.viewmodels.SharedViewModel
import com.swein.easypermissionmanager.EasyPermissionManager
import java.io.File



class FragmentLandingPage : Fragment() {

    private var _binding: FragmentLandingPageBinding? = null

    private val binding get() = _binding!!

    private val viewModel: SharedViewModel by activityViewModels()

    //TODO used to select pictures from album - may need to initialize inside onViewCreated()
    private val selectPictureLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
        binding.ivBackground.setImageURI(it)
    }

    private var tempImageUri: Uri? = null
    private var tempImageFilePath = ""
    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()){ success ->
        if (success){
            binding.tvTitle.visibility = View.GONE
            binding.ivBackground.setImageURI(tempImageUri)
            viewModel.setImageURI(tempImageUri!!)
            findNavController().navigate(R.id.action_LandingPage_to_fragmentThinking)
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
        if(!this.isResumed) {
            promptPermissions()
        }

        //TODO make camera landscape mode?
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