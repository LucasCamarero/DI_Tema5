package com.lucascamarero.di_tema5.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import android.graphics.Color

// Declara un ViewModel encargado de gestionar el selector de colores
class ColoresViewModel : ViewModel() {

    // Variable que indica el modo actual: puede ser "HSV" o "RGB"
    var modo by mutableStateOf("HSV")

    // Valores iniciales para HSV (representan un color específico)
    var hue by mutableStateOf(307f)
    var saturation by mutableStateOf(0.923f)
    var value by mutableStateOf(0.961f)

    // Nivel de opacidad (1f = opaco, 0f = totalmente transparente)
    var alpha by mutableStateOf(1f)

    // Valores RGB que se calcularán a partir de HSV
    var red by mutableStateOf(0f)
    var green by mutableStateOf(0f)
    var blue by mutableStateOf(0f)

    // Bloque que se ejecuta al crear la clase
    init {
        // Actualiza los valores RGB tomando como base HSV inicial
        actualizarHSV()
    }

    // Función que actualiza los valores HSV y recalcula automáticamente los RGB
    fun actualizarHSV(
        // Parámetro opcional para el matiz del color (Hue)
        h: Float = hue,
        // Parámetro opcional para la saturación del color
        s: Float = saturation,
        // Parámetro opcional para el brillo o valor (Value)
        v: Float = value,
        // Parámetro opcional para la opacidad (Alpha)
        a: Float = alpha
    ) {
        // Asigna los nuevos valores a las variables correspondientes
        hue = h
        saturation = s
        value = v
        alpha = a

        // Convierte HSV + Alpha en un color entero (ARGB)
        val colorInt = Color.HSVToColor((alpha * 255).toInt(), floatArrayOf(hue, saturation, value))

        // Extrae el componente rojo del color entero
        red = Color.red(colorInt).toFloat()
        // Extrae el componente verde del color entero
        green = Color.green(colorInt).toFloat()
        // Extrae el componente azul del color entero
        blue = Color.blue(colorInt).toFloat()
    }

    // Función que actualiza los valores RGB y recalcula automáticamente HSV
    fun actualizarRGB(
        // Parámetro opcional para el rojo
        r: Float = red,
        // Parámetro opcional para el verde
        g: Float = green,
        // Parámetro opcional para el azul
        b: Float = blue,
        // Parámetro opcional para la opacidad
        a: Float = alpha
    ) {
        // Asigna nuevos valores RGB
        red = r
        green = g
        blue = b
        alpha = a

        // Array donde se guardará el resultado de la conversión a HSV
        val hsv = FloatArray(3)

        // Convierte los valores RGB a HSV y los almacena en el array
        Color.RGBToHSV(r.toInt(), g.toInt(), b.toInt(), hsv)

        // Actualiza las variables con los valores convertidos
        hue = hsv[0]
        saturation = hsv[1]
        value = hsv[2]
    }

    // Función para actualizar solo el alpha y recalcular el color
    fun actualizarAlpha(a: Float) {
        // Asigna el nuevo valor de alpha
        alpha = a
        // Recalcula los valores RGB con el nuevo alpha
        actualizarHSV()
    }

    // Cambia el modo entre "HSV" y "RGB"
    fun cambiarModo(nuevoModo: String) {
        // Asigna el nuevo modo a la variable
        modo = nuevoModo
    }
}