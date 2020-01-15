package com.rushabh.wipro_rushabh.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rushabh.wipro_rushabh.R
import com.rushabh.wipro_rushabh.roomDB.Facts
import kotlinx.android.synthetic.main.layout_fact_item.view.*

class FactAdapter(
    val facts: Facts,
    val context: Context
) : RecyclerView.Adapter<FactAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return facts.rows.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.layout_fact_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FactAdapter.ViewHolder, position: Int) {
        val row = facts.rows.get(position)
        holder.textTitle.text = row.title
        holder.textDesc.text = row.description
        Glide.with(context)
            .load(row.imageHref)
            .placeholder(R.drawable.img_place_holder)
            .error(R.drawable.img_place_holder)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.imgPic)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textTitle = view.textTitle
        val textDesc = view.textDesc
        val imgPic = view.imgPic
    }

}