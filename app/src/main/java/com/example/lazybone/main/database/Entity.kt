//import androidx.room.Embedded
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import androidx.room.Relation
//
//@Entity(tableName = "sets")
//data class SetEntity(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val workoutId: Int,
//    val weight: Double,
//    val reps: Int
//)
//
//@Entity(tableName = "workouts")
//data class WorkoutEntity(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val programId: Int,
//    val name: String
//)
//
//@Entity(tableName = "programs")
//data class ProgramEntity(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val date: String
//)
//
//data class WorkoutWithSets(
//    @Embedded val workout: WorkoutEntity,
//
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "workoutId"
//    )
//    val sets: List<SetEntity>
//)
//
//data class ProgramWithWorkouts(
//    @Embedded val program: ProgramEntity,
//
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "programId"
//    )
//    val workouts: List<WorkoutEntity>
//)