package com.idz.colman24class2
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.Navigation
import com.idz.colman24class2.model.Student

class StudentDetailsFragment : Fragment() {
    var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
//         student = arguments?.let { StudentDetailsFragmentArgs.fromBundle(it) }

        val args = StudentDetailsFragmentArgs.fromBundle(requireArguments())
        student = args.student
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_student_details, container, false)
        setupView(view)
        return view
    }

    private fun setupView(view: View) {
        view.findViewById<TextView>(R.id.students_details_name_text_view).text = student!!.name
        view.findViewById<TextView>(R.id.students_details_id_text_view).text = student!!.id
        view.findViewById<TextView>(R.id.students_details_phone_text_view).text = student!!.phone
        view.findViewById<TextView>(R.id.students_details_address_text_view).text = student!!.address
        view.findViewById<CheckBox>(R.id.students_details_enabled_check_box).isChecked = student!!.isChecked
        view.findViewById<TextView>(R.id.students_details_date_of_birth_text_view).text = student!!.dateOfBirth.toString()
        view.findViewById<TextView>(R.id.students_details_time_of_birth_text_view).text = student!!.timeOfBirth.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.editStudentFragment -> {
                student?.let {
                    val action = StudentDetailsFragmentDirections.actionStudentDetailsFragmentToEditStudentFragment(it)
                    Navigation.findNavController(requireView()).navigate(action)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
