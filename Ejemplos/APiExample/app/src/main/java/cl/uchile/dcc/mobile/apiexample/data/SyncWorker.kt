package cl.uchile.dcc.mobile.apiexample.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class SyncWorker @JvmOverloads constructor(
    context: Context,
    params: WorkerParameters,
    private val postRepository: PostRepository = PostRepository()
): CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            postRepository.refreshPost()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}