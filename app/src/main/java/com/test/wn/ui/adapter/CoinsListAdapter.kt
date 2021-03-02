package com.test.wn.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
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

class CoinsListAdapter(private val activity: Activity, private val coinsList: List<JsonObject>): RecyclerView.Adapter<CoinsListAdapter.ViewHolder>(),
    Filterable {

    var filteredListResult= coinsList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_coins, parent, false))

    override fun getItemCount() = filteredListResult.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coinsData = filteredListResult[position]
        holder.setCoinDetail(activity, position, coinsData)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            @SuppressLint("DefaultLocale")
            override fun performFiltering(charString: CharSequence?): FilterResults {
                filteredListResult = if (charString.toString().isEmpty()) {
                    coinsList
                } else {
                    val resultList = ArrayList<JsonObject>()
                    val searchInputString = charString.toString().toLowerCase().trim()
                    coinsList.forEach {
                        if(isContain(it,searchInputString)) resultList.add(it)
                    }
                    resultList
                }
                return FilterResults().apply { values = filteredListResult }
            }

            override fun publishResults(charSquence: CharSequence?, filterResults: FilterResults?) {
                filteredListResult = filterResults!!.values as List<JsonObject>
                notifyDataSetChanged()
            }
        }
    }

    private fun isContain(data: JsonObject, searchInputString: String): Boolean {
        return data["symbol"].asString.toLowerCase().contains(searchInputString) ||
                data["slug"].asString.toLowerCase().contains(searchInputString) ||
                data["id"].asString.toLowerCase().contains(searchInputString) ||
                data["name"].asString.toLowerCase().contains(searchInputString)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val layoutItemNotDivisibleBy5 = itemView.layoutItemNotDivisibleBy5
        private val layoutItemDivisibleBy5 = itemView.layoutItemDivisibleBy5

        private val imvCoinsIconNo5 = itemView.imvCoinsIconNo5
        private val tvCoinsNameNo5 = itemView.tvCoinsNameNo5
        private val tvCoinsDescription = itemView.tvCoinsDescription

        private val imvCoinsIcon5 = itemView.imvCoinsIcon5
        private val tvCoinsName5 = itemView.tvCoinsName5

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