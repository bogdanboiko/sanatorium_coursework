package com.example.medappcoursework.ui.patients_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.medappcoursework.databinding.ItemCategoryBinding
import com.example.medappcoursework.domain.BaseModel.Patient
import com.example.medappcoursework.ui.callback.DeletionCallback
import com.example.medappcoursework.ui.callback.NavigationCallback

class PatientAdapter(private val navigation: NavigationCallback, private val deletion: DeletionCallback) :
    ListAdapter<Patient, PatientAdapter.CategoryViewHolder>(Diff) {
    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(patient: Patient) {
            binding.textView.text = patient.name

            itemView.setOnClickListener {
                navigation.onItemClick(patient)
            }

            binding.deleteButton.setOnClickListener {
                deletion.delete(patient)
            }
        }
    }

    object Diff : DiffUtil.ItemCallback<Patient>() {
        override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}