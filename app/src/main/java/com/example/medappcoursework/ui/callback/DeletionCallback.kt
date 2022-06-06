package com.example.medappcoursework.ui.callback

import com.example.medappcoursework.domain.BaseModel

interface DeletionCallback {
    fun delete(baseModel: BaseModel)
}