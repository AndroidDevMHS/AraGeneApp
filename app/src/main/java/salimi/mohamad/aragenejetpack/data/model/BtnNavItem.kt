package salimi.mohamad.aragenejetpack.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BtnNavItem(
    val title:String,
    val route:String,
    val selectedIcon:ImageVector,
    val unSelectedIcon:ImageVector,
    val hasNews:Boolean,
    val badgeCount:Int?=null
)
