package salimi.mohamad.aragenejetpack.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class ArticleRes(
    val sheet2: List<Sheet2>
)

@Parcelize
data class Sheet2(
    val id: Int,
    val text: String,
    val title: String
):Parcelable