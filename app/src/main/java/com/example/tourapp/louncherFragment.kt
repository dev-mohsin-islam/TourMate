package com.example.tourapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.navigation.fragment.findNavController
import com.example.tourapp.databinding.FragmentLouncherBinding
import com.example.tourapp.viewmodel.LoginViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [louncherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class louncherFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLouncherBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLouncherBinding.inflate(inflater, container, false)
        loginViewModel.authenticationStatus.observe(viewLifecycleOwner){
            if(it == LoginViewModel.AuthenticationStatus.AUTHENTICATED){
                findNavController().navigate(R.id.nav_home)
            }else if(it == LoginViewModel.AuthenticationStatus.UNAUTHENTICATED){
                findNavController().navigate(R.id.login_action)
            }else if(it == LoginViewModel.AuthenticationStatus.INVALID_AUTHENTICATION){
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

}