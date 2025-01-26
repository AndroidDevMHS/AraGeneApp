package salimi.mohamad.aragenejetpack.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import salimi.mohamad.aragenejetpack.data.repository.DataStoreRepository
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    companion object {
        const val USER_LOGIN_STATE_KEY = "UserLoginKey"
        const val USER_PHONE_KEY = "UserPhoneKey"
        const val FIRST_LOGIN_KEY="FirstLogin"
        const val SELECTED_PACK_KEY="SelectedPackKey"

    }
    init {
        viewModelScope.launch {
            repository.getString(USER_PHONE_KEY)?.let {
                userPhoneNumber.emit(it)
            }
        }
    }
    fun saveLoginState(isLoginIn: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.putBoolean(USER_LOGIN_STATE_KEY, isLoginIn)

        }
    }
    fun saveFirstLogin(showWelcome: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.putBoolean(FIRST_LOGIN_KEY, showWelcome)

        }
    }

    fun savePhoneNumber(phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.putString(USER_PHONE_KEY, phone)
            userPhoneNumber.emit(phone)
        }
    }

    fun getUserLoginState(): Boolean =
        runBlocking { repository.getBoolean(USER_LOGIN_STATE_KEY) ?: false }

    fun getUserWelcome(): Boolean =
        runBlocking { repository.getBoolean(FIRST_LOGIN_KEY) ?: true }

    val userPhoneNumber = MutableStateFlow("")
    fun getUserPhone() {
        viewModelScope.launch {
            repository.getString(USER_PHONE_KEY)?.let {
                userPhoneNumber.emit(it)
            }
        }
    }

    fun clearData() {
        viewModelScope.launch {
            repository.deleteAllData()
        }
    }
}