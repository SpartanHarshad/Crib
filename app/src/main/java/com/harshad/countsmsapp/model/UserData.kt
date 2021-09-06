package com.harshad.countsmsapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    val mobileNo: String = "",
    val noOfDays: String = "",
) : Parcelable
