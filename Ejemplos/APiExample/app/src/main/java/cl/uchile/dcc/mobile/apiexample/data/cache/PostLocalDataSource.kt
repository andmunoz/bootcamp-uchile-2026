package cl.uchile.dcc.mobile.apiexample.data.cache

import android.content.Context
import kotlinx.coroutines.flow.Flow

class PostLocalDataSource(
    context: Context
) {
    private val database = PostDatabase.getInstance(context)
    private val postDao = database.postDao()

    fun getPosts(): Flow<List<Post>> = postDao.getAllPosts()
    fun getPost(id: String): Flow<Post> = postDao.getPostById(id)

    suspend fun updateAllPosts(posts: List<Post>) {
        // IMPLEMENTAR RESOLUCIÓN DE CONFLICTOS
        // Caso 1: Nuevo Post Local => Sincronizar HACIA la nube
        // Caso 2: Post Local Eliminado => ?
        // Caso 3: Post Local Actualizado => Sincronizar HACIA la nube
        // Caso 4: Post Local y Remoto Actualizados => ?
        postDao.addAllPost(posts)
    }
}