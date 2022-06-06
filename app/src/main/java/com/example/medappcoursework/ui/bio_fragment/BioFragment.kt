package com.example.medappcoursework.ui.bio_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medappcoursework.databinding.FragmentPatientBioBinding
import com.example.medappcoursework.domain.BaseModel
import com.example.medappcoursework.domain.BaseModel.Patient
import com.example.medappcoursework.ui.callback.DeletionCallback
import com.example.medappcoursework.ui.callback.NavigationCallback
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class BioFragment : Fragment(), NavigationCallback, DeletionCallback {
    private lateinit var binding: FragmentPatientBioBinding
    private val viewModel: BioViewModel by viewModel()
    private val arguments by navArgs<BioFragmentArgs>()
    private val treatmentAdapter = TreatmentAdapter(this, this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPatientBioBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.arrivalDatePicker.maxDate = Date().time
        fetchPatientData()
        configEditButton()
        configConfirmButton()
        configCancelButton()
        configArchiveButton()
        configAddTreatmentButton()
        configTreatmentRecycler()
    }

    private fun configArchiveButton() {
        binding.archiveButton.setOnClickListener {
            viewModel.movePatientToArchive(arguments.patientId)
        }
    }

    private fun configCancelButton() {
        binding.cancelButton.setOnClickListener {
            switchVisibility(false)
        }
    }

    private fun configAddTreatmentButton() {
        binding.addTreatmentButton.setOnClickListener {
            findNavController().navigate(
                BioFragmentDirections.actionPatientBioFragmentToPatientTreatmentDialog(
                    UUID.randomUUID().toString(),
                    arguments.patientId
                )
            )
        }
    }

    private fun fetchPatientData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPatientInfo(arguments.patientId).collect {
                with(binding) {
                    it?.let {
                        if (it.isArchived) {
                            archiveButton.visibility = View.GONE
                        }

                        nameEditText.setText(it.name)
                        bioEditText.setText(it.bio)
                        val format = SimpleDateFormat("dd/MM/yyyy")
                        val date = format.format(it.arrivalDate)
                        arrivalDateEditText.text = "Arrival date: $date"

                        if (it.treatmentHistory.isEmpty()) {
                            placeholderTextView.visibility = View.VISIBLE
                        } else {
                            placeholderTextView.visibility = View.INVISIBLE
                        }

                        treatmentAdapter.submitList(it.treatmentHistory)
                    }

                }
            }
        }
    }

    private fun configConfirmButton() {
        binding.confirmPatientInfoButton.setOnClickListener {
            if (validateInput()) {
                with(binding) {
                    val calendar = Calendar.getInstance()
                    calendar.set(
                        arrivalDatePicker.year,
                        arrivalDatePicker.month,
                        arrivalDatePicker.dayOfMonth
                    )

                    viewModel.insertOrUpdatePatientInfo(
                        Patient(
                            arguments.patientId,
                            nameEditText.text.toString(),
                            bioEditText.text.toString(),
                            arguments.diseaseId,
                            calendar.time,
                            false
                        )
                    )

                    switchVisibility(false)
                }

                switchFieldsBehavior(false)
            }
        }
    }

    private fun switchVisibility(isVisible: Boolean) {
        with(binding) {
            if (isVisible) {
                editPatientInfoButton.visibility = View.GONE
                arrivalDateEditText.visibility = View.GONE
                addTreatmentButton.visibility = View.GONE
                arrivalDatePicker.visibility = View.VISIBLE
                confirmPatientInfoButton.visibility = View.VISIBLE
                cancelButton.visibility = View.VISIBLE
                archiveButton.visibility = View.GONE
            } else {
                arrivalDateEditText.visibility = View.VISIBLE
                addTreatmentButton.visibility = View.VISIBLE
                arrivalDatePicker.visibility = View.GONE
                confirmPatientInfoButton.visibility = View.GONE
                editPatientInfoButton.visibility = View.VISIBLE
                archiveButton.visibility = View.VISIBLE
                cancelButton.visibility = View.GONE
            }
        }
    }

    private fun validateInput(): Boolean {
        with(binding) {
            return if (nameEditText.text.isEmpty() || bioEditText.text.isEmpty()
            ) {
                Toast.makeText(
                    context,
                    "You must fill all fields before confirmation",
                    Toast.LENGTH_LONG
                ).show()
                false
            } else {
                true
            }
        }
    }

    private fun configEditButton() {
        with(binding) {
            editPatientInfoButton.setOnClickListener {
                switchVisibility(true)
                switchFieldsBehavior(true)
            }
        }
    }

    private fun switchFieldsBehavior(isEnabled: Boolean) {
        with(binding) {
            nameEditText.isEnabled = isEnabled
            bioEditText.isEnabled = isEnabled
        }
    }

    private fun configTreatmentRecycler() {
        binding.treatmentRecycler.apply {
            adapter = treatmentAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onItemClick(model: BaseModel) {
        findNavController().navigate(
            BioFragmentDirections.actionPatientBioFragmentToPatientTreatmentDialog(
                model.id,
                arguments.patientId
            )
        )
    }

    override fun delete(baseModel: BaseModel) {
        viewModel.deleteTreatment(baseModel.id)
    }
}