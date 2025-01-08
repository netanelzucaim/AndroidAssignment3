package com.idz.colman24class2

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


class AddStudentFragment : Fragment() {
    var saveButton: Button? = null
    var cancelButton: Button? = null
    var nameEditText: EditText? = null
    var idEditText: EditText? = null
    var savedTextField: TextView? = null
    var phoneEditText: EditText? = null
    var addressEditText: EditText? = null
    var enabledCheckBox: CheckBox? = null

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
    }
    private fun onSaveClicked(view: View) {
        val student = Student(nameEditText?.text.toString(), idEditText?.text.toString(),phoneEditText?.text.toString(),addressEditText?.text.toString(),enabledCheckBox!!.isChecked )
        Model.shared.students.add(student)
        savedTextField?.text = "${nameEditText?.text} ${idEditText?.text} is saved...!!!"
    }
    private fun onCancelClick(view: View) {
        Navigation.findNavController(view).popBackStack()
    }

}