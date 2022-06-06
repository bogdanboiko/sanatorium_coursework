package com.example.medappcoursework.ui.categories_fragment.category_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.medappcoursework.databinding.DialogDiseaseCategoryDetailsBinding
import com.example.medappcoursework.ui.categories_fragment.CategoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiseaseCategoryDetailsDialog : DialogFragment() {
    private lateinit var binding: DialogDiseaseCategoryDetailsBinding
    private val viewModel: CategoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogDiseaseCategoryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configButtons()

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun configButtons() {
        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        binding.submitDiseaseCategoryButton.setOnClickListener {
            val caption = binding.diseaseCaptionEditText.text.toString()
            val description = binding.diseaseDetailsEditText.text.toString()
            if (caption.isEmpty() || caption.isEmpty()) {
                Toast.makeText(context, "Some of fields are empty, please fill them", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addCategory(caption, description)
                dismiss()
            }
        }
    }
}