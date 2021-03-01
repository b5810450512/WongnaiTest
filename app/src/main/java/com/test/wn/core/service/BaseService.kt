package com.test.wn.core.service

import com.test.wn.core.service.DataServiceUrl.BASE_URL
import com.test.wn.core.service.ServiceConfig.CALL_TIMEOUT
import com.test.wn.core.service.ServiceConfig.CONNECT_TIMEOUT
import com.test.wn.core.service.ServiceConfig.READ_TIMEOUT
import com.test.wn.core.service.ServiceConfig.WRITE_TIMEOUT
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object BaseService {

    fun create(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(getClient())
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .certificatePinner(getDefaultCertificatePinner())
            .callTimeout(CALL_TIMEOUT.toLong(), TimeUnit.MINUTES)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor())
            .build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun getDefaultCertificatePinner()= CertificatePinner.Builder().build()
}