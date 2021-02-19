package ru.androidschool.besttodo.data.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.androidschool.besttodo.data.database.TaskDao
import ru.androidschool.besttodo.data.database.TaskDatabase
import ru.androidschool.besttodo.data.model.Task
import ru.androidschool.besttodo.domain.repository.TaskRepository
import kotlin.coroutines.CoroutineContext

class TasksRepositoryImpl(context: Context) : TaskRepository, CoroutineScope {

    private val taskDao: TaskDao
    private val job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        val database = TaskDatabase.getInstance(context)
        taskDao = database!!.taskDao()
        job = Job()
    }

    override fun insert(task: Task) {
        launch(Dispatchers.IO) {
            taskDao.insertTask(task)
        }
    }

    override fun update(task: Task) {
        launch(Dispatchers.IO) { taskDao.updateTask(task) }
    }

    override fun delete(task: Task) {
        launch(Dispatchers.IO) { taskDao.deleteTask(task) }
    }

    override fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getTasks()
    }
}