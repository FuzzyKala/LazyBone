import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity)

    @Query("SELECT * FROM workouts ORDER BY id DESC")
    fun getAllWorkouts(): Flow<List<WorkoutEntity>>

    @Transaction
    @Query("SELECT * FROM workouts WHERE id = :workoutId")
    fun getWorkoutWithSets(workoutId: Int): Flow<WorkoutWithSets>

    @Delete
    suspend fun deleteWorkout(workout: WorkoutEntity)
}

@Dao
interface ProgramDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgram(program: ProgramEntity)

    @Query("SELECT * FROM programs WHERE date = :date")
    fun getProgramByDate(date: String): List<ProgramEntity>

    @Delete
    suspend fun deleteProgram(program: ProgramEntity)

    @Transaction
    @Query("SELECT * FROM programs WHERE id = :programId")
    fun getProgramWithWorkouts(programId: Int): Flow<ProgramWithWorkouts>
}
