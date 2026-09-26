package cl.uchile.dcc.mobile.apiexample

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import cl.uchile.dcc.mobile.apiexample.data.PostRepository
import cl.uchile.dcc.mobile.apiexample.data.SyncWorker
import cl.uchile.dcc.mobile.apiexample.data.api.PostRemoteDataSource
import cl.uchile.dcc.mobile.apiexample.data.cache.PostLocalDataSource
import cl.uchile.dcc.mobile.apiexample.ui.PostsScreenApp
import cl.uchile.dcc.mobile.apiexample.ui.theme.APiExampleTheme
import cl.uchile.dcc.mobile.apiexample.viewmodel.PostViewModel
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val postRepository = PostRepository(
            remoteDataSource = PostRemoteDataSource(),
            localDataSource = PostLocalDataSource(this)
        )
        val viewModel = PostViewModel(postRepository)
        schedulePeriodicSync(this)
        setContent {
            APiExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PostsScreenApp(
                        viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    fun schedulePeriodicSync(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
        val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(
            1,
            TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setBackoffCriteria(
                androidx.work.BackoffPolicy.LINEAR,
                1,
                TimeUnit.MINUTES
            )
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "Post_Sync_2",
            ExistingPeriodicWorkPolicy.KEEP,
            syncRequest
        )
    }

    fun syncNow(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
        val syncRequest = OneTimeWorkRequestBuilder<SyncWorker>()
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(context).enqueue(syncRequest)
    }
}
