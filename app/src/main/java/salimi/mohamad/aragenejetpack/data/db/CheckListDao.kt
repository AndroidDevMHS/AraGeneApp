package salimi.mohamad.aragenejetpack.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import salimi.mohamad.aragenejetpack.data.model.FahliCheckList

@Dao
interface CheckListDao {
    @Query("SELECT * FROM checkListTable")
    fun getAllItems(): Flow<List<FahliCheckList>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addNewStudent(student: FahliCheckList)

    @Update
    suspend fun updateStudent(student: FahliCheckList)

    @Delete
    suspend fun deleteStudent(student: FahliCheckList)

    @Query("DELETE FROM checkListTable")
    suspend fun deleteAllStudents()
}