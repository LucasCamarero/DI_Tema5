package com.lucascamarero.di_tema5.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DireccionesViewModel: ViewModel() {

    private val _address = MutableLiveData<String>()

    init {
        _address.value = "Calle del perro, " +
                "Piso 6º B, " +
                "Bilbao, " +
                "España"
    }

    var selectedFormat by mutableStateOf(FormatType.ORIGINAL)

    fun onFormatSelected(format: FormatType) {
        selectedFormat = format
    }

    enum class FormatType {
        ORIGINAL, ONE_LINE, MULTILINE
    }

    fun getFormattedAddress(): String {

        val address = _address.value ?: return ""

        return when (selectedFormat) {
            FormatType.ORIGINAL -> address
            FormatType.ONE_LINE -> address.replace("," , " -")
            FormatType.MULTILINE -> address.replace(", " , "\n")
        }
    }
}