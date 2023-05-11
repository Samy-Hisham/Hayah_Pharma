package com.android.hayahpharma.ui.expired

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.databinding.FragmentDefineItemBinding
import com.android.hayahpharma.model.Item
import com.android.hayahpharma.model.ModelDataItemItem
import com.android.hayahpharma.ui.home.HomeViewModel
import com.android.hayahpharma.ui.itemdetail.ItemDetailViewModel
import com.android.saidalytech.uitls.showToast
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DefineItemFragment : Fragment() {

    private lateinit var binding: FragmentDefineItemBinding

    private val itemDetailViewModel: ItemDetailViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    var id: Int? = null
    var count = 1

    val list: MutableList<Item>? = null
    var model: Item? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_define_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDefineItemBinding.bind(view)

        id = DefineItemFragmentArgs.fromBundle(requireArguments()).id

        itemDetailViewModel.getItemDetail(id!!)
        observe()

    }

    private fun onClicK(price: Double) {
        binding.btnAdd.setOnClickListener {
            count++
            binding.qty.text = count.toString()
            binding.price.text = (count * price).toString()
        }
        binding.btnSub.setOnClickListener {
            count--
            binding.qty.text = count.toString()
            binding.price.text = (count * price).toString()
        }
    }

    private fun observe() {
        itemDetailViewModel.apply {
            successMD.observe(viewLifecycleOwner) {

                val data = it

                data.apply {
                    data.itemId
                    data.categoryId
                    data.imageName
                    binding.txtItem.text = itemName
                    binding.price.text = (count * salesPrice).toString()
                    binding.qty.text = count.toString()

                }
                Glide.with(binding.root.context)
                    .load(data?.imageName)
                    .into(binding.imgItem)
                onClicK(it.salesPrice)
                onClickToAddList(it.itemName, it.salesPrice, it.itemId, it.categoryId, it.imageName,count)
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
        qty: Int
    ) {
        binding.done.setOnClickListener {
            val newItemToAdd = ModelDataItemItem(
                category,
                imageItem,
                id,
                nameItem,
                price,
                price,
                qty = qty
            )
            homeViewModel.addItemToExpiredList(newItemToAdd)

            showToast(requireContext(), "Item is Added")
            findNavController().popBackStack()
        }
    }
}