package cdio.group21.litaire.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import cdio.group21.litaire.R
import cdio.group21.litaire.databinding.FragmentSolvedBinding
import cdio.group21.litaire.databinding.FragmentSuggestionBinding
import cdio.group21.litaire.viewmodels.SharedViewModel


class FragmentSolved : Fragment() {

    private var _binding: FragmentSolvedBinding? = null

    private val binding get() = _binding!!

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBackbutton.setOnClickListener(){
            findNavController().navigate(R.id.action_fragmentSolved_to_LandingPage)
        }
    }

}