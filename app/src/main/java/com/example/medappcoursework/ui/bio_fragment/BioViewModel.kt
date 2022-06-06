package com.example.medappcoursework.ui.bio_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medappcoursework.domain.BaseModel.Treatment
import com.example.medappcoursework.domain.BaseModel.Patient
import com.example.medappcoursework.domain.repository.SanatoriumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BioViewModel(private val repository: SanatoriumRepository) : ViewModel() {
    fun getPatientInfo(patientId: String): Flow<Patient?> {
        return repository.getPatient(patientId)
    }

    fun insertOrUpdatePatientInfo(patient: Patient) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPatient(patient)
        }
    }

    fun insertOrUpdateTreatment(treatment: Treatment) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTreatment(treatment)
        }
    }

    fun movePatientToArchive(patientId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.movePatientToArchive(patientId)
        }
    }

    fun getPatientTreatment(treatmentId: String): Flow<Treatment?> {
        return repository.getPatientTreatment(treatmentId)
    }

    fun deleteTreatment(treatmentId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTreatment(treatmentId)
        }
    }
}