package com.varol.testcase.di

import com.varol.testcase.domain.GetProductsUseCase
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val useCaseModule: Module = module {
    single { GetProductsUseCase(get()) }

}