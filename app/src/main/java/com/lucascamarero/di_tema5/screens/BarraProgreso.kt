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

@Composable
fun BarraProgreso(progressViewModel: ProgresoViewModel) {

    var ejecutando by remember { mutableStateOf(false) }
    var progreso by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()
    val progress: Float by progressViewModel.progress.observeAsState(initial = 0f)

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

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            CircularProgressIndicator(
                progress = {progress}
            )

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = {progress}
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    progressViewModel.startProgress()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Iniciar progreso",
                    style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}