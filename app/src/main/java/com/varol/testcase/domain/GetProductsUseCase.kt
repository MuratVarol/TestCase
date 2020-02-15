package com.varol.testcase.domain

import com.varol.testcase.data.remote.repository.ProductsRepository
import com.varol.testcase.internal.screen.product.ProductUiModel
import com.varol.testcase.internal.util.Failure
import com.varol.testcase.internal.util.functional.Either
import io.reactivex.Single

class GetProductsUseCase(
    private val repository: ProductsRepository
) {
    fun getProducts(): Single<Either<Failure, List<ProductUiModel>?>> {
        return repository.getProducts()
    }
}