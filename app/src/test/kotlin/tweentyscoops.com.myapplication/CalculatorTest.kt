package tweentyscoops.com.myapplication

import android.content.Context
import io.mockk.*
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

@RunWith(JUnitPlatform::class)
class CalculatorTest : Spek({

    var mockedAnimal: Animal = mockk()
    val mockedContext: Context = mockk()

    beforeEachTest {
        mockedAnimal = mockk()
    }

    describe("A Calculator") {
        val mockedView: ViewImpl = mockk()
        val calculator by memoized { Calculator(mockedAnimal, mockedContext, mockedView) }

        it("1+1 should be 2") {
            every { mockedAnimal.tesFunc() } returns "20scoops"
            val values = calculator.plus(1, 1)
            print("first value: ${values.first}")
            assertEquals(2, values.second)
            verify(exactly = 1) { calculator.plus(1, 1) }
        }

        it("should be call callback") {
            every { mockedView.showResult(any()) } just Runs
            calculator.minus(5, 5)
            verify(exactly = 1) { mockedView.showResult("result" to 0) }
        }
    }
})