package cl.uchile.dcc.mobile.apiexample.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.apiexample.data.cache.Post
import cl.uchile.dcc.mobile.apiexample.viewmodel.PostViewModel

@Composable
fun CardBlog(
    post: Post,
    viewModel: PostViewModel
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp),
        content = {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = post.title,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Creado el ${post.created}"
                    )
                    Text(
                        text = "Actualizado el ${post.updated}"
                    )
                }
            }
        }
    )
}