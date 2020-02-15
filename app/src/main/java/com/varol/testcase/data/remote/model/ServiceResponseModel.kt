package com.varol.testcase.data.remote.model

data class ServiceResponseModel(
    val header: HeaderResponseModel? = null,
    val filters: List<String?>? = null,
    val products: List<ProductsResponseModel?>? = null
)