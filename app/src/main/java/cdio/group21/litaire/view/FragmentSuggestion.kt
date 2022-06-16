package cdio.group21.litaire.view

import Card
import Suit
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import cdio.group21.litaire.R
import cdio.group21.litaire.databinding.FragmentSuggestionBinding
import cdio.group21.litaire.utils.SolitaireDrawUtils
import cdio.group21.litaire.viewmodels.SharedViewModel


typealias IconID = Int

class FragmentSuggestion : Fragment() {

    private var _binding: FragmentSuggestionBinding? = null

    private val binding get() = _binding!!

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
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
        viewModel.getSuggestion().observe(viewLifecycleOwner) {
            setSuggestionUI(it.first, it.second)
        }
        binding.ivBackbutton.setOnClickListener(){
            findNavController().navigate(R.id.action_fragmentSuggestion_to_LandingPage)
        }

        viewModel.getGameState().observe(viewLifecycleOwner){ solitaire ->
            if(solitaire == null) return@observe
            viewModel.getPreviewBitmap().value?.let { preview ->
                binding.ivBackground.setImageBitmap(SolitaireDrawUtils.drawSolitaireGame(
                    preview, solitaire))
            }
        }

        binding.applyEditsButton.setOnClickListener {
            if(binding.TextEditGame.text.length != 5) return@setOnClickListener
            val text = binding.TextEditGame.text.split(" ")
            val fromText = text[0]
            val toText = text[1]

            Log.i("Edit button", "Trying to edit card $fromText to $toText")

            val gameState =  viewModel.getGameState().value
            if(gameState != null){
                val fromCard = gameState.findCardFromString(fromText)
                val changeToCard = Card(rank = Rank.fromChar(toText[0]), suit = Suit.fromChar(toText[1]))
                Log.i("Edit button", "replacing $fromCard with $changeToCard")
                if(fromCard == null) return@setOnClickListener
                viewModel.replaceCardInGame(fromCard, changeToCard)
                Log.i("Edit button", "succesfully replaced")
                binding.TextEditGame.setText("")
            }

        }




    }

    private fun setSuggestionUI(from: Card, to: Card){
        val fromNum = from.rank.toString()
        val fromIcon = suitToIconID(from.suit)
        val toNum = to.rank.toString()
        val toIcon = suitToIconID(to.suit)

        binding.leftMoveIcon.setImageResource(fromIcon)
        binding.leftMoveText.text = fromNum.toString()

        binding.rightMoveIcon.setImageResource(toIcon)
        binding.rightMoveText.text = toNum.toString()
    }

    private fun suitToIconID(suit: Suit): IconID {
        when(suit){
            Suit.HEART -> return R.drawable.vector_hearts
            Suit.SPADE -> return R.drawable.vector_spades
            Suit.DIAMOND -> return R.drawable.vector_diamonds
            Suit.CLUB -> return R.drawable.vector_clubs
            else -> return R.drawable.vector_circle
        }
    }


}