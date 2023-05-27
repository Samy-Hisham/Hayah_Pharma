package com.android.hayahpharma.ui.expired

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.adapter.AdapterRecyclerItems
import com.android.hayahpharma.databinding.FragmentAddItemExpiredBinding
import com.android.hayahpharma.model.ModelDataItem
import com.android.hayahpharma.model.ModelDataItemItem
import com.android.hayahpharma.ui.home.HomeViewModel
import com.android.hayahpharma.ui.itemdetail.ItemDetailViewModel
import com.android.saidalytech.uitls.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddItemExpiredFragment : Fragment() {

    private lateinit var binding: FragmentAddItemExpiredBinding

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val itemDetailViewModel: ItemDetailViewModel by activityViewModels()

    private val adapterRecyclerItems: AdapterRecyclerItems by lazy { AdapterRecyclerItems() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_item_expired, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddItemExpiredBinding.bind(view)

        onClick()
        observe()
    }

    private fun observe() {

        homeViewModel.successSMD.observe(viewLifecycleOwner) {

            val data = it
            adapterRecyclerItems.setList(data as ModelDataItem)
            binding.recycleItem.adapter = adapterRecyclerItems
        }
        homeViewModel.failureSMD.observe(viewLifecycleOwner) {
            showToast(requireContext(), it)
        }
    }

    private fun onClick() {
        binding.editSearch.doOnTextChanged { text, start, before, count ->
            homeViewModel.search(binding.editSearch.text.toString())
        }

        adapterRecyclerItems.onUserClicks = object : AdapterRecyclerItems.OnUserClicks {
            override fun onClick(id: Int) {
//                findNavController().navigate(AddItemExpiredFragmentDirections.actionAddItemExpiredFragmentToDefineItemFragment(id))
                itemDetailViewModel.getItemDetail(id)
                observeForAddItem()
            }
        }

        binding.buy.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeForAddItem() {
        itemDetailViewModel.apply {
            successMD.observe(viewLifecycleOwner) {
                onClickToAddList(it.itemName, it.salesPrice, it.itemId, it.categoryId, it.imageName)
            }
            failureMD.observe(viewLifecycleOwner) {
                showToast(requireContext(), it)
            }
        }
    }

    private fun onClickToAddList(
        nameItem: String,
        price: Double,
        id: Int,
        category: Int,
        imageItem: String,
    ) {
        val newItemToAdd = ModelDataItemItem(
            category,
            imageItem,
            id,
            nameItem,
            price,
            price
        )
        homeViewModel.addItemToExpiredList(newItemToAdd)

        showToast(requireContext(), "Item is Added")
    }
}