package com.mpetek.memesgenerator.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.mpetek.memesgenerator.R
import com.mpetek.memesgenerator.model.AllMemesData
import com.mpetek.memesgenerator.model.Meme
import com.mpetek.memesgenerator.model.Memes
import com.mpetek.memesgenerator.view.ShowMemeActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.memes_rv_item.view.*

class MemesAdapter(var activity: Activity,var dataList: ArrayList<Meme>): RecyclerView.Adapter<MemesAdapter.DataViewHolder> (),
    Filterable {
    class DataViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.memes_rv_item,parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        Picasso.get()
            .load(dataList[position].url)
            .into(holder.view.item_photo_iv)

        holder.view.meme_layout.setOnClickListener {

            val intent = Intent(activity, ShowMemeActivity::class.java)
            intent.putExtra("url", dataList[position].url)
            intent.putExtra("id", dataList[position].id)
            intent.putExtra("name", dataList[position].name)
            activity.startActivity(intent)
            activity.overridePendingTransition(0,0)
        }
    }
    fun updateDataList(newDataList: AllMemesData) {
        dataList.clear()
        dataList.addAll(newDataList.data.memes)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {
                dataList = filterResults.values as ArrayList<Meme>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    dataList
                else
                    dataList.filter {
                        it.name.toLowerCase().contains(queryString) ||
                                it.id.toLowerCase().contains(queryString)
                    }
                return filterResults
            }
        }
    }
}