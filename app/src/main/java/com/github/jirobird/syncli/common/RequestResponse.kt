package com.github.jirobird.syncli.common

sealed class RequestResponse<T>(val data: T? = null, val message:String? = null) {
    class Success<T> (data: T): RequestResponse<T>(data)
    class Error<T> (data: T? = null, message: String): RequestResponse<T>(data, message)
    class Loading<T> (data: T? = null): RequestResponse<T>(data)
}
