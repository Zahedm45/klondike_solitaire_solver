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
import cdio.group21.litaire.databinding.FragmentSuggestionBinding
import cdio.group21.litaire.viewmodels.SharedViewModel


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
            setSuggestionUI(it.first.name, it.second.name)
        }
        binding.ivBackbutton.setOnClickListener(){
            findNavController().navigate(R.id.action_fragmentSuggestion_to_LandingPage)
        }


    }

    private fun setSuggestionUI(from: String, to: String){
        val fromNum = from[0]
        val fromIcon = charToCardIconID(from[1])
        val toNum = to[0]
        val toIcon = charToCardIconID(to[1])

        binding.leftMoveIcon.setImageResource(fromIcon)
        binding.leftMoveText.text = fromNum.toString()

        binding.rightMoveIcon.setImageResource(toIcon)
        binding.rightMoveText.text = toNum.toString()
    }

    private fun charToCardIconID(char: Char): Int {
        when(char){
            'H' -> return R.drawable.vector_hearts
            'S' -> return R.drawable.vector_spades
            'D' -> return R.drawable.vector_diamonds
            'C' -> return R.drawable.vector_clubs
            else -> return R.drawable.vector_circle
        }
    }


}