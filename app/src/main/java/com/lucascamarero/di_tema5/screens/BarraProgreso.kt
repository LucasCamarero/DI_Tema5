package com.lucascamarero.di_tema5.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lucascamarero.di_tema5.viewmodels.ProgresoViewModel

// Composable que muestra una barra de progreso circular y lineal con un botón para iniciar la animación
@Composable
fun BarraProgreso(progressViewModel: ProgresoViewModel) {

    // Observa el LiveData de progreso desde el ViewModel, con valor inicial 0f
    val progress: Float by progressViewModel.progress.observeAsState(initial = 0f)

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
                "Barra de Progreso",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            // Indicador de progreso circular que refleja el valor de progress
            CircularProgressIndicator(
                progress = {progress}
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Indicador de progreso lineal que refleja el valor de progress
            LinearProgressIndicator(
                progress = {progress}
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Botón que inicia la animación de progreso en el ViewModel
            Button(
                onClick = {
                    progressViewModel.startProgress()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    "Iniciar progreso",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}