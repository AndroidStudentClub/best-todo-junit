package ru.androidschool.besttodo.domain.repository

import ru.androidschool.besttodo.data.model.Task

interface TaskRepository {

    fun getAllTasks(): List<Task>
}