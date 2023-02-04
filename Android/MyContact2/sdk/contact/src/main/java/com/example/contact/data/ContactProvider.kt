package com.example.contact.data

import android.os.Parcel
import android.os.Parcelable

internal data class ContactProvider(
    val contactId: String = "",
    val name: String? = null,
    val photoUri: String? = null,
    val phoneNumbers: List<String>? = listOf(),
    val emails: List<String>? = listOf()
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(contactId)
        parcel.writeString(name)
        parcel.writeString(photoUri)
        parcel.writeStringList(phoneNumbers)
        parcel.writeStringList(emails)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContactProvider> {
        override fun createFromParcel(parcel: Parcel): ContactProvider {
            return ContactProvider(parcel)
        }

        override fun newArray(size: Int): Array<ContactProvider?> {
            return arrayOfNulls(size)
        }
    }
}