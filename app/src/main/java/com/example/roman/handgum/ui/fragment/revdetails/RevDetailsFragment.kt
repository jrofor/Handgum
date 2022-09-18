package com.example.roman.handgum.ui.fragment.revdetails

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.roman.handgum.R
import com.example.roman.handgum.databinding.FragmentRevDetailsBinding
import com.example.roman.handgum.ui.base.BaseFragment
import com.example.roman.handgum.utils.extensions.observe
import com.example.roman.handgum.utils.rx.viewbinding.viewBinding
import javax.inject.Inject

/**
 * @author rofor
 */
class RevDetailsFragment : BaseFragment(R.layout.fragment_rev_details) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<RevDetailsViewModel> { viewModelFactory }

    override val titleRes = R.string.fragment_rev_details_title
    override val navArgs: RevDetailsFragmentArgs by navArgs()
    private val binding: FragmentRevDetailsBinding by viewBinding()

    override fun initDI() {
        appComponent.revDetailsComponent().create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.apply {
            viewModel.setUrlLink(navArgs.url)
            lifecycle.addObserver(this)
            observe(viewModel.liveState) { setWebViewLoadUrl(it.urlLink) }
        }
    }

    private fun initViews() = with(binding) {
        webView.apply {
            webViewClient = WebViewClient()
            webChromeClient = simpleWebChromeClient
        }
    }

    private var simpleWebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, progress: Int) {
            binding.progressBar.apply {
                if (progress in 0..99) {
                    if (!isVisible) showProgressBar(true)
                    setProgress(progress)
                } else {
                    showProgressBar(false)
                }
            }
        }
    }

    private fun showProgressBar(visible: Boolean) {
        binding.progressBar.apply {
            isVisible = visible
            isIndeterminate = !visible
        }
    }

    private fun setWebViewLoadUrl(url: String) {
        binding.webView.loadUrl(url)
    }
}
