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
