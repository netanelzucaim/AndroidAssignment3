package com.idz.colman24class2.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Random

class Model private constructor() {

    // The list of students is now of type ArrayList<Student>, where Student is Parcelable
    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    init {
        // Populate the students list with Parcelable Student objects
        for (i in 0..20) {
            val student = Student(
                name = "Name $i",
                id = "Student ID: $i",
                phone = "Phone: $i",
                address = "Address: $i",
                isChecked = false,
                dateOfBirth = generateRandomDateOfBirth()
            )
            students.add(student)
        }
    }

    private fun generateRandomDateOfBirth(): String {
        val random = Random()
        val year = 2000 + random.nextInt(11)
        val month = 1 + random.nextInt(12)
        val day = 1 + random.nextInt(28)
        val dateOfBirth = LocalDate.of(year, month, day)
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return dateOfBirth.format(formatter)
    }
}
