package com.idz.colman24class2.model
import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data public class Student(
    var name: String,
    var id: String,
    var phone: String,
    var address: String,
    var isChecked: Boolean,
    var dateOfBirth: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(id)
        parcel.writeString(phone)
        parcel.writeString(address)
        parcel.writeByte(if (isChecked) 1 else 0)
        parcel.writeString(dateOfBirth)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }
}