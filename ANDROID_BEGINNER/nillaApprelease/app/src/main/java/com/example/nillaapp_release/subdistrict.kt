package com.example.nillaapp_release

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class subdistrict(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
