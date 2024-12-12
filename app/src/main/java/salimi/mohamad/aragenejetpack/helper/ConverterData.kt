package salimi.mohamad.aragenejetpack.helper

import android.annotation.SuppressLint
import ir.huri.jcal.JalaliCalendar

@SuppressLint("DefaultLocale")
fun convertPersianToGregorian(persianDate: String): String {
    // تاریخ ورودی به صورت yyyy-mm-dd
    val parts = persianDate.split("-")
    val year = parts[0].toInt()
    val month = parts[1].toInt()
    val day = parts[2].toInt()

    // تبدیل تاریخ شمسی به میلادی
    val jalaliCalendar = JalaliCalendar(year, month, day)
    val gregorianCalendar = jalaliCalendar.toGregorian()

    // دسترسی به سال، ماه و روز میلادی
    val gregorianYear = gregorianCalendar.get(java.util.Calendar.YEAR)
    val gregorianMonth = gregorianCalendar.get(java.util.Calendar.MONTH) + 1 // ماه‌ها از 0 شروع می‌شوند
    val gregorianDay = gregorianCalendar.get(java.util.Calendar.DAY_OF_MONTH)

    // فرمت خروجی میلادی به صورت yyyy-mm-dd
    return "${gregorianYear}-${String.format("%02d", gregorianMonth)}-${String.format("%02d", gregorianDay)}"
}