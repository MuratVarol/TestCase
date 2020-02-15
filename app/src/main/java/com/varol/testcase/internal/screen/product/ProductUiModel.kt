package com.varol.testcase.internal.screen.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductUiModel(
    val id: Long,
    val name : String,
    val imageUrl : String,
    val isAvailable : Boolean,
    val releaseDate : String,
    val description : String,
    val longDescription : String,
    val rating : Float,
    val price: String,
    var isFavorite : Boolean = false
):Parcelable