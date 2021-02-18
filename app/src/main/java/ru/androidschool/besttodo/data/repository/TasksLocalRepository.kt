package ru.androidschool.besttodo.data.repository

import ru.androidschool.besttodo.data.model.Task
import ru.androidschool.besttodo.data.model.TaskCategory
import ru.androidschool.besttodo.domain.repository.TaskRepository

class TasksLocalRepository : TaskRepository {

    override fun getAllTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        for (i in 0..10) {
            tasks.add(
                Task(
                    "Новая задача $i",
                    i / 2 == 0,
                    TaskCategory.values()[(0 until 4).random()]
                )
            )
        }

        return tasks
    }
}