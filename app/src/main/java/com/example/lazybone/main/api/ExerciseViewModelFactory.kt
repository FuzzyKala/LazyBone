package com.example.lazybone.main.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lazybone.main.ui.viewModel.ExerciseViewModel


// Allows for injecting dependencies (like ExerciseRepository) into ViewModel.
class ExerciseViewModelFactory(private val repository: ExerciseRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExerciseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
