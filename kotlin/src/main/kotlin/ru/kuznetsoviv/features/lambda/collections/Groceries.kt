package ru.kuznetsoviv.features.lambda.collections

/**
 * Пример использования лямбда выражений в коллекциях.
 */
data class Groceries(
    val name: String,
    val category: String,
    val unit: String,
    val unitPrice: Double,
    val quantity: Int
)

fun main() {
    val groceries = listOf(
        Groceries("Tomatoes", "Vegetable", "lb", 3.0, 3),
        Groceries("Mushrooms", "Vegetable", "lb", 4.0, 1),
        Groceries("Bagels", "Bakery", "Pack", 1.5, 2),
        Groceries("Olive oil", "Pantry", "Bottle", 6.0, 1),
        Groceries("Ice cream", "Frozen", "Pack", 3.0, 2)
    )

    println("Max price is ${groceries.maxBy { it.unitPrice }}")
    println("Sum all groceries is ${groceries.sumByDouble { it.unitPrice }}")

    val vegetables = groceries.filter { it.category == "Vegetable" }
    println("vegetables: $vegetables")
    val notFrozen = groceries.filterNot { it.category == "Frozen" }
    println("notFrozen: $notFrozen")

    val groceryNames = groceries.map { it.name }
    println("groceryNames $groceryNames")
    val halfUnitPrices = groceries.map { it.unitPrice * 0.5 }
    println("halfUnitPrices $halfUnitPrices")

    println("Groceries with unitPrice > 3.0: ")
    groceries.filter { it.unitPrice > 3.0 }
        .forEach { println(it.name) }

    groceries.groupBy { it.category }.forEach {
        println(it.key)
        it.value.forEach { println("   ${it.name}") }
    }

    val changeFrom50 = groceries.fold(50.0) { change, item -> change - item.unitPrice * item.quantity }
    println("changeFrom50: $changeFrom50")

}