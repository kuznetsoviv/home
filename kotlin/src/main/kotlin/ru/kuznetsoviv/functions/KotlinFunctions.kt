package ru.kuznetsoviv.functions

fun main() {
    println(4 plus 5)
    val strFunc = { println()}
    val test = {c: Double -> c * 1.8}
    convert(20.0, test)
    val str = "rr";
    str.also { println("test") }
}

infix fun Int.plus(x: Int): Int = this + x

fun convert(x: Double, converter: (Double) -> Double) : Double {
    val result = converter(x)
    println("$x is converted to $result") // выводим результат
    return result // вернуть результат
}