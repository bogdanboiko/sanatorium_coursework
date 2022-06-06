package com.example.medappcoursework.data.response

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation
import com.example.medappcoursework.data.entity.PatientEntity
import com.example.medappcoursework.data.entity.TreatmentEntity
import java.util.*

data class PatientResponse(
    @Embedded
    val patient: PatientEntity,
    @Relation(parentColumn = "id", entityColumn = "patientId")
    val treatmentHistory: List<TreatmentEntity>
)