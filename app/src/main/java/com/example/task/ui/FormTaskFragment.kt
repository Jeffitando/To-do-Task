package com.example.task.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.task.R
import com.example.task.databinding.FragmentFormTaskBinding
import com.example.task.helper.FirebaseHelper
import com.example.task.model.Task


class FormTaskFragment : Fragment() {

    private val args: FormTaskFragmentArgs by navArgs()

    private var _binding: FragmentFormTaskBinding? = null
    private val binding get() = _binding!!


    private lateinit var task: Task
    private var newTask: Boolean = true
    private var statusTask: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListners()

        getArgs()
    }

    private fun getArgs() {
        args.task.let {
            if (it != null) {
                task = it

                newTask = false
                statusTask = task.status
                binding.textToolbar.text = "Editando tarefa..."
            }
        }
    }

    private fun initListners() {
        binding.btnSave.setOnClickListener { validateTask() }
        // na segunda underline estava um ( i )
        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            statusTask = when (id) {
                R.id.rbTodo -> 0
                R.id.rbDoing -> 1
                else -> 2

            }
        }

    }

    private fun validateTask() {


        val description = binding.edtDescription.text.toString().trim()

        if (description.isNotEmpty()) {
            binding.progressBar.isVisible = true

            if (newTask) task = Task()
            task.description = description
            task.status = statusTask

            saveTask()

        } else {
            Toast.makeText(
                requireContext(), "Informe uma descrição para a tarefa.", Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun saveTask() {
        FirebaseHelper.getDatabase().child("task").child(FirebaseHelper.getIdUser() ?: "")
            .child(task.id).setValue(task).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (newTask) {// Nova tarefa
                        //quando filanizar a tarefa a tela vai fechar e voltar para a anterior
                        findNavController().popBackStack()
                        Toast.makeText(
                            requireContext(), "Tarefa salva com sucesso.", Toast.LENGTH_SHORT
                        ).show()
                    } else { // Editando tarefa
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            requireContext(), "Tarefa atualizada com sucesso.", Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(), "Tarefa salva com sucesso.", Toast.LENGTH_SHORT
                    ).show()
                }
            }.addOnFailureListener {
                binding.progressBar.isVisible = false
                Toast.makeText(requireContext(), "Erro ao salvar tarefa.", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}