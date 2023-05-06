package com.example.roman.handgum.navigation.revdetails

import com.example.roman.handgum.feature.revdetails.view.RevDetailsFragmentArgs
import com.example.roman.handgum.navigation.NavigationDispatcher
import com.example.roman.handgum.navigation.R
import com.example.roman.handgum.navigation.navigate
import com.example.roman.handgum.navigationapi.revdetails.RevDetailsNavigator
import javax.inject.Inject

class RevDetailsNavigatorImpl @Inject constructor(
    private val navigationDispatcher: NavigationDispatcher
) : RevDetailsNavigator {

    override fun pushRevDetailsScreen(url: String) {
        navigationDispatcher.navigate(
            R.id.rev_details_graph,
            RevDetailsFragmentArgs(url = url).toBundle(),
        )
    }
}