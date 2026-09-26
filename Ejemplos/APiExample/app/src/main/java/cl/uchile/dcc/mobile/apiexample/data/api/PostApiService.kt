package cl.uchile.dcc.mobile.apiexample.data.api

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.Response
import retrofit2.http.Header

interface PostApiService {
    @GET("posts/records")          // HTTP GET
    suspend fun getPosts(
        @Header("Authorization") token: String
    ): Response<PostResult>

    @GET("posts/records/{id}")     // HTTP GET
    suspend fun getPostById(
        @Path("id") id: Int
    ): Response<Post>

    @POST("posts/records")         // HTTP POST
    suspend fun createPost(
        @Body post: Post
    ): Response<Post>

    @PATCH("posts/records/{id}")     // HTTP PUT
    suspend fun updatePost(
        @Path("id") id: Int,
        @Body post: Post
    ): Response<Post>

    @DELETE("posts/records/{id}")  // HTTP DELETE
    suspend fun deletePost(
        @Path("id") id: Int
    )
}