package com.example.medappcoursework.domain.repository

import com.example.medappcoursework.domain.BaseModel.Disease
import com.example.medappcoursework.domain.BaseModel.Patient
import com.example.medappcoursework.domain.BaseModel.Treatment
import kotlinx.coroutines.flow.Flow

interface SanatoriumRepository {
    suspend fun addDisease(disease: Disease)
    fun getDiseaseList() : Flow<List<Disease>>
    suspend fun deleteCategory(diseaseId: String)

    suspend fun addPatient(patient: Patient)
    fun getPatient(patientId: String): Flow<Patient?>
    fun getPatientList(categoryId: String): Flow<List<Patient>>
    suspend fun deletePatient(patientId: String)
    suspend fun movePatientToArchive(patientId: String)
    fun getPatientsDiseaseCategory(id: String): Flow<Disease>

    suspend fun addTreatment(treatment: Treatment)
    fun getPatientTreatment(treatmentId: String): Flow<Treatment?>
    suspend fun deleteTreatment(treatmentId: String)
}