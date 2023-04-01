package com.programmergabut.airbnbcompose.di.network

import com.programmergabut.airbnbcompose.core.BuildConfig
import com.programmergabut.airbnbcompose.repository.PlacesRepository
import com.programmergabut.airbnbcompose.repository.PlacesRepositoryImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.dsl.module
import kotlinx.serialization.json.Json as JsonSerializer

val networkModule = module {
    single<PlacesRepository> { PlacesRepositoryImpl(
        httpClient,
        BuildConfig.CLIENT_ID
    ) }
}

val httpClient = HttpClient(Android) {
    install(Logging) {
        level = LogLevel.ALL
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer(JsonSerializer {
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}