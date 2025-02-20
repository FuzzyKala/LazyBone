package com.example.lazybone.main.ui.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.lazybone.main.ui.components.exerciseDetails.Program
import com.example.lazybone.main.ui.components.exerciseDetails.Workout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class ProgramViewModel() : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val _programs = MutableStateFlow(
        listOf(Program(id = 1, date = LocalDate.now(), workouts = emptyList()))
    )

    @RequiresApi(Build.VERSION_CODES.O)
    val programs = _programs.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun addWorkoutToProgram(workout: Workout, dateForAddingWorkout: LocalDate) {
//        val programId = _programs.value.find { it.date.isEqual(dateForAddingWorkout) }?.id
        val existingProgram = _programs.value.find { it.date.isEqual(dateForAddingWorkout) }

        if (existingProgram != null) {
            val updatedWorkouts =
                existingProgram.workouts.filter { it.name != workout.name } + workout
            val updatedProgram = existingProgram.copy(workouts = updatedWorkouts)

            _programs.value =
                _programs.value.map { if (it.id == existingProgram.id) updatedProgram else it }
        } else {
            val newProgram = Program(
                id = _programs.value.size + 1,
                date = dateForAddingWorkout,
                workouts = listOf(workout)
            )
            _programs.value = _programs.value + newProgram
        }

        Log.d("ProgramUpdate", "Updated programs: ${_programs.value}")

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getProgramByDate(date: LocalDate): Program? {
        val program = _programs.value.find { it.date.isEqual(date) }
        Log.d("Debug", "Searched for program on date: $date, found: $program")
        return program
    }


}
