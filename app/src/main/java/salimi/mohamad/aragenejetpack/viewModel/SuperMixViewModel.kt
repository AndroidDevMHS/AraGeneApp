package salimi.mohamad.aragenejetpack.viewModel

import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SuperMixViewModel: ViewModel() {

    // ذخیره موقعیت اسکرول
    private val _scrollPosition = MutableStateFlow(0)
    val scrollPosition: StateFlow<Int> = _scrollPosition

    // ذخیره موقعیت اسکرول
    fun saveScrollPosition(position: Int) {
        _scrollPosition.value = position
    }
}