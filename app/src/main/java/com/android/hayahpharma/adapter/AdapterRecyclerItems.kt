package com.android.hayahpharma.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.hayahpharma.databinding.ItemRecycleBinding
import com.android.hayahpharma.model.ModelDataItemItem
import com.android.hayahpharma.model.ModelDisount
import com.bumptech.glide.Glide

class AdapterRecyclerItems : RecyclerView.Adapter<AdapterRecyclerItems.Holder>() {

    var list: MutableList<ModelDataItemItem>? = null

    @SuppressLint("NotifyDataSetChanged")
    @JvmName("setList1")
    fun setList(list: MutableList<ModelDataItemItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    var onUserClicks: OnUserClicks? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding = ItemRecycleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        val data = list?.get(position)

        holder.binding.apply {
            txtItem.text = data?.itemName
            price.text = "${data?.salesPrice.toString()} EGP"
            phPrice.text = "${data?.puchasePrice.toString()} EGP"
        }

        Glide.with(holder.itemView.context)
            .load(data?.imageName)
            .into(holder.binding.imgItem)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    inner class Holder(val binding: ItemRecycleBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {

                onUserClicks?.onClick(list?.get(layoutPosition)!!.itemId)
                notifyDataSetChanged()
            }
        }
    }

    interface OnUserClicks {
        fun onClick(id: Int)
    }

}