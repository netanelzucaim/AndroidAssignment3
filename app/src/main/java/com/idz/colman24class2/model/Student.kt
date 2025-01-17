package com.idz.colman24class2.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey val id: String,
    val name: String,
    var phone: String,
    var address: String,
    var isChecked: Boolean
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(address)
        parcel.writeByte(if (isChecked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {

        const val ID_KEY = "id"
        const val NAME_KEY = "name"
        const val PHONE_KEY = "phone"
        const val ADDRESS_KEY = "address"
//        const val AVATAR_URL_KEY = "avatarUrl"
        const val IS_CHECKED_KEY = "isChecked"

        // Create a Student instance from a Map
        fun fromJSON(json: Map<String, Any>): Student {
            val id = json[ID_KEY] as? String ?: ""
            val name = json[NAME_KEY] as? String ?: ""
            val phone = json[PHONE_KEY] as? String ?: ""
            val address = json[ADDRESS_KEY] as? String ?: ""
//            val avatarUrl = json[AVATAR_URL_KEY] as? String ?: ""
            val isChecked = json[IS_CHECKED_KEY] as? Boolean ?: false

            // Creating the Student object
            return Student(
                id = id,
                name = name,
                phone = phone,  // You may want to change how avatarUrl is used
                address = address,  // Assuming address is empty in this case
                isChecked = isChecked
            )
        }

        // Parcelable.Creator implementation
        @JvmField
        val CREATOR: Parcelable.Creator<Student> = object : Parcelable.Creator<Student> {
            override fun createFromParcel(parcel: Parcel): Student {
                return Student(parcel)
            }

            override fun newArray(size: Int): Array<Student?> {
                return arrayOfNulls(size)
            }
        }
    }

    // Convert the Student object to a Map
    val json: Map<String, Any>
        get() = hashMapOf(
            ID_KEY to id,
            NAME_KEY to name,
            PHONE_KEY to phone,
            ADDRESS_KEY to address,
//            AVATAR_URL_KEY to phone,  // Assuming phone is used for avatarUrl here
            IS_CHECKED_KEY to isChecked
        )
}
