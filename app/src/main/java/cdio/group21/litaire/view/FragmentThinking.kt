package cdio.group21.litaire.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import cdio.group21.litaire.R
import cdio.group21.litaire.databinding.FragmentLandingPageBinding
import cdio.group21.litaire.databinding.FragmentThinkingBinding
import cdio.group21.litaire.viewmodels.SharedViewModel
import java.io.File

class FragmentThinking : Fragment() {
    private var _binding: FragmentThinkingBinding? = null

    private val binding get() = _binding!!
    //Shared viewmodel between fragments because some data is shared.
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThinkingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBackground.setImageURI(viewModel.getImageURI().value)
        //TODO should navigate to solved or nomoves dependent on data received
        viewModel.processImage(viewModel.getImageURI().value!!, {navigateToSuggestion()}, {navigateToSuggestion()})
        //viewModel.processImage(viewModel.getImageURI().value!!)
    }

    private fun navigateToSuggestion(){
        findNavController().navigate(R.id.action_fragmentThinking_to_fragmentSuggestion)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }



}