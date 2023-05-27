package com.android.hayahpharma.ui.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.adapter.AdapterRecyclerDiscountItems
import com.android.hayahpharma.adapter.AdapterRecyclerItems
import com.android.hayahpharma.databinding.FragmentItemsDiscountBinding
import com.android.saidalytech.uitls.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsDiscountFragment : Fragment() {

    private lateinit var binding: FragmentItemsDiscountBinding

    private val viewModel: DiscountViewModel by viewModels()
    private val adapter: AdapterRecyclerDiscountItems by lazy { AdapterRecyclerDiscountItems() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items_discount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemsDiscountBinding.bind(view)

        observe()

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observe() {
        viewModel.getDiscount()
        viewModel.apply {
            successMD.observe(viewLifecycleOwner) { it ->

                val data = it

                adapter.setList(data)
                binding.recycleItem.adapter = adapter

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