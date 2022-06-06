package com.example.medappcoursework.di

import androidx.room.Room
import com.example.medappcoursework.data.SanatoriumDatabase
import org.koin.dsl.module

val localDataBaseModule = module {
    single {
        Room.databaseBuilder(get(), SanatoriumDatabase::class.java, "Sanatorium.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<SanatoriumDatabase>().getDiseaseDao()
    }

    single {
        get<SanatoriumDatabase>().getPatientDao()
    }

    single {
        get<SanatoriumDatabase>().getTreatmentDao()
    }
}