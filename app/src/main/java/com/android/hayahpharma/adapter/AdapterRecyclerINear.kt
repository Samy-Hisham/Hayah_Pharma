package com.android.hayahpharma.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.hayahpharma.databinding.ItemListNearBinding
import com.android.hayahpharma.model.ModelNearUserItem

class AdapterRecyclerINear : RecyclerView.Adapter<AdapterRecyclerINear.Holder>() {

    var list: MutableList<ModelNearUserItem>? = null

    @SuppressLint("NotifyDataSetChanged")
    @JvmName("setList1")
    fun setList(list: MutableList<ModelNearUserItem>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding =
            ItemListNearBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        val data = list?.get(position)

        holder.binding.apply {
            txtName.text = data?.name
            phone.text = data?.phoneNumber
        }

    }

    override fun getItemCount(): Int = list?.size ?: 0

    inner class Holder(val binding: ItemListNearBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}