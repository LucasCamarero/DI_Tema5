package com.lucascamarero.di_tema5.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucascamarero.di_tema5.models.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Declara un ViewModel encargado de gestionar la lista de imágenes de la galería
class GalleryViewModel : ViewModel() {

    // StateFlow interno que mantiene la lista de elementos de galería
    private val _galleryItems = MutableStateFlow<List<GalleryItem>>(emptyList())
    // StateFlow público y de solo lectura para exponer la lista
    val galleryItems: StateFlow<List<GalleryItem>> = _galleryItems

    // Al crear el ViewModel, carga datos de ejemplo
    init {
        loadSampleData()
    }

    // Función privada que carga una lista de elementos de ejemplo
    private fun loadSampleData() {
        // Ejecuta la carga dentro del scope del ViewModel usando corrutinas
        viewModelScope.launch {
            // Asigna una lista de GalleryItem con fotos de internet
            _galleryItems.value = listOf(
                GalleryItem(1, "Rojo sobre azul", "https://images.pexels.com/photos/845434/pexels-photo-845434.jpeg"),
                GalleryItem(2, "Liz", "https://images.pexels.com/photos/415829/pexels-photo-415829.jpeg"),
                GalleryItem(3, "Montaña", "https://images.pexels.com/photos/417173/pexels-photo-417173.jpeg"),
                GalleryItem(4, "De espaldas", "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d"),
                GalleryItem(5, "Nubes", "https://images.unsplash.com/photo-1499346030926-9a72daac6c63"),
                GalleryItem(6, "Chica con gafas", "https://images.pexels.com/photos/1130626/pexels-photo-1130626.jpeg"),
                GalleryItem(7, "Chica sonriente", "https://images.pexels.com/photos/774909/pexels-photo-774909.jpeg"),
                GalleryItem(8, "La City", "https://images.pexels.com/photos/374870/pexels-photo-374870.jpeg")
            )
        }
    }
}