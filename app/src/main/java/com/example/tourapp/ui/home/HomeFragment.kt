package com.example.tourapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tourapp.R
import com.example.tourapp.adapter.TourAdapter
import com.example.tourapp.databinding.FragmentHomeBinding
import com.example.tourapp.databinding.FragmentLoginBinding
import com.example.tourapp.model.TourModel
import com.example.tourapp.tourDetailsFragment
import com.example.tourapp.viewmodel.LoginViewModel
import com.example.tourapp.viewmodel.TourViewModel

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val tourViewModel : TourViewModel by viewModels()
    private val loginViewModel : LoginViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val adapter = TourAdapter { tour, id ->

        }
        binding.tourListHome.layoutManager = LinearLayoutManager(activity)
        val mockTours = listOf(
            TourModel(id = "1", title = "Tour 1", isCompleted = false),
            TourModel(id = "2", title = "Tour 2", isCompleted = true),
            TourModel(id = "3", title = "Tour 3", isCompleted = false)
        )
        binding.tourListHome.adapter = adapter
        loginViewModel.user?.let { user ->
            tourViewModel.getTourByUserId(user.uid).observe(viewLifecycleOwner) { tours ->
                adapter.submitList(tours)
            }
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.newTourFragment)
        }
        return binding.root
    }



}