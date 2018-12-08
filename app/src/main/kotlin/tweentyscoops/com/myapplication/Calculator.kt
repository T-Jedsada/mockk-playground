package tweentyscoops.com.myapplication

import android.content.Context

class Calculator(
    private val mockObj: Animal,
    private val context: Context,
    private val view: ViewImpl
) {

    fun plus(x: Int, y: Int): Pair<String, Int> = (mockObj.tesFunc()) to (x + y)

    fun minus(x: Int, y: Int) {
        view.showResult("result" to (x - y))
    }
}