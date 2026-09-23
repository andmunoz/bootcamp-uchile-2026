package cl.uchile.dcc.mobile.apiexample.data

import android.util.Log
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class PostRepository {
    private val apiService = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PostApiService::class.java)

    suspend fun getPosts(): List<Post> {
        return try {
            apiService.getPosts()
        } catch (e: IOException) {
            throw Exception("Servicio no disponible")
        } catch (e: HttpException) {
            when (e.code()) {
                401 -> throw Exception("Autenticación fallida")
                403 -> throw Exception("No tiene permisos para acceder al servicio")
                404 -> throw Exception("Recurso no encontrado")
                500 -> throw Exception("Error interno del servidor")
                else -> throw Exception("Error desconocido (Status Code: ${e.code()}")
            }
        } catch (e: Exception) {
            throw Exception("Error desconocido: ${e.message}")
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

    suspend fun getPostByUserId(userId: Int): List<Post> {
        val response = apiService.getPostsByUserId(userId)
        if (response.isSuccessful) {    // Status Code 200
            return response.body() ?: emptyList()
        } else {                        // Status Code 400, 401, 403, 404, 500
            throw Exception("Error al obtener los posts del usuario")
        }
    }

    suspend fun crearPost(post: Post): Post {
        return apiService.createPost(post)
    }

    suspend fun updatePost(id: Int, post: Post): Post {
        return apiService.updatePost(id, post)
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