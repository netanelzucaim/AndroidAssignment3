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
    companion object {
        val shared = Model()
    }

//    init {
//        // Populate the students list with Parcelable Student objects
//        for (i in 0..20) {
//            val student = Student(
//                name = "Name $i",
//                id = "Student ID: $i",
//                phone = "Phone: $i",
//                address = "Address: $i",
//                isChecked = false
//            )
//            students.add(student)
fun getAllStudents(callback: StudentsCallback) {
    executor.execute {
        val students = database.studentDao().getAllStudents()
        Thread.sleep(4000)
        mainHandler.post {
            callback(students)
        }
    }
}
    fun add(student: Student, callback: EmptyCallback) {
        executor.execute {
            database.studentDao().insertStudents(student)
            Thread.sleep(4000)
            mainHandler.post {
                callback()
            }
        }
    }
}
