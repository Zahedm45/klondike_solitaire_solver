package cdio.group21.litaire.view

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import cdio.group21.litaire.R
import cdio.group21.litaire.data.Card2
import cdio.group21.litaire.data.Suit
import cdio.group21.litaire.databinding.FragmentSuggestionBinding
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
        viewModel.getImageBitmap().observe(viewLifecycleOwner, {
            binding.ivBackground.setImageBitmap(it)
        })
        viewModel.getSuggestion().observe(viewLifecycleOwner) {
            setSuggestionUI(it.first, it.second)
        }
        binding.ivBackbutton.setOnClickListener(){
            findNavController().navigate(R.id.action_fragmentSuggestion_to_LandingPage)
        }


    }

    private fun setSuggestionUI(from: Card2, to: Card2){
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