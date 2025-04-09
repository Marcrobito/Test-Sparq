package com.teamsparq.test.data

sealed class NetworkResponse<out T> {
    data object NotInitialized : NetworkResponse<Nothing>()
    data object Loading : NetworkResponse<Nothing>()
    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Error(val error: Exception) : NetworkResponse<Nothing>()
}