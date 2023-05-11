package com.android.hayahpharma.ui.near

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.hayahpharma.R
import com.android.hayahpharma.adapter.AdapterRecyclerINear
import com.android.hayahpharma.databinding.FragmentNearUserBinding
import com.android.hayahpharma.uitls.MySharedPreference
import com.android.saidalytech.uitls.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NearUserFragment : Fragment() {

    private lateinit var binding: FragmentNearUserBinding

    private val viewModel: NearViewModel by viewModels()
    private val adapter: AdapterRecyclerINear by lazy { AdapterRecyclerINear() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_near_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNearUserBinding.bind(view)

        observe()
    }

    private fun observe() {

        val id = MySharedPreference.getGovId()

        viewModel.getNearLocation(id.toInt())
        viewModel.apply {
            successMD.observe(viewLifecycleOwner) {

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