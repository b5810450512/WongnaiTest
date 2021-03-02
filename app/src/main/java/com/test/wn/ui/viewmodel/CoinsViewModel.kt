package com.test.wn.ui.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.gson.JsonObject
import com.test.wn.core.livedata.SingleLiveData
import com.test.wn.core.service.ServiceManager

class CoinsViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    val coinsList = SingleLiveData<List<JsonObject>>()

    @SuppressLint("CheckResult")
    fun initCoinsList() {
        ServiceManager.callService().subscribe({
            val coins = it["data"].asJsonObject["coins"].asJsonArray.toList()
            val coinsListResult = coins.map { it.asJsonObject }
            coinsList.value = coinsListResult
        },{
            print(it.message)
        })
    }

}