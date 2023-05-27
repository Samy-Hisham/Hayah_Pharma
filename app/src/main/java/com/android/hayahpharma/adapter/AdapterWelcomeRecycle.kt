//package com.android.hayahpharma.adapter
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.android.hayahpharma.databinding.ItemWelcomeBinding
//import com.android.hayahpharma.model.ModelWelcome
//
//
//class AdapterWelcomeRecycle : RecyclerView.Adapter<AdapterWelcomeRecycle.Holder>() {
//
//
//    var list: MutableList<ModelWelcome>? = null
//
////    @SuppressLint("NotifyDataSetChanged")
////    @JvmName("setList1")
////    fun setList(list: MutableList<ModelWelcome>) {
////        this.list = list
////        notifyDataSetChanged()
////    }
//
//
//    var con: Context? = null
//    fun AdapterRecycler(pl: MutableList<ModelWelcome?>, con: Context?) {
//        pl = list!!
//        this.con = con
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val binding =
//            ItemWelcomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return Holder(binding)
//    }
//
//    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
//    override fun onBindViewHolder(holder: Holder, position: Int) {
//
//        val data: ModelWelcome = list?.get(position)!!
//
//        holder.binding.name.setText(data.getName());
//        holder.binding.icon.setImageResource(data.getOrderImage())
//    }
//
//    override fun getItemCount(): Int = list?.size ?: 0
//
//    inner class Holder(val binding: ItemWelcomeBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//    }
//}