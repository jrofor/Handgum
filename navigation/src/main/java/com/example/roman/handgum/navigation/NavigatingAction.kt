package com.example.roman.handgum.navigation;

import android.os.Bundle
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

sealed class NavigatingAction {
    abstract override fun toString(): String

    class Navigating(
        val resId: Int,
        val args: Bundle?,
        val navOptions: NavOptions?,
        val navigatorExtras: Navigator.Extras?,
    ) : NavigatingAction() {
        constructor(
            directions: NavDirections,
            navOptions: NavOptions?,
            navigatorExtras: Navigator.Extras?,
        ) : this(directions.actionId, directions.arguments, navOptions, navigatorExtras)

        override fun toString(): String = buildString {
            append("Navigate(")
            append("resId=")
            append(resId)
            append(", args=")
            append(args)
            append(", navOptions=")
            append(navOptions)
            append(", navigatorExtras=")
            append(navigatorExtras)
            append(")")
        }
    }

    object Back : NavigatingAction() {
        override fun toString(): String = "Back()"
    }

    class BackToDestination(
        val destinationId: Int,
        val inclusive: Boolean,
    ) : NavigatingAction() {
        override fun toString(): String = buildString {
            append("BackToDestination(")
            append("destinationId=")
            append(destinationId)
            append(", inclusive=")
            append(inclusive)
            append(")")
        }
    }

    object Exit : NavigatingAction() {
        override fun toString(): String = "Exit()"
    }
}
