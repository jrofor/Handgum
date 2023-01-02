package com.example.roman.handgum.navigation

import android.app.Activity
import androidx.navigation.NavController

class ActivityNavController(
    private val navController: NavController,
    private val activity: Activity,
) : NavigationActionProvider {

    override fun invoke(action: NavigatingAction): Boolean {
        return when (action) {
            is NavigatingAction.Navigating -> {
                navController.navigate(
                    action.resId,
                    action.args,
                    action.navOptions,
                    action.navigatorExtras,
                )
                true
            }
            is NavigatingAction.Back -> navController.popBackStack()
            is NavigatingAction.BackToDestination -> navController.popBackStack(
                action.destinationId,
                action.inclusive
            )
            is NavigatingAction.Exit -> {
                activity.finish()
                true
            }

        }
    }
}


