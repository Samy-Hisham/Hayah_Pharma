package com.android.hayahpharma.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.hayahpharma.databinding.ItemPerReturnBinding
import com.android.hayahpharma.model.ModelPreReturnItem

class AdapterPreReturnRecycle : RecyclerView.Adapter<AdapterPreReturnRecycle.Holder>() {

    var list: MutableList<ModelPreReturnItem>? = null
    var onUserClicks: OnUserClicks? = null

    var count = 1

    @SuppressLint("NotifyDataSetChanged")
    @JvmName("setList1")
    fun setList(list: MutableList<ModelPreReturnItem>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemPerReturnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        val data = list?.get(position)

        holder.binding.apply {
            txtOrder.text = count.toString()
            txtDate.text = data?.date
        }

        count++
    }

    override fun getItemCount(): Int = list?.size ?: 0

    inner class Holder(val binding: ItemPerReturnBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
//                onUserClicks?.onClick(list?.get(layoutPosition)!!.userId)
            }
        }
    }

    interface OnUserClicks {
        fun onClick(id: Int)
    }
}