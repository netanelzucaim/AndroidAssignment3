package com.idz.colman24class2
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation

class StudentDetailsFragment : Fragment() {
    var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
         name = arguments?.let { StudentDetailsFragmentArgs.fromBundle(it).studentName }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.editStudentFragment -> {
                name?.let {
                    val action = StudentDetailsFragmentDirections.actionStudentDetailsFragmentToEditStudentFragment(it)
                    Navigation.findNavController(requireView()).navigate(action)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_student_details, container, false)
        view.findViewById<TextView>(R.id.students_details_name_text_view).text = name
        return view
    }
}