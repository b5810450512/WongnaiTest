package com.test.wn.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.google.gson.JsonObject
import com.test.wn.R
import kotlinx.android.synthetic.main.item_coins.view.*
import kotlinx.android.synthetic.main.layout_item_divisible_by_5.view.*
import kotlinx.android.synthetic.main.layout_item_not_divisible_by_5.view.*
import org.sufficientlysecure.htmltextview.HtmlFormatter
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder
import org.sufficientlysecure.htmltextview.HtmlResImageGetter

class CoinsListAdapter(private val activity: Activity, private val coinsList: List<JsonObject>): RecyclerView.Adapter<CoinsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_coins, parent, false))

    override fun getItemCount() = coinsList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coinsData = coinsList[position]

        holder.setCoinDetail(activity, position, coinsData)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layoutItemNotDivisibleBy5 = itemView.layoutItemNotDivisibleBy5
        val layoutItemDivisibleBy5 = itemView.layoutItemDivisibleBy5

        val imvCoinsIconNo5 = itemView.imvCoinsIconNo5
        val tvCoinsNameNo5 = itemView.tvCoinsNameNo5
        val tvCoinsDescription = itemView.tvCoinsDescription

        val imvCoinsIcon5 = itemView.imvCoinsIcon5
        val tvCoinsName5 = itemView.tvCoinsName5

        fun setCoinDetail(activity: Activity, position: Int, coinDetail: JsonObject) {
            val rowNumber = position + 1
            if(rowNumber % 5 == 0) {
                layoutItemNotDivisibleBy5.visibility = View.GONE
                layoutItemDivisibleBy5.visibility = View.VISIBLE

                GlideToVectorYou.justLoadImage(activity, Uri.parse(coinDetail["iconUrl"].asString), imvCoinsIcon5)

                tvCoinsName5.text = coinDetail["name"].asString
            } else {
                layoutItemDivisibleBy5.visibility = View.GONE
                layoutItemNotDivisibleBy5.visibility = View.VISIBLE

                GlideToVectorYou.justLoadImage(activity, Uri.parse(coinDetail["iconUrl"].asString), imvCoinsIconNo5)

                tvCoinsNameNo5.text = coinDetail["name"].asString

                val description = coinDetail["description"]
                if(!description.isJsonNull) {
                    val removedHtmlDescription = HtmlFormatter.formatHtml(HtmlFormatterBuilder().setHtml(description.asString).setImageGetter(
                        HtmlResImageGetter(tvCoinsDescription.context)
                    ))
                    tvCoinsDescription.text = removedHtmlDescription
                }
            }
        }
    }

}