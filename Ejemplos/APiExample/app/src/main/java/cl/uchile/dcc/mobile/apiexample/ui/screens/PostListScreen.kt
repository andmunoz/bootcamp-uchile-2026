package cl.uchile.dcc.mobile.apiexample.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cl.uchile.dcc.mobile.apiexample.ui.components.CardBlog
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
        val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { viewModel.refreshPosts() },
                enabled = !isRefreshing,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Actualizar"
                )
            }
        }
        /* when (syncStatus?.state) {
            WorkInfo.State.RUNNING -> Text("Sincronizando...")
            WorkInfo.State.SUCCEEDED -> Text("Sincronización exitosa!")
            WorkInfo.State.FAILED -> Text("Sincronización fallida!")
            else -> {}
        } */
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = {
                viewModel.refreshPosts()
            }
        ) {
            if (post.isEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "No hay publicaciones")
                }
            }
            else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()

                ) {
                    items(post.size) { index ->
                        CardBlog(post[index], viewModel)
                    }
                }
            }
        }
    }
}