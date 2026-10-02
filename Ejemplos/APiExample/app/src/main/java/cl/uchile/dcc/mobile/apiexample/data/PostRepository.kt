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

            // Sincronizar desde Local al Remoto
            Log.d("PostRepository", "Guardando post locales en remoto")
            val localPosts = localDataSource.getPosts().firstOrNull()
            var counter = 0
            localPosts?.forEach { post ->
                val p = remotePosts?.firstOrNull { it.id == post.id }
                if (p == null) {
                    val r = remoteDataSource.addPost(post)
                    counter++
                }
            }
            Log.d("PostRepository", "$counter posts locales fueron guardados en remoto")

            // Sincronizar desde RTDB a Local
            Log.d("PostRepository", "Actualizando post remotos en local")
            var addCounter = 0
            var updateCounter = 0
            remotePosts?.forEach { post ->
                val p = localDataSource.getPost(post.id).firstOrNull()
                if (p == null) {
                    localDataSource.addPost(post)
                    addCounter++
                } else {
                    localDataSource.updatePost(post)
                    updateCounter++
                }
            }
            Log.d("PostRepository", "$addCounter posts remotos fueron agregados en local")
            Log.d("PostRepository", "$updateCounter posts remotos fueron actualizados en local")
        } catch (e: Exception) {
            Log.e("PostRepository", "Error al obtener los posts del usuario (${e.message})")
        }
    }

    suspend fun addPost(post: Post) {
        try {
            Log.d("PostRepository", "Agregando post")
            localDataSource.addPost(post)
            Log.d("PostRepository", "Post agregado")
        } catch (e: Exception) {
            Log.e("PostRepository", "Error al agregar el post (${e.message})")
        }
    }
}