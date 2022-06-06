package com.example.medappcoursework.ui.patients_fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medappcoursework.databinding.FragmentPatientListBinding
import com.example.medappcoursework.domain.BaseModel
import com.example.medappcoursework.ui.callback.DeletionCallback
import com.example.medappcoursework.ui.callback.NavigationCallback
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class PatientListFragment : Fragment(), NavigationCallback, DeletionCallback {
    private lateinit var dataJob: Job
    private lateinit var binding: FragmentPatientListBinding
    private val arguments by navArgs<PatientListFragmentArgs>()
    private val viewModel: PatientListViewModel by viewModel()
    private val adapter = PatientAdapter(this, this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPatientListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configCaption()
        configAddPatientButton()
        configPatientRecycler()
        configDataButtons()
        fetchData(false)
    }

    private fun fetchData(isFromArchive: Boolean) {
        if (this::dataJob.isInitialized) {
            dataJob.cancel()
        }

        dataJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPatientList(arguments.diseaseId).collect {
                if (isFromArchive) {
                    binding.placeholderTextView.visibility = View.INVISIBLE
                    adapter.submitList(it.filter { patient -> patient.isArchived })
                } else {
                    if (it.isEmpty()) {
                        binding.placeholderTextView.visibility = View.VISIBLE
                    } else {
                        binding.placeholderTextView.visibility = View.INVISIBLE
                    }

                    adapter.submitList(it.filter { patient -> !patient.isArchived })
                }
            }
        }
    }

    private fun configDataButtons() {
        binding.accountingButton.setOnClickListener {
            binding.archiveButton.setBackgroundColor(UNPRESSED_BUTTON_COLOR)
            it.setBackgroundColor(PRESSED_BUTTON_COLOR)
            fetchData(false)
        }

        binding.archiveButton.setOnClickListener {
            fetchData(true)
            binding.accountingButton.setBackgroundColor(UNPRESSED_BUTTON_COLOR)
            it.setBackgroundColor(PRESSED_BUTTON_COLOR)
        }
    }

    private fun configPatientRecycler() {
        binding.patientRecycler.apply {
            adapter = this@PatientListFragment.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        fetchData(false)
    }

    private fun configAddPatientButton() {
        binding.addPatientButton.setOnClickListener {
            findNavController().navigate(
                PatientListFragmentDirections.actionPatientListFragmentToPatientBioFragment(
                    arguments.diseaseId,
                    UUID.randomUUID().toString()
                )
            )
        }
    }

    private fun configCaption() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getDiseaseCategory(arguments.diseaseId).collect {
                binding.categoryCaptionTextView.text = it.name
                binding.categoryDescriptionTextView.text = it.description

            }
        }
    }

    override fun onItemClick(model: BaseModel) {
        findNavController().navigate(
            PatientListFragmentDirections.actionPatientListFragmentToPatientBioFragment(
                arguments.diseaseId,
                model.id
            )
        )
    }

    override fun delete(baseModel: BaseModel) {
        viewModel.deletePatient(baseModel.id)
    }

    companion object {
        val PRESSED_BUTTON_COLOR = Color.rgb(33, 150, 243)
        val UNPRESSED_BUTTON_COLOR = Color.rgb(63, 81, 181)
    }
}