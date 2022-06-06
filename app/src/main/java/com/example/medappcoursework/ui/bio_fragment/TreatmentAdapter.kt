package com.example.medappcoursework.ui.bio_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.medappcoursework.databinding.ItemCategoryBinding
import com.example.medappcoursework.domain.BaseModel.Treatment
import com.example.medappcoursework.ui.callback.DeletionCallback
import com.example.medappcoursework.ui.callback.NavigationCallback

class TreatmentAdapter(private val navigation: NavigationCallback, private val deletion: DeletionCallback) :
    ListAdapter<Treatment, TreatmentAdapter.CategoryViewHolder>(Diff) {
    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(treatment: Treatment) {
            binding.textView.text = treatment.procedure

            itemView.setOnClickListener {
                navigation.onItemClick(treatment)
            }

            binding.deleteButton.setOnClickListener {
                deletion.delete(treatment)
            }
        }
    }

    object Diff : DiffUtil.ItemCallback<Treatment>() {
        override fun areItemsTheSame(oldItem: Treatment, newItem: Treatment): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Treatment, newItem: Treatment): Boolean {
            return oldItem.id == newItem.id && oldItem.patientId == newItem.patientId && oldItem.procedure == newItem.procedure
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