package dev.hossain.android.catalog.ui.home

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(private val preferences: SharedPreferences) : ViewModel() {
    init {
        Timber.d("Got injected preference: $preferences")
    }
}
