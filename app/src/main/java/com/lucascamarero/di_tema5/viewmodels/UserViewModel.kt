package com.lucascamarero.di_tema5.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class UserViewModel {

    // Estado compartido entre pantallas: pulsaciones del contador
    var pulsaciones by mutableStateOf(0)
        private set

    fun setnum(num: Int) {
        pulsaciones = num
    }
}