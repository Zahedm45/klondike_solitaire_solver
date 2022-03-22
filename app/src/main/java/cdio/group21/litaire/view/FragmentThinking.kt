package cdio.group21.litaire.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cdio.group21.litaire.R

class FragmentThinking : Fragment() {

    companion object {
        fun newInstance() = FragmentThinking()
    }

    private lateinit var viewModel: FragmentThinkingVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_thinking_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentThinkingVM::class.java)
        // TODO: Use the ViewModel
    }

}