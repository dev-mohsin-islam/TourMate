package com.example.tourapp.model

import com.google.firebase.Timestamp
import java.sql.Time

data class TourModel(
    var id: String? =null,
    val userId: String? = null,
    val title: String? = null,
    val destination: String? = null,
    val tourBudget: Int? = null,
    val isCompleted: Boolean = false,
    val createdAt: Timestamp = Timestamp.now(),

    )
