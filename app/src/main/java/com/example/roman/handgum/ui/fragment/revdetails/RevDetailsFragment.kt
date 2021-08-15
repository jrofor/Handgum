package com.example.roman.handgum.ui.fragment.revdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roman.handgum.databinding.FragmentRevDetailsBinding
import com.example.roman.handgum.ui.base.BaseFragment

/**
 * @author rofor
 */
class RevDetailsFragment : BaseFragment() {

    private var _binding: FragmentRevDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRevDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
