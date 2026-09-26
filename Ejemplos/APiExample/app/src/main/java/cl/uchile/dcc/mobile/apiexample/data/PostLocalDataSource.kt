package cl.uchile.dcc.mobile.apiexample.data

class PostLocalDataSource {
    private val posts = mutableListOf<Post>()

    fun getPosts(): List<Post> {
        return posts
    }

    suspend fun insertAllPosts(posts: List<Post>) {
        this.posts.clear()
        this.posts.addAll(posts)
    }
}