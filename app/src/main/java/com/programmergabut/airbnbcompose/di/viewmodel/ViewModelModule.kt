package com.programmergabut.airbnbcompose.di.viewmodel

import com.programmergabut.airbnbcompose.ui.PlacesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PlacesViewModel(get()) }
}