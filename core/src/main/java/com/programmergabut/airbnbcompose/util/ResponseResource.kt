package com.programmergabut.airbnbcompose.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Response resource
 * This class is used as a data wrapper to help passing data
 * between business layers with knowing it's state.
 * @param T
 * @constructor Create empty Response resource
 */
sealed class ResponseResource<T> {
    data class Success<T>(val data: T) : ResponseResource<T>()
    data class Error<T>(val errorMessage: T) : ResponseResource<T>()
}


fun <T> setSuccess(data: T): ResponseResource.Success<T> =ResponseResource.Success(data)

fun <T> setError(data: T) = ResponseResource.Error(data)