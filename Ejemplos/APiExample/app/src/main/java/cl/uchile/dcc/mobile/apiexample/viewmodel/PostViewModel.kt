package cl.uchile.dcc.mobile.apiexample.viewmodel

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
            _postsList.value = repository.getPosts()
        }
    }
}