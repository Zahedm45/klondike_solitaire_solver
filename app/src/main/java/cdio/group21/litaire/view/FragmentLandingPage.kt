package cdio.group21.litaire.view

import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cdio.group21.litaire.R
import cdio.group21.litaire.databinding.FragmentLandingPageBinding
import cdio.group21.litaire.utils.SolitaireDrawUtils.drawSolitaireGame
import cdio.group21.litaire.viewmodels.LandingPageViewModel
import cdio.group21.litaire.viewmodels.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream


class FragmentLandingPage : Fragment() {

	private var _binding: FragmentLandingPageBinding? = null

	private val binding get() = _binding

	private val sharedViewModel: SharedViewModel by activityViewModels()
	private val viewModel: LandingPageViewModel by viewModels()
	private var tempImageUri: Uri? = null

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
			binding?.ivBackground?.setImageBitmap(it)
			sharedViewModel.processImage(it.copy(Bitmap.Config.RGB_565, false))
			//findNavController().navigate(R.id.action_LandingPage_to_fragmentSuggestion)
		}

		sharedViewModel.getDetectionList().observe(viewLifecycleOwner) { detectionList ->
			sharedViewModel.updateGame(detectionList).getOrElse {
				updateUItoLoading(visible = false)
				setErrorMessage(enabled = true, msg = it.message ?: "Error: Not message specified")
			}
		}

		sharedViewModel.getGameState().observe(viewLifecycleOwner) { game ->
			val img = viewModel.getImageBitmap().value ?: return@observe
			val game = game ?: return@observe

			Log.i("Gamestate: ", "has been observed")
			val imgResult = drawSolitaireGame(img, game)
			sharedViewModel.setPreviewBitmap(imgResult)
			binding?.ivBackground?.setImageBitmap(imgResult)
			findNavController().navigate(R.id.fragmentSuggestion)
		}


		binding?.ivCameraButton?.setOnClickListener {
			takePicture()
		}
		binding?.ivAlbumButton?.setOnClickListener {
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
		cameraLauncher.launch(cameraIntent)
	}


	private val selectPictureLauncher =
		registerForActivityResult(ActivityResultContracts.GetContent()) {
			lifecycleScope.launch {
				setErrorMessage(enabled = false, msg = "")
				updateUItoLoading(visible = true)
				it?.apply {
					launch {
						binding?.ivBackground?.setImageURI(it)
					}
					launch {
						viewModel.setImageBitmap(uriToBitmap(it))
					}
				}

			}

		}


	private val cameraLauncher = registerForActivityResult(
		ActivityResultContracts.StartActivityForResult()
	) {
		lifecycleScope.launch {
			if (it.resultCode == RESULT_OK) {
				setErrorMessage(enabled = false, msg = "")
				updateUItoLoading(visible = true)
				launch {
					binding?.ivBackground?.setImageURI(tempImageUri)
				}
				launch {
					viewModel.setImageBitmap(uriToBitmap(tempImageUri!!))
				}
			}
		}
	}

	private suspend fun uriToBitmap(uri: Uri): Bitmap {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
			val source = ImageDecoder.createSource(requireActivity().contentResolver, uri)
			val bitmap = withContext(Dispatchers.IO) {
				async { return@async ImageDecoder.decodeBitmap(source) }.await()
			}
			//val bitmap = ImageDecoder.decodeBitmap(source)

			val desiredWidth = 2048
			val scalingFactor = desiredWidth.toDouble() / bitmap.width.toDouble()
			val scaledBitmap = Bitmap.createScaledBitmap(
				bitmap,
				(bitmap.width * scalingFactor).toInt(),
				(bitmap.height * scalingFactor).toInt(), true
			)

			val baos = ByteArrayOutputStream()
			scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos)
			val data = baos.toByteArray()

			return BitmapFactory.decodeByteArray(data, 0, data.size)
		} else {
			TODO("VERSION.SDK_INT < P")
		}
	}

	private inline fun updateUItoLoading(visible: Boolean) {
		binding?.ivAlbumButton?.visibility = if (visible) View.GONE else View.VISIBLE
		binding?.ivCameraButton?.visibility = if (visible) View.GONE else View.VISIBLE
		binding?.tvTitle?.visibility = if (visible) View.GONE else View.VISIBLE
		binding?.tvAction?.text = if (visible) "Loading..." else ""
		binding?.pbThinking?.visibility = if (visible) View.VISIBLE else View.GONE
	}

	private inline fun setErrorMessage(enabled: Boolean, msg: String = "Error, Try Again!") {
		binding?.ivAlbumButton?.visibility = if (!enabled) View.GONE else View.VISIBLE
		binding?.ivCameraButton?.visibility = if (!enabled) View.GONE else View.VISIBLE
		binding?.tvTitle?.visibility = if (!enabled) View.GONE else View.VISIBLE
		binding?.tvAction?.text = if (enabled) msg else ""
	}

}




