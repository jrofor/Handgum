package com.example.roman.handgum.ui.fragment.revlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roman.handgum.R
import com.example.roman.handgum.databinding.FragmentRevListBinding
import com.example.roman.handgum.domain.models.ReviewModel
import com.example.roman.handgum.ui.base.BaseFragment
import com.example.roman.handgum.ui.fragment.revlist.adapter.ItemDecoration
import com.example.roman.handgum.ui.fragment.revlist.adapter.ReviewAdapter
import javax.inject.Inject

/**
 * @author rofor
 */
class RevListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<RevListViewModel> { viewModelFactory }

    override val isNavigateBackVisible = false
    override val titleRes = R.string.fragment_rev_list_title

    private var _binding: FragmentRevListBinding? = null
    private val binding get() = _binding!!

    private val reviewAdapter = ReviewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.revListComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRevListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        binding.apply {
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

        viewModel.apply {
            documentLiveData.observe({ viewLifecycleOwner.lifecycle }, ::setData)
            loadingLiveData.observe({ viewLifecycleOwner.lifecycle }, ::setRefreshing)
            noDataLiveData.observe({ viewLifecycleOwner.lifecycle }, ::showNoDataMessage)
            start()
        }
        return binding.root
    }

    private fun navigateToRevDetailsFragment(url: String) {
        val actions =
            RevListFragmentDirections.actionRevListFragmentToRevDetailsFragment(url)
        findNavController().navigate(actions)
    }

    private fun setData(list: List<ReviewModel>) {
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
                val actions =
                    RevListFragmentDirections.actionRevListFragmentToFeatureFragment()
                findNavController().navigate(actions)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}