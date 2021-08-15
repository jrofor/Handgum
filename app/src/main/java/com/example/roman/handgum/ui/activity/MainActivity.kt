package com.example.roman.handgum.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.roman.handgum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //simple navigation
        /*if (savedInstanceState == null) {
            val revListFragment = RevListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, revListFragment, F_LIST_TAG).commit()
        }*/
    }

    companion object {
        //var F_LIST_TAG = "list_fragment"
    }
}