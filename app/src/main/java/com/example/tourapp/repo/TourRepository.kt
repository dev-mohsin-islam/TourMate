package com.example.tourapp.repo

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tourapp.model.TourModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TourRepository {
    val db = Firebase.firestore
    fun addTour(tourModel: TourModel) {
        db.collection(tour_collection)
            .add(tourModel)
            .addOnSuccessListener { documentReference ->
                Log.d("TourRepository", "Tour added successfully with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.e("TourRepository", "Failed to add tour", e)
            }
    }


    companion object {
        val tour_collection = "tour_collection"

    }

    fun getToursByUserID(userId: String): LiveData<List<TourModel>> {
        val tourListLiveData = MutableLiveData<List<TourModel>>()

        // Start listening to the Firestore collection
        db.collection(tour_collection)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    // Log the error and return
                    Log.e("TourRepository", "Error fetching tours by userId: $userId", error)
                    return@addSnapshotListener
                }

                if (value != null && !value.isEmpty) {
                    // Convert documents to TourModel objects
                    val temp = ArrayList<TourModel>()
                    for (doc in value) {
                        try {
                            temp.add(doc.toObject(TourModel::class.java))
                        } catch (e: Exception) {
                            Log.e("TourRepository", "Error converting document to TourModel", e)
                        }
                    }
                    // Post the value to LiveData
                    tourListLiveData.postValue(temp)
                } else {
                    // If no data or empty, post an empty list
                    tourListLiveData.postValue(emptyList())
                }
            }

        return tourListLiveData
    }

    fun getTourById(tourID: String) : LiveData<TourModel> {
        val tourLiveData = MutableLiveData<TourModel>()
        db.collection(tour_collection).document(tourID)
            .addSnapshotListener() { value, error ->
                if (error != null) {
                    Log.e("TourRepository", "Error fetching tour")
                    return@addSnapshotListener
                }
                tourLiveData.postValue(value!!.toObject(TourModel::class.java))

            }
        return  tourLiveData
    }


}