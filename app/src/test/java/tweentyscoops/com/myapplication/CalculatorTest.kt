//package tweentyscoops.com.myapplication
//
//import android.content.Context
//import io.mockk.*
//import junit.framework.Assert.assertEquals
//import org.junit.Test
//import tweentyscoops.com.myapplication.view.ViewImpl
//
//class CalculatorTest {
//
//    private val mockedAnimal: Animal = mockk()
//    private val mockedContext: Context = mockk()
//    private val mockedView: ViewImpl = mockk()
//    private val calculator = Calculator(mockedAnimal, mockedContext, mockedView)
//
//    init {
//        MockKAnnotations.init(this)
//    }
//
//    @Test
//    fun `1+1 should be 2`() {
//        every { mockedAnimal.tesFunc() } returns "20scoops"
//        val values = calculator.plus(1, 1)
//        assertEquals(2, values.second)
//        verify(exactly = 1) { calculator.plus(1, 1) }
//    }
//
//    @Test
//    fun `should be call callback`() {
//        every { mockedView.showResult(any()) } just Runs
//        calculator.minus(5, 5)
//        verify(exactly = 1) { mockedView.showResult("result" to 0) }
//    }
//
//    @Test
//    fun `HOF should be call`() {
//        val mockedFunc: () -> Unit = mockk()
//        every { mockedFunc.invoke() } just Runs
//        calculator.hof(mockedFunc)
//        verify(exactly = 1) { mockedFunc.invoke() }
//    }
//}