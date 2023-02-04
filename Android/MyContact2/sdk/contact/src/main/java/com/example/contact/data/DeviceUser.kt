package com.example.contact.data

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

internal data class DeviceUser(
    var id: String = "",
    var name: String? = null,
    var lookUpKey: String? = null,
    var thumbnailPhotoUri: String? = null,
    var photoUri: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(lookUpKey)
        parcel.writeString(thumbnailPhotoUri)
        parcel.writeString(photoUri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DeviceUser> {
        override fun createFromParcel(parcel: Parcel): DeviceUser {
            return DeviceUser(parcel)
        }

        override fun newArray(size: Int): Array<DeviceUser?> {
            return arrayOfNulls(size)
        }
    }
}
