package com.example.practice_tablet_movil.data

sealed class OperationResult<out T> {
    data class Success<T>(val data: T) : OperationResult<T>()
    data class Failure(val exception: Exception) : OperationResult<Nothing>()
    //data class UnAuthorized(val exception: Exception): OperationResult<Nothing>()
}