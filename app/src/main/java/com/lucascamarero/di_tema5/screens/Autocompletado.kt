package com.lucascamarero.di_tema5.screens

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import android.Manifest
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Autocompletado(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar()
    }
}

//Autocompletado hecho con SearchBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    Column {
        var lista = listOf<String>("Hola", "Adios", "Coca cola")
        var listaSugerencias = remember { mutableListOf<String>() }
        var query by remember{mutableStateOf("")}
        var active by remember{mutableStateOf(false)}

        Column() {
            SearchBar(
                query = query,
                onQueryChange = {query = it
                    if (query.isNotBlank()) {
                        listaSugerencias.clear()
                        listaSugerencias.addAll(
                            lista.filter { it.contains(query, ignoreCase = true) }
                        )
                    } else {
                        listaSugerencias.clear()
                    }},
                onSearch = {active = false},
                active = active, // sirve para desplegar el menú
                onActiveChange = {active = it},
                placeholder = {Text("Buscar")}
            ) {
                listaSugerencias.forEach { texto ->
                    Text(
                        texto,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .clickable{query = texto
                                listaSugerencias.clear()},
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}