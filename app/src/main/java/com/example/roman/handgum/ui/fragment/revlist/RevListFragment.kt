package com.example.roman.handgum.ui.fragment.revlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.apply {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}