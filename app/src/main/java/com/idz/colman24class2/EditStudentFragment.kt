package com.idz.colman24class2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student

class EditStudentFragment : Fragment() {
    var student: Student? = null
    var saveButton: Button? = null
    var cancelButton: Button? = null
    var deleteButton: Button? = null
    var nameEditText: EditText? = null
    var idEditText: EditText? = null
    var savedTextField: TextView? = null
    var phoneEditText: EditText? = null
    var addressEditText: EditText? = null
    var enabledCheckBox: CheckBox? = null
    var dateOfBirthEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = StudentDetailsFragmentArgs.fromBundle(requireArguments())
        student = args.student
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_student, container, false)
        setupView(view)
        saveButton?.setOnClickListener(::onSaveClicked)
        cancelButton?.setOnClickListener(::onCancelClick)
        deleteButton?.setOnClickListener(::onDeleteClicked)

        dateOfBirthEditText?.setOnClickListener {
            showDatePickerDialog()
        }
        return view
    }

    private fun onSaveClicked(view: View) {
    val updatedStudent = Student(
        nameEditText?.text.toString(),
        idEditText?.text.toString(),
        phoneEditText?.text.toString(),
        addressEditText?.text.toString(),
        enabledCheckBox?.isChecked ?: false,
        dateOfBirthEditText?.text.toString()
    )

    val studentIndex = Model.shared.students.indexOfFirst { it.id == student?.id }
    if (studentIndex != -1) {
        Model.shared.students[studentIndex] = updatedStudent
    }
    Navigation.findNavController(view).popBackStack()
    Navigation.findNavController(view).popBackStack()

}
    private fun onCancelClick(view: View) {
        Navigation.findNavController(view).popBackStack()
        Navigation.findNavController(view).popBackStack()

    }
    private fun onDeleteClicked(view: View){
        Model.shared.students.remove(student)
        Navigation.findNavController(view).popBackStack()
        Navigation.findNavController(view).popBackStack()
    }

    private fun showDatePickerDialog() {
        val calendar = java.util.Calendar.getInstance()
        val year = calendar.get(java.util.Calendar.YEAR)
        val month = calendar.get(java.util.Calendar.MONTH)
        val dayOfMonth = calendar.get(java.util.Calendar.DAY_OF_MONTH)

        val datePickerDialog = android.app.DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                dateOfBirthEditText?.setText(selectedDate)
            },
            year, month, dayOfMonth
        )
        datePickerDialog.show()
    }

    private fun setupView(view: View?) {
        saveButton = view?.findViewById(R.id.edit_student_save_button)
        cancelButton = view?.findViewById(R.id.edit_student_cancel_button)
        deleteButton = view?.findViewById(R.id.edit_student_delete_button)
        nameEditText = view?.findViewById(R.id.edit_student_name_edit_text)
        idEditText = view?.findViewById(R.id.edit_student_id_edit_text)
        phoneEditText = view?.findViewById(R.id.edit_student_phone_edit_text)
        addressEditText = view?.findViewById(R.id.edit_student_address_edit_text)
        enabledCheckBox = view?.findViewById(R.id.edit_student_enabled_check_box)
        dateOfBirthEditText = view?.findViewById(R.id.edit_student_date_of_birth)
        nameEditText?.setText(student?.name)
        idEditText?.setText(student?.id)
        phoneEditText?.setText(student?.phone)
        addressEditText?.setText(student?.address)
        enabledCheckBox?.isChecked = student?.isChecked!!
        dateOfBirthEditText?.setText(student?.dateOfBirth)

    }

}