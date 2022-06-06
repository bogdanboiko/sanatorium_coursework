package com.example.medappcoursework.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import java.util.*

@Entity(
    tableName = "patient",
    primaryKeys = ["id"],
    foreignKeys = [ForeignKey(
        entity = DiseaseEntity::class,
        parentColumns = ["id"],
        childColumns = ["diseaseId"],
        onDelete = CASCADE
    )]
)
data class PatientEntity(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "bio")
    val bio: String,
    @ColumnInfo(name = "diseaseId")
    val diseaseId: String,
    @ColumnInfo(name = "arrivalDate")
    val arrivalDate: Date,
    @ColumnInfo(name = "isArchived")
    val isArchived: Boolean
)
