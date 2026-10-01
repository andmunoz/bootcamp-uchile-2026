package cl.uchile.dcc.mobile.apiexample.data

import android.util.Log
import cl.uchile.dcc.mobile.apiexample.data.firebase.PostRemoteDataSource
import cl.uchile.dcc.mobile.apiexample.data.cache.Post
import cl.uchile.dcc.mobile.apiexample.data.cache.PostLocalDataSource
import kotlinx.coroutines.flow.Flow

class PostRepository(
    private val remoteDataSource: PostRemoteDataSource,     // Retrofit
    private val localDataSource: PostLocalDataSource        // Room
) {
    val postsList: Flow<List<Post>> = localDataSource.getPosts()

    suspend fun refreshPost() {
        try {
            Log.d("PostRepository", "Actualizando caché")
            val posts = remoteDataSource.getPosts().getOrNull()
            Log.d("PostRepository", "${posts?.size} posts obtenidos")
            for (post in posts!!) {
                Log.d("PostRepository", "Post: ${post.title}")
                Post(
                    id = post.id,
                    remoteId = post.remoteId,
                    title = post.title,
                    description = post.description,
                    active = post.active,
                    created = post.created,
                    updated = post.updated
                )
            }
            localDataSource.updateAllPosts(posts)
            Log.d("PostRepository", "Caché actualizado")
        } catch (e: Exception) {
            Log.e("PostRepository", "Error al obtener los posts del usuario (${e.message})")
        }
    }

    suspend fun addPost(post: Post) {
        try {
            Log.d("PostRepository", "Agregando post")
            val newPost = remoteDataSource.addPost(post).getOrNull()
            Log.d("PostRepository", "Post agregado")
        } catch (e: Exception) {
            Log.e("PostRepository", "Error al agregar el post (${e.message})")
        }
    }
}