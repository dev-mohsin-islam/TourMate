package com.example.tourapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tourapp.model.TourModel
import com.example.tourapp.repo.TourRepository

class TourViewModel : ViewModel(){
    private val repository = TourRepository()
    fun addTour(tourModel: TourModel) {
        repository.addTour(tourModel)
    }
    fun getTourByUserId(userId: String) = repository.getToursByUserID(userId)

    fun updateTourStatus(tourModel: TourModel) {
        TODO("Not yet implemented")
    }
}