package com.lucascamarero.di_tema5.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lucascamarero.di_tema5.R

// Composable que muestra un CUADRO DE DIÁLOGO para salir de la app
@Composable
fun CuadroDialogo(navController: NavController) {

    // Obtiene el contexto actual de la app
    val context = LocalContext.current
    // Convierte el contexto a Activity si es posible (para poder cerrarla)
    val activity = context as? Activity

    // Estado que controla si se muestra el AlertDialog, inicializado en true
    var mostrarDialogo by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Título de la sección
            Text(
                "Cuadro de Diálogo",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            // boton para abrir el cuadro de diálogo
            Button(onClick = {
                mostrarDialogo = true
            },
                modifier = Modifier.padding(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )) {
                Text("Abrir Cuadro de Diálogo",
                    style = MaterialTheme.typography.bodyMedium)
            }

            // Imagen de resources para demostrar que el cuadro de diálogo
            // sale sobre la imagen
            Image(
                painter = painterResource(id = R.drawable.imagen1),
                contentDescription = "Imagen local",
                contentScale = ContentScale.Crop, // Ajusta cómo se escala la imagen
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )

            // Si mostrarDialogo es true, se muestra el AlertDialog
            if (mostrarDialogo) {

                AlertDialog(
                    // Acción al intentar cerrar el diálogo tocando fuera
                    onDismissRequest = {
                        mostrarDialogo = false
                    },
                    // Título del diálogo
                    title = {
                        Text(
                            text = "Salir de la app",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    // Mensaje dentro del diálogo
                    text = {
                        Text(
                            "¿Estás seguro de que deseas salir?",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 20.sp
                        )
                    },
                    // Botón de confirmación
                    confirmButton = {
                        TextButton(
                            onClick = {
                                // Cierra la actividad si existe
                                activity?.finish()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary,
                                contentColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                "Sí",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    },
                    // Botón de cancelación
                    dismissButton = {
                        TextButton(
                            onClick = {
                                // Oculta el diálogo
                                mostrarDialogo = false
                                // Navega hacia atrás
                                navController.popBackStack()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary,
                                contentColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                "No",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
