package com.example.roman.handgum.ui.fragment.revlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roman.handgum.App
import com.example.roman.handgum.R
import com.example.roman.handgum.core.baseview.BaseFragment
import com.example.roman.handgum.core.utils.extensions.observe
import com.example.roman.handgum.core.utils.viewbinding.viewBinding
import com.example.roman.handgum.databinding.FragmentRevListBinding
import com.example.roman.handgum.database.domain.models.ReviewModel
import com.example.roman.handgum.feature.revdetails.RevDetailsFragmentArgs
import com.example.roman.handgum.ui.fragment.revlist.adapter.ItemDecoration
import com.example.roman.handgum.ui.fragment.revlist.adapter.ReviewAdapter
import timber.log.Timber
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
        (requireActivity().application as App).appComponent.revListComponent().create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
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
                onItemClickListener = { navigateToRevDetailsFragment(url = it) }
            }
        }
    }

    private fun navigateToRevDetailsFragment(url: String) {
        findNavController(this).navigate(
            R.id.rev_details_graph,
            RevDetailsFragmentArgs(url).toBundle(),
        )
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

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.tag("ViewBinding").v("RevListFragment onDestroyView")
    }

}