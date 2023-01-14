package com.programmergabut.airbnbcompose.di.network

import com.programmergabut.airbnbcompose.repository.TestRepository
import com.programmergabut.airbnbcompose.repository.TestRepositoryImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.dsl.module


val networkModule = module {
    single<TestRepository> { TestRepositoryImpl(httpClient) }
}

val httpClient = HttpClient(Android) {
    install(Logging) {
        level = LogLevel.ALL
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}
