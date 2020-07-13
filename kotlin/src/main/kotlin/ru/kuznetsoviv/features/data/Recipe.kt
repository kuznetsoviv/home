package ru.kuznetsoviv.features.data

/**
 * По умолчанию в data классах реализутся методы equals, toString и hashCode.
 * Также есть возможность копирования данных.
 * Класс данных предоставляет возможность деструкторизации.
 * В конструкторе можно добавлять свойства со значениями по умолчанию. Аналогично для функций.
 * При создании объекта можно явно указать, какие значения его свойств заполняются. Аналогично для функций.
 */
data class Recipe(val title: String, val isVegetarian: Boolean, val difficulty: String = "Easy")

fun main() {
    val r1 = Recipe("Chicken Bhuna", isVegetarian = false)
    val r2 = Recipe("Chicken Bhuna", false)
    println(r1 == r2)
    println(r1.toString())
    // функция копирования
    val r3 = r2.copy(isVegetarian = true)
    println(r3.copy())
    // деструкторизация данных
    val (title, vegetarian) = r3
    println("$title $vegetarian")
    // тоже самое
    // val title = r3.component1()
    // val vegetarian = r3.component2()
}
