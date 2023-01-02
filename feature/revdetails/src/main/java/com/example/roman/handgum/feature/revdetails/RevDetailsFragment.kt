package com.example.roman.handgum.feature.revdetails

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.roman.handgum.core.baseview.BaseFragment
import com.example.roman.handgum.core.di.findDependencies
import com.example.roman.handgum.core.utils.extensions.observe
import com.example.roman.handgum.core.utils.viewbinding.viewBinding
import com.example.roman.handgum.feature.revdetails.databinding.FragmentRevDetailsBinding
import com.example.roman.handgum.feature.revdetails.di.DaggerRevDetailsComponent
import javax.inject.Inject

/**
 * @author rofor
 */
class RevDetailsFragment : BaseFragment(R.layout.fragment_rev_details) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<RevDetailsViewModel> { viewModelFactory }

    //override val titleRes = R.string.fragment_rev_details_title
    override val navArgs: RevDetailsFragmentArgs by navArgs()
    private val binding: FragmentRevDetailsBinding by viewBinding()

    override fun initDI() {
        DaggerRevDetailsComponent.factory().create(findDependencies()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.apply {
            lifecycle.addObserver(this)
            viewModel.setUrlLink(navArgs.url)
            observe(viewModel.liveState) { setWebViewLoadUrl(it.urlLink) }
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.onBackPressed()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }

    private fun initViews() = with(binding) {
        webView.apply {
            webViewClient = WebViewClient()
            webChromeClient = simpleWebChromeClient
        }
    }

    private var simpleWebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, progress: Int) {
            if (this@RevDetailsFragment.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
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
