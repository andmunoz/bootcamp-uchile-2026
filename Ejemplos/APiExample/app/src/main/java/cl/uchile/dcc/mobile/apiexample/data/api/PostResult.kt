package cl.uchile.dcc.mobile.apiexample.data.api

import android.text.style.ImageSpan

data class PostResult (
    val items: List<Post>,
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int
)

data class Post (
    val id: String,
    val title: String,
    val description: String,
    val collectionId: String,
    val collectionName: String,
    val options: List<String>,
    val featuredImages: List<String>,
    val active: Boolean,
    val created: String,
    val updated: String
)