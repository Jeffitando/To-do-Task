package com.example.task.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task.R
import com.example.task.databinding.SplashFragmentBinding


class SplashFragment : Fragment() {

    private var _binding: SplashFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    // As funcôes devem ser chamadas dentro do (OnViewCreated)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Tempo em que a Splash vai ficar visivel para o usuario
        Handler(Looper.getMainLooper()).postDelayed(this::checkAuth, 5000)
    }

    private fun checkAuth() {
        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
