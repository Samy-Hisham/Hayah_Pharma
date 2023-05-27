package com.android.hayahpharma.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.databinding.FragmentInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)

        onClick()
    }

    private fun onClick() {
        binding.apply {
            vision.setOnClickListener {
                findNavController().navigate(R.id.action_infoFragment_to_visionFragment)
            }
            mission.setOnClickListener {
                findNavController().navigate(R.id.action_infoFragment_to_missionFragment)
            }
            valuable.setOnClickListener {
                findNavController().navigate(R.id.action_infoFragment_to_valuableFragment)
            }
        }

    }
}