package com.example.roman.handgum.navigation

interface NavigationActionProvider {
    operator fun invoke(action: NavigatingAction): Boolean
}

val NAVIGATION_PROVIDER_STUB = object : NavigationActionProvider {
    override fun invoke(action: NavigatingAction): Boolean {
        return false
    }
}