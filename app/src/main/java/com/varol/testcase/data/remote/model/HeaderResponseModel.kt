package com.varol.testcase.data.remote.model

import com.varol.testcase.internal.extension.EMPTY

data class HeaderResponseModel(
    val headerTitle : String? = String.EMPTY,
    val headerDescription : String? = String.EMPTY
)