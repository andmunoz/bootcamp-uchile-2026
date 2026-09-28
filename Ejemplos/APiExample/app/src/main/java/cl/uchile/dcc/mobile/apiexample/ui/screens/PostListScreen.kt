package cl.uchile.dcc.mobile.apiexample.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cl.uchile.dcc.mobile.apiexample.viewmodel.PostViewModel

@Composable
fun PostListScreen(
    viewModel: PostViewModel,
    modifier: Modifier
) {
    val post by viewModel.postList.collectAsStateWithLifecycle()
    // val syncStatus by viewModel.observeSyncStatus(LocalContext.current).collectAsState(initial = null)
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { viewModel.refreshPosts() }
            ) {
                Text(text = "Cargar Artículos")
            }
        }
        /* when (syncStatus?.state) {
            WorkInfo.State.RUNNING -> Text("Sincronizando...")
            WorkInfo.State.SUCCEEDED -> Text("Sincronización exitosa!")
            WorkInfo.State.FAILED -> Text("Sincronización fallida!")
            else -> {}
        } */
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(post.size) { index ->
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = post[index].title.trim()
                )
            }
        }
    }
}