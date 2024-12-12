package salimi.mohamad.aragenejetpack.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import salimi.mohamad.aragenejetpack.data.model.FahliCheckList
import salimi.mohamad.aragenejetpack.data.repository.FahliCheckListRepository
import salimi.mohamad.aragenejetpack.helper.cancelNotificationsForGroup
import javax.inject.Inject

@HiltViewModel
class FahliCheckDbViewModel @Inject constructor(
    private val repository: FahliCheckListRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val allCheckList: Flow<List<FahliCheckList>> = repository.allCheckList

    fun addNewItem(item: FahliCheckList, callback: (Long) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val itemId = repository.addNewGroup(item)
            callback(itemId)
        }
    }

    fun deleteGroup(item: FahliCheckList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteGroup(item)
            cancelNotificationsForGroup(context, item.id)
        }
    }
}