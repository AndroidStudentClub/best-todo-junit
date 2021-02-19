package ru.androidschool.besttodo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.androidschool.besttodo.data.model.Task
import ru.androidschool.besttodo.data.model.TaskCategory
import java.util.concurrent.Executors

const val DATABASE_VERSION_CODE = 1

@TypeConverters(TaskCategoryConverter::class)
@Database(entities = [Task::class], version = DATABASE_VERSION_CODE, exportSchema = true)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase? {
            if (INSTANCE == null) {
                synchronized(TaskDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        TaskDatabase::class.java, "best_todo_database"
                    )
                        .allowMainThreadQueries()
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Executors.newSingleThreadExecutor().execute {
                                    for (task in PREPOPULATE_DATA) {
                                        getInstance(context)!!.taskDao().insertTask(task)
                                    }
                                }
                            }
                        })
                        .build()
                }
            }
            return INSTANCE
        }

        val PREPOPULATE_DATA = listOf(
            Task(
                1,
                "Прочитать книгу Рефакторинг",
                false,
                category = TaskCategory.STUDY
            ),
            Task(
                2,
                "Купить авиабилеты",
                false,
                category = TaskCategory.PERSONAL
            )
        )
    }
}