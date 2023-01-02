package com.example.roman.handgum.navigation

import javax.inject.Inject

class NavigationActionProviderImpl @Inject constructor() : NavProviderKeeper, NavigationDispatcher {

    private var navigationActionProvider: NavigationActionProvider? = null

    override fun setProvider(provider: NavigationActionProvider) {
        deleteProvider(provider)
        navigationActionProvider = provider
    }

    override fun deleteProvider(provider: NavigationActionProvider) {
        if (navigationActionProvider == provider) {
            navigationActionProvider = null
        }
    }

    override fun dispatch(action: NavigatingAction) =
        requireNotNull(navigationActionProvider).invoke(action)

}