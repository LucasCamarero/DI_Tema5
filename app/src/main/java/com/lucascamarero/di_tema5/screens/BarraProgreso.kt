package com.lucascamarero.di_tema5.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun BarraProgreso() {

    var ejecutando by remember { mutableStateOf(false) }
    var progreso by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

            Text("Barra de Progreso",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.padding(10.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.padding(12.dp))

            Button(
                enabled = !ejecutando,
                onClick = {
                    progreso = 0f
                    ejecutando = true

                    scope.launch {

                        // Simulación de una tarea que avanza poco a poco
                        while (progreso < 1f) {
                            delay(300)  // tiempo entre cada avance
                            progreso += 0.1f       // sube el 10% cada vez
                        }

                        ejecutando = false
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Iniciar tarea",
                    style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.height(30.dp))

            // La barra aparece solo cuando la tarea está en marcha
            if (ejecutando) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("${(progreso * 100).toInt()}%")
                    Spacer(modifier = Modifier.height(10.dp))
                    LinearProgressIndicator(
                        progress = progreso,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
