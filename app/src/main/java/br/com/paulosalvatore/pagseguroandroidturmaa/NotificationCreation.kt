package br.com.paulosalvatore.pagseguroandroidturmaa

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

object NotificationCreation {
    private const val NOTIFY_ID = 1000
    private val vibration = longArrayOf(300, 400, 500, 400, 300)

    private var notificationManager: NotificationManager? = null

    private const val CHANNEL_ID = "KotlinPush_1"
    private const val CHANNEL_NAME = "KotlinPush - Push Channel 1"
    private const val CHANNEL_DESCRIPTION = "KotlinPush - Push Channel - Used for main notifications"

    fun create(context: Context, title: String, body: String) {
        if (notificationManager == null) {
            notificationManager = context
                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        notificationManager?.let { notificationManager ->
            // Channel
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var channel = notificationManager.getNotificationChannel(CHANNEL_ID)

                if (channel == null) {
                    channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)

                    channel.description = CHANNEL_DESCRIPTION
                    channel.enableVibration(true)
                    channel.enableLights(true)
                    channel.vibrationPattern = vibration

                    notificationManager.createNotificationChannel(channel)
                }
            }

            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

            // Build notification
            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentText(body)
                .setTicker(title)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setStyle(NotificationCompat.BigTextStyle().bigText(body))
                .setOnlyAlertOnce(true)
                .build()

            notificationManager.notify(NOTIFY_ID, notification)
        }
    }
}
