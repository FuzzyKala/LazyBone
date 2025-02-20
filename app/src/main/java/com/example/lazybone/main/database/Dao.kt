//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.example.lazybone.main.ui.components.exerciseDetails.Program
//import com.example.lazybone.main.ui.components.exerciseDetails.Workout
//
//@Dao
//interface WorkoutDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertWorkout(workout: Workout)
//
//    @Query("SELECT * FROM workouts ORDER BY id DESC")
//    fun getAllWorkouts(): kotlinx.coroutines.flow.Flow<List<Workout>>
//
//    @Delete
//    suspend fun deleteWorkout(workout: Workout)
//}
//
//@Dao
//interface ProgramDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertProgram(program: Program)
//
//    @Query("SELECT * FROM programs WHERE date = :date")
//    fun getProgramByDate(date: String): List<Program>
//
//    @Delete
//    suspend fun deleteProgram(program: Program)
//}
