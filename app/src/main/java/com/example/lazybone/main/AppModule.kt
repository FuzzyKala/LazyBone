package com.example.lazybone.main

import com.example.lazybone.main.api.ExerciseRepository
import com.example.lazybone.main.api.RetrofitInstance
import com.example.lazybone.main.ui.viewModel.ExerciseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RetrofitInstance.api } // Provide API service
    single { ExerciseRepository(get()) } // Provide ExerciseRepository
    viewModel { ExerciseViewModel(get()) } // Provide ExerciseViewModel
}
