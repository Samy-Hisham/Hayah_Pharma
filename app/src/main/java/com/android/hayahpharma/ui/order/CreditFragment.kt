package com.android.hayahpharma.ui.order

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.databinding.FragmentCreditBinding
import com.android.hayahpharma.ui.home.HomeViewModel
import com.android.saidalytech.uitls.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreditFragment : Fragment() {

    private lateinit var binding: FragmentCreditBinding

    private val sharedViewModel: HomeViewModel by activityViewModels()
    private val orderViewModel: OrderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_credit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreditBinding.bind(view)

        onClick()
    }

    private fun onClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.cancel.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buy.setOnClickListener {

            val card = binding.editCard.text.toString().trim()
            val expiry = binding.editExpire.text.toString().trim()
            val cvv = binding.editCvv.text.toString().trim()

            when {
                card.isBlank() -> {
                    binding.editCard.error = getString(R.string.required)
                }

                expiry.isBlank() -> {
                    binding.editExpire.error = getString(R.string.required)
                }

                cvv.isBlank() -> {
                    binding.editCvv.error = getString(R.string.required)
                }

                else -> {
                    orderViewModel.sendOrder(sharedViewModel.shoppingCartItemsToOrder)

                    orderViewModel.successMD.observe(viewLifecycleOwner) {
                        findNavController().navigate(R.id.action_creditFragment_to_orderDoneFragment)
                    }
                    orderViewModel.failureMD.observe(viewLifecycleOwner) {
                        showToast(requireContext(), it)
                    }
                    orderViewModel.progressMD.observe(viewLifecycleOwner) {
                        if (it == true) {
                            binding.progress.visibility = View.VISIBLE
                        } else {
                            binding.progress.visibility = View.GONE

                        }
                    }
                }

            }
        }
    }
}