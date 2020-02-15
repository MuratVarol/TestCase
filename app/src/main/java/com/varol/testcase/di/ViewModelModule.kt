package com.varol.testcase.di

import com.varol.testcase.internal.screen.main.MainViewModel
import com.varol.testcase.internal.screen.product.ProductsViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val viewModelModule: Module = module {
    viewModel { ProductsViewModel(get(), get()) }
    viewModel { MainViewModel(get()) }
}