package com.example.lazybone.main.ui.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class DateViewModel : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    private val _today = mutableStateOf(LocalDate.now())

    @RequiresApi(Build.VERSION_CODES.O)
    private val _selectedDate = mutableStateOf(LocalDate.now())

    @RequiresApi(Build.VERSION_CODES.O)
    val today: State<LocalDate> = _today

    @RequiresApi(Build.VERSION_CODES.O)
    val selectedDate: State<LocalDate> = _selectedDate

    @RequiresApi(Build.VERSION_CODES.O)
    fun setSelectedDate(date: LocalDate) {
        _selectedDate.value = date
    }
}