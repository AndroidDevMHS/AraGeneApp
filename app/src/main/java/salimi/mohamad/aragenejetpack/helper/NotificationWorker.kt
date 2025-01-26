package salimi.mohamad.aragenejetpack.helper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.data.model.FahliCheckList
import salimi.mohamad.aragenejetpack.data.model.PlannerItem
import salimi.mohamad.aragenejetpack.data.repository.PlannerRepository
import salimi.mohamad.aragenejetpack.screens.navGrph.Screens
import salimi.mohamad.aragenejetpack.ui.MainActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit


@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val repository: PlannerRepository
) : Worker(context, workerParams) {
    override fun doWork(): Result {

        val message = inputData.getString("MESSAGE") ?: "این پیام پیش‌فرض است."
        val groupId = inputData.getInt("GROUP_ID", -1)
        val text = inputData.getString("TEXT") ?: ""
        val day = inputData.getInt("DAY", -1)

        if (groupId == -1) {
            return Result.failure()
        }
        val ta = Calendar.getInstance()
        when (day) {
            40 -> ta.add(Calendar.DAY_OF_YEAR, 3)
            45 -> ta.add(Calendar.DAY_OF_YEAR, 3)
            227 -> ta.add(Calendar.DAY_OF_YEAR, 5)
            else -> ta.add(Calendar.DAY_OF_YEAR, 1)

        }


        sendNotification(message, groupId, context)

        CoroutineScope(Dispatchers.IO).launch {
            repository.addNewPlan(
                PlannerItem(
                    day = day,
                    message = text,
                    time = ta.time.toString()
                )
            )
        }
        return Result.success()
    }
    private fun sendNotification(message: String, groupId: Int, context: Context) {
        val channelId = "ARAGENE_CHANNEL"

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Scheduled Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(context, MainActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("app://${Screens.Planner.route}")
            putExtra("fromNotification", true)
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent,  PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("یادآوری")
            .setContentText(message)
            .setSmallIcon(R.drawable.logo)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(groupId, notification)
       // notificationManager.notify((System.currentTimeMillis().toInt() + groupId), notification)
    }
}


fun scheduleNotifications(
    context: Context,
    item: FahliCheckList,
    itemId: Int,
) {
    val sharedPreferences = context.getSharedPreferences("selectedPack", Context.MODE_PRIVATE)
    val selectedPackage = sharedPreferences.getInt("packNumber", 5)
    val workManager = WorkManager.getInstance(context)
    val notDay = listOf(0, selectedPackage-1, selectedPackage, selectedPackage+1, selectedPackage+16, 40, 45, 100, 111, 147, 210, 220, 227)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    val start: Date = try {
        dateFormat.parse(item.dateOfStartMiladi) ?: Date()
    } catch (e: Exception) {
        e.printStackTrace()
        return
    }

    notDay.forEachIndexed { _, day ->
        val calendar = Calendar.getInstance()
        // calendar.time = start
        //calendar.add(Calendar.DAY_OF_YEAR, day)
        //calendar.add(Calendar.HOUR_OF_DAY, 0)
        calendar.add(Calendar.MINUTE,1)
        calendar.add(Calendar.SECOND, day)
        calendar.add(Calendar.MILLISECOND, day)


        val now = Calendar.getInstance()
        val delay = calendar.timeInMillis - now.timeInMillis
        val formattedDate = dateFormat.format(calendar.time)

        val message: String
        val text: String
        when (day) {
            0 -> {
                message ="گروه همزمان سازی ${item.groupName}\n  فردا نیاز به تزریق هورمون دارد "
                text ="فردا روز شروع است. برای گروه ${item.groupName}\n سیدر گذاری و تزریق هورمون روز یک, دو سی سی برای هر دام باید انجام شود "

            }

            selectedPackage-1 -> {
                message ="گروه هم زمان سازی  ${item.groupName}\n فردا روز $selectedPackage ام است و نیاز به انجام اقدامی خاص است "
                text ="فردا باید به گروه ${item.groupName} \n هورمون روز $selectedPackage, به اندازه ۱.۲ سی سی و هورمون پودری به اندازه ۲ سی سی تزریق شود و سیدر برداری کنید. "
            }

            selectedPackage-> {
                message ="گروه همزمان سازی ${item.groupName} \nامروز روز $selectedPackage امه، اقدامات لازم انجام دهید. "
                text ="تزریق هورمون روز $selectedPackage, ۱.۲ سی سی و هورمون پودری ۲ سی سی و سیدر برداری را در گروه ${item.groupName}\n انجام دهید."
            }

            selectedPackage+1 -> {
                message = "گروه ${item.groupName} \n شروع قوچ اندازی "
                text = "در گروه ${item.groupName}\n قوچ را رها کنید. "
            }

            selectedPackage+16 -> {
                message =
                    "فردا 16 روز از سیدربرداری گروه ${item.groupName}\n میگذره نیاز به اقدام برای رها سازی قوچ است."
                text =
                    "فردا روز 16 بعد از سیدر برداری در گروه ${item.groupName}\n است لطفا قوچ را در این گروه به مدت سه روز رها کنید"
            }

            40 -> {
                message = "گروه ${item.groupName}\n 7 روز دیگر باید سونوگرافی شود "
                text = "لطفا گروه ${item.groupName}\n را 7 روز دیگر سونوگرافی کنید "
            }

            45 -> {
                message = "گروه ${item.groupName}\n 2 روز دیگر باید سونوگرافی شود "
                text =
                    "گروه ${item.groupName}\n2 روز دیگر باید سونوگرافی شود\n چنانچه به دکتر سونوگراف دسترسی ندارید تیک مربوطه را فعال کنید"
            }

            100 -> {
                message = "گروه ${item.groupName}\n در دوره رشد جفت است."
                text =
                    "اقدام بسیار مهم برای گروه ${item.groupName}\n رشد جفت در این بازه زمانی اتفاق می‌افتد و نسبت به آبستن سبک تغذیه باید تغییر کند، برای این منظور ویدیوی مربوط به این موضوع در اپلیکیشن اضافه شده است."
            }

            111 -> {
                message ="گروه ${item.groupName}\n نیاز به تزریق واکسن دارد."
                text ="طی یک هفته تا ۱۰ روز آینده باید واکسن آنتروتاکسمی به گروه ${item.groupName} تزریق شود، تغذیه مناسب آبستن سنگین و مواد معدنی باید انجام گردد"
            }

            147 -> {
                message = "حدود یک هفته از شروع زایمان های گروه ${item.groupName} میگذره !"
                text =
                    "حدود یک هفته از شروع زایمان های گروه ${item.groupName} میگذره و باید به نیازهای بره ها توجه کنید لطفا ویدئو زیر را مشاهده فرمایید."
            }

            /* 170 -> {
                 message ="بابت زایمانهای گروه ${item.groupName}\n تبریک ما را پذیرا باشید"
                 text ="بابت زایمانهای گروه ${item.groupName}\n تبریک ما را پذیرا باشید"
             }*/

            210 -> {
                message ="شروع از شیر گیری بره های حاصل از گروه ${item.groupName}"
                text ="از شیرگیری بره های حاصل از گروه ${item.groupName}\n را دو هفته دیگر یعنی دو و نیم ماه بعد از به دنیا آمدن برهها مطابق دستورالعمل از شیرگیری انجام دهید."
            }

            220 -> {
                message ="زمان خرید پک جدید هم زمان سازی برای گروه ${item.groupName}\n فرا رسیده است"
                text ="زمان خرید پک جدید هم زمان سازی برای گروه ${item.groupName}\n فرا رسیده است"
            }

            227 -> {
                message =
                    "زمان سفارش پک جدید همزمان سازی فرا رسیده است لطفاً برای این موضوع اقدام فرمایید"
                text =
                    "زمان سفارش پک جدید همزمان سازی فرا رسیده است لطفاً برای این موضوع اقدام فرمایید و تیک مربوطه را در اپلیکیشن علامت بگذارید"
            }

            else -> {
                message = "${item.groupName} موجود نیست "
                text = ""
            }
        }

        val data = Data.Builder()
            .putString("MESSAGE", message)
            .putString("TEXT", text)
            .putInt("DAY", day)
            .putInt("GROUP_ID", itemId)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInputData(data)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .addTag("ScheduledNotification:${itemId}")
            .build()

        workManager.enqueueUniqueWork(
            "ScheduledNotification:${itemId}_Day:${day}",
            ExistingWorkPolicy.KEEP,
            workRequest
        )
    }
}

fun cancelNotificationsForGroup(context: Context, groupId: Int) {
    val workManager = WorkManager.getInstance(context)
    workManager.cancelAllWorkByTag("ScheduledNotification:${groupId}")
}