package salimi.mohamad.aragenejetpack.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import salimi.mohamad.aragenejetpack.data.model.SmsRequest
import salimi.mohamad.aragenejetpack.data.remote.ApiInterface
import javax.inject.Inject

@HiltViewModel
class SmsViewModel @Inject constructor(

    private val apiInterface: ApiInterface,

) : ViewModel() {

    fun sendSms(sms: SmsRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiInterface.sendSms(
                sms.userName,
                sms.password,
                sms.fromNumber,
                sms.toNumbers,
                sms.messageContent
            )
            response.enqueue(object :Callback<Int>{
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    Log.e("3636",response.body().toString())
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("3636",t.toString())
                }

            })
            Log.e("3636", response.toString())

        }
    }
}