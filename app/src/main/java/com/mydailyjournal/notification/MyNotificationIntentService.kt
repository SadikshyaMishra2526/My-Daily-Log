package com.mydailyjournal.notification

import androidx.core.app.NotificationManagerCompat

import android.app.PendingIntent
import android.content.Intent
import android.app.IntentService
import android.app.Notification
import com.mydailyjournal.R
import com.mydailyjournal.ui.activities.home.HomeActivity


 class MyNotificationIntentService : IntentService("MyNewIntentService") {
    override fun onHandleIntent(intent: Intent?) {
        val builder: Notification.Builder = Notification.Builder(this)
        builder.setContentTitle("My Title")
        builder.setContentText("This is the Body")
        builder.setSmallIcon(R.drawable.icon)
        val notifyIntent = Intent(this, HomeActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent)
        val notificationCompat: Notification = builder.build()
        val managerCompat = NotificationManagerCompat.from(this)
        managerCompat.notify(NOTIFICATION_ID, notificationCompat)
    }

    companion object {
        private const val NOTIFICATION_ID = 3
    }
}