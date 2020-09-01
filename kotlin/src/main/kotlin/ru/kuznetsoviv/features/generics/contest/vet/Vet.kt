package ru.kuznetsoviv.features.generics.contest.vet

import ru.kuznetsoviv.features.generics.contest.pets.Pet

class Vet <T: Pet> {

    fun treat(pet: T) {
        println("Treat Pet ${pet.name}")
    }

}
