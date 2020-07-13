package ru.kuznetsoviv.features.oop.animals

import ru.kuznetsoviv.features.oop.api.Roamable

/**
 * С помощью ключевого слова open можно указать поля и методы в классе, которые могут быть переопределены в потомках.
 */
abstract class Animal : Roamable {

    // у абстрактного свойства нельзя переопределить методы get или set
    abstract val image: String
    abstract val food: String
    abstract val habitat: String
    open val hunger = 10

    abstract fun makeNoise()

    abstract fun eat()

    override fun roam() {
        println("The Animal is roaming")
    }

    fun sleep() {
        println("Th Animal is sleeping")
    }

}
