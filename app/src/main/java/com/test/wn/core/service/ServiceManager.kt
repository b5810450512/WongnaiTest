package com.test.wn.core.service

import com.google.gson.JsonObject
import com.test.wn.core.service.DataServiceUrl.COINS
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object ServiceManager {

    private fun createInstance()= BaseService.create()

    fun callServicePost( params: HashMap<String, Any>): Single<JsonObject> {
        return createInstance().getDataFrom(COINS)
//            .doOnSuccess { if (!it.success) throw Throwable(it.message) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}