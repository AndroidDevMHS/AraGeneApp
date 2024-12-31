package salimi.mohamad.aragenejetpack.data.model

data class VideoUrlResponse(
    val link: String,
    val title: String,
    val id: Int
)

data class VideoUrlResponseWrapper(
    val sheet1: List<VideoUrlResponse>
)