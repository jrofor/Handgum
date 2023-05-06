package com.example.roman.handgum.feature.revlist.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.roman.handgum.core.baseview.BaseFragment
import com.example.roman.handgum.core.di.findDependencies
import com.example.roman.handgum.core.utils.viewbinding.viewBinding
import com.example.roman.handgum.feature.revlist.RevListViewModel
import com.example.roman.handgum.feature.revlist.di.DaggerRevListComponent
import com.example.roman.handgum.feature.revlist.R
import com.example.roman.handgum.feature.revlist.databinding.FragmentRevListBinding
import javax.inject.Inject

/**
 * @author rofor
 */
class RevListFragment : BaseFragment(R.layout.fragment_rev_list) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<RevListViewModel> { viewModelFactory }

    override val isNavigateBackVisible = false

    private val binding: FragmentRevListBinding by viewBinding()

    override fun initDI() {
        DaggerRevListComponent.factory().create(findDependencies()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.apply {
            lifecycle.addObserver(this)
        }
    }

    private fun initViews() = with(binding) {
        root.setContent {
            RevListView(
                viewModel = viewModel,
            )
        }
    }

}