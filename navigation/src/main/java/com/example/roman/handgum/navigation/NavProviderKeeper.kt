package com.example.roman.handgum.navigation


interface NavProviderKeeper {
    fun setProvider(provider: NavigationActionProvider)
    fun deleteProvider(provider: NavigationActionProvider)
}