package com.example.medappcoursework.di

import com.example.medappcoursework.data.SanatoriumRepositoryImpl
import com.example.medappcoursework.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single<SanatoriumRepository> {
        SanatoriumRepositoryImpl(get(), get(), get())
    }
}