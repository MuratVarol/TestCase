package com.varol.testcase.data.remote.datasource

import com.varol.testcase.data.ServiceRequestHandler
import com.varol.testcase.data.remote.model.ServiceResponseModel
import com.varol.testcase.data.remote.service.Api
import com.varol.testcase.internal.util.Failure
import com.varol.testcase.internal.util.functional.Either
import io.reactivex.Single

typealias service = ServiceRequestHandler

class ProductsDataSource(private val api: Api) {

    fun getAllFromService(
    ): Single<Either<Failure, ServiceResponseModel>> {
        return service.sendRequest(
            api.getAllFromService()
        )

    }

}
