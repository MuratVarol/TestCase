package com.varol.testcase.application

import android.app.Application
import com.varol.testcase.di.*
import org.koin.android.ext.android.startKoin


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin(
            this,
            listOf(
                appModule,
                networkModule,
                repositoryModule,
                dataSourceModule,
                useCaseModule,
                viewModelModule
            )
        )
    }

}
