package cl.uchile.dcc.mobile.apiexample.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.dcc.mobile.apiexample.data.Post
import cl.uchile.dcc.mobile.apiexample.data.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(
    private val repository: PostRepository
) : ViewModel() {
    private val _postsList = MutableStateFlow<List<Post>>(value = emptyList())
    val postsList = _postsList.asStateFlow()

    fun loadPosts() {
        viewModelScope.launch {
            try {
                _postsList.value = repository.getPosts()
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error al cargar los posts (${e.message})", e)
                _postsList.value = emptyList()
            }
        }
    }
}