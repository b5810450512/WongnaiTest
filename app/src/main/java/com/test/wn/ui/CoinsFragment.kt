package com.test.wn.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.wn.R
import com.test.wn.core.uibase.FragmentBase
import com.test.wn.ui.adapter.CoinsListAdapter
import com.test.wn.ui.viewmodel.CoinsViewModel
import kotlinx.android.synthetic.main.fragment_coins.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import androidx.lifecycle.Observer
import com.google.gson.JsonObject

class CoinsFragment: FragmentBase() {

    private val viewModel: CoinsViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coins, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initCoinsList()
        initEvent()
        initObserveViewModel()
    }

    override fun initEvent() {
        swrRefreshCoinsList.setOnRefreshListener {
            viewModel.initCoinsList()
            swrRefreshCoinsList.isRefreshing = false
        }
    }

    override fun initObserveViewModel() {
        viewModel.coinsList.observe(viewLifecycleOwner, Observer { coinsList ->
            createCoinsList(coinsList)
        })
    }

    private fun createCoinsList(coinsList: List<JsonObject>) {
        val adapter = CoinsListAdapter(requireActivity(), coinsList)
        rcvCoinsList.adapter = adapter
    }

}