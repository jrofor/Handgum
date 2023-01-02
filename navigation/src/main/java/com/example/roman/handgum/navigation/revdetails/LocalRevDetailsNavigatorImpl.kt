package com.example.roman.handgum.navigation.revdetails

import com.example.roman.handgum.navigation.NavigationDispatcher
import com.example.roman.handgum.navigation.back
import com.example.roman.handgum.navigationapi.revdetails.LocalRevDetailsNavigator
import javax.inject.Inject


class LocalRevDetailsNavigatorImpl @Inject constructor(
    private val navigationDispatcher: NavigationDispatcher,
) : LocalRevDetailsNavigator {

    override fun popBack() {
        navigationDispatcher.back()
    }
}