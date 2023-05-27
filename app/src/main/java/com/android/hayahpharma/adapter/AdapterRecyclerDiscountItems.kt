package com.android.hayahpharma.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.hayahpharma.databinding.ItemRecycleBinding
import com.android.hayahpharma.databinding.ItemRecycleDiscountBinding
import com.android.hayahpharma.model.ModelDataItemItem
import com.android.hayahpharma.model.ModelDisountItem
import com.android.hayahpharma.ui.notification.ItemsDiscountFragment
import com.bumptech.glide.Glide

class AdapterRecyclerDiscountItems : RecyclerView.Adapter<AdapterRecyclerDiscountItems.Holder>() {

    var list: MutableList<ModelDisountItem>? = null

    @SuppressLint("NotifyDataSetChanged")
    @JvmName("setList1")
    fun setList(list: MutableList<ModelDisountItem>?) {
        this.list = list
        notifyDataSetChanged()
    }

    var onUserClicks: OnUserClicks? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding = ItemRecycleDiscountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        val data = list?.get(position)

        holder.binding.apply {
            txtItem.text = data?.itemName
            price.text = "${data?.salesPrice.toString()} EGP"
            phPrice.text = "${data?.discount.toString()} EGP"
            pAd.text = "${data?.priceAfterDiscount.toString()} EGP"
        }

        Glide.with(holder.itemView.context)
            .load(data?.imageName)
            .into(holder.binding.imgItem)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    inner class Holder(val binding: ItemRecycleDiscountBinding) : RecyclerView.ViewHolder(binding.root) {

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