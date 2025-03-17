import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Header

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val token: String)
data class TimetableResponse(val timetable: List<String>)

interface ApiService {
    @POST("api/token/")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("api/timetable/")
    fun getTimetable(@Header("Authorization") token: String): Call<TimetableResponse>
}
