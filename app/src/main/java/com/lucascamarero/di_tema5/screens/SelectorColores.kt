package com.lucascamarero.di_tema5.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lucascamarero.di_tema5.viewmodels.ColoresViewModel

// Composable que dibuja la pantalla del selector de colores
@Composable
fun SelectorColores(viewModel: ColoresViewModel = viewModel()) {

    // Obtiene el portapapeles del sistema para copiar el código HEX
    val clipboard = LocalClipboardManager.current
    // Obtiene el modo actual (HSV o RGB)
    val modo = viewModel.modo
    // Crea un color basado en los valores HSV del ViewModel
    val color = Color.hsv(viewModel.hue, viewModel.saturation, viewModel.value, viewModel.alpha)
    // Crea el valor HEX incluyendo Alpha, Rojo, Verde y Azul
    val hex = String.format(
        "#%02X%02X%02X%02X",
        (viewModel.alpha * 255).toInt(),
        viewModel.red.toInt(),
        viewModel.green.toInt(),
        viewModel.blue.toInt()
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Muestra el título principal de la pantalla
            Text(
                "Selector de colores",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            // Fila con el cuadro del color y los valores numéricos
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Cuadro que muestra el color actual
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(color)
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Columna para los valores HSV o RGB según el modo
                Column {
                    if (modo == "HSV") {
                        Text("Hue: ${viewModel.hue.toInt()}",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge)
                        Text("Saturation: ${"%.3f".format(viewModel.saturation)}",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge)
                        Text("Value: ${"%.3f".format(viewModel.value)}",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge)
                        Text("Alpha: ${"%.3f".format(viewModel.alpha)}",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge)
                    } else {
                        Text("Red: ${viewModel.red.toInt()}",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge)
                        Text("Green: ${viewModel.green.toInt()}",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge)
                        Text("Blue: ${viewModel.blue.toInt()}",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge)
                        Text("Alpha: ${(viewModel.alpha * 255).toInt()}",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge)
                    }

                    Spacer(Modifier.height(8.dp))

                    // Fila con el valor HEX y botón para copiarlo
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(hex,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge)

                        IconButton(onClick = {
                            clipboard.setText(AnnotatedString(hex))
                        }) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copiar")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón para cambiar entre modo HSV y RGB
            Button(onClick = {
                if(modo == "HSV") {
                    viewModel.cambiarModo("RGB")
                } else {
                    viewModel.cambiarModo("HSV")
                }
            }) { Text(modo,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium) }

            Spacer(modifier = Modifier.height(20.dp))

            // Si el modo es HSV, muestra sliders de HSV
            if (modo == "HSV") {
                SliderColor(
                    "Hue",
                    viewModel.hue,
                    0f..360f,
                    { viewModel.actualizarHSV(h = it) },
                    HueBrush()
                )
                SliderColor(
                    "Saturation",
                    viewModel.saturation,
                    0f..1f,
                    { viewModel.actualizarHSV(s = it) },
                    SaturationBrush(viewModel.hue)
                )
                SliderColor(
                    "Value",
                    viewModel.value,
                    0f..1f,
                    { viewModel.actualizarHSV(v = it) },
                    ValueBrush()
                )
                SliderColor(
                    "Alpha",
                    viewModel.alpha,
                    0f..1f,
                    { viewModel.actualizarAlpha(it) },
                    Brush.horizontalGradient(listOf(Color.Transparent, Color.White))
                )
            } else {
                // Si el modo es RGB, muestra sliders de RGB
                SliderColor(
                    "Red",
                    viewModel.red,
                    0f..255f,
                    { viewModel.actualizarRGB(r = it) },
                    RGBBrush(0f)
                )
                SliderColor(
                    "Green",
                    viewModel.green,
                    0f..255f,
                    { viewModel.actualizarRGB(g = it) },
                    RGBBrush(120f)
                )
                SliderColor(
                    "Blue",
                    viewModel.blue,
                    0f..255f,
                    { viewModel.actualizarRGB(b = it) },
                    RGBBrush(240f)
                )
                SliderColor(
                    "Alpha",
                    viewModel.alpha,
                    0f..1f,
                    { viewModel.actualizarAlpha(it) },
                    Brush.horizontalGradient(listOf(Color.Transparent, Color.White))
                )
            }
        }
    }
}

// Composable para mostrar un slider y un campo de texto
@Composable
fun SliderColor(
    nombre: String,
    valor: Float, // Valor actual
    rango: ClosedFloatingPointRange<Float>, // Rango permitido
    onValueChange: (Float) -> Unit,
    brush: Brush // Fondo degradado del slider
) {
    // Estado local para el campo de texto
    var texto by remember { mutableStateOf(valor.toString()) }

    // Campo de texto editable
    OutlinedTextField(
        value = texto,
        onValueChange = { nuevo ->
            texto = nuevo
            nuevo.toFloatOrNull()?.let { numero ->
                if (numero in rango) {
                    onValueChange(numero)
                }
            }
        },
        label = { Text(nombre) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
        singleLine = true
    )

    // Slider deslizante para cambiar el valor
    Slider(
        value = valor,
        onValueChange = { nuevo ->
            texto = nuevo.toString()
            onValueChange(nuevo)
        },
        valueRange = rango,
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(brush)
    )
    Spacer(Modifier.height(10.dp))
}

// Degradado para Hue (matiz)
@Composable
fun HueBrush(): Brush = Brush.horizontalGradient(
    listOf(Color.Red, Color.Yellow, Color.Green, Color.Cyan, Color.Blue, Color.Magenta, Color.Red)
)

// Degradado para Saturation según el hue actual
@Composable
fun SaturationBrush(hue: Float): Brush = Brush.horizontalGradient(
    listOf(Color.hsv(hue, 0f, 1f), Color.hsv(hue, 1f, 1f))
)

// Degradado simple de negro a blanco
@Composable
fun ValueBrush(): Brush = Brush.horizontalGradient(
    listOf(Color.Black, Color.White)
)

// Degradado RGB utilizando offset
@Composable
fun RGBBrush(offset: Float): Brush = Brush.horizontalGradient(
    listOf(Color.hsv(offset, 1f, 0f), Color.hsv(offset, 1f, 1f))
)
