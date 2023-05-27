package com.android.hayahpharma.ui.preorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.android.hayahpharma.R
import com.android.hayahpharma.adapter.AdapterDetailPreOrderRecycle
import com.android.hayahpharma.databinding.FragmentItemDetailPreOrderBinding
import com.android.hayahpharma.model.Item
import com.android.hayahpharma.model.ItemXXX
import com.android.hayahpharma.uitls.MySharedPreference
import com.android.saidalytech.uitls.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailPreOrderFragment : Fragment() {

    private lateinit var binding: FragmentItemDetailPreOrderBinding

    private val viewModel: PreOrderViewModel by viewModels()

    private val adapter: AdapterDetailPreOrderRecycle by lazy { AdapterDetailPreOrderRecycle() }

    var orderId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_detail_pre_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemDetailPreOrderBinding.bind(view)

        orderId = ItemDetailPreOrderFragmentArgs.fromBundle(requireArguments()).orderId

        viewModel.getPreOrder(MySharedPreference.getCode())
        observe()
    }

    private fun observe() {

        viewModel.apply {
            successMD.observe(viewLifecycleOwner) {

                val data = it


                val filterList = data.filter {
                    it.orderId == orderId
                }.map {
                    it.items
                }.map {
                    it.toMutableList()
                    adapter.setList(it as MutableList<ItemXXX>)
                    binding.recycleItem.adapter = adapter
                }.toMutableList()

            }
            failureMD.observe(viewLifecycleOwner) {
                showToast(requireContext(), it)
            }
            progressMD.observe(viewLifecycleOwner) {
                if (it == true) {
                    binding.progress.visibility = View.VISIBLE
                } else {
                    binding.progress.visibility = View.GONE
                }
            }
        }
    }

}