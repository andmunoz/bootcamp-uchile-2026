package cl.uchile.dcc.mobile.apiexample.data

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Response

interface PostApiService {
    @GET("/posts")          // Método HTTP GET
    suspend fun getPosts(): List<Post>

    @GET("/posts/{id}")     // Método HTTP GET
    suspend fun getPostById(
        @Path("id") id: Int
    ): Response<Post>

    @GET("/posts")
    suspend fun getPostsByUserId(
        @Query("userId") userId: Int
    ): Response<List<Post>>

    @POST("/posts")         // Método HTTP POST
    suspend fun createPost(
        @Body post: Post
    ): Post

    @PUT("/posts/{id}")     // Método HTTP PUT
    suspend fun updatePost(
        @Path("id") id: Int,
        @Body post: Post
    ): Post

    @PATCH("/posts/{id}")   // Método HTTP PATCH
    suspend fun patchPost(
        @Path("id") id: Int,
        @Body attributes: Map<String, String>
    ): Post

    @DELETE("/posts/{id}")  // Método HTTP DELETE
    suspend fun deletePost(
        @Path("id") id: Int
    )
}