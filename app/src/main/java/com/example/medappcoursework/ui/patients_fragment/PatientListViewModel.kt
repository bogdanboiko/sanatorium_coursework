package com.example.medappcoursework.ui.patients_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medappcoursework.domain.BaseModel.Patient
import com.example.medappcoursework.domain.BaseModel.Disease
import com.example.medappcoursework.domain.repository.SanatoriumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PatientListViewModel(private val sanatoriumRepository: SanatoriumRepository) : ViewModel() {
    fun getDiseaseCategory(id: String): Flow<Disease> {
        return sanatoriumRepository.getPatientsDiseaseCategory(id)
    }

    fun getPatientList(id: String): Flow<List<Patient>> {
        return sanatoriumRepository.getPatientList(id)
    }

    fun deletePatient(patientId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            sanatoriumRepository.deletePatient(patientId)
        }
    }
}