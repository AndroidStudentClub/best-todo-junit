package ru.androidschool.besttodo.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.androidschool.besttodo.data.model.Task
import ru.androidschool.besttodo.domain.repository.TaskRepository

class TasksListViewModel(application: Application, private val repository: TaskRepository) :
    AndroidViewModel(application) {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    init {
        _dataLoading.value = true
    }

    fun insertTask(task: Task) {
        _dataLoading.postValue(true)
        viewModelScope.launch {
            repository.insert(task)
        }
        _dataLoading.postValue(false)
    }

    fun updateTask(task: Task) {
        _dataLoading.postValue(true)
        viewModelScope.launch {
            repository.update(task)
        }
        _dataLoading.postValue(false)
    }

    fun deleteTask(task: Task) {
        _dataLoading.postValue(true)
        viewModelScope.launch {
            repository.delete(task)
        }
        _dataLoading.postValue(false)
    }

    fun getAllTasks(): LiveData<List<Task>> {
        var tasks: LiveData<List<Task>>? = null
        _dataLoading.postValue(true)
        viewModelScope.launch {
            tasks = repository.getAllTasks()
        }
        _dataLoading.postValue(false)
        return tasks!!
    }
}