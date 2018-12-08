package tweentyscoops.com.myapplication.http

import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tweentyscoops.com.myapplication.dao.UserDao

interface UserApi {

    @GET("information")
    fun getUserInformation(@Query("user_id") userId: String): Single<Response<UserDao>>

    @GET("list/{userId}")
    fun getListUser(@Path("userId") userId: String): Deferred<Response<UserDao>>
}