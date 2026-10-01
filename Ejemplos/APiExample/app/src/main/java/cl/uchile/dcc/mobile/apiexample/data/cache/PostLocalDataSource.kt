package cl.uchile.dcc.mobile.apiexample.data.cache

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class PostLocalDataSource(
    context: Context
) {
    private val database = PostDatabase.getInstance(context)
    private val postDao = database.postDao()

    fun getPosts(): Flow<List<Post>> = postDao.getAllPosts()
    fun getPost(id: Int): Flow<Post> = postDao.getPostById(id)
    suspend fun addPost(post: Post) = postDao.addPost(post)
    suspend fun updatePost(post: Post) = postDao.updatePost(post)
    suspend fun deletePost(post: Post) = postDao.deletePost(post)
}