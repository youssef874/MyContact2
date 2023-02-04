package com.example.contact.repository.data

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

data class Contact(
    var id: String = "",
    var name: String? = null,
    var photoUri: Uri? = null,
    var phoneNumbers: MutableList<String> = mutableListOf(),
    var emails: MutableList<String> = mutableListOf()
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readParcelable(Uri::class.java.classLoader),
        parcel.createStringArrayList() as ArrayList,
        parcel.createStringArrayList() as ArrayList
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeParcelable(photoUri, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }
}
