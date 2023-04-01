package com.programmergabut.airbnbcompose.di.repository

import com.programmergabut.airbnbcompose.repository.PlacesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single {
        PlacesRepositoryImpl(get(), get())
    }
}