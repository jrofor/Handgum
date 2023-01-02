package com.example.roman.handgum.feature.revdlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roman.handgum.commonentity.ui.models.ReviewModel
import com.example.roman.handgum.core.baseview.BaseFragment
import com.example.roman.handgum.core.di.findDependencies
import com.example.roman.handgum.core.utils.extensions.observe
import com.example.roman.handgum.core.utils.viewbinding.viewBinding
import com.example.roman.handgum.feature.revdlist.adapter.ItemDecoration
import com.example.roman.handgum.feature.revdlist.adapter.ReviewAdapter
import com.example.roman.handgum.feature.revdlist.di.DaggerRevListComponent
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
    //override val titleRes = R.string.fragment_rev_list_title

    private val binding: FragmentRevListBinding by viewBinding()
    private val reviewAdapter = ReviewAdapter()

    override fun initDI() {
        DaggerRevListComponent.factory().create(findDependencies()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setHasOptionsMenu(true)
        initViews()
        viewModel.apply {
            lifecycle.addObserver(this)
            observe(viewModel.showModalProgress) { setRefreshing(it) }
            observe(viewModel.reviews) { renderReviews(it) }
            observe(viewModel.missingDataNotice) { showNoDataMessage(it) }
        }
    }

    private fun initViews() = with(binding) {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }
        recycle.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(ItemDecoration(resources.getDimensionPixelOffset(R.dimen.review_item_offset)))
            adapter = this@RevListFragment.reviewAdapter.apply {
                onItemClickListener = { viewModel.itemClicked(url = it) }
            }
        }
    }

    private fun renderReviews(list: List<ReviewModel>) {
        reviewAdapter.items = list
    }

    private fun setRefreshing(isRefresh: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isRefresh
    }

    private fun showNoDataMessage(isShow: Boolean) {
        binding.apply {
            if (isShow) {
                recycle.visibility = View.GONE
                tvError.visibility = View.VISIBLE
            } else {
                recycle.visibility = View.VISIBLE
                tvError.visibility = View.GONE
            }
        }
    }

/*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.feature -> {
                val actions = RevListFragmentDirections.actionRevListFragmentToFeatureFragment()
                findNavController(this).navigate(actions)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
*/

    override fun onDestroyView() {
        super.onDestroyView()
    }

}