package ru.androidschool.besttodo.data.model

data class Task(
    val title: String,
    val isChecked: Boolean,
    val category: TaskCategory
)
