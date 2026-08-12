package cl.uchile.dcc.mobile.peoplecounter.model

data class RegistryFormState(
    val nombre: String = "",
    val edad: Int = 0,
    val genero: String = "",
    val error: String? = null,
    val success: String? = null
)
