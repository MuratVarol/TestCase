package com.varol.testcase.di

import com.varol.testcase.data.remote.repository.ProductsRepository
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val repositoryModule: Module = module {
    single { ProductsRepository(get()) }
}
