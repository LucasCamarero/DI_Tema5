package com.lucascamarero.di_tema5.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucascamarero.di_tema5.models.TodoItem

class ListaViewModel : ViewModel() {

    private val _items = MutableLiveData<List<TodoItem>>()
    val items: LiveData<List<TodoItem>> = _items

    init {
        _items.value = emptyList()
    }

    fun addItem(texto: String) {
        val currentList = _items.value ?: emptyList()
        _items.value = currentList + TodoItem(texto)
    }

    fun removeItem(item: TodoItem) {
        val currentList = _items.value ?: emptyList()
        _items.value = currentList - item
    }

    fun toggleChecked(item: TodoItem) {
        val currentList = _items.value ?: emptyList()
        _items.value = currentList.map {
            if (it == item) it.copy(checked = !it.checked)
            else it
        }
    }
}
