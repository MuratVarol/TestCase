package com.varol.testcase.data.remote.model

import com.varol.testcase.internal.extension.EMPTY
import com.varol.testcase.internal.extension.ZERO

data class ProductsResponseModel(
    val name: String? = String.EMPTY,
    val type: String? = String.EMPTY,
    val id: Long? = Long.ZERO,
    val color: String? = String.EMPTY,
    val imageURL: String? = String.EMPTY,
    val available: Boolean? = false,
    val releaseDate: String?,
    val description: String? = String.EMPTY,
    val longDescription: String? = String.EMPTY,
    val rating: Float? = Float.ZERO,
    val price: Price? = null
)

data class Price(
    val value: Double = Double.ZERO,
    val currency: String? = String.EMPTY
)