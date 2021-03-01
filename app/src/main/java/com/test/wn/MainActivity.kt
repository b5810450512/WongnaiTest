package com.test.wn

import android.os.Bundle
import com.test.wn.core.uibase.ActivityBase
import com.test.wn.ui.CoinsFragment

class MainActivity : ActivityBase() {

    override fun initEvent()= Unit

    override fun initObserveViewModel() = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(R.id.frlMainContainer, CoinsFragment(),"CoinsFragment")
    }

}