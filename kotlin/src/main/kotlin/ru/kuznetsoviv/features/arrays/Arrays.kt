package ru.kuznetsoviv.features.arrays

/**
 * Пример функции с возможностями массивов в kotlin.
 */
fun arrays() {
    var array = arrayOf(1, 3, 2)
    var nullArray: Array<String?> = arrayOfNulls(2)
    var size = array.size
    var reverseArray = array.reverse()
    var isIn = array.contains(1)
    var sum = array.sum()
    var average = array.average()
}

fun lists() {
    // unmutable list example
    var list = listOf("One, Two")
    println(list.contains("Two"))
    var mutableList = mutableListOf("One", "Two")
    mutableList[0] = "Three"
}

fun maps() {
    val recipeMap = mutableMapOf("Recipe1" to "Chicken", "Recipe2" to "Salad", "Recipe3" to "Curry")
    recipeMap.putIfAbsent("Recipe3", "Raspberry")
}