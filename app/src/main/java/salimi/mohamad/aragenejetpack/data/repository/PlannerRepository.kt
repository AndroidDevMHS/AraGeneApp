package salimi.mohamad.aragenejetpack.data.repository

import kotlinx.coroutines.flow.Flow
import salimi.mohamad.aragenejetpack.data.db.PlannerDao
import salimi.mohamad.aragenejetpack.data.model.PlannerItem
import javax.inject.Inject

class PlannerRepository @Inject constructor(private val plannerDao: PlannerDao) {

    val allMessage: Flow<List<PlannerItem>> = plannerDao.getAllMessage()

    suspend fun addNewPlan(item: PlannerItem): Long {
        return plannerDao.addNewPlan(item)
    }

    suspend fun updateDone(itemId: Int, done: Boolean) {
        plannerDao.updateDone(planId = itemId, done = done)
    }

    suspend fun updateShowSono(itemId: Int, showSono: Boolean) {
        plannerDao.updateShowSono(planId = itemId, showSono = showSono)
    }

    suspend fun updateShowBuyPack(itemId: Int, showBuyPack: Boolean) {
        plannerDao.updateShowBuyPack(planId = itemId, showBuyPack = showBuyPack)
    }

    suspend fun updateShowVideoLink(itemId: Int, showVideoLink: Boolean) {
        plannerDao.updateShowVideoLink(planId = itemId, showVideoLink = showVideoLink)
    }

    suspend fun deleteGroup(item: PlannerItem) {
        plannerDao.deletePlan(item)
    }
}