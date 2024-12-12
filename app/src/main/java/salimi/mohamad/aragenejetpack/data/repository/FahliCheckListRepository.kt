package salimi.mohamad.aragenejetpack.data.repository

import kotlinx.coroutines.flow.Flow
import salimi.mohamad.aragenejetpack.data.db.CheckListDao
import salimi.mohamad.aragenejetpack.data.model.FahliCheckList
import javax.inject.Inject

class FahliCheckListRepository @Inject constructor(private val studentDao: CheckListDao) {

    val allCheckList:Flow<List<FahliCheckList>> = studentDao.getAllItems()

    suspend fun addNewGroup(item: FahliCheckList){
        studentDao.addNewStudent(item)
    }

    suspend fun updateGroup(item:FahliCheckList){
        studentDao.updateStudent(item)
    }

    suspend fun deleteGroup(item: FahliCheckList){

        studentDao.deleteStudent(item)
    }

    suspend fun deleteAll(){
        studentDao.deleteAllStudents()
    }
}