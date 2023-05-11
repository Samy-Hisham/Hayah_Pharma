package com.android.hayahpharma.ui.auth.register

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.databinding.FragmentSignUpBinding
import com.android.hayahpharma.model.ModelRegister
import com.android.hayahpharma.uitls.MySharedPreference
import com.android.saidalytech.uitls.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignUpBinding.bind(view)

        binding.btnSignUp.setOnClickListener {
            validation()
        }

        binding.showPass.setOnClickListener {
            binding.showPass.visibility = View.GONE
            binding.editPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.hidePass.visibility = View.VISIBLE
        }
        binding.hidePass.setOnClickListener {
            binding.hidePass.visibility = View.GONE
            binding.editPass.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.showPass.visibility = View.VISIBLE
        }

        binding.showConfirmedPass.setOnClickListener {
            binding.showConfirmedPass.visibility = View.GONE
            binding.editConfirmPass.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
            binding.hideConfirmedPass.visibility = View.VISIBLE
        }
        binding.hideConfirmedPass.setOnClickListener {
            binding.hideConfirmedPass.visibility = View.GONE
            binding.editConfirmPass.transformationMethod =
                PasswordTransformationMethod.getInstance()
            binding.showConfirmedPass.visibility = View.VISIBLE
        }
    }

    private fun validation() {

        binding.apply {
            val name = editName.text.toString().trim()
            val pass = editPass.text.toString().trim()
            val confirmedPass = editConfirmPass.text.toString().trim()
            val phone = editPhone.text.toString()
            val govId = spinnerId.selectedItem.toString()

            if (name.isBlank()) {
                editName.error = getString(R.string.required)
            } else if (pass.isBlank()) {
                editPass.error = getString(R.string.required)
            } else if (confirmedPass.isBlank()) {
                editPass.error = getString(R.string.required)
            } else if (confirmedPass != pass) {
                editConfirmPass.error = getString(R.string.not_match)
            } else if (phone.isBlank()) {
                editPhone.error = getString(R.string.required)
            } else if (govId.equals("Government Id")) {
                Toast.makeText(requireContext(), "select your gender", Toast.LENGTH_SHORT).show()
            } else {

                registerViewModel.register(ModelRegister(confirmedPass,
                    name,
                    govId.toInt(),
                    pass,
                    phone))

                observe()
            }
        }
    }

    private fun observe() {
        registerViewModel.apply {

            successMD.observe(viewLifecycleOwner) {
                val data = it

                showToast(requireContext(), getString(R.string.Successful_Register))

                MySharedPreference.apply {
                    setGovId(data.governoratesId)
                    setGovName(data.governoratesName)
                    setUserName(data.name)
                    setUserTOKEN(data.token)
                    setCode(data.code)
                }

                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment(data.code))
            }
            failureMD.observe(viewLifecycleOwner) {

                showToast(requireContext(), it.error)
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
