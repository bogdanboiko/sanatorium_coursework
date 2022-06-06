package com.example.medappcoursework.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.medappcoursework.data.dao.DiseaseDao
import com.example.medappcoursework.data.dao.PatientDao
import com.example.medappcoursework.data.dao.TreatmentDao
import com.example.medappcoursework.data.entity.DiseaseEntity
import com.example.medappcoursework.data.entity.PatientEntity
import com.example.medappcoursework.data.entity.TreatmentEntity

@Database(entities = [DiseaseEntity::class, PatientEntity::class, TreatmentEntity::class], version = 6)
@TypeConverters(Converters::class)
abstract class SanatoriumDatabase : RoomDatabase() {
    abstract fun getDiseaseDao(): DiseaseDao
    abstract fun getPatientDao(): PatientDao
    abstract fun getTreatmentDao(): TreatmentDao

}