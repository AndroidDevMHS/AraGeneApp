package salimi.mohamad.aragenejetpack.data.remote

import retrofit2.Response
import retrofit2.http.GET
import salimi.mohamad.aragenejetpack.data.model.UrlDataModel

interface ApiInterfaceUrl {
    @GET("excel/api.php")
    suspend fun urlApi(): Response<UrlDataModel>
}