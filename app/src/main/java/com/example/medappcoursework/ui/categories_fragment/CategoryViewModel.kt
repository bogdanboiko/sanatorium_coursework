package com.example.medappcoursework.ui.categories_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medappcoursework.domain.BaseModel
import com.example.medappcoursework.domain.repository.SanatoriumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CategoryViewModel(private val sanatoriumRepository: SanatoriumRepository) : ViewModel() {
    val categories = sanatoriumRepository.getDiseaseList()

    fun addCategory(name: String, description: String) {
        viewModelScope.launch {
            sanatoriumRepository.addDisease(BaseModel.Disease(UUID.randomUUID().toString(), name, description))
        }
    }

    fun deleteCategory(diseaseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            sanatoriumRepository.deleteCategory(diseaseId)
        }
    }
}