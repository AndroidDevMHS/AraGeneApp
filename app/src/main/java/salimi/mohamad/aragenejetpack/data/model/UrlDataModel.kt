package salimi.mohamad.aragenejetpack.data.model

import com.google.gson.annotations.SerializedName

data class UrlDataModel(
    @SerializedName("Sheet1") val sheet1: List<Sheet1>,
    @SerializedName("Sheet2") val sheet2: List<Sheet2>
)

data class Sheet1(
    @SerializedName("link") val link: String,
    @SerializedName("title") val title: String
)


data class Sheet2(
    @SerializedName("text") val text: String,
    @SerializedName("title") val title: String
)