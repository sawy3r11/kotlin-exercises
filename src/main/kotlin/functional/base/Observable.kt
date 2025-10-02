package functional.base.observable

import junit.framework.TestCase.assertTrue
import org.junit.Test
import kotlin.test.assertEquals

class Observable<T>(initial: T, val observers: MutableList<(T) -> Unit> = mutableListOf()) {
    fun observe(function: (T) -> Unit) {
        addObserver(function)
    }

    private fun addObserver(function: (T) -> Unit) {
        observers.add(function)
    }

    var value: T = initial
        set(newValue) {
            field = newValue
            notifyObservers(newValue)
        }

    private fun notifyObservers(newValue: T) {
        observers.forEach { it(newValue) }
    }
}

fun main() {
    val observable = Observable(1,)
    println(observable.value) // 1
    observable.observe { println("Changed to $it") }
    observable.value = 2 // Changed to 2
    println(observable.value) // 2
    observable.observe { println("now it is $it") }
    observable.value = 3
    // Changed to 3
    // now it is 3
}

class ObservableTest {

    @Test
    fun `Observable should notify observers`() {
        val observable = Observable(1,)
        var notified = false
        observable.observe { notified = true }
        observable.value = 2
        assertTrue(notified)
    }

    @Test
    fun `Observable should notify all observers`() {
        val observable = Observable(1,)
        var notified1 = false
        var notified2 = false
        observable.observe { notified1 = true }
        observable.observe { notified2 = true }
        observable.value = 2
        assertTrue(notified1)
        assertTrue(notified2)
    }

    @Test
    fun `Observable should notify observers with new value`() {
        val observable = Observable(1,)
        var value = 0
        observable.observe { value = it }
        observable.value = 2
        assertEquals(2, value)
    }
}
