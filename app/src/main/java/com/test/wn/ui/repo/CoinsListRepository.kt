package com.test.wn.ui.repo

import com.google.gson.JsonObject
import com.test.wn.core.coroutine.BaseRemoteDataSource
import com.test.wn.core.di.datafilter.ResultWrapper
import com.test.wn.core.service.ApiInterface
import com.test.wn.core.service.DataServiceUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoinsListRepository(private val remote: CoinListRemote) {

    suspend fun callApiCoins(): ResultWrapper<JsonObject>{
        return withContext(Dispatchers.IO){
            remote.callCoinsApi()
        }
    }

}

class CoinListRemote (private val api: ApiInterface) :  BaseRemoteDataSource() {

    fun callCoinsApi(): ResultWrapper<JsonObject> = request(api.getDataFrom(DataServiceUrl.COINS))

}