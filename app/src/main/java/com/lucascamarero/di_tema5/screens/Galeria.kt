package com.lucascamarero.di_tema5.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.lucascamarero.di_tema5.models.GalleryItem
import com.lucascamarero.di_tema5.viewmodels.GalleryViewModel

// Composable que muestra una galería de imágenes usando un ViewModel con StateFlow
@Composable
fun Galeria(viewModel: GalleryViewModel) {

    // Convierte el StateFlow del ViewModel a un estado observable en Compose
    val items = viewModel.galleryItems.collectAsState()

    // Estado local que determina el modo de visualización: true = grid de 2 columnas, false = 1 columna
    var isGrid by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Título de la sección
                Text(
                    "Galería",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge
                )

                // Botón para cambiar la vista de la galería
                IconButton(onClick = { isGrid = !isGrid }) {
                    Icon(
                        imageVector = if (isGrid) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropUp,
                        contentDescription = "Cambiar vista",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(100.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Items de la galería
        itemsIndexed(
            // Dividimos la lista de items en sublistas según el modo de vista
            // Si isGrid == true → filas de 2 elementos (grid de 2 columnas)
            // Si isGrid == false → filas de 1 elemento (vista lista)
            items.value.chunked(if (isGrid) 2 else 1)
        ) { _, rowItems ->  //'_' es el índice de la fila (no lo usamos), rowItems es la sublista de items de esta fila
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Iteramos sobre cada item de la fila
                for (item in rowItems) {
                    GalleryItemView(
                        item = item,
                        modifier = Modifier.weight(1f) // Cada elemento ocupa proporcionalmente el ancho disponible
                    )
                }
                // Si la fila tiene menos elementos que columnas, se agrega un Spacer para alinear
                if (rowItems.size < (if (isGrid) 2 else 1)) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

// Composable que representa un solo elemento de la galería con imagen y título
@Composable
fun GalleryItemView(item: GalleryItem, modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        // Imagen cargada de forma asíncrona con Coil
        Image(
            painter = rememberAsyncImagePainter(item.imageUrl),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Título de la imagen
        Text(text = item.title, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(20.dp))
    }
}