package com.example.roman.handgum.navigation

import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.Navigator


interface NavigationDispatcher {
    fun dispatch(action: NavigatingAction): Boolean
}

fun NavigationDispatcher.navigate(
    destinationId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) = dispatch(NavigatingAction.Navigating(destinationId, args, navOptions, navigatorExtras))

fun NavigationDispatcher.back() = dispatch(NavigatingAction.Back)

fun NavigationDispatcher.backToDestination(
    destinationId: Int,
    inclusive: Boolean,
) = dispatch(NavigatingAction.BackToDestination(destinationId, inclusive))