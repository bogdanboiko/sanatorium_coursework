package com.example.medappcoursework.di

import com.example.medappcoursework.ui.bio_fragment.BioViewModel
import com.example.medappcoursework.ui.categories_fragment.CategoryViewModel
import com.example.medappcoursework.ui.patients_fragment.PatientListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CategoryViewModel(get())
    }

    viewModel {
        PatientListViewModel(get())
    }

    viewModel {
        BioViewModel(get())
    }
}