package com.lucascamarero.di_tema5.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lucascamarero.di_tema5.viewmodels.DireccionesViewModel

// Composable que muestra la dirección en distintos formatos y permite seleccionar el formato con RadioButtons
@Composable
fun FormateadorDirecciones(direccionesViewModel: DireccionesViewModel) {

    // Observa el formato seleccionado desde el ViewModel (state de Compose)
    val selectedFormat by remember { direccionesViewModel::selectedFormat }

    // Obtiene la dirección formateada según el formato seleccionado
    val formattedAddress = direccionesViewModel.getFormattedAddress()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            // Título de la sección
            Text(
                "Formateador de direcciones",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            // Muestra la dirección ya formateada según el formato seleccionado
            Text(
                formattedAddress,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            // Column con RadioButtons para seleccionar el formato
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                RadioOption(
                    label = "Original",
                    selected = selectedFormat == DireccionesViewModel.FormatType.ORIGINAL,
                    onSelect = { direccionesViewModel.onFormatSelected(DireccionesViewModel.FormatType.ORIGINAL) }
                )

                RadioOption(
                    label = "Una línea",
                    selected = selectedFormat == DireccionesViewModel.FormatType.ONE_LINE,
                    onSelect = { direccionesViewModel.onFormatSelected(DireccionesViewModel.FormatType.ONE_LINE) }
                )

                RadioOption(
                    label = "Multilínea",
                    selected = selectedFormat == DireccionesViewModel.FormatType.MULTILINE,
                    onSelect = { direccionesViewModel.onFormatSelected(DireccionesViewModel.FormatType.MULTILINE) }
                )
            }
        }
    }
}

// Composable que representa una opción de RadioButton con etiqueta
@Composable
fun RadioOption(label: String, selected: Boolean, onSelect: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // RadioButton que refleja si está seleccionado y llama a onSelect al hacer clic
        RadioButton(
            selected = selected,
            onClick = onSelect
        )
        // Texto que acompaña al RadioButton
        Text(
            label,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
