package com.varol.testcase.internal.screen.product

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.varol.testcase.base.BaseAndroidViewModel
import com.varol.testcase.domain.GetProductsUseCase
import com.varol.testcase.internal.screen.product.product_list.ProductListDirections

class ProductsViewModel(
    context: Context,
    private val getProducts: GetProductsUseCase
): BaseAndroidViewModel(context){

    val selectedProducts = MutableLiveData<List<ProductUiModel>>()
    private val fetchedProducts = MutableLiveData<List<ProductUiModel>>()
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
                        selectedProducts.postValue(emptyList())
                    }
                    products.either(::handleFailure, ::handleSuccessFetchProducts)
                }
        )
    }

    fun getFavorites() {
        selectedProducts.postValue(
            fetchedProducts.value?.filter {
                it.isFavorite
            }
        )
    }

    fun getAll() {
        selectedProducts.postValue(fetchedProducts.value)
    }

    fun getAvailables() {
        selectedProducts.postValue(
            fetchedProducts.value?.filter {
                it.isAvailable
            }
        )
    }

    private fun handleSuccessFetchProducts(products: List<ProductUiModel>?) {
        products?.let { products->
            isEmptyResult.postValue(false)
            fetchedProducts.postValue(products)
            selectedProducts.postValue(products)
        }?:run {
            isEmptyResult.postValue(true)
            fetchedProducts.postValue(emptyList())
            selectedProducts.postValue(emptyList())
        }
    }

    fun handleProductSelect(product: ProductUiModel){
        navigate(ProductListDirections().toDetailPage(product))
    }

}