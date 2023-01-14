package com.programmergabut.airbnbcompose

import android.app.Application
import com.programmergabut.airbnbcompose.di.network.networkModule
import com.programmergabut.airbnbcompose.di.repository.repositoryModule
import com.programmergabut.airbnbcompose.di.viewmodel.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                viewModelModule,
                repositoryModule,
                networkModule
            )
        }
    }
}