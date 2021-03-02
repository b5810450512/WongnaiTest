package com.test.wn.core.service

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("{postUrl}")
    @Headers("Content-Type: application/json; charset=utf-8")
    fun getDataFrom(@Path("postUrl") postUrl: String): Call<JsonObject>

}