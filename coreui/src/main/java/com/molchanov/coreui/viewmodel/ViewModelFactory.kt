package com.molchanov.coreui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelFactory @Inject constructor() : ViewModelProvider.NewInstanceFactory() {

    private val viewModelMap = HashMap<Class<*>, ViewModel>()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return if (viewModelMap.containsKey(modelClass)) viewModelMap[modelClass] as T
        else {
            modelClass.newInstance().also {
                viewModelMap[modelClass] = it
            }
        }
    }
}