package cl.uchile.dcc.mobile.apiexample.data.api

sealed class PostApiResponse<out T> {
    data class Success<out T>(val data: T) : PostApiResponse<T>()
    data class Error(val code: Int, val message: String) : PostApiResponse<Nothing>()
    object NetworkError : PostApiResponse<Nothing>()
}
