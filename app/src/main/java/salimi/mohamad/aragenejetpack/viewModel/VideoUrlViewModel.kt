package salimi.mohamad.aragenejetpack.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import salimi.mohamad.aragenejetpack.data.model.Sheet2
import salimi.mohamad.aragenejetpack.data.model.VideoUrlResponse
import salimi.mohamad.aragenejetpack.data.remote.ApiInterface
import salimi.mohamad.aragenejetpack.data.repository.SmsRepository
import javax.inject.Inject
import javax.inject.Named

sealed class VideoUrlState {
    data object Loading : VideoUrlState()
    data class Success(val videos: List<VideoUrlResponse>) : VideoUrlState()
    data class Error(val message: String) : VideoUrlState()
}

sealed class ArticleState {
    data object Loading : ArticleState()
    data class Success(val articles: List<Sheet2>) : ArticleState()
    data class Error(val message: String) : ArticleState()
}

@HiltViewModel
class VideoUrlViewModel @Inject constructor(
     private val repositoryApi: SmsRepository
) : ViewModel() {

    // Video state
    private val _videoState = MutableStateFlow<VideoUrlState>(VideoUrlState.Loading)
    val videoState: StateFlow<VideoUrlState> get() = _videoState

    // Article state
    private val _articleState = MutableStateFlow<ArticleState>(ArticleState.Loading)
    val articleState: StateFlow<ArticleState> get() = _articleState

    fun sendRequest() {
        viewModelScope.launch(Dispatchers.IO) {
            _videoState.value = VideoUrlState.Loading
            try {
                val response = repositoryApi.videoUrl()
                if (response.isSuccessful) {
                    val videos = response.body()?.sheet1 ?: emptyList()
                    _videoState.value = VideoUrlState.Success(videos)
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "خطای نامشخص"
                    _videoState.value = VideoUrlState.Error("خطا در دریافت داده: $errorMessage")
                }
            } catch (e: Exception) {
                _videoState.value = VideoUrlState.Error("خطا: ${e.message}")
            }
        }
    }

    // Fetch articles
    fun fetchArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            _articleState.value = ArticleState.Loading
            try {
                val response = repositoryApi.articleTxt()
                _articleState.value = ArticleState.Success(response.sheet2)
            } catch (e: Exception) {
                _articleState.value = ArticleState.Error("خطا: ${e.message}")
            }
        }
    }
}