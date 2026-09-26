package cl.uchile.dcc.mobile.apiexample

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import cl.uchile.dcc.mobile.apiexample.data.PostRepository
import cl.uchile.dcc.mobile.apiexample.data.SyncWorker
import cl.uchile.dcc.mobile.apiexample.ui.theme.APiExampleTheme
import cl.uchile.dcc.mobile.apiexample.viewmodel.PostViewModel
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val postRepository = PostRepository()
        val viewModel = PostViewModel(postRepository)
        // syncNow(this)
        schedulePeriodicSync(this)
        setContent {
            APiExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
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

@Composable
fun Greeting(viewModel: PostViewModel, modifier: Modifier = Modifier) {
    val post by viewModel.postsList.collectAsState()
    val syncStatus by viewModel.observeSyncStatus(LocalContext.current).collectAsState(initial = null)
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { viewModel.loadPosts() }
            ) {
                Text(text = "Cargar Artículos")
            }
            Button(
                onClick = { viewModel.clearPosts() }
            ) {
                Text(text = "Limpiar Artículos")
            }
        }
        when (syncStatus?.state) {
            WorkInfo.State.RUNNING -> Text("Sincronizando...")
            WorkInfo.State.SUCCEEDED -> Text("Sincronización exitosa!")
            WorkInfo.State.FAILED -> Text("Sincronización fallida!")
            else -> {}
        }
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(post.size) { index ->
                Text(
                    modifier = modifier.padding(8.dp),
                    text = post[index].title.trim()
                )
            }
        }
    }
}
