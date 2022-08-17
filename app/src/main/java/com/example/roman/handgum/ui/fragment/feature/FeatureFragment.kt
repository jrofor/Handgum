package com.example.roman.handgum.ui.fragment.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.roman.handgum.R
import com.example.roman.handgum.databinding.FragmentFeatureBinding
import com.example.roman.handgum.ui.base.BaseFragment
import javax.inject.Inject


class FeatureFragment : BaseFragment(R.layout.fragment_feature) {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<FeatureViewModel> { viewModelFactory }


    override val titleRes = R.string.fragment_rev_details_title

    private var _binding: FragmentFeatureBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.featureComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeatureBinding.inflate(inflater, container, false)
        binding.apply {

        }

        viewModel.apply {
            fLivaData.observe({ viewLifecycleOwner.lifecycle }, ::f)
            start()
        }
        return binding.root
    }

    private fun f(s: String) {
        binding.textView.text = s
    }


}