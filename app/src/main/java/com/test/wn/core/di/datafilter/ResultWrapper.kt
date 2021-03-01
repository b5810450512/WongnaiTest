package com.test.wn.core.di.datafilter

data class ResultWrapper<T>(val isSuccess: Boolean, val message: String?, val data: T?) {
    companion object {
        fun <T> success(data: T?): ResultWrapper<T> =
            ResultWrapper(
                true,
                null,
                data
            )
        fun <T> error(message: String?): ResultWrapper<T> =
            ResultWrapper(
                false,
                message,
                null
            )
    }
}