package com.android.hayahpharma.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.hayahpharma.databinding.ItemExpiredBinding
import com.android.hayahpharma.model.Item
import com.bumptech.glide.Glide

class AdapterRecyclerExpired : RecyclerView.Adapter<AdapterRecyclerExpired.Holder>() {

    var list: List<Item>? = null
    var count = 1

    @SuppressLint("NotifyDataSetChanged")
    @JvmName("setList1")
    fun setList(list: List<Item>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding =
            ItemExpiredBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        val data = list?.get(position)

        holder.binding.apply {

            txtName.text = data?.itemName

            var qty: Int

            price.text = data?.price.toString()
            txtQty.text = count.toString()

            holder.binding.btnAdd.setOnClickListener {
                count++
                holder.binding.textView2.text = count.toString()
                qty = count * data?.price!!.toInt()
                txtQty.text = count.toString()
                holder.binding.price.text = "$qty EGP"
            }

            holder.binding.btnSub.setOnClickListener {
                count--
                holder.binding.textView2.text = count.toString()
                txtQty.text = count.toString()
                qty = count * data?.price!!.toInt()
                holder.binding.price.text = "$qty EGP"
            }

            holder.binding.textView2.text = count.toString()
            qty = count * data?.price!!.toInt()
            holder.binding.price.text = "$qty EGP"

            count = 1
        }

        Glide.with(holder.itemView.context)
            .load(data?.itemImage)
            .into(holder.binding.imgItem)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    inner class Holder(val binding: ItemExpiredBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}