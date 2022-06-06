package com.example.medappcoursework.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "treatment", primaryKeys = ["id"], foreignKeys = [ForeignKey(
    entity = PatientEntity::class,
    parentColumns = ["id"],
    childColumns = ["patientId"],
    onDelete = ForeignKey.CASCADE)])
data class TreatmentEntity(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "patientId")
    val patientId: String,
    @ColumnInfo(name = "procedure")
    val procedure: String
)
