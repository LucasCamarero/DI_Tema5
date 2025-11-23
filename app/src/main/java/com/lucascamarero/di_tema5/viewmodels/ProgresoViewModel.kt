package com.lucascamarero.di_tema5.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProgresoViewModel: ViewModel() {

    private val _progress = MutableLiveData<Float>()
    val progress: LiveData<Float> = _progress

    init {
        _progress.value = 0f
    }

    // Simulamos el progreso
    fun startProgress() {
        viewModelScope.launch {
            _progress.value = 0f

            while (_progress.value !! < 1f) {
                delay(100)
                _progress.value = _progress.value!! + 0.01f
            }
        }
    }
}