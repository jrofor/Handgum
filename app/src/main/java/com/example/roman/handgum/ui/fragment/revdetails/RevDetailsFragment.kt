package com.example.roman.handgum.ui.fragment.revdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.roman.handgum.R
import com.example.roman.handgum.databinding.FragmentRevDetailsBinding
import com.example.roman.handgum.ui.base.BaseFragment
import javax.inject.Inject

/**
 * @author rofor
 */
class RevDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<RevDetailsViewModel> { viewModelFactory }

    override val titleRes = R.string.fragment_rev_details_title

    private var _binding: FragmentRevDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.revDetailsComponent().create().inject(this)

        val args: RevDetailsFragmentArgs by navArgs()
        viewModel.urlLink = args.url
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRevDetailsBinding.inflate(inflater, container, false)
        binding.apply {
            webView.webViewClient = WebViewClient()
        }
        viewModel.apply {
            urlLinkLivaData.observe({ viewLifecycleOwner.lifecycle }, ::setWebViewLoadUrl)
            start()

            return binding.root
        }
    }

    private fun setWebViewLoadUrl(url: String) {
        binding.webView.loadUrl(url)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
