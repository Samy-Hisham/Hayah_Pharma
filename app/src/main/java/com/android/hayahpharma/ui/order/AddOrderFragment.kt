package com.android.hayahpharma.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.adapter.AdapterRecycleOrder
import com.android.hayahpharma.databinding.FragmentAddOrderBinding
import com.android.hayahpharma.model.ItemX
import com.android.hayahpharma.model.ModelItemDetail
import com.android.hayahpharma.ui.home.HomeViewModel
import com.android.saidalytech.uitls.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddOrderFragment : Fragment() {

    private lateinit var binding: FragmentAddOrderBinding

    private val sharedViewModel: HomeViewModel by activityViewModels()
    private val orderViewModel: OrderViewModel by viewModels()
    private val adapter: AdapterRecycleOrder by lazy { AdapterRecycleOrder() }

    var list: List<ItemX>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddOrderBinding.bind(view)

        observe()
        onClick()
        observeForUi()
    }

    private fun onClick() {
        binding.addMore.setOnClickListener {
            findNavController().navigate(R.id.action_addOrderFragment_to_homeFragment)
        }
        binding.buy.setOnClickListener {

            findNavController().navigate(R.id.action_addOrderFragment_to_detailOrderFragment)

//            orderViewModel.sendOrder(sharedViewModel.shoppingCartItemsToOrder)
//
//            orderViewModel.successMD.observe(viewLifecycleOwner) {
//                showToast(requireContext(), "Done")
//                findNavController().navigate(R.id.action_addOrderFragment_to_homeFragment)
//            }
//            orderViewModel.failureMD.observe(viewLifecycleOwner) {
//                showToast(requireContext(), it)
//            }
//            orderViewModel.progressMD.observe(viewLifecycleOwner) {
//                if (it == true) {
//                    binding.progress.visibility = View.VISIBLE
//                } else {
//                    binding.progress.visibility = View.GONE
//                }
//            }
        }

        adapter.onUserClicks = object : AdapterRecycleOrder.OnUserClicks{
            override fun onClickPlus(id: Int) {
                sharedViewModel.incrementItemQuantity(id)
            }

            override fun onClickMines(id: Int) {
                sharedViewModel.decrementItemQuantity(id)
            }
        }

    }

    fun observeForUi(){
        sharedViewModel.currentItemActionIndex.observe(viewLifecycleOwner){ itemX ->
            adapter.setList(sharedViewModel.shoppingCartItemsToOrder)
            adapter.notifyDataSetChanged()
        }
    }
    private fun observe() {
        adapter.setList(sharedViewModel.shoppingCartItemsToOrder)
        binding.recycle.adapter = adapter
    }
}
