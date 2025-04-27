package com.selva.myapplication.utils.network

sealed class Result<T> {
    data class Success<T>(val data: T?): Result<T>()
    data class Error<T>(val message: String): Result<T>()
}