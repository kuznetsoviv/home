package ru.kuznetsoviv.features.oop.animals

class Wolf(): Animal() {

    override val image = "wolf.jpg"
    override val food = "meat"
    override val habitat = "forest"

    override fun makeNoise() {
        println("Howl")
    }

    override fun eat() {
        println("The wolf is eating $food")
    }

}