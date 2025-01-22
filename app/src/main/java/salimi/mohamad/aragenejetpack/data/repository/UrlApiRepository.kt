package salimi.mohamad.aragenejetpack.data.repository

import salimi.mohamad.aragenejetpack.data.remote.ApiInterface
import salimi.mohamad.aragenejetpack.data.remote.ApiInterfaceUrl
import javax.inject.Inject
import javax.inject.Named

class UrlApiRepository @Inject constructor(
    @Named("UrlApiInter") private val urlApi: ApiInterfaceUrl
) {
    suspend fun urlApi()=urlApi.urlApi()
}