package ru.androidschool.besttodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.androidschool.besttodo.data.repository.TasksLocalRepository
import ru.androidschool.besttodo.presentation.create.CreateTaskDialog
import ru.androidschool.besttodo.presentation.main.TasksAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var taskAdapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.tasks)

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            CreateTaskDialog.newInstance(this.requireContext()) {
                // TODO
            }
        }

        taskAdapter = TasksAdapter()
        taskAdapter.setTasks(TasksLocalRepository().getAllTasks())

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
            adapter = taskAdapter
        }

        if (taskAdapter.isEmpty()) {
            view.findViewById<ConstraintLayout>(R.id.empty_placeholder).visibility = View.VISIBLE
        } else {
            view.findViewById<ConstraintLayout>(R.id.empty_placeholder).visibility = View.GONE
        }
    }
}