package com.example.tourapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tourapp.databinding.FragmentNewTourBinding
import com.example.tourapp.model.TourModel
import com.example.tourapp.viewmodel.LoginViewModel
import com.example.tourapp.viewmodel.TourViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [newTourFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class newTourFragment : Fragment() {
    private lateinit var binding: FragmentNewTourBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private val tourViewModel: TourViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTourBinding.inflate(inflater, container, false)
        binding.saveTour.setOnClickListener(){
            val title = binding.tourName.text.toString()
            val destination = binding.whereToGo.text.toString()
            val budgetString = binding.tourBudget.text.toString()
            if(title.isNotEmpty() && destination.isNotEmpty() && budgetString.isNotEmpty()){
                val tourBudget = binding.tourBudget.text.toString().toInt()
                 val tourModel : TourModel = TourModel(
                     title = title,
                     destination = destination,
                     tourBudget = tourBudget,
                     userId = loginViewModel.user?.uid
                 )

                tourViewModel.addTour(tourModel)
                findNavController().popBackStack()

            }else{
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }


}