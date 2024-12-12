package salimi.mohamad.aragenejetpack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import salimi.mohamad.aragenejetpack.utils.Constants.CHECKLIST_TABLE

@Entity(tableName = CHECKLIST_TABLE )
data class FahliCheckList(
    @PrimaryKey(autoGenerate = true)   val id:Int=0,
    val groupName:String,
    val countOfGroup:Int,
    val dateOfStartShamsi:String,
    val dateOfStartMiladi:String
)
