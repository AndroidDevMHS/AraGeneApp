package salimi.mohamad.aragenejetpack.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import salimi.mohamad.aragenejetpack.data.model.Sheet1
import salimi.mohamad.aragenejetpack.data.model.Sheet2
import salimi.mohamad.aragenejetpack.data.repository.UrlApiRepository
import javax.inject.Inject

sealed class UrlState {
    data object Loading : UrlState()
    data class Success(val videos: List<Sheet1>) : UrlState()
    data class Error(val message: String) : UrlState()
}

sealed class ArticleState {
    data object Loading : ArticleState()
    data class Success(val articles: List<Sheet2>) : ArticleState()
    data class Error(val message: String) : ArticleState()
}

@HiltViewModel
class VideoUrlViewModel @Inject constructor(
     private val repositoryApi: UrlApiRepository
) : ViewModel() {

    // Video state
    private val _videoState = MutableStateFlow<UrlState>(UrlState.Loading)
    val videoState: StateFlow<UrlState> get() = _videoState

    // Article state
    private val _articleState = MutableStateFlow<ArticleState>(ArticleState.Loading)
    val articleState: StateFlow<ArticleState> get() = _articleState

    private val _selectedArticle = MutableStateFlow<Sheet2?>(null)
    val selectedArticle: StateFlow<Sheet2?> = _selectedArticle

    fun selectArticle(article: Sheet2) {
        _selectedArticle.value = article
    }

    fun sendRequest() {
        viewModelScope.launch(Dispatchers.IO) {
            _videoState.value = UrlState.Loading
            _articleState.value = ArticleState.Loading
            try {
                val response = repositoryApi.urlApi()
                if (response.isSuccessful) {
                    val videos = response.body()?.sheet1?: emptyList()
                    val article=response.body()?.sheet2?: emptyList()
                    Log.e("3030", "Body: ${response.body()}")
                    _videoState.value = UrlState.Success(videos)
                    _articleState.value=ArticleState.Success(article)
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "خطای نامشخص"
                    _videoState.value = UrlState.Error("خطا در دریافت داده: $errorMessage")
                }
            } catch (e: Exception) {
                _videoState.value = UrlState.Error("خطا: ${e.message}")
            }
        }
    }
}