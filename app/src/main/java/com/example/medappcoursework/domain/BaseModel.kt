package com.example.medappcoursework.domain

import java.util.*

sealed class BaseModel {
    abstract val id: String

    data class Disease(override val id: String, val name: String, val description: String) : BaseModel()

    data class Patient(
        override val id: String,
        val name: String,
        val bio: String,
        val diseaseId: String,
        val arrivalDate: Date,
        val isArchived: Boolean,
        val treatmentHistory: List<Treatment> = emptyList()
    ) : BaseModel()

    data class Treatment(override val id: String, val patientId: String, val procedure: String) : BaseModel()
}
