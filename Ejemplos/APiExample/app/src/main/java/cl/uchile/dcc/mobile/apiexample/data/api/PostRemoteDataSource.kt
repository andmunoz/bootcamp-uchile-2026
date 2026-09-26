package cl.uchile.dcc.mobile.apiexample.data.api

import android.util.Log
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class PostRemoteDataSource {
    private val apiService = Retrofit.Builder()
        .baseUrl("https://pocketbase.io/api/collections/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PostApiService::class.java)

    suspend fun getPosts(): List<Post> {
        val response = safeApiCall { apiService.getPosts("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb2xsZWN0aW9uSWQiOiJwYmNfMzE0MjYzNTgyMyIsImV4cCI6MTc5MDM4MDI2MiwiaWQiOiJLVDQ1YnpOQTM0MFhhN0wiLCJyZWZyZXNoYWJsZSI6dHJ1ZSwidHlwZSI6ImF1dGgifQ._vYeP1IDf3AVBdwEYFXfwf0A8OvvV5Ihlr6jns17G_M") }
        if (response is PostApiResponse.Success) {
            return response.data.items
        } else {
            throw Exception("Error al obtener los posts del usuario")
        }
    }

    suspend fun getPostById(id: Int): Post {
        val response = safeApiCall { apiService.getPostById(id) }
        if (response is PostApiResponse.Success) {
            return response.data
        } else {
            throw Exception("Error al obtener el post")
        }
    }

    suspend fun crearPost(post: Post): Post {
        val response = safeApiCall { apiService.createPost(post) }
        if (response is PostApiResponse.Success) {
            return response.data
        } else {
            throw Exception("Error al obtener los posts del usuario")
        }
    }

    suspend fun updatePost(id: Int, post: Post): Post {
        val response = safeApiCall { apiService.updatePost(id, post) }
        if (response is PostApiResponse.Success) {
            return response.data
        } else {
            throw Exception("Error al obtener los posts del usuario")
        }
    }

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): PostApiResponse<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                PostApiResponse.Success(response.body()!!)
            } else {
                Log.e("PostRepository", "Error al obtener los posts del usuario (${response.code()})")
                PostApiResponse.Error(response.code(), response.message())
            }
        } catch (e: IOException) {
            Log.e("PostRepository", "Error al obtener los posts del usuario (${e.message})")
            PostApiResponse.NetworkError
        } catch (e: Exception) {
            Log.e("PostRepository", "Error al obtener los posts del usuario (${e.message})")
            PostApiResponse.Error(-1, e.message ?: "Error desconocido")
        }
    }
}