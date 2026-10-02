package cl.uchile.dcc.mobile.apiexample.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.dcc.mobile.apiexample.data.cache.Post
import cl.uchile.dcc.mobile.apiexample.data.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    fun refreshPosts() {
        viewModelScope.launch {
            try {
                _isRefreshing.value = true
                repository.refreshPost()
                _isRefreshing.value = false
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error al refrescar (${e.message})")
            }
        }
    }

    fun addPost() {
        val dateFormater = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
        val post = Post(
            title = "Hola Mundo",
            description = "Esto es un post de pruebas",
            active = true,
            created = dateFormater.format(Date()),
            updated = dateFormater.format(Date())
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