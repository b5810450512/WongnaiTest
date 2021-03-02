package com.test.wn.core.coroutine

import com.test.wn.core.di.datafilter.ResultWrapper
import retrofit2.Call

abstract class BaseRemoteDataSource {

    protected fun <T> request(call: Call<T>): ResultWrapper<T> {
        return try {
            val response = call.execute()
            if (response.isSuccessful) {
                ResultWrapper.success(response.body())
            } else {
                ResultWrapper.error("กรุณาลองใหม่อีกครั้ง")
            }
        } catch (exception: Throwable) {
            ResultWrapper.error("กรุณาลองใหม่อีกครั้ง")
        }
    }
}