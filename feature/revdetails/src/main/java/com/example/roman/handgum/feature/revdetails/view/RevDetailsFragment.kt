package com.example.roman.handgum.feature.revdetails.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.roman.handgum.core.baseview.BaseFragment
import com.example.roman.handgum.core.di.findDependencies
import com.example.roman.handgum.core.utils.extensions.observe
import com.example.roman.handgum.core.utils.viewbinding.viewBinding
import com.example.roman.handgum.feature.revdetails.R
import com.example.roman.handgum.feature.revdetails.RevDetailsEvents
import com.example.roman.handgum.feature.revdetails.RevDetailsViewModel
import com.example.roman.handgum.feature.revdetails.databinding.FragmentRevDetailsBinding
import com.example.roman.handgum.feature.revdetails.di.DaggerRevDetailsComponent
import javax.inject.Inject

/**
 * @author rofor
 */
internal class RevDetailsFragment : BaseFragment(R.layout.fragment_rev_details) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<RevDetailsViewModel> { viewModelFactory }

    override val navArgs: RevDetailsFragmentArgs by navArgs()
    private val binding: FragmentRevDetailsBinding by viewBinding()

    override fun initDI() {
        DaggerRevDetailsComponent.factory().create(findDependencies()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.apply {
            navArgs = this@RevDetailsFragment.navArgs
            lifecycle.addObserver(this)
            observe(viewModel.eventState, ::renderEvent)
        }
    }

    private fun initViews() = with(binding) {
        root.setContent {
            RevDetailsView(viewModel = viewModel)
        }
    }

    private fun renderEvent(event: RevDetailsEvents) {
        when (event) {
            is RevDetailsEvents.ShowWeb -> openUrl(event.url)
        }
    }

}
