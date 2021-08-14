package com.example.roman.handgum.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.roman.handgum.R
import com.example.roman.handgum.databinding.ActivityMainBinding
import com.example.roman.handgum.ui.fragment.RevListFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            val revListFragment = RevListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_list, revListFragment, F_LIST_TAG).commit()
        }
    }

    companion object {
        var F_LIST_TAG = "list_fragment"
    }
}