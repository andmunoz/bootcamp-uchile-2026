package cl.uchile.dcc.mobile.mytraveldiary.data.notifications

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import cl.uchile.dcc.mobile.mytraveldiary.MainActivity
import cl.uchile.dcc.mobile.mytraveldiary.R
import cl.uchile.dcc.mobile.mytraveldiary.data.database.VisitedPlace
import kotlin.jvm.java

class NotificationRepository(
    private val context: Context
) {
    companion object {
        const val CHANNEL_VISITS = "visits"
        const val CHANNEL_REMINDERS = "reminders"
        const val CHANNEL_SYSTEM = "system"
    }

    fun createChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelVisits = NotificationChannel(
                CHANNEL_VISITS,
                "Registro de Visitas",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Canal de Visitas"
            }
            val channelReminders = NotificationChannel(
                CHANNEL_REMINDERS,
                "Recordatorios",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Canal de Recordatorios"
            }
            val channelSystem = NotificationChannel(
                CHANNEL_SYSTEM,
                "Sistema",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Canal del Sistema"
            }
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channelVisits)
            notificationManager.createNotificationChannel(channelReminders)
            notificationManager.createNotificationChannel(channelSystem)
        }
    }

    fun hasPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    @SuppressLint("MissingPermission")
    fun sendSystemNotification(title: String, text: String) {
        if (!hasPermission()) return

        val intent = Intent(
            context,
            MainActivity::class.java
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("visit", title)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(
            context,
            CHANNEL_VISITS
        )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat
            .from(context)
            .notify(
                0,
                notification
            )
    }

    @SuppressLint("MissingPermission")
    fun sendVisitNotification(visit: VisitedPlace) {
        if (!hasPermission()) return

        val intent = Intent(
            context,
            MainActivity::class.java
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("visit", visit.id)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(
            context,
            CHANNEL_VISITS
        )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Nueva Visita")
            .setContentText("Nueva visita registrada: ${visit.nombre}")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat
            .from(context)
            .notify(
                visit.id,
                notification
            )
    }
}