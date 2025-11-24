package com.lucascamarero.di_tema5.models

// Declara una data class que representa un ítem de una lista de tareas
data class TodoItem(
    val texto: String,
    val checked: Boolean = false
)