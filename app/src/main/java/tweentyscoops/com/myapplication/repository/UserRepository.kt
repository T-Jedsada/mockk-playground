package tweentyscoops.com.myapplication.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Deferred
import retrofit2.Response
import tweentyscoops.com.myapplication.dao.UserDao
import tweentyscoops.com.myapplication.http.UserApi

class UserRepository(private val userApi: UserApi) {

    fun getUserInformationSingle(
        userId: String
    ): Single<Response<UserDao>> = userApi.getUserInformation(userId)

    fun getListUserSingle(userId: String): Deferred<Response<UserDao>> = userApi.getListUser(userId)

    fun getUserInformation(
        userId: String,
        onSuccess: (data: UserDao?) -> Unit,
        onError: (error: String?) -> Unit
    ) {
        getUserInformationSingle(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { onSuccess.invoke(it.body()) },
                onError = { onError.invoke(it.message) }
            )
    }

    suspend fun getListUser(
        userId: String,
        onSuccess: (data: UserDao?) -> Unit,
        onError: (error: String?) -> Unit
    ) {
        val response = getListUserSingle(userId).await()
        when (response.isSuccessful) {
            true -> onSuccess.invoke(response.body())
            else -> onError.invoke("error")
        }
    }
}