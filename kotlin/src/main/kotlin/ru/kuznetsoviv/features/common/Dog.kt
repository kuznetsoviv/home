package ru.kuznetsoviv.features.common

/**
 * С помощью деклараций val или var перед именем переменной можно указать, что эта переменная будет свойством класа.
 * Если не указывать val или var, то переменная передается в конструктор, как простой параметр функции
 */
class Dog(val name: String, weight: Int, breed: String) {

    // Пример объявления дополнительного конструктора.
    // Каждый вторичный конструктор должен делегировать управление первичному (если он есть).
    constructor(name: String) : this(name, 2, "f") {
        // код, который выполняется при вызове вторичного конструктора
    }

    // Все свойства, определяемые в классе должны инициализироваться
    var breed = "Dog $breed"

    // Пример определения свойства component1
    operator fun component1(): String = breed

    // префикс lateinit позволяет указать свойство без его начальной инициализации
    lateinit var masterName: String

    // пример использования сеттора для свойства
    var weight = weight
        set(value) {
            if (value > 0) field = value
        }

    // пример определения геттера для свойства
    val weightInKgs: Double
        get() = weight / 2.2

    /**
     * Блок инициализации выполняется после вызова конструктораю.
     * var weight = weight * 1.5 - выполниться раньше (порядок выполнения опеределяется порядком инициализации)
     */
    init {
        println("create dog")
    }

    fun bark() {
        println(if (weight < 20) "Yip!" else "Wolf!")
    }
}

fun main() {
    val dog = Dog("Fido", 10, "Mixed")
    println(dog.name)
    dog.bark()
    dog.weight = 2
    println(dog.weight)
}