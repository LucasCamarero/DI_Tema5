package com.lucascamarero.di_tema5.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Declara un ViewModel encargado de gestionar un contador
class ContadorViewModel: ViewModel() {

    // LiveData mutable interna que almacena el valor del contador
    private val _count = MutableLiveData<Int>()
    // LiveData pública y de solo lectura para exponer el contador
    val count: LiveData<Int> = _count

    // Incrementa el contador en 1
    fun upCount() {
        _count.value = (_count.value ?: 0) + 1
    }

    // Decrementa el contador en 1
    fun downCount() {
        _count.value = (_count.value ?: 0) - 1
    }

    // Inicializa el valor del contador a 0 cuando se crea el ViewModel
    init {
        _count.value = 0
    }
}