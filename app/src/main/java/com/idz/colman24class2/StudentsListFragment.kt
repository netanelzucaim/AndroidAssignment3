package com.idz.colman24class2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idz.colman24class2.adapter.StudentsRecyclerAdapter
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student

interface OnItemClickListener {
    fun onItemClick(position: Int)
    fun onItemClick(student: Student?)
}

class StudentsListFragment : Fragment() {

    var students: MutableList<Student>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_students_list, container, false)

       students = Model.shared.students

        val recyclerView: RecyclerView = view.findViewById(R.id.students_list_fragment_recycler_view)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        val adapter = StudentsRecyclerAdapter(students)
        adapter.listener = object : OnItemClickListener{
            override fun onItemClick(position: Int) {
                Log.d("TAG", "On click Activity listener on position $position")
            }

            override fun onItemClick(student: Student?) {

                val action = StudentsListFragmentDirections.actionStudentsListFragmentToStudentDetailsFragment(student!!)
                Navigation.findNavController(view).navigate(action)

            }
        }
        recyclerView.adapter = adapter
        return view
    }


}