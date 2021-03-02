package com.test.wn.ui.coins.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.test.wn.core.livedata.SingleLiveData
import com.test.wn.ui.coins.repo.CoinsListRepository
import kotlinx.coroutines.launch

class CoinsViewModel(private val repo: CoinsListRepository): ViewModel() {

    val coinsList = SingleLiveData<List<JsonObject>>()
    val isLoading = SingleLiveData<Boolean>()

    fun initCoinsList() {
        viewModelScope.launch {
            isLoading.value = true
            val coinsRequest = repo.callApiCoins()
            if(coinsRequest.isSuccess) {
                val coins = coinsRequest.data!!["data"].asJsonObject["coins"].asJsonArray.toList()
                val coinsListResult = coins.map { it.asJsonObject }
                coinsList.value = coinsListResult
            }
            isLoading.value = false
        }
    }

}