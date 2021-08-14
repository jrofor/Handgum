package com.example.roman.handgum.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roman.handgum.databinding.FragmentRevListBinding
import com.example.roman.handgum.ui.base.BaseFragment

/**
 * @author rofor
 */
class RevListFragment : BaseFragment() {

    private var _binding: FragmentRevListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRevListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}