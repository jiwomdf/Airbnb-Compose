package com.programmergabut.airbnbcompose.di.repository

import com.programmergabut.airbnbcompose.repository.TestRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single {
        TestRepositoryImpl(get(), get())
    }
}