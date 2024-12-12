package salimi.mohamad.aragenejetpack.helper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.data.model.FahliCheckList
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun getNotificationDates(startDate: String): List<Date> {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val startDateParsed = sdf.parse(startDate)
    val notificationDates = mutableListOf<Date>()

    if (startDateParsed != null) {
        val cal = Calendar.getInstance()
        cal.time = startDateParsed  // تاریخ شروع را تنظیم می‌کنیم

        val days = listOf(
            0,
            5,
            6,
            10,
            40,
            50,
            100,
            111,
            140,
            160,
            210,
            220,
            227
        )  // اینجا شما می‌توانید روزهای مورد نظر خود را تنظیم کنید
        days.forEach { value ->
            val notificationDate =
                cal.clone() as Calendar  // برای هر روز جدید یک کپی از تاریخ می‌گیریم
            notificationDate.add(Calendar.DAY_OF_YEAR, value)  // اضافه کردن روز به تاریخ شروع
            notificationDates.add(notificationDate.time)  // افزودن تاریخ نوتیفیکیشن به لیست
        }
    }
    return notificationDates
}

class NotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val notificationId = inputData.getInt("notificationId", 0)
        val title = inputData.getString("title") ?: "Default Title"
        val message = inputData.getString("message") ?: "Default Message"
        createNotificationChannel()

        // ارسال نوتیفیکیشن با BigTextStyle برای نمایش چند خطی
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val bigTextStyle = NotificationCompat.BigTextStyle()
            .bigText(message)  // متنی که می‌خواهید به صورت چند خطی نمایش داده شود

        val notification = NotificationCompat.Builder(applicationContext, "channel_id")
            .setContentTitle(title)
            .setContentText(message)  // متن پیش‌فرض نوتیفیکیشن برای نوتیفیکیشن باریک
            .setStyle(bigTextStyle)  // اعمال BigTextStyle
            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationCompat.PRIORITY_HIGH)  // تنظیم اولویت نوتیفیکیشن
            .build()

        notificationManager.notify(notificationId, notification)

        return Result.success()
    }

    // ایجاد کانال نوتیفیکیشن (برای Android 8.0 به بالا)
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel_id",
                "Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is the default notification channel"
            }
            val notificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

// ارسال نوتیفیکیشن با شناسه یکتا برای هر روز
fun scheduleNotification(
    context: Context,
    groupId: Int,
    day: Int,
    time: Long,
    title: String,
    message: String
) {
    // ایجاد شناسه یکتا با ترکیب شناسه گروه و روز
    val notificationId = "$groupId-$day".hashCode() // شناسه یکتا بر اساس گروه و روز

    val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
        .setInitialDelay(time, TimeUnit.MILLISECONDS)
        .setInputData(
            workDataOf(
                "notificationId" to notificationId.toString(),
                "title" to title,
                "message" to message
            )
        )
        .build()

    WorkManager.getInstance(context).enqueue(workRequest)
}

fun scheduleNotificationsForGroup(context: Context, group: FahliCheckList) {
    val notificationDates = getNotificationDates(group.dateOfStartMiladi)

    notificationDates.forEachIndexed { index, date ->
        Log.e("1212", date.toString())
        val notificationTime = date.time - System.currentTimeMillis()

        // تنظیم متن نوتیفیکیشن با استفاده از تابع getNotificationMessage
        val message = getNotificationMessage(index, group)
        Log.e("1212", notificationTime.toString())
        // برای هر روز شناسه یکتا را ایجاد می‌کنیم
        scheduleNotification(
            context,
            group.id,
            index,
            notificationTime,
            "گروه ${group.groupName}",
            message
        )
    }
}

// لغو نوتیفیکیشن‌ها برای گروه
fun cancelGroupNotifications(context: Context, group: FahliCheckList) {
    // لغو نوتیفیکیشن‌های مرتبط با هر روز
    val notificationDates = getNotificationDates(group.dateOfStartMiladi)
    notificationDates.forEachIndexed { index, _ ->
        val notificationId = "$group.id-$index".hashCode() // شناسه یکتا برای هر روز
        cancelNotification(context, notificationId)
    }
}

// لغو نوتیفیکیشن
fun cancelNotification(context: Context, notificationId: Int) {
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.cancel(notificationId)
}

fun getNotificationMessage(day: Int, group: FahliCheckList): String {
    return when (day) {
        0 -> "سیدر گذاری و تزریق هورمون روز ۱, دو سی سی برای هر دام"
        1 -> "تزریق هورمون روز ۵, ۱.۲ سی سی و هورمون پودری ۲ سی سی و سیدر برداری"
        2 -> "شروع قوچ اندازی"
        3 -> "لطفاً نتیجه قوچ اندازی گله را در فرم ایجاد شده در اپلیکیشن ثبت کنید"
        4 -> "بسیار مهم، هفته آینده زمان مناسب برای سونوگرافی گروه ${group.groupName} است, برای آن حتماً اقدام فرمایید، در صورتی که به متخصص سونوگراف دسترسی ندارید تیک دسترسی ندارم را فعال کنید"
        5 -> "لطفاً تاریخ سونوگرافی را در اپلیکیشن وارد نمایید، و چنانچه به دکتر سونوگراف دسترسی ندارید در اپلیکیشن تیک دسترسی ندارم را فعال کنید"
        6 -> "ااقدام بسیار مهم برای گروه ${group.groupName}؛ رشد جفت در این بازه زمانی اتفاق می افتد و نسبت به آبستن سبک تغذیه باید تغییر کند، برای این منظور ویدیوی مربوط به این موضوع در اپلیکیشن اضافه شده است، رمز دسترسی به این ویدیو 1234 است."
        7 -> "1234 است. طی یک هفته تا ۱۰ روز آینده باید واکسن آنتروتاکسمی به دامهایتان تزریق شود، تغذیه مناسب آبستن سنگین و مواد معدنی باید انجام گردد، برای این موضوع یک ویدیو در اپلیکیشن آپلود شده آن را نگاه کنید و رمز آن"
        8 -> " برای زایمان میشهایتان آماده باشید نکات طلایی در ویدیویی که به آن دسترسی پیدا کرده اید توضیح داده شده است"
        9 -> "بابت زایمانهای گله تبریک ما را پذیرا باشید، لطفاً فرم مربوط به زایمانها را پر کنید."
        10 -> "از شیرگیری برههای حاصل از گروه ${group.groupName} را از25 روز دیگر یعنی دو و نیم ماه بعد از به دنیا آمدن برهها مطابق دستورالعمل از شیرگیری انجام دهید. "
        12 -> "زمان سفارش پک جدید همزمان سازی فرا رسیده است لطفاً برای این موضوع اقدام فرمایید و تیک مربوطه را در اپلیکیشن علامت بگذارید."
        else -> "گروه ${group.groupName} به تاریخ ${group.dateOfStartShamsi} نیاز به توجه دارد."
    }
}