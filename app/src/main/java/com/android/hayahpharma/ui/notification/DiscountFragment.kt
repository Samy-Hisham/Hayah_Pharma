package com.android.hayahpharma.ui.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.databinding.FragmentDiscountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscountFragment : Fragment() {

    private lateinit var binding: FragmentDiscountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDiscountBinding.bind(view)

        onClick()
    }

    private fun onClick() {
        binding.notifaction.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.discount1.setOnClickListener {
            findNavController().navigate(R.id.action_discountFragment_to_homeFragment)
        }

        binding.discount2.setOnClickListener {
            findNavController().navigate(R.id.action_discountFragment_to_itemsDiscountFragment)
        }
    }
}