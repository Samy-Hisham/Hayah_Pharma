package com.android.hayahpharma.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.hayahpharma.R
import com.android.hayahpharma.databinding.FragmentLoginBinding
import com.android.hayahpharma.model.ModelLogin
import com.android.hayahpharma.uitls.MySharedPreference
import com.android.saidalytech.uitls.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    var code: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

//        code = LoginFragmentArgs.fromBundle(requireArguments()).code

        code = MySharedPreference.getCode()
        binding.editCode.setText(code)

        onClick()
    }

    private fun onClick() {

        binding.txtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.btnLogin.setOnClickListener {

            validation()
        }
    }

    private fun validation() {

        binding.apply {

            val email = editCode.text.toString().trim()
            val pass = editPass.text.toString().trim()

            when {
                email.isBlank() -> {
                    editCode.error = getString(R.string.required)
                }
                pass.isBlank() -> {
                    editPass.error = getString(R.string.required)
                }
                else -> {

                    loginViewModel.login(ModelLogin(code.toString(), pass))
                    observe()
                }
            }
        }
    }

    private fun observe() {

        loginViewModel.successMD.observe(viewLifecycleOwner) {

            val data = it

            MySharedPreference.apply {
//                setGovId(data.governoratesId)
                setGovName(data.governoratesName)
                setUserName(data.name)
                setUserTOKEN(data.token)
            }

            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }

        loginViewModel.failureMD.observe(viewLifecycleOwner) {
            showToast(requireContext(), it)
        }
        loginViewModel.progressMD.observe(viewLifecycleOwner) {

            if (it == true) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }
    }
}
