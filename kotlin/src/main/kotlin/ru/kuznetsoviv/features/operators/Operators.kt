package ru.kuznetsoviv.features.operators

import ru.kuznetsoviv.features.oop.animals.Animal
import ru.kuznetsoviv.features.oop.animals.Hippo
import ru.kuznetsoviv.features.oop.animals.Wolf

/**
 * Оператор when - используется, как switch в java
 */
fun exampleWhen() {
    val animals = arrayListOf(Wolf(), Hippo())
    for (animal in animals) {
        when (animal) {
            is Wolf -> println("It is wolf")
            is Hippo -> println("It is Hippo")
            else -> println("Unknown type")
        }
    }
}

/**
 * Оператор is - умное приведение типа.
 * Оператор as - простое приведение типа.
 */
fun exampleIsAs(animal: Animal) {
    if (animal is Wolf) {
        animal.eat()
    }
    val wolf = animal as Wolf
    wolf.eat()
}

/**
 * Оператор as? - приводит к типу, если арумент имеет подходящий тип, иначе возвращает null.
 */
fun exampleNotNullAs(animal: Animal) {
    val wolf = animal as? Wolf
    wolf?.eat()
}


/**
 * Пример использования Элвис оператора.
 */
fun exampleElvis() {
    val test: String? = null
    println(test ?: "Elvis")
}

/**
 * Оператор !! намеренно выдает NullPointerException
 */
fun exampleNullPointerException() {
    val test: String? = null
    println(test!!.length)
}

/**
 * Пример использования проверки в функции Interval.
 */
fun setWorkRatePercentage(x: Int): Int {
    if (x !in 1..100) {
        throw IllegalArgumentException("Percentage not in range 0..100 $x")
    }
    return 2 * x
}
