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
    fun saveFirstLogin(key:String,showWelcome: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.putBoolean(key, showWelcome)

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

    fun getUserWelcome(key:String): Boolean =
        runBlocking { repository.getBoolean(key) ?: true }

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