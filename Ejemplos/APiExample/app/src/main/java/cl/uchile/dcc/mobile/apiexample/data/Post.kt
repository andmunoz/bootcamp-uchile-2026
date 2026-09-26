package cl.uchile.dcc.mobile.apiexample.data

data class Post(
    val active: Boolean,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val description: String,
    val id: String,
    val title: String,
    val updated: String,
    val options: List<String>,
    val featuredImages: List<String>
)
