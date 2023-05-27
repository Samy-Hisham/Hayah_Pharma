package com.android.hayahpharma.ui.expired

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.adapter.AdapterRecyclerExpired
import com.android.hayahpharma.databinding.FragmentExpiredBinding
import com.android.hayahpharma.model.ModelReturns
import com.android.hayahpharma.ui.home.HomeViewModel
import com.android.saidalytech.uitls.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpiredFragment : Fragment() {

    private lateinit var binding: FragmentExpiredBinding

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val returnViewModel: ReturnsViewModel by viewModels()

    private val adapter: AdapterRecyclerExpired by lazy { AdapterRecyclerExpired() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expired, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExpiredBinding.bind(view)

        onClick()
        observe()
    }

    private fun observe() {

        adapter.setList(homeViewModel.shoppingCartItems)
        binding.recycleItem.adapter = adapter
    }

    private fun onClick() {

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_expiredFragment2_to_addItemExpiredFragment)
        }

        binding.order.setOnClickListener {
            returnViewModel.addReturn(homeViewModel.shoppingCartItems)

            returnViewModel.apply {
                successMD.observe(viewLifecycleOwner) {
                    showToast(requireContext(), "Your Order is Added")
//                    findNavController().navigate(R.id.action_expiredFragment2_to_homeFragment)
                }
                failureMD.observe(viewLifecycleOwner) {
                    showToast(requireContext(), "error")
                }
            }
        }

        binding.preReturns.setOnClickListener {
            findNavController().navigate(R.id.action_expiredFragment2_to_preReturnsFragment)
        }
    }
}