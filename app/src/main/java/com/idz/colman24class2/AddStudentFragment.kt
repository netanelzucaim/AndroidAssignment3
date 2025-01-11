package com.idz.colman24class2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student
import java.util.Calendar
import java.util.Locale


class AddStudentFragment : Fragment() {
    var saveButton: Button? = null
    var cancelButton: Button? = null
    var nameEditText: EditText? = null
    var idEditText: EditText? = null
    var savedTextField: TextView? = null
    var phoneEditText: EditText? = null
    var addressEditText: EditText? = null
    var enabledCheckBox: CheckBox? = null
    var dateOfBirthEditText: EditText? = null
    var timeOfBirthEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_student, container, false)
        setupView(view)
        saveButton?.setOnClickListener(::onSaveClicked)
        cancelButton?.setOnClickListener(::onCancelClick)
        dateOfBirthEditText?.setOnClickListener {
            showDatePickerDialog()
        }
        timeOfBirthEditText?.setOnClickListener {
            showTimePickerDialog()
        }
        return view
    }
    private fun setupView(view: View?) {
        saveButton = view?.findViewById(R.id.add_student_save_button)
        cancelButton = view?.findViewById(R.id.add_student_cancel_button)
        nameEditText = view?.findViewById(R.id.add_student_name_edit_text)
        idEditText = view?.findViewById(R.id.add_student_id_edit_text)
        savedTextField = view?.findViewById(R.id.add_student_save_message_text_view)
        phoneEditText = view?.findViewById(R.id.add_student_phone_edit_text)
        addressEditText = view?.findViewById(R.id.add_student_address_edit_text)
        enabledCheckBox = view?.findViewById(R.id.add_student_enabled_check_box)
        dateOfBirthEditText = view?.findViewById(R.id.add_student_date_of_birth)
        timeOfBirthEditText = view?.findViewById(R.id.add_student_time_of_birth)
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                dateOfBirthEditText?.setText(selectedDate)
            },
            year, month, dayOfMonth
        )
        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val calendar = java.util.Calendar.getInstance()
        val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)  // Get the current hour in 24-hour format
        val minute = calendar.get(java.util.Calendar.MINUTE)  // Get the current minute

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, selectedHour, selectedMinute ->
                // Format the time in 12-hour format with AM/PM
                val formattedTime = formatTimeWithAmPm(selectedHour, selectedMinute)
                timeOfBirthEditText?.setText(formattedTime)  // Set the formatted time to the EditText
            },
            hour, minute, false  // `false` for 12-hour format (AM/PM)
        )
        timePickerDialog.show()
    }

    private fun formatTimeWithAmPm(hour: Int, minute: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)

        val sdf = java.text.SimpleDateFormat("hh:mm a", Locale.getDefault())
        return sdf.format(calendar.time)
    }

    private fun onSaveClicked(view: View) {
        val student = Student(nameEditText?.text.toString(), idEditText?.text.toString(),phoneEditText?.text.toString(),addressEditText?.text.toString(),enabledCheckBox!!.isChecked, dateOfBirthEditText?.text.toString(), timeOfBirthEditText?.text.toString() )
        Model.shared.students.add(student)

        val alertDialog = android.app.AlertDialog.Builder(requireContext())
            .setTitle("Student Saved")
            .setMessage("${nameEditText?.text} ${idEditText?.text} has been saved!")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()  // Dismiss the dialog when OK is pressed
            }
            .create()

        // Show the dialog
        alertDialog.show()

        // Use a Handler to wait a few seconds before navigating to the previous page
        val handler = android.os.Handler()
        handler.postDelayed({
            // Navigate to the previous fragment (back to the previous page)
            Navigation.findNavController(view).popBackStack()
        }, 2000)
    }
    private fun onCancelClick(view: View) {
        Navigation.findNavController(view).popBackStack()
    }

}