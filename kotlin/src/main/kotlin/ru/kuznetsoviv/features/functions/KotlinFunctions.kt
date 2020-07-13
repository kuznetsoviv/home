package ru.kuznetsoviv.features.functions

fun main() {
    println(4 plus 5)
    val strFunc = { t: String -> println(t) }
    val test = { c: Double -> c * 1.8 }
    convert(20.0, test)
    testForeach()
}

infix fun Int.plus(x: Int): Int = this + x

fun convert(x: Double, converter: (Double) -> Double): Double {
    val result = converter(x)
    println("$x is converted to $result") // выводим результат
    return result // вернуть результат
}

fun testForeach() {
    val array = arrayOf("one", "two", "three")
    for ((index, value) in array.withIndex()) {
        println("Index $index has item $value")
    }
}

/**
 * Пример декларации функции.
 */
fun happyBirthday(name: String?, age: Int): String {
    return "Happy ${age}th birthday, $name"
}