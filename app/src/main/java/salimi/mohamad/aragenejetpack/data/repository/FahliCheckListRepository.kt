package salimi.mohamad.aragenejetpack.data.repository

import kotlinx.coroutines.flow.Flow
import salimi.mohamad.aragenejetpack.data.db.CheckListDao
import salimi.mohamad.aragenejetpack.data.model.FahliCheckList
import javax.inject.Inject

class FahliCheckListRepository @Inject constructor(private val checkListDao: CheckListDao) {

    val allCheckList:Flow<List<FahliCheckList>> = checkListDao.getAllItems()

    suspend fun addNewGroup(item: FahliCheckList):Long{
        return checkListDao.addNewItem(item)
    }

    suspend fun updateGroup(item:FahliCheckList){
        checkListDao.updateItem(item)
    }

    suspend fun deleteGroup(item: FahliCheckList){

        checkListDao.deleteItem(item)
    }

    suspend fun deleteAll(){
        checkListDao.deleteAllItem()
    }
}