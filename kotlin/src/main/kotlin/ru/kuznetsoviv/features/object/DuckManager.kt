package ru.kuznetsoviv.features.`object`

/**
 * object - это класс, для котого создается только один экземпляр.
 */
object DuckManager {

    val allDucks = mutableListOf<String>()

    fun addDuck(duck: String) {
        allDucks.add(duck)
    }

}