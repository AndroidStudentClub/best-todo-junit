package ru.androidschool.besttodo.domain.repository

import androidx.lifecycle.LiveData
import ru.androidschool.besttodo.data.model.Task

interface TaskRepository {

    fun getAllTasks(): LiveData<List<Task>>
    fun insert(task: Task)
    fun update(task: Task)
    fun delete(task: Task)
}