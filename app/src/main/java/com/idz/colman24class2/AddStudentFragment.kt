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


class AddStudentFragment : Fragment() {
    var saveButton: Button? = null
    var cancelButton: Button? = null
    var nameTextField: EditText? = null
    var idTextField: EditText? = null
    var savedTextField: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_student, container, false)

        return view
    }

}