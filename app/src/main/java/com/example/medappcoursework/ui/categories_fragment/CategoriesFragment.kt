package com.example.medappcoursework.ui.categories_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medappcoursework.databinding.FragmentCategoriesBinding
import com.example.medappcoursework.domain.BaseModel
import com.example.medappcoursework.ui.callback.DeletionCallback
import com.example.medappcoursework.ui.callback.NavigationCallback
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class CategoriesFragment : Fragment(), NavigationCallback, DeletionCallback {
    private val categoryAdapter = CategoryAdapter(this, this)
    private lateinit var binding: FragmentCategoriesBinding
    private val viewModel: CategoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configCategoryRecycler()
        configConfirmButton()
    }

    private fun configCategoryRecycler() {
        binding.diseaseCategoryRecycler.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.categories.collect {
                if (it.isEmpty()) {
                    binding.placeholderTextView.visibility = View.VISIBLE
                } else {
                    binding.placeholderTextView.visibility = View.INVISIBLE
                }

                categoryAdapter.submitList(it)
            }
        }
    }

    private fun configConfirmButton() {
        binding.addCategoryButton.setOnClickListener {
            findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToDiseaseCategoryDetailsDialog())
        }
    }

    override fun onItemClick(model: BaseModel) {
        findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToPatientListFragment(model.id))
    }

    override fun delete(baseModel: BaseModel) {
        viewModel.deleteCategory(baseModel.id)
    }
}