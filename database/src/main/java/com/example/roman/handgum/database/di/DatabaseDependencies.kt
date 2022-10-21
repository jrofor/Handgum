package com.example.roman.handgum.database.di

import android.content.Context
import com.example.roman.handgum.core.di.Dependencies

//The interface which is implemented in AppComponent.
//In this interface, we specify the dependencies we need to DatabaseComponent,
// which we take from AppComponent and his modules
interface DatabaseDependencies: Dependencies {
    val context: Context
}