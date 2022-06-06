package com.example.medappcoursework.domain.mapper

import com.example.medappcoursework.data.response.PatientResponse
import com.example.medappcoursework.domain.BaseModel
import com.example.medappcoursework.domain.BaseModel.Patient

fun mapPatientEntityToDomain(patientResponse: PatientResponse) : Patient {
    return Patient(
        patientResponse.patient.id,
        patientResponse.patient.name,
        patientResponse.patient.bio,
        patientResponse.patient.diseaseId,
        patientResponse.patient.arrivalDate,
        patientResponse.patient.isArchived,
        patientResponse.treatmentHistory.map { treatment ->
            BaseModel.Treatment(
                treatment.id,
                treatment.patientId,
                treatment.procedure
            )
        }
    )
}