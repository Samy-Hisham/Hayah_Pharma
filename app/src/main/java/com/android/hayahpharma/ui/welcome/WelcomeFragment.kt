package com.android.hayahpharma.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.android.hayahpharma.R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)

        onClick()
    }

    private fun onClick() {
        binding.apply {
            createOrder.setOnClickListener {
                findNavController().navigate(R.id.action_welcomeFragment_to_homeFragment)
            }
            nearrestStore.setOnClickListener {
                findNavController().navigate(R.id.action_welcomeFragment_to_nearUserFragment2)
            }
            preOrder.setOnClickListener {
                findNavController().navigate(R.id.action_welcomeFragment_to_preOrderFragment)
            }
            preReturn.setOnClickListener {
                findNavController().navigate(R.id.action_welcomeFragment_to_expiredFragment2)
            }
            discount.setOnClickListener {
                findNavController().navigate(R.id.action_welcomeFragment_to_discountFragment)
            }
            about.setOnClickListener {
                findNavController().navigate(R.id.action_welcomeFragment_to_infoFragment)
            }
        }
    }
}