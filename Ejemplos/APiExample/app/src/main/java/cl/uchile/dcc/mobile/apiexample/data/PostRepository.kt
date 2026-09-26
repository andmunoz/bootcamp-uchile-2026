package cl.uchile.dcc.mobile.apiexample.data

import android.util.Log
import cl.uchile.dcc.mobile.apiexample.data.api.PostRemoteDataSource
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
            val posts = remoteDataSource.getPosts().map {
                Post(
                    remoteId = it.id,
                    title = it.title,
                    description = it.description,
                    active = it.active,
                    created = it.created,
                    updated = it.updated
                )
            }
            localDataSource.updateAllPosts(posts)
            Log.d("PostRepository", "Caché actualizado")
        } catch (e: Exception) {
            Log.e("PostRepository", "Error al obtener los posts del usuario (${e.message})")
        }
    }
}