package com.idz.colman24class2.model
import android.os.Looper
import androidx.core.os.HandlerCompat
import com.idz.colman24class2.model.dao.AppLocalDb
import com.idz.colman24class2.model.dao.AppLocalDbRepository
import java.util.concurrent.Executors
typealias StudentsCallback = (List<Student>) -> Unit
typealias EmptyCallback = () -> Unit
interface GetAllStudentsListener {
    fun onCompletion(students: List<Student>)
}
class Model private constructor() {

    // The list of students is now of type ArrayList<Student>, where Student is Parcelable
//    val students: MutableList<Student> = ArrayList()
    private val database: AppLocalDbRepository = AppLocalDb.database
    private val executor = Executors.newSingleThreadExecutor()
    private val mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())

    private val firebaseModel = FirebaseModel()

    companion object {
        val shared = Model()
    }

    fun getAllStudents(callback: StudentsCallback) {
        firebaseModel.getAllStudents(callback)
    }


    fun add(student: Student, callback: EmptyCallback) {
        firebaseModel.add(student, callback)
    }

    fun delete(student: Student, callback: EmptyCallback) {
        firebaseModel.delete(student, callback)
    }

    fun update(student: Student, callback: EmptyCallback) {
        firebaseModel.update(student, callback)
    }
 }





