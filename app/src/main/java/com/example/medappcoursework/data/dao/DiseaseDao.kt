package com.example.medappcoursework.data.dao

import androidx.room.*
import com.example.medappcoursework.data.entity.DiseaseEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DiseaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addDisease(disease: DiseaseEntity)

    @Query("SELECT * FROM disease")
    abstract fun getAllCategories(): Flow<List<DiseaseEntity>>

    @Query("SELECT * FROM disease WHERE id =:id")
    abstract fun getDiseaseCategory(id: String): Flow<DiseaseEntity>

    @Query("DELETE FROM disease WHERE id = :diseaseId")
    abstract fun deleteCategory(diseaseId: String)
}