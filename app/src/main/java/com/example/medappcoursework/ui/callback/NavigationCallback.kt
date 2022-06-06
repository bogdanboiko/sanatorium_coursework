package com.example.medappcoursework.ui.callback

import com.example.medappcoursework.domain.BaseModel

interface NavigationCallback {
    fun onItemClick(model: BaseModel)
}