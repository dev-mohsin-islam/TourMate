package com.example.tourapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tourapp.databinding.FragmentLoginBinding
import com.example.tourapp.viewmodel.LoginViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.login.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                binding.email.error = "Email is required"
                binding.password.error = "Password is required"
            }else{
                loginViewModel.login(email, password)
            }
        }

//        loginViewModel.authenticationStatus.observe(viewLifecycleOwner){
//            if(it == LoginViewModel.AuthenticationStatus.AUTHENTICATED){
//                findNavController().navigate(R.id.tour_list_action)
//            }else{
//                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
//            }
//        }
        binding.signup.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            if(email.isEmpty() || password.isEmpty()){
                binding.email.error = "Email is required"
                binding.password.error = "Password is required"
            }else{
                loginViewModel.registration(email, password)
            }
//            findNavController().navigate(R.id.login_action)
        }

        // Inflate the layout for this fragment
        return binding.root
    }


}