package com.android.hayahpharma.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hayahpharma.R
import com.android.hayahpharma.databinding.FragmentMissionBinding
import com.android.hayahpharma.databinding.FragmentValuableBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MissionFragment : Fragment() {

    private lateinit var binding: FragmentMissionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMissionBinding.bind(view)
    }
}