package ru.kuznetsoviv.features.oop.animals

class Hippo() : Animal() {

    // final позволяет запрещать переопределение полей и методов в наследниках
    final override val image = "hippo.jpg"
    override val food = "grass"
    override val habitat = "water"

    override fun makeNoise() {
        println("Grunt! Grunt!")
    }

    override fun eat() {
        println("The Hippo is eating $food")
    }
}
