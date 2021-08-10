package ru.kuznetsoviv.features.delegates

import kotlin.reflect.KProperty

/**
 * Делегирование в kotlin позволяет инициализировать переменную с помощью делегата,
 * у которого есть методы getValue и setValue.
 */
class Example {
    var p: String by Delegate()
    var value: Int = 0
    operator fun unaryPlus() {
        value++
    }
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

fun main() {
    val example = Example()
    +example
    println(example.value)
    print(example.p)
}
