package tweentyscoops.com.myapplication.ui.main

import tweentyscoops.com.myapplication.dao.UserDao

interface MainView {
    fun showDialogProgressBar()
    fun hideDialogProgressBar()
    fun onGetUserInformationSuccess(data: UserDao?)
    fun onGetUserInformationError(errorMessage: String?)
}