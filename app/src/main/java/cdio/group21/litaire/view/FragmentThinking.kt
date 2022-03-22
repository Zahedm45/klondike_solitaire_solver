package cdio.group21.litaire.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cdio.group21.litaire.R
import cdio.group21.litaire.databinding.FragmentLandingPageBinding
import cdio.group21.litaire.databinding.FragmentThinkingBinding

class FragmentThinking : Fragment() {
    private var _binding: FragmentThinkingBinding? = null

    private val binding get() = _binding!!
    //Shared viewmodel between fragments because some data is shared.
    private val viewModel: FragmentThinkingVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Use the ViewModel
    }

}