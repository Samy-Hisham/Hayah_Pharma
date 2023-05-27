package com.android.hayahpharma.ui.expired

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.adapter.AdapterPreReturnRecycle
import com.android.hayahpharma.databinding.FragmentPreReturnsBinding
import com.android.saidalytech.uitls.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreReturnsFragment : Fragment() {

    private lateinit var binding: FragmentPreReturnsBinding

    private val preReturnViewModel: ReturnsViewModel by viewModels()
    private val adapterPreOrderRecycle: AdapterPreReturnRecycle by lazy { AdapterPreReturnRecycle() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pre_returns, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPreReturnsBinding.bind(view)

        preReturnViewModel.preReturn()
        observe()
        onClick()
    }

    private fun onClick() {
//        adapterPreOrderRecycle.onUserClicks = object : AdapterPreReturnRecycle.OnUserClicks {
//            override fun onClick(id: String) {
//
//            }
//        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observe() {
        preReturnViewModel.apply {
            successPMD.observe(viewLifecycleOwner) {
                val data = it

                adapterPreOrderRecycle.setList(data)
                binding.recycleOrder.adapter = adapterPreOrderRecycle
            }
            failurePMD.observe(viewLifecycleOwner) {
                showToast(requireContext(),it)
            }
            progressPMD.observe(viewLifecycleOwner) {
                if (it == true) {
                    binding.progress.visibility = View.VISIBLE
                } else {
                    binding.progress.visibility = View.GONE
                }
            }
        }
    }
}