package cl.uchile.dcc.mobile.apiexample.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostRepository {
    private val apiService = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PostApiService::class.java)

    suspend fun getPosts(): List<Post> {
        return apiService.getPosts()
    }

    suspend fun getPostById(id: Int): Post {
        return apiService.getPostById(id)
    }
}