package com.example.medappcoursework.data.dao

import androidx.room.*
import com.example.medappcoursework.data.entity.TreatmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TreatmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTreatment(treatment: TreatmentEntity)

    @Query("SELECT * FROM treatment WHERE patientId = :patientId")
    abstract fun getPatientTreatmentHistory(patientId: String): Flow<List<TreatmentEntity>>

    @Query("SELECT * FROM treatment WHERE id = :treatmentId")
    abstract fun getPatientTreatment(treatmentId: String): Flow<TreatmentEntity?>

    @Query("DELETE FROM treatment WHERE id = :treatmentId")
    abstract fun deleteTreatment(treatmentId: String)
}