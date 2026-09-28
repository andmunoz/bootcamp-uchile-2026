package cl.uchile.dcc.mobile.apiexample.viewmodel

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import cl.uchile.dcc.mobile.apiexample.data.PostSyncWorker
import java.util.concurrent.TimeUnit

class PostSyncWorkManager(
    workerUniqueName: String
) {
    val uniqueName = workerUniqueName

    fun syncNow(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
        val syncRequest = OneTimeWorkRequestBuilder<PostSyncWorker>()
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(context).enqueue(syncRequest)
    }

    fun schedulePeriodicSync(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
        val syncRequest = PeriodicWorkRequestBuilder<PostSyncWorker>(
            15,
            TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setBackoffCriteria(
                androidx.work.BackoffPolicy.LINEAR,
                15,
                TimeUnit.MINUTES
            )
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            uniqueName,
            ExistingPeriodicWorkPolicy.KEEP,
            syncRequest
        )
    }

    fun cancelWorker(context: Context) {
        WorkManager.getInstance(context).cancelUniqueWork(uniqueName)
    }

    fun cancelAllWorkers(context: Context) {
        WorkManager.getInstance(context).cancelAllWork()
    }
}