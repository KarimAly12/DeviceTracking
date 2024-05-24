package com.example.devicetracking.core.domain.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi


data class ChildLocationObject(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChildLocationObject> {
        override fun createFromParcel(parcel: Parcel): ChildLocationObject {
            return ChildLocationObject(parcel)
        }

        override fun newArray(size: Int): Array<ChildLocationObject?> {
            return arrayOfNulls(size)
        }
    }

}
data class ChildObject(
    var email:String = "",
    val firstName: String = "",
    val lastName: String = "",
    var childLocationObject: ChildLocationObject = ChildLocationObject(),
    val inTrip: Boolean = false
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readParcelable(ChildLocationObject::class.java.classLoader, ChildLocationObject::class.java)!!,
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeParcelable(childLocationObject, flags)
        parcel.writeByte(if (inTrip) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChildObject> {
        override fun createFromParcel(parcel: Parcel): ChildObject {
            return ChildObject(parcel)
        }

        override fun newArray(size: Int): Array<ChildObject?> {
            return arrayOfNulls(size)
        }
    }
}
