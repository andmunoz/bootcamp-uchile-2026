package cl.uchile.dcc.mobile.mynotes.data.notes.api

import io.ktor.client.HttpClient

expect fun createHttpClient(url: String): HttpClient