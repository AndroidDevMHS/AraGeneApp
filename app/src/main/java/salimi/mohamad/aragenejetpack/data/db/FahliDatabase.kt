 package salimi.mohamad.aragenejetpack.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import salimi.mohamad.aragenejetpack.data.model.FahliCheckList

 @Database(entities = [FahliCheckList::class], version = 1, exportSchema = false)
abstract class FahliDatabase:RoomDatabase(){

    abstract fun checkListDao(): CheckListDao
}