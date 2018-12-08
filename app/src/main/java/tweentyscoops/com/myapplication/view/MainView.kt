package tweentyscoops.com.myapplication.view

import tweentyscoops.com.myapplication.dao.UserDao

interface MainView {
    fun onGetUserInformationSuccess(data: UserDao?)
    fun onGetUserInformationError(errorMessage: String?)
    fun showView()
}