package cl.uchile.dcc.mobile.apiexample.data

data class PostResult (
    val items: List<Post>,
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int
)