package salimi.mohamad.aragenejetpack.helper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.screens.navGrph.Screens
import salimi.mohamad.aragenejetpack.ui.MainActivity
import salimi.mohamad.aragenejetpack.ui.pendingIntentO


fun sendNotification(message: String, groupId: Int, context: Context) {
    val channelId = "ARAGENE_CHANNEL"

    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    // ایجاد کانال نوتیفیکیشن برای اندروید ۸ به بالا
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

    // استفاده از groupId برای شناسه یکتا
    notificationManager.notify((System.currentTimeMillis().toInt() + groupId), notification)
}
