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

@Composable
fun FormateadorDirecciones(direccionesViewModel: DireccionesViewModel) {

    val selectedFormat by remember { direccionesViewModel:: selectedFormat}
    val formattedAddress = direccionesViewModel.getFormattedAddress()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {

            Text("Formateador de direcciones",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            Text(formattedAddress,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            // Radio Buttons
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

@Composable
fun RadioOption(label: String, selected: Boolean, onSelect: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect
        )
        Text(label,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium)
    }
}