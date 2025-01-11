package com.idz.colman24class2.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Random
import kotlin.random.Random as randomKotlin

class Model private constructor() {

    // The list of students is now of type ArrayList<Student>, where Student is Parcelable
    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    init {

        val names = arrayOf("Alice", "Bob", "Nati", "Charlie", "David", "Eva", "Noam")
        // Populate the students list with Parcelable Student objects
        for (i in 0..20) {
            val randomName = names[randomKotlin.nextInt(names.size)]
            val student = Student(
                name = randomName,
                id = (randomKotlin.nextInt(1, 101)).toString(),
                phone = "$i",
                address = "$i",
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
