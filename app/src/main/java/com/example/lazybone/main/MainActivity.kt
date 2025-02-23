package com.example.lazybone.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lazybone.main.ui.navigation.AppNavController
import com.example.lazybone.main.ui.theme.LazyBoneTheme
import com.example.lazybone.main.ui.viewModel.ExerciseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ExerciseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyBoneTheme {
                AppNavController()
            }
        }
    }
}


