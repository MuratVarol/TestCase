package com.varol.testcase.di

import com.varol.testcase.data.remote.datasource.ProductsDataSource
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val dataSourceModule: Module = module {
    single { ProductsDataSource(get()) }
}
