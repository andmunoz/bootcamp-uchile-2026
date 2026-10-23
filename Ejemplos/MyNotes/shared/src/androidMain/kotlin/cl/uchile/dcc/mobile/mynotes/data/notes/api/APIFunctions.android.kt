package cl.uchile.dcc.mobile.mynotes.data.notes.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun createHttpClient(url: String): HttpClient {
    val httpClient = HttpClient(OkHttp) {
        // 1. Serialización JSON
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }

        // 2. Timeouts
        install(HttpTimeout) {
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 15_000
        }

        // 3. Logging (solo en debug)
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.BODY
        }

        // 4. Peticiones por defecto
        defaultRequest {
            url(url)
            contentType(ContentType.Application.Json)
        }
    }
    return httpClient
}