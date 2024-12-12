package salimi.mohamad.aragenejetpack.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import salimi.mohamad.aragenejetpack.data.model.PlannerItem


@Dao
interface PlannerDao {

    @Query("SELECT * FROM plannerTable")
    fun getAllMessage(): Flow<List<PlannerItem>>

    @Insert
    suspend fun addNewPlan(plan: PlannerItem): Long

    @Query("UPDATE plannerTable Set done=:done WHERE planId=:planId")
    suspend fun updateDone(planId: Int, done: Boolean)

    @Query("UPDATE plannerTable Set showSono=:showSono WHERE planId=:planId")
    suspend fun updateShowSono(planId: Int, showSono: Boolean)

    @Query("UPDATE plannerTable Set showBuyPack=:showBuyPack WHERE planId=:planId")
    suspend fun updateShowBuyPack(planId: Int, showBuyPack: Boolean)

    @Query("UPDATE plannerTable Set showVideoLink=:showVideoLink WHERE planId=:planId")
    suspend fun updateShowVideoLink(planId: Int, showVideoLink: Boolean)

    @Delete
    suspend fun deletePlan(plan: PlannerItem)

}