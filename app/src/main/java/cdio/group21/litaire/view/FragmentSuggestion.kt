package cdio.group21.litaire.view

import Card
import Rank
import Suit
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cdio.group21.litaire.R
import cdio.group21.litaire.databinding.FragmentSuggestionBinding
import cdio.group21.litaire.utils.SolitaireDrawUtils
import cdio.group21.litaire.viewmodels.ImageAcquisitionMode
import cdio.group21.litaire.viewmodels.SharedViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


typealias IconID = Int

class FragmentSuggestion : Fragment() {

	private var _binding: FragmentSuggestionBinding? = null

	private val binding get() = _binding!!

	private val viewModel: SharedViewModel by activityViewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		_binding = FragmentSuggestionBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel.getPreviewBitmap().observe(viewLifecycleOwner) {
			binding.ivBackground.setImageBitmap(it)
		}

		viewModel.getMoves().observe(viewLifecycleOwner) { moves ->
			if (moves.isEmpty()) {
				return@observe setSuggestionUI("📷", "📸")
			}
			val currentMove = moves.firstOrNull()
				?: return@observe setSuggestionUI("Draw three cards", "Talon")

			val source = if (currentMove.indexOfSourceBlock.toInt() == 8) {
				"Talon"
			} else {
				"${currentMove.card} ${currentMove.indexOfSourceBlock + 1}"
			}

			val destination = if (currentMove.isMoveToFoundation)
				"Foundation" else (currentMove.indexOfDestination + 1).toString()

			setSuggestionUI(
				"$source",
				destination
			)

		}

		binding.cardView2.setOnClickListener {
			val moves = viewModel.getMoves().value ?: return@setOnClickListener
			if (moves.isEmpty()) return@setOnClickListener
			moves.removeFirst()
			viewModel.getMoves().postValue(moves)

			// Blink so the user knows that their input was registered
			binding.cardView2.visibility = View.INVISIBLE
			lifecycleScope.launch {
				delay(100)
				binding.cardView2.visibility = View.VISIBLE
			}
		}

		binding.ivAlbumButtonSuggestion.setOnClickListener {
			viewModel.acquiringImage.postValue(ImageAcquisitionMode.GALLERY)
			findNavController().navigate(R.id.action_fragmentSuggestion_to_LandingPage)
		}

		binding.ivCameraButtonSuggestion.setOnClickListener {
			viewModel.acquiringImage.postValue(ImageAcquisitionMode.CAMERA)
			findNavController().navigate(R.id.action_fragmentSuggestion_to_LandingPage)
		}

		binding.ivUndobutton.setOnClickListener {
			viewModel.undoSolverRun()
		}

		viewModel.getGameState().observe(viewLifecycleOwner) { solitaire ->
			if (solitaire == null) return@observe
			viewModel.getPreviewBitmap().value?.let { preview ->
				binding.ivBackground.setImageBitmap(
					SolitaireDrawUtils.drawSolitaireGame(
						preview, solitaire
					)
				)
			}
		}

		binding.applyEditsButton.setOnClickListener {
			if (binding.TextEditGame.text.length != 5) return@setOnClickListener
			val text = binding.TextEditGame.text.split(" ")
			val fromText = text[0]
			val toText = text[1]

			Log.i("Edit button", "Trying to edit card $fromText to $toText")

			val gameState = viewModel.getGameState().value
			if (gameState != null) {
				val fromCard = gameState.findCardFromString(fromText)
				val changeToCard =
					Card(rank = Rank.fromChar(toText[0]), suit = Suit.fromChar(toText[1]))
				Log.i("Edit button", "replacing $fromCard with $changeToCard")
				if (fromCard == null) return@setOnClickListener
				viewModel.replaceCardInGame(fromCard, changeToCard)
				Log.i("Edit button", "succesfully replaced")
				binding.TextEditGame.setText("")
			}
		}

	}



	private fun setSuggestionUI(from: String, to: String) {
		binding.leftMoveText.text = from
		binding.rightMoveText.text = to
	}


}