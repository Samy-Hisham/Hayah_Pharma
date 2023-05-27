package com.android.hayahpharma.ui.preorder

import android.os.Bundle
import android.support.annotation.MainThread
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.adapter.AdapterPreOrderRecycle
import com.android.hayahpharma.databinding.FragmentPreOrderBinding
import com.android.hayahpharma.model.ModelPreOrderItem
import com.android.hayahpharma.uitls.MySharedPreference
import com.android.saidalytech.uitls.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class PreOrderFragment : Fragment() {

    private lateinit var binding: FragmentPreOrderBinding

    private val viewModel: PreOrderViewModel by viewModels()

    private val adapterPreOrderRecycle: AdapterPreOrderRecycle by lazy { AdapterPreOrderRecycle() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pre_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPreOrderBinding.bind(view)

        viewModel.getPreOrder(MySharedPreference.getCode())
        observe()
        onClicK()
        observeCost()
    }

    private fun onClicK() {
        adapterPreOrderRecycle.onUserClicks = object : AdapterPreOrderRecycle.OnUserClicks {
            override fun onClick(id: Int) {
                findNavController().navigate(
                    PreOrderFragmentDirections.actionPreOrderFragmentToItemDetailPreOrderFragment(
                        id
                    )
                )
            }
        }
    }

    private fun observeCost(){
        viewModel.fullCost.observe(viewLifecycleOwner){
            lifecycleScope.launchWhenCreated {
                withContext(Dispatchers.Main){
                    binding.total.text = it.toString()
                }
            }
        }
    }
    private fun observe() {
        viewModel.apply {
            successMD.observe(viewLifecycleOwner) {

                val data = it
                var filterData: ModelPreOrderItem ?= null

                data.map {
                     filterData = it
                }
                filterData?.total = 0

//                var total: MutableList<Int>? = null
//                var totalprice = 0
//                data.forEach {
//                    total?.add(it.total)
//                }
//                total?.forEach {
//                    totalprice = it
//                }
//                binding.total.text = totalprice.toString()

                adapterPreOrderRecycle.setList(data)
                binding.recycleOrder.adapter = adapterPreOrderRecycle

//                binding.total.text = adapterPreOrderRecycle.getTotal(filterData!!)
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