package com.example.roman.handgum.ui.fragment.deeplink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.roman.handgum.R
import com.example.roman.handgum.databinding.FragmentDeepLinkBinding
import com.example.roman.handgum.domain.utils.Notifier
import com.example.roman.handgum.ui.base.BaseFragment

class DeepLinkFragment : BaseFragment() {

    override val titleRes = R.string.fragment_deep_link_title

    private var _binding: FragmentDeepLinkBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeepLinkBinding.inflate(inflater, container, false)
        binding.apply {
            button.setOnClickListener {

                val context = requireContext().applicationContext
                val arg = Bundle()
                arg.putString(
                    resources.getString(R.string.fragment_rev_details_nav_arg_name),
                    resources.getString(R.string.test_link)
                )
                val pendingIntent = findNavController()
                    .createDeepLink()
                    .setDestination(R.id.revDetailsFragment)
                    .setArguments(arg)
                    .createPendingIntent()

                Notifier.postNotification(100L, context, pendingIntent)

            }
        }

        return binding.root
    }

}