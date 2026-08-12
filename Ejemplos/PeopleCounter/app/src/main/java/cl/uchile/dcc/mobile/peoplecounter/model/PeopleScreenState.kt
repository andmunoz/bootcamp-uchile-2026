package cl.uchile.dcc.mobile.peoplecounter.model

sealed class PeopleScreenState {
    object Loading: PeopleScreenState()
    object Empty: PeopleScreenState()
    object Saving: PeopleScreenState()
    data class Success(val people: List<PersonRegistry>, val counter: Int) : PeopleScreenState()
    data class Error(val message: String) : PeopleScreenState()
}
