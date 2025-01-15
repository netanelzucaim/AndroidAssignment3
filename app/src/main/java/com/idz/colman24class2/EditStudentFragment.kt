package com.idz.colman24class2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.idz.colman24class2.databinding.FragmentEditStudentBinding
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student

class EditStudentFragment : Fragment() {
    private var binding: FragmentEditStudentBinding? = null

    var student: Student? = null
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
//        val view = inflater.inflate(R.layout.fragment_edit_student, container, false)

//        return view
        binding = FragmentEditStudentBinding.inflate(inflater, container, false)
        binding?.cancelButton?.setOnClickListener(::onCancelClicked)
        binding?.saveButton?.setOnClickListener(::onSaveClicked)
        binding?.deleteButton?.setOnClickListener(::onDeleteClicked)
        setupView(binding?.root)
        return binding?.root

    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    private fun onSaveClicked(view: View) {
        binding?.progressBar?.visibility = View.VISIBLE
        setStudent()

        Model.shared.update(student!!) {
            binding?.progressBar?.visibility = View.GONE
            Navigation.findNavController(view).popBackStack()
            Navigation.findNavController(view).popBackStack()
        }
}
    private fun onCancelClicked(view: View) {
        Navigation.findNavController(view).popBackStack()
        Navigation.findNavController(view).popBackStack()

    }
    private fun onDeleteClicked(view: View){
        binding?.progressBar?.visibility = View.VISIBLE
        Model.shared.delete(student!!) {
            binding?.progressBar?.visibility = View.GONE
            Navigation.findNavController(view).popBackStack()
            Navigation.findNavController(view).popBackStack()
        }
    }
    private fun setupView(view: View?) {
        binding?.nameEditText?.setText(student?.name)
        binding?.idEditText?.setText(student?.id)
        binding?.phoneEditText?.setText(student?.phone)
        binding?.addressEditText?.setText(student?.address)
        binding?.enabledCheckBox?.isChecked = student?.isChecked!!
    }

    private fun setStudent() {

        student = Student(
            name = binding?.nameEditText?.text.toString(),
            id = binding?.idEditText?.text.toString(),
            phone = binding?.phoneEditText?.text.toString(),
            address = binding?.addressEditText?.text.toString(),
            isChecked = binding?.enabledCheckBox?.isChecked ?: false
        )
    }

}