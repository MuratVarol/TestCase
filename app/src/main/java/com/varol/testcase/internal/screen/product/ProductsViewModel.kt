package com.varol.testcase.internal.screen.product

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.varol.testcase.base.BaseAndroidViewModel
import com.varol.testcase.domain.GetProductsUseCase
import com.varol.testcase.internal.extension.notifyDataChange
import com.varol.testcase.internal.screen.product.product_list.ProductListDirections
import com.varol.testcase.internal.util.SingleLiveData

class ProductsViewModel(
    context: Context,
    private val getProducts: GetProductsUseCase
): BaseAndroidViewModel(context){

    val allProducts = MutableLiveData<List<ProductUiModel>>()
    val availableProducts = MutableLiveData<List<ProductUiModel>>()
    val favoritedProducts = MutableLiveData<List<ProductUiModel>>()
    val isEmptyResult = MutableLiveData<Boolean>()

    init {
        fetchAllProducts()
    }

    fun fetchAllProducts(){
        showProgress()
        disposables.add(
            getProducts.getProducts()
                .subscribe { products ->
                    hideProgress()
                    products.ifLeft {
                        isEmptyResult.postValue(true)
                        allProducts.postValue(emptyList())
                    }
                    products.either(::handleFailure, ::handleSuccessFetchProducts)
                }
        )
    }

    private fun handleSuccessFetchProducts(products: List<ProductUiModel>?) {
        products?.let { products->
            isEmptyResult.postValue(false)
            allProducts.postValue(products)
        }?:run {
            isEmptyResult.postValue(true)
            allProducts.postValue(emptyList())
        }
    }

    fun handleProductSelect(product: ProductUiModel){
        navigate(ProductListDirections().toDetailPage(product))
    }

}