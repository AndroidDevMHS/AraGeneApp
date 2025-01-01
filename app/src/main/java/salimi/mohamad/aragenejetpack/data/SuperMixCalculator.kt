package salimi.mohamad.aragenejetpack.data


fun superMixCalculator(count: Int, day: Int, weight: Int): List<Any> {

    fun dailyUsage(weight: Int) = (weight * 0.04)
    val fullDailyI5 = count * dailyUsage(weight) /// تا روز 8ام
    val weight2L12 = (((fullDailyI5 / count) / 8) * 7) + weight// وزن 2ام
    val fullDailyI12 = count * dailyUsage(weight2L12.toInt())
    val weight3L19 = (((fullDailyI12 / count) / 7) * 7) + weight2L12// تا روز19 ام
    val fullDailyI19 = count * dailyUsage(weight2L12.toInt())
    val weight3L25 = (((fullDailyI19 / count) / 5.5) * 7) + weight3L19// تا روز19 ام
    val fullDailyI25 = count * dailyUsage(weight3L19.toInt())
    val weight3L29 = (((fullDailyI25 / count) / 5.5) * 7) + weight3L25// تا روز19 ام
    val fullDailyI29 = count * dailyUsage(weight3L25.toInt())
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


    val (fullDaily, pair) = when (day) {
        in 1..7 ->
            Pair(
                fullDailyI5,
                Pair(
                    fullDailyI5 * consanterePer[day - 1],
                    fullDailyI5 * younjePer[day - 1]
                )
            )

        in 8..14 ->
            Pair(
                fullDailyI12,
                Pair(
                    fullDailyI12 * consanterePer[day - 1],
                    fullDailyI12 * younjePer[day - 1]
                )
            )

        in 15..20 ->
            Pair(
                fullDailyI19,
                Pair(
                    fullDailyI19 * consanterePer[day - 1],
                    fullDailyI19 * younjePer[day - 1]
                )
            )

        in 21..24 -> Pair(
            fullDailyI25,
            Pair(
                fullDailyI25 * consanterePer[day - 1],
                fullDailyI25 * younjePer[day - 1]
            )
        )

        else -> Pair(
            fullDailyI29,
            Pair(
                fullDailyI29 + consanterePer[day - 1],
                fullDailyI29 + younjePer[day - 1]
            )
        )
    }
    return listOf(fullDaily, pair)
}