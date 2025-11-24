package com.lucascamarero.di_tema5.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// ViewModel que gestiona un progreso animado de manera segura
class ProgresoViewModel : ViewModel() {

    // LiveData interna que mantiene el valor del progreso (0f a 1f)
    private val _progress = MutableLiveData(0f)
    // LiveData pública de solo lectura
    val progress: LiveData<Float> = _progress

    // Función que simula el progreso incrementando su valor poco a poco
    fun startProgress() {
        // Ejecuta la carga dentro del scope del ViewModel usando corrutinas
        viewModelScope.launch {
            _progress.value = 0f // Reinicia el progreso

            // Se asegura de no usar null con el operador Elvis (?:)
            while ((_progress.value ?: 0f) < 1f) {
                delay(100)  // Espera 100ms entre incrementos
                _progress.value = (_progress.value ?: 0f) + 0.01f  // Incrementa el progreso en 0.01
            }
        }
    }
}
