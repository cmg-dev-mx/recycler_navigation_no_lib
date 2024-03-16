package mx.dev.shellcore.android.recyclernavigation.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mx.dev.shellcore.android.recyclernavigation.core.model.Info
import mx.dev.shellcore.android.recyclernavigation.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    var info: Info? = null

    private val binding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.fragmentDetailTitle.text = info?.title
        binding.fragmentDetailDescription.text = info?.description
        binding.fragmentDetailBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return binding.root
    }
}