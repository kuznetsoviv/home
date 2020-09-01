package ru.kuznetsoviv.features.companion

class Duck {

    // Создается один объект принадлежащий этому классу, используется всеми экземплярами этого класса.
    // Может использоваться как статический аналог на java.
    companion object {
        fun create(): Duck = Duck()
    }

}

fun main() {
    val duck = Duck.create()
    println(duck)
}