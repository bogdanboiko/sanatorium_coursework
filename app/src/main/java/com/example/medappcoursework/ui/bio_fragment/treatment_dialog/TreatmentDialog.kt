package com.example.medappcoursework.ui.bio_fragment.treatment_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.medappcoursework.databinding.DialogTreatmentBinding
import com.example.medappcoursework.domain.BaseModel
import com.example.medappcoursework.ui.bio_fragment.BioViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TreatmentDialog : DialogFragment() {
    private lateinit var binding: DialogTreatmentBinding
    private val viewModel: BioViewModel by viewModel()
    private val arguments by navArgs<TreatmentDialogArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogTreatmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configButtons()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPatientTreatment(arguments.treatmentId).collect {
                it?.let {
                    binding.procedureEditText.setText(it.procedure)
                }
            }
        }
    }

    private fun configButtons() {
        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        binding.submitTreatmentButton.setOnClickListener {
            if (binding.procedureEditText.text.isEmpty()) {
                Toast.makeText(
                    context,
                    "You must fill all fields before confirmation",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                viewModel.insertOrUpdateTreatment(
                    BaseModel.Treatment(
                        arguments.treatmentId,
                        arguments.patientId,
                        binding.procedureEditText.text.toString()
                    )
                )
                dismiss()
            }
        }
    }
}