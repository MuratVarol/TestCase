package com.varol.testcase.data.remote.repository

import com.varol.testcase.data.remote.datasource.ProductsDataSource
import com.varol.testcase.data.remote.model.ProductsResponseModel
import com.varol.testcase.internal.extension.EMPTY
import com.varol.testcase.internal.extension.ZERO
import com.varol.testcase.internal.extension.emptyIfNull
import com.varol.testcase.internal.extension.toFormattedDate
import com.varol.testcase.internal.screen.product.ProductUiModel
import com.varol.testcase.internal.util.Failure
import com.varol.testcase.internal.util.functional.Either
import io.reactivex.Single

class ProductsRepository(private val productsDataSource: ProductsDataSource) {

    fun getProducts(
    ): Single<Either<Failure, List<ProductUiModel>?>> {
        val result = productsDataSource.getAllFromService()
        return result.map { response ->
            response.transform {
                it.products.toUiModel()
            }
        }
    }


    private fun List<ProductsResponseModel?>?.toUiModel(): List<ProductUiModel>? {
        return this?.filterNotNull()?.map {
            with(it) {
                ProductUiModel(
                    id = id ?: Long.ZERO,
                    name = name.emptyIfNull(),
                    imageUrl = imageURL.emptyIfNull(),
                    isAvailable = available ?: false,
                    releaseDate = releaseDate
                        ?.toLongOrNull()
                        ?.toFormattedDate()
                        .emptyIfNull(),
                    description = description.emptyIfNull(),
                    longDescription = longDescription.emptyIfNull(),
                    rating = rating ?: Float.ZERO,
                    price = price?.let { price ->
                        price.value.toString() + " " +
                                price.currency
                                    ?.toLowerCase()
                                    ?.capitalize()
                    } ?: run {
                        String.EMPTY
                    }
                )
            }
        }
    }

}