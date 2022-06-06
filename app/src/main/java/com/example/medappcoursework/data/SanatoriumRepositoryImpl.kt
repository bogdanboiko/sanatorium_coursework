package com.example.medappcoursework.data

import com.example.medappcoursework.data.dao.DiseaseDao
import com.example.medappcoursework.data.dao.PatientDao
import com.example.medappcoursework.data.dao.TreatmentDao
import com.example.medappcoursework.data.entity.DiseaseEntity
import com.example.medappcoursework.data.entity.PatientEntity
import com.example.medappcoursework.data.entity.TreatmentEntity
import com.example.medappcoursework.domain.BaseModel.Patient
import com.example.medappcoursework.domain.BaseModel.Disease
import com.example.medappcoursework.domain.BaseModel.Treatment
import com.example.medappcoursework.domain.mapper.mapPatientEntityToDomain
import com.example.medappcoursework.domain.repository.SanatoriumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SanatoriumRepositoryImpl(
    private val diseaseDao: DiseaseDao,
    private val patientDao: PatientDao,
    private val treatmentDao: TreatmentDao
) : SanatoriumRepository {
    override suspend fun addDisease(disease: Disease) {
        diseaseDao.addDisease(DiseaseEntity(disease.id, disease.name, disease.description))
    }

    override fun getDiseaseList(): Flow<List<Disease>> {
        return diseaseDao.getAllCategories().map { list ->
            list.map {
                Disease(
                    it.id,
                    it.name,
                    it.description
                )
            }
        }
    }

    override suspend fun deleteCategory(diseaseId: String) {
        diseaseDao.deleteCategory(diseaseId)
    }

    override suspend fun addPatient(patient: Patient) {
        patientDao.insertPatient(
            PatientEntity(
                patient.id,
                patient.name,
                patient.bio,
                patient.diseaseId,
                patient.arrivalDate,
                patient.isArchived
            )
        )
    }

    override fun getPatient(patientId: String): Flow<Patient?> {
        return patientDao.getPatient(patientId).map {
            it?.let {
                mapPatientEntityToDomain(it)
            }
        }
    }

    override fun getPatientsDiseaseCategory(id: String): Flow<Disease> {
        return diseaseDao.getDiseaseCategory(id).map {
            Disease(
                it.id,
                it.name,
                it.description
            )
        }
    }

    override fun getPatientList(categoryId: String): Flow<List<Patient>> {
        return patientDao.getPatientList(categoryId).map {
            it.map(::mapPatientEntityToDomain)
        }
    }

    override suspend fun deletePatient(patientId: String) {
        patientDao.deletePatient(patientId)
    }

    override suspend fun movePatientToArchive(patientId: String) {
        patientDao.movePatientToArchive(patientId)
    }


    override suspend fun addTreatment(treatment: Treatment) {
        treatmentDao.insertTreatment(
            TreatmentEntity(
                treatment.id,
                treatment.patientId,
                treatment.procedure
            )
        )
    }

    override fun getPatientTreatment(treatmentId: String): Flow<Treatment?> {
        return treatmentDao.getPatientTreatment(treatmentId)
            .map { it?.let { Treatment(it.id, it.patientId, it.procedure) } }
    }

    override suspend fun deleteTreatment(treatmentId: String) {
        treatmentDao.deleteTreatment(treatmentId)
    }
}