package com.example.medappcoursework.ui.categories_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.medappcoursework.databinding.ItemCategoryBinding
import com.example.medappcoursework.domain.BaseModel.Disease
import com.example.medappcoursework.ui.callback.DeletionCallback
import com.example.medappcoursework.ui.callback.NavigationCallback

class CategoryAdapter(private val navigation: NavigationCallback, private val deletion: DeletionCallback) :
    ListAdapter<Disease, CategoryAdapter.CategoryViewHolder>(Diff) {
    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(disease: Disease) {
            binding.textView.text = disease.name

            itemView.setOnClickListener {
                navigation.onItemClick(disease)
            }

            binding.deleteButton.setOnClickListener {
                deletion.delete(disease)
            }
        }
    }

    object Diff : DiffUtil.ItemCallback<Disease>() {
        override fun areItemsTheSame(oldItem: Disease, newItem: Disease): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Disease, newItem: Disease): Boolean {
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