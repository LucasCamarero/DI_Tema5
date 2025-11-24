package com.lucascamarero.di_tema5.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import com.lucascamarero.di_tema5.viewmodels.ListaViewModel
import com.lucascamarero.di_tema5.models.TodoItem

// Composable que muestra una lista de tareas con opción de agregar, marcar y eliminar
@Composable
fun TodoList(listaViewModel: ListaViewModel) {

    // Estado local para el texto del campo de entrada
    var texto by remember { mutableStateOf("") }

    // Observamos la lista de items desde el ViewModel
    val lista by listaViewModel.items.observeAsState(emptyList())

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
                "Todo List",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(18.dp))

            // Fila para el input de texto y botón agregar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Campo de texto para ingresar la tarea
                OutlinedTextField(
                    value = texto,
                    onValueChange = { nuevoTexto -> texto = nuevoTexto },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                        cursorColor = MaterialTheme.colorScheme.primary
                    )
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Botón para agregar el item a la lista
                Button(
                    onClick = {
                        if (texto.isNotBlank()) {
                            listaViewModel.addItem(texto)
                            texto = "" // Limpiamos el campo
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Agregar", style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(18.dp))
        }

        // Items de la lista: cada tarea se renderiza con CrearItem
        items(lista) { item ->
            CrearItem(
                item = item,
                onToggle = { listaViewModel.toggleChecked(item) }, // Cambiar estado checked
                onDelete = { listaViewModel.removeItem(item) }     // Eliminar si está marcado
            )
        }
    }
}

// Composable que representa un solo item de la lista con checkbox y botón de eliminar
@Composable
fun CrearItem(
    item: TodoItem,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Checkbox para marcar la tarea como completada
        Checkbox(
            checked = item.checked,
            onCheckedChange = { onToggle() },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.primary,
                checkmarkColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Texto de la tarea con line-through si está completada
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = item.texto,
                style = MaterialTheme.typography.bodyLarge.copy(
                    textDecoration = if (item.checked) {
                        TextDecoration.LineThrough
                    } else {
                        TextDecoration.None
                    }
                )
            )
        }

        // IconButton para eliminar la tarea (solo si está marcada)
        IconButton(
            onClick = {
                if (item.checked) {
                    onDelete()
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Eliminar",
                tint = if (item.checked)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                modifier = Modifier.size(25.dp)
            )
        }
    }
}