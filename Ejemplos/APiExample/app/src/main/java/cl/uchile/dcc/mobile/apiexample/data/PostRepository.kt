package cl.uchile.dcc.mobile.apiexample.data

import android.util.Log

class PostRepository(
    private val remoteDataSource: PostRemoteDataSource = PostRemoteDataSource(), // Retrofit
    private val localDataSource: PostLocalDataSource = PostLocalDataSource()     // List => Room
) {
    private var allPosts: List<Post> = localDataSource.getPosts()

    fun getPosts(): List<Post> {
        allPosts = localDataSource.getPosts()
        return allPosts
    }

    suspend fun refreshPost() {
        try {
            Log.d("PostRepository", "Actualizando caché")
            val posts = remoteDataSource.getPosts()
            localDataSource.insertAllPosts(posts)
            Log.d("PostRepository", "Caché actualizado")
        } catch (e: Exception) {
            Log.e("PostRepository", "Error al obtener los posts del usuario (${e.message})")
        }
    }
}