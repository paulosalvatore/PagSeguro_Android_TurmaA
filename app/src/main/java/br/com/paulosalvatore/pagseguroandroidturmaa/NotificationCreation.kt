package br.com.paulosalvatore.pagseguroandroidturmaa

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService

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

            // Build notification
            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentText(body)
                .setTicker(title)
                .build()

            notificationManager.notify(NOTIFY_ID, notification)
        }
    }
}
