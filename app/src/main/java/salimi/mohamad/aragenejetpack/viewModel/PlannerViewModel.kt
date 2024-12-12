package salimi.mohamad.aragenejetpack.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import salimi.mohamad.aragenejetpack.data.model.PlannerItem
import salimi.mohamad.aragenejetpack.data.repository.PlannerRepository
import javax.inject.Inject


@HiltViewModel
class PlannerViewModel @Inject constructor(
    private val repository: PlannerRepository,
) : ViewModel() {

    val allMessage: Flow<List<PlannerItem>> = repository.allMessage

    fun addNewGroup(item: PlannerItem, onResult: (Long) -> Unit) {
        viewModelScope.launch {
            val groupId = repository.addNewPlan(item)
            onResult(groupId)
        }
    }

    fun updateDone(itemId: Int, done: Boolean) {
        viewModelScope.launch {
            repository.updateDone(itemId = itemId, done = done)
        }
    }

    fun updateShowSono(itemId: Int, showSono: Boolean) {
        viewModelScope.launch {
            repository.updateShowSono(itemId = itemId, showSono = showSono)
        }
    }

    fun updateShowBuyPack(itemId: Int, showBuyPack: Boolean) {
        viewModelScope.launch {
            repository.updateShowBuyPack(itemId = itemId, showBuyPack = showBuyPack)
        }
    }

    fun updateShowVideoLink(itemId: Int, showVideoLink: Boolean) {
        viewModelScope.launch {
            repository.updateShowVideoLink(itemId = itemId, showVideoLink = showVideoLink)
        }
    }


    fun deleteGroup(item: PlannerItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteGroup(item)
        }
    }
}
