package com.example.nyam_project

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val title: String,
    val desc: String,
    val photo: Int,
    val additionalInfo: String,
    val recipeLink: String
): Parcelable
