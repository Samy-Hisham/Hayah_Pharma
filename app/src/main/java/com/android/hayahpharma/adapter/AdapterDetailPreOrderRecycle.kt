package com.android.hayahpharma.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.hayahpharma.databinding.ItemPerOrderBinding
import com.android.hayahpharma.databinding.ItemSelectedPerOrderBinding
import com.android.hayahpharma.model.ItemXXX
import com.android.hayahpharma.model.ModelPreOrderItem
import com.bumptech.glide.Glide

class AdapterDetailPreOrderRecycle : RecyclerView.Adapter<AdapterDetailPreOrderRecycle.Holder>() {

    var list: MutableList<ItemXXX>? = null
    var totalALLItem = 0

    @SuppressLint("NotifyDataSetChanged")
    @JvmName("setList1")
    fun setList(list: MutableList<ItemXXX>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemSelectedPerOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        val data = list?.get(position)
//
//        for (i in list!!) {
//            totalALLItem += i.total
//        }

//        setTotal(totalALLItem)

        holder.binding.apply {
            txtName.text = data?.name

            txtQuantity.text = data?.qty.toString()
            txtPrice.text = "${data?.price.toString()} EGP"
            txtTotal.text = "${data?.total.toString()} EGP"
        }

        Glide.with(holder.itemView.context)
            .load(data?.imageName)
            .into(holder.binding.imgItem)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    inner class Holder(val binding: ItemSelectedPerOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

//    fun setTotal(total: Int) {
//        this.totalALLItem = total
//    }
//
//    fun getTotal(): Int {
//        return totalALLItem
//    }
}