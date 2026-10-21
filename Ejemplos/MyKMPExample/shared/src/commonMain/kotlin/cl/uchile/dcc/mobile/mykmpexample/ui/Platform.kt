package cl.uchile.dcc.mobile.mykmpexample.ui

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform