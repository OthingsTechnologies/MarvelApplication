package com.emmanuel.martinez.rodriguez.androiddevelopertest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory( private val pageSize:Int): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return HomeViewModel(pageSize) as T

    }
}