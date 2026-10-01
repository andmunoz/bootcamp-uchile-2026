package cl.uchile.dcc.mobile.apiexample.data.firebase

import com.google.firebase.database.FirebaseDatabase

object FirebaseConfig {
    val database: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }

    fun getPostsRef() = database.getReference("posts")
    fun getPostRef(postId: String) = database.getReference("posts/$postId")
}