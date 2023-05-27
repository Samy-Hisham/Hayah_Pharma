package com.android.hayahpharma.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.adapter.AdapterRecyclerItems
import com.android.hayahpharma.databinding.FragmentHomeBinding
import com.android.hayahpharma.model.ModelDataItem
import com.android.saidalytech.uitls.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: HomeViewModel by activityViewModels()
    private val adapterRecyclerItems: AdapterRecyclerItems by lazy { AdapterRecyclerItems() }

    var data: ModelDataItem? = null
    var id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        observe()
        onClick()

    }

    private fun onClick() {

        adapterRecyclerItems.onUserClicks = object : AdapterRecyclerItems.OnUserClicks {
            override fun onClick(id: Int) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToItemDetailFragment(
                    id))
            }
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

//        binding.preOrder.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_preOrderFragment)
//        }
//        binding.editSearch.doOnTextChanged { text, start, before, count ->
//            sharedViewModel.search(binding.editSearch.text.toString())
//        }
    }

    private fun observe() {

        sharedViewModel.getData(data.toString())
        sharedViewModel.apply {
            successMD.observe(viewLifecycleOwner) { it ->

                val data = it

                adapterRecyclerItems.setList(data)
                binding.recycleItem.adapter = adapterRecyclerItems

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

        sharedViewModel.successSMD.observe(viewLifecycleOwner) {

            val data = it
            adapterRecyclerItems.setList(data as ModelDataItem)
            binding.recycleItem.adapter = adapterRecyclerItems
        }
        sharedViewModel.failureSMD.observe(viewLifecycleOwner) {
            showToast(requireContext(), it)
        }
    }
}