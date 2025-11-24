package com.lucascamarero.di_tema5.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucascamarero.di_tema5.models.TodoItem

// Declara un ViewModel encargado de gestionar una lista de tareas
class ListaViewModel : ViewModel() {

    // LiveData interna que mantiene la lista de elementos TodoItem
    private val _items = MutableLiveData<List<TodoItem>>()
    // LiveData pública de solo lectura para exponer la lista
    val items: LiveData<List<TodoItem>> = _items

    // Inicializa la lista como vacía al crear el ViewModel
    init {
        _items.value = emptyList()
    }

    // Agrega un nuevo ítem a la lista con el texto recibido
    fun addItem(texto: String) {
        val currentList = _items.value ?: emptyList()  // Obtiene la lista actual o una vacía si es null
        _items.value = currentList + TodoItem(texto)   // Añade un nuevo TodoItem al final
    }

    // Elimina el ítem recibido de la lista
    fun removeItem(item: TodoItem) {
        val currentList = _items.value ?: emptyList()  // Obtiene la lista actual
        _items.value = currentList - item              // Crea una nueva lista sin el ítem indicado
    }

    // Cambia el estado 'checked' del ítem recibido
    fun toggleChecked(item: TodoItem) {
        val currentList = _items.value ?: emptyList() // Obtiene la lista actual
        val newList = mutableListOf<TodoItem>()   // Nueva lista mutable

        for (i in currentList) {
            if (i == item)
                newList.add(i.copy(checked = !i.checked))   // Cambia checked
            else
                newList.add(i)                              // Deja el resto igual
        }

        _items.value = newList   // Asigna la nueva lista
    }
}