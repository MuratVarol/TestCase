package com.varol.testcase.data.remote.service

import com.varol.testcase.data.remote.model.ServiceResponseModel
import io.reactivex.Single
import retrofit2.http.GET

interface Api {
    companion object {
        const val ALL_PRODUCTS = "products-test.json"
    }

    @GET(ALL_PRODUCTS)
    fun getAllFromService(
    ): Single<ServiceResponseModel>

}
