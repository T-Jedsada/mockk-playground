package tweentyscoops.com.myapplication.ui.main

import android.content.Context
import tweentyscoops.com.myapplication.repository.UserRepository

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

    suspend fun getListUser(userId: String) {
        view.showDialogProgressBar()
        userRepository.getListUser(userId, {
            view.onGetUserInformationSuccess(it)
        }, {
            view.onGetUserInformationError(it)
        })
        view.hideDialogProgressBar()
    }
}