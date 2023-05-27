package com.android.hayahpharma.ui.order

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.databinding.FragmentOrderDoneBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDoneFragment : Fragment() {

    private lateinit var binding : FragmentOrderDoneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_done, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderDoneBinding.bind(view)


        binding.buy.setOnClickListener {
            findNavController().navigate(R.id.action_orderDoneFragment_to_welcomeFragment)
        }

    }
}