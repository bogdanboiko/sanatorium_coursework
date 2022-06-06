package com.example.medappcoursework.data.dao

import androidx.room.*
import com.example.medappcoursework.data.entity.PatientEntity
import com.example.medappcoursework.data.response.PatientResponse
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PatientDao {
    @Query("SELECT * FROM patient WHERE diseaseId = :categoryId")
    abstract fun getPatientList(categoryId: String): Flow<List<PatientResponse>>

    @Query("SELECT * FROM patient WHERE id = :patientId")
    abstract fun getPatient(patientId: String): Flow<PatientResponse?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPatient(patient: PatientEntity)

    @Query("DELETE FROM patient WHERE id = :patientId")
    abstract fun deletePatient(patientId: String)

    @Query("UPDATE patient SET isArchived = 1 WHERE id = :patientId")
    abstract fun movePatientToArchive(patientId: String)
}