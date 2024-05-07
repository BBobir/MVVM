package com.humbur.mvvm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ApplicationViewModelFactory(val application: Application):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {


        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(application)as T
        }

        throw IllegalArgumentException("Factory is not available")
    }
}
