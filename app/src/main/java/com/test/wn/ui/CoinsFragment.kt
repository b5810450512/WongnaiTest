package com.test.wn.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.wn.R
import com.test.wn.core.uibase.FragmentBase
import com.test.wn.ui.viewmodel.CoinsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CoinsFragment: FragmentBase() {

    private val viewModel: CoinsViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coins, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEvent()
        initObserveViewModel()
    }

    override fun initEvent() {

    }

    override fun initObserveViewModel() {
//        viewModel.loading.observe(this, Observer {
//            if(it) showLoading() else hideLoading()
//        })
    }

//    private fun createAdHocList(adHocList: List<AdHocSub>){
//        rcvAdhocAssetList.layoutManager = LinearLayoutManager(context)
//        val adapter = AdHocAssetListAdapter(adHocList)
//        rcvAdhocAssetList.adapter = adapter
//    }
}