package salimi.mohamad.aragenejetpack.data.model

data class ArticleRes(
    val sheet2: List<Sheet2>
)

data class Sheet2(
    val id: Int,
    val text: String,
    val title: String
)