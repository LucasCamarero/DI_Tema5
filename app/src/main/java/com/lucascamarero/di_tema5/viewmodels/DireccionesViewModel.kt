package com.lucascamarero.di_tema5.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Declara un ViewModel encargado de gestionar una dirección y su formato
class DireccionesViewModel: ViewModel() {

    // LiveData interna que contiene la dirección original en texto
    private val _address = MutableLiveData<String>()

    // Inicializa el valor de la dirección con un texto predefinido
    init {
        _address.value = "Calle del perro, " +
                "Piso 6º B, " +
                "Bilbao, " +
                "España"
    }

    // Enum que define los tipos de formato disponibles para la dirección
    enum class FormatType {
        ORIGINAL, ONE_LINE, MULTILINE
    }

    // Estado Compose que guarda el formato actualmente seleccionado
    var selectedFormat by mutableStateOf(FormatType.ORIGINAL)

    // Actualiza el formato seleccionado por el usuario
    fun onFormatSelected(format: FormatType) {
        selectedFormat = format
    }

    // Devuelve la dirección en el formato elegido
    fun getFormattedAddress(): String {

        // Obtiene la dirección actual o devuelve cadena vacía si es null
        val address = _address.value ?: return ""

        // Aplica el formato en función del tipo seleccionado
        return when (selectedFormat) {
            FormatType.ORIGINAL -> address
            FormatType.ONE_LINE -> address.replace("," , " -")
            FormatType.MULTILINE -> address.replace(", " , "\n")
        }
    }
}