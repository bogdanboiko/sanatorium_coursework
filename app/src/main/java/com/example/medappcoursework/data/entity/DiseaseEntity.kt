package com.example.medappcoursework.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "disease", primaryKeys = ["id"])
data class DiseaseEntity(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String
)
