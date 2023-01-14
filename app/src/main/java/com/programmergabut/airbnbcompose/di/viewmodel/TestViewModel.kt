package com.programmergabut.airbnbcompose.di.viewmodel

import com.programmergabut.airbnbcompose.ui.TestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TestViewModel(get()) }
}