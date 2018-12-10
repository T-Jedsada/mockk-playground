package tweentyscoops.com.myapplication

import android.content.Context
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.Response
import tweentyscoops.com.myapplication.dao.UserDao
import tweentyscoops.com.myapplication.http.UserApi
import tweentyscoops.com.myapplication.repository.UserRepository
import tweentyscoops.com.myapplication.ui.main.MainPresenter
import tweentyscoops.com.myapplication.ui.main.MainView

@RunWith(PowerMockRunner::class)
class MainPresenterTest {

    @Rule
    @JvmField
    var rxSchedulerRule = RxSchedulersOverrideRule()

    @Mock
    lateinit var mockedUserApi: UserApi
    @Mock
    lateinit var mockedView: MainView
    @Mock
    lateinit var mockedContext: Context

    private lateinit var userRepository: UserRepository
    private lateinit var mainPresenter: MainPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository(mockedUserApi)
        mainPresenter = MainPresenter(
            userRepository,
            mockedContext,
            mockedView
        )
    }

    @Test
    fun `get user information should be success`() {
        val mockedResult = UserDao("12345678", "20scoops")
        val mockResponse = Response.success(mockedResult)
        val mockObservable = Single.just(mockResponse)
        whenever(userRepository.getUserInformationSingle(Mockito.anyString())).thenReturn(
            mockObservable
        )
        mainPresenter.getUserInformation("12345678")
        verify(mockedView, times(1)).onGetUserInformationSuccess(mockedResult)
    }

    @Test
    fun `get user information should be error`() {
        val throwable = Throwable("error")
        val mockObservable = Single.error<Response<UserDao>>(throwable)
        whenever(userRepository.getUserInformationSingle(Mockito.anyString())).thenReturn(
            mockObservable
        )
        mainPresenter.getUserInformation("12345678")
        verify(mockedView, times(1)).onGetUserInformationError(throwable.message)
    }

    @Test
    fun `get list user should be success`() {
        val mockedResult = UserDao("12345678", "20scoops")
        val mockResponse = Response.success(mockedResult)
        val deferred = CompletableDeferred(mockResponse)
        whenever(userRepository.getListUserSingle("1234456")).thenReturn(deferred)
        runBlocking { mainPresenter.getListUser("1234456") }
        verify(mockedView, times(1)).onGetUserInformationSuccess(mockedResult)
    }

    @Test
    fun `get list user should be error`() {
        val errorMessage = "error"
        val body = ResponseBody.create(MediaType.parse("text/plain"), errorMessage)
        val mockResponse = Response.error<UserDao>(500, body)
        val deferred = CompletableDeferred(mockResponse)
        whenever(userRepository.getListUserSingle("123456")).thenReturn(deferred)
        runBlocking { mainPresenter.getListUser("123456") }
        verify(mockedView, times(1)).showDialogProgressBar()
        verify(mockedView, times(1)).onGetUserInformationError(errorMessage)
        verify(mockedView, times(1)).hideDialogProgressBar()
    }
}