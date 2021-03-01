package com.test.wn.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.wn.R

class CoinsListAdapter(private val coinsList: List<String>): RecyclerView.Adapter<CoinsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_coins, parent, false))

    override fun getItemCount() = coinsList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coinsData = coinsList[position]

//        holder.tvAssetName.text = holder.getDetail(data)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val tvAssetName = itemView.tvAssetName
    }

}