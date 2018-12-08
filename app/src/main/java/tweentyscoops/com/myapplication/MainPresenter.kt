package tweentyscoops.com.myapplication

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tweentyscoops.com.myapplication.repository.UserRepository
import tweentyscoops.com.myapplication.view.MainView

class MainPresenter(
    private val userRepository: UserRepository,
    private val context: Context,
    private val view: MainView
) {

    fun getUserInformation(userId: String) {
        userRepository.getUserInformation(userId, {
            view.onGetUserInformationSuccess(it)
        }, {
            view.onGetUserInformationError(it)
        })
    }

    fun getListUser(userId: String) {
        GlobalScope.launch {
            userRepository.getListUser(userId, {
                view.onGetUserInformationSuccess(it)
            }, {
                view.onGetUserInformationError(it)
            })
        }
    }
}