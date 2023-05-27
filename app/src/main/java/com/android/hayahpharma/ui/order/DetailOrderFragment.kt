package com.android.hayahpharma.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.databinding.FragmentDetailOrderBinding
import com.android.hayahpharma.ui.home.HomeViewModel
import com.android.hayahpharma.uitls.MySharedPreference
import com.android.saidalytech.uitls.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailOrderFragment : Fragment() {

    private lateinit var binding: FragmentDetailOrderBinding

    private val sharedViewModel: HomeViewModel by activityViewModels()
    private val orderViewModel: OrderViewModel by viewModels()

    var isCheck: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailOrderBinding.bind(view)

        onClick()
    }

    private fun onClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.editPhone.setText(MySharedPreference.getUserPhone())
        binding.editCity.setText(MySharedPreference.getUserAddress())

        binding.cashRadio.setOnClickListener {
            isCheck = 2
        }
        binding.cashRadio.setOnClickListener {
            isCheck = 1
        }

        binding.buy.setOnClickListener {
            if (isCheck == 1) {


                orderViewModel.sendOrder(sharedViewModel.shoppingCartItemsToOrder)

                orderViewModel.successMD.observe(viewLifecycleOwner) {
                    findNavController().navigate(R.id.action_detailOrderFragment_to_orderDoneFragment)
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
            } else {

                findNavController().navigate(R.id.action_detailOrderFragment_to_creditFragment)

            }
        }
    }
}