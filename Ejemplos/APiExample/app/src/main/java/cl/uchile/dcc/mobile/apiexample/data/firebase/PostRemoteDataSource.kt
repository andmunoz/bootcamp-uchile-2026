package cl.uchile.dcc.mobile.apiexample.data.firebase

import cl.uchile.dcc.mobile.apiexample.data.cache.Post
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class PostRemoteDataSource {
    private val database = FirebaseConfig.database

    suspend fun addPost(post: Post): Result<String> {
        return try {
            val ref = FirebaseConfig.getPostsRef().push()
            val postConId = post.copy(remoteId = ref.key ?: "")
            ref.setValue(postConId).await()
            Result.success(postConId.remoteId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPosts(): Result<List<Post>> {
        return try {
            val snapshot = FirebaseConfig.getPostsRef().get().await()
            val posts = snapshot.children.mapNotNull { it.getValue(Post::class.java) }
            Result.success(posts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updatePost(
        remotePostId: String,
        post: Post
    ): Result<Unit> {
        return try {
            val ref = FirebaseConfig.getPostRef(remotePostId)
            ref.setValue(post).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun removePost(
        remotePostId: String
    ): Result<Unit> {
        return try {
            val ref = FirebaseConfig.getPostRef(remotePostId)
            ref.removeValue().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun postListener(): Flow<List<Post>> = callbackFlow {
        val ref = FirebaseConfig.getPostsRef()
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val posts = dataSnapshot.children.mapNotNull { it.getValue(Post::class.java) }
                trySend(posts)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                close(databaseError.toException())
            }
        }
    }
}