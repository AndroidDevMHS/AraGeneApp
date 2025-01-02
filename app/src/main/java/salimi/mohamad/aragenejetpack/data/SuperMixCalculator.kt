package salimi.mohamad.aragenejetpack.data

import android.util.Log
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.ceil
import kotlin.math.round


fun superMixCalculator(count: Int, day: Int, weight: Int): List<Float> {

    fun dailyUsage(weight: Int) = (weight * 0.04)
    val fullDailyI5 = count * dailyUsage(weight).toFloat() /// تا روز 8ام
    val weight2L12 = (((fullDailyI5 / count) / 8) * 7) + weight// وزن 2ام
    val fullDailyI12 = count * dailyUsage(weight2L12.toInt()).toFloat()
    val weight3L19 = ceil((((fullDailyI12 / count) / 7) * 7) + weight2L12)// تا روز19 ام
    val fullDailyI19 = count * dailyUsage(weight3L19.toInt()).toFloat()
    val weight3L25 = ceil((((fullDailyI19 / count) / 5.5) * 7) + weight3L19)// تا روز19 ام
    val fullDailyI25 = count * dailyUsage(weight3L25.toInt()).toFloat()
    val weight3L29 = ceil((((fullDailyI25 / count) / 5.5) * 7) + weight3L25)// تا روز19 ام
    val fullDailyI29 = count * dailyUsage(weight3L29.toInt())

    val consanterePer = listOf(
        0.07,
        0.07,
        0.14,
        0.14,
        0.21,
        0.21,
        0.29,
        0.28,
        0.34,
        0.34,
        0.41,
        0.41,
        0.48,
        0.48,
        0.57,
        0.57,
        0.67,
        0.67,
        0.77,
        0.77,
        0.81,
        0.81,
        0.91,
        0.91,
        0.95,
        0.95
    )

    val younjePer = listOf(
        0.93,
        0.93,
        0.86,
        0.86,
        0.79,
        0.79,
        0.71,
        0.72,
        0.66,
        0.66,
        0.59,
        0.59,
        0.52,
        0.52,
        0.43,
        0.43,
        0.33,
        0.33,
        0.23,
        0.23,
        0.19,
        0.19,
        0.09,
        0.09,
        0.05,
        0.05
    )

    val (fullDaily: Float, cons: Float, younj: Float) = when (day) {
        in 1..7 -> Triple(
            fullDailyI5.toFloat(),
            (fullDailyI5 * consanterePer[day - 1]).toFloat(),
            (fullDailyI5 * younjePer[day - 1]).toFloat()
        )

        in 8..14 -> Triple(
            fullDailyI12.toFloat(),
            (fullDailyI12 * consanterePer[day - 1]).toFloat(),
            (fullDailyI12 * younjePer[day - 1]).toFloat()
        )

        in 15..20 -> Triple(
            fullDailyI19.toFloat(),
            (fullDailyI19 * consanterePer[day - 1]).toFloat(),
            (fullDailyI19 * younjePer[day - 1]).toFloat()
        )

       /* in 21..24 -> Triple(
            fullDailyI25.toFloat(),
            (fullDailyI25 * consanterePer[day - 1]).toFloat(),
            (fullDailyI25 * younjePer[day - 1]).toFloat()
        )*/

        else -> Triple(
            fullDailyI29.toFloat(),
            (fullDailyI29 * consanterePer[day - 1]).toFloat(),
            (fullDailyI29 * younjePer[day - 1]).toFloat()
        )
    }
    Log.e("3030", "fullDaily $fullDaily cons $cons yonj $younj w1 $weight2L12 w2 $weight3L19 w3 $weight3L25 w4 $weight3L29")
// بازگشت مقادیر به‌صورت لیست
    return listOf(roundTwo( fullDaily), roundTwo(cons), roundTwo(younj))
}

fun roundOne(number: Float): Float {
    val number1 = BigDecimal("$number")
    val roundedNumber = number1.setScale(1, RoundingMode.HALF_UP)
    return roundedNumber.toFloat()
}
fun roundTwo(number: Float): Float {
    val number1 = BigDecimal("$number")
    val roundedNumber = number1.setScale(2, RoundingMode.HALF_UP)
    return roundedNumber.toFloat()
}