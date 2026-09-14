package cl.uchile.dcc.mobile.apiexample.data

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PostApiService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>

    @GET("/posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post

    @POST("/posts")
    suspend fun createPost(post: Post): Post

    @PUT("/posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, post: Post): Post

    @PATCH("/posts/{id}")
    suspend fun patchPost(@Path("id") id: Int, post: Post): Post

    @DELETE("/posts/{id}")
    suspend fun deletePost(@Path("id") id: Int)
}