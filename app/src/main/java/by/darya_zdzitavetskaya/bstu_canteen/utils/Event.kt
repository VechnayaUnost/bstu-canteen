package by.darya_zdzitavetskaya.bstu_canteen.utils

class Event<out T>(private val content: T?) {

    var handled: Boolean = false

    val data: T?
        get() {
            return if (handled) {
                null
            } else {
                handled = true
                content
            }
        }
}

fun <T> T.toEvent(): Event<T> {
    return Event(this)
}