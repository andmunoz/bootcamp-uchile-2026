@file:Suppress("KaptKotlinCompilerPlugin")

package cl.uchile.dcc.mobile.apiexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import cl.uchile.dcc.mobile.apiexample.data.PostRepository
import cl.uchile.dcc.mobile.apiexample.data.firebase.PostRemoteDataSource
import cl.uchile.dcc.mobile.apiexample.data.cache.PostLocalDataSource
import cl.uchile.dcc.mobile.apiexample.ui.PostsScreenApp
import cl.uchile.dcc.mobile.apiexample.ui.theme.APiExampleTheme
import cl.uchile.dcc.mobile.apiexample.viewmodel.PostSyncWorkManager
import cl.uchile.dcc.mobile.apiexample.viewmodel.PostViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Creamos el Repository de artículos con una fuente local y una remota
        val postRepository = PostRepository(
            remoteDataSource = PostRemoteDataSource(),
            localDataSource = PostLocalDataSource(this)
        )

        // Creamos el ViewModel con el Repository
        val postViewModel = PostViewModel(postRepository)

        // Creamos el WorkManager para sincronizar los artículos y lo programamos
        val syncWorker = PostSyncWorkManager("Post_Sync")
        syncWorker.cancelAllWorkers(this)
        syncWorker.schedulePeriodicSync(this)

        // Construimos la interfaz ahora con el postViewModel creado
        setContent {
            APiExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PostsScreenApp(
                        viewModel = postViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
