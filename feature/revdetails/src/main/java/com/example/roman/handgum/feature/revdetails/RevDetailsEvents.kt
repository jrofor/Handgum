package com.example.roman.handgum.feature.revdetails

internal sealed class RevDetailsEvents {

    internal data class ShowWeb(val url: String) : RevDetailsEvents()
}