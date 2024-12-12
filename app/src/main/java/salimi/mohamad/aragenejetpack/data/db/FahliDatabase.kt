 package salimi.mohamad.aragenejetpack.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import salimi.mohamad.aragenejetpack.data.model.FahliCheckList
import salimi.mohamad.aragenejetpack.data.model.PlannerItem

 @Database(entities = [FahliCheckList::class, PlannerItem::class], version = 1, exportSchema = false)
 abstract class FahliDatabase:RoomDatabase(){

     abstract fun checkListDao(): CheckListDao

     abstract fun plannerDao():PlannerDao
 }