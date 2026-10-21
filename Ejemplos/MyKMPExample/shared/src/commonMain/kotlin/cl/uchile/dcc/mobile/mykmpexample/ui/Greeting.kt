package cl.uchile.dcc.mobile.mykmpexample.ui

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return sayHello(platform.name)
    }
}