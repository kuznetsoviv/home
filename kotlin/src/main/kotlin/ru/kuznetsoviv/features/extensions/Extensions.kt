package ru.kuznetsoviv.features.extensions

fun Double.toDollar(): String {
    return "$$this"
}

val String.halfLength
    get() = length / 2

fun main() {
    println(5.5.toDollar())
    println("test".halfLength)
}