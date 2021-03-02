package com.test.wn.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.gson.JsonObject
import com.test.wn.R
import com.test.wn.core.uibase.FragmentBase
import com.test.wn.ui.adapter.CoinsListAdapter
import com.test.wn.ui.viewmodel.CoinsViewModel
import kotlinx.android.synthetic.main.fragment_coins.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CoinsFragment: FragmentBase() {

    private val viewModel: CoinsViewModel by sharedViewModel()
    private lateinit var coinListAdapter: CoinsListAdapter
    private var inputSearchString = ""

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
            coinListAdapter.notifyDataSetChanged()
            coinListAdapter.filter.filter(inputSearchString)
            swrRefreshCoinsList.isRefreshing = false
        }

        edtSearchCoins.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)= Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                inputSearchString = p0.toString()
                coinListAdapter.filter.filter(inputSearchString)
            }
            override fun afterTextChanged(p0: Editable?)= Unit
        })

    }

    override fun initObserveViewModel() {
        viewModel.coinsList.observe(viewLifecycleOwner, Observer { coinsList ->
            createCoinsList(coinsList)
        })
    }

    private fun createCoinsList(coinsList: List<JsonObject>) {
        coinListAdapter = CoinsListAdapter(requireActivity(), coinsList)
        rcvCoinsList.adapter = coinListAdapter
    }

}