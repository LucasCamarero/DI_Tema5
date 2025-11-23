package com.lucascamarero.di_tema5.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ContadorViewModel: ViewModel() {

    // Estado compartido entre pantallas: pulsaciones del contador
    var pulsaciones by mutableStateOf(0)
        private set

    fun setNum(num: Int) {
        pulsaciones += num
    }
}