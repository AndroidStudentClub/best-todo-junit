package ru.androidschool.besttodo.presentation.create

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.androidschool.besttodo.R

class CreateTaskDialog(
    context: Context,
    private val onMerge: () -> Unit
) : BottomSheetDialog(context, R.style.TransparentBottomSheet) {

    override fun onCreate(savedInstanceState: Bundle?) {
        val contentView = View.inflate(context, R.layout.dialog_create_task, null)
        setContentView(contentView)

        val saveButton = contentView.findViewById<LinearLayout>(R.id.save_button)

        saveButton.setOnClickListener {
            onMerge.invoke()
            dismiss()
        }


        super.onCreate(savedInstanceState)
    }


    companion object {
        fun newInstance(
            context: Context,
            onMerge: () -> Unit
        ) {
            val dialog = CreateTaskDialog(context, onMerge)
            dialog.show()
        }
    }
}