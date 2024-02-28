package com.codercampy.stopwatch

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val name: String,
    val age: Int
) : Parcelable