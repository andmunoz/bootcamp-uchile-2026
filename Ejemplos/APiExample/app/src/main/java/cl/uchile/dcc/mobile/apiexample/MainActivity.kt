package cl.uchile.dcc.mobile.apiexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.apiexample.data.PostRepository
import cl.uchile.dcc.mobile.apiexample.ui.theme.APiExampleTheme
import cl.uchile.dcc.mobile.apiexample.viewmodel.PostViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val postRepository = PostRepository()
        val viewModel = PostViewModel(postRepository)
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
}

@Composable
fun Greeting(viewModel: PostViewModel, modifier: Modifier = Modifier) {
    val post by viewModel.postsList.collectAsState()
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = { viewModel.loadPosts() }
        ) {
            Text(text = "Cargar Artículos")
        }
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(post.size) { index ->
                Text(
                    modifier = modifier.padding(8.dp),
                    text = post[index].title
                )
            }
        }
    }
}