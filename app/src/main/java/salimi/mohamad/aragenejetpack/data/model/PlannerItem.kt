package salimi.mohamad.aragenejetpack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import salimi.mohamad.aragenejetpack.utils.Constants.PLANNER_TABLE

@Entity(tableName = PLANNER_TABLE)
data class PlannerItem(
    @PrimaryKey(autoGenerate = true) val planId:Int=0,
    val day:Int,
    val message:String,
    val time: String,
    val done:Boolean=false,
    val showSono:Boolean=false,
    val showBuyPack:Boolean=false,
    val showVideoLink:Boolean=false
)
