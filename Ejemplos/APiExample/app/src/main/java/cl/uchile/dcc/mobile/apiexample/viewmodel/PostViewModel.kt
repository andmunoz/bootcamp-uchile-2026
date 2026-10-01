package cl.uchile.dcc.mobile.apiexample.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.dcc.mobile.apiexample.data.cache.Post
import cl.uchile.dcc.mobile.apiexample.data.PostRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PostViewModel(
    private val repository: PostRepository
) : ViewModel() {
    val postList: StateFlow<List<Post>> = repository.postsList.catch { e ->
        emit(emptyList())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        refreshPosts()
    }

    fun refreshPosts() {
        viewModelScope.launch {
            try {
                repository.refreshPost()
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error al refrescar (${e.message})")
            }
        }
    }

    fun addPost() {
        val post = Post(
            id = 1,
            remoteId = "001",
            title = "Hola Mundo",
            description = "Esto es un post de pruebas",
            active = true,
            created = "",
            updated = ""
        )
        viewModelScope.launch {
            try {
                repository.addPost(post)
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error al agregar (${e.message})")
            }
        }
    }
}