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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FormateadorDirecciones() {

    val originalAddress = """
        1234 Calle Falsa, Piso 5, Ciudad Ejemplo, País Ficticio, 12345
    """.trimIndent()

    var selectedOption by remember { mutableStateOf("Original") }

    // Formateo según selección:
    val formattedAddress = when (selectedOption) {
        "Original" -> originalAddress
        "Una línea" -> originalAddress.replace(",", " -")
        "Multilínea" -> originalAddress
            .replace(",", "\n")
        else -> originalAddress
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Text(formattedAddress,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleSmall)

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            // Radio Buttons
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                RadioOption(
                    label = "Original",
                    selected = selectedOption == "Original",
                    onSelect = { selectedOption = it }
                )

                RadioOption(
                    label = "Una línea",
                    selected = selectedOption == "Una línea",
                    onSelect = { selectedOption = it }
                )

                RadioOption(
                    label = "Multilínea",
                    selected = selectedOption == "Multilínea",
                    onSelect = { selectedOption = it }
                )
            }
        }
    }
}

@Composable
fun RadioOption(label: String, selected: Boolean, onSelect: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        RadioButton(selected = selected, onClick = { onSelect(label) })
        Text(label,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium)
    }
}