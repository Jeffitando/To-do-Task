package com.example.task.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.task.databinding.FragmentRegisterBinding
import com.example.task.databinding.FragmentRegisterBinding.inflate


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun validateData() {
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()

        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {

                binding.progressBar.isVisible = true

                registerUser(email, password)

            } else {
                Toast.makeText(requireContext(),"Informe sua senha.", LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(requireContext(),"Informe seu e-mail.", LENGTH_SHORT).show()
        }
    }

    private fun registerUser(email: String, password: String){

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}





