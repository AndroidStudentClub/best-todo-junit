package ru.androidschool.besttodo.presentation.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.androidschool.besttodo.domain.repository.TaskRepository

class TasksListViewModelFactory(
    private val repository: TaskRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TasksListViewModel(application, repository) as T
    }
}