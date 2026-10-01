package cl.uchile.dcc.mobile.apiexample.data

import android.util.Log
import cl.uchile.dcc.mobile.apiexample.data.firebase.PostRemoteDataSource
import cl.uchile.dcc.mobile.apiexample.data.cache.Post
import cl.uchile.dcc.mobile.apiexample.data.cache.PostLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class PostRepository(
    private val remoteDataSource: PostRemoteDataSource,     // Retrofit
    private val localDataSource: PostLocalDataSource        // Room
) {
    val postsList: Flow<List<Post>> = localDataSource.getPosts()

    suspend fun refreshPost() {
        try {
            // Leer Post remotos
            Log.d("PostRepository", "Actualizando caché")
            val remotePosts = remoteDataSource.getPosts().getOrNull()
            Log.d("PostRepository", "${remotePosts?.size} posts obtenidos")
            for (post in remotePosts!!) {
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

            // Sincronizar desde Local al Remoto
            val localPosts = localDataSource.getPosts().firstOrNull()
            localPosts?.forEach { post ->
                val p = remotePosts.firstOrNull { it.id == post.id }
                if (p == null) {
                    val t = remoteDataSource.addPost(post)
                }
            }

            // Sincronizar desde RTDB a Local
            remotePosts.forEach { post ->
                val p = localDataSource.getPost(post.id).firstOrNull()
                if (p == null) {
                    localDataSource.addPost(post)
                } else {
                    localDataSource.updatePost(post)
                }
            }

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